package com.coship.depgm.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coship.depgm.common.DepgmConfig;

@Component
public class PosterService {
	private static final Logger logger = LoggerFactory.getLogger(PosterService.class);

	@Autowired
	private FtpService ftpService; 
	
	public FTPClient connectPosterFtp(String folder) throws Exception {
		return ftpService.connect(DepgmConfig.getPosterDir() + "/" + folder,
				DepgmConfig.getPosterHost(), DepgmConfig.getPosterPort(),
				DepgmConfig.getPosterUser(), DepgmConfig.getPosterPwd());
	}
	
	public String upload(InputStream input, String folder, String name) throws Exception {
		FTPClient ftpClient = null;
		try {
			ftpClient = connectPosterFtp(folder);
			if (ftpClient != null) {
				ftpService.upload(ftpClient, name, input);
			}
		} catch (IOException e) {
			logger.error("upload poster with error", e);
		} finally {
			ftpService.close(ftpClient);
		}
		return folder + "/" + name;
	}
}