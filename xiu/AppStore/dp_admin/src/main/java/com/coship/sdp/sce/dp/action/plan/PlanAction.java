package com.coship.sdp.sce.dp.action.plan;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.sce.dp.plan.service.PlanService;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 精细化运营-方案管理Action
 * @author 908618
 *
 */
@Controller
public class PlanAction extends BaseAction implements ModelDriven<Plan> {

	private static final long serialVersionUID = -8577224037207479782L;
	private static final Logger logger = LoggerFactory.getLogger(PlanAction.class);
	private String MODULE_NAME = this.getClass().getName();

    @Autowired
    private PlanService planService;
    
    //方案对象
    private Plan plan;
    private String planId;
    
	// 分页对象
	private Page<Plan> page;
	
	//应用包名
	private String appPkgName;
	
	/**
	 * 跳转到创建方案页面
	 * @return
	 */
	public String goCreatePlan() {
		//初始化时间
		Date startTime = new Date();
		Date endTime = DateUtils.addYears(startTime, 1);
		plan = new Plan();
		plan.setStartTime(startTime);
		plan.setEndTime(endTime);
		
		return "create";
	}
	
	/**
	 * 创建方案
	 * @return
	 */
	public String createPlan() {
		logger.info("PlanAction.createPlan start, planName={}",plan.getName());
		try {
			planService.createPlan(plan);
		} catch (Exception e) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败！";
			}
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			return ERROR;
		}

		// 返回
		logger.info("PlanAction.createPlan success, planName={}",plan.getName());
		return "successToList";
	}
	
	/**
	 * 修改方案
	 * @return
	 */
	public String modifyPlan(){
		try {
			planService.modifyPlan(plan);
		} catch (Exception e) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败！";
			}
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			return ERROR;
		}
		return "successToList";
	}
	
	
	/**
	 * 启用方案
	 */
	public String enabledPlan(){
		String operFlag = "enabled";
		return this.modifyPlanStatus(operFlag);
	}
	
	/**
	 * 禁用用方案
	 */
	public String disablePlan(){
		String operFlag = "disable";
		return this.modifyPlanStatus(operFlag);
	}

	/**
	 * 设为默认方案
	 * @return
	 */
	public String defaultPlan(){
		try {
			// 验证参数
			if(StringUtils.isBlank(planId)){
				throw new IllegalArgumentException("传入的参数id不正确");
			}
			
			//设为默认方案
			planService.modifyPlanDetault(planId);
			write("success");
		} catch (Exception e) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败！";
			}
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write(exception_msg);
		}
		return NONE;
	}
	
	/**
	 * 删除方案
	 * @return
	 */
	public String deletePlan(){
		try {
			// 验证参数
			String planIds = request.getParameter("planIds");
			logger.info("PlanAction.deletePlan start, planIds={}", planIds);
			if(StringUtils.isBlank(planIds)){
				throw new IllegalArgumentException("传入的参数id不正确");
			}
			
			//修改方案状态
			String[] planIdArr = StringUtils.split(planIds, ",");
			planService.deletePlan(planIdArr);
			write("success");
			logger.info("PlanAction.deletePlan success, planIds={}", planIds);
		} catch (Exception e) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败！";
			}
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write(exception_msg);
		}
		return NONE;
	}
	
	/**
	 * 查询方案列表
	 * @return
	 */
	public String list(){
        page = new Page<Plan>();
        page.setPageSize(limit);
        page.setCurrentPage(start);
        
        if(null == plan){
        	plan = new Plan();
        	plan.setStatus(-2);
        }

        //构造查询条件
        Map<String, Object> specialParams = new HashMap<String, Object>();
        //查询
		try{
			page = planService.searchPlanListByPage(page, plan, specialParams);
		}catch (Exception e){
			e.printStackTrace();
		}
		// 返回
		return "list";
	}
	
	public String goList(){
		return list();
	}
	
	/**
	 * 显示方案的基本信息
	 * @return
	 */
	public String doDisplay(){
		// 验证参数
		String flag = request.getParameter("flag");
		if(StringUtils.isBlank(planId)){
			throw new IllegalArgumentException("传入的参数id不正确");
		}

		try {
			plan = planService.getPlanById(planId);
		} catch (Exception e) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败！";
			}
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			return ERROR;
		}

		// 返回
		String returnResult = "detail";
		if("modify".equals(flag)){
			returnResult = "modify";
		}
		return returnResult;
	}
	
    /**
     * 设定方案优先级
     * @return
     */
	public String doSort() {
		this.setResult("success", false);
		String sortNum = request.getParameter("sortNum");
		
		try {
			if(StringUtils.isNotBlank(planId) && StringUtils.isNotBlank(sortNum)){
				Plan plan = planService.getPlanById(planId);
				if(null != plan){
					plan.setSortNum(Integer.valueOf(sortNum).intValue());
					planService.modifyPlan(plan);
					this.setResult("success", true);
				}else{
					this.setResult("msg", "找不到分类信息");
				}
			}else{
				this.setResult("msg", "参数错误");
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE_NAME);
		}
		
		this.write(JSONObject.fromObject(this.getResult()).toString());
		return null;
	}
	
	
	/**
	 * 应用关联方案页面，展示方案列表
	 * @return
	 */
	public String listForApp(){
		String strAppId = request.getParameter("appId");
		if (!StringUtils.isNotBlank(strAppId)) {
			Debug.logWarning("appId is null", Plan.class.getName());
			return ERROR;
		}
		if( null == plan){
			plan = new Plan();
			plan.setStatus(-2);
		}
		try{
			page = planService.listForApp(this.start,this.limit,strAppId,plan);
		}catch (Exception e){
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败！";
			}
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			return ERROR;
		}
		return "listForApp";
	}
	
	/**
	 * 根据应用包名获取关联的有效方案列表
	 * @return
	 */
	public Set<Plan> getPlanSetByApp(){
		if(StringUtils.isBlank(appPkgName)){
			throw new IllegalArgumentException("应用包名不能为空");
		}
		return planService.getPlanSetByApp(appPkgName);
	}
	

	@Override
	public Plan getModel() {
		return plan;
	}
	
	//修改方案状态
	private String modifyPlanStatus(String operFlag){
		try {
			// 验证参数
			String planIds = request.getParameter("planIds");
			logger.info("PlanAction.modifyPlanStatus start, planIds={}, operFlag={}",
					planIds, operFlag);
			if(StringUtils.isBlank(planIds)){
				throw new IllegalArgumentException("传入的参数id不正确");
			}
			
			//修改方案状态
			String[] planIdArr = StringUtils.split(planIds, ",");
			planService.modifyPlanStatus(planIdArr, operFlag);
			write("success");
			logger.info("PlanAction.modifyPlanStatus success, planIds={}, operFlag={}",
					planIds, operFlag);
		} catch (Exception e) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败！";
			}
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write(exception_msg);
		}
		return NONE;
	}
	
	/**
	 * 根据 planId 复制方案
	 *
	 */
	public void copy(){
		String strId = request.getParameter("planId");
		if (!StringUtils.isNotBlank(strId)) {
			Debug.logWarning("id is null", MODULE_NAME);
			write( "方案id不能为空");
			return;
		}

		String strReturn = "";
		try{
			strReturn = planService.copy(strId);
		}catch (Exception e){
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败！";
			}
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write("复制方案后台异常");
			return;
		}
		write(strReturn);
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Page<Plan> getPage() {
		return page;
	}

	public void setPage(Page<Plan> page) {
		this.page = page;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getAppPkgName() {
		return appPkgName;
	}

	public void setAppPkgName(String appPkgName) {
		this.appPkgName = appPkgName;
	}
	

}
