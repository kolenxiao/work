/*
 * 文 件 名：FileUtil.java
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<文件操作工具类>
 * 修 改 人：xiaoyingping/908618
 * 修改时间：2014-7-28
 * 修改内容：<修改内容>
 */
package com.coship.depgm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coship.depgm.action.ProgramContentAction;
import com.coship.depgm.common.UID;

/**
 * 文件操作工具类.
 * 
 * @author 906055
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(ProgramContentAction.class);

	/**
	 * 处理文件上传到本地.
	 * 
	 * @param attachmentFile
	 *            附件文件对象
	 * @param path
	 *            路径
	 * @param uploadFileName
	 *            上传路径
	 * @throws FileNotFoundException
	 *             找不到文件异常
	 * @throws IOException
	 *             IO读写异常
	 */
	public static void uploadFile(File attachmentFile, String path,
			final String uploadFileName) throws FileNotFoundException, IOException {

		logger.info("uploadFile start...");

		InputStream streamIn = null;
		OutputStream streamOut = null;
		File uploadFile = null;
		try {
			uploadFile = new File(path);

			if (!uploadFile.isDirectory()) {
				boolean isMake = uploadFile.mkdirs();

				if (!isMake) {
					logger.info("the directory is exists ");
				}
			}
			if (!"".equals(attachmentFile.getName())) {
				streamIn = new FileInputStream(attachmentFile);
				streamOut = new FileOutputStream(uploadFile.getPath() + File.separator + uploadFileName);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];

				while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
					streamOut.write(buffer, 0, bytesRead);
				}
			}
		} catch (FileNotFoundException e) {
			logger.error("upload file fail", e);
		} finally {
			try {
				if (null != streamIn) {
					streamIn.close();
				}
				if (null != streamOut) {
					streamOut.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		logger.info("uploadFile end... ");

	}

	/**
	 * 重新处理文件名：使用uuid来转换.
	 * 
	 * @param fileName
	 *            文件名称
	 * @return 重命名后的文件名称
	 */
	public static String resetFileName(String fileName) {
		// 获取文件后缀
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		// 随机数+原来的文件后缀；
		String resetFileName = UID.create().toString() + suffix;
		return resetFileName;
	}
}