package com.coship.sdp.sce.dp.action.recommend;

import java.util.Arrays;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.HttpUtil;
import com.coship.sdp.sce.dp.common.QueryAppInfoThread;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanelItem;
import com.coship.sdp.sce.dp.recommend.service.AppRecommendPanelItemService;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

public class AppRecommendPanelItemAction extends BaseAction
{
    private static final long serialVersionUID = -2371677510146203730L;

    private Page<AppRecommendPanelItem> page;
    
    @Autowired
    private AppRecommendPanelItemService appRecommendPanelItemService;
    
    private String MODULE_NAME = this.getClass().getName();
    
    //版块名称
    private String itemName;
    
    //排序字段
    private String sort;
    
    //主键
    private String key;
    
    //版块主键
    private String panelKey;
    
    //pojo对象
    private AppRecommendPanelItem aformObj;
    
    //主键集合
    private String ids;
    
    //分类ID
    private Integer type;
    
    //版块名称
    private String panelName;
    
    //关联类型显示名
    private String showName;
    
    
    /**
     * 获取版块元素列表
     * @return
     */
    public String doPanelItemList()
    {
        try
        {
            this.page = setUpPage(page, limit, start);
            appRecommendPanelItemService.queryItemListByPanelId(page, itemName, type, panelKey);
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        
        return "doPanelItemList";
    }
    
    
    /**
     * 查询可用的元素
     * @return
     */
    public String doUseFulItemList()
    {
        try
        {
            this.page = setUpPage(page, limit, start);
            appRecommendPanelItemService.queryUnRecommendItemList(page, itemName, type);
            
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        
        return "doUseFulItemList";
    }
    
    /**
     * 查询元素列表
     * @return
     */
    public String doList()
    {
        try
        {
            this.page = setUpPage(page, limit, start);
            appRecommendPanelItemService.queryAppRecommendPanelItemList(page, itemName, type);
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        
        return "doListItem";
    }
    
    
    /**
     * 版块人工排序
     * @return
     */
    public String doSort()
    {
      try{
          appRecommendPanelItemService.doSortAppRecoomendPanelItem(key, sort);
          
          //刷新缓存
          HttpUtil.getAppRecommendPanelItemJson();
      }catch(Exception e){
          Debug.logError(e, e.getMessage(), MODULE_NAME);
          exception_msg = getText("sdp.sce.dp.admin.common.query.error");
          return ERROR;
      }
        
      return doPanelItemList();    
    }
    
    /**
     * 新增版块信息
     */
    public String doSaveItem()
    {
        try
        {
            appRecommendPanelItemService.saveAppRecommendPanelItem(aformObj);

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
    public String doUpdateItem()
    {
        try
        {
            appRecommendPanelItemService.updateAppRecoomendPanelItem(aformObj);
            
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
 
    
    public String doRecommendItem()
    {
       try{
           String[] idsStr = ids.split(",");
           appRecommendPanelItemService.doRecommendItem(idsStr, panelKey);
           setResult("success", "操作成功");
           
           //刷新缓存
           HttpUtil.getAppRecommendPanelItemJson();
       }catch(Exception e){
           setResult(
                   "exception",
                   "版块关联元素失败");
           Debug.logError(e, e.getMessage(), MODULE_NAME);
       }
       
       write(JSONObject.fromObject(getResult()).toString());   
       return null;
    }
      
    
    public String doDeletePanelItem()
    {
        try
        {
            String[] idsStr = ids.split(",");
            int result = appRecommendPanelItemService
                    .unRecommendItemToPanel(Arrays.asList(idsStr));

            if (result == idsStr.length)
            {
                setResult("success", String.format("成功取消关联%d条记录", result));
            }
            else
            {
                setResult("msg", String.format("请求取消：%d条记录,实际取消:%d条记录",
                        idsStr.length, result));

            }
            // 刷新缓存
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
     * 删除元素
     * @return
     */
    public String doDeleteItem()
    {
        try
        {
            String[] idsStr = ids.split(",");
            int result = appRecommendPanelItemService.deleteItem(Arrays.asList(idsStr));

            if (result == idsStr.length)
            {
                setResult("success", String.format("成功删除%d条记录", result));
            }
            else
            {
                setResult("msg", "删除录数："+idsStr.length+"条,实际删除:"+result);
          
            }
            
            //刷新缓存
            HttpUtil.getAppRecommendPanelItemJson();

        }
        catch (Exception e)
        {
            setResult(
                    "exception",
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
            aformObj = appRecommendPanelItemService.getAppRecoomendPanelItemById(key);
            
            setShowName(appRecommendPanelItemService.getItemTypeValue(
                    aformObj.getType(), aformObj.getTypeValue()));
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
     * 跳转新增元素页面
     * @return
     */
    public String toSaveItem()
    {
        return "toSave";
    }

    public Page<AppRecommendPanelItem> getPage()
    {
        return page;
    }

    public void setPage(Page<AppRecommendPanelItem> page)
    {
        this.page = page;
    }

    public AppRecommendPanelItemService getAppRecommendPanelItemService()
    {
        return appRecommendPanelItemService;
    }

    public void setAppRecommendPanelItemService(
            AppRecommendPanelItemService appRecommendPanelItemService)
    {
        this.appRecommendPanelItemService = appRecommendPanelItemService;
    }

    public String getMODULE_NAME()
    {
        return MODULE_NAME;
    }

    public void setMODULE_NAME(String mODULE_NAME)
    {
        MODULE_NAME = mODULE_NAME;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getPanelKey()
    {
        return panelKey;
    }

    public void setPanelKey(String panelKey)
    {
        this.panelKey = panelKey;
    }

    public AppRecommendPanelItem getAformObj()
    {
        return aformObj;
    }

    public void setAformObj(AppRecommendPanelItem aformObj)
    {
        this.aformObj = aformObj;
    }

    public String getIds()
    {
        return ids;
    }

    public void setIds(String ids)
    {
        this.ids = ids;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }


    public String getPanelName()
    {
        return panelName;
    }


    public void setPanelName(String panelName)
    {
        this.panelName = panelName;
    }


    public String getShowName()
    {
        return showName;
    }


    public void setShowName(String showName)
    {
        this.showName = showName;
    }    
}
