package com.xiu.uuc.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.core.service.AbnormalLogService;
import com.xiu.uuc.dal.param.AbnormalLogParam;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.web.action.base.BaseAction;

public class AbnormalLogAction extends BaseAction implements ModelDriven<AbnormalLogParam>{

	public String auditAccountBalance(){
		try {
			//abnormalLogService.auditAccountBalance();
			abnormalLogService.auditAccountBalanceEfficient();
			setResult("虚拟账户异动审计","账目平衡审计成功");
			return SUCCESS;
		} catch (BusinessException e) {
			setErrorReason("虚拟账户异动审计,账目平衡审计失败");
			return ERROR;
		}
	}
	
	public String auditAccountPayLimit(){
		try {
			AcctItemDTO acctItem = new AcctItemDTO(); 
			abnormalLogService.auditAccountPayLimit(acctItem);
			setResult("虚拟账户异动审计","账目额度次数审计成功");
			return SUCCESS;
		} catch (BusinessException e) {
			setErrorReason("虚拟账户异动审计，账目额度次数审计失败");
			return ERROR;
		}
	}

	@Autowired
	public void setAbnormalLogService(AbnormalLogService abnormalLogService) {
		this.abnormalLogService = abnormalLogService;
	}

	@Override
	public AbnormalLogParam getModel() {
		return abnormalLogParam;
	}
	
	private static final long serialVersionUID = 8874503944126563034L;
	private AbnormalLogService abnormalLogService;
	private AbnormalLogParam abnormalLogParam = new AbnormalLogParam();
}
