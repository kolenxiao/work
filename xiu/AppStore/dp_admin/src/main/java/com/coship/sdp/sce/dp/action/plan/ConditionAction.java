/*
 * 文件名称：ConditionAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-03-27
 *
 */
package com.coship.sdp.sce.dp.action.plan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.plan.entity.Condition;
import com.coship.sdp.sce.dp.plan.service.ConditionService;
import com.coship.sdp.sce.dp.plan.service.PlanConditionService;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/*
 * 处理精细化管理_查询条件 操作请求的action类
 * @author 909194
 * @version [版本号, 2014-03-27]
 * @since [产品/模块版本]
 */
@Controller
public class ConditionAction extends BaseAction{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -5962885816021571684L;

	/**
	 * 模块的名称.
	 */
	private static final String MODULE_NAME = ConditionAction.class.getName();

	/**
	 * 分页对象
	 */
	private Page<Condition> page;
	
	/**
	 * 专用于查询
	 */
	private Condition queryCondition;
    
    @Autowired
    private ConditionService conditionService;
    
    @Autowired
    private PlanConditionService planConditionService;
    

	/**
	 * 查询全部条件列表.
	 *
	 * @return 返回列表页面配置字符串
	 */
	public String list(){
		if( null == queryCondition){
			queryCondition = new Condition();
			//查询所有状态的数据
			queryCondition.setStatus(-2);
		}
		try{
			page = conditionService.list(this.start,this.limit,queryCondition);
		}catch (Exception e){
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			exception_msg = "方案条件查询出错";
			return ERROR;
		}
		return "list";
	}
	
	/**
	 * 根据 ids 删除数据
	 *
	 */
	public void delete(){
		String strIds = request.getParameter("ids");
		if (!StringUtils.isNotBlank(strIds)) {
			Debug.logWarning("ids is null", MODULE_NAME);
			write("error");
			return;
		}

		List<String> lstIds = new ArrayList<String>();
		String[] objIds = strIds.split(","); 
		for (String strId : objIds) {
			lstIds.add(strId);
		}
		
		try{
			conditionService.delete(lstIds);
		}catch (Exception e){
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write("error");
			return;
		}
		write("success");
	}
	
	/**
	 * 根据 ids 启用数据
	 *
	 */
	public void enable(){
		String strIds = request.getParameter("ids");
		if (!StringUtils.isNotBlank(strIds)) {
			Debug.logWarning("ids is null", MODULE_NAME);
			write("error");
			return;
		}

		List<String> lstIds = new ArrayList<String>();
		String[] objIds = strIds.split(","); 
		for (String strId : objIds) {
			lstIds.add(strId);
		}
		
		try{
			conditionService.enable(lstIds);
		}catch (Exception e){
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write("error");
			return;
		}
		write("success");
	}
	
	/**
	 * 根据 ids 禁用数据
	 *
	 */
	public void disable(){
		String strIds = request.getParameter("ids");
		if (!StringUtils.isNotBlank(strIds)) {
			Debug.logWarning("ids is null", MODULE_NAME);
			write("error");
			return;
		}

		List<String> lstIds = new ArrayList<String>();
		String[] objIds = strIds.split(","); 
		for (String strId : objIds) {
			lstIds.add(strId);
		}
		
		try{
			conditionService.disable(lstIds);
		}catch (Exception e){
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write("error");
			return;
		}
		write("success");
	}
	
	
    /**
    *
    * 跳转到新增页面
    */
   public String toAdd(){
       try{
    	   this.queryCondition = new Condition();
       }catch (Exception e){
           Debug.logError(e, "toAdd()" + e.getMessage(), MODULE_NAME);
           exception_msg = this.getText("sdp.sce.dp.admin.common.jump.error");
           return ERROR;
       }
       return "edit";
   }
   
   /**
   *
   * 跳转到修改页面
   */
  public String toEdit(){
	  String strId = request.getParameter("id");
      try{
    	  queryCondition = conditionService.findCondition(strId);
      }catch (Exception e){
          Debug.logError(e, "toEdit()" + e.getMessage(), MODULE_NAME);
          exception_msg = this.getText("sdp.sce.dp.admin.common.jump.error");
          return ERROR;
      }
      return "edit";
  }
  
	  /**
	  *
	  * 新增条件
	  */
	 public String save(){
	     try{
	         conditionService.save(queryCondition);
	     }catch (Exception e){
	         Debug.logError(e, "save()" + e.getMessage(), MODULE_NAME);
	         exception_msg = "条件新增保存出错";
	         return ERROR;
	     }
	     return "Success";
	 }
 
	 /**
	 *
	 * 修改条件
	 */
	public String update(){
	    try{
	        conditionService.update(queryCondition);
	    }catch (Exception e){
	        Debug.logError(e, "update()" + e.getMessage(), MODULE_NAME);
	        exception_msg = "条件修改保存出错";
	        return ERROR;
	    }
	    return "Success";
	}
	
	
	/**
	 * 查询所有启用数据，根据planId判断是否包含此条件
	 *
	 * @return 返回列表页面配置字符串
	 */
	public String listForPlan(){
		String strPlanId = request.getParameter("planId");
		
		if( null == queryCondition){
			queryCondition = new Condition();
			queryCondition.setStatus(-2);
		}
		try{
			page = planConditionService.listForPlan(this.start,this.limit,strPlanId,queryCondition);
		}catch (Exception e){
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			exception_msg = "根据方案id查询条件列表出错";
			return ERROR;
		}
		return "listForPlan";
	}
	
	/**
	 * 根据 planId 和 conditionIds 建立关联关系
	 *
	 */
	public void addPlanCondition(){
		String strConditionIds = request.getParameter("conditionIds");
		String strPlanId = request.getParameter("planId");
		if (!StringUtils.isNotBlank(strConditionIds) || !StringUtils.isNotBlank(strPlanId)) {
			Debug.logWarning("planId 和 conditionIds 不能为空", MODULE_NAME);
			write("error");
			return;
		}

		List<String> lstConditionIds = new ArrayList<String>();
		String[] objConditionIds = strConditionIds.split(","); 
		for (String strId : objConditionIds) {
			lstConditionIds.add(strId);
		}
		
		try{
			planConditionService.addPlanCondition(strPlanId,lstConditionIds);
		}catch (Exception e){
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write("error");
			return;
		}
		write("success");
	}
	
	/**
	 * 根据 planId 和 conditionIds 删除关联关系
	 *
	 */
	public void deletePlanCondition(){
		String strConditionIds = request.getParameter("conditionIds");
		String strPlanId = request.getParameter("planId");
		if (!StringUtils.isNotBlank(strConditionIds) || !StringUtils.isNotBlank(strPlanId)) {
			Debug.logWarning("planId 和 conditionIds 不能为空", MODULE_NAME);
			write("error");
			return;
		}

		List<String> lstConditionIds = new ArrayList<String>();
		String[] objConditionIds = strConditionIds.split(","); 
		for (String strId : objConditionIds) {
			lstConditionIds.add(strId);
		}
		
		try{
			planConditionService.deletePlanCondition(strPlanId,lstConditionIds);
		}catch (Exception e){
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write("error");
			return;
		}
		write("success");
	}
	

	public Page<Condition> getPage() {
		return page;
	}


	public void setPage(Page<Condition> page) {
		this.page = page;
	}


	public Condition getQueryCondition() {
		return queryCondition;
	}


	public void setQueryCondition(Condition queryCondition) {
		this.queryCondition = queryCondition;
	}
    
}
