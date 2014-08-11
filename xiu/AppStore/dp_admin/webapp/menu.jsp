<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.coship.sdp.permission.entity.Resource" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开发者管理后台</title>
<script type="text/javascript">
	function menuSlide(idName, hideOtherList, menuid, event) {
		var event = window.event || event;
		var obj = event.srcElement || event.target;

		if (hideOtherList == 1) {
			$("#" + menuid + " dl").slideUp(300);
			$("#" + menuid + " a").each(function() {
				if ($(this).attr("id") == idName) {
					$(this).removeAttr("id");
				}
			});
		} else {

		}
		$(obj).attr("id", idName);
		if ($(obj).next("dl").is(":hidden")) {
			$(obj).next("dl").slideDown(300)
		} else {
			$(obj).next("dl").slideUp(300);
			$(obj).removeAttr("id");
		}
	}

	function addID(idName, event) {
		var event = window.event || event;
		var obj = event.srcElement || event.target;
		$("#menubar dl a").removeAttr("id");
		$(obj).attr("id", idName);
	}

	$(function() {
		adjustMenuHeight();
		window.onresize = adjustMenuHeight;
		//展开第一项
		$("#menubar dl:eq(0)").show();
		$("#menubar a:eq(0)").attr("id", "menu_on");
	})

	function adjustMenuHeight() {
		var webH = document.documentElement.clientHeight;
		var mtopH = document.getElementById("menubar_top").offsetHeight;
		document.getElementById("wrap_menu").style.height = (webH - mtopH - 1)
				+ "px";
	}

	function hideMenu(widthpx, showSwitch, hideSwitch) {
		top.document.getElementById("leftFrame").style.width = "0.8%";
		top.document.getElementById("mainFrame").style.width = "98.2%";
		document.getElementById(showSwitch).style.display = "block";
		document.getElementById(hideSwitch).style.display = "none";
		var newdiv = document.createElement("div");
		newdiv.setAttribute("id", "menu_mask");
		document.body.appendChild(newdiv);
		var webH = document.documentElement.clientHeight;
		document.getElementById("menu_mask").style.height = (webH - 2) + "px";
	}

	function showMenu(widthpx, showSwitch, hideSwitch) {
		top.document.getElementById("leftFrame").style.width = "15%";
		top.document.getElementById("mainFrame").style.width = "84%";
		document.getElementById(showSwitch).style.display = "block";
		document.getElementById(hideSwitch).style.display = "none";
		document.body.removeChild(document.getElementById("menu_mask"));
	}
</script>
</head>

<body>
	<div id="menu_switchHide">
		<a href="#" onclick="hideMenu(10,'menu_switchShow','menu_switchHide')"><img
			src="images/btn_close.gif" width="10px" height="17px" alt="折叠" /> </a>
	</div>
	<div id="menu_switchShow">
		<a href="#"
			onclick="showMenu(182,'menu_switchHide','menu_switchShow')"><img
			src="images/btn_display.gif" width="10px" height="17px" alt="展开" /> </a>
	</div>
	<div id="menubar_top">
		<img src="images/bg_menutop.jpg" width="182px" height="16px" />
	</div>
	<div id="wrap_menu">
		<div id="menubar">
			<ul>

		  <%if(request.getSession().getAttribute("resList")!=null){
			    List<Resource> res = (List)request.getSession().getAttribute("resList");


				for(int i=0; i<res.size(); i++) {
					Resource r = res.get(i);
					if(r.getLevel() == 1) {
			%>
				<li><a href="#"
						onclick="menuSlide('menu_on',1,'menubar',event)">
						<%=r.getName() %>
						</a>
			<%
						if(r.getRes()!= null && r.getRes().size() >0) {
			%>
						<dl>
			<%
							for(Iterator<Resource> iter = r.getRes().iterator(); iter.hasNext();) {
								Resource child = iter.next();
			%>
							<dd>
								<a href="<%=child.getUrl() %>" target="mainFrame"
												onclick="addID('menusub_on',event)">
								<%=child.getName() %>
												</a>
							</dd>
			<%
							}
			%>
						</dl>
			<%
						}
					}
				}
		  }%>


			</ul>
		</div>
	</div>
</body>
</html>
