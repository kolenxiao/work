package com.xiu.uuc.manager;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import com.xiu.uuc.facade.dto.BankAcctDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.manager.util.BaseTest;

public class BankAcctManagerImplTest extends BaseTest{
	
	@Test
	@Rollback(false)
	public void testInsertBankAcct() throws Exception {
		BankAcctDTO bankAcctDTO = new BankAcctDTO();
		bankAcctDTO.setCustId(custId);
		bankAcctDTO.setUserId(userId);
		bankAcctDTO.setBankAcctNo("123456");
		bankAcctDTO.setBankAcctName("menglei");
		bankAcctDTO.setBankName("走秀银行"+System.currentTimeMillis());
		bankAcctDTO.setCreateChannelId(1);
		Result result = bankAcctManager.insertBankAcct(bankAcctDTO);
		if(FacadeConstant.SUCCESS.equals(result.getSuccess())){
			bankAcctId = (Long)result.getData();
		}
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	@Rollback(false)
	public void testUpdateBankAcct() throws Exception {
		BankAcctDTO bankAcctDTO = new BankAcctDTO();
		bankAcctDTO.setUserId(userId);
		bankAcctDTO.setBankName("走秀银行x");
		Result result = (Result)bankAcctManager.updateBankAcct(bankAcctDTO);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	public void testListBankAcct() throws Exception {
		BankAcctDTO bankAcctDTO = new BankAcctDTO();
		bankAcctDTO.setUserId(userId);
		Result result = (Result)bankAcctManager.listBankAcct(bankAcctDTO);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}
	
	@Test
	public void testFindBankAcct() throws Exception {
		BankAcctDTO bankAcctDTO = new BankAcctDTO();
		bankAcctDTO.setBankAcctId(bankAcctId);
        Result result = (Result)bankAcctManager.listBankAcct(bankAcctDTO);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}
	
	@Test
	@Rollback(false)
	public void testDeleteBankAcct() throws Exception {
		BankAcctDTO bankAcctDTO = new BankAcctDTO();
		bankAcctDTO.setBankAcctId(bankAcctId);
		Result result = bankAcctManager.deleteBankAcct(bankAcctDTO);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}
	
	private static Long userId = 33L;
	private static Long custId = 29L;
	private static Long bankAcctId = null;

	@Autowired
	private BankAcctManager bankAcctManager;
}
