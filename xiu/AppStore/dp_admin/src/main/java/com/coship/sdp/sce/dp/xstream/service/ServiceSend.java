package com.coship.sdp.sce.dp.xstream.service;


import java.util.List;

import com.coship.mse.opcode.OpCodeMessage;
import com.coship.mse.opcode.OpCodeSender;
import com.coship.mse.opcode.OpCodeXStreamConverter;
import com.coship.sdp.sce.dp.xstream.dto.SyncAppInfoDataList;
import com.thoughtworks.xstream.XStream;

public class ServiceSend
{
	/**
	 * 
	 * @param obj  
	 * @param opCode 操作码  
	 * @param routeName 路由名称
	 * @param componentType 容器包含的对象类型
	 * @throws Exception
	 * 
	 *  @see SyncAppInfoDataList
	 */
	public static  String sendData(final Object obj, final String opCode,
			final String routeName, final Class<?> componentType)
	{

		OpCodeSender sender = new OpCodeSender(new OpCodeXStreamConverter(
				opCode)
		{

			protected void send(XStream xs)
			{
				xs.alias("data-list", List.class);
				xs.alias("data", componentType);
			}
			
			//无响应数据可省略该实现
			protected void response(XStream xs) {
				xs.alias("ReturnCode", String.class);
			}
		}, true);
		
		String result = "1";
		try{
			OpCodeMessage op = sender.send(routeName, obj);
			result = op.getReturnCode();
		}catch(Exception e){
			//
		}
		
		return result;

	}
}
