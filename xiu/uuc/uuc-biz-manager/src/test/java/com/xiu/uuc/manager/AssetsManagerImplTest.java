package com.xiu.uuc.manager;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import com.xiu.uuc.common.util.KeyNames;
import com.xiu.uuc.facade.dto.AcctChangeDTO;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.facade.dto.IntegeralRuleDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserAcctDTO;
import com.xiu.uuc.facade.dto.VirtualAcctItemDTO;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.facade.util.TypeEnum;
import com.xiu.uuc.manager.util.BaseTest;

public class AssetsManagerImplTest extends BaseTest{

	@Test
	public void testGetAcctChangeDetailList() throws Exception {
		AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
		acctChangeDTO.setUserId(userId);
		Result result = assetsManager.getAcctChangeDetailList(acctChangeDTO);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	public void testGetAcctItemDetailList() throws Exception {
		AcctItemDTO acctItemDTO = new AcctItemDTO();
		acctItemDTO.setUserId(userId);
		acctItemDTO.setAcctTypeCode(TypeEnum.AcctItemType.WITHDRAWAL_YES.getKey());
		Result result = assetsManager.getAcctItemDetailList(acctItemDTO);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	@Rollback(false)
	public void testSetUserAcctFreezeOrUnFreeze() throws Exception {
		Result result;
		UserAcctDTO userAcctDTO = new UserAcctDTO();
		userAcctDTO.setUserId(userId);
		result = assetsManager.setUserAcctFreezeOrUnFreeze(userAcctDTO, operatorFlag_Freeze);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
		result = assetsManager.setUserAcctFreezeOrUnFreeze(userAcctDTO, operatorFlag_unFreeze);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}
	
	@Test
	public void testCheckIsFreezeByUserAcct() throws Exception {
		Result result;
		UserAcctDTO userAcctDTO = new UserAcctDTO();
		userAcctDTO.setUserId(userId);
		result = assetsManager.checkIsFreezeByUserAcct(userAcctDTO);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}
	
	@Test
	@Rollback(false)
	public void testSetUserAcctItemFreezeMoney() throws Exception {
		Result result;
		AcctItemDTO acctItemDTO = new AcctItemDTO();
        acctItemDTO.setUserId(userId);
        acctItemDTO.setAcctTypeCode("01");
        acctItemDTO.setFreezeAmount(1L);
        acctItemDTO.setOnlyClearZero("0");
        acctItemDTO.setOperType("02");
        
        AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
        acctChangeDTO.setRltId(1111L);
        acctChangeDTO.setBusiType("01");
        acctChangeDTO.setIoAmount(acctItemDTO.getFreezeAmount());
        acctChangeDTO.setRltChannelId("1");
		
		result = assetsManager.setUserAcctItemFreezeMoney(acctItemDTO, acctChangeDTO);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}
	
	@Test
	public void testGetVirtualAccountInfo() throws Exception {
		Result result;
		VirtualAcctItemDTO virtualAcctItemDTO = new VirtualAcctItemDTO();
		result = assetsManager.getVirtualAccountInfo(virtualAcctItemDTO);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}
	
	@Test
	@Rollback(false)
	public void testModifyVirtualAccountMoneyOrIntegral() throws Exception {
		Result result;
		AcctItemDTO acctItemDTO = new AcctItemDTO();
        acctItemDTO.setUserId(userId);
        acctItemDTO.setAcctTypeCode("01");
        acctItemDTO.setTotalAmount(500L);
        
        AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
        acctChangeDTO.setRltId(1111L);
        acctChangeDTO.setBusiType("01");
        acctChangeDTO.setIoAmount(acctItemDTO.getTotalAmount());
        acctChangeDTO.setRltChannelId("1");
        
        @SuppressWarnings("unused")
		IntegeralRuleDTO integeralRuleDTO = new IntegeralRuleDTO();
		result = assetsManager.modifyVirtualAccountMoneyOrIntegral(acctItemDTO,acctChangeDTO, null, acctItemIoTypeFlag_In,operatorFlag_Money);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}
	
	@Test
	@Rollback(false)
	public void testDecVirtualAccountMoneyByItemTypeCode() throws Exception {
		Result result;
		AcctItemDTO acctItemDTO = new AcctItemDTO();
        acctItemDTO.setUserId(userId);
        acctItemDTO.setTotalAmount(100L);
        acctItemDTO.setAcctTypeCode("01");
        
        AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
        acctChangeDTO.setRltId(1111L);
        acctChangeDTO.setBusiType("05");
        acctChangeDTO.setIoAmount(acctItemDTO.getTotalAmount());
        acctChangeDTO.setRltChannelId("1");
        acctChangeDTO.setOperId("123");
		result = assetsManager.decVirtualAccountMoneyByItemTypeCode(acctItemDTO, acctChangeDTO);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetIntegeralByRule() throws Exception {
		Result result;
		IntegeralRuleDTO integeralRuleDTO = new IntegeralRuleDTO();
		integeralRuleDTO.setCreatePointCode(createPointCode);
		Map map = new HashMap();
		map.put("p1", 1);
		map.put("p2", 1);
		integeralRuleDTO.setMap(map);
		result = assetsManager.getIntegeralByRule(integeralRuleDTO);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Autowired
	private AssetsManager assetsManager;
	private static Long userId = 1018L;                                                     //用户ID
	private static String operatorFlag_Freeze = KeyNames.ACCT_STATE_FROZEN;                //用户账户冻结
	private static String operatorFlag_unFreeze = KeyNames.ACCT_STATE_NATURAL;             //用户账户解冻
	private static String acctItemIoTypeFlag_In = KeyNames.ACCT_ITEM_IO_TYPE_IN;           //账目进出类型 ：入账
	@SuppressWarnings("unused")
	private static String acctItemIoTypeFlag_Out = KeyNames.ACCT_ITEM_IO_TYPE_OUT;         //账目进出类型 ：出账
	private static String operatorFlag_Money = KeyNames.ACCT_ITEM_OPERATER_MONEY;          //账目操作类型：对可提现或者不可提现账目进行金额操作
	@SuppressWarnings("unused")
	private static String operatorFlag_Integral = KeyNames.ACCT_ITEM_OPERATER_INTEGRAL;    //账目操作类型：对积分账目进行积分操作	
	private static String createPointCode = "001";                                            //积分规则创建点码
}
