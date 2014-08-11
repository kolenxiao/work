/*
 * 文件名称：dpTypeAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-8-31
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.action.type;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.exception.ServiceException;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.DefaultTypeCodeConstants;
import com.coship.sdp.sce.dp.common.QueryAppInfoThread;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommend;
import com.coship.sdp.sce.dp.recommend.entity.AppTypeRecommend;
import com.coship.sdp.sce.dp.recommend.service.AppRecommendService;
import com.coship.sdp.sce.dp.recommend.service.AppTypeRecommendService;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.sce.dp.utils.FileUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 分类管理控制器
 * @author FuJian/906126
 * @version [版本号, 2011-8-31]
 * @since [产品/模块版本]
 */
@Controller
public class DpTypeAction extends BaseAction
{
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * className.
     */
    private static final String MODULE = DpTypeAction.class.getName();

    /**
     * 操作日志.
     */
    private OpLoggerService opLoggerService;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 实体类.
     */
    private DpType dpType;

    /**
     * 查询数据封装对象
     */
    private DpType dpTypeQueryInfo;

    /**
     * 分页对象.
     */
    private Page<DpType> page;

    /**
     * 分类类别列表
     */
    private List<DpType> dpTypeList;

    /**
     * 分类对象.
     */
    private DpTypeService dpTypeService;

    /**
     * id集合.
     */
    private String ids;

    /**
     * 页面跳转标识.
     */
    private String forward;

    /**
     * 上传获取焦点图片文件
     */
    private File uploadFocus;

    /**
     * 上传获取焦点图片文件名.
     */
    private String uploadFocusFileName;

    /**
     * 上传获取焦点图片类型.
     */
    private String uploadFocusContentType;

    /**
     * 上传失去焦点图片文件
     */
    private File uploadLoseFocus;

    /**
     * 上传失去焦点图片文件名.
     */
    private String uploadLoseFocusFileName;

    /**
     * 上传失去焦点图片类型.
     */
    private String uploadLoseFocusContentType;
    /**
     * 应用服务层对象.
     */
    @Autowired
    private AppRecommendService appRecommendService;
    
    @Autowired
    private AppTypeRecommendService appTypeRecommendService;
    /**
     * <p>位置：开发者后台-》应用管理-》应用推荐</p>
     * 查询应用下的推荐分类，不包含根分类
     * @return
     */
	public String doSearchRecommendList() {
		Debug.logVerbose("DpTypeAction.doSearchRecommendList() start...");
		try {
			this.page = setUpPage(getPage(), getLimit(), getStart()); // 设置分页对象
 			dpTypeService.searchRecomentDpTypeList(page, getDpTypeQueryInfo(), true); // 查询推荐分类，将结果保存到page对象中
			setAppTotal(page);//统计分类下的应用数
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(),MODULE);
			exception_msg = getText("sdp.sce.dp.admin.common.query.error");
			return ERROR;
		}
		Debug.logVerbose("DpTypeAction.doSearchRecommendList() end...");
		return "doSearchRecommandList";
	}
	
	
	/**
	 * <p>位置：开发者后台-》应用管理-》应用推荐-》新增</p>
	 * 查询可添加的推荐分类，不包含根分类
	 * @return
	 */
	public String doSearchUncludeTypeRecommend() {
		Debug.logVerbose("DpTypeAction.doSearchUncludeTypeRecommend() start...");
		try {
			this.page = setUpPage(getPage(), getLimit(), getStart());//设置分页对象	
			dpTypeService.searchRecomentDpTypeList(page,getDpTypeQueryInfo(),false);//查询可新增分类，将结果保存到page对象中
	
			if(null != dpTypeQueryInfo)
			{
				dpTypeQueryInfo.setTypeCode(dpTypeQueryInfo.getParentTypeCode());//保存上一次所选分类
			}
			
			setDpTypeName(page.getResultList());//设置分类的名称
			setDpTypeList(dpTypeService.findVisibleTypeByParentCodeArr(DefaultTypeCodeConstants.APP_TYPE_CODE,DefaultTypeCodeConstants.GAME_TYPE_CODE));
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(),MODULE);
			exception_msg = getText("sdp.sce.dp.admin.common.query.error");
			return ERROR;
		}
		
		Debug.logVerbose("DpTypeAction.doSearchUncludeTypeRecommend() end...");
		return "doSearchUncludeTypeRecommend";
	}
	
	/**
	 *  <h1>1、位置：开发者后台-》应用管理-》应用推荐-》新增</h1>
	 *  
	 *   添加应用推荐分类，不包含根分类且不存在重复分类
	 *  
	 *  <h2>2、位置：开发者后台-》应用管理-》应用推荐-》取消推荐</h2>
	 *  
	 *   取消应用推荐分类，并删除分类下关联应用
	 * 
	 * @return
	 */
	public String doCommend(){
        Debug.logVerbose("AppRecommendAction.doCommend() start...");
        User userObj = (User) request.getSession().getAttribute("user");//操作用户
        
        try{
            String operaterLog = "";
            String commendFlag = request.getParameter("commendFlag");//标志  commendFlag=1：推荐；commendFlag=0：取消推荐.
            String[] idArr = new String[]{};
            
			if (StringUtils.isNotEmpty(ids)) {
				idArr = ids.trim().split(",");//获取ID集合
			}
			
			if (StringUtils.equals(commendFlag, "1")) {
				operaterLog = "sdp.sce.dp.admin.log.recommended.operate";
				addTypeToRecommend(userObj,idArr);//新增推荐
			}else{
				operaterLog = "sdp.sce.dp.admin.log.cancel.recommended";
				cancleTypeFromRecommend(idArr);//取消推荐
			}
            
			write(JSONObject.fromObject(getResult()).toString());//输出操作结果
			new QueryAppInfoThread().start();//启动线程,通知服务接口
            
			/*----------------------------记录日志-----------------------------*/
            List<String> logParamList = initLogParames(userObj,
                    "sdp.sce.dp.admin.ap.appinfo", operaterLog,
                    "sdp.sce.dp.admin.log.operate.result.success");
            
            logParamList.add(ids);
            
            opLoggerService.info(getText("sdp.sce.dp.admin.ap.name"),
                    getText("sdp.sce.dp.admin.ap.commend", logParamList),
                    getText(operaterLog));
            
        }catch(Exception e){
            Debug.logError(e, e.getMessage(),MODULE);
            exception_msg = getText("sdp.sce.dp.admin.ap.commend.error");
            return ERROR;
        }
        
		return null;
	}
	
	
    /**
     * 分页列表.
     *
     * @return String
     */
    public String doList()
    {
        if (Debug.verboseOn())
        {
            Debug.logVerbose("doList start...", MODULE);
        }
        try
        {
            if (dpTypeQueryInfo == null)
            {
                dpTypeQueryInfo = new DpType();
            }
            this.page = new Page<DpType>();
            page.setPageSize(this.limit);
            page.setCurrentPage(this.start);

            this.page = this.dpTypeService.serchDpType(page, dpTypeQueryInfo);
            dpTypeQueryInfo.setTypeCode(dpTypeQueryInfo.getParentTypeCode());
            dpTypeList = dpTypeService
                    .findByParentTypeCode(DefaultTypeCodeConstants.ROOT_TYPE_CODE);
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = this.getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        Debug.logVerbose("doList end...", MODULE);
        return "list";
    }

    public String doChangeVisibleStateType()
    {
    	if (Debug.verboseOn())
        {
            Debug.logVerbose("DpTypeAction.doRemoveAppToSubjectType() start...", MODULE);
        }
    	try {
			DpType type = dpTypeService.findType(dpType.getId());

			type.setVisibleFlag(dpType.getVisibleFlag());

			dpTypeService.updateType(type);

			setResult("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			setResult("exception", "更改分类显示状态失败.");
            write(JSONObject.fromObject(getResult()).toString());
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = "更改分类显示状态失败.";
		}

		write(JSONObject.fromObject(getResult()).toString());
        Debug.logVerbose("DpTypeAction.doRemoveAppToSubjectType() end...");
		return null;

    }

    /**
     * 进入编辑或者增加页面.
     *
     * @return
     */
    public String doDisplay()
    {
        if (Debug.verboseOn())
        {
            Debug.logVerbose("doDisplay start...", MODULE);
        }
        String path = "addOrEdit";
        try
        {
            if (dpType != null)
            {
                this.dpType = this.dpTypeService.findType(this.dpType.getId());
                // 跳转到详情界面
                if (Constants.TO_DETAIL_FORWARD.equals(forward))
                {
                    path = "doFind";
                }
            }
            dpTypeList = dpTypeService
                    .findByParentTypeCode(DefaultTypeCodeConstants.ROOT_TYPE_CODE);

        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = this.getText("sdp.sce.dp.admin.common.jump.error");
            return ERROR;
        }
        Debug.logVerbose("doDisplay end...", MODULE);
        return path;
    }

    /**
     * 添加分类.
     *
     * @return
     */
    public String doAdd()
    {

        Debug.logVerbose("doAdd start...", MODULE);

        try
        {
            if (uploadFocus != null)
            {
                doUpload(uploadFocus, uploadFocusFileName, 1);
            }

            if (uploadLoseFocus != null)
            {
                doUpload(uploadLoseFocus, uploadLoseFocusFileName, 2);
            }

            User user = (User) request.getSession().getAttribute("user");
            // add by zhengxinlian/906976 默认设置为可见
            dpType.setVisibleFlag(1);
            dpType.setCreateUser(user.getUserName());
            dpType.setCreateDate(new Date());
            dpType.setUpdateDate(new Date());
            dpType.setTypeCode(UUID.randomUUID().toString());
            this.dpTypeService.saveType(this.dpType);

            // 日志参数
            List<String> logParamList = initLogParame(user.getUserName(),
                    "sdp.sce.dp.admin.dptype",
                    "sdp.sce.dp.admin.log.add.operate",
                    this.dpType.getTypeName());
            // 记录日志
            opLoggerService.info(this.getText("sdp.sce.dp.admin.system"), this
                    .getText("sdp.sce.dp.admin.log.type.operate.log",
                            logParamList), this.getText(Constants.ADD));

        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
        }
        return doList();
    }

    /**
     * 修改分类信息.
     *
     * @return
     */
    public String doEdit()
    {
        Debug.logVerbose("doEdit start...", MODULE);

        try
        {
            User user = (User) request.getSession().getAttribute("user");

            if (uploadFocus != null)
            {
                doUpload(uploadFocus, uploadFocusFileName, 1);
            }

            if (uploadLoseFocus != null)
            {
                doUpload(uploadLoseFocus, uploadLoseFocusFileName, 2);
            }
            DpType dbDpType = dpTypeService.findType(dpType.getId());

            dbDpType.setUpdateDate(new Date());
            if (StringUtils.isNotEmpty(dpType.getTypeImg1()))
            {
                dbDpType.setTypeImg1(dpType.getTypeImg1());
            }
            if (StringUtils.isNotEmpty(dpType.getTypeImg2()))
            {
                dbDpType.setTypeImg1(dpType.getTypeImg2());
            }
            dbDpType.setParentTypeCode(dpType.getParentTypeCode());
            dbDpType.setTypeName(dpType.getTypeName());
            dbDpType.setTypeDesc(dpType.getTypeDesc());

            this.dpTypeService.updateType(dbDpType);

            // 日志参数
            List<String> logParamList = initLogParame(user.getUserName(),
                    "sdp.sce.dp.admin.dptype",
                    "sdp.sce.dp.admin.log.update.operate",
                    this.dpType.getTypeName());
            // 记录日志
            opLoggerService.info(this.getText("sdp.sce.dp.admin.system"), this
                    .getText("sdp.sce.dp.admin.log.type.operate.log",
                            logParamList), this.getText(Constants.MOD));
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
        }
        Debug.logVerbose("doEdit end...", MODULE);
        return doList();
    }

    /**
     * 根据界面上checkbox选择的分类进行删除 edit by 905735.
     *
     * @return
     */
    public String doDelete()
    {

        Debug.logVerbose("doDelete start...", MODULE);
        try
        {
            User user = (User) request.getSession().getAttribute("user");
            
            //
            this.dpTypeService.deleteTypeByIds(ids);
            
            //先删除推荐
            cancleTypeFromRecommend(ids.split(","));
           
            
            // 日志参数
            List<String> logParamList = initLogParame(user.getUserName(),
                    "sdp.sce.dp.admin.dptype",
                    "sdp.sce.dp.admin.log.delete.operate", ids);
            // 记录日志
            opLoggerService.info(
                    getText("sdp.sce.dp.admin.system"),
                    getText("sdp.sce.dp.admin.log.type.operate.log",
                            logParamList), this.getText(Constants.DEL));

        }
        catch (ServiceException e)
        {
            this.setResult("success", false);
            this.setResult("msg", getText(e.getMessage()));
            Debug.logWarning(e, getText(e.getMessage()), MODULE);
        }
        catch (Exception e)
        {
            this.setResult("success", false);
            this.setResult("msg",
                    getText("sdp.sce.dp.admin.dptype.type.bind.data"));
            Debug.logError(getText("sdp.sce.dp.admin.dptype.type.bind.data"),
                    MODULE);
            Debug.logError(e, e.getMessage(), MODULE);
        }

        this.write(JSONObject.fromObject(this.getResult()).toString());
        return null;
    }

    /**
     * 上传图片
     * @param upload 上传的文件
     * @param uploadFileName 上传的文件名
     * @param i 上传图片的index
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private String doUpload(File upload, String uploadFileName, int i)
    {

        String uploadPath = Constants.APP_IMAGES_SAVE_PATH;
        String saveuploadFileName = FileUtil.resetFileName(uploadFileName);
        if (i == 1)
        {
            dpType.setTypeImg1(saveuploadFileName);
        }
        else
        {
            dpType.setTypeImg2(saveuploadFileName);
        }
        try
        {
            FileUtil.uploadFile(upload, uploadPath, saveuploadFileName);
        }
        catch (FileNotFoundException e)
        {
            Debug.logError(e.getMessage());
        }
        catch (IOException e)
        {
            Debug.logError(e.getMessage());
        }

        return null;
    }

    /**
     * 分类排序
     * @return
     */
	public String doSort() {
		this.setResult("success", false);
		String typeId = request.getParameter("typeId");
		String sortNum = request.getParameter("sortNum");
		
		try {
			if(StringUtils.isNotBlank(typeId) && StringUtils.isNotBlank(sortNum)){
				DpType dbDpType = dpTypeService.findType(typeId);
				if(null != dbDpType){
		            dbDpType.setSortNum(Integer.valueOf(sortNum).intValue());
		            dbDpType.setUpdateDate(new Date());
					this.dpTypeService.updateType(dbDpType);

					// 日志参数
					User user = (User) request.getSession().getAttribute("user");
					List<String> logParamList = initLogParame(user.getUserName(),
							"sdp.sce.dp.admin.dptype",
							"sdp.sce.dp.admin.log.sort.operate",
							dbDpType.getTypeName());
					// 记录日志
					opLoggerService.info(this.getText("sdp.sce.dp.admin.system"),
							this.getText("sdp.sce.dp.admin.log.type.operate.log",
									logParamList), this.getText(Constants.SORT));
		            
					this.setResult("success", true);
				}else{
					this.setResult("msg", "找不到分类信息");
				}
			}else{
				this.setResult("msg", "参数错误");
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
		}
		
		this.write(JSONObject.fromObject(this.getResult()).toString());
		return null;
	}

    /**
     * 分类名称是否唯一
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String isTypeNameUnique()
    {
        try
        {
            String hql = "";
            if ("edit".equals(forward))
            {
                hql = "from DpType dt where "
                    + " dt.typeName='" + dpType.getTypeName() + "'"
                    + " and dt.parentTypeCode='" + dpType.getParentTypeCode()
                    + "' and dt.id not in('" + dpType.getId() + "')";
            }
            else
            {
                hql = "from DpType dt where " +
                " dt.typeName='" + dpType.getTypeName() + "'" +
                " and dt.parentTypeCode='" + dpType.getParentTypeCode()+"'";
            }

            List<DpType> listTemp = dpTypeService.findByHQL(hql);
            if (listTemp.size() > 0)
            {
                this.setResult("msg", true);
            }
            else
            {
                this.setResult("success", true);
            }
        }
        catch (Exception e)
        {
            Debug.logError(e.getMessage());
        }
        this.write(JSONObject.fromObject(this.getResult()).toString());
        return null;
    }
    
	private void setDpTypeName(List list) throws Exception {
		List<DpType> list2 = list;
		for (DpType temp : list2) {
			String tempPCode = temp.getParentTypeCode();
			if (tempPCode != null) {
				DpType p = dpTypeService.findByTypeCode(tempPCode);
				if (p != null) {
					temp.setParentTypeName(p.getTypeName());
				}
			}
		}
	}
	
	/**
	 *  <p>位置：开发者后台-》应用管理-》应用推荐-》新增</p>
	 *  添加应用推荐分类，不包含根分类且不存在重复分类
	 * @return
	 */
	private void addTypeToRecommend(User userObj,String[] idArr) throws Exception {
		List<AppTypeRecommend> tempList = new ArrayList<AppTypeRecommend>();
		for (int k = 0; k < idArr.length; k++) {
			String tempId = idArr[k].trim();
			DpType aDpType = dpTypeService.findType(tempId);
			AppTypeRecommend appTypeRecommend = new AppTypeRecommend();
			appTypeRecommend.setAppRecommendCTime(new Date());
			appTypeRecommend.setCreateUser(userObj.getCreatedUser());
			appTypeRecommend.setDpType(aDpType);
			Double sort = 999.0;
			appTypeRecommend.setSort(sort);
			tempList.add(appTypeRecommend);
		}
		appTypeRecommendService.recommendAppTypeInfo(tempList);
		setResult("success", "操作成功");
	}
    
	/**
	 *  <p>位置：开发者后台-》应用管理-》应用推荐-》新增</p>
	 *  取消应用推荐分类，并删除分类下关联应用
	 * @return
	 */
	private void cancleTypeFromRecommend(String[] idArr)throws Exception{
		for(String str : idArr){
			DpType aDpType = new DpType();
			aDpType.setId(str.trim());
			appTypeRecommendService.cancleRecommendDpTypeInfo(aDpType);
		}		
       setResult("success", true);
	}
	
	/**
	 * 查询分类下的应用数,该方法会改变对象引用
	 * @param page 分页对象
	 * @throws Exception
	 */
	private void setAppTotal(Page<DpType> page) throws Exception{
        // 获取专题分类下的个数
        if (CollectionUtils.isNotEmpty(page.getResultList()))
        {
            for (DpType type : page.getResultList())
            {
                int count = appTypeRecommendService.getAppTotalByTypeId(type.getId());
                type.setAppTotal(count);
            }
        }
	}
	
    /**
     * 初始化日志参数.
     * @param user 用户对象
     * @param operate1 操作1
     * @param operate2 操作2
     * @param operate3 操作3
     * @return
     */
	private List<String> initLogParames(User user, String operate1,
			String operate2, String operate3) {
		// 日志参数
		List<String> logParamList = new ArrayList<String>();

		logParamList.add(user.getUserName());

		logParamList.add(getText(operate1));

		logParamList.add(getText(operate2));

		logParamList.add(getText(operate3));

		return logParamList;
	}
	
    public String getIds()
    {
        return ids;
    }

    public void setIds(String ids)
    {
        this.ids = ids;
    }

    public Page<DpType> getPage()
    {
        return page;
    }

    public void setPage(Page<DpType> page)
    {
        this.page = page;
    }

    public DpType getDpType()
    {
        return dpType;
    }

    public void setDpType(DpType dpType)
    {
        this.dpType = dpType;
    }

    public OpLoggerService getOpLoggerService()
    {
        return opLoggerService;
    }

    public void setOpLoggerService(OpLoggerService opLoggerService)
    {
        this.opLoggerService = opLoggerService;
    }

    public DpTypeService getDpTypeService()
    {
        return dpTypeService;
    }

    public void setDpTypeService(DpTypeService dpTypeService)
    {
        this.dpTypeService = dpTypeService;
    }

    public String getForward()
    {
        return forward;
    }

    public void setForward(String forward)
    {
        this.forward = forward;
    }

    public List<DpType> getDpTypeList()
    {
        return dpTypeList;
    }

    public void setDpTypeList(List<DpType> dpTypeList)
    {
        this.dpTypeList = dpTypeList;
    }

    public DpType getDpTypeQueryInfo()
    {
        return dpTypeQueryInfo;
    }

    public void setDpTypeQueryInfo(DpType dpTypeQueryInfo)
    {
        this.dpTypeQueryInfo = dpTypeQueryInfo;
    }

    public File getUploadFocus()
    {
        return uploadFocus;
    }

    public void setUploadFocus(File uploadFocus)
    {
        this.uploadFocus = uploadFocus;
    }

    public String getUploadFocusFileName()
    {
        return uploadFocusFileName;
    }

    public void setUploadFocusFileName(String uploadFocusFileName)
    {
        this.uploadFocusFileName = uploadFocusFileName;
    }

    public String getUploadFocusContentType()
    {
        return uploadFocusContentType;
    }

    public void setUploadFocusContentType(String uploadFocusContentType)
    {
        this.uploadFocusContentType = uploadFocusContentType;
    }

    public File getUploadLoseFocus()
    {
        return uploadLoseFocus;
    }

    public void setUploadLoseFocus(File uploadLoseFocus)
    {
        this.uploadLoseFocus = uploadLoseFocus;
    }

    public String getUploadLoseFocusFileName()
    {
        return uploadLoseFocusFileName;
    }

    public void setUploadLoseFocusFileName(String uploadLoseFocusFileName)
    {
        this.uploadLoseFocusFileName = uploadLoseFocusFileName;
    }

    public String getUploadLoseFocusContentType()
    {
        return uploadLoseFocusContentType;
    }

    public void setUploadLoseFocusContentType(String uploadLoseFocusContentType)
    {
        this.uploadLoseFocusContentType = uploadLoseFocusContentType;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }


	public AppRecommendService getAppRecommendService() {
		return appRecommendService;
	}


	public void setAppRecommendService(AppRecommendService appRecommendService) {
		this.appRecommendService = appRecommendService;
	}


	public AppTypeRecommendService getAppTypeRecommendService() {
		return appTypeRecommendService;
	}


	public void setAppTypeRecommendService(
			AppTypeRecommendService appTypeRecommendService) {
		this.appTypeRecommendService = appTypeRecommendService;
	}
    
  
}
