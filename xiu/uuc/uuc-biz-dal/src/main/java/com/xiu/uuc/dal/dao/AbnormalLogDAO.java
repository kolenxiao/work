package com.xiu.uuc.dal.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.xiu.uuc.dal.param.AbnormalLogParam;
import com.xiu.uuc.dal.param.AcctChangeParam;
import com.xiu.uuc.dal.param.AcctItemParam;
import com.xiu.uuc.dal.po.AbnormalLogPO;
import com.xiu.uuc.dal.po.AcctChangePO;
import com.xiu.uuc.dal.po.AcctItemPO;

/**
 * @ClassName: AbnormalLogDAO 
 * @Description: 虚拟账户异动日志Dao接口
 * @author menglei
 * @date Dec 14, 2011 10:04:47 AM 
 */
public interface AbnormalLogDAO {
	
	/**
     * @Title: insertAbnormalLog 
     * @Description: 新增虚拟账户异动日志 
     * @return Long    返回类型 
     * @throws
     */
	Long insertAbnormalLog(AbnormalLogPO abnormalLogPO) throws DataAccessException;

	/**
	 * @Title: listAbnormalLog 
	 * @Description: 查询虚拟账户异动日志 
	 * @return List<AbnormalLogPO>    返回类型 
	 * @throws
	 */
    List<AbnormalLogPO> listAbnormalLog(AbnormalLogParam abnormalLogParam) throws DataAccessException ;
    
    /**
	 * @Title: updateAbnormalLogPO 
	 * @Description: 修改异动信息发送邮件状态
	 * @return Integer    返回类型 
	 * @throws
	 */
	Integer updateAbnormalLogPO(AbnormalLogPO abnormalLogPO) throws DataAccessException ;
    
    /**
	 * @Title: getlistAcctItemAudit 
	 * @Description: 查询待审核账目信息列表
	 * @return List<AcctItemPO>    返回类型 
	 * @throws
	 */
    List<AcctItemPO> getlistAcctItemAudit(AcctItemParam acctItemParam) throws DataAccessException ;
    
    /**
	 * @Title: getChangeSumAudit 
	 * @Description: 根据账目id和账目类型分组，
	 *               统计该账目对应的账目流水进出帐总额，进出帐笔数
	 * @return    返回类型  AbnormalLogPO
	 * @throws
	 */
    AbnormalLogPO getChangeSumAudit(AbnormalLogParam abnormalLogParam) throws DataAccessException ;
    
    /**
	 * @Title: getLastAcctChangePO 
	 * @Description: 通过账目id和账目类型获取该账目id离系统时间最近的那条流水记录（一段时间内）
	 * @return    返回类型  AcctChangePO
	 * @throws
	 */
    AcctChangePO getLastAcctChangePO(AcctChangeParam acctChangeParam) throws DataAccessException ;
    
    /**
	 * @Title: getNotBalanceAlarmInfoList 
	 * @Description:  统计查询虚拟账户账目变更流水信息 在一段时间内账目不平衡的列表信息 
	 * @return List<AbnormalLogPO>    返回类型 
	 * @throws
	 */
    List<AbnormalLogPO> getNotBalanceAlarmInfoList(AbnormalLogParam abnormalLogParam) throws DataAccessException ;

}
