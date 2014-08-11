package com.coship.sdp.sce.dp.xstream.dto;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("data-list")
public class SyncAppInfoDataList<T>
{
	@XStreamImplicit
	private  List<T> dataList = new ArrayList<T>();
	@XStreamAsAttribute
	private String name;
	
	public SyncAppInfoDataList(String name)
	{
        this.name = name;
	}

	public List<T> getDataList()
	{
		return dataList;
	}

	public void setDataList(List<T> dataList)
	{
		this.dataList = dataList;
	}
	
}
