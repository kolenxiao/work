<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<link rel="stylesheet" href="<%=ctxPath%>/css/orbit-1.2.3.css"/>
<script type="text/javascript" src="<%=ctxPath%>/js/jquery.orbit-1.2.3.min.js"></script>
<script type="text/javascript" src="<%=ctxPath%>/js/raty/jquery.raty.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询AP应用详情</title>
<style>
table { table-layout : fixed;
	word-wrap: break-word;
}
.app_pic {width:619px; margin-left:6px;}
.picture {border-left:1px solid #cacaca; border-right:1px solid #cacaca; padding:6px 0px 6px 9px;}
.picture img {margin-right:6px;}
.app_pic_topbg {width:619px; height:3px; background:url(../images/app_pic_tpbg.jpg) no-repeat;}
.app_pic_bottombg {width:619px; height:13px; background:url(../images/app_pic_bottombg.png) no-repeat;}

</style>
<script type="text/javascript">
function ShowHideBox(me){
	var el = document.getElementById('box1');
	if(el.style.display == 'none'){
		el.style.display = 'block';
		me.style.background = "url(<%=ctxPath%>/images/icon_up.gif) no-repeat";
		document.getElementById("nav01").setAttribute("calss","view_nav");
	} else{
		el.style.display = 'none';
		me.style.background = "url(<%=ctxPath%>/images/icon_down.gif ) no-repeat";
		document.getElementById("nav01").setAttribute("calss","view_nav");
	}
}
$(window).load(function() {
	$('.featured').orbit();
});
</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.dptype.pro" />：<s:text name="sdp.sce.dp.admin.ap.name" /> &gt; <s:text name="sdp.sce.dp.admin.ap.appinfo.detail" /></p>
  <div id="pright"></div>
</div>
<div class="view_nav"  id="nav01" >
	<span class="float_l"><s:text name="sdp.sce.dp.admin.ap.appinfo.detail" /></span>
	<span class="icon_down" onclick="ShowHideBox(this,'box1','nav01')" ></span>
</div>
<div id="box1" style="padding-bottom: 20px;margin-left: 10%;margin-right: 10%" >
	<div style="padding-top:5px; padding-bottom: 20px">
			<table>
				<tr>
					<td rowspan="10">
						<s:iterator value="appInfo.attachmentList" id="attachment">
							<s:if test="%{#attachment.fileDesc=='logo'}">
								<img src="${appFilePath.logoPath}<s:property value="#attachment.fileSaveName" />" height="100" width="100"/>
							</s:if>
						</s:iterator>
					</td>
				</tr>
				<tr>
					<td>${appInfo.appName }</td>
					<td colspan="3">
						<div id="avgStar"></div> 
						<script type="text/javascript">
						    $(function() {
						    	$('div#avgStar').raty({
						    		readOnly : true,
						    		path     : '<%=ctxPath%>/js/raty/img',
						    		showHalf : true,
						    		start    : ${appScoreSummary.avgStar}
						    	});
						    });
					     </script>
					</td>
				</tr>
				<tr>
					<td>所属分类：</td>
					<td><s:property value="appInfo.dpType.typeName"/></td>
					<td style="padding-left: 30px">应用语言：</td>
					<td>${appInfo.languageDpType.typeName }</td>
				</tr>
				<tr>
					<td >系统要求：</td>
					<td>
						${appInfo.systemDpType.typeName}+
					</td>
					<td style="padding-left: 30px">开发商：</td>
					<td>${appInfo.developer}</td>
				</tr>
				<tr>
					<td>价格：</td>
					<td>${appInfo.price}</td>
					<td style="padding-left: 30px">版本：</td>
					<td>${appInfo.version}</td>
				</tr>
				<tr>
					<td>实际平均评分：</td>
					<td>${appScoreSummary.avgScore}</td>
					<td style="padding-left: 30px">人工平均评分：</td>
					<td>${appInfo.handAvgScore}</td>
				</tr>
				<tr>
					<td>实际评分人数：</td>
					<td>${appScoreSummary.scoreCount}</td>
					<td style="padding-left: 30px">人工增加评分人数：</td>
					<td>${appInfo.handScoreCount}</td>
				</tr>
				<tr>
					<td>实际下载次数：</td>
					<td>${appInfo.downloadCount}</td>
					<td style="padding-left: 30px">人工增加下载次数：</td>
					<td>${appInfo.handDownCount}</td>
				</tr>
				<tr>
					<td style="padding-right: 30px">标签：</td>
					<td colspan="3">${appTags}</td>
				</tr>
				<tr>
					<td style="padding-right: 30px">操作类型：</td>
					<td colspan="3">
						<s:iterator value="appInfo.opModeList" var="opMode">
							<s:iterator value="opModeDpTypeList" var="opModeDpType">
								<s:if test="#opModeDpType.typeCode==#opMode">
									<s:property value="#opModeDpType.typeName"/>&nbsp;&nbsp;
								</s:if>
							</s:iterator>			
						</s:iterator>
					</td>
				</tr>
				<tr>
					<td style="padding-right: 30px">应用包名：</td>
					<td colspan="3">${appInfo.appFilePackage}</td>
				</tr>
				<tr>
					<td style="padding-right: 30px">应用发布包：</td>
					<td colspan="3">
					<a  href="dpAppInfo!doDownLoad.action?appInfo.id=${appInfo.id }&flag=1" target="downloadFrame">
						<s:iterator value="appInfo.attachmentList" id="attachment">
							<s:if test="%{#attachment.fileDesc=='appfile'}">
								<s:property value="#attachment.fileSaveName" />
							</s:if>
						</s:iterator>
					</a></td>
				</tr>
				<tr>
					<td style="padding-right: 30px">下载地址：</td>
					<td colspan="3">
						<s:iterator value="appInfo.attachmentList" id="attachment">
							<s:if test="%{#attachment.fileDesc=='appfile'}">
								<a  href="${appFilePath.appPath}<s:property value="#attachment.fileSaveName" />" target="downloadFrame">
									${appFilePath.appPath}<s:property value="#attachment.fileSaveName" />
								</a>
							</s:if>
						</s:iterator>
					</td>
				</tr>
			</table>
	</div>
	<!-- 应用简介 -->
	<div id="appdesc" style="padding-top:5px; padding-bottom: 20px">
		<s:text name="sdp.sce.dp.admin.ap.desc" />:
		<hr>
		${appInfo.appDesc }
	</div>
	<!-- 应用截图 -->
	<div style="padding-top:5px; padding-bottom: 20px">
		应用截图：
		<hr>
		<br/>
		<div class="featured">
			<s:iterator value="appInfo.attachmentList" id="attachment">
				<s:if test="%{#attachment.fileDesc=='img'}">
					<img src="${appFilePath.imgPath}<s:property value="#attachment.fileSaveName" />" height="300" width="750"/>
				</s:if>
			</s:iterator>
		</div>
	</div>
	<!-- 应用海报 -->
	<div id="app" style="padding-top:5px; padding-bottom: 20px">
		应用海报：
		<hr>
		<br/>
		<div class="featured">
			<s:iterator value="appInfo.attachmentList" id="attachment">
				<s:if test="%{#attachment.fileDesc=='poster'}">
					<img src="${appFilePath.imgPath}<s:property value="#attachment.fileSaveName" />"  height="300" width="750"/>
				</s:if>
			</s:iterator>
		</div>
	</div>
	
   <!-- 专题海报 -->
   <div id="app" style="padding-top:5px; padding-bottom: 20px">
		专题海报：
		<hr>
		<br/>
		<div class="featured">
		    <s:if test="%{appInfo.subjectPoster != null}">
					<img src="${appFilePath.imgPath}${appInfo.subjectPoster}"  height="300" width="750"/>
			</s:if>
		</div>
	</div>
	<div style="padding-top:5px; padding-bottom: 20px">
		游戏图标：
		<hr>
		<br/>
		<div class="featured">
			<s:iterator value="appInfo.attachmentList" id="attachment">
				<s:if test="%{#attachment.fileDesc=='gameLogo'}">
					<img src="${appFilePath.logoPath}<s:property value="#attachment.fileSaveName" />" height="300" width="750"/>
				</s:if>
			</s:iterator>
		</div>
	</div>
	
	<s:if test="auditRecordList!=null && auditRecordList.size>0">
	<!-- 审核记录start -->
	<div class="data_list" style="padding-top:5px; padding-bottom: 20px">
	     审核记录：
	  <hr>
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <th width="40" scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
	      <th scope="col">审核时间</th>
	      <th scope="col">审核人</th>
	      <th scope="col">审核状态</th>
<!-- 	      <th scope="col">测试链接</th> -->
	      <th scope="col">备注</th>
	    </tr>
		<s:iterator value="auditRecordList" status="st">
		<tr>
			<td>${st.index + 1 }</td>
			<td><s:date name="auditDate"/></td>
			<td><s:property value="assessor" /></td>
			<td>
				<s:if test="%{auditResult == 1}"><s:text name="sdp.sce.dp.admin.ap.state.pass" /></s:if>
				<s:if test="%{auditResult == 2}"><s:text name="sdp.sce.dp.admin.ap.state.notpass" /></s:if>
				&nbsp;
			</td>
			 <s:if test="%{auditOption.length() > 50}">
				<td title='<s:property value="auditOption"/>'>
              		<s:property value="%{auditOption.substring(0, 50) + \"..\"}"/>
              	</td>
             </s:if>
             <s:else>
              	<td>
              		<s:property value="auditOption"/>
              	</td>
            </s:else>
		</tr>
		</s:iterator>
	  </table>
	</div>
	</s:if>
	<s:if test="noBackBtn==null">
		<div class="btnlistbar" align="center">
	  		<input class="inputstyle" type="button" value='<s:text name="sdp.sce.dp.admin.global.btn.return" />' onclick="javascript:history.back()"/>
		</div>
	</s:if>
</div>
<script type="text/javascript">
	document.write("<iframe id='downloadFrame' style='display: none'></iframe> ");
</script>

</body>
</html>
