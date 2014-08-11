<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分页</title>
</head>
		<div class="pagebar">
			<ul>
				<li><input type="hidden" id="totalPage"
					value="${page.totalPage}">
				</li>
				<li>每页显示<s:select id="pageSize" name="page.pageSize"
						list="#{'10':'10','20':'20','50':'50','100':'100'}" onchange="doChange(this)"></s:select>条
				</li>
				<li><input type="hidden" id="currentPage"
					value="${page.currentPage}">第${page.currentPage}/${page.totalPage}页</li>
				<li class="pnum_list">
				    <s:if test="page.hasPrePage ==true">
						<a class="link2"
							href="#" onclick="page('${page.prePage}')">上一页</a>
					</s:if>
					<s:else>
						<font color="#858585"> 上一页</font>
					</s:else>
				</li>
				<li class="pnum_list">
				   <s:if test="page.hasNextPage ==true">
						<a class="link2"
							href="#" onclick="page('${page.nextPage}')">下一页</a>
					</s:if>
					<s:else>
						<font color="#858585"> 下一页</font>
					</s:else>
				</li>
				<li class="jump_num">转到 <input id="pageNo2" name="pageNo"  onkeyup="this.value=this.value.replace(/\D/g,'');"
					type="text" /> 页</li>
				<li class="lastpageli"><input type="button"
					class="btn_confirmpage" onclick="jumpPage('pageNo2');" />
			</ul>
		</div>
</html>
