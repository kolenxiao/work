<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色授权</title>
<script type="text/javascript">
	//去除空格
	function myTrim(str) {
		var i, strlength, t, chartemp, returnstr;
		var str = str;
		strlength = str.length;
		t = str;
		for (i = 0; i < strlength; i++) {
			chartemp = str.substring(i, i + 1);

			if (chartemp == " ") {
				t = str.substring(i + 1, strlength);
			} else {
				break;
			}
		}
		returnstr = t;
		strlength = t.length;
		for (i = strlength; i >= 0; i--) {
			chartemp = t.substring(i, i - 1);
			if (chartemp == " ") {
				returnstr = t.substring(i - 1, 0);
			} else {
				break;
			}
		}
		return (returnstr);
	}
	//分页跳转函数
	function jumpPage() {
		//获取页码值
		var pageNoVal = myTrim($("#pageNo").val());
		//总页码数
		var totalNum = $("#totalPage").val();

		var strlength = pageNoVal.length;

		if (strlength == 0) {
			dialogList('提示信息',300,150,'你没有输入页码',0,0,1,this);
			return;
		} else {
			for (i = 0; i < strlength; i++) {
				var tempchar = pageNoVal.substring(i, i + 1);
				if (!(tempchar == 0 || tempchar == 1 || tempchar == 2
						|| tempchar == 3 || tempchar == 4 || tempchar == 5
						|| tempchar == 6 || tempchar == 7 || tempchar == 8 || tempchar == 9)) {
					dialogList('提示信息',300,150,'分页跳转只能输入正整数',0,0,1,this);
					flag = false;
					return flag;
				}
			}
			pageNoVal = parseInt(pageNoVal);

			totalNum = parseInt(totalNum);

			if (pageNoVal == 0) {
				$("#pageNo").attr("value",'');
				dialogList('提示信息',300,150,'分页数不能为0',0,0,1,this);
				return;
			}
			if (pageNoVal > totalNum) {
				$("#pageNo").attr("value",'');
				dialogList('提示信息',300,150,'超过了最大分页',0,0,1,this);
				return;
			}
			var pageNoVal = myTrim($("#pageNo").val());
			location.href = 'usrservice!doList.action?usrService.usrServType=1&start=' + pageNoVal;
		}

	}
	//每页显示多少条函数
	function doChange(obj) {

		var pageSizeVal = obj.options[obj.selectedIndex].value;
		location.href = 'usrservice!doList.action?usrService.usrServType=2&limit='
				+ pageSizeVal;
	}

	// 全部选中
	function selectAll(obj) {
		if (obj.checked == true) {
			$.each($('input:checkbox[name="checkbox"]').get(), function(index,
					obj) {
				obj.checked = true;
			});
		} else {
			$.each($('input:checkbox[name="checkbox"]').get(), function(index,
					obj) {
				obj.checked = false;
			});
		}
	}
	//删除服务
	function del() {
		var idArray = new Array();
		var childCheckboxs = document.getElementsByName("checkbox");
		var c_length = childCheckboxs.length;
		for ( var i = 0; i < c_length; i++) {
			if (childCheckboxs[i].checked)
				idArray.push(childCheckboxs[i].id);
		}
		if (idArray.length > 0) {
			//把字符串数组转换成字符串
			var idVal = eval(idArray);
			if (window.confirm("确定删除信息吗？")) {
				location.href = 'usrservice!doDelete.action?usrService.usrServType=2&id='
						+ idVal;
			}
		} else {
			dialogList('提示信息',300,150,'请选择需要删除的数据!',0,0,1,this);
		}

	}

	//查询函数
	function searchService() {
		var startTime = document.getElementById("beginUsrServCtime").value;
		var endTime = document.getElementById("endUsrServCtime").value;
		if (CompareDate(startTime, endTime)) {
			dialogList('提示信息',300,150,'开始时间不能比结束时间大',0,0,1,this);
			return false;
		}
		var form = document.getElementById("searchForm");
		form.action = "usrservice!doList.action?usrService.usrServType=2";
		form.submit();
	}

	function selectCheckbox(field) {
		if(field.checked) {
			field.value = true;
		} else {
			field.value = false;
		}
	}

</script>
</head>
<body id="cnt_body">
	<div id="position">
		<p>当前位置: 权限管理&gt; 角色授权</p>
		<div id="pright"></div>
	</div>
	<div id="searchbar">
		<div id="search_itemlist">

		</div>
	</div>

<s:form action="role!addPermission.action" method="post" >
<input type='hidden' name='roleId' value="<s:property value='roleId'/>" />
	<div class="data_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">

			<tr>
				<th class="select_td" scope="col">资源名称</th>
				<th class="select_td" scope="col">资源链接</th>
				<th class="select_td" scope="col">资源级别</th>
				<th class="select_td" scope="col">资源标识</th>
			</tr>
			<s:iterator value="res" status="st"  >

					<tr class="tr_even">

						<td align="left">
						<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="rolePerMap.<s:property value="enName" />" id="<s:property value='id' />"
						<s:if test="rolePerMap[enName]">
						checked="checked" value='true'
						</s:if>
						onclick="selectCheckbox(this)"/>

						</label> </span>
						<s:property value="name" /></td>
						<td><s:property value="url" />
					</td>
					<td>
						一级菜单
					</td>
					<td><s:property value="enName" />
					</td>
					</tr>

				<s:if test="res.size > 0">
					<s:iterator value="res" status="chiled" >
					<tr class="tr_even">

						<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span id="sprycheckbox1"> <label>

						<input type="checkbox" name="rolePerMap.<s:property value="enName" />" id="<s:property value='id' />"
						<s:if test="rolePerMap[enName]">
						checked="checked" value='true'
						</s:if>
						onclick="selectCheckbox(this)"/>

						</label> </span>
						<s:property value="name" /></td>
						<td><s:property value="url" />
					</td>
					<td>
						二级菜单
					</td>
					<td><s:property value="enName" />
					</td>
					</tr>

					<s:if test="res.size > 0">
					<s:iterator value="res" status="st">
					<tr class="tr_even">

						<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span id="sprycheckbox1"> <label>

					<input type="checkbox" name="rolePerMap.<s:property value="enName" />" id="<s:property value='id'/>"
					<s:if test="rolePerMap[enName]">
					checked="checked" value='true'
						</s:if>
					onclick="selectCheckbox(this)"/>
						</label> </span>
						<s:property value="name" /></td>
						<td><s:property value="url" />
					</td>
					<td>
						按钮
					</td>
					<td><s:property value="enName" />
					</td>
					</tr>
					</s:iterator>
				</s:if>
					</s:iterator>
				</s:if>

			</s:iterator>

		</table>

	</div>

<div class="btnlistbar" align="center">
  <input class="inputstyle" type="submit" value="提交" />
</div>
</s:form>
	<script type="text/javascript">
		var sprycheckbox1 = new Spry.Widget.ValidationCheckbox("sprycheckbox1",
				{
					isRequired : false
				});
	</script>
</body>
</html>
