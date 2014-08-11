<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>参数列表</title>
<script type="text/javascript">

	//分页跳转函数
	function jumpPage(no) {
		url = 'systemParam!list.action?';
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'systemParam!list.action?';
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'systemParam!list.action?limit=' + pageSizeVal;
		formSubmit('searchForm', url);
	}

	//查询
	function doQuery(){
		var form = document.forms[0];
		form.action = "systemParam!list.action";
		form.submit();
	}
	
	//新增
	function doCreate(){
		var form = document.forms[0];
		form.action = "systemParam!display.action?flag=create";
		form.submit();
	}
	
	//修改
	function doModify(paramId){
		if(undefined == paramId){
			alert("id不能为空");
			return;
		}
		var url = "systemParam!display.action?paramId=" + paramId;
		url = url + "&flag=modify";
		location.href = url;
	}
	
	//删除
	function doDelete(paramId){
		if(undefined == paramId){
			alert("id不能为空");
			return;
		}
		
		if(confirm("确认删除方案？")){
			$.post("systemParam!delete.action",{"paramIds": paramId},function(data){
				if("success" == data){
					alert("操作成功");
					doQuery();
				}else{
					alert(data);
				}
			});
		}
	}
	
	//删除
	function batchDelete(){
		var paramIdArray = new Array();
		var flag = true;
		$(":checkbox[name=checkbox]:checked").each(function(i,e){
			paramIdArray.push(e.value);
		});
		if(true == flag){
			if(paramIdArray.length < 1){
				alert("请选中要删除的参数");
				return;
			}
			if(confirm("确认删除参数？")){
				$.post("systemParam!delete.action",{"paramIds": paramIdArray.join(",")},function(data){
					if("success" == data){
						alert("操作成功");
						doQuery();
					}else{
						alert(data);
					}
				});
			}
		}
	}

	
</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>系统管理&gt; 参数列表</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
	<div id="search_itemlist">
		<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple">
			<s:hidden name="flag" id="flag" />
			<input type="hidden" id="limit" name="limt" value="${limit}" />
			<ul>
				<li>参数名称： <s:textfield id="name" name="systemParam.name" size="15"
						maxlength="64" value="%{systemParam.name}"
						onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li>参数编码： <s:textfield id="code" name="systemParam.code" size="15"
						maxlength="64" value="%{systemParam.code}"
						onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li>参数值： <s:textfield id="code" name="systemParam.value" size="15"
						maxlength="64" value="%{systemParam.value}"
						onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li><input type="button" value="" class="btn_sendData" onclick="doQuery()" />&nbsp;</li>
			</ul>
		</s:form>
	</div>
</div>

<div class="databar">
	<div class="btnbar">
		<ul>
			<s:if test="#session.userResMap.createOrModifyParam!=null">
				<li><a href="#" onclick="doCreate()"><b>新增</b></a></li>
			</s:if>
			<s:if test="#session.userResMap.deleteParam!=null">
				<li><a href="#" onclick="batchDelete()"><b>删除</b></a></li>
			</s:if>
		</ul>
	</div>
	<jsp:include page="/common/prepage.jsp"></jsp:include>
</div>
<div class="data_list">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
 			 <th width="40" scope="col">
  				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox1" id="checkboxAll" onclick="selectAll(this)" />
					</label>
				</span>
			</th>
			<th scope="col">序号</th>
			<th scope="col">名称</th>
			<th scope="col">编码</th>
			<th scope="col">值</th>
			<th scope="col">类型</th>
			<th scope="col">操作</th>
		</tr>
		<s:iterator value="page.resultList" status="st"> 
			<tr>
				<td nowrap>
					<input type="checkbox" name="checkbox" value="<s:property value="id"/>" data_status="<s:property value='status' />" 
					 data_defaulted="<s:property value='defaulted' />" onclick="selectChildAll()" />
				</td>
				<td>${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
				<td><s:property value="name"/></td>
				<td><s:property value="code"/></td>
				<td>
					<s:if test="%{type==1}"><s:property value="value"/></s:if>
					<s:else><a href="${appFilePath.systemPath}${value}" target="_blank"><s:property value="value"/></a></s:else>
				</td>
				<td>
					<s:if test="%{type==1}">文字</s:if>
					<s:else>图片</s:else>
				</td>
				<td class="editbar">
					<s:if test="#session.userResMap.createOrModifyParam!=null">
						<a href="#" onclick="doModify('<s:property value="id"/>')" target="mainFrame" >编辑</a>
					</s:if>
					<s:if test="#session.userResMap.deleteParam!=null">
						<s:if test="%{status != 1}">
							<a href="#" onclick="doDelete('<s:property value="id"/>')" target="mainFrame" >删除</a>
						</s:if>
					</s:if>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<div class="btnbar">
		<ul>
			<s:if test="#session.userResMap.createOrModifyParam!=null">
				<li><a href="#" onclick="doCreate()"><b>新增</b></a></li>
			</s:if>
			<s:if test="#session.userResMap.deleteParam!=null">
				<li><a href="#" onclick="batchDelete()"><b>删除</b></a></li>
			</s:if>
		</ul>
	</div>
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>
</body>
</html>
