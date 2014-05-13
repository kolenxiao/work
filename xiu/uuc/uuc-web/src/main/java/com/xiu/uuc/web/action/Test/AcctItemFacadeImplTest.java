package com.xiu.uuc.web.action.Test;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import com.caucho.hessian.client.HessianProxyFactory;
import com.xiu.uuc.facade.AcctItemFacade;
import com.xiu.uuc.facade.dto.AcctChangeDTO;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.facade.dto.IntegeralRuleDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.VirtualAcctItemDTO;

public class AcctItemFacadeImplTest {

	public static void main(String[] args) throws MalformedURLException {
		
		AcctItemFacadeImplTest acctItemFacadeImplTest = new AcctItemFacadeImplTest();
		
		//acctItemFacadeImplTest.getVirtualAccountInfo();//虚拟帐户信息（账目）包括：可提现总金额,可提现冻结金额,不可提现总金额,不可提现冻结金额,总积分,积分冻结     
		//acctItemFacadeImplTest.getIntegeralByRule(); //根据积分规则获取积分数量
		//acctItemFacadeImplTest.getVirtualItemList(); //虚拟账户查询（当前用户当前渠道虚拟账户账目信息）
		acctItemFacadeImplTest.addVirtualAccountMoney();      //虚拟账户金额充值 使用场景：虚拟账户金额充值
		//acctItemFacadeImplTest.decVirtualAccountMoney();      //虚拟账户金额支付 使用场景：虚拟账户金额支付
		//acctItemFacadeImplTest.decVirtualAccountMoneyByItemTypeCode(); //虚拟账户金额扣款 使用场景：虚拟账户金额扣款
		//acctItemFacadeImplTest.addVirtualAccountIntegral(); //虚拟账户积分充值 使用场景：虚拟账户积分充值
		//acctItemFacadeImplTest.decVirtualAccountIntegral(); //虚拟账户积分充值 使用场景：虚拟账户积分支付
        
	}
	
	private AcctItemFacade getAcctItemFacade(){
		AcctItemFacade acctItemFacade = null;
		try {
			String url = "http://uuc.xiu.com:80/remote/acctItemFacade";
			HessianProxyFactory factory = new HessianProxyFactory();
			acctItemFacade = (AcctItemFacade) factory.create(AcctItemFacade.class, url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return acctItemFacade;
	}
	
	//虚拟帐户信息（账目）包括：可提现总金额,可提现冻结金额,不可提现总金额,不可提现冻结金额,总积分,积分冻结 
	private void getVirtualAccountInfo() throws MalformedURLException {
		AcctItemFacade acctItemFacade = getAcctItemFacade();
		VirtualAcctItemDTO virtualAcctItemDTO = new VirtualAcctItemDTO();
		//virtualAcctItemDTO.setUserId(118L);
        Result rs = (Result)acctItemFacade.getVirtualAccountInfo(virtualAcctItemDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else {
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//根据积分规则获取积分数量
	@SuppressWarnings("unchecked")
	private void getIntegeralByRule() throws MalformedURLException {
		AcctItemFacade acctItemFacade = getAcctItemFacade();
		IntegeralRuleDTO integeralRuleDTO = new IntegeralRuleDTO();
		integeralRuleDTO.setCreatePointCode("001");
		Map map = new HashMap();
		map.put("p1", 1);
		map.put("p2", 1);
		integeralRuleDTO.setMap(map);
        Result rs = (Result)acctItemFacade.getIntegeralByRule(integeralRuleDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//虚拟账户查询（当前用户当前渠道虚拟账户账目信息）
	private void getVirtualItemList() throws MalformedURLException {
		AcctItemFacade acctItemFacade = getAcctItemFacade();
		AcctItemDTO acctItemDTO = new AcctItemDTO();
		acctItemDTO.setUserId(100000000225L);
		acctItemDTO.setAcctTypeCode("01");
        Result rs = (Result)acctItemFacade.getVirtualItemList(acctItemDTO);
        System.out.println("acctItemFacade==="+acctItemFacade);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//虚拟账户金额充值 使用场景：虚拟账户金额充值（可提现或者不可提现）
	private void addVirtualAccountMoney()throws MalformedURLException {
		AcctItemFacade acctItemFacade = getAcctItemFacade();
		AcctItemDTO acctItemDTO = new AcctItemDTO();
        acctItemDTO.setUserId(110000000007L);
        acctItemDTO.setAcctTypeCode("01");
        acctItemDTO.setTotalAmount(500L);
        
        AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
        //acctChangeDTO.setAcctItemId(100000000292L);
        acctChangeDTO.setRltId(11111L);
        acctChangeDTO.setBusiType("01");
        acctChangeDTO.setIoAmount(acctItemDTO.getTotalAmount());
        acctChangeDTO.setRltChannelId("1");
        acctChangeDTO.setOperId("999");
        
        Result rs = (Result)acctItemFacade.addVirtualAccountMoney(acctItemDTO, acctChangeDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}

	//虚拟账户金额支付 使用场景：虚拟账户金额支付 
	private void decVirtualAccountMoney()throws MalformedURLException {
		AcctItemFacade acctItemFacade = getAcctItemFacade();
        AcctItemDTO acctItemDTO = new AcctItemDTO();
        
        acctItemDTO.setUserId(110000000007L);
        acctItemDTO.setTotalAmount(500L);
        
        AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
        acctChangeDTO.setRltId(11111L);
        acctChangeDTO.setBusiType("02");
        acctChangeDTO.setRltChannelId("1");
        acctChangeDTO.setOperId("000");
        
        Result rs = (Result)acctItemFacade.decVirtualAccountMoney(acctItemDTO, acctChangeDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//虚拟账户积分充值 使用场景：虚拟账户积分充值
	@SuppressWarnings({ "unused", "unchecked" })
	private void addVirtualAccountIntegral()throws MalformedURLException {
		AcctItemFacade acctItemFacade = getAcctItemFacade();
		AcctItemDTO acctItemDTO = new AcctItemDTO();
        acctItemDTO.setUserId(110000000007L);
        
        AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
        acctChangeDTO.setRltId(11111L);
        acctChangeDTO.setBusiType("04");
        acctChangeDTO.setIoAmount(acctItemDTO.getTotalAmount());
        acctChangeDTO.setRltChannelId("1");
        acctChangeDTO.setOperId("99999");
        
        Map map = new HashMap();
        map.put("p1", 1);
        map.put("p2", 1);
        IntegeralRuleDTO integeralRuleDTO = new IntegeralRuleDTO("004",map);
        Result rs = (Result)acctItemFacade.addVirtualAccountIntegral(acctItemDTO, acctChangeDTO,integeralRuleDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//虚拟账户积分充值 使用场景：虚拟账户积分支付
	private void decVirtualAccountIntegral()throws MalformedURLException {
		AcctItemFacade acctItemFacade = getAcctItemFacade();
		AcctItemDTO acctItemDTO = new AcctItemDTO();
        acctItemDTO.setUserId(110000000007L);
        acctItemDTO.setTotalAmount(50L);
        
        AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
        acctChangeDTO.setRltId(11111L);
        acctChangeDTO.setBusiType("05");
        acctChangeDTO.setIoAmount(acctItemDTO.getTotalAmount());
        acctChangeDTO.setRltChannelId("1");
        acctChangeDTO.setOperId("99999");
        
        Result rs = (Result)acctItemFacade.decVirtualAccountIntegral(acctItemDTO, acctChangeDTO,null);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	
	//虚拟账户金额扣款 使用场景：虚拟账户金额扣款
	private void decVirtualAccountMoneyByItemTypeCode()throws MalformedURLException {
		AcctItemFacade acctItemFacade = getAcctItemFacade();
		AcctItemDTO acctItemDTO = new AcctItemDTO();
        acctItemDTO.setUserId(110000000007L);
        acctItemDTO.setTotalAmount(100L);
        acctItemDTO.setAcctTypeCode("01");
        
        AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
        acctChangeDTO.setRltId(11111L);
        acctChangeDTO.setBusiType("05");
        acctChangeDTO.setIoAmount(acctItemDTO.getTotalAmount());
        acctChangeDTO.setRltChannelId("1");
        acctChangeDTO.setOperId("123");
        
        Result rs = (Result)acctItemFacade.decVirtualAccountMoneyByItemTypeCode(acctItemDTO, acctChangeDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
}
