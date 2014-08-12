package com.coship.depgm.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.coship.depgm.common.DepgmConfig;
import com.coship.depgm.service.ProgramTypeService;
import com.coship.depgm.utils.HttpUtils;

/**
 * @author 908618
 * 
 */
public class DepgAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(DepgAction.class);

	@Autowired
	private ProgramTypeService programTypeService;

	// 分类id
	private String typeId;
	// 节目id
	private String programId;

	private static String PROGRAM_TYPE_LIST_URL = DepgmConfig.getDepgUrl() + "/getProgramType";
	private static String LIVE_PROGRAMS_URL = DepgmConfig.getDepgUrl() + "/getLivePrograms";
	private static String BTV_PROGRAMS_URL = DepgmConfig.getDepgUrl() + "/getBTVPrograms";
	private static String PROGRAM_DETAIL_URL = DepgmConfig.getDepgUrl() + "/getProgramDetail";

	/**
	 * 获取节目分类列表
	 */
	@SuppressWarnings("rawtypes")
	public void getProgramTypeList() {
		try {
			String response = HttpUtils.sendGet(PROGRAM_TYPE_LIST_URL, "utf-8");
			JSONObject jo = JSON.parseObject(response);
			if (null != jo) {
				String ret = jo.getString("ret");
				if (StringUtils.equals("0", ret)) {
					String typeList = jo.getString("result");
					if(StringUtils.isNotBlank(typeList)){
						List<Map> list = JSON.parseArray(typeList, Map.class);
						resultMap.put("rows", list);
					}
					jsonMap();
				}
			}
		} catch (Exception e) {
			logger.error("调用接口getProgramTypeList失败", e);
			jsonRet("1", e.getMessage());
		}
	}

	/**
	 * 获取直播分类节目列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getLivePrograms() {
		try {
			Assert.notNull(typeId, "分类id不能为空!");
			Map params = new HashMap();
			params.put("typeId", typeId);
			params.put("pageSize", String.valueOf(getPager().getMax()));
			params.put("curPage", String.valueOf(getPager().getPage()));
			
			String response = HttpUtils.sendPost(LIVE_PROGRAMS_URL, params, "utf-8");
			JSONObject jo = JSON.parseObject(response);
			if (null != jo) {
				String ret = jo.getString("ret");
				if (StringUtils.equals("0", ret)) {
					String programList = jo.getString("result");
					List<Map> rows = null;
					if(StringUtils.isNotBlank(programList)){
						rows = JSON.parseArray(programList, Map.class);
					}
					if(null == rows){
						rows = new ArrayList<Map>();
					}
					resultMap.put("total", jo.get("totalCount"));
					resultMap.put("rows", rows);
					jsonMap();
				}
			}
		} catch (Exception e) {
			logger.error("调用接口getLivePrograms失败", e);
			jsonRet("1", e.getMessage());
		}
	}

	/**
	 * 获取一周回看分类节目列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getBTVPrograms() {
		try {
			Assert.notNull(typeId, "分类id不能为空!");
			Map params = new HashMap();
			params.put("typeId", typeId);
			params.put("pageSize", String.valueOf(getPager().getMax()));
			params.put("curPage", String.valueOf(getPager().getPage()));
			
			String response = HttpUtils.sendPost(BTV_PROGRAMS_URL, params, "utf-8");
			JSONObject jo = JSON.parseObject(response);
			if (null != jo) {
				String ret = jo.getString("ret");
				if (StringUtils.equals("0", ret)) {
					String programList = jo.getString("result");
					List<Map> rows = null;
					if(StringUtils.isNotBlank(programList)){
						rows = JSON.parseArray(programList, Map.class);
					}
					if(null == rows){
						rows = new ArrayList<Map>();
					}
					resultMap.put("total", jo.get("totalCount"));
					resultMap.put("rows", rows);
					jsonMap();
				}
			}
		} catch (Exception e) {
			logger.error("调用接口getBTVPrograms失败", e);
			jsonRet("1", e.getMessage());
		}
	}

	/**
	 * 获取回看节目详情
	 */
	public void getProgramDetail() {
		try {
			Assert.notNull(programId, "节目id不能为空!");
			String url = PROGRAM_DETAIL_URL + "?programId=" + programId;
			String response = HttpUtils.sendGet(url, "utf-8");
			JSONObject jo = JSON.parseObject(response);
			if (null != jo) {
				String ret = jo.getString("ret");
				if (StringUtils.equals("0", ret)) {
					String result = jo.getString("result");
					this.getTestJSON(result);  
				}
			}
		} catch (Exception e) {
			logger.error("调用接口getProgramDetail失败", e);
			jsonRet("1", e.getMessage());
		}
	}
	
	private String getTestJSON(String s) throws Exception{  
        response.setCharacterEncoding("utf-8");  
        PrintWriter pw = response.getWriter();  
        pw.write(s);  

        pw.flush();  
        pw.close();  
        return null;  
    }  

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

}
