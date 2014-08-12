<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<jsp:include page="../../resource/resource.jsp"></jsp:include>

<head>
<script type="text/javascript">

	//初始选中第一条分类
	function initSelectRow(){
		$('#list_data').datagrid('selectRow', 0);
	}

	//根据分类查询节目列表
	function getBTVProgramsByTypeId(index, row){
		$('#dg_program').datagrid({  
			title:'节目单列表',
		    url:'depg_getBTVPrograms',
		    queryParams:{typeId: row.id},
		    columns:[[
		        {field:'name',title:'节目名称',width:100,align:'center',formatter:nameFormatter},
		        {field:'poster',title:'海报',width:100,align:'center',formatter:posterFormatter}
		    ]],
		    pagination:true,
		    pageSize:10,
		    border:0,
		    striped:true,
		    fit:true,
		    fitColumns:true,
		    rownumbers:true,
		    singleSelect:true,
		    onDblClickRow:doDblClick
        });
	}

	//弹出节目详情页面
	function doDblClick(index, row){
		doDetail(row.id);
	}
	
	function doDetail(programId){
    	$("#dlg_detail").form("clear");
    	$("#dlg_detail").window({
    		title: "节目详细信息",
    		width: 800,
    		height: 600,
    		modal:false
    	}).window("open");
    	
    	$.getJSON('depg_getProgramDetail?programId='+ programId, function(data){
    		$("#poster").attr("src", data.poster).show();
    		$("#name").html(data.name);
    		if(undefined != data.director){
    			$("#director").html("导演: " + data.director);
    		}
    		if(undefined != data.leadingActor){
    			$("#leadingActor").html("主演: " + data.leadingActor);
    		}
    		if(undefined != data.assetTypes){
    			$("#assetTypes").html("类型: " + data.assetTypes);
    		}
    		if(undefined != data.desc){
    			$("#description").html("简介:<br>"+data.desc);
    		}

    		//导入节目单数据
    		if(undefined != data.programGuideList){
    			$('#div_guid').show();
    			$('#tab_guid').datagrid({  
    				title:'节目单列表',
    			    columns:[[
    			        {field:'name',title:'名称',width:120,align:'center'},
    			        {field:'beginTime',title:'开始时间',width:80,align:'center',formatter:dateFormatter},
    			        {field:'endTime',title:'结束时间',width:80,align:'center',formatter:dateFormatter}
    			    ]],
    			    border:0,
    			    striped:true,
    			    fit:true,
    			    fitColumns:true,
    			    rownumbers:true,
    			    singleSelect:true
    	        });
    			$('#tab_guid').datagrid('loadData',data.programGuideList);
    		}else{
        		$('#div_guid').hide();
        		$('#tab_guid').datagrid('loadData',{total:0,rows:[]});
    		}
    	});
    }


	//Formatter
	function nameFormatter(value,row,index){
      	return '<a href="#" onclick=doDetail("'+row.id+'")>'+value+'</a>';	
    }

	function posterFormatter(value,row,index){
		return '<img name="poster" width="120px" height="80px" src='+value+' />';
    } 
	
	function dateFormatter(value){ 
		var date = new Date(value.replace(/-/g,   "/"));
		return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate() + " " + date.getHours() + ':' + date.getMinutes();;
	}
	
	
		
</script>
</head>

<body>
<div style="postition:relative;width:14%;height:100%;float:left;border:1px #95B8E7 solid;overflow: scroll;">
	<table id="list_data" class="easyui-datagrid" title="分类列表" url="depg_getProgramTypeList" singleSelect="true" fitColumns="true" 
		data-options="onSelect:getBTVProgramsByTypeId, onLoadSuccess:initSelectRow">
		<thead>
			<tr>	
				<th field="name" width="100" align="center">分类名称</th>
			</tr>
		</thead>	
	</table>
</div>

<div style="postition:relative;width:85%;height:100%;border:1px #95B8E7 solid;float:right;">
	<table id="dg_program">
	</table>
</div>


<div id="dlg_detail">
	<div>
		<div style="width:220px; height:330px; float:left;">
			<img id="poster" width="200px" height="300px" src="" style="display:none;">
		</div>
	  	<div style="width:300px;  height:330px; float:left;">
	  		<table style="text-align: left" border="0">
	    		<tr>
		            <th width="60%"><font size="3" color="red" id="name"></font></th>
		        </tr>
		        <tr>
		        	<th width="60%"><font size="2" id="director"></font></th>
		        </tr>
		        <tr>
		            <th width="60%"><font size="2" id="leadingActor"></font></th>
		        </tr>
		        <tr>
		            <th width="60%"><font size="2" id="assetTypes"></font></th>
		        </tr>
		        <tr>
		        	<th width="60%" style="word-wrap:break-word"><font size="2" color="blue" id="description"></font></th>
		        </tr>
	    	</table>
	  	</div>
	</div>

	<div id="div_guid" style="postition:relative;width:750;height:200;border:1px #95B8E7 solid;float:left;display:none">
		<table id="tab_guid">
		</table>
	</div>
	
</div>




</body>
</html>