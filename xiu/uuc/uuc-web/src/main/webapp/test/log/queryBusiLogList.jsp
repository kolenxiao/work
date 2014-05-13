<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>

<script type="text/javascript">
	function doQuery(){
		inputForm.submit();
	}
</script>

<body>	
	<form name="inputForm" action="queryBusiLogList.action" method="post" target="_self">
		<table>
			<tr>
				<td>
				          操作业务:<s:select name="busiTypeCode" value="busiTypeCode" list="result.busiTypeList" listKey="code" listValue="description"  headerKey="" headerValue="-操作业务-" theme="simple" />
				         关键值:<input name="keyWord" value='<s:property value="keyWord"/>' />
			   </td>
			</tr>
			<tr>			   
			   <td>操作前内容:<input name="beforeContent" value='<s:property value="beforeContent"/>' />
                                                  操作后内容:<input name="afterContent" value='<s:property value="afterContent"/>' />
			                  操作人员:<input name="operId" value='<s:property value="operId"/>' />
			                  操作IP:<input name="operIp" value='<s:property value="operIp"/>' />
			   </td>
			</tr>
			<tr>			   
			   <td> 操作时间:<s:textfield name="createTimeMin" theme="simple" cssClass="inpData"/>
			   -<s:textfield name="createTimeMax" theme="simple" cssClass="inpData"/>
			   <input type="button" value="查询" onclick="doQuery()">
			   </td>
			  
			</tr>
		</table>
		<br>
		<table style="BORDER-COLLAPSE: collapse" borderColor=#000000 border=1>
			<tr>
				<th nowrap="nowrap">序号</th>
			    <th nowrap="nowrap">操作业务</th>
			    <th nowrap="nowrap">关键值</th>
				<th nowrap="nowrap">关键值类型</th>	
				<th nowrap="nowrap">操作前内容</th>
				<th nowrap="nowrap">操作后内容</th>
				<th nowrap="nowrap">备注</th>				
				<th nowrap="nowrap">操作人员</th>
				<th nowrap="nowrap">操作IP</th>
				<th nowrap="nowrap">操作时间</th>	
			</tr>
            <s:if test="result.logList!=null ">
                <s:iterator value="result.logList" status="status">         
                    <tr>
                        <td><s:property value="#status.index+1"/></td>
                        <td><s:property value="busiTypeDesc"/></td>
                        <td><s:property value="keyWord"/></td>
                        <td><s:property value="keyWordTypeDesc"/></td>          
                        <td><s:property value="beforeContent"/></td>
                        <td><s:property value="afterContent"/></td>
                        <td><s:property value="remark"/></td>
                        <td><s:property value="operId"/></td>
                        <td><s:property value="operIp"/></td>
                        <td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                </s:iterator>
            </s:if>
            <s:else>
                <tr>
                    <td colspan="10">无相应的查询记录!</td>
                </tr>
            </s:else>			
		</table>
		<br>
        <s:if test="result.page!=null ">
    		<div>第几页<input name="currentPage" value='<s:property value="result.page.currentPage"/>' />&nbsp;
    		      每页<input name="pageSize" value='<s:property value="result.page.pageSize"/>' />条&nbsp;
    		      总共<s:property value="result.page.totalRecord"/>条&nbsp;
    		      总共<s:property value="result.page.totalPage"/>页
    		</div>        
        <s:else>
            <div>第几页<input name="currentPage" value='1' />&nbsp;
                                      每页<input name="pageSize" value='1000' />条&nbsp;
                                      总共<s:property value="0"/>条&nbsp;
                                      总共<s:property value="0"/>页
            </div>
        </s:else>
        </s:if>
	</form>
</body>
</html>