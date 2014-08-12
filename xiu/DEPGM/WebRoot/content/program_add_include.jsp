<%@ page language="java" pageEncoding="UTF-8"%>

<div id="dlg_create" class="easyui-dialog" style="width:800px;height:600px;padding:10px 20px" closed="true" modal="true" buttons="#dlg-buttons">
    <form id="dlg_form" method="post" enctype="multipart/form-data">
    	<table class="input">
    		<tr>
	            <th width="30%"><label class="panel-title" for="name">节目名称:</label></th>
	            <td width="70%">
	                <input type="text" id="create_name" name="name" class="easyui-validatebox" data-options='required:true,validType:"length[0,50]"'>
	            </td>
	        </tr>
	        <tr>
	            <th width="30%"><label class="panel-title" for="typeId">所属分类:</label></th>
	            <td width="70%">
	            	<input class="easyui-combobox" id="create_typeId" name="typeId">
	            </td>
	        </tr>
	        <tr>
	            <th width="30%"><label class="panel-title" for="name">总集数:</label></th>
	            <td width="70%">
	                <input type="text" id="create_chapter" name="chapter" class="easyui-numberbox" data-options="required:true, min:0, max:10000">
	            </td>
	        </tr>
	        <tr>
	        	<th width="30%"></th>
	        	<td>
	        		<img name="poster" id="poster" width="80px" height="120px" required="true"/>
	        		&nbsp;&nbsp;&nbsp;&nbsp;
	        		<img name="horiPoster" id="horiPoster" width="120px" height="80px" required="true"/>
	        	</td>
	        </tr>
	        <tr>
	        	<th width="30%"><label class="panel-title" for="attachment">上传竖版海报:</label></th>
	        	<td>
	        		<input name="attachment" type="file" id="attachment" onchange="preImg(this.id,'poster');" />
	        	</td>
	        </tr>
	        <tr>
	        	<th width="30%"><label class="panel-title" for="visible">上传横版海报:</label></th>
	        	<td>
	        		<input name="hattachment" type="file" id="h_attachment" onchange="preImg(this.id,'horiPoster');" />
	        	</td>
	        </tr>
	        <tr>
	            <th width="30%"><label class="panel-title" for="description">描述:</label></th>
	            <td width="70%">
	                <textarea class="easyui-validatebox" type="textarea" id="description" name="description"  rows="10" style="width:500px" 
	                data-options='validType:"length[0,4000]"' ></textarea>
	            </td>
	        </tr>
    	</table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="doSave()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_create').dialog('close')">取消</a>
</div>