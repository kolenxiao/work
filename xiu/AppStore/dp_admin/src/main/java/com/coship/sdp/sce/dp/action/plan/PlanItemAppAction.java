package com.coship.sdp.sce.dp.action.plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.exception.BusinessException;
import com.coship.sdp.sce.dp.plan.entity.PlanItem;
import com.coship.sdp.sce.dp.plan.entity.PlanItemApp;
import com.coship.sdp.sce.dp.plan.service.PlanItemAppService;
import com.coship.sdp.sce.dp.plan.service.PlanItemService;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 精细化运营-方案类项管理Action
 * @author 908618
 *
 */
@Controller
public class PlanItemAppAction extends BaseAction {

	private static final long serialVersionUID = -3273879959368370159L;
	private String MODULE_NAME = this.getClass().getName();

    @Autowired
    private PlanItemAppService planItemAppService;
    @Autowired
    private PlanItemService planItemService;
    @Autowired
    private OpLoggerService opLoggerService;
    @Autowired
    private DpTypeService dpTypeService;
    
	// 分页对象
	private Page<?> page;
    
    private String planId;
    private String planItemId;
    private Integer itemType;
    private String planItemAppId;
    private String appName;
    private String typeId;
  
    
    /**
     * 查询分类关联的应用列表
     * @return
     */
    @SuppressWarnings("unchecked")
	public String listSelectedApp(){
        //构造查询条件
        if(StringUtils.isBlank(planItemId)){
			throw new IllegalArgumentException("传入的方案id不正确");
		}
        Map<String, Object> specialParams = new HashMap<String, Object>();
        specialParams.put("appName", appName);
        
        try {
        	//获取首页的版块列表
        	page = setUpPage(page, limit, start);
        	planItemAppService.searchSelectedApp((Page<PlanItemApp>) page, planItemId, specialParams);
		} catch (Exception e) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败";
			}
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			return ERROR;
		}

		// 返回
		return "selectedAppList";
    }
    
    
    /**
     * 查询分类未关联的应用列表
     * @return
     */
    @SuppressWarnings("unchecked")
	public String listUnSelectedApp(){
        //构造查询条件
        if(StringUtils.isBlank(planItemId)){
			throw new IllegalArgumentException("传入的参数不正确");
		}
        PlanItem planItem = planItemService.getPlanItemById(planItemId);
        itemType = planItem.getItemType();
        
        Map<String, Object> specialParams = new HashMap<String, Object>();
        specialParams.put("appName", appName);
        specialParams.put("typeId", typeId);
        
        try {
        	//获取首页的版块列表
        	page = setUpPage(page, limit, start);
        	planItemAppService.searchUnSelectedApp((Page<DpAppInfo>) page, planItemId, specialParams);
		} catch (Exception e) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败";
			}
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			return ERROR;
		}

		// 返回
        List<DpType> dpTypeList = this.searchDpTypeList();
        setResult("dpTypeList", dpTypeList);
		return "unSelectAppList";
    }


    /**
     * 保存分类关联的应用
     * @return
     */
    public String addPlanItemApp()
    {
       try{
			if(StringUtils.isBlank(planItemId)){
				throw new IllegalArgumentException("传入的分类id不正确");
			}
			
			String appIds = request.getParameter("appIds");
			if(StringUtils.isNotBlank(appIds)){
				String[] appIdArr = StringUtils.split(appIds, ",");
				planItemAppService.save(planItemId, appIdArr);
			}
			write("success");
       }catch(Exception e){
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
     * 删除分类关联的应用
     * @return
     */
    public String deletePlanItemApp()
    {
       try{
			String planItemAppIds = request.getParameter("planItemAppIds");
			if(StringUtils.isNotBlank(planItemAppIds)){
				String[] planItemAppIdArr = StringUtils.split(planItemAppIds, ",");
				planItemAppService.delete(planItemAppIdArr);
			}
			write("success");
       }catch(Exception e){
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
	 * 批量排序
	 * @return
	 */
	public String doSort() {
		try {
			String planItemAppIds = request.getParameter("planItemAppIds");
			String sortNums = request.getParameter("sortNums");
			
			if (StringUtils.isNotBlank(planItemAppIds) && StringUtils.isNotBlank(sortNums)) {
				String[] planItemAppIdArr = StringUtils.split(planItemAppIds, ",");
				String[] sortNumArr = StringUtils.split(sortNums, ",");
				
				planItemAppService.batchSort(planItemAppIdArr, sortNumArr);
				this.setResult("success", true);
			}else{
				this.setResult("success", false);
				this.setResult("msg", "参数错误");
			}
		} catch (Exception e) {
			this.setResult("success", false);
			Debug.logError(e, e.getMessage(), MODULE_NAME);
		}
		this.write(JSONObject.fromObject(this.getResult()).toString());
		return NONE;
	}
	
	/**
	 * 根据 appId 和 planIds 建立关联关系
	 *
	 */
	public void appAddPlan(){
		String strPlanId = request.getParameter("planId");
		String strAppId = request.getParameter("appId");
		String strAddItemIds = request.getParameter("addItemIds");
		String strDeleteItemIds = request.getParameter("deleteItemIds");
		
		List<String> lstAddItemIds = new ArrayList<String>();
		if(StringUtils.isNotBlank(strAddItemIds)){
			String[] objAddItemIds = strAddItemIds.split(","); 
			for (String strId : objAddItemIds) {
				lstAddItemIds.add(strId);
			}
		}
		
		List<String> lstDeleteItemIds = new ArrayList<String>();
		if(StringUtils.isNotBlank(strDeleteItemIds)){
			String[] objDeleteItemIds = strDeleteItemIds.split(","); 
			for (String strId : objDeleteItemIds) {
				lstDeleteItemIds.add(strId);
			}
		}
		
		if (lstAddItemIds.size() < 1 && lstDeleteItemIds.size() < 1 ) {
			Debug.logWarning("strAddItemIds 和 strDeleteItemIds 不能都为空", MODULE_NAME);
			write("error");
			return;
		}
		
		try{
			planItemAppService.appAddPlan(strAppId,strPlanId,lstAddItemIds,lstDeleteItemIds);
		}catch (Exception e){
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write("error");
			return;
		}
		write("success");
	}
	
	private List<DpType> searchDpTypeList() {
		List<DpType> list = null;
		try {
			list = dpTypeService.getGameAndAppTypeList();
		} catch (Exception e) {
			throw new BusinessException("", e);
		}
		if(null == list){
			list = new ArrayList<DpType>();
		}
		return list;
	}


	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getPlanItemId() {
		return planItemId;
	}

	public void setPlanItemId(String planItemId) {
		this.planItemId = planItemId;
	}


	public String getPlanItemAppId() {
		return planItemAppId;
	}


	public void setPlanItemAppId(String planItemAppId) {
		this.planItemAppId = planItemAppId;
	}

	public Page<?> getPage() {
		return page;
	}

	public void setPage(Page<?> page) {
		this.page = page;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}


	public Integer getItemType() {
		return itemType;
	}


	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

}
