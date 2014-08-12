<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<jsp:include page="../../resource/resource.jsp"></jsp:include>

<head>
<script type="text/javascript">

	//根据分类查询节目列表
	function getLiveProgramsByTypeId(index, row){
		$('#dg_program').datagrid({  
			title:'节目单列表',
		    url:'depg_getLivePrograms',
		    queryParams:{typeId: row.id},
		    columns:[[
		        {field:'name',title:'节目',width:100,align:'center'},
		        {field:'channelName',title:'频道',width:50,align:'center'},
		        {field:'beginTime',title:'开始时间',width:50,align:'center',formatter:dateFormatter},
		        {field:'endTime',title:'结束时间',width:50,align:'center',formatter:dateFormatter},
		        {field:'poster',title:'海报',width:100,align:'center',formatter:posterFormatter}
		    ]],
		    pagination:true,
		    pageSize:10,
		    border:0,
		    striped:true,
		    fit:true,
		    fitColumns:true,
		    rownumbers:true,
		    singleSelect:true
        });
	}
	
	function initSelectRow(){
		$('#list_data').datagrid('selectRow', 0);
	}

	
	//列表中显示海报
	function posterFormatter(value,row,index){
		return '<img name="poster" width="120px" height="80px" src='+value+' />';
    } 
	
	function dateFormatter(value,row,index) {
		var date = new Date(value.replace(/-/g,   "/"));
		return date.getHours() + ':' + date.getMinutes();
	}

	
</script>
</head>

<body>
<div style="postition:relative;width:14%;height:100%;float:left;border:1px #95B8E7 solid;overflow: scroll;">
	<table id="list_data" class="easyui-datagrid" title="分类列表" url="depg_getProgramTypeList" singleSelect="true" fitColumns="true" 
		data-options="onSelect:getLiveProgramsByTypeId, onLoadSuccess:initSelectRow">
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



</body>
</html>