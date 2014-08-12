package com.coship.depgm.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FtpService {
	private static final Logger logger = LoggerFactory.getLogger(FtpService.class);
	
	public FTPClient connect(String path, String addr, int port,
			String username, String password) throws Exception {
		FTPClient ftp = new FTPClient();
		int reply;
		ftp.connect(addr, port);
		ftp.login(username, password);
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		ftp.enterLocalPassiveMode();
		ftp.setBufferSize(1024);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return null;
		}
		ftp.makeDirectory(path);
		ftp.changeWorkingDirectory(path);
		return ftp;
	}
	
	public void upload(FTPClient client, String name, InputStream input) throws Exception{
		client.storeFile(name, input);
		client.logout();
		input.close();
	}
			
	public void close(FTPClient ftpClient) {
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.disconnect();
			} catch (IOException ioe) {
				logger.error("ftp close error:", ioe);
			}
		}
	}
}