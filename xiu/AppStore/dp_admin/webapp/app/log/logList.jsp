<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=ctxPath%>/js/common.js"></script>
<title>日志列表</title>
<script type="text/javascript">

//分页跳转函数
function jumpPage(no) {
	url = 'oplogger!doList.action?';
	//需要传参数：form表单id，跳转URL，页码输入框的id。
	jumpPageTo('searchForm',url,no);

}
//每页显示多少条函数
function doChange(obj) {

	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url =  'oplogger!doList.action?limit='+ pageSizeVal;
	formSubmit('searchForm',url);

}


//查询函数
function searchLog(startTimeId,endTimeId) {
	url = 'oplogger!doList.action?';
	//searchForm是form表单id,url 为跳转路径,startTimeId为开始时间输入框的Id，endTimeId为结束时间输入框的Id
	querydata('searchForm',url, startTimeId,endTimeId);

}
	function page(start){
	  url = 'oplogger!doList.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页
	  goUrlPage('searchForm',url, start);

	}

function exportLog(){

		var form = document.getElementById("searchForm");
		var startTime =  document.getElementById("operStartDate").value;
		var endTime =  document.getElementById("operEndDate").value;
		if(CompareDate(startTime,endTime))
		{
			document.getElementById("operStartDate").value ="";
			document.getElementById("operEndDate").value="";
			dialogList('提示信息',300,150,'开始时间不能比结束时间大',0,0,1,this);
			return false;
		}else
		{
			if (!isNull(startTime)&&!isNull(endTime))
			{
				var diff=monthDiff(startTime,endTime);
				if(diff>3)
				{
					document.getElementById("operStartDate").value ="";
					document.getElementById("operEndDate").value="";
					dialogList('提示信息',300,150,'导出间隔时间不能超过3个月',0,0,1,this);
					return false;
				}
			}

		}

		form.action="oplogger!exportLog.action";
    	form.submit();
   }

	function monthDiff(sDate1,sDate2){
       	var  aStart,aEnd,oDate1,  oDate2;
       	aStart  =  sDate1.split("-")  ;
       	aEnd  =  sDate2.split("-") ;

		oDate1=new Date(aStart[0],aStart[1],aStart[2],0,0,0);
		oDate2=new Date(aEnd[0],aEnd[1],aEnd[2],0,0,0);
      	var iStartYear = oDate1.getFullYear();
      	var iStartMonth = oDate1.getMonth();
      	var iEndYear = oDate2.getFullYear();
      	var iEndMonth =  oDate2.getMonth();

       	var diffMonth= (iEndYear-iStartYear)*12;

       	if (aEnd[2] > aStart[2])
       	{
       		diffMonth += 1;
       	}

       if(iEndMonth>iStartMonth){//结束时间月分大于开始时间月分
       	 diffMonth += (iEndMonth-iStartMonth);
       }else{
       	 diffMonth -= (iStartMonth-iEndMonth);
       }
       return diffMonth;
   }
</script>
<style>
#search_itemlist{ white-space : nowrap;}
#search_itemlist li{display:inline;}

</style>
</head>
<body id="cnt_body">
	<div id="position">
		<p>当前位置: 系统管理&gt; 日志列表</p>
		<div id="pright"></div>
	</div>
	<div id="searchbar" >
		<div id="search_itemlist" >
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
				<ul>
				<li>操作用户： <s:textfield id="operatorName"
							name="opLogger.operatorName" size="6" maxlength="16"
							value="%{opLogger.operatorName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');"/>
				</li>
				<li>操作类型:
        		   <s:select name="opLogger.operationType" id="operationType"
					list="operationTypes"

					headerKey=""
					headerValue="请选择"

					multiple="false"
					required="true"  cssStyle="width:152px"/>

       			 </li>

       			 <li>操作摸块:
              		<s:select name="opLogger.businessName" id="businessName"
					list="mainMenuList"
					listKey="name"
					listValue="name"
					headerKey=""
					headerValue="请选择"

					 cssStyle="width:152px"/>

       			 </li>
				<li>操作时间：
					<s:textfield id="operStartDate"
							name="opLogger.operStartDate" size="6"
							value="%{opLogger.operStartDate}"  readonly="true" /></li><li>
							 <img
							onclick="WdatePicker({el:'operStartDate',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
				</li>
				<li>到
					<s:textfield id="operEndDate"
							name="opLogger.operEndDate" size="6"
							value="%{opLogger.operEndDate}" readonly="true"/> </li><li>
							<img
							onclick="WdatePicker({el:'operEndDate',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
				</li>
				<li>
					<input type="button" value="" class="btn_sendData"  onclick="searchLog('operStartDate','operEndDate')" />&nbsp;
				</li>
			</ul>
			</s:form>
		</div>

	</div>
	<div class="databar">

		<div class="btnbar">
			<ul>
			<li><a
					href="#" onclick="javascript:exportLog()"
					target="mainFrame"><b>导出</b></a>
				</li>
			</ul>
		</div>
		<!-- 2011.11.03 Edit 引入分页界面 -->
		<jsp:include page="/common/prepage.jsp"></jsp:include>
		<!-- ended of 2011.11.03 Edit  -->
			</div>
	<div class="data_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">

			<tr>
				<th width="30">序号</th>
				<th width="190"  scope="col">操作时间</th>
				<th width="120"  scope="col">登录IP</th>
				<th width="90"  scope="col">操作用户</th>
				<th width="90"  scope="col">操作摸块 </th>
				<th width="90"  scope="col">操作类型 </th>
				<th  scope="col">描述</th>
			</tr>

			<s:iterator value="page.resultList" status="st">
				<tr>
					<td>${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
					<td><s:date name="operationDate" format="yyyy-MM-dd HH:mm" /></td>
					<td><s:property value="clientIp" /></td>
					<td><s:property value="operatorName" /></td>
					<td><s:property value="businessName" /></td>
					<td><s:property value="operationType" /></td>
					<td align="left"><s:property value="description" /></td>
				</tr>
			</s:iterator>

		</table>

	</div>

	<div class="databar">

		<div class="btnbar">
			<ul>
			<li><a
					href="#" onclick="javascript:exportLog()"
					target="mainFrame"><b>导出</b></a>
				</li>
			</ul>
		</div>
			<!-- 2011.11.03 Edit 引入分页界面 -->
		<jsp:include page="/common/nextpage.jsp"></jsp:include>
		<!-- ended of 2011.11.03 Edit  -->
	</div>
</body>
</html>
