package com.coship.depgm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboBoxAction extends BaseAction {
	private static final long serialVersionUID = -8420630165671820614L;

	public void getYesOrNoList() throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", true);
		map.put("name", "是");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("code", false);
		map.put("name", "否");
		list.add(map);
		writeResult(list);
	}

	public void getVideoTypeList() throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "HD");
		map.put("name", "高清");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("code", "SD");
		map.put("name", "标清");
		list.add(map);

		writeResult(list);
	}
	
	@SuppressWarnings("rawtypes")
	private void writeResult(List list) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		mapper.writeValue(response.getOutputStream(), list);
	}
}