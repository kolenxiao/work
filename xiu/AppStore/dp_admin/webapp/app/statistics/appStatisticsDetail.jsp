<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应用统计详情</title>
<style type="text/css">
	#imgDiv {
		float: left;
		border: none;
	}

	.jRatingAverage {
		background-color:#f62929;
		position:relative;
		top:0;
		left:0;
		z-index:2;
		height:100%;
	}

	.jRatingColor {
		background-color:#ffc239; /* bgcolor of the stars*/
		position:relative;
		top:0;
		left:0;
		z-index:2;
		height:100%;
	}

	/** Div containing the stars **/
	.jStar {
		position:relative;
		left:0;
		z-index:3;
	}
</style>
<script type="text/javascript">
//分页跳转函数
function jumpPage(no) {
	url = 'dpAppInfoStat!viewDetailAppStatic.action?';
	//需要传参数：form表单id，跳转URL，页码输入框的id。
	jumpPageTo('searchForm', url, no);
}

//上下页显示
function page(start) {
	url = 'dpAppInfoStat!viewDetailAppStatic.action?';
	//searchForm是form表单id,url 为跳转路径,start为第几页
	goUrlPage('searchForm', url, start);
}

//每页显示多少条函数
function doChange(obj) {

	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url = 'dpAppInfoStat!viewDetailAppStatic.action?limit=' + pageSizeVal;
	//表单提交
	formSubmit('searchForm', url);
}

function backToList()
{
	window.location="dpAppInfoStat!searchAppStatistic.action";
}

//平均评分星
$(document).ready(function(){
	var score = ${appScoreSummary.avgScore};
	var width = score*12;
	var str = "height: 10px; width: "+width+"px; overflow: hidden; z-index: 1; position: relative;";
	$("#avgStar").attr("style",str);
});

</script>
</head>
<body>
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/><s:text name="sdp.sce.dp.admin.ap.name" />&gt;应用统计&gt;应用统计详情
  </p>
  <div id="pright"></div>
</div>
<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
<s:hidden name="appStatQuery.appId" value="%{appStatQuery.appId}"></s:hidden>
<div id="box" style="padding-bottom: 2px;margin-left: 20px" >
			<table>
				<tr>
					<td rowspan="8">
						<s:iterator value="dpAppInfo.attachmentList" id="attachment">
							<s:if test="%{#attachment.fileDesc=='logo'}">
								<img src="${appFilePath.logoPath}<s:property value="#attachment.fileSaveName" />" height="100" width="100"/>
							</s:if>
						</s:iterator>
					</td>
				</tr>
				<tr>
					<td colspan="4">${dpAppInfo.appName }</td>
				</tr>
				<tr>
					<td>所属分类：</td>
					<td><s:property value="dpAppInfo.dpType.typeName"/></td>
					<td style="padding-left: 30px">应用语言:</td>
					<td>${dpAppInfo.language }</td>
				</tr>
				<tr>
					<td >系统要求：</td>
					<td>
						${dpAppInfo.system}+
					</td>
					<td style="padding-left: 30px">开发商:</td>
					<td>${dpAppInfo.developer}</td>
				</tr>
				<tr>
					<td>价格：</td>
					<td>${dpAppInfo.price}</td>
					<td style="padding-left: 30px">版本:</td>
					<td>${dpAppInfo.version}</td>
				</tr>
				<tr>
					<td style="padding-right: 30px">平均评分:</td>
					<td colspan="3">${appScoreSummary.avgScore}</td>
				</tr>
				<tr>
					<td style="padding-right: 30px">应用发布包:</td>
					<td colspan="3">
					<a  href="dpAppInfo!doDownLoad.action?appInfo.id=${dpAppInfo.id }&flag=1" target="downloadFrame">
						<s:iterator value="dpAppInfo.attachmentList" id="attachment">
							<s:if test="%{#attachment.fileDesc=='appfile'}">
								<s:property value="#attachment.fileSaveName" />
							</s:if>
						</s:iterator>
					</a></td>
				</tr>

			</table>
</div>

<div class="view_nav"  id="nav02" >
	<span class="float_l">用户评分概览</span><br/>
</div>

<div id="box2" style="padding-bottom: 2px;" >
	<div style="margin-left: 20px;margin-top: 8px;margin-right: 50px">
		<table>
			<tr>
				<td width="250px">
					<div style="height: 10px; width: 60px; overflow: hidden; z-index: 1; position: relative;">
						<div class="jRatingColor" style="width:60px;"></div>
						<div class="jRatingAverage" style="width: 0px; top: -10px;"></div>
						<div class="jStar" style="width: 60px; height: 10px; top: -20px; background: url('<%=ctxPath%>/images/small_star.png') repeat-x scroll 0% 0% transparent;"></div>
					</div>
				</td>
				<td width="80px">${appScoreSummary.star5 }人</td>
				<td width="300px" rowspan="5" align="center">
					平均评分:<br>
					${appScoreSummary.avgScore}
					<div id="avgStar" style="height: 10px; width: 60px; overflow: hidden; z-index: 1; position: relative;">
						<div class="jRatingColor" style="width:60px;"></div>
						<div class="jRatingAverage" style="width: 0px; top: -10px;"></div>
						<div class="jStar" style="width: 60px; height: 10px; top: -20px; background: url('<%=ctxPath%>/images/small_star.png') repeat-x scroll 0% 0% transparent;"></div>
					</div>
					${appScoreSummary.star1+appScoreSummary.star2+appScoreSummary.star3+appScoreSummary.star4+appScoreSummary.star5}人参与评分
				</td>
			</tr>
			<tr>
				<td width="250px">
					<div style="height: 10px; width: 48px; overflow: hidden; z-index: 1; position: relative;">
						<div class="jRatingColor" style="width: 48px;"></div>
						<div class="jRatingAverage" style="width: 0px; top: -10px;"></div>
						<div class="jStar" style="width: 48px; height: 10px; top: -20px; background: url('<%=ctxPath%>/images/small_star.png') repeat-x scroll 0% 0% transparent;"></div>
					</div>
				</td>
				<td width="80px">${appScoreSummary.star4 }人</td>
			</tr>
			<tr>
				<td width="250px">
					<div style="height: 10px; width: 36px; overflow: hidden; z-index: 1; position: relative;">
						<div class="jRatingColor" style="width: 36px;"></div>
						<div class="jRatingAverage" style="width: 0px; top: -10px;"></div>
						<div class="jStar" style="width: 36px; height: 10px; top: -20px; background: url('<%=ctxPath%>/images/small_star.png') repeat-x scroll 0% 0% transparent;"></div>
					</div>
				</td>
				<td width="80px">
					${appScoreSummary.star3 }人
				</td>
			</tr>
			<tr>
				<td width="250px">
					<div style="height: 10px; width: 24px; overflow: hidden; z-index: 1; position: relative;">
						<div class="jRatingColor" style="width: 24px;"></div>
						<div class="jRatingAverage" style="width: 0px; top: -10px;"></div>
						<div class="jStar" style="width: 24px; height: 10px; top: -20px; background: url('<%=ctxPath%>/images/small_star.png') repeat-x scroll 0% 0% transparent;"></div>
					</div>
				</td>
				<td width="80px">${appScoreSummary.star2 }人</td>
			</tr>
			<tr>
				<td width="250px">
					<div style="height: 10px; width: 12px; overflow: hidden; z-index: 1; position: relative;">
						<div class="jRatingColor" style="width: 12px;"></div>
						<div class="jRatingAverage" style="width: 0px; top: -10px;"></div>
						<div class="jStar" style="width: 12px; height: 10px; top: -20px; background: url('<%=ctxPath%>/images/small_star.png') repeat-x scroll 0% 0% transparent;"></div>
					</div>
				</td>
				<td width="80px">${appScoreSummary.star1 }人</td>
			</tr>
		</table>
	</div>
</div>
<div class="view_nav"  id="nav01" >
	<span class="float_l">用户评论详情</span>
</div>
<div class="data_list">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
			<th scope="col">用户</th>
			<th scope="col">评分</th>
			<th scope="col">评论内容</th>
			<th scope="col">时间</th>
		</tr>
  		<s:iterator value="commnetPage.resultList" status="st"  >
		<tr >
			<td width="50">${(commnetPage.currentPage-1) * commnetPage.pageSize + st.index + 1 }</td>
			<td><s:property value="commentUserName"/></td>
			<td><s:property value="score"/></td>

				<s:if test="%{commentContent.length() > 50}">
	               	<td title='<s:property value="commentContent"/>'><s:property value="%{commentContent.substring(0, 50) + \"..\"}"/></td>
	            </s:if>
	            <s:else>
	               	<td><s:property value="commentContent"/></td>
	            </s:else>
			<td><s:date name="createDate" format="yyyy-MM-dd"></s:date></td>
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<jsp:include page="/common/prepage.jsp"></jsp:include>
</div>
</s:form>
<div class="btnlistbar">
	<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="backToList();" />
</div>
</body>
</html>