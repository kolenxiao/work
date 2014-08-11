/*
 * 文 件 名：FileUtil.java
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<文件操作工具类>
 * 修 改 人：sunwengang/903820
 * 修改时间：2011-7-25
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.coship.sdp.utils.Debug;

/**
 * 文件操作工具类.
 * 
 * @author 906055
 */
public class FileUtil
{

    /**
     * className.
     */
    private static final String MODULE = FileUtil.class.getName();

    /**
     * 文件缓冲区
     */
    private static final int FILE_BUFFER_LENGTH = 2 * 1024 * 1024;

    /**
     * 处理文件上传到本地.
     * @param attachmentFile 附件文件对象
     * @param path 路径
     * @param uploadFileName 上传路径
     * @throws FileNotFoundException 找不到文件异常
     * @throws IOException IO读写异常
     */
    public static void uploadFile(File attachmentFile, String path,
            final String uploadFileName) throws FileNotFoundException,
            IOException
    {

        Debug.logVerbose("uploadFile strat ");

        InputStream streamIn = null;
        OutputStream streamOut = null;
        File uploadFile = null;
        try
        {
            uploadFile = new File(path);

            if (!uploadFile.isDirectory())
            {
                boolean isMake = uploadFile.mkdirs();

                if (!isMake)
                {
                    Debug.logInfo("the directory is exists ", MODULE);
                }
            }
            if (!"".equals(attachmentFile.getName()))
            {

                streamIn = new FileInputStream(attachmentFile);

                streamOut = new FileOutputStream(uploadFile.getPath()
                        + File.separator + uploadFileName);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];

                while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1)
                {
                    streamOut.write(buffer, 0, bytesRead);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            Debug.logError("upload file fail" + e.getMessage(), MODULE);
        }
        finally
        {

            try
            {
                if (null != streamIn)
                {
                    streamIn.close();
                }
            }
            catch (IOException e)
            {
                Debug.logError(e, e.getMessage(), MODULE);
            }

            try
            {
                if (null != streamOut)
                {
                    streamOut.close();
                }

            }
            catch (IOException e)
            {
                Debug.logError(e, e.getMessage(), MODULE);
            }

        }
        Debug.logVerbose("uploadFile end ", MODULE);

    }

    /**
     * 附件下载.
     * @param filePath 本地目录
     * @param fileName 下载的文件名称
     * @param response response对象
     * @throws FileNotFoundException 找不到文件异常
     * @throws IOException IO读写异常
     */
    public static void downLoad(String filePath, String fileName,
            HttpServletResponse response) throws FileNotFoundException,
            IOException
    {
        InputStream fis = null;
        OutputStream toClient = null;
        try
        {
            // 文件名称
            String path = filePath + File.separator + fileName;
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 以流的形式下载文件。
            fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            int bytesRead = fis.read(buffer);
            if (bytesRead == -1)
            {
                Debug.logVerbose("Stream read is complete.");
            }
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader(" Content-Length ", "" + file.length());

            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + fileName);
            toClient.write(buffer);
            toClient.flush();
        }
        catch (IOException ex)
        {
            Debug.logError(ex.getMessage());
        }
        finally
        {
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e)
                {
                    Debug.logError(e.getMessage());
                }
            }
            if (toClient != null)
            {
                try
                {
                    toClient.close();
                }
                catch (IOException e)
                {
                    Debug.logError(e.getMessage());
                }
            }
        }
    }

    /**
     * 删除文件.
     * @param delFilePath 文件路径：如 d:\\save\\test.doc
     */
    public static boolean deleteFile(String delFilePath)
    {
        Debug.logVerbose("delFilePath  = " + delFilePath);
        boolean flag = false;
        // 删除原来附件的本地文件
        File delFile = new File(delFilePath);
        if (null != delFile && delFile.exists())
        {
            flag = delFile.delete();
            if (flag)
            {
                Debug.logVerbose("Delete files: " + delFilePath + " success!",
                        MODULE);
            }
            else
            {
                Debug.logInfo("delete file error ", MODULE);
            }
        }
        return flag;
    }

    /**
     *  强制删除文件
     * 
     * @param file 文件字符串   
     * @return boolean 是否删除 true 删除成功 false删除失败
     * @exception throws [违例类型] [违例说明]
     */
    public static boolean forceDelete(String file)
    {
        boolean result = false;
        int tryCount = 0;
        // 必须设置才可以删除
        System.gc();
        while (!result && tryCount++ < 10)
        {
            result = deleteFile(file);
        }
        return result;
    }

    /**
     * 重新处理文件名：使用uuid来转换.
     * @param fileName 文件名称
     * @return 重命名后的文件名称
     */
    public static String resetFileName(String fileName)
    {
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 获取随机数
        UUID uuid = UUID.randomUUID();
        // 随机数+原来的文件后缀；
        String resetFileName = uuid.toString() + suffix;
        return resetFileName;
    }

    /**
     * 获得保存后的文件名.
     * 
     * @param fileName 原文件名
     * @param file 文件对象
     * @param path 保存路径
     * @return String 保存后的文件名
     */
    public static String getSaveFileName(String fileName, File file, String path)
    {

        String saveUploadFileName = resetFileName(fileName);

        try
        {

            // 上传文件
            FileUtil.uploadFile(file, path, saveUploadFileName);

        }
        catch (FileNotFoundException e)
        {
            Debug.logError(e, e.getMessage(), MODULE);

        }
        catch (IOException e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
        }
        return saveUploadFileName;
    }

    /**
     * 拷贝文件.
     * 
     * 分别对2个文件构建输入输出流,并且使用一个字节数组作为我们内存的缓存器, 然后使用流从srcFileName 中读出数据到缓存里, 在将缓存数据写到destFileName里面去. 这里的缓存是2MB的字节数组
     * 
     * @param srcFileName 源文件
     * @param destFileName 目标文件
     * @throws Exception [参数说明]
     * @return long [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public static boolean copyFile4Transfer(File srcFileName, File destFileName)
    {

        boolean flag = true;

        int length = FILE_BUFFER_LENGTH;

        FileInputStream in = null;
        FileOutputStream out = null;
        FileChannel inC = null;
        FileChannel outC = null;
        try
        {
            in = new FileInputStream(srcFileName);
            out = new FileOutputStream(destFileName);
            inC = in.getChannel();
            outC = out.getChannel();
            int i = 0;
            while (true)
            {
                if (inC.position() == inC.size())
                {
                    break;
                }

                if ((inC.size() - inC.position()) < FILE_BUFFER_LENGTH)
                {
                    length = (int) (inC.size() - inC.position());
                }
                else
                {
                    length = FILE_BUFFER_LENGTH;
                }
                inC.transferTo(inC.position(), length, outC);
                inC.position(inC.position() + length);
                i++;
            }
        }
        catch (FileNotFoundException e)
        {
            flag = false;
            Debug.logError(e, e.getMessage(), MODULE);
        }
        catch (IOException e)
        {
            flag = false;
            Debug.logError(e, e.getMessage(), MODULE);
        }
        finally
        {

            if (null != inC)
            {

                try
                {
                    inC.close();
                }
                catch (IOException e)
                {
                    flag = false;
                    Debug.logError(e, e.getMessage(), MODULE);
                }
            }

            if (null != outC)
            {
                try
                {
                    outC.close();
                }
                catch (IOException e)
                {
                    flag = false;
                    Debug.logError(e, e.getMessage(), MODULE);
                }
            }

            if (null != in)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    flag = false;
                    Debug.logError(e, e.getMessage(), MODULE);
                }
            }
            if (null != out)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    flag = false;
                    Debug.logError(e, e.getMessage(), MODULE);
                }
            }

        }
        return flag;
    }
}
