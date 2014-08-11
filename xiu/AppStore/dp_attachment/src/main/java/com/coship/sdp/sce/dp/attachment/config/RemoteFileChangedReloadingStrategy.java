package com.coship.sdp.sce.dp.attachment.config;

import java.io.IOException;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

public class RemoteFileChangedReloadingStrategy extends FileChangedReloadingStrategy
{
  private static final Logger logger = Logger.getLogger(RemoteFileChangedReloadingStrategy.class);
  private String lastModifiedDate;

  public RemoteFileChangedReloadingStrategy()
  {
    this.lastModifiedDate = null;
  }

  protected boolean hasChanged()
  {
    if (this.lastModifiedDate == null) {
      return true;
    }

    String tmpLastModifiedDate = getLastModifiedDate();
    if (tmpLastModifiedDate != null) {
      return (!(tmpLastModifiedDate.equals(this.lastModifiedDate)));
    }

    return false;
  }

  protected void updateLastModified()
  {
    String tmpLastModifiedDate = getLastModifiedDate();
    if (tmpLastModifiedDate != null)
      this.lastModifiedDate = tmpLastModifiedDate;
  }

  protected String getLastModifiedDate()
  {
    HttpClient httpclient = new HttpClient();
    GetMethod getMethod = new GetMethod(this.configuration.getURL().toString());
    try {
      httpclient.executeMethod(getMethod);
      Header lastModifiedHeader = getMethod.getResponseHeader("Last-Modified");

      if (lastModifiedHeader != null) {
        String str = lastModifiedHeader.getValue();
        return str;
      }
    }
    catch (HttpException e)
    {
      logger.error("打开URL失败，URL：" + this.configuration.getURL().toString());
    } catch (IOException e) {
      logger.error("打开URL失败：" + this.configuration.getURL().toString());
    } finally {
      getMethod.releaseConnection();
    }
    return null;
  }
}