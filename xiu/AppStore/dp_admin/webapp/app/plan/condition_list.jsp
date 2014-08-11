<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>方案条件列表</title>
<script type="text/javascript">

	//分页跳转函数
	function jumpPage(no) {
		url = 'condition!list.action?';
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'condition!list.action?';
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'condition!list.action?limit=' + pageSizeVal;
		formSubmit('searchForm', url);
	}
	
	//查询
	function search() {
		url = 'condition!list.action?';
		goUrlPage('searchForm', url, 1);
	}

	//转向新增页面
	function toAdd() {
		location.href = "condition!toAdd.action";
	}
	//转向编辑页面
	function toEdit(id) {
		if(!!id){
			location.href = "condition!toEdit.action?id="+id;
		}else{
			dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,"数据异常，请刷新后重试",0, 0, 1, this);
		}
	}
	//删除
	function del(id) {
		var idArray = new Array();
		if(!!id){
			idArray.push(id);
		}else{
			var childCheckboxs = document.getElementsByName("checkbox");
			
			for ( var i = 0; i < childCheckboxs.length; i++) {
				if (childCheckboxs[i].checked){
					var vId = childCheckboxs[i].id;
					idArray.push(vId);
					if( 1 == childCheckboxs[i].getAttribute("data-status")){
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,"不能删除【已启用】的数据",0, 0, 1, this);
						return;
					}
					if( 1 == childCheckboxs[i].getAttribute("data-planflag")){
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,"不能删除【已关联方案】的数据",0, 0, 1, this);
						return;
					}
				}
			}
			
			if (idArray.length < 1){
				dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,
						'<s:text name="sdp.sce.dp.admin.global.select.del.data" />',0, 0, 1, this);
				return ;
			}
		}
		
		dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,
					'<s:text name="sdp.sce.dp.admin.global.del.data.confirm" />',0, 0, 2, this);
			
			//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
			$("#dialog_btn_conform").click(
				function(){
					//把字符串数组转换成字符串
					var parm = {};
					parm["ids"] = idArray.join(",");
					$.post("condition!delete.action",parm,function(data){
						$("#dialog").remove();
						if("success" == data){
							dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'删除成功',0, 0, 1, this);
							$("#dialog_btn_conform").click(function(){
								search();
							});
						}else{
							dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300, 150, '删除失败', 0,0, 1, this);
						}
					});
				});
	}
	//启用
	function enable(id) {
		var idArray = new Array();
		if(!!id){
			idArray.push(id);
		}else{
			var childCheckboxs = document.getElementsByName("checkbox");
			
			for ( var i = 0; i < childCheckboxs.length; i++) {
				if (childCheckboxs[i].checked){
					var vId = childCheckboxs[i].id;
					idArray.push(vId);
					if( 1 == childCheckboxs[i].getAttribute("data-status")){
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,"不能启用【已启用】的数据",0, 0, 1, this);
						return;
					}
				}
			}
			if (idArray.length < 1){
				dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150, '请选中要启用的数据',0, 0, 1, this);
				return ;
			}
		}	
		dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'您确认要【启用】吗?',0, 0, 2, this);
		//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
		$("#dialog_btn_conform").click(
			function(){
				//把字符串数组转换成字符串 传入参数ids
				$.post("condition!enable.action",{"ids": idArray.join(",")},function(data){
					$("#dialog").remove();
					if("success" == data){
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'启用成功',0, 0, 1, this);
						$("#dialog_btn_conform").click(function(){
							search();
						});
					}else{
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300, 150, '启用失败', 0,0, 1, this);
					}
				});
		});
	}
	//禁用
	function disable(id) {
		var idArray = new Array();
		if(!!id){
			idArray.push(id);
		}else{
			var childCheckboxs = document.getElementsByName("checkbox");
			
			for ( var i = 0; i < childCheckboxs.length; i++) {
				if (childCheckboxs[i].checked){
					var vId = childCheckboxs[i].id;
					idArray.push(vId);
					if( 0 == childCheckboxs[i].getAttribute("data-status")){
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,"不能禁用【已禁用】的数据",0, 0, 1, this);
						return;
					}
				}
			}
			if (idArray.length < 1){
				dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150, '请选中要禁用的数据',0, 0, 1, this);
				return ;
			}
		}	
		dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'您确认要【禁用】吗?',0, 0, 2, this);
		//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
		$("#dialog_btn_conform").click(
			function(){
				//把字符串数组转换成字符串 传入参数ids
				$.post("condition!disable.action",{"ids": idArray.join(",")},function(data){
					$("#dialog").remove();
					if("success" == data){
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'禁用成功',0, 0, 1, this);
						$("#dialog_btn_conform").click(function(){
							search();
						});
					}else{
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300, 150, '禁用失败', 0,0, 1, this);
					}
				});
		});
	}
	
	function showPlan(name,info,id){
		var vPosition = $("#position_"+id);
		var vPointX = vPosition.offset().left - 20;
		var vPointY = vPosition.offset().top;
		$("#plan-info-div").css({"left":vPointX,"top":vPointY});
		
		var vHtml = "<b>条件名称：</b>"+name+"<br/>";
		if( null == info || "" == info){
			vHtml += " <b>方案：</b>无";
		}else{
			vHtml += " <b>方案：</b><br/>";
			var arrInfo = info.split("<->");
			for(var index in arrInfo){
				vHtml += arrInfo[index] +"<br/>";
			}
		}
		$("#plan-info-div").html(vHtml);
		$("#plan-info-div").show();
	}
	
	function hidePlan(){
		$("#plan-info-div").hide();
	}

</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>方案管理&gt; 条件管理</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
		<s:form id="searchForm" name="searchForm" action="condition" method="post" cssStyle="margin:0" theme="simple" >
			<input type="hidden" id="limit" name="limt" value="${limit}"/>
			<ul>
				<li>条件名称:
					<s:textfield id="queryCondition.name" name="queryCondition.name" size="15" maxlength="64"
								value="%{queryCondition.name}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li>条件编码:
					<s:select list="#{'':'全部','channelId':'渠道号 (channelId) ','cityCode':'区域码 (cityCode) '}"  name="queryCondition.code" id="queryCondition.code" value="%{queryCondition.code}">
					</s:select>								
				</li>
				<li>条件值:
					<s:textfield id="queryCondition.value" name="queryCondition.value" size="15" maxlength="64"
								value="%{queryCondition.value}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>								
				<li>条件状态:
					<s:select list="#{'-2':'全部',1:'已启用',0:'已禁用'}"  name="queryCondition.status" id="queryCondition.status" value="%{queryCondition.status}">
					</s:select>
				</li>		
				<li>是否关联方案:
					<s:select list="#{'-2':'全部',1:'已关联',0:'未关联'}"  name="queryCondition.planSearchFlag" id="queryCondition.planSearchFlag" value="%{queryCondition.planSearchFlag}">
					</s:select>
				</li>		
				<li>
					<input type="button" value="" class="btn_sendData"  onclick="search()" />&nbsp;
				</li>
			</ul>
		</s:form>
	</div>
</div>

<div class="databar">
	<div class="btnbar">
		<ul>
			<li><a href="#" onclick="toAdd()"><b><s:text name="sdp.sce.dp.admin.global.btn.add" /></b> </a></li>
			<li><a href="#" onclick="del()"><b><s:text name="sdp.sce.dp.admin.global.btn.del" /></b> </a></li>
			<li><a href="#" onclick="enable()"><b>启用</b> </a></li>
			<li><a href="#" onclick="disable()"><b>禁用</b> </a></li>
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
			<th scope="col">条件名称</th>
			<th scope="col">条件编码</th>
			<th scope="col">条件值</th>
			<th scope="col" style="width:200px;">条件描述</th>
			<th scope="col">条件状态</th>
			<th scope="col">是否关联方案</th>
			<th scope="col">操作</th>
		</tr>
  		<s:iterator value="page.resultList" status="st"  >
		<tr>
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox" id="<s:property value='id' />"  
						data-status="<s:property value='status' />"  data-planflag="<s:property value="planFlag"/>"  onclick="selectChildAll()"/>
					</label>
				</span>
			</td>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td>
				<!-- 查看应用详情超链接 -->
				<a class="detail_link" href="javascript:toEdit('<s:property value="id"/>')"   
					onmouseover="showPlan('<s:property value="name"/>','<s:property value="planInfo"/>','<s:property value="id"/>')"
					onmouseout="hidePlan();">
					<s:if test="%{name.length() > 12}">
	               		<s:property value="%{name.substring(0, 12) + \"..\"}"/>
	               	</s:if>
	               	<s:else>
	               		<s:property value="name"/>
	                </s:else>
				</a>
			</td>
			<td id="position_<s:property value='id'/>"><s:property value="code"/></td>
			<td><s:property value="value"/></td>
			<td><s:property value="description"/></td>
			<td>
				<s:if test="%{status == 1}">
					<font color="green">已启用</font>
				</s:if>
				<s:elseif test="%{status == 0}">
					<font color="red">已禁用</font>
				</s:elseif>
				<s:elseif test="%{status == -1}">
					<font color="black">已删除</font>
				</s:elseif>			
			</td>
			<td>
				<s:if test="%{planFlag == 1}">
					<font color="green">已关联</font>
				</s:if>
				<s:elseif test="%{planFlag == 0}">
					<font color="black">未关联</font>
				</s:elseif>		
			</td>			
			<td class="editbar">
				<a class="detail_link" href="javascript:toEdit('<s:property value="id"/>')" >编辑</a>
				<s:if test="%{status == 0}">
					<s:if test="%{planFlag == 0}">
						<a class="detail_link" href="javascript:del('<s:property value="id"/>')" >删除</a>
					</s:if>
				</s:if>	
				<s:if test="%{status == 0}">
					<a class="detail_link" href="javascript:enable('<s:property value="id"/>')" >启用</a>
				</s:if>
				<s:if test="%{status == 1}">
						<a class="detail_link" href="javascript:disable('<s:property value="id"/>')" >禁用</a>
				</s:if>
			</td>			
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<div class="btnbar">
		<ul>
			<li><a href="#" onclick="toAdd()"><b><s:text name="sdp.sce.dp.admin.global.btn.add" /></b> </a></li>
			<li><a href="#" onclick="del()"><b><s:text name="sdp.sce.dp.admin.global.btn.del" /></b> </a></li>
			<li><a href="#" onclick="enable()"><b>启用</b> </a></li>
			<li><a href="#" onclick="disable()"><b>禁用</b> </a></li>
		</ul>								
	</div>
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>
<div id="plan-info-div" style="position:absolute;top:100px;left:100px;background-color:lightblue;padding:5px;5px;5px;5px;">
</div>
</body>
</html>
