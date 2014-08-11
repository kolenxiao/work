package com.coship.sdp.sce.dp.action.plan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.PlanConstants;
import com.coship.sdp.sce.dp.plan.entity.Item;
import com.coship.sdp.sce.dp.plan.entity.PlanItem;
import com.coship.sdp.sce.dp.plan.service.ItemService;
import com.coship.sdp.sce.dp.plan.service.PlanItemService;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanel;
import com.coship.sdp.sce.dp.recommend.service.AppRecommendPanelService;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.subject.service.AppSubjectTypeService;
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
public class PlanItemAction extends BaseAction {
	
	private static final long serialVersionUID = -7105482781365941953L;
	private static final Logger logger = LoggerFactory.getLogger(PlanItemAction.class);
	private String MODULE_NAME = this.getClass().getName();

    @Autowired
    private PlanItemService planItemService;
    @Autowired
    private ItemService itemService;   
    @Autowired
    private AppRecommendPanelService appRecommendPanelService;
    @Autowired
    private AppSubjectTypeService appSubjectTypeService;
    @Autowired
    private DpTypeService dpTypeService;
    
	// 分页对象
    private Page<PlanItem> planItemPage;
	
	private Page<AppRecommendPanel> panelPage;
	private Page<AppSubjectType> subjectPage;
	private Page<DpType> dpTypePage;
	private Page<?> page;
    
    //方案对象
    private PlanItem planItem = new PlanItem();
    
    private String planId;
    private String planItemId;
    private String operFlag;
    private String panelName;
    private String subjectName;
    private String typeName;
    private String name;
    
    /**
     * 查询方案已关联的类项列表
     * @return
     */
    public String listSelected(){
        try {
            //构造查询条件
            if(StringUtils.isBlank(planId)){
    			throw new IllegalArgumentException("传入的方案id不正确");
    		}
            
        	//获取首页的版块列表
        	panelPage = setUpPage(panelPage, Integer.MAX_VALUE, start);
            appRecommendPanelService.queryAppRecommendPanelList(panelPage, "");
            
        	//获取专题列表
            subjectPage = setUpPage(subjectPage, Integer.MAX_VALUE, start);
            appSubjectTypeService.searchAppSubjectType(subjectPage, new AppSubjectType());
            
        	//获取分类列表
			List<DpType> dpTypeList = dpTypeService.getGameAndAppTypeList();
			//获取ItemList
			Item queryItem = new Item();
			queryItem.setStatus(-2);
			Page<Item> itemPage = itemService.list(0, Integer.MAX_VALUE, queryItem);
			List<Item> itemList = itemPage.getResultList();
			
			
            //获取方案关联的首页类项
            List<PlanItem> planItemPanelList = planItemService.getPlanItemListByPlanIdAndType(planId, PlanConstants.ITEM_TYPE_PANEL);
            for (PlanItem planItem : planItemPanelList) {
				for (AppRecommendPanel appRecommendPanel : panelPage.getResultList()) {
					if(planItem.getItemId().equals(appRecommendPanel.getId())){
						planItem.setAppRecommendPanel(appRecommendPanel);
						break;
					}
				}
			}
            setResult("planItemPanelList", planItemPanelList);
            
            //获取方案关联的专题类项
            List<PlanItem> planItemSubjectList = planItemService.getPlanItemListByPlanIdAndType(planId, PlanConstants.ITEM_TYPE_SUBJECT);
            for (PlanItem planItem : planItemSubjectList) {
				for (AppSubjectType appSubjectType : subjectPage.getResultList()) {
					if(planItem.getItemId().equals(appSubjectType.getId())){
						planItem.setAppSubjectType(appSubjectType);
						break;
					}
				}
			}
            setResult("planItemSubjectList", planItemSubjectList);
            
            //获取方案关联的分类类项(包括固定分类和自定义分类)
            List<PlanItem> planItemClassList = planItemService.getSelectedClass(planId);
            for (PlanItem planItem : planItemClassList) {
            	if(PlanConstants.ITEM_TYPE_DPTYPE == planItem.getItemType()){
            		for (DpType dpType : dpTypeList) {
    					if(planItem.getItemId().equals(dpType.getId())){
    						planItem.setDpType(dpType);
    						break;
    					}
    				}
            	}else if(PlanConstants.ITEM_TYPE_SELFTYPE == planItem.getItemType()){
            		for (Item item : itemList) {
						if(planItem.getItemId().equals(item.getId())){
							planItem.setItem(item);
							break;
						}
					}
            	}
			}
            setResult("planItemClassList", planItemClassList);
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
		return "list";
    }
    
    /**
     * 新增方案关联的类项
     * @return
     */
    public String addPlanItem() {
    	logger.info("PlanItemAction.addPlanItem start, planId={}, operFlag={}", planId, operFlag);
       try{
			if(StringUtils.isBlank(planId) || StringUtils.isBlank(operFlag)){
				throw new IllegalArgumentException("传入的参数不正确");
			}
			
			Integer itemType = null;
			if ("addPanel".equals(operFlag)) {
				itemType = PlanConstants.ITEM_TYPE_PANEL;
			} else if ("addSubject".equals(operFlag)) {
				itemType = PlanConstants.ITEM_TYPE_SUBJECT;
			} else if ("addDpType".equals(operFlag)) {
				itemType = PlanConstants.ITEM_TYPE_DPTYPE;
			} else if ("addSelfType".equals(operFlag)) {
				itemType = PlanConstants.ITEM_TYPE_SELFTYPE;
			} else {
				throw new IllegalArgumentException("传入的参数不正确");
			}
			
			String itemIds = request.getParameter("itemIds");
			if(StringUtils.isNotBlank(itemIds)){
				String[] itemIdArr = StringUtils.split(itemIds, ",");
				planItemService.savePlanItem(planId, itemIdArr, itemType);
			}
			write("success");
			logger.info("PlanItemAction.addPlanItem success, planId={}, operFlag={}", planId, operFlag);
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
     * 删除方案关联的类项
     * @return
     */
	public String deletePlanItem() {
		try {
			String planItemIds = request.getParameter("planItemIds");
			logger.info("PlanItemAction.deletePlanItem start, planItemIds={}", planItemIds);
			if (StringUtils.isBlank(planItemIds)) {
				throw new IllegalArgumentException("传入的参数不正确");
			}

			String[] planItemIdArr = StringUtils.split(planItemIds, ",");
			planItemService.deletePlanItem(planItemIdArr);
			write("success");
			logger.info("PlanItemAction.deletePlanItem success, planItemIds={}", planItemIds);
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
     * 查询方案未关联的版块列表
     * @return
     */
    public String listUnSelectedPanel(){
        //构造查询条件
        if(StringUtils.isBlank(planId)){
			throw new IllegalArgumentException("传入的方案id不正确");
		}        
        Map<String, Object> specialParams = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(panelName)){
        	specialParams.put("panelName", panelName);
		}
        
        try {
        	//获取首页的版块列表
        	page = setUpPage(page, limit, start);
        	planItemService.queryUnSelectedPlanItem(page, planId, PlanConstants.ITEM_TYPE_PANEL, specialParams);
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
		return "unSelectedPanelList";
    }
    
    /**
     * 查询方案未关联的专题列表
     * @return
     */
    public String listUnSelectedSubject(){
        //构造查询条件
        if(StringUtils.isBlank(planId)){
			throw new IllegalArgumentException("传入的方案id不正确");
		}
        Map<String, Object> specialParams = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(subjectName)){
        	specialParams.put("subjectName", subjectName);
		}
        
        try {
        	//获取首页的版块列表
        	page = setUpPage(page, limit, start);
        	planItemService.queryUnSelectedPlanItem(page, planId, PlanConstants.ITEM_TYPE_SUBJECT, specialParams);
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
		return "unSelectedSubjectList";
    }
    
    
    /**
     * 查询方案未关联的固定分类列表
     * @return
     */
    public String listUnSelectedDpType(){
        //构造查询条件
        if(StringUtils.isBlank(planId)){
			throw new IllegalArgumentException("传入的参数不正确");
		}
        Map<String, Object> specialParams = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(typeName)){
        	specialParams.put("typeName", typeName);
		}
        
        try {
        	//获取首页的版块列表
        	page = setUpPage(page, limit, start);
        	planItemService.queryUnSelectedPlanItem(page, planId, PlanConstants.ITEM_TYPE_DPTYPE, specialParams);
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
		return "unSelectedDpTypeList";
    }
    
    /**
     * 查询方案未关联的自定义分类列表
     * @return
     */
    public String listUnSelectedSelf(){
        //构造查询条件
        if(StringUtils.isBlank(planId)){
			throw new IllegalArgumentException("传入的方案id不正确");
		}
        Map<String, Object> specialParams = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(name)){
        	specialParams.put("name", name);
		}
        
        try {
        	//获取首页的版块列表
        	page = setUpPage(page, limit, start);
        	planItemService.queryUnSelectedPlanItem(page, planId, PlanConstants.ITEM_TYPE_SELFTYPE, specialParams);
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
		return "unSelectedSelfTypeList";
    }
    

	
    /**
     * 分类排序
     * @return
     */
	public String doSort() {
		this.setResult("success", false);
		String planItemId = request.getParameter("planItemId");
		String sortNum = request.getParameter("sortNum");
		
		try {
			if(StringUtils.isNotBlank(planItemId) && StringUtils.isNotBlank(sortNum)){
				PlanItem planItem = planItemService.getPlanItemById(planItemId);
				if(null != planItem){
					planItem.setSortNum(Integer.valueOf(sortNum).intValue());
					planItemService.modifyPlanItem(planItem);

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

	public PlanItem getPlanItem() {
		return planItem;
	}

	public void setPlanItem(PlanItem planItem) {
		this.planItem = planItem;
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

	public String getOperFlag() {
		return operFlag;
	}

	public void setOperFlag(String operFlag) {
		this.operFlag = operFlag;
	}

	public Page<AppRecommendPanel> getPanelPage() {
		return panelPage;
	}

	public void setPanelPage(Page<AppRecommendPanel> panelPage) {
		this.panelPage = panelPage;
	}

	public Page<AppSubjectType> getSubjectPage() {
		return subjectPage;
	}

	public void setSubjectPage(Page<AppSubjectType> subjectPage) {
		this.subjectPage = subjectPage;
	}

	public Page<DpType> getDpTypePage() {
		return dpTypePage;
	}

	public void setDpTypePage(Page<DpType> dpTypePage) {
		this.dpTypePage = dpTypePage;
	}

	public Page<PlanItem> getPlanItemPage() {
		return planItemPage;
	}

	public void setPlanItemPage(Page<PlanItem> planItemPage) {
		this.planItemPage = planItemPage;
	}

	public Page<?> getPage() {
		return page;
	}

	public void setPage(Page<?> page) {
		this.page = page;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
