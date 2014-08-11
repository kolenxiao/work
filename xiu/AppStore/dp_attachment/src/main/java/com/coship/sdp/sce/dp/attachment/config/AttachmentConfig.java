package com.coship.sdp.sce.dp.attachment.config;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class AttachmentConfig
{
  private static final Logger logger = Logger.getLogger(AttachmentConfig.class);
  private static final String LOCAL_FILE_TYPE = "file";
  private static final String SEQUENCE_PREFIX = "8";
  private Configuration configuration = null;
  private static AttachmentConfig attachmentConfig;

  private AttachmentConfig()
  {
    this.configuration = ConfigurationHelper.getDeployConfiguration("attachment.properties");
    if (this.configuration == null)
      logger.error("读attachemnt组件配置文件失败, 配置文件：attachment/attachment.properties");
  }

  public static AttachmentConfig getInstance()
  {
    if (attachmentConfig == null) {
      attachmentConfig = new AttachmentConfig();
    }
    return attachmentConfig;
  }

  public String getUploadPath()
  {
    if (this.configuration == null) {
      return null;
    }

    String uploadPath = getAbsoluteUploadPath();
    try
    {
      String userName = URLEncoder.encode(getUserName(), "UTF-8");
      String password = URLEncoder.encode(getPassword(), "UTF-8");

      String uri = null;
      if ("file".equalsIgnoreCase(getFileType()))
      {
        uri = getFileType() + "://" + uploadPath;
      }
      else
      {
        uri = getFileType() + "://" + userName + ":" + password + "@" + getFileServerIp() + ":" + getFileServerPort() + uploadPath;
      }

      return uri;
    } catch (UnsupportedEncodingException e) {
      logger.error("用户名密码配置错误，用户名：" + getUserName() + " 密码：" + getPassword()); }
    return null;
  }

  public String getSyncPath()
  {
    if (this.configuration == null) {
      return null;
    }

    String syncPath = getAbsoluteSyncPath();
    try
    {
      String userName = URLEncoder.encode(getUserName(), "UTF-8");
      String password = URLEncoder.encode(getPassword(), "UTF-8");

      String uri = null;
      if ("file".equalsIgnoreCase(getFileType()))
      {
        uri = getFileType() + "://" + syncPath;
      }
      else
      {
        uri = getFileType() + "://" + userName + ":" + password + "@" + getFileServerIp() + ":" + getFileServerPort() + syncPath;
      }

      return uri;
    } catch (UnsupportedEncodingException e) {
      logger.error("用户名密码配置错误，用户名：" + getUserName() + " 密码：" + getPassword()); }
    return null;
  }

  public String getLocalAbsoluteTempPath()
  {
    if (this.configuration == null) {
      return null;
    }

    String localPath = this.configuration.getString("LOCAL_ABSOLUTE_TEMP_PATH");
    if (localPath != null) {
      localPath = localPath + "/";
    }
    return localPath;
  }

  private String getFileType()
  {
    if (this.configuration == null) {
      return null;
    }

    String fileType = this.configuration.getString("FILE_TYPE");
    return fileType;
  }

  private String getFileServerIp()
  {
    if (this.configuration == null) {
      return null;
    }

    String fileServerIp = this.configuration.getString("FILE_SERVER_IP");
    return fileServerIp;
  }

  private String getFileServerPort()
  {
    if (this.configuration == null) {
      return null;
    }

    String fileServerPort = this.configuration.getString("FILE_SERVER_PORT");
    return fileServerPort;
  }

  private String getUserName()
  {
    if (this.configuration == null) {
      return null;
    }

    String userName = this.configuration.getString("USERNAME");
    return userName;
  }

  private String getPassword()
  {
    if (this.configuration == null) {
      return null;
    }

    String password = this.configuration.getString("PASSWORD");
    return password;
  }

  private String getAbsoluteUploadPath()
  {
    if (this.configuration == null) {
      return null;
    }

    String path = this.configuration.getString("ABSOLUTE_UPLOAD_PATH");
    if (path != null) {
      path = path + "/";
    }
    return path;
  }

  private String getAbsoluteSyncPath()
  {
    if (this.configuration == null) {
      return null;
    }

    String path = this.configuration.getString("ABSOLUTE_SYNC_PATH");
    if (path != null) {
      path = path + "/";
    }
    return path;
  }

  public String getSimsId()
  {
    if (this.configuration == null) {
      return null;
    }

    String simsId = this.configuration.getString("SIMS_ID");
    return simsId;
  }

  public String getSequencePrefix()
  {
    String simsId = getSimsId();
    if (simsId == null) {
      return "8";
    }
    return "8" + StringUtils.right(simsId, 3);
  }
}