package com.coship.sdp.sce.dp.util;

/**
 * 异步消息对象
 * 
 * @author 907632
 * @since v01.02.06
 */
public class AsynMsg
{

	// 状态码 0成功 ；1失败
	private int statusCode = 0;

	// 消息内容
	private String msg;
	private String extendMsg;

	public AsynMsg()
	{

	}

	public AsynMsg(int statusCode, String msg,String extendMsg)
	{
		this.statusCode = statusCode;
		this.msg = msg;
		this.extendMsg = extendMsg;
	}

	public int getStatusCode()
	{
		return statusCode;
	}

	public void setStatusCode(int statusCode)
	{
		if (statusCode < 0 || statusCode > 1)
		{
			throw new RuntimeException("statusCode must between 0 and 1");
		}

		this.statusCode = statusCode;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public String getExtendMsg()
	{
		return extendMsg;
	}

	public void setExtendMsg(String extendMsg)
	{
		this.extendMsg = extendMsg;
	}

}
