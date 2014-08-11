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
<title>USR</title>
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
			location.href = 'user!doShowAllUserRole.action?start=' + pageNoVal;
		}

	}
	//每页显示多少条函数
	function doChange(obj) {

		var pageSizeVal = obj.options[obj.selectedIndex].value;
		location.href = 'user!doShowAllUserRole.action?limit='
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
</script>
</head>
<body id="cnt_body">
	<div id="position">
		<p>当前位置: 权限管理&gt; 用户管理</p>
		<div id="pright"></div>
	</div>
	<div id="searchbar">
		<div id="search_itemlist">

		</div>
	</div>
	<%-- <div class="databar">
		<div class="btnbar">
			<ul>
			<s:if test="#session.addRole != null">
				<li>
				<a href="<s:property value="#session.addRole.url"/>"
					target="mainFrame"><b>新增</b> </a>
				</li>
				</s:if>
			<s:if test="#session.deleteRole != null">
				<li><a href="#" onclick="del('<s:property value="#session.deleteRole.url"/>')"><b>删除</b> </a>
				</li>
			</s:if>
			</ul>
		</div>
	</div>--%>
<s:form id="usrApiForm" name="usrApiForm" action="user!upDateUserAndRole.action" method="post" cssStyle="margin:0" theme="simple">
<input type="hidden" name="userID" value="<s:property value="userID" />"/>
	<div class="data_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">

			<tr>
				<th  scope="col">角色名</th>
				<th  scope="col">描述</th>
				<%--<th  scope="col">真实姓名</th>
				<th  scope="col">电子邮箱</th>
				<th  scope="col">联系电话</th>
				<th  scope="col">是否有效</th>
				<th width="140" class="editbar" scope="col">操作</th>
				 --%>
			</tr>
			<s:iterator value="userRoleList" status="st"  >

				<tr class="tr_even">
					<td><s:property value="name" /></td>
					<td><s:property value="description" />
					</td>

					<%-- <s:if test="#session.modifyRole != null">
					<a href=""
						target="mainFrame">编辑</a>
					</s:if>
					--%>

				</tr>
			</s:iterator>

		</table>

	</div>
	<%--
	<div class="databar">
		<div class="pagebar">
			<ul>
				<li><input type="hidden" id="totalPage"
					value="${page.totalPage}">
				</li>
				<li>每页显示<s:select id="pageSize" name="page.pageSize"
						list="#{'10':'10','20':'20','50':'50'}" onchange="doChange(this)"></s:select>条
				</li>
				<li>第${page.currentPage}/${page.totalPage}页</li>
				<li class="pnum_list"><s:if test="page.hasPrePage ==true">
						<a class="link2"
							href='user!doShowAllUserRole.action?start=${page.prePage}&userID='+<s:property value="userID" />>上一页</a>
					</s:if> <s:else>
						<font color="#858585"> 上一页</font>
					</s:else>
				</li>
				<li class="pnum_list"><s:if test="page.hasNextPage ==true">
						<a class="link2"
							href='user!doShowAllUserRole.action?start=${page.nextPage}&userID='+<s:property value="userID" />>下一页</a>
					</s:if> <s:else>
						<font color="#858585"> 下一页</font>
					</s:else>
				</li>
				<li class="jump_num">转到 <input id="pageNo" name="pageNo"
					type="text" /> 页</li>
				<li class="lastpageli"><input type="button"
					class="btn_confirmpage" onclick="jumpPage();" />
			</ul>

		</div>
	</div>
	--%>
	<div class="btnlistbar" align="center">

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
