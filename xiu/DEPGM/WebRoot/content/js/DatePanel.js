	var date = new Date(); 
    var weekIndex = date.getDay();
    var weekArry = new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六');  
    var week = weekArry[weekIndex]; 
    var curDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
function fixDatePanel(result,week,date){   
	if(result==0){
    	var dtNew=operDate(date.getTime(),false,6);
    	$("#weeks-panel ul li:eq(0) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(0) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),false,5);
    	$("#weeks-panel ul li:eq(1) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(1) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),false,4);
    	$("#weeks-panel ul li:eq(2) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(2) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate()); 
    	 dtNew=operDate(date.getTime(),false,3);
    	$("#weeks-panel ul li:eq(3) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(3) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),false,2);
    	$("#weeks-panel ul li:eq(4) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(4) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),false,1);
    	$("#weeks-panel ul li:eq(5) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(5) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	
    	$("#weeks-panel ul li:eq(6) .tabs-title").html(week+"&nbsp;"+(date.getMonth()+1)+"/"+date.getDate());
    	$("#weeks-panel ul li:eq(6) .tabs-title").attr("date",date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());

	}
    if(result==1){
    	$("#weeks-panel ul li:eq(0) .tabs-title").html(week+"&nbsp;"+(date.getMonth()+1)+"/"+date.getDate());
    	$("#weeks-panel ul li:eq(0) .tabs-title").attr("date",date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
    	 dtNew=operDate(date.getTime(),true,1);
    	$("#weeks-panel ul li:eq(1) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(1) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),true,2);
       	$("#weeks-panel ul li:eq(2) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate()); 
    	$("#weeks-panel ul li:eq(2) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),true,3);
    	$("#weeks-panel ul li:eq(3) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate()); 
    	$("#weeks-panel ul li:eq(3) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),true,4);
    	$("#weeks-panel ul li:eq(4) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(4) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),true,5);
    	$("#weeks-panel ul li:eq(5) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(5) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	var dtNew=operDate(date.getTime(),true,6);
    	$("#weeks-panel ul li:eq(6) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(6) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate()); 
    }
    if(result==2){
    	 dtNew=operDate(date.getTime(),false,1);
    	$("#weeks-panel ul li:eq(0) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(0) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(1) .tabs-title").html(week+"&nbsp;"+(date.getMonth()+1)+"/"+date.getDate());
    	$("#weeks-panel ul li:eq(1) .tabs-title").attr("date",week+"&nbsp;"+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
    	 dtNew=operDate(date.getTime(),true,1);
        $("#weeks-panel ul li:eq(2) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate()); 
    	$("#weeks-panel ul li:eq(2) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),true,2);
        $("#weeks-panel ul li:eq(3) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate()); 
    	$("#weeks-panel ul li:eq(3) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),true,3);
        $("#weeks-panel ul li:eq(4) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate()); 
    	$("#weeks-panel ul li:eq(4) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),true,4);
        $("#weeks-panel ul li:eq(5) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(5) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	var dtNew=operDate(date.getTime(),true,5);
    	$("#weeks-panel ul li:eq(6) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(6) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    }
    if(result==3){
    	 dtNew=operDate(date.getTime(),false,2);
    	$("#weeks-panel ul li:eq(0) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(0) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),false,1);
     	$("#weeks-panel ul li:eq(1) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(1) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(2) .tabs-title").attr("date",date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
    	$("#weeks-panel ul li:eq(2) .tabs-title").html(week+"&nbsp;"+(date.getMonth()+1)+"/"+date.getDate());
    	 dtNew=operDate(date.getTime(),true,1);
    	$("#weeks-panel ul li:eq(3) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(3) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),true,2);
    	$("#weeks-panel ul li:eq(4) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(4) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),true,3);
    	$("#weeks-panel ul li:eq(5) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(5) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	var dtNew=operDate(date.getTime(),true,4);
    	$("#weeks-panel ul li:eq(6) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(6) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    }
    if(result==4){
    	 dtNew=operDate(date.getTime(),false,3);
    	$("#weeks-panel ul li:eq(0) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(0) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),false,2);
    	$("#weeks-panel ul li:eq(1) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(1) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),false,1);
    	$("#weeks-panel ul li:eq(2) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
     	$("#weeks-panel ul li:eq(2) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
     	$("#weeks-panel ul li:eq(3) .tabs-title").attr("date",date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
     	$("#weeks-panel ul li:eq(3) .tabs-title").html(week+"&nbsp;"+(date.getMonth()+1)+"/"+date.getDate());
	   	 dtNew=operDate(date.getTime(),true,1);
	   	$("#weeks-panel ul li:eq(4) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
	 	$("#weeks-panel ul li:eq(4) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
	 	 dtNew=operDate(date.getTime(),true,2);
	 	$("#weeks-panel ul li:eq(5) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
	 	$("#weeks-panel ul li:eq(5) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	var dtNew=operDate(date.getTime(),true,3);
    	$("#weeks-panel ul li:eq(6) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(6) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    }
    if(result==5){
	   	 dtNew=operDate(date.getTime(),false,4);
	   	$("#weeks-panel ul li:eq(0) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
	 	$("#weeks-panel ul li:eq(0) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
	 	 dtNew=operDate(date.getTime(),false,3);
	 	$("#weeks-panel ul li:eq(1) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
	 	$("#weeks-panel ul li:eq(1) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
	 	 dtNew=operDate(date.getTime(),false,2);
	 	$("#weeks-panel ul li:eq(2) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
	  	$("#weeks-panel ul li:eq(2) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
	  	 dtNew=operDate(date.getTime(),false,1);
	  	$("#weeks-panel ul li:eq(3) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(3) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(4) .tabs-title").attr("date",date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
    	$("#weeks-panel ul li:eq(4) .tabs-title").html(week+"&nbsp;"+(date.getMonth()+1)+"/"+date.getDate());
	 	 dtNew=operDate(date.getTime(),true,1);
	 	$("#weeks-panel ul li:eq(5) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
		$("#weeks-panel ul li:eq(5) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	var dtNew=operDate(date.getTime(),true,2);
    	$("#weeks-panel ul li:eq(6) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(6) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    }
    if(result==6){
	   	 dtNew=operDate(date.getTime(),false,5);
	   	$("#weeks-panel ul li:eq(0) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
	 	$("#weeks-panel ul li:eq(0) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
	 	 dtNew=operDate(date.getTime(),false,4);
	 	$("#weeks-panel ul li:eq(1) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
	 	$("#weeks-panel ul li:eq(1) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
	 	 dtNew=operDate(date.getTime(),false,3);
	 	$("#weeks-panel ul li:eq(2) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
	  	$("#weeks-panel ul li:eq(2) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
	  	 dtNew=operDate(date.getTime(),false,2);
	  	$("#weeks-panel ul li:eq(3) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(3) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    	 dtNew=operDate(date.getTime(),false,1);
    	$("#weeks-panel ul li:eq(4) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
 	 	$("#weeks-panel ul li:eq(4) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
 	 	$("#weeks-panel ul li:eq(5) .tabs-title").attr("date",date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
    	$("#weeks-panel ul li:eq(5) .tabs-title").html(week+"&nbsp;"+(date.getMonth()+1)+"/"+date.getDate());
    	var dtNew=operDate(date.getTime(),true,1);
    	$("#weeks-panel ul li:eq(6) .tabs-title").attr("date",dtNew.getFullYear()+"-"+(dtNew.getMonth()+1)+"-"+dtNew.getDate());
    	$("#weeks-panel ul li:eq(6) .tabs-title").html(weekArry[dtNew.getDay()]+"&nbsp;"+(dtNew.getMonth()+1)+"/"+dtNew.getDate());
    }
}
    
$(function () {      

	currentWeek();
    
    $("#weeks-panel ul li .tabs-title").click(function(){
        $("#weeks-panel ul li").removeClass("tabs-selected");
    	var row = $('#dg_channel').datagrid('getSelected');
    	_globalDate = $(this).attr("date");
    	if(row!=null){
    		submit(row.id,_globalDate);
    	}
    });
});

function initWeekStyle(weekidx){
    $("#weeks-panel ul li").each(function(index,element){
    	if(index==(weekidx-1)||(weekidx==0&&index==6)){
    		$(this).addClass("tabs-selected");
    	}
    	else{
    		$(this).removeClass("tabs-selected");
    	}
    });
}

function preWeek(){		
	$("#weeks-panel ul li").removeClass("tabs-selected");
	var preDate=operDate(date.getTime(),false,13-(7-weekIndex));
	fixDatePanel(1,weekArry[1],preDate);
	initWeekStyle(1);
	
	var row = $('#dg_channel').datagrid('getSelected');
	_globalDate = preDate.getFullYear()+"-"+(preDate.getMonth()+1)+"-"+preDate.getDate();
	if(row!=null){
		submit(row.id,_globalDate);
	}
	
}
function currentWeek(){
	$("#weeks-panel ul li").removeClass("tabs-selected");
	initWeekStyle(weekIndex);
	fixDatePanel(weekIndex,week,date);
	
	var row = $('#dg_channel').datagrid('getSelected');
	_globalDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	if(row!=null){
		submit(row.id,_globalDate);
	}
	
}
function nextWeek(){
	$("#weeks-panel ul li").removeClass("tabs-selected");
	var nextDate=operDate(date.getTime(),true,8-weekIndex);
	fixDatePanel(1,weekArry[1],nextDate);
	initWeekStyle(1);
	
	var row = $('#dg_channel').datagrid('getSelected');
	_globalDate = nextDate.getFullYear()+"-"+(nextDate.getMonth()+1)+"-"+nextDate.getDate();
	if(row!=null){
		submit(row.id,_globalDate);
	}
	
}

var _globalDate=formatDate(new Date());

var Common = {
	    //EasyUI用DataGrid用日期格式化
	    TimeFormatter: function (value, rec, index) {
            var unixTimestamp = new Date(value);  
            var hour=unixTimestamp.getHours();
            var minute=unixTimestamp.getMinutes();
            return (hour<10?'0'+hour:hour)+":"+(minute<10?'0'+minute:minute);  
	    },
	    ButtonFormatter:function(contentName,rec){ 
            var btn = '<a onclick="editRow(\''+rec.contentId+'\',\''+rec.id+'\',\''+contentName+'\',\''+rec.name+'\')" href="javascript:void(0)" style="color:#0E2D5F">'+contentName+'</a>';  
	    	if(null==rec.contentId){
	    		btn = '<a onclick="editRow(\''+rec.contentId+'\',\''+rec.id+'\',\''+contentName+'\',\''+rec.name+'\')" href="javascript:void(0)" style="color:red">请关联节目</a>';
	    	}
            return btn;  
        },  
	    ChannelFormatter:function(value,rec){
	    	if(rec.avaliableToday==1){
	    		var row = '<a onClick="submit(\''+value+'\')" href="javascript:void(0)" style="color:#000000;text-decoration:none;">'+rec.name+'</a>';
	    	}else{
	    		var row = '<a onClick="submit(\''+value+'\')" href="javascript:void(0)" style="color:red;text-decoration:none;">'+rec.name+'</a>';
	    	}
	    	return row;
	    },
	    CheckBoxFormatter:function(value,rec){
	    	var row = '<input type="checkbox" onClick="reverseCheck(\''+rec.id+'\')" value="'+rec.id+'"/>';
	    	return row;
	    }
	};
function reverseCheck(value){
	$("input[type=checkbox]").each(function(){
		if($(this).val()!=value){
			$(this).removeAttr("checked"); 
		}else{
			$("#dialog_programContentId").val(value);
			$(this).attr("checked","checked");
		}
	});
}	
function editRow(contentId,id,contentName,name){
	$('#list_programContent').datagrid('load', {});
	$('#dialog_program_name').text(name);
	if(contentName==''||contentName==null||contentName=='null'){
		$("#contentNameSearchBox").val(name);
		searchAlias();
	}else{
		$("#contentNameSearchBox").val(contentName);
	}
	$('#dialog_programId').val(id);
	$('#dialog_programContentId').val(contentId=='null'?'':contentId);
	$('#dialog_old_content').text((null==contentName||contentName==''||contentName=='null')?'':contentName);
	$('#dialog').dialog();
}
function submit(channelId){
	$('#dg_program').datagrid({
		url:'pgm_getProgramList',
		queryParams:{
		channelId:channelId,
		beginTime:_globalDate,
		endTime:_globalDate
		}
		});
}
function formatDate(date) {
    return date.getFullYear() + "-" + (date.getMonth() +1) + "-" + date.getDate();
}
function operDate(time,flag,days){
	var date = new Date();
	if(flag){
		return new Date(time+days*24*60*60*1000);
	}
	else{
		return new Date(time-days*24*60*60*1000);
	}
}
function getCurrentDate(){
	return new Date();
}

function searchAlias(){
	var contentName=$("#contentNameSearchBox").val();
	$('#list_programContent').datagrid({
		url:'programContent_list',
		queryParams:{
		name:contentName
		}
		});
}
function createProgramContent(){
	$('#dlg_create').dialog('close');
	$('#dlg_create').dialog('open').dialog('setTitle','新增节目信息');
    $('#dlg_form').form('clear');
}
//保存新增的节目
function doSave(){
    $('#dlg_form').form('submit',{
        url: 'programContent_create',
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if ("0" == result.ret){
            	$('#dlg_create').dialog('close');
            	var createName=$("#create_name").val();
            	var contentName=$("#contentNameSearchBox").val(createName);
            	searchAlias();
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.retInfo
                });
            }
        }
    });
}
$(function () {                   
    $('#dialog').dialog('close'); 
    $('#channelSearchBox').keydown(function(e){
    	if(e.keyCode==13){
    		var name=$('#channelSearchBox').val();
        		$('#dg_channel').datagrid('reload',{
        			name:name
        		});
    	}
    });
   $("#preWeek").tooltip({
	   position: 'top',
	   content: '<span style="color:#fff">查看上周节目单</span>',
	   onShow: function(){
	   $(this).tooltip('tip').css({
	   backgroundColor: '#666',
	   borderColor: '#666'
	   });
	   }
	   });

	$("#currentWeek").tooltip({
	   position: 'top',
	   content: '<span style="color:#fff">查看本周节目单</span>',
	   onShow: function(){
	   $(this).tooltip('tip').css({
	   backgroundColor: '#666',
	   borderColor: '#666'
	   });
	   }
	   });

	$("#nextWeek").tooltip({
		   position: 'top',
		   content: '<span style="color:#fff">查看下周节目单</span>',
		   onShow: function(){
		   $(this).tooltip('tip').css({
		   backgroundColor: '#666',
		   borderColor: '#666'
		   });
		   }
		   });
});