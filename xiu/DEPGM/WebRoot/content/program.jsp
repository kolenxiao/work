<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<jsp:include page="../resource/resource.jsp"></jsp:include>
<script type="text/javascript" src="js/DatePanel.js"></script>
<script type="text/javascript">

</script>
<style type="text/css">
.searchbox-button:hover{
	background-color: #95B8E7;
	border:1px;
	border-color: #95B8E7;
}
.week-button:hover{
	background-color: #95B8E7;
	border:1px solid #95B8E7;
}
a{
	text-decoration:none;color:blue;
}
</style>
<body>
<div style="postition:relative;width:15%;height:100%;float:left;border:1px #95B8E7 solid;overflow: scroll;">
	<div id="search-channel-toolbar" style="text-align:center;">
	    	<input class="easyui-validatebox" style="width:100px" placeholder="按回车搜索" type="text" name="name" id="channelSearchBox"/>
	</div>
	<table id="dg_channel" class="easyui-datagrid" border="0" fitColumns="true" singleSelect="true"
		url="channel_listNoPage" toolbar="#search-channel-toolbar" data-options="onClickRow:function(index,data){submit(data.id);}">
		<thead>
			<tr>
				<th field="id" formatter="Common.ChannelFormatter" align="center" width="50px" align="center">频道列表</th>	
			</tr>
		</thead>
	</table>
</div>
<div style="postition:relative;width:84%;height:100%;border:1px #95B8E7 solid;float:right;">
	<div id="search-toolbar">
		<div style="width:90%;position:relative;height:30px;float:left;">
			<div class="easyui-tabs" tabWidth="auto" style="height:30px;font-size:10px;" id="weeks-panel">
			    <div title="" style="padding:1px;"></div>
			    <div title="" style="padding:1px;"></div>
			    <div title="" style="padding:1px;"></div>
			    <div title="" style="padding:1px;"></div>
			    <div title="" style="padding:1px;"></div>
			    <div title="" style="padding:1px;"></div>
			    <div title="" style="padding:1px;"></div>
		    </div>
	    </div>
	    <div class="tabs-header" style="height:26px;width:9.7%;position:relative;border:1px solid #95B8E7;text-align:center;">
	    	<div class="week-button" id="preWeek" onclick="preWeek()" style="line-height:22px;width:25%;text-align:center;height:22px;margin-left:5px;float:left;position:relative;cursor:pointer;font-size:14px;"><<</div>

	    	<div class="week-button" id="currentWeek" onclick="currentWeek()" style="line-height:22px;width:20%;text-align:center;height:22px;margin-left:5px;float:left;position:relative;cursor:pointer;font-size:14px;">||</div>

	    	<div class="week-button" id="nextWeek" onclick="nextWeek()" style="line-height:22px;width:25%;text-align:center;height:22px;margin-left:5px;float:left;position:relative;cursor:pointer;font-size:14px;">>></div>
	    </div>
	</div>
	<table id="dg_program" toolbar="#search-toolbar" class="easyui-datagrid" singleSelect="true" title="节目单列表" fitColumns="true" border="0"
		 striped="true" fit="true" rownumbers="true">
		<thead>
			<tr>	
				<th field="name" width="200" align="left">节目单名</th>
				<th field="beginTime" formatter="Common.TimeFormatter" width="100" align="center">开始时间</th>
				<th field="endTime" formatter="Common.TimeFormatter" width="100" align="center">结束时间</th>
				<th field="typeName" width="100" align="center">类型</th>
				<th field="contentName" formatter="Common.ButtonFormatter" width="100" align="center">节目</th>
			</tr>
		</thead>
	</table>

</div>	
<div id="dialog" class="easyui-dialog" style="width:750px;height:420px;"
		data-options="title:'修改节目单',modal:true,
			buttons:[{
				text:'保存',
				iconCls: 'icon-ok',
				handler:function(){   
				                   var contentId=$('#dialog_programContentId').val();
				                   var id=$('#dialog_programId').val();
				                   if(contentId==''||contentId==null){
				                  		 $.messager.show({
										                        title: 'Error',
										                        msg: '请选择节目关联',
										                        showType: 'slide',
                												timeout: 2000
										                    });
										return;										                    
				                   }
				                   $('#dialog').dialog('close');
				                    $.ajax({
								             type: 'post',
								             url: 'pgm_editShip',
								             data: {contentId:contentId, id:id},
								             dataType: 'json',
								             success: function(data){
								                         if(data.ret==0){
								                         $.messager.show({
										                        title: 'Error',
										                        msg: 'Failed,please try again.',
										                        showType: 'slide',
                												timeout: 2000
										                    });
								                         }
								                         else{
								                         	$('#dg_program').datagrid('reload');
								                         }
								                      }
								         });
				                   }
			},{
				text:'取消',
				iconCls: 'icon-cancel',
				handler:function(){
				$('#dialog').dialog('close');
				$('#list_programContent').datagrid('load', {});
				}
			}]">
	<div style="text-align:left;padding:10px;align:center;height:100px;padding-top:10px;">
		<form name="program_edit" id="program_edit" method="post" action="">
			<table>
				<tr>
				<td><label style="font-size: 12px;font-weight: normal;">节目单名称:</label>
				</td>
				<td>
				<span style="font-size: 12px;font-weight: normal;color:red;" id="dialog_program_name"/></td>
				<td>
		    	<label style="font-size: 12px;font-weight: normal;">当前节目:</label>
		    	</td>
				<td>
		    	<span style="font-size: 12px;font-weight: normal;color:red;" id="dialog_old_content"/></td>
		    	<td></td>
		    	</tr>
		    	
		    	<tr>
				<td><label style="font-size: 12px;font-weight: normal;">修改为:</label></td>
				<td><input name="name" id="contentNameSearchBox" placeholder=" 输入关键字查找" style="width:150px;"/></td>
				
<!-- 				
		    	<td><select class="easyui-combobox" data-options="editable:false,valueField:'id',textField:'name',url:'pgm_getTypeList'" id="dialogSelect" name="dialogType" style="width:100px;">
				</select>
				</td>
 -->				<td><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchAlias()">查询</a>
 					<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="createProgramContent()">创建节目</a>
 					</td>
				</tr>
			</table>
			<input type="hidden" id="dialog_programId"/>
			<input type="hidden" id="dialog_programContentId"/>
		</form>
		<p style="color:red;">*从以下节目列表中勾选相应的节目点击保存按钮保存*</p>
		<table id="list_programContent" class="easyui-datagrid" title="节目列表" url="" toolbar="#toolbar" singleSelect="true" fitColumns="true" 
			rownumbers="true" pagination="true" striped="true" data-options="onClickRow:function(index,data){reverseCheck(data.id);}">
			<thead>
				<tr>
					<th field="id" width="50px;" formatter="Common.CheckBoxFormatter" align="center"></th>	
					<th field="name" width="50px;" align="center">节目名称</th>
					<th field="typeName" width="50px;" align="center">所属分类</th>
					<th field="btv" formatter="YesOrNoFormatter" width="50px;" align="center">是否支持回看</th>
					<th field="chapter" width="50px;" align="center">总集数</th>
				</tr>
			</thead>
		</table>
    </div>
</div>
<jsp:include page="program_add_include.jsp"/>
</body>
</html>
