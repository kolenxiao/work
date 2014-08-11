package com.coship.sdp.sce.dp.release;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;

import org.apache.commons.io.IOUtils;

import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.utils.Debug;

/**
 * 发布调用的上传war包工具类.
 * @author
 *
 */
public class DeployUtil
{

    /**
     * 缓冲区.
     */
    private static final int BUFFER_SIZE = 16 * 1024 * 1024;

    /**
     * 消息头长度.
     */
    private static final int HEAD_BUFFER_SIZE = 1000;

    /**
     * 总字节大小.
     */
    private static long totalSize;

    /**
     * 头信息缓冲区.
     */
    private ByteBuffer headBuffer;

    /**
     * 发布应用工程.
     * @param ip 网络地址
     * @param port 端口号
     * @param deployName 部署名称
     * @param project 项目文件
     * @return
     * @throws Exception
     */
    public String deployProject(String ip, String port, String deployName,
            File project) throws Exception
    {

        String url = makeURL(ip, port);
        // 根据URL获取一个连接
        HttpURLConnection conn = openConnection(url);
        // 创建一个字节缓冲区：目的是存储项目名称
        headBuffer = ByteBuffer.allocate(HEAD_BUFFER_SIZE);
        headBuffer.put(deployName.getBytes());
        String result = null;
        OutputStream ops = null;
        FileInputStream fis = null;
        try
        {
            ops = conn.getOutputStream();
            // 前面六十位保存项目名称
            ops.write(headBuffer.array());

            byte[] buffer = new byte[BUFFER_SIZE];
            fis = new FileInputStream(project);
            int mark = -1;
            while ((mark = fis.read(buffer)) != -1)
            {
                ops.write(buffer, 0, mark);
                ops.flush();
                conn.getInputStream();

                totalSize += (long) mark;
            }
            InputStream is = conn.getInputStream();
            result = IOUtils.toString(is);
            conn.disconnect();

        }
        catch (IOException e)
        {
            Debug.logError(e.getMessage());
        }
        finally
        {

            if (fis != null)
            {
                fis.close();
            }
            if (ops != null)
            {
                ops.close();
            }
        }
        return result;
    }

    /**
     * 创建http连接.
     * @param url
     * @return
     * @throws Exception
     */
    private HttpURLConnection openConnection(String url) throws Exception
    {
        // 连接URL
        URL httpUrl = new URL(url);
        // 打开连接
        HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.connect();

        conn.setConnectTimeout(Constants.CONNECT_TIMEOUT); // 设置连接主机超时
        conn.setReadTimeout(Constants.READ_TIMEOUT); // 设置从主机读取数据超时

        return conn;
    }

    /**
     * 拼装连接URL.
     * @param ip
     * @param port
     * @return
     * @throws Exception
     */
    private String makeURL(String ip, String port) throws Exception
    {
        StringBuffer url = new StringBuffer("http://");

        url.append(ip);
        url.append(":");
        url.append(port);
        url.append(Constants.DEPLOY_PROJECT_PATH);

        return url.toString();
    }
}
