<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/includejs.jsp"%>
<style type="text/css">
	#demo{overflow:hiden;width:6px;text-align:center;}
	#demo img{border:none;width:60px;height:25px;}
	#enlarge_images{position:absolute;display:none;z-index:2;border:5px solid #f4f4f4}
	.data_view th
	{
		background: none repeat scroll 0 0 #E9F0F9;
    	border: 1px solid #97B8E0;
    	color: #515151;
        padding: 4px 4px 4px 18px;
        text-align: left;
        width:188px;
	}

	#imgDiv {
		float: left;
		border: none;
	}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="sdp.sce.dp.admin.dp" /></title>
<script type="text/javascript">
function ShowHideBox(me,boxId,navId){
	var el = document.getElementById(boxId);
	if(el.style.display == 'none'){
		el.style.display = 'block';
		me.style.background = "url(<%=ctxPath%>/images/icon_up.gif) no-repeat";
		document.getElementById(navId).setAttribute("calss","view_nav");
	} else{
		el.style.display = 'none';
		me.style.background = "url(<%=ctxPath%>/images/icon_down.gif ) no-repeat";
		document.getElementById(navId).setAttribute("calss","view_nav");
	}
}

</script>
</head>
<body id="cnt_body">
<s:hidden name="dpSatff.id" value="%{dpStaff.id }" />
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.dptype.pro" />：<s:text name="sdp.sce.dp.admin.dpstaff" /> &gt; <s:text name="sdp.sce.dp.admin.ap.apdetail" /></p>
  <div id="pright"></div>
</div>


<div style="clear: both;"></div>

<!--  基本信息start-->
<div class="view_nav"  id="nav01" >
	<span class="float_l">基本信息</span>
	<span class="icon_down" onclick="ShowHideBox(this,'box1','nav01')" ></span>
</div>
<div id="box1">
	<div class="data_view">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	 <tr>
	      <th>用户名</th>
	      <td><s:property value="dpStaff.userName"/></td>
	      <td rowspan="4" width="100">
	      	<a href="${appFilePath.headIcon}${dpStaff.headIcon}" target="parent">
	      		<img src="${appFilePath.headIcon}${dpStaff.headIcon}" height="100" width="100"/>
	      	</a>
	      </td>
	    </tr>
	    <tr>
	      <th >
	      <s:if test='%{dpStaff.developerTypeCode == 0}'>
	      	公司简称
	      </s:if>
	      <s:else>
	      	昵称
	      </s:else>
	      </th>
	       <td><s:property value="dpStaff.nickName"/></td>
	    </tr>
	    <s:if test='%{dpStaff.developerTypeCode != "0"}'>
		    <tr>
		      <th>性别</th>
		      <td>
		      	<s:if test='%{dpStaff.sex == "0"}'>
					男
				</s:if>
				<s:else>
					女
				</s:else>
			  </td>
		    </tr>
		    <tr>
		       <th>生日</th>
		       <td><s:date name="dpStaff.birthday" format="yyyy-MM-dd" /></td>
		    </tr>
		    <tr>
		      <th>电子邮箱</th>
		      <td colspan="2"><s:property value="dpStaff.email"/></td>
		    </tr>
	    </s:if>
	    <s:else>
		    <tr>
				<th>营业执照</th>
				<td><s:property value="dpStaff.bizLicense"/></td>
			</tr>
			<tr>
		      <th>电子邮箱</th>
		      <td><s:property value="dpStaff.email"/></td>
		    </tr>
	    </s:else>
	     <tr>
	      <th >手机</th>
	      <td  colspan="2"><s:property value="dpStaff.mobileNum"/></td>
	    </tr>
	    <tr>
	      <th>座机</th>
	      <td colspan="2"><s:property value="dpStaff.phoneNum"/></td>
	    </tr>
	    <tr>
	      <th>邮编</th>
	      <td colspan="2"><s:property value="dpStaff.postCode"/></td>
	    </tr>
	    <tr>
	      <th>通讯地址</th>
	      <td colspan="2"><s:property value="dpStaff.address"/></td>
	    </tr>
	    <tr>
		   <th>简介</th>
		   <td colspan="2"><s:property value="dpStaff.briefIntro"/></td>
	    </tr>
	  </table>
	</div>
</div>
<!-- 基本信息end -->

<div id="showphoto" class="showphoto" style="display:none;">
	<div id="photo"style="height:320px;">
    	<img style="height:320px;"/>
    </div>
</div>

<!-- 合作资料start -->
<div class="view_nav"  id="nav02" >
	<span class="float_l">证件信息</span>
	<span class="icon_down" onclick="ShowHideBox(this,'box2','nav02')" ></span>
</div>
<div id="box2">
	<div class="data_view">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <s:if test='%{dpStaff.developerTypeCode != "0"}'>
		      <th width="180px"><s:text name="sdp.sce.dp.admin.dpstaff.realname" /></th>
		      <td>
			      <s:property value="dpStaff.realName"/>
		      </td>
	      </s:if>
	      <s:else>
				<th width="180px">公司名称</th>
				<td>
					<s:property value="dpStaff.corpName"/>
				</td>
	      </s:else>
	      <td rowspan="3" width="100px">
	      	<a href="${appFilePath.headIcon}${dpStaff.identityImg}" target="parent">
	      		<img src="${appFilePath.headIcon}${dpStaff.identityImg}" height="80" width="100"/>
	      	</a>
	      </td>
	    </tr>
	    <tr>
	      <th>证件类型</th>
	      <td>
	      	<s:if test="%{dpStaff.identityCardType == 58}">身份证</s:if>
	      	<s:elseif test="%{dpStaff.identityCardType==59}">军人证</s:elseif>
	      	<s:elseif test="%{dpStaff.identityCardType==60}">护照</s:elseif>
	      	<s:elseif test="%{dpStaff.identityCardType==61}">港澳同胞回乡证</s:elseif>
	      	<s:elseif test="%{dpStaff.identityCardType==62}">营业执照</s:elseif>
	      </td>
	    </tr>
	    <tr>
	      <th>证件号码</th>
	      <td ><s:property value="dpStaff.identityCard"/></td>
	    </tr>
	  </table>
	</div>
</div>
<!-- 合作资料end -->

<!-- 支付账号 start-->
<div class="view_nav"  id="nav03" >
	<span class="float_l">支付账户</span>
	<span class="icon_down" onclick="ShowHideBox(this,'box3','nav03')" ></span>
</div>
<div id="box3">
	<div class="data_view">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <th scope="row">支付方式</th>
	      <td width="80%">
	      <s:if test='%{dpStaff.payMethod == "0"}'>
	      	银行支付
	      </s:if>
	      <s:if test='%{dpStaff.payMethod == "1"}'>
	      	第三方支付
	      </s:if>
	      </td>
	    </tr>
	    <tr>
	      <th scope="row">银行名称/支付平台名称</th>
	      <td width="80%"><s:property value="dpStaff.saveBankName"/></td>
	    </tr>
	    <tr>
	      <th scope="row">账户名称</th>
	      <td width="80%"><s:property value="dpStaff.bankAccountName"/></td>
	    </tr>
	    <tr>
	      <th scope="row">账户号码</th>
	      <td width="80%"><s:property value="dpStaff.bankCardNum"/></td>
	    </tr>
	  </table>
	</div>
</div>
<!-- 支付账号 end -->

<!-- 审核记录start -->
<div class="view_nav"  id="nav04" >
	<span class="float_l">审核记录</span>
	<span class="icon_down" onclick="ShowHideBox(this,'box4','nav04')" ></span>
</div>
<div id="box4">
	<div class="data_list">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <th width="40" scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
	      <th scope="col">审核时间</th>
	      <th scope="col">审核人</th>
	      <th scope="col">审核状态</th>
	      <th scope="col">备注</th>
	    </tr>
		<s:iterator value="list" status="st">
		<tr>
			<td>${st.index + 1 }</td>
			<td><s:date name="auditDate"/></td>
			<td><s:property value="assessor" /></td>
			<td>
				<s:if test="%{auditResult == '1001'}"> <s:text name="sdp.sce.dp.admin.dpstaff.audit.not.pass" /></s:if>
				<s:elseif test="%{auditResult == '1002'}"><s:text name="sdp.sce.dp.admin.dpstaff.audit.to.wait" /></s:elseif>
				<s:elseif test="%{auditResult == '1003'}"> <s:text name="sdp.sce.dp.admin.dpstaff.audit.pass" /></s:elseif>
				<s:elseif test="%{auditResult == '1004'}"> <s:text name="sdp.sce.dp.admin.dpstaff.draft" /></s:elseif>
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
</div>
<!-- 审核记录end -->

<div class="btnlistbar" align="center">
  <input class="inputstyle" type="button" value='<s:text name="sdp.sce.dp.admin.global.btn.return" />' onclick="javascript:history.back()"/>
</div>
</body>
</html>
