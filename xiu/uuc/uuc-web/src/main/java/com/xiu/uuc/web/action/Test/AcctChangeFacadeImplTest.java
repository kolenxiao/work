package com.xiu.uuc.web.action.Test;

import java.net.MalformedURLException;
import com.caucho.hessian.client.HessianProxyFactory;
import com.xiu.uuc.facade.AcctChangeFacade;
import com.xiu.uuc.facade.dto.AcctChangeDTO;
import com.xiu.uuc.facade.dto.AcctChangeExtDTO;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserAcctDTO;
import com.xiu.uuc.web.util.DateUtil;

public class AcctChangeFacadeImplTest {
	
	public static void main(String[] args) throws MalformedURLException {
		
		AcctChangeFacadeImplTest acctChangeFacadeImplTest = new AcctChangeFacadeImplTest();
		
	    acctChangeFacadeImplTest.checkIsFreezeUserAcct();//校验当前用户账户是否被冻结 true表示被冻结 false表示未被冻结
		acctChangeFacadeImplTest.getVirtualChangeList();//虚拟账户明细查询（当前用户当前渠道虚拟账户账目变更信息，这里指金额）
		acctChangeFacadeImplTest.getVirtualChangeListExt();//虚拟账户明细查询（当前用户当前渠道虚拟账户账目变更信息，这里指金额）
		acctChangeFacadeImplTest.setUserAcctFreeze();//设置用户账户冻结
		acctChangeFacadeImplTest.setUserAcctUnFreeze();//设置用户账户解冻
		acctChangeFacadeImplTest.setUserAcctItemFreezeMoney();//修改当前账目冻结金额(主要是可提现，不可提现账目冻结金额缺省为0)
		
	}
	
	private AcctChangeFacade getAcctChangeFacade(){
		AcctChangeFacade acctChangeFacade = null;
		try {
			String url = "http://uuc.xiu.com:8080/remote/acctChangeFacade";
			HessianProxyFactory factory = new HessianProxyFactory();
			acctChangeFacade = (AcctChangeFacade) factory.create(AcctChangeFacade.class, url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return acctChangeFacade;
	}
	
	//校验当前用户账户是否被冻结 01表示账户正常 02账户已经冻结 
	private void checkIsFreezeUserAcct() throws MalformedURLException {
		AcctChangeFacade acctChangeFacade = getAcctChangeFacade();
		UserAcctDTO userAcctDTO = new UserAcctDTO();
		userAcctDTO.setUserId(110000000007L);
        Result rs = (Result)acctChangeFacade.checkIsFreezeUserAcct(userAcctDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//设置用户账户冻结
	private void setUserAcctFreeze() throws MalformedURLException {
		AcctChangeFacade acctChangeFacade = getAcctChangeFacade();
		UserAcctDTO userAcctDTO = new UserAcctDTO();
		userAcctDTO.setUserId(110000000007L);
        Result rs = (Result)acctChangeFacade.setUserAcctFreeze(userAcctDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//设置用户账户解冻
	private void setUserAcctUnFreeze() throws MalformedURLException {
		AcctChangeFacade acctChangeFacade = getAcctChangeFacade();
		UserAcctDTO userAcctDTO = new UserAcctDTO();
		userAcctDTO.setUserId(110000000007L);
		userAcctDTO.setDataVersion(1L);
        Result rs = (Result)acctChangeFacade.setUserAcctUnFreeze(userAcctDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//修改当前账目冻结金额(主要是可提现，不可提现账目冻结金额缺省为0)
	private void setUserAcctItemFreezeMoney()throws MalformedURLException {
		AcctChangeFacade acctChangeFacade = getAcctChangeFacade();
		AcctItemDTO acctItemDTO = new AcctItemDTO();
        acctItemDTO.setUserId(110000000008L);
        acctItemDTO.setAcctTypeCode("01");
        acctItemDTO.setFreezeAmount(500L);
        acctItemDTO.setOnlyClearZero("0");
        acctItemDTO.setOperType("02");
        
        AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
        acctChangeDTO.setRltId(11111L);
        acctChangeDTO.setBusiType("01");
        acctChangeDTO.setIoAmount(acctItemDTO.getFreezeAmount());
        acctChangeDTO.setRltChannelId("1");
        acctChangeDTO.setOperId("999");
        
        Result rs = (Result)acctChangeFacade.setUserAcctItemFreezeMoney(acctItemDTO, acctChangeDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//虚拟账户明细查询（当前用户当前渠道虚拟账户账目变更信息，这里指金额）
	private void getVirtualChangeList() throws MalformedURLException {
		AcctChangeFacade acctChangeFacade = getAcctChangeFacade();
		AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
		//acctChangeDTO.setUserId(110000000007L);
		//acctChangeDTO.setBusiType("02");
		acctChangeDTO.setValidTime(DateUtil.getDateByFormatStr("2011-08-18 19:40:48.375", DateUtil.MSEL_FORMAT));
        Result rs = (Result)acctChangeFacade.getVirtualChangeList(acctChangeDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//虚拟账户明细查询（当前用户当前渠道虚拟账户账目变更信息，这里指金额）
	private void getVirtualChangeListExt() throws MalformedURLException {
		AcctChangeFacade acctChangeFacade = getAcctChangeFacade();
		AcctChangeExtDTO acctChangeExtDTO = new AcctChangeExtDTO();
		//acctChangeDTO.setUserId(110000000007L);
		//acctChangeDTO.setBusiType("02");
		//acctChangeExtDTO.setValidTime(DateUtil.getDateByFormatStr("2011-08-18 19:40:48.375", DateUtil.MSEL_FORMAT));
        Result rs = (Result)acctChangeFacade.getVirtualChangeListExt(acctChangeExtDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}

}
