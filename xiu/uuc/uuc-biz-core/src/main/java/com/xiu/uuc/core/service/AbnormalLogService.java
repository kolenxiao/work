package com.xiu.uuc.core.service;

import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.facade.dto.AcctItemDTO;

/**
 * @ClassName: AbnormalLogService 
 * @Description: 虚拟账户异动日志Service接口
 * @author menglei
 * @date Dec 14, 2011 10:04:47 AM 
 */
public interface AbnormalLogService {
    
    /**
     * @Title: auditAccountBalanceEfficient 
	 * @Description: (一次性从数据库中查询出来,更高效)
	 *               审计单个用户可提现，不可提现，积分三种类型账目总账
	 *               和对应账目类型流水进出帐总额是否平衡
	 *               当账目不平衡时，写账目异常表，同时发告警邮件
	 *               通过job调度平台异步调度
	 * @return void    返回类型 
	 * @throws
     */
    void auditAccountBalanceEfficient()throws BusinessException;
    
    /**
     * @Title: auditAccountPayLimit 
	 * @Description: 审计单个用户支付情况
	 *               情况1 单个用户,当前账目类型在一段时间内支付额度>支付额度伐值
	 *               情况2 单个用户,当前账目类型在一段时间内支付次数>支付次数伐值
	 *               当情况为1或者2时候，写账目异常表，同时发告警邮件
	 *               准实时线程异布调度
	 * @return void    返回类型 
	 * @throws
     */
    void auditAccountPayLimit(AcctItemDTO acctItemDTO)throws BusinessException;
}
