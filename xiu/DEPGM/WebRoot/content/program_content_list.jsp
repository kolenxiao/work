<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.coship.depgm.common.DepgmConfig" %>

<html>
<jsp:include page="../resource/resource.jsp"></jsp:include>
<head>

<script type="text/javascript">

	$(function(){
		//分类
		$.ajax({ 
			url: 'programType_list',
			dataType: 'json', 
			success: function(jsonstr){
				//原始的
		        $('#create_typeId').combobox({
		            data:jsonstr,
			        valueField:'id',
			        textField:'name',
			        required:true,
			        editable:false
		        });
		        
				//添加"全部"选项
				jsonstr.unshift({
					'id':'',
					'name':'全部'
				});
		        $('#typeId').combobox({
		            data:jsonstr,
			        valueField:'id',
			        textField:'name',
			        editable:false
		        });
 
		    }
		});
	});

	//查询
	function doSearch() {
		var params = {
   			'name': $("#name").val(),
   			'typeId': $('#typeId').combobox('getValue')
	   	};
		
		$('#list_data').datagrid('load', params);
	}
	
	//弹出新增页面
	function doCreate() {
		resetFileInput();
		$("#dlg_form #poster").attr("src","").hide();
        $("#dlg_form #horiPoster").attr("src","").hide();
		$('#dlg_create').dialog('close');
		$('#dlg_create').dialog('open').dialog('setTitle','新增节目信息');
	    $('#dlg_form').form('clear');
	    url = "programContent_create";
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
	//弹出修改页面
	function doEdit(rowIndex){
		resetFileInput();
		$('#list_data').datagrid('selectRow', rowIndex);
        var row = $("#list_data").datagrid("getSelected");
        if(row){
            $("#dlg_create").dialog("open").dialog("setTitle","修改节目信息");
            $("#dlg_form").form("load",row);
            $("#dlg_form #poster").attr("src",'<%=DepgmConfig.getPosterHttpUrl()%>'+row.poster).show();
            $("#dlg_form #horiPoster").attr("src",'<%=DepgmConfig.getPosterHttpUrl()%>'+row.horiPoster).show();

            $("#create_typeId").combobox('setValue', row.typeId);
            url = "programContent_update?id="+row.id;
        }
    }
	
	//删除
	function doDelete(){
	    var row = $('#list_data').datagrid('getSelected');
	    if (row){
	        $.messager.confirm('删除','确认删除节目?',function(r){
	            if (r){
	                $.post('programContent_delete',{id:row.id},function(result){
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
	
   //上传图片时进行预览
   function preImg(sourceId, targetId) {
		var url = getFileUrl(sourceId);
		var $imgPre = $("#"+targetId);
		if($imgPre.length>0){
			$imgPre.show();
			$imgPre.attr("src", url);
		}
    }
   
   function getFileUrl(sourceId) {
		var url = "";
		if (navigator.userAgent.indexOf("MSIE")>=1) { // IE
				url = document.getElementById(sourceId).value;
			    var newPreview = document.getElementById("newPreview");   
				var imgDiv = document.createElement("div");
				$('#showPic>img').remove();
				$('#showPic').append(imgDiv);
				imgDiv.style.width = "80px";    
				imgDiv.style.height = "60px";
				imgDiv.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod = scale)";  
				imgDiv.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = url;
				newPreview.appendChild(imgDiv);   
				var showPicUrl = document.getElementById("showPicUrl");
				showPicUrl.innerText= url;
				newPreview.style.width = "80px";
				newPreview.style.height = "60px";
				document.getElementById("imgPre").style.display='none';
		} else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox
			url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
		} else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome
			url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
		}
		return url;
	}


    function resetFileInput(){
    	var vfile = $("#attachment");
    	var hfile = $("#h_attachment");
    	var cfile = $("#chapter_attachment");
    	vfile.after(vfile.clone().val(""));
    	vfile.remove(); 
    	hfile.after(hfile.clone().val(""));
    	hfile.remove();
    	cfile.after(cfile.clone().val(""));
    	cfile.remove();
    }

    var Common = {
    	    ButtonFormatter:function(value,rec){ 
                var btn = '<a onclick="editChapter(\''+rec.id+'\')" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text">'+value+'</span></span></a>';  
                return btn;  
            }  
    	    
    	};
    function editChapter(contentId){
    		$("#dialog-chapter").dialog("open");
    		$('#list_chapter').datagrid({
    			url:'programContentChapter_chapterList',
                queryParams:{contentId:contentId},
    		});
    }
    function doDeleteChapter(){
    	var row = $('#list_chapter').datagrid('getSelected');
	    if (row){
	        $.messager.confirm('删除','确认删除该剧集?',function(r){
	            if (r){
	                $.post('programContentChapter_deleteChapter',{id:row.id},function(result){
	                    if ("0" == result.ret){
	                        $('#list_chapter').datagrid('reload');
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
    //创建剧集
    function doCreateChapter(){
		resetFileInput();
		$("#dlg_chapterForm #chapter_poster").attr("src","");
		$('#dlg_createChapter').dialog('close');
		$('#dlg_createChapter').dialog('open').dialog('setTitle','新增剧集');
	    $('#dlg_chapterForm').form('clear');
	    var row = $("#list_data").datagrid("getSelected");
	    $('#content_id').val(row.id);
	    $('#content_name').val(row.name);
	    url = "programContentChapter_createChapter";
    }
	//弹出修改剧集页面
	function doEditChapter(){
		resetFileInput();
        var row = $("#list_chapter").datagrid("getSelected");
        if(row){
            $("#dlg_createChapter").dialog("open").dialog("setTitle","修改剧集");
            $("#dlg_chapterForm").form("load",row);
            $("#dlg_chapterForm #chapter_poster").attr("src",'<%=DepgmConfig.getPosterHttpUrl()%>'+row.poster);
            url = "programContentChapter_updateChapter?id="+row.id;
        }
    }
	// 保存剧集
	function doSaveChapter(){
	    $('#dlg_chapterForm').form('submit',{
	        url: url,
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	            var result = eval('('+result+')');
	            if ("0" == result.ret){
	            	$('#dlg_createChapter').dialog('close');
	                $('#list_chapter').datagrid('reload');
                } else {
                    $.messager.show({
                        title: 'Error',
                        msg: result.retInfo
                    });
                }
	        }
	    });
	}
	
	function nameFormatter(value,row,index){
      	return '<a href="#" onclick=doEdit("'+index+'")>'+value+'</a>';	
    }

</script>
<style type="text/css">
#chapter_file_calcel:hover{
background-color:#0E2D5F;
}
a{
	text-decoration:none;color:blue;
}
</style>
</head>

<body>

<table id="list_data" class="easyui-datagrid" title="节目列表" url="programContent_list" toolbar="#toolbar" singleSelect="true" fitColumns="true" 
	rownumbers="true" pagination="true" striped="true" pageSize="20">
	<thead>
		<tr>	
			<th field="name" formatter="nameFormatter" width="300" align="left">节目名称</th>
			<th field="typeName" width="100" align="center">所属分类</th>
			<th field="btv" formatter="YesOrNoFormatter" width="100" align="center">回看状态</th>
			<th field="chapter" formatter="Common.ButtonFormatter" width="100" align="center">总集数</th>
		</tr>
	</thead>
</table>

<div id="toolbar" style="padding:6px">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:doCreate();">新增</a>      
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:doDelete()">删除</a>    
	<div style='height:25;border-left:1px solid #ccc;border-right:1px solid #fff;display:inline;margin: 0px 6px'></div>
	<span>节目名称:</span><input type="text" id="name" value="" size=12 />  
	<span>节目分类:</span><input class="easyui-combobox" id="typeId" style="width:80px">
	<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()" plain="true">查询</a>
</div>

<!--  -->
<jsp:include page="program_add_include.jsp"/>

<div id="chapterToolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:doCreateChapter();">新增</a>    
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:doEditChapter()">编辑</a>    
    <a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="javascript:doDeleteChapter()">删除</a>    
</div>
<div id="dialog-chapter" class="easyui-dialog" closed="true" style="width:800px;height:500px;" data-options="title:'剧集列表',modal:true">
	<table id="list_chapter" class="easyui-datagrid" url="" singleSelect="true" fitColumns="true" toolbar="#chapterToolbar"
	rownumbers="true" pagination="true" pageSize="20">
		<thead>
			<tr>
				<th field="contentName" width="100" align="center">节目名称</th>
				<th field="chapter" width="100" align="center">第几集</th>
			</tr>
		</thead>
	</table>
</div>

<div id="dlg-chapterButtons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="doSaveChapter()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_createChapter').dialog('close')">取消</a>
</div>
<div id="dlg_createChapter" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px" closed="true" buttons="#dlg-chapterButtons" data-options="modal:true">
    <form id="dlg_chapterForm" method="post" enctype="multipart/form-data">
	    <table class="input">
    		<tr>
	            <th width="30%"><label class="panel-title" for="contentName">节目名称:</label></th>
	            <td width="70%">
	                <input type="hidden" name="id" id="chapter_id"/>
	                <input type="hidden" name="contentId" id="content_id"/>
	                <input type="text" readonly="readonly" style="border:0px;" onclick="$(this).blur()" id="content_name" name="contentName" class="easyui-validatebox" data-options="required:true">
	            </td>
	        </tr>
	        <tr>
	            <th width="30%"><label class="panel-title" for="name">第几集:</label></th>
	            <td width="70%">
	                <input type="text" id="chapter_no" name="chapter" class="easyui-validatebox" data-options="required:true">
	            </td>
	        </tr>
	        <tr>
	        	<th width="30%"><label class="panel-title" for="visible"></label></th>
	        	<td>
	        		<img name="poster" id="chapter_poster" width="300px" height="200px" required="true"/>
	        	</td>
	        </tr>
	        <tr>
	        	<th width="30%"><label class="panel-title" for="visible">上传新海报:</label></th>
	        	<td>
	        		<input name="attachment" type="file" id="chapter_attachment" />
	        		<a href="#" id="chapter_file_calcel" style="color:#0E2D5F;width:45px;font-size:12px;" onclick="resetFileInput()"><img width="12px;" src="../resource/images/cancel.png"></a>
	        	</td>
	        </tr>
		 </table>
	 </form>
</body>
</html>
