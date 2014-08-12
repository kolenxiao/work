<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<jsp:include page="../resource/resource.jsp"></jsp:include>

<head>
<script type="text/javascript">
	
	//弹出新增页面
	function doCreate() {
		$('#dlg').dialog('close');
		$('#dlg').dialog('open').dialog('setTitle','新增节目分类');
	    $('#dlg_form').form('clear');
	    url = "programType_create";
	}
	
	//弹出修改页面
	function doEdit(rowIndex){
		$('#list_data').datagrid('selectRow', rowIndex);
        var row = $("#list_data").datagrid("getSelected");
        if(row){
            $("#dlg").dialog("open").dialog("setTitle","修改节目分类");
            $("#dlg_form").form("load",row);
            url = "programType_update?id="+row.id;
        }
    }
	
	//保存
	function saveProgramType(){
	    $('#dlg_form').form('submit',{
	        url: url,
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	            var result = eval('('+result+')');
	            if ("0" == result.ret){
	            	$('#dlg').dialog('close');
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
	        $.messager.confirm('删除','确认删除节目分类?',function(r){
	            if (r){
	                $.post('programType_delete',{id:row.id},function(result){
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

</script>
</head>
<style>
a{
	text-decoration:none;color:blue;
}
</style>
<body>
<table id="list_data" class="easyui-datagrid" title="节目分类列表" url="programType_list" toolbar="#toolbar" singleSelect="true" fitColumns="true" 
	rownumbers="true" striped="true">
	<thead>
		<tr>	
			<th field="name" formatter="nameFormatter" width="100" align="center">分类名称</th>
			<th field="visible" formatter="YesOrNoFormatter" width="100" align="center">是否显示</th>
			<th field="tvSouTypeId" width="100" align="center">搜视网分类ID</th>
			<th field="rank" width="100" align="center">排序</th>
		</tr>
	</thead>
</table>

<div id="toolbar">    
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:doCreate();">新增</a>      
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:doDelete()">删除</a>    
</div>

<div id="dlg" class="easyui-dialog" style="width:500px;height:280px;padding:10px 20px" closed="true" modal="true" buttons="#dlg-buttons">
    <form id="dlg_form" method="post">
    	<table class="input">
    		<tr>
	            <th width="30%"><label class="panel-title" for="name">分类名称:</label></th>
	            <td width="70%">
	                <input type="text" name="name" placeholder="输入分类名称" class="easyui-validatebox" data-options='required:true,validType:"length[0,20]"'>
	            </td>
	        </tr>
	        <tr>
	            <th width="30%"><label class="panel-title" for="visible">是否显示:</label></th>
	            <td width="70%" class="color">
	            	<input class="easyui-combobox" id="visible" name="visible" data-options="valueField:'code',textField:'name',url:'comboBox_getYesOrNoList',required:true,editable:false">
	            </td>
	        </tr>
	        <tr>
	            <th width="30%" nowrap="nowrap"><label class="panel-title" for="tvSouTypeId">搜视网分类ID:</label></th>
	            <td width="70%">
	                <input type="text" name="tvSouTypeId" placeholder="输入搜视网分类ID">
	            </td>
	        </tr>
	        <tr>
	            <th width="30%"><label class="panel-title" for="rank">排序:</label></th>
	            <td width="70%"  class="color">
	                <input type="text" id="rank" name="rank" placeholder="输入一个排序数字" class="easyui-numberbox" data-options="required:true, min:0, max:10000">
	            </td>
	        </tr>
    	</table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveProgramType()" plain="true">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" plain="true">取消</a>
</div>

</body>
</html>