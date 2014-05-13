package com.xiu.uuc.web.action.Test;

import java.net.MalformedURLException;
import com.caucho.hessian.client.HessianProxyFactory;
import com.xiu.uuc.facade.BankAcctFacade;
import com.xiu.uuc.facade.dto.BankAcctDTO;
import com.xiu.uuc.facade.dto.Result;

public class BankAcctFacadeImplTest {
	
	private static Long bankAcctId = 372L;
	private static Long userId = 958L;

	public static void main(String[] args) throws MalformedURLException {
		BankAcctFacadeImplTest bankAcctFacadeImplTest = new BankAcctFacadeImplTest();
		bankAcctFacadeImplTest.addBankAcctInfo();      //新增用户提现银行账号信息 
		bankAcctFacadeImplTest.updateBankAcctInfo();      //修改用户提现银行帐号信息 
		bankAcctFacadeImplTest.findBankAcctDetailInfo();     //查询特定提现银行账号详情
		bankAcctFacadeImplTest.deleteBankAcctInfo();      //删除用户提现银行帐号信息
		bankAcctFacadeImplTest.getBankAcctListInfo();      //查询用户已有的提现银行账号列表 
	}
	
	private BankAcctFacade getBankAcctFacade(){
		BankAcctFacade bankAcctFacade = null;
		try {
			String url = "http://uuc.xiu.com:8080/remote/bankAcctFacade";
			HessianProxyFactory factory = new HessianProxyFactory();
			bankAcctFacade = (BankAcctFacade) factory.create(BankAcctFacade.class, url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return bankAcctFacade;
	}
	
	//新增用户提现银行账号信息 
	@SuppressWarnings("unused")
	private void addBankAcctInfo() throws MalformedURLException {
		BankAcctFacade bankAcctFacade = getBankAcctFacade();
		BankAcctDTO bankAcctDTO = new BankAcctDTO();
		bankAcctDTO.setUserId(userId);
		bankAcctDTO.setBankAcctNo("123456");
		bankAcctDTO.setBankAcctName("menglei");
		bankAcctDTO.setBankName("走秀银行测试");
		bankAcctDTO.setCreateChannelId(1);
        Result rs = (Result)bankAcctFacade.addBankAcctInfo(bankAcctDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        	bankAcctId = (Long)rs.getData();
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//修改用户提现银行帐号信息 
	@SuppressWarnings("unused")
	private void updateBankAcctInfo() throws MalformedURLException {
		BankAcctFacade bankAcctFacade = getBankAcctFacade();
		BankAcctDTO bankAcctDTO = new BankAcctDTO();
		bankAcctDTO.setBankAcctId(bankAcctId);
		bankAcctDTO.setBankName("modifyBankName");
        Result rs = (Result)bankAcctFacade.updateBankAcctInfo(bankAcctDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//查询特定提现银行账号详情 
	@SuppressWarnings("unused")
	private void findBankAcctDetailInfo() throws MalformedURLException {
		BankAcctFacade bankAcctFacade = getBankAcctFacade();
		BankAcctDTO bankAcctDTO = new BankAcctDTO();
		bankAcctDTO.setBankAcctId(bankAcctId);
        Result rs = (Result)bankAcctFacade.findBankAcctDetailInfo(bankAcctDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//删除用户提现银行帐号信息 
	@SuppressWarnings("unused")
	private void deleteBankAcctInfo() throws MalformedURLException {
		BankAcctFacade bankAcctFacade = getBankAcctFacade();
		BankAcctDTO bankAcctDTO = new BankAcctDTO();
		bankAcctDTO.setBankAcctId(bankAcctId);
        Result rs = (Result)bankAcctFacade.deleteBankAcctInfo(bankAcctDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}
	
	//查询用户已有的提现银行账号列表
	@SuppressWarnings("unused")
	private void getBankAcctListInfo() throws MalformedURLException {
		BankAcctFacade bankAcctFacade = getBankAcctFacade();
		BankAcctDTO bankAcctDTO = new BankAcctDTO();
		//bankAcctDTO.setUserId(100000000225L);
		//bankAcctDTO.setBankName("走秀银行x");
		//bankAcctDTO.setBankAcctName("3");
        Result rs = (Result)bankAcctFacade.getBankAcctListInfo(bankAcctDTO);
        if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
	}

}
