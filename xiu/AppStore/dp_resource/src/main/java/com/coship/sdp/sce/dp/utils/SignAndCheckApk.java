

/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.coship.sdp.sce.dp.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.pkcs.ContentInfo;
import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;
import sun.security.x509.AlgorithmId;
import sun.security.x509.X500Name;

/**
 * Command line tool to sign JAR files (including APKs and OTA updates) in a way
 * compatible with the mincrypt verifier, using SHA1 and RSA keys.
 */
public class SignAndCheckApk {
    private static boolean DEBUG = false;
    private static final int MAX_COMMENT_LEN = 0xFFFF;
    private static final int BUFFER_LENGTH = (8 * 1024);
    private static final String STRING_CERFORMAT = "cerFormat=X509";
    private static final String NEW_LINE = "\r\n";
    private static final String STRING_SIGN = "sign=";

    public static X509Certificate readPublicKey(File file) throws IOException,
            GeneralSecurityException {
        FileInputStream input = new FileInputStream(file);
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return (X509Certificate) cf.generateCertificate(input);
        } finally {
            input.close();
        }
    }

    /**
     * Reads the password from stdin and returns it as a string.
     * 
     * @param keyFile
     *            The file containing the private key. Used to prompt the user.
     */
    private static String readPassword(File keyFile) {
        // TODO: use Console.readPassword() when it's available.
        System.out.print("Enter password for " + keyFile
                + " (password will not be hidden): ");
        System.out.flush();
        // coship shenshaohui add ,2011-11-7, we set the passwd to static
        String password = "CoshipSTP";
        return password;
        // add end
    }

    /**
     * Decrypt an encrypted PKCS 8 format private key.
     * 
     * Based on ghstark's post on Aug 6, 2006 at
     * http://forums.sun.com/thread.jspa?threadID=758133&messageID=4330949
     * 
     * @param encryptedPrivateKey
     *            The raw data of the private key
     * @param keyFile
     *            The file containing the private key
     */
    private static KeySpec decryptPrivateKey(byte[] encryptedPrivateKey,
            File keyFile) throws GeneralSecurityException {
        EncryptedPrivateKeyInfo epkInfo;
        try {
            epkInfo = new EncryptedPrivateKeyInfo(encryptedPrivateKey);
        } catch (IOException ex) {
            // Probably not an encrypted key.
            return null;
        }

        char[] password = readPassword(keyFile).toCharArray();

        SecretKeyFactory skFactory = SecretKeyFactory.getInstance(epkInfo
                .getAlgName());
        Key key = skFactory.generateSecret(new PBEKeySpec(password));

        Cipher cipher = Cipher.getInstance(epkInfo.getAlgName());
        cipher.init(Cipher.DECRYPT_MODE, key, epkInfo.getAlgParameters());

        try {
            return epkInfo.getKeySpec(cipher);
        } catch (InvalidKeySpecException ex) {
            System.err.println("signapk: Password for " + keyFile
                    + " may be bad.");
            throw ex;
        }
    }

    /** Read a PKCS 8 format private key. */
    public static PrivateKey readPrivateKey(File file) throws IOException,
            GeneralSecurityException {
        DataInputStream input = new DataInputStream(new FileInputStream(file));
        try {
            if (DEBUG)
                System.out.println("file.getName()=" + file.getName());
            System.out.println("");
            byte[] bytes = new byte[(int) file.length()];
            input.read(bytes);

            KeySpec spec = decryptPrivateKey(bytes, file);
            if (spec == null) {
                spec = new PKCS8EncodedKeySpec(bytes);
            }

            try {
                return KeyFactory.getInstance("RSA").generatePrivate(spec);
            } catch (InvalidKeySpecException ex) {
                return KeyFactory.getInstance("DSA").generatePrivate(spec);
            }
        } finally {
            input.close();
        }
    }

    private static void writeSignatureBlock(Signature signature,
            X509Certificate publicKey, OutputStream out) {
        try {
            byte[] sign = signature.sign();
            if (DEBUG)
                printHexString(sign, sign.length);
            try {
                try {
                    SignerInfo signerInfo = new SignerInfo(new X500Name(
                            publicKey.getIssuerX500Principal().getName()),
                            publicKey.getSerialNumber(), AlgorithmId
                                    .get("SHA1"), AlgorithmId.get("RSA"), sign);

                    PKCS7 pkcs7 = new PKCS7(new AlgorithmId[] { AlgorithmId
                            .get("SHA1") }, new ContentInfo(
                            ContentInfo.DATA_OID, null),
                            new X509Certificate[] { publicKey },
                            new SignerInfo[] { signerInfo });

                    pkcs7.encodeSignedData(out);

                } catch (NoSuchAlgorithmException e) {
                    System.err
                            .println("error while new struct SignerInfo or PKCS7");
                }
            } catch (IOException e) {
                System.err.println("error while use pkcs7 encodeSignedData");
            }

        } catch (SignatureException e) {
            System.err.println("error while get sign");
        }
    }

    public static void printHexString(byte[] b, int len) {
        int printLen = 0;
        if (len <= 0) {
            printLen = b.length;
        } else {
            printLen = len;
        }
        System.out.println("printHexString printLen " + printLen);
        if (printLen > b.length) {
            System.out.println(" error , printLen " + printLen
                    + "should not big than bufferlen " + b.length);
            return;
        }
        for (int i = 0; i < printLen; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(" " + hex.toUpperCase());
        }
        System.out.println("");
    }

    public static void printString(byte[] b, int len) {
        int printLen = 0;
        if (len <= 0) {
            printLen = b.length;
        } else {
            printLen = len;
        }

        if (printLen > b.length) {
            System.out.println(" error , printLen " + printLen
                    + "should not big than bufferlen " + b.length);
            return;
        }
        char hex;
        for (int i = 0; i < printLen; i++) {
            hex = (char) (b[i] & 0xFF);
            System.out.print("" + hex);
        }
        System.out.println("");
    }

    // delete the comment of the zip/apk file.
    public static boolean deleteFileComment(String sourceFileName,
            String fileComment, String targetFileName) throws IOException {
        if (!checkFileExists(sourceFileName)) {
            System.err
                    .println("error, in param file not exit, or the input param is null");
            return false;
        }
        if (fileComment == null) {
            System.err.println("error, in param fileComment=" + fileComment);
            return false;
        }

        File sourceFile = null;
        File targetFile = null;
        sourceFile = new File(sourceFileName);
        targetFile = new File(targetFileName);

        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input,
                BUFFER_LENGTH);

        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output,
                BUFFER_LENGTH);

        byte[] b = new byte[BUFFER_LENGTH];
        int len;
        long totalWriteLength = 0;
        long writeLength;
        long copyDataLength = input.available() - fileComment.length() - 2;
        if (DEBUG)
            System.out.println("fileComment=" + fileComment);
        if (DEBUG)
            System.out.println("copyDataLength=" + copyDataLength
                    + ", input.available()=" + input.available()
                    + ", fileComment.length()" + fileComment.length());
        while ((totalWriteLength < copyDataLength)) {
            writeLength = Math.min((copyDataLength - totalWriteLength),
                    BUFFER_LENGTH);
            if ((len = inBuff.read(b, 0, (int) writeLength)) != -1) {
                outBuff.write(b, 0, len);
                totalWriteLength += len;
            } else {
                System.err.println("error,while copy file " + sourceFileName
                        + ", totalWriteLength =" + totalWriteLength
                        + ", writeLength= " + writeLength + ", len=" + len);
                inBuff.close();
                outBuff.close();
                output.close();
                input.close();
                b = null;
                input = null;
                output = null;
                return false;
            }
        }

        if (totalWriteLength == copyDataLength) {
            System.out.println("copy copyDataLength=" + copyDataLength
                    + " successful");
        }

        outBuff.flush();
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
        b = null;
        input = null;
        output = null;
        sourceFile = null;
        targetFile = null;
        return true;
    }

    // add the String signInfo at the end of the apk/zip file.
    public static String getSignInfo(String unsignedFileName,
            X509Certificate publicKey, PrivateKey privateKey) {
        if (!checkFileExists(unsignedFileName)) {
            System.err
                    .println("error, in param file not exit, or the input param is null");
            return null;
        }

        File unsignedFile = null;
        byte[] fileData;
        unsignedFile = new File(unsignedFileName);
        if (DEBUG)
            System.err.println("GetSignInfo  unsignedFileName="
                    + unsignedFileName);
        try {
            FileInputStream input = new FileInputStream(unsignedFile);
            BufferedInputStream inBuff = new BufferedInputStream(input);

            ByteArrayOutputStream outBuff = new ByteArrayOutputStream(
                    BUFFER_LENGTH);
            System.out.println("Available bytes:" + inBuff.available());

            byte[] temp = new byte[BUFFER_LENGTH];
            int size = 0;
            while ((size = inBuff.read(temp)) != -1) {
                outBuff.write(temp, 0, size);
            }
            fileData = outBuff.toByteArray();
            // fileData.length not contain 2byte that comment length.
            System.out.println("Readed bytes count:" + fileData.length);
            inBuff.close();
            input.close();
            outBuff.close();
            unsignedFile = null;
            try {
                Signature signature = Signature.getInstance("SHA1withRSA");

                try {
                    signature.initSign(privateKey);
                    try {
                        // must update the data 1 byte everytime,otherwise cause
                        // verfy fail.
                        for (int i = 0; i < fileData.length; i++) {
                            signature.update((byte) fileData[i]);
                        }
                    } catch (SignatureException e) {
                        e.printStackTrace();
                    }
                    try {
                        ByteArrayOutputStream out = null;
                        out = new ByteArrayOutputStream();
                        writeSignatureBlock(signature, publicKey, out);
                        out.flush();
                        byte[] b = out.toByteArray();
                        // -check after pkcs7 encode, before base64 encode.
                        if (DEBUG)
                            printHexString(b, b.length);
                        BASE64Encoder enc = new BASE64Encoder();
                        String signInfo = enc.encode(b);
                        enc = null;
                        // Base64 base64 = new Base64();
                        // String signInfo = base64.encodeToString(b,
                        // base64.DEFAULT);
                        String otherCommentString = STRING_CERFORMAT + NEW_LINE
                                + STRING_SIGN;
                        String allCommentInfo = otherCommentString + signInfo;
                        out.close();
                        out = null;
                        return allCommentInfo;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addFileComment(String unsignedFileName,
            String stringComment) {
        if (!checkFileExists(unsignedFileName)) {
            System.err
                    .println("error, in param file not exit, or the input param is null");
            return false;
        }
        if (stringComment == null) {
            System.err
                    .println("error, in param stringComment=" + stringComment);
            return false;
        }

        if (stringComment.length() > MAX_COMMENT_LEN) {
            System.err.println("stringComment.length()="
                    + stringComment.length() + "> MAX_COMMENT_LEN="
                    + MAX_COMMENT_LEN);
            return false;
        }

        int signInfoLength = (short) stringComment.length();
        System.out.println("signInfoLength= 0x"
                + Integer.toHexString(signInfoLength));
        File unsignedFile = null;
        unsignedFile = new File(unsignedFileName);
        // the zip request 2 bytes to stand for the length of the file's comment
        // length
        int commentLen[] = new int[2];
        try {
            FileOutputStream output = new FileOutputStream(unsignedFileName,
                    true);
            for (int i = 0; i < 2; i++) {
                commentLen[i] = (signInfoLength >> (8 * i)) & (0xFF);
                output.write(commentLen[i]);
            }
            output.flush();
            OutputStreamWriter writer = null;
            writer = new OutputStreamWriter(output);
            writer.write(stringComment);
            writer.flush();
            writer.close();
            output.close();
            writer = null;
            unsignedFile = null;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getZipComment(String fileName) {
        if (DEBUG)
            System.out.println("fileName=" + fileName);
        if (!checkFileExists(fileName)) {
            System.err
                    .println("error, in param file not exit, or the input param is null");
            return null;
        }
        String retStrComment = null;
        try {
            File file = new File(fileName);
            int fileLen = (int) file.length();
            FileInputStream in = new FileInputStream(file);
            /*
             * The whole ZIP comment (including the magic byte sequence) MUST
             * fit in the buffer otherwise, the comment will not be recognized
             * correctly You can safely increase the buffer size if you like
             */
            byte[] buffer = new byte[Math.min(fileLen, MAX_COMMENT_LEN)];
            if (DEBUG)
                System.out.println("buffer.length=" + buffer.length);
            int len;
            in.skip(fileLen - buffer.length);
            if ((len = in.read(buffer)) > 0) {
                retStrComment = getZipCommentFromBuffer(buffer, len);
            }
            if (DEBUG)
                System.out.println("fileLen=" + fileLen + ", buffer.length="
                        + buffer.length + ", len=" + len);
            buffer = null;
            in.close();
            file = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (DEBUG)
            System.out.println("retStrComment=" + retStrComment);
        if (DEBUG)
            System.out
                    .println("retStrComment.length=" + retStrComment.length());
        return retStrComment;
    }

    private static String getZipCommentFromBuffer(byte[] buffer, int len) {
        byte[] magicDirEnd = { 0x50, 0x4b, 0x05, 0x06 };
        int buffLen = Math.min(buffer.length, len);
        if (DEBUG)
            System.out.println("buffer.length=" + buffer.length + ", len="
                    + len);
        // Check the buffer from the end
        for (int i = buffLen - 22; i >= 0; i--) {
            boolean isMagicStart = true;
            for (int k = 0; k < magicDirEnd.length; k++) {
                if (buffer[i + k] != magicDirEnd[k]) {
                    isMagicStart = false;
                    break;
                }
            }

            if (isMagicStart) {
                int commentLen = (buffer[i + 20] & 0xFF)
                        + ((buffer[i + 21] << 8) & (0xFF << 8));
                int realLen = buffLen - i - 22;
                System.out.println("ZIP comment found at buffer position "
                        + (i + 22) + " with commentLen=" + commentLen
                        + ", realLen=" + realLen + " OK!");
                if (commentLen != realLen) {
                    System.err
                            .println("WARNING! ZIP comment size mismatch: directory says len is "
                                    + commentLen
                                    + ", but file ends after "
                                    + realLen + " bytes!");
                    return null;
                }
                try {
                    String comment = new String(buffer, i + 22, commentLen,
                            "ISO-8859-1");
                    return comment;
                } catch (UnsupportedEncodingException e) {
                    System.err.println("error, while from byte[] to String ");
                }
            }
        }
        System.out.println("error, file comment not found!");
        return null;
    }

    private static boolean checkFile(String publicKeyName, String sourceFileName) {

        if (!checkFileExists(publicKeyName, sourceFileName)) {
            System.err
                    .println("error, in param file not exit, or the input param is null");
            return false;
        }

        long dataLenWithOutComment = -1;
        String fileComment = null;
        long commentLength = 0;
        if ((fileComment = getZipComment(sourceFileName)) != null) {
            try {
                byte commentArray[] = fileComment.getBytes();
                // String commentStringArray[] = fileComment.split(STRING_SIGN);
                String commentStringArray[] = fileComment.split(STRING_SIGN);
                System.out.println("after spilt with " + STRING_CERFORMAT
                        + ", commentStringArray.length="
                        + commentStringArray.length);
                if (commentStringArray.length <= 1) {
                    System.err.println("error: after spilt with " + STRING_SIGN
                            + ", commentStringArray.length="
                            + commentStringArray.length);
                    return false;
                }
                byte signCommentArray[] = new byte[commentStringArray[1]
                        .length()];
                if (DEBUG)
                    System.out.println("commentStringArray[1]="
                            + commentStringArray[1]);
                if (DEBUG)
                    System.out.println("commentStringArray[0]="
                            + commentStringArray[0]);
                if (DEBUG)
                    System.out.println("commentStringArray[1]="
                            + commentStringArray[1]);
                BASE64Decoder dec = new BASE64Decoder();
                signCommentArray = dec.decodeBuffer(commentStringArray[1]);
                dec = null;
                // signCommentArray = Base64.decode(commentStringArray[1],
                // Base64.DEFAULT);
                try {
                    X509Certificate publicKey = readPublicKey(new File(
                            publicKeyName));
                    try {
                        Signature checkSignature = Signature
                                .getInstance("SHA1withRSA");
                        try {
                            checkSignature.initVerify(publicKey);
                            FileInputStream inputFile = null;
                            inputFile = new FileInputStream(sourceFileName);
                            int orgFileDataLen = inputFile.available();
                            System.err.println("orgFileDataLen="
                                    + orgFileDataLen);
                            try {
                                int len = -1;
                                int i = 0;
                                int totalReadLen = 0;
                                int readLength;
                                // final int BUFFER = (8*1024);
                                final int BUFFER = 1;
                                byte[] orgFileDataBuffer = new byte[BUFFER];
                                dataLenWithOutComment = orgFileDataLen
                                        - fileComment.length() - 2;
                                while (totalReadLen < dataLenWithOutComment) {
                                    readLength = Math
                                            .min(
                                                    ((int) dataLenWithOutComment - totalReadLen),
                                                    BUFFER);
                                    if ((len = inputFile.read(
                                            orgFileDataBuffer, 0,
                                            (int) readLength)) != -1) {
                                        checkSignature.update(
                                                orgFileDataBuffer, 0, len);
                                        i++;
                                        totalReadLen += len;
                                    }
                                }
                                orgFileDataBuffer = null;
                                inputFile = null;
                                System.out.println("i=" + i + ", len=" + len
                                        + ", totalReadLen=" + totalReadLen);
                                boolean checkResult = false;
                                checkResult = checkSignature.verify(
                                        signCommentArray,
                                        (signCommentArray.length - 256), 256);
                                System.out.println("signCommentArray.length="
                                        + signCommentArray.length);
                                System.out.println(" checkResult :"
                                        + checkResult);
                                signCommentArray = null;
                                return checkResult;
                            } catch (SignatureException e) {
                                System.err
                                        .println("error, while update verify");
                            }
                        } catch (InvalidKeyException e) {
                            System.err.println("error, while initVerify");
                        }
                    } catch (NoSuchAlgorithmException e) {
                        System.err
                                .println("error, while Signature.getInstance");
                    }
                } catch (GeneralSecurityException e) {
                    System.err.println("error, while readPublicKey");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("io error, in function checkFile");
            }
        } else {
            System.err.println("can not get the file comment, sourceFileName="
                    + sourceFileName + ", fileComment=" + fileComment);
        }
        return false;
    }

    /*
     * check the fileName is null and exists.return true, thease fileName is no
     * null,and there have these files whitch named fileName.return false, the
     * String is null or not exists whitch named this fileName.
     */
    public static boolean checkFileExists(String... list) {
        File file = null;
        if (DEBUG)
            System.out.println("list.length=" + list.length);
        if (list.length > 0) {
            int paramNumCount = 0;
            for (int i = 0; i < list.length; i++) {
                if (list[i] == null) {
                    System.err.println("input param num =" + list.length
                            + ", the param " + (i + 1) + ", fileName="
                            + list[i]);
                    if (file != null) {
                        file = null;
                    }
                    return false;
                }
                file = new File(list[i]);
                if (!file.exists()) {
                    System.err.println("fileName=" + list[i]
                            + " is not exists.");
                    file = null;
                    return false;
                }
            }
            if (DEBUG)
                System.out.println("the input param is OK");
            file = null;
            return true;
        } else {
            System.out.println("warnning: you have not input any params");
            return true;
        }
    }

    public static void main(String[] args) {
        if ((args.length != 3 && args.length != 4)
                || ((args.length == 3) && (!args[0].equals("-check")))) {
            System.err
                    .println("Usage: SignAndCheckApk publickey.x509[.cer] privatekey.pk8  input.jar output.jar [-check]");
            System.err
                    .println("Usage: SignAndCheckApk -check publickey.x509[.cer] input.jar ");
            System.exit(2);
        }

        if ((args.length == 3)) {
            if (DEBUG) {
                System.out.println("begin check");
            }
            String publicKeyName = args[1];
            String sourceFileName = args[2];
            if (checkFile(publicKeyName, sourceFileName)) {
                System.exit(0);
            } else {
                System.err.println("checkFile " + sourceFileName + " fail!");
            }
            System.exit(2);
        }

        String publicKeyName = args[0];
        String privateKeyName = args[1];
        String sourceFileName = args[2];
        String targetFileName = args[3];

        // check the input params.
        if (!checkFileExists(publicKeyName, privateKeyName, sourceFileName)) {
            System.err
                    .println("error, in param file not exit, or the input param is null");
            System.exit(2);
        }

        boolean retDeleteFileComment = false;
        String retStringSignInfo = null;
        boolean retAddFileComment = false;
        String fileComment = null;
        if ((fileComment = getZipComment(sourceFileName)) != null) {
            try {
                if (retDeleteFileComment = deleteFileComment(sourceFileName,
                        fileComment, targetFileName)) {
                    try {
                        X509Certificate publicKey = readPublicKey(new File(
                                publicKeyName));
                        PrivateKey privateKey = readPrivateKey(new File(
                                privateKeyName));

                        if ((retStringSignInfo = getSignInfo(targetFileName,
                                publicKey, privateKey)) != null) {
                            if ((retAddFileComment = addFileComment(
                                    targetFileName, retStringSignInfo))) {
                                System.out
                                        .println("addFileComment, successful");
                                System.exit(0);
                            } else {
                                System.err
                                        .println("error: while call function addFileComment, return retAddFileComment="
                                                + retAddFileComment);
                            }
                        } else {
                            System.err
                                    .println("error: while call function getSignInfo, return retStringSignInfo="
                                            + retStringSignInfo);
                        }

                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.err
                            .println("error: while call function deleteFileComment, retDeleteFileComment="
                                    + retDeleteFileComment);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err
                    .println("error: while call function getZipComment: fileComment="
                            + fileComment);
        }
    }
}
