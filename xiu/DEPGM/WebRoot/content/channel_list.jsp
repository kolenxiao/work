<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<jsp:include page="../resource/resource.jsp"></jsp:include>

<head>
<script type="text/javascript">

	//查询
	function doSearch() {
		var params = {
   			'name': $("#name").val(),
   			'videoType': $('#videoType').combobox('getValue')
	   	};
		var btv = $('#btv').combobox('getValue');
		if(btv!=''){
			params.btv = btv;
		}
		$('#list_data').datagrid('load', params);
	}
	
	//弹出新增页面
	function doCreate() {
		$('#dlg_create').dialog('close');
		$('#dlg_create').dialog('open').dialog('setTitle','新增频道信息');
	    $('#dlg_form').form('clear');
	    url = "channel_create";
	}
	
	//弹出修改页面
	function doEdit(rowIndex){
		$('#list_data').datagrid('selectRow', rowIndex);
        var row = $("#list_data").datagrid("getSelected");
        if(row){
            $("#dlg_create").dialog("open").dialog("setTitle","修改频道信息");
            $("#dlg_form").form("load",row);
            $("#create_typeId").combobox('setValue', row.typeId);
            url = "channel_update?id="+row.id;
        }
    }
	
	//保存
	function doSave(){
	    $('#dlg_form').form('submit',{
	        url: url,
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	            var result = eval('('+result+')');
	            if ("0" == result.ret){
	            	$('#dlg_create').dialog('close');
	                $('#list_data').datagrid('reload');
                } else {
                    $.messager.show({
                        title: 'Error',
                        msg: result.retInfo
                    });
                }
	        }
	    });
	}
	
	//删除
	function doDelete(){
	    var row = $('#list_data').datagrid('getSelected');
	    if (row){
	        $.messager.confirm('删除','确认删除频道?',function(r){
	            if (r){
	                $.post('channel_delete',{id:row.id},function(result){
	                    if ("0" == result.ret){
	                        $('#list_data').datagrid('reload');
	                    } else {
	                        $.messager.show({
	                            title: 'Error',
	                            msg: result.retInfo
	                        });
	                    }
	                },'json');
	            }
	        });
	    }
	}
	
	function nameFormatter(value,row,index){
      	return '<a href="#" onclick=doEdit("'+index+'")>'+value+'</a>';	
    }
	
	$(function(){
		$.ajax({ 
			url: 'comboBox_getVideoTypeList',
			dataType: 'json', 
			success: function(jsonstr){
				jsonstr.unshift({
					'code':'',
					'name':'全部'
				});
		        $('#videoType').combobox({
		            data:jsonstr,
			        valueField:'code',
			        textField:'name',
			        editable:false
		        });
		    }
		});
		
		$.ajax({ 
			url: 'comboBox_getYesOrNoList',
			dataType: 'json', 
			success: function(jsonstr){
				jsonstr.unshift({
					'code':'',
					'name':'全部'
				});
		        $('#btv').combobox({
		            data:jsonstr,
			        valueField:'code',
			        textField:'name',
			        editable:false
		        });
		    }
		});
		
	});


</script>
</head>
<style>
a{
	text-decoration:none;color:blue;
}
</style>
<body>

<table id="list_data" class="easyui-datagrid" title="频道列表" url="channel_list" toolbar="#toolbar" singleSelect="true" fitColumns="true" 
	rownumbers="true" pagination="true" striped="true" pageSize="20">
	<thead>
		<tr>	
			<th field="name" formatter="nameFormatter" width="200" align="left">频道名称</th>
			<th field="soutvId" width="100" align="center">搜视网频道ID</th>
			<th field="videoType" formatter="videoTypeFormatter" width="100" align="center">清晰度</th>
			<th field="btv" formatter="YesOrNoFormatter" width="100" align="center">是否支持回看</th>
			<th field="networkID" width="100" align="center">networkID</th>
			<th field="tsID" width="100" align="center">tsID</th>
			<th field="serviceID" width="100" align="center">serviceID</th>
			<th field="rank" width="100" align="center">排序</th>
		</tr>
	</thead>
</table>

<div id="toolbar" style="padding:6px">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:doCreate();">新增</a>     
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:doDelete()">删除</a>    
	<div style='height:25;border-left:1px solid #ccc;border-right:1px solid #fff;display:inline;margin: 0px 6px'></div>
	<span>频道名称:</span><input type="text" id="name"/>
	<span>清晰度:</span><input class="easyui-combobox" id="videoType" style="width:70px">
	<span>是否回看:</span><input class="easyui-combobox" id="btv" style="width:70px">
	<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()" plain="true">查询</a>
</div>

<div id="dlg_create" class="easyui-dialog" style="width:500px;height:380px;padding:10px 20px" closed="true" modal="true" buttons="#dlg-buttons">
    <form id="dlg_form" method="post" enctype="multipart/form-data">
    	<table class="input">
    		<tr>
	            <th width="30%"><label class="panel-title" for="name">频道名称:</label></th>
	            <td width="70%">
	                <input type="text" id="create_name" name="name" class="easyui-validatebox" data-options='required:true,validType:"length[0,50]"'>
	            </td>
	        </tr>
	        <tr>
	            <th width="30%" nowrap="nowrap"><label class="panel-title" for="soutvId">搜视网频道ID:</label></th>
	            <td width="70%" class="color">
	                <input type="text" id="create_soutvId" name="soutvId" class="easyui-validatebox" data-options='required:true,validType:"length[0,50]"'>
	            </td>
	        </tr>
	        <tr>
	            <th width="30%"><label class="panel-title" for="soutvId">清晰度:</label></th>
	            <td width="70%">
	                <input class="easyui-combobox" id="create_videoType" name="videoType" data-options="valueField:'code',textField:'name',url:'comboBox_getVideoTypeList',required:true,editable:false">
	            </td>
	        </tr>
	        <tr>
	            <th width="30%"><label class="panel-title" for="btv">是否回看:</label></th>
	            <td width="70%" class="color">
	                <input class="easyui-combobox" id="create_btv" name="btv" data-options="valueField:'code',textField:'name',url:'comboBox_getYesOrNoList',required:true,editable:false">
	            </td>
	        </tr>
	        <tr>
	            <th width="30%"><label class="panel-title" for="networkID">networkID:</label></th>
	            <td width="70%">
	                <input type="text" id="create_networkID" name="networkID" class="easyui-validatebox" data-options='required:true,validType:"length[0,50]"'>
	            </td>
	        </tr>
	        <tr>
	            <th width="30%"><label class="panel-title" for="tsID">tsID:</label></th>
	            <td width="70%" class="color">
	                <input type="text" id="create_tsID" name="tsID" class="easyui-validatebox" data-options='required:true,validType:"length[0,50]"'>
	            </td>
	        </tr>
	        <tr>
	            <th width="30%"><label class="panel-title" for="serviceID">serviceID:</label></th>
	            <td width="70%">
	                <input type="text" id="create_serviceID" name="serviceID" class="easyui-validatebox" data-options='required:true,validType:"length[0,50]"'>
	            </td>
	        </tr>
	        <tr>
	            <th width="30%"><label class="panel-title" for="rank">排序:</label></th>
	            <td width="70%" class="color">
	                <input type="text" id="rank" name="rank" placeholder="输入一个排序数字" class="easyui-numberbox" data-options="max:10000">
	            </td>
	        </tr>
    	</table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="doSave()" plain="true">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_create').dialog('close')" plain="true">取消</a>
</div>
</body>
</html>
