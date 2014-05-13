package com.xiu.uuc.web.action;

import java.net.MalformedURLException;
import java.net.URL;
import com.caucho.hessian.client.HessianProxyFactory;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.xiu.uuc.facade.AcctChangeFacade;
import com.xiu.uuc.facade.AcctItemFacade;
import com.xiu.uuc.facade.BankAcctFacade;
import com.xiu.uuc.facade.UserManageFacade;
import com.xiu.uuc.facade.dto.AcctChangeDTO;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.facade.dto.BankAcctDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserAcctDTO;

public class TestHessian {

	public static void main(String[] args) throws Throwable {
		TestHessian testHessian = new TestHessian();
		//Result rs = testHessian.addVirtualAccountMoney();      //虚拟账户金额充值 使用场景：虚拟账户金额充值
		//Result rs = testHessian.decVirtualAccountMoney();      //虚拟账户金额支付 使用场景：虚拟账户金额支付
		//Result rs = testHessian.setUserAcctItemFreezeMoney();  //修改当前账目冻结金额(主要是可提现，不可提现账目冻结金额缺省为0)
		//Result rs = testHessian.setUserAcctFreeze();           //设置用户账号冻结
		//Result rs = testHessian.getVirtualAccountInfo();         //虚拟帐户基本信息（账目）包括：账户总计 可提现 不可提现 冻结
		//Result rs = testHessian.getBankAcctListInfo();        //查询用户已有的提现银行账号列表 
		//Result rs = testHessian.updateBankAcctInfo();      //编辑用户提现银行账号帐号信息 
		Result rs = testHessian.bbb();
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	@SuppressWarnings("unused")
	private Result aaa() throws MalformedURLException {
//		String url = "http://192.168.50.39:8080/uuc/remote/addressManageFacade"; 
//        HessianProxyFactory factory = new HessianProxyFactory(); 
//        AddressManageFacade facade = (AddressManageFacade) factory.create(AddressManageFacade.class, url);
//        Result rs = (Result)facade.getRcvAddressInfoById(110000000003l);
		
		//String url = "http://192.168.50.39:8080/uuc/remote/userManageFacade"; 
		//String url = "http://192.168.80.168:8183/uuc-web/remote/userManageFacade"; 
		String url = "http://192.168.50.39:8080/uuc/remote/userManageFacade";
		
        HessianProxyFactory factory = new HessianProxyFactory(); 
        UserManageFacade facade = (UserManageFacade) factory.create(UserManageFacade.class, url);
        //Result rs = (Result)facade.modifyUserPassword(210l, "96e79218965eb72c92a549dd5a330112", "123456");
        Result rs = (Result)facade.getPasswordByLogonNameAndChannelId("k009@kk.com", 2);
        System.out.println("getSuccess==="+rs.getSuccess());
        System.out.println("getSuccess==="+rs.getData());
        return rs;
	}
	
	private Result bbb() throws Throwable {
		String url = "http://192.168.50.39:8080/uuc/remote/userManageFacade.json"; 
		
		JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(url));
		
		Object[] params = {"k009@kk.com", 2};
		Result rs = (Result)client.invoke("getPasswordByLogonNameAndChannelId", params, Result.class);

        System.out.println("getSuccess==="+rs.getSuccess());
        System.out.println("getSuccessbbbbbb==="+rs.getData());
        return rs;
	}
	
	//虚拟账户金额充值 使用场景：虚拟账户金额充值（可提现或者不可提现）
	@SuppressWarnings("unused")
	private Result addVirtualAccountMoney() throws MalformedURLException {
		String url = "http://192.168.50.71:8080/uuc/remote/acctItemFacade"; 
        HessianProxyFactory factory = new HessianProxyFactory(); 
        AcctItemFacade acctItemFacade = (AcctItemFacade) factory.create(AcctItemFacade.class, url);
        AcctItemDTO acctItemDTO = new AcctItemDTO();
        
        acctItemDTO.setUserId(100000000225L);
        acctItemDTO.setAcctTypeCode("01");
        acctItemDTO.setTotalAmount(3000L);
        
        AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
        acctChangeDTO.setAcctItemId(100000000292L);
        acctChangeDTO.setRltId(11111L);
        acctChangeDTO.setBusiType("01");
        acctChangeDTO.setIoAmount(acctItemDTO.getTotalAmount());
        acctChangeDTO.setRltChannelId("1");
        acctChangeDTO.setOperId("999");
        
        Result rs = (Result)acctItemFacade.addVirtualAccountMoney(acctItemDTO, acctChangeDTO);
        System.out.println("acctItemFacade==="+acctItemFacade);
        return rs;
	}
	
	
	//虚拟账户金额支付 使用场景：虚拟账户金额支付 
	@SuppressWarnings("unused")
	private Result decVirtualAccountMoney() throws MalformedURLException {
		String url = "http://localhost:8080/uuc/remote/acctItemFacade"; 
        HessianProxyFactory factory = new HessianProxyFactory(); 
        AcctItemFacade acctItemFacade = (AcctItemFacade) factory.create(AcctItemFacade.class, url);
        AcctItemDTO acctItemDTO = new AcctItemDTO();
        
        acctItemDTO.setUserId(100000000210L);
        acctItemDTO.setTotalAmount(200L);
        
        AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
        acctChangeDTO.setRltId(11111L);
        acctChangeDTO.setBusiType("02");
        acctChangeDTO.setRltChannelId("1");
        acctChangeDTO.setOperId("666");
        
        Result rs = (Result)acctItemFacade.decVirtualAccountMoney(acctItemDTO, acctChangeDTO);
        System.out.println("acctItemFacade==="+acctItemFacade);
        return rs;
	}
	
	
	//修改当前账目冻结金额(主要是可提现，不可提现账目冻结金额缺省为0)
	@SuppressWarnings("unused")
	private Result setUserAcctItemFreezeMoney() throws MalformedURLException {
		String url = "http://localhost:8080/uuc/remote/acctChangeFacade"; 
        HessianProxyFactory factory = new HessianProxyFactory(); 
        AcctChangeFacade acctChangeFacade = (AcctChangeFacade) factory.create(AcctChangeFacade.class, url);
        AcctItemDTO acctItemDTO = new AcctItemDTO();
        
        acctItemDTO.setAcctItemId(100000000250L);
        acctItemDTO.setAcctTypeCode("01");
        acctItemDTO.setFreezeAmount(300L);
        
        AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
        acctChangeDTO.setAcctItemId(100000000250L);
        acctChangeDTO.setRltId(11111L);
        acctChangeDTO.setBusiType("03");
        acctChangeDTO.setIoType("01");
        acctChangeDTO.setIoAmount(acctItemDTO.getFreezeAmount());
        acctChangeDTO.setRltChannelId("1");
        acctChangeDTO.setOperId("888");
        
        Result rs = (Result)acctChangeFacade.setUserAcctItemFreezeMoney(acctItemDTO, acctChangeDTO);
        System.out.println("acctChangeFacade==="+acctChangeFacade);
        return rs;
	}
	
	//设置用户账号冻结
	@SuppressWarnings("unused")
	private Result setUserAcctFreeze() throws MalformedURLException {
		String url = "http://localhost:8080/uuc/remote/acctChangeFacade"; 
        HessianProxyFactory factory = new HessianProxyFactory(); 
        AcctChangeFacade acctChangeFacade = (AcctChangeFacade) factory.create(AcctChangeFacade.class, url);
        UserAcctDTO userAcctDTO = new UserAcctDTO();
        userAcctDTO.setUserId(100000000214L);
        Result rs = (Result)acctChangeFacade.setUserAcctFreeze(userAcctDTO);
        System.out.println("acctChangeFacade==="+acctChangeFacade);
        return rs;
	}
	
	//虚拟帐户基本信息（账目）包括：账户总计 可提现 不可提现 冻结
	@SuppressWarnings("unused")
	private Result getVirtualAccountInfo() throws MalformedURLException {
		String url = "http://192.168.50.71:8080/uuc/remote/acctItemFacade"; 
        HessianProxyFactory factory = new HessianProxyFactory(); 
        AcctItemFacade acctItemFacade = (AcctItemFacade) factory.create(AcctItemFacade.class, url);
        UserAcctDTO userAcctDTO = new UserAcctDTO();
        userAcctDTO.setUserId(100000000210L);
        //Result rs = (Result)acctItemFacade.getVirtualAccountInfo(userAcctDTO);
        Result rs= null;
        System.out.println("acctItemFacade==="+acctItemFacade);
        return rs;
	}
	
	//查询用户已有的提现银行账号列表 
	@SuppressWarnings("unused")
	private Result getBankAcctListInfo() throws MalformedURLException {
		String url = "http://192.168.50.71:8080/uuc/remote/bankAcctFacade"; 
        HessianProxyFactory factory = new HessianProxyFactory(); 
        BankAcctFacade bankAcctFacade = (BankAcctFacade) factory.create(BankAcctFacade.class, url);
        BankAcctDTO bankAcctDTO = new BankAcctDTO();
        bankAcctDTO.setUserId(100000000225L);
        Result rs = (Result)bankAcctFacade.getBankAcctListInfo(bankAcctDTO);
        System.out.println("bankAcctFacade==="+bankAcctFacade);
        return rs;
   }
	
	//编辑用户提现银行账号帐号信息 
	@SuppressWarnings("unused")
	private Result updateBankAcctInfo() throws MalformedURLException {
		String url = "http://192.168.50.71:8080/uuc/remote/bankAcctFacade"; 
        HessianProxyFactory factory = new HessianProxyFactory(); 
        BankAcctFacade bankAcctFacade = (BankAcctFacade) factory.create(BankAcctFacade.class, url);
        BankAcctDTO bankAcctDTO = new BankAcctDTO();
        bankAcctDTO.setBankAcctId(60L);
        bankAcctDTO.setBankAcctNo("8888888");
        bankAcctDTO.setBankAcctName("menglei");
        Result rs = (Result)bankAcctFacade.updateBankAcctInfo(bankAcctDTO);
        System.out.println("bankAcctFacade==="+bankAcctFacade);
        return rs;
   }

}
