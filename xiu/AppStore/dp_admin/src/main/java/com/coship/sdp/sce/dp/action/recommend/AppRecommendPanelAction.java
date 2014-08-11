package com.coship.sdp.sce.dp.action.recommend;

import java.util.Arrays;

import net.sf.json.JSONObject;


import org.springframework.beans.factory.annotation.Autowired;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.HttpUtil;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanel;
import com.coship.sdp.sce.dp.recommend.service.AppRecommendPanelService;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;


public class AppRecommendPanelAction extends BaseAction
{
    private static final long serialVersionUID = -2371677510146203730L;
   
    private String MODULE_NAME = this.getClass().getName();
    
    private Page<AppRecommendPanel> page;
    
    @Autowired
    private AppRecommendPanelService appRecommendPanelService;
    
    //版块名称
    private String panelName;
    
    //排序字段
    private String sort;
    
    //主键
    private String key;
    
    //pojo对象
    private AppRecommendPanel aformObj;
    
    //主键集合
    private String ids;
    
    
    /**
     * 版块查询列表
     * @return
     */
    public String doList()
    {
        try
        {
            //初始化分页对象
            this.page = setUpPage(page, limit, start);
            
            //根据条件查询版块信息
            appRecommendPanelService.queryAppRecommendPanelList(page, panelName);
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        
        return "doListPanel";
    }
    
    /**
     * 版块人工排序
     * @return
     */
    public String doSort()
    {
      try{
          appRecommendPanelService.doSortAppRecoomendPanel(key, sort);
          
          //刷新缓存
          HttpUtil.getAppRecommendPanelItemJson();
      }catch(Exception e){
          Debug.logError(e, e.getMessage(), MODULE_NAME);
          exception_msg = getText("sdp.sce.dp.admin.common.query.error");
          return ERROR;
      }
        
      return doList();    
    }
    
    /**
     * 新增版块信息
     */
    public String doSavePanel()
    {
        try
        {
            appRecommendPanelService.saveAppRecommendPanel(aformObj);
            
            //刷新缓存
            HttpUtil.getAppRecommendPanelItemJson();
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }

        return doList();
    }
    
    /**
     * 修改版块元素信息
     * @return
     */
    public String doUpdatePanel()
    {
        try
        {
            appRecommendPanelService.updateAppRecoomendPanel(aformObj);
            
            //刷新缓存
            HttpUtil.getAppRecommendPanelItemJson();
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        return doList();
    }
    
    /**
     * 删除版块记录
     * @return
     */
    public String doDeletePanel()
    {
        try
        {
            String[] idsStr = ids.split(",");// 获取ID集合
            int result = appRecommendPanelService
                    .deleteAppRecoomendPanel(Arrays.asList(idsStr));

            if (result == idsStr.length)
            {
                setResult("success", String.format("成功删除%d条记录", result));
            }
            else
            {
                setResult("msg", "请求" + idsStr.length + "条,实际删除:" + result);

            }
            
            //刷新缓存
            HttpUtil.getAppRecommendPanelItemJson();
        }
        catch (Exception e)
        {
            setResult("exception",
                    getText("sdp.sce.dp.admin.common.data.delete.exception"));
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = getText("sdp.sce.dp.admin.common.data.delete.exception");
        }

        write(JSONObject.fromObject(getResult()).toString());
        return null;
    }
    
    /**
     * 修改版块信息
     * @return
     */
    public String toDetail()
    {
        try
        {
            aformObj = appRecommendPanelService.getAppRecoomendPanelById(key);
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        
        return "toEdit";
    }
    
    /**
     * 新增版块信息
     * @return
     */
    public String toSavePanel()
    {
        return "toSave";
    }

    public Page<AppRecommendPanel> getPage()
    {
        return page;
    }

    public void setPage(Page<AppRecommendPanel> page)
    {
        this.page = page;
    }

    public AppRecommendPanelService getAppRecommendPanelService()
    {
        return appRecommendPanelService;
    }

    public void setAppRecommendPanelService(
            AppRecommendPanelService appRecommendPanelService)
    {
        this.appRecommendPanelService = appRecommendPanelService;
    }

    public String getPanelName()
    {
        return panelName;
    }

    public void setPanelName(String panelName)
    {
        this.panelName = panelName;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public AppRecommendPanel getAformObj()
    {
        return aformObj;
    }

    public void setAformObj(AppRecommendPanel aformObj)
    {
        this.aformObj = aformObj;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getIds()
    {
        return ids;
    }

    public void setIds(String ids)
    {
        this.ids = ids;
    }
}
