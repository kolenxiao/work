package com.coship.appstore.service.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coship.appstore.service.AppStoreCacheFlushTask;
import com.coship.sdp.utils.Debug;

public class GlobalAppListServlet extends HttpServlet {


	/**
	 *
	 */
	private static final long serialVersionUID = -1181552332121062464L;

	@Override
	public void init() throws ServletException {
		super.init();
		Debug.logVerbose("------------GlobalAppListServlet begin-------------");
		new AppStoreCacheFlushTask().start();
		Debug.logVerbose("------------GlobalAppListServlet end-------------");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
