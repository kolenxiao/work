package com.coship.depgm.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.coship.depgm.common.DepgmConfig;
import com.coship.depgm.service.JobService;
import com.coship.mse.core.MseContext;

/**
 * 
 * @author 907645
 *
 */
public class DepgmFilter implements Filter {	
	protected static Logger logger = Logger.getLogger(DepgmFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String uri = httpRequest.getRequestURI();

		HttpSession session = httpRequest.getSession();
		String name = (String) session.getAttribute("userName");
		if((null==name||name.equals(""))&&!uri.contains("login")&&!uri.contains("/images/")&&!uri.contains("/services/")){
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.jsp");
			return;
		}
		if(uri.indexOf("receiveMsg")>-1){
			MseContext.getMessageServlet().service(request, response);
			return;
		}
		if (uri.endsWith("/")) {
			httpResponse.sendRedirect("index.jsp");
			return;
		}
		try{			
			chain.doFilter(request, response);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void destroy() {}
	
	private static ApplicationContext applicationContext;
	
	@Override
	public void init(FilterConfig config) throws ServletException {	
		String location = System.getProperty("user.dir") + File.separator + config.getServletContext().getInitParameter("depgmConfig");
		DepgmConfig.initConfig(location);
		
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		try {
			JobService.startBtvJob();
			JobService.startTvSouJob();
		} catch (Exception e) {
			logger.error("任务启动异常", e);
		}
	}
	
	public static Object getBean(String name){
		return applicationContext.getBean(name);
	}
}
