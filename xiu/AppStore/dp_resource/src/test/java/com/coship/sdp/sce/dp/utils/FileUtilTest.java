package com.coship.sdp.sce.dp.utils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * FileUtil测试类.
 *
 * @author 906055
 *
 */
public class FileUtilTest {

	/**
	 * 文件路径.
	 */
	private final String filePath = "c:" + File.separator + "dptest";
	/**
	 * 文件名.
	 */
	private final String fileName = "download.txt";
	/**
	 * MockHttpServletResponse对象，模拟现实环境的HttpServletResponse.
	 */
	private HttpServletResponse response = new MockHttpServletResponse();

	/**
	 * 测试文件上传.
	 *
	 * @throws IOException
	 *             IO异常
	 */
	@Test
	public final void testUploadFile() throws IOException {

		File dirFile = new File(filePath);
		// if (!(dirFile.exists()) && !(dirFile.isDirectory())) {
		dirFile.mkdirs();
		// }
		File file = new File(filePath + File.separator + fileName);
		// if (!file.exists()) {
		file.createNewFile();

		// }

		// 写入操作
		String filein = "ABCDEDFDFDFADFDFDFDFDFDF" + "\r\n";
		RandomAccessFile mm = null;
		mm = new RandomAccessFile(file, "rw");
		mm.writeBytes(filein);
		mm.close();

		FileUtil.uploadFile(file, filePath,
				file.getName() + new Date().getTime());

	}

	/**
	 * 测试文件下载.
	 *
	 * @throws IOException
	 *             IO异常
	 */
	@Test
	public final void testDownLoad() throws IOException {
		FileUtil.downLoad(filePath, fileName, response);
	}

	/**
	 * 测试删除文件.
	 */
	@Test
	public final void testDeleteFile() {
		FileUtil.deleteFile(filePath + File.separator + fileName);
	}

	/**
	 * 测试文件重命名.
	 */
	@Test
	public final void testResetFileName() {
		new FileUtil();
		FileUtil.resetFileName(fileName);

	}

}
