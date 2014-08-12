package com.coship.depgm.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author 906324
 */
public class BaseAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected static ObjectMapper mapper = new ObjectMapper();
	protected Map<String, Object> resultMap = new HashMap<String, Object>();
	protected  boolean notWriteJson;
	protected Pager pager = new Pager();
	protected List<?> list;
	protected Object object;
	
	public void jsonPage() throws JsonGenerationException, Exception {
		resultMap.put("total", pager.getTotal());
		resultMap.put("rows", pager.getDatas());
		responseResultMap();
	}
	
	public void jsonList() throws JsonGenerationException, Exception {
		responseResultList();
	}
	
	public void jsonObject() throws JsonGenerationException, Exception {
		resultMap.put("result", object);
		responseResultMap();
	}
	
	public void jsonMap() throws JsonGenerationException, Exception {
		responseResultMap();
	}
	
	public void success() throws JsonGenerationException, Exception {
		jsonRet("0","成功");
	}
	
	public void jsonRet(String ret, String retInfo) {
		resultMap.put("ret", ret);
		resultMap.put("retInfo", retInfo);
		responseResultMap();
	}
	
	public void responseResultMap() {
		response.setContentType("text/html;charset=UTF-8");
		try {
			mapper.writeValue(response.getOutputStream(), resultMap);
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}
	private void responseResultList() throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		mapper.writeValue(response.getOutputStream(), list);
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setPage(int page) {
		pager.setPage(page);
	}

	public void setRows(int rows) {
		pager.setMax(rows);
	}

	public void setSort(String sort) {
		pager.setSort(sort);
	}

	public void setOrder(String order) {
		pager.setOrder(order);
	}

	public Pager getPager() {
		return pager;
	}
	
}