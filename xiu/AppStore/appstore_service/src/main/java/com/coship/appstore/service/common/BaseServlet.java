package com.coship.appstore.service.common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.coship.appstore.service.AppStoreActionV4;
import com.coship.sdp.utils.Debug;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseServlet() {
        super();
    }
    
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
	   super.init(servletConfig);
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String reqUri = request.getRequestURI();

		Map<String, String[]> map = request.getParameterMap();

        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession()
                .getServletContext());

        AppStoreActionV4 action = (AppStoreActionV4) ctx.getBean("appStoreActionV4");
        action.setRequest(request);
        action.setResponse(response);

		try
		{
			BeanUtils.getNewInstance().setupBeforeInitDispatcher(action, map) ;
		} catch (Exception e)
		{
			 Debug.logVerbose(e);
		}
		
		try
		{
			int index1 = reqUri.indexOf(".action");
			int index = 0;
			
			if (index1 == -1)
			{
				index = reqUri.length();
			} else
			{
				index = index1;
			}
			
			String method = reqUri.substring(reqUri.indexOf("!") + 1, index);
			BeanUtils.getNewInstance().invokeMethod(action, method, new Object[]{});
		}catch(Exception e){
			 Debug.logVerbose(e);
			response.sendError(404, "could not found the path:"+reqUri);
		}
	}
}
