package com.coship.sdp.sce.dp.attachment.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

public final class ConfigurationHelper
{
  private static final Logger logger = Logger.getLogger(ConfigurationHelper.class);
  private static final String XML = ".xml";
  private static final String PROPERTIES = ".properties";
  private static final String HTTP = "HTTP://";
  private static String basePath = null;

  private static String deployBasePath = null;

  public static void setBasePath(String basePath)
  {
    basePath = basePath;
  }

  public static String getBasePath()
  {
    if (basePath == null) {
      return System.getProperty("user.dir");
    }
    return basePath;
  }

  public static InputStream readConfiguration(String configurationFileName)
  {
    if (configurationFileName == null) {
      return null;
    }

    String fileName = getFullFileName(configurationFileName);

    boolean isUrl = isUrlFile(fileName);

    if (isUrl)
    {
      try {
        URL url = new URL(fileName);
        return url.openStream();
      } catch (MalformedURLException e) {
        logger.error("打开URL配置文件失败，URL=" + fileName);
      } catch (IOException e) {
        logger.error("打开URL配置文件失败，URL=" + fileName);
      }
      return null;
    }
    try {
      File file = new File(fileName);
      FileInputStream fileInputStream = new FileInputStream(file);
      return fileInputStream;
    } catch (FileNotFoundException e) {
      logger.error("打开配置文件失败，filename=" + fileName);
    }
    return null;
  }

  public static Configuration getConfiguration(String configurationFileName)
  {
    return getConfiguration(configurationFileName, 0L);
  }

  public static Configuration getConfiguration(String configurationFileName, long refreshDelay)
  {
    if (configurationFileName == null) {
      return null;
    }

    String fileName = getFullFileName(configurationFileName);

    boolean isXmlFile = false;
    if (configurationFileName.lastIndexOf(".xml") > 0)
      isXmlFile = true;
    else if (configurationFileName.lastIndexOf(".properties") > 0)
      isXmlFile = false;
    else {
      return null;
    }

    boolean isUrl = isUrlFile(fileName);

    if (isXmlFile) {
      XMLConfiguration xmlConfiguration = null;
      if (isUrl)
        try {
          xmlConfiguration = new XMLConfiguration(new URL(fileName));
        } catch (ConfigurationException e) {
          logger.error("打开URL配置文件失败，URL=" + fileName);
        } catch (MalformedURLException e) {
          logger.error("打开URL配置文件失败，URL=" + fileName);
        }
      else {
        try {
          xmlConfiguration = new XMLConfiguration(fileName);
        } catch (ConfigurationException e) {
          logger.error("打开配置文件失败，filename=" + fileName);
        }

      }

      if (refreshDelay > 0L) {
        FileChangedReloadingStrategy fileChangedReloadingStrategy = new FileChangedReloadingStrategy();
        fileChangedReloadingStrategy.setConfiguration(xmlConfiguration);
        fileChangedReloadingStrategy.setRefreshDelay(refreshDelay);
        xmlConfiguration.setReloadingStrategy(fileChangedReloadingStrategy);
      }
      return xmlConfiguration;
    }
    PropertiesConfiguration propertiesConfiguration = null;
    if (isUrl)
      try {
        propertiesConfiguration = new PropertiesConfiguration(new URL(fileName));
      } catch (ConfigurationException fileChangedReloadingStrategy) {
        logger.error("打开URL配置文件失败，URL=" + fileName);
      } catch (MalformedURLException fileChangedReloadingStrategy) {
        logger.error("打开URL配置文件失败，URL=" + fileName);
      }
    else {
      try {
        propertiesConfiguration = new PropertiesConfiguration(fileName);
      }
      catch (ConfigurationException fileChangedReloadingStrategy) {
        logger.error("打开配置文件失败，filename=" + fileName);
      }

    }

    if (refreshDelay > 0L) {
      FileChangedReloadingStrategy reloadingStrategy = null;
      if (isUrl)
        reloadingStrategy = new RemoteFileChangedReloadingStrategy();
      else {
        reloadingStrategy = new FileChangedReloadingStrategy();
      }
      reloadingStrategy.setConfiguration(propertiesConfiguration);
      reloadingStrategy.setRefreshDelay(refreshDelay);
      propertiesConfiguration.setReloadingStrategy(reloadingStrategy);
    }

    return propertiesConfiguration;
  }

  public static void setDeployBasePath(String deployBasePath)
  {
    deployBasePath = deployBasePath;
  }

  public static String getDeployBasePath()
  {
    if (deployBasePath == null) {
      return System.getProperty("user.dir");
    }
    return deployBasePath;
  }

  public static InputStream readDeployConfiguration(String configurationFileName)
  {
    if (configurationFileName == null) {
      return null;
    }

    String fileName = getDeployFullFileName(configurationFileName);

    boolean isUrl = isUrlFile(fileName);

    if (isUrl)
    {
      try {
        URL url = new URL(fileName);
        return url.openStream();
      } catch (MalformedURLException e) {
        logger.error("打开URL配置文件失败，URL=" + fileName);
      } catch (IOException e) {
        logger.error("打开URL配置文件失败，URL=" + fileName);
      }
      return null;
    }
    try {
      File file = new File(fileName);
      FileInputStream fileInputStream = new FileInputStream(file);
      return fileInputStream;
    } catch (FileNotFoundException e) {
      logger.error("打开配置文件失败，filename=" + fileName);
    }
    return null;
  }

  public static Configuration getDeployConfiguration(String configurationFileName)
  {
    return getDeployConfiguration(configurationFileName, 0L);
  }

  public static Configuration getDeployConfiguration(String configurationFileName, long refreshDelay)
  {
    if (configurationFileName == null) {
      return null;
    }

    String fileName = getDeployFullFileName(configurationFileName);

    boolean isXmlFile = false;
    if (configurationFileName.lastIndexOf(".xml") > 0)
      isXmlFile = true;
    else if (configurationFileName.lastIndexOf(".properties") > 0)
      isXmlFile = false;
    else {
      return null;
    }

    boolean isUrl = isUrlFile(fileName);

    if (isXmlFile) {
      XMLConfiguration xmlConfiguration = null;
      if (isUrl)
        try {
          xmlConfiguration = new XMLConfiguration(new URL(fileName));
        } catch (ConfigurationException e) {
          logger.error("打开URL配置文件失败，URL=" + fileName);
        } catch (MalformedURLException e) {
          logger.error("打开URL配置文件失败，URL=" + fileName);
        }
      else {
        try {
          xmlConfiguration = new XMLConfiguration(fileName);
        } catch (ConfigurationException e) {
          logger.error("打开配置文件失败，filename=" + fileName);
        }

      }

      if (refreshDelay > 0L) {
        FileChangedReloadingStrategy fileChangedReloadingStrategy = new FileChangedReloadingStrategy();
        fileChangedReloadingStrategy.setConfiguration(xmlConfiguration);
        fileChangedReloadingStrategy.setRefreshDelay(refreshDelay);
        xmlConfiguration.setReloadingStrategy(fileChangedReloadingStrategy);
      }
      return xmlConfiguration;
    }
    PropertiesConfiguration propertiesConfiguration = null;
    if (isUrl)
      try {
        propertiesConfiguration = new PropertiesConfiguration(new URL(fileName));
      } catch (ConfigurationException fileChangedReloadingStrategy) {
        logger.error("打开URL配置文件失败，URL=" + fileName);
      } catch (MalformedURLException fileChangedReloadingStrategy) {
        logger.error("打开URL配置文件失败，URL=" + fileName);
      }
    else {
      try {
        propertiesConfiguration = new PropertiesConfiguration(fileName);
      }
      catch (ConfigurationException fileChangedReloadingStrategy) {
        logger.error("打开配置文件失败，filename=" + fileName);
      }

    }

    if (refreshDelay > 0L) {
      FileChangedReloadingStrategy reloadingStrategy = null;
      if (isUrl)
        reloadingStrategy = new RemoteFileChangedReloadingStrategy();
      else {
        reloadingStrategy = new FileChangedReloadingStrategy();
      }
      reloadingStrategy.setConfiguration(propertiesConfiguration);
      reloadingStrategy.setRefreshDelay(refreshDelay);
      propertiesConfiguration.setReloadingStrategy(reloadingStrategy);
    }

    return propertiesConfiguration;
  }

  public static String getFullFileName(String fileName)
  {
    if (basePath != null) {
      return basePath + File.separator + fileName;
    }
    return fileName;
  }

  public static String getDeployFullFileName(String fileName)
  {
    if (deployBasePath != null) {
      return deployBasePath + File.separator + fileName;
    }
    return fileName;
  }

  private static boolean isUrlFile(String fileName)
  {
    return (fileName.toUpperCase().startsWith("HTTP://"));
  }
}