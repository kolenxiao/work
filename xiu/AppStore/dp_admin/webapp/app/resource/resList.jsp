<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import ="java.util.List, java.util.ArrayList, java.util.Map, java.util.HashMap, com.coship.sdp.permission.entity.Resource" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript">

     function openFold(level, id){
 		 var show_img_src = document.getElementById("img_"+id).src;

    	 if(show_img_src.indexOf("btn_plus.gif")!=-1){
    		 openNode(level, id);
    	 }else{
    		 closeNode(level, id);
    	 }
     }

     function openAllNode(level, id){
    	 openNode(level, id);

    	 var child_level = parseInt(level)+1;
		 var child_array = document.getElementsByName(child_level+","+id);

		 for(var i=0;i<child_array.length;i++){
			 var res_date = child_array[i].id.split(",");
			 openAllNode(child_level, res_date[0]);
		 }
     }


     function openNode(level, id){
    	 if(document.getElementById("img_"+id)!=null){
    	     var show_img_src = document.getElementById("img_"+id).src;
    	     document.getElementById("img_"+id).src = show_img_src.replace("btn_plus.gif","btn_minus.gif");
    	 }

    	 var child_level = parseInt(level)+1;
		 var child_array = document.getElementsByName(child_level+","+id);

		 var init_indent = 2*level;

		 var signFrame = document.getElementById("res_table_id");

		 for(var i=0;i<child_array.length;i++){
	 		 var current_row_no = document.getElementById("tr_"+id).rowIndex;

			 current_row_no++;
			 var newTR = signFrame.insertRow(current_row_no);

			 var res_date = child_array[i].id.split(",");
			 newTR.id = "tr_"+res_date[0];

			 newTR.insertCell(0).innerHTML = "<label><input name='"+res_date[7]+"' id='checkbox_"+res_date[0]+"' type='checkbox' onclick=\"selectCheckboxByPid('"+res_date[6]+"', '"+res_date[0]+"', '"+res_date[7]+"', this.checked)\"/></label>";
			 newTR.insertCell(1).innerHTML = "<div style='word-wrap:break-word;overflow:hidden;width:200px;text-indent:"+init_indent+"em;' align='left'><span onclick=\"openFold('"+res_date[6]+"', '"+res_date[0]+"');\" style='cursor:pointer'><img id='img_"+res_date[0]+"' src='./images/btn_plus.gif'>"+res_date[1]+"</span></div>";
			 newTR.insertCell(2).innerHTML = res_date[2];
			 newTR.insertCell(3).innerHTML = res_date[3];
			 newTR.insertCell(4).innerHTML = res_date[4];
			 newTR.insertCell(5).innerHTML = res_date[5];
			 var newTD = newTR.insertCell(6);
			 newTD.className="editbar";
			 if(child_level<=2){
				 newTD.innerHTML = "<span><a href='resource!showAddRes.action?id="+res_date[0]+"' target='mainFrame'><s:text name='sdp.sce.dp.admin.global.btn.add'/></a></span>"+
                 "<span><a href='resource!showModify.action?id="+res_date[0]+"' target='mainFrame'><s:text name='sdp.sce.dp.admin.global.btn.modify'/></a></span>";
			 }else{
				 newTD.innerHTML = "<span><s:text name='sdp.sce.dp.admin.global.btn.add'/></span>"+
                 "<span><a href='resource!showModify.action?id="+res_date[0]+"' target='mainFrame'><s:text name='sdp.sce.dp.admin.global.btn.modify'/></a></span>";
			 }
		 }
     }

     function closeNode(level, id){
    	 if(document.getElementById("img_"+id)!=null){
    		 var show_img_src = document.getElementById("img_"+id).src;
        	 document.getElementById("img_"+id).src = show_img_src.replace("btn_minus.gif","btn_plus.gif");
    	 }

    	 var child_level = parseInt(level)+1;
		 var child_array = document.getElementsByName(child_level+","+id);

		 var signFrame = document.getElementById("res_table_id");

    	 for(var i=0;i<child_array.length;i++){
			 var res_date = child_array[i].id.split(",");

			 closeNode(child_level, res_date[0]);

			 if(document.getElementById("tr_"+res_date[0])!=null){
				 signFrame.deleteRow(document.getElementById("tr_"+res_date[0]).rowIndex);
			 }
		 }
     }

     function selectNode(level, id, parentId){
    	 var child_level = parseInt(level)+1;
		 var child_array = document.getElementsByName(child_level+","+id);

 		 for(var i=0;i<child_array.length;i++){
 			 var res_date = child_array[i].id.split(",");
 			 if(document.getElementById("checkbox_"+res_date[0])==null){
 				openAllNode(level ,res_date[7]);
 			 }
			 document.getElementById("checkbox_"+res_date[0]).checked=true;
 		 }
     }

     function selectAllNode(level, id, parentId){
    	 selectNode(level, id, parentId);

    	 var child_level = parseInt(level)+1;
		 var child_array = document.getElementsByName(child_level+","+id);

 		 for(var i=0;i<child_array.length;i++){
 			 var res_date = child_array[i].id.split(",");
			 selectAllNode(child_level, res_date[0], res_date[7]);
 		 }
     }

     function disSelectNode(level, id, parentId){
    	 var child_level = parseInt(level)+1;
		 var child_array = document.getElementsByName(child_level+","+id);

 		 for(var i=0;i<child_array.length;i++){
 			 var res_date = child_array[i].id.split(",");
			 document.getElementById("checkbox_"+res_date[0]).checked=false;
 		 }
     }

     function disSelectAllNode(level, id, parentId){
    	 disSelectNode(level, id, parentId);

    	 var child_level = parseInt(level)+1;
		 var child_array = document.getElementsByName(child_level+","+id);

 		 for(var i=0;i<child_array.length;i++){
 			 var res_date = child_array[i].id.split(",");
 			disSelectAllNode(child_level, res_date[0], res_date[7]);
 		 }
     }

     function selectCheckboxByPid(level, id, parentId, checked){

    	//2012-02-23 add 如果子选框中有一个没有选中，则去除全选框的选中状态。
     	 var checkAll=$("#totalCheckbox")[0];
 		 $.each($('table tr td :checkbox').get(), function(index,
 				obj) {
 			         if(obj.checked==false){
 			        	 checkAll.checked=false;
 			             return false;
 			         }
 			         checkAll.checked=true;
 		 });
 		//ended of 2012-02-23

    	 if(checked){
    		 var show_img_src = document.getElementById("img_"+id).src;

        	 if(show_img_src.indexOf("btn_plus.gif")!=-1){
        		 openAllNode(level, id);
        	 }

    		 selectAllNode(level, id, parentId);
    	 }else{
    		 if(parentId!=null){
 				var parent_checkbox = document.getElementById("checkbox_"+parentId);
 				if(parent_checkbox!=null){
 					parent_checkbox.checked=false;
 					var parent_parent_checkbox_id = parent_checkbox.name;
 					var parent_parent_checkbox = document.getElementById("checkbox_"+parent_parent_checkbox_id);
 					if(parent_parent_checkbox!=null){
 						parent_parent_checkbox.checked=false;
 					}
 				}
 			}

    		 disSelectAllNode(level, id, parentId);
    	 }
     }

	// Begin Add 2011-8-1

	// 获得每一个tr td里的checkbox，并设置它的状态和totalCheckbox一致
	function selectChange(){
		$("table tr td :checkbox").attr("checked",$("#totalCheckbox").attr("checked"));
	}


	// 删除资源操作
	function delList(){
		var $checkedBox = $("table tr td :checked");// 获得已选择的checkbox
		var idList= '';
		var checkedBoxLen = $checkedBox.length;
		if(0 == checkedBoxLen){
			dialogList('<s:text name="sdp.sce.dp.admin.global.btn.tishi"/>',300,150,'<s:text name="sdp.sce.dp.admin.resources.select.del.data"/>',0,0,1,this);
			return;
		}else{
			dialogList('<s:text name="sdp.sce.dp.admin.global.btn.tishi"/>',300,150,'<s:text name="sdp.sce.dp.admin.global.del.data.confirm"/>',0,0,2,
					this);
			//注意:此处的dialog_btn_conform为dialogList的"确定"按钮的id,点击"确定"时执行删除操作

			$("#dialog_btn_conform").click(function(){
				// 把选中的id按倒序组织成一个string
				for(var i=(checkedBoxLen-1);i>=0;i--){
					idList += $checkedBox[i].id ;
					if(i>0)idList += '_';
				}
				// 删除资源的链接url
				location.href = "<s:property value='#session.userResMap.deleteRes.url' />?idList="+idList.replaceAll("checkbox_", "");
			});
		}

	}

	function errorMsg() {
		var msg = '<s:property value="errorMsg"/>';
		if(msg != null && msg.trim().length > 0) {
			dialogList('<s:text name="sdp.sce.dp.admin.global.btn.tishi"/>',300,150,msg.replace("SUCCESS","<s:text name='sdp.sce.dp.admin.resources.delete.success'/>")+"。",0,0,1,this);
		}
	}
</script>
</head>

<body id="cnt_body" >
	<div id="position">
		<p><s:text name="sdp.sce.dp.admin.global.label.current.position"/> <s:text name="sdp.sce.dp.admin.resources"/>&gt; <s:text name="sdp.sce.dp.admin.resources.list"/></p>
		<div id="pright"></div>
	</div>

<div id="searchbar" >
  <div id="search_itemlist" style="display:none;">

  </div>
</div>

	<div class="databar">
		<div class="btnbar">
			<ul>
			<li>
				<s:if test="#session.userResMap.addRes != null">
					<a href='resource!showAddRes.action?id='
						target="mainFrame"><b><s:text name="sdp.sce.dp.admin.resources.add.menu"/></b> </a>
				</s:if>
			</li>
			<li>

			</li>
			<li>
				<s:if test="#session.userResMap.deleteRes != null">
					<a id="delIdList" href="#" onclick="delList();" target="mainFrame"><b><s:text name="sdp.sce.dp.admin.global.btn.del"/></b></a>
				</s:if>
			</li>
			</ul>
		</div>
	</div>

<div class="data_list">
<table id="res_table_id" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th width="20px" scope="col">
        <span id="sprycheckbox1">
        <label><input type="checkbox" id="totalCheckbox" onClick="selectChange()"/></label>
        </span>
    </th>
    <th scope="col" width="200px"><s:text name="sdp.sce.dp.admin.resources.resourceName"/></th>
    <th scope="col" width="250px"><s:text name="sdp.sce.dp.admin.resources.resourceURL"/></th>
    <th scope="col" width="150px"><s:text name="sdp.sce.dp.admin.resources.resource.identifier"/></th>
    <th scope="col" width="60px"><s:text name="sdp.sce.dp.admin.resources.resourceCreator"/></th>
    <th scope="col" width="130px"><s:text name="sdp.sce.dp.admin.resources.resource.Utime"/></th>
    <th class="editbar"  scope="col" width="120px"><s:text name="sdp.sce.dp.admin.global.label.operate"/></th>
  </tr>
  <s:iterator value="resLevelOne">
      <tr id="tr_<s:property value='id'/>">
		<td>
		<label><input id="checkbox_<s:property value='id'/>" type="checkbox" onclick="selectCheckboxByPid('<s:property value="level"/>', '<s:property value="id"/>', null, this.checked)"/></label>
		</td>
		<td>
		    <div style="word-wrap:break-word;overflow:hidden;width:200px;" align="left" >
			<span onclick="openFold('<s:property value="level"/>', '<s:property value="id"/>');" style="cursor:pointer">
				<img id="img_<s:property value='id'/>" src="./images/btn_plus.gif"><s:property value="name"/>
			</span>
			</div>
		</td>
		<td><s:property value="url"/></td>
		<td><s:property value="enName"/></td>
		<td><s:property value="createdUser"/></td>
		<td><s:date name="updatedDate" format="yyyy-MM-dd HH:mm:ss" /></td>


		<td class="editbar">
			<s:if test="#session.userResMap.addRes != null">
				    <a href="resource!showAddRes.action?id=<s:property value="id"/>" target="mainFrame"><s:text name="sdp.sce.dp.admin.global.btn.add"/></a>
			</s:if>
    		<s:if test="#session.userResMap.modifyRes != null">
				    <a href="resource!showModify.action?id=<s:property value="id"/>" target="mainFrame"><s:text name="sdp.sce.dp.admin.global.btn.modify"/></a>
			</s:if>
   		</td>
	</tr>
  </s:iterator>
  </table>
</div>

<div class="databar">
		<div class="btnbar">
			<ul>
			<li>
				<s:if test="#session.userResMap.addRes != null">
					<a href='resource!showAddRes.action?id=' target="mainFrame"><b><s:text name="sdp.sce.dp.admin.resources.add.menu"/></b> </a>
				</s:if>
			</li>
			<li>

			</li>
			<li>
				<s:if test="#session.userResMap.deleteRes != null">
					<a id="delIdList" href="#" onclick="delList()" target="mainFrame"><b><s:text name="sdp.sce.dp.admin.global.btn.del"/></b></a>
				</s:if>
			</li>
			</ul>
		</div>
</div>

<s:iterator value="resChildren">
<input type="hidden" name="<s:property value='level'/>,<s:property value='parentRes.id'/>" id="<s:property value='id'/>,<s:property value='name'/>,<s:property value='url'/>,<s:property value='enName'/>,<s:property value='createdUser'/>,<s:date name="updatedDate" format="yyyy-MM-dd HH:mm:ss" />,<s:property value='level'/>,<s:property value='parentRes.id'/>" />
</s:iterator>

</body>
</html>
