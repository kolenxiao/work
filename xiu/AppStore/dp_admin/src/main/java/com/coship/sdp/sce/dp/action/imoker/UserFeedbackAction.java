package com.coship.sdp.sce.dp.action.imoker;


import org.springframework.beans.factory.annotation.Autowired;

import com.coship.sdp.sce.dp.action.ap.DpStaffAction;
import com.coship.sdp.sce.dp.ap.entity.DpStaff;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.imoker.entity.UserFeedback;
import com.coship.sdp.sce.dp.imoker.service.UserFeedbackService;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

public class UserFeedbackAction extends BaseAction
{
	
    /**
     * 查询开发者列表资源信息key
     */
    private static final String QUERY_MSG_ERROR = "sdp.sce.dp.admin.dpstaff.queryData.error";
    /**
     * 类名称.
     */
    private static final String MODULE = UserFeedbackAction.class
            .getName();
	/**
	 * 分页.
	 */
	private Page<UserFeedback> page;
	
    /**
     * 应用签名信息服务接口
     */
    @Autowired
    private UserFeedbackService userFeedbackService;
    
    private UserFeedback userFeedback;
    
    private String id;

    
	public String doList()
	{
		this.page = new Page<UserFeedback>();
		page.setPageSize(this.limit);
		page.setCurrentPage(this.start);

		try
		{
			// 获取分页数据
			page = userFeedbackService.findFeedback(page, null);
		} catch (Exception e)
		{
			Debug.logError(e, "doList()" + e.getMessage(), MODULE);

			// 国际化的异常信息
			exception_msg = getText(QUERY_MSG_ERROR);
			return ERROR;
		}
		return "doList";
	}
    
	public String doDetail()
	{
		try
		{
			// 获取分页数据
			userFeedback = userFeedbackService.findFeedbackDetail(id);
		} catch (Exception e)
		{
			Debug.logError(e, "doList()" + e.getMessage(), MODULE);

			// 国际化的异常信息
			exception_msg = getText(QUERY_MSG_ERROR);
			return ERROR;
		}
		return "doDetail";
	}
    
    
	public Page<UserFeedback> getPage()
	{
		return page;
	}

	public void setPage(Page<UserFeedback> page)
	{
		this.page = page;
	}

	public UserFeedbackService getUserFeedbackService()
	{
		return userFeedbackService;
	}

	public void setUserFeedbackService(UserFeedbackService userFeedbackService)
	{
		this.userFeedbackService = userFeedbackService;
	}

	public UserFeedback getUserFeedback()
	{
		return userFeedback;
	}

	public void setUserFeedback(UserFeedback userFeedback)
	{
		this.userFeedback = userFeedback;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
}
