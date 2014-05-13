package com.xiu.uuc.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.xiu.uuc.facade.dto.BusiLogQueryDTO;
import com.xiu.uuc.facade.dto.BusiTypeDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.manager.LogManager;
import com.xiu.uuc.web.action.base.BaseAction;

public class LogQueryAction extends BaseAction implements ModelDriven<BusiLogQueryDTO> {
	
	/**
	 * 查询业务日志
	 * @return
	 * @throws Exception
	 */
	public String queryBusiLogList() throws Exception {
		try {
			//查日志列表
			Result result = logManager.queryBusiLogList(busiLogQueryDTO);
			//查询业务操作类型列表
			List<BusiTypeDTO> busiTypeList = logManager.queryBusiTypeList(new BusiTypeDTO());
			
			setResult("logList",result.getData());
			setResult("busiTypeList",busiTypeList);
			setResult("page",result.getPage());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryBusiLogList发生异常:"+e.getMessage());
			setErrorReason(e.getMessage());
			return ERROR;
		}
	}
	
	   public String goQueryBusiLogList() throws Exception {
	        try {
	            //查询业务操作类型列表
	            List<BusiTypeDTO> busiTypeList = logManager.queryBusiTypeList(new BusiTypeDTO());
	            setResult("busiTypeList",busiTypeList);
	            return SUCCESS;
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error("goQueryBusiLogList发生异常:"+e.getMessage());
	            setErrorReason(e.getMessage());
	            return ERROR;
	        }
	    }
	
	

	@Override
	public BusiLogQueryDTO getModel() {
		return busiLogQueryDTO;
	}
	
	@Autowired
	private LogManager logManager;
    private static final long serialVersionUID = 2648689494151515436L;
	private static final Logger logger = Logger.getLogger(LogQueryAction.class);
	private BusiLogQueryDTO busiLogQueryDTO = new BusiLogQueryDTO();
	
}
