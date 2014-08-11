<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.coship.appstore.service.dto.*"%>
<%@page import="com.coship.appstore.service.common.*"%>
<%@page import="net.sf.json.*"%>
<%
	ResponseDTO responseDTO =new ResponseDTO();
	responseDTO.setRespseDesc(AppConstants.MSG_SYS_ERROR);
	responseDTO.setRespseStatus(AppConstants.RESPOSE_STATUS_ERROR);
	responseDTO.setResultObject(null);
	out.write(JSONObject.fromObject(responseDTO).toString());
%>