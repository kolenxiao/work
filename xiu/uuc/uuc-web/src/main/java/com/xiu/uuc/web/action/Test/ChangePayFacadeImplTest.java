package com.xiu.uuc.web.action.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import com.caucho.hessian.client.HessianProxyFactory;
import com.xiu.uuc.facade.ChangePayFacade;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.TractDTO;
import com.xiu.uuc.facade.dto.TractQueryDTO;
import com.xiu.uuc.facade.dto.TradeDTO;

public class ChangePayFacadeImplTest {
	
	public static void main(String[] args) throws MalformedURLException {
		ChangePayFacadeImplTest changePayFacadeImplTest = new ChangePayFacadeImplTest();
		changePayFacadeImplTest.incomeAccounts();                  //退货退款入账接口
		changePayFacadeImplTest.outcomeAccounts();                 //换货扣款出账接口
		changePayFacadeImplTest.getTractRefundDetailList();        //查询老订单入虚拟帐户明细接口
		changePayFacadeImplTest.getTractRefundNotEndDetailList();  //查询未完结退款列表接口
	}
	
	private ChangePayFacade getChangePayFacade(){
		ChangePayFacade changePayFacade = null;
		try {
			String url = "http://uuc.xiu.com:8080/remote/changePayFacade";
			HessianProxyFactory factory = new HessianProxyFactory();
			changePayFacade = (ChangePayFacade) factory.create(ChangePayFacade.class, url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return changePayFacade;
	}
	
	//退货退款入账接口
	private void incomeAccounts() throws MalformedURLException {
		
		ChangePayFacade changePayFacade = getChangePayFacade();
		
		TradeDTO tradeDTO = new TradeDTO();
		tradeDTO.setRltId(10L);
		tradeDTO.setRltCode("10");
		tradeDTO.setRltSeq("rltseq10");
		tradeDTO.setTotalAmount(900L);
		tradeDTO.setRltChannelId("11");
		//tradeDTO.setBusiType("va1107");
		//tradeDTO.setAcctTypeCode("04");
		tradeDTO.setOperId("andy");
		//tradeDTO.setIoType("01");
		tradeDTO.setOperMode("1");
		tradeDTO.setUserId(110000000007L);
		
		TractDTO tractDTO1 = new TractDTO();
		tractDTO1.setIoAmount(900L);
		//tractDTO1.setIoType("01");
		tractDTO1.setPayMode("虚拟账户");
		tractDTO1.setRefundId("refundId10");
		tractDTO1.setOldRltId(10L);
		tractDTO1.setResCode("1");
		tractDTO1.setRltSeq("rltseq1");
		
		/*TractDTO tractDTO2 = new TractDTO();
		tractDTO2.setIoAmount(100L);
		//tractDTO2.setIoType("01");
		tractDTO2.setPayMode("招商银行");
		tractDTO2.setRefundId("refundId20");
		tractDTO2.setOldRltId(10L);*/
	
		List<TractDTO> tractDTOList = new ArrayList<TractDTO>();
		tractDTOList.add(tractDTO1);
		//tractDTOList.add(tractDTO2);
		tradeDTO.setTractDTOList(tractDTOList);
		
        Result rs = (Result)changePayFacade.incomeAccounts(tradeDTO);
        if ("1".equals(rs.getSuccess())) {
        	Long rltId = (Long)rs.getData();
        	System.out.println("退货退款老订单ID="+rltId);
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
       
	}
	
	//换货扣款出账接口
	@SuppressWarnings("unused")
	private void outcomeAccounts() throws MalformedURLException {
		
		ChangePayFacade changePayFacade = getChangePayFacade();
		
		TradeDTO tradeDTO = new TradeDTO();
		tradeDTO.setRltId(10L);
		tradeDTO.setRltCode("10");
		tradeDTO.setRltSeq("rltseq10");
		tradeDTO.setTotalAmount(1L);
		tradeDTO.setRltChannelId("11");
		//tradeDTO.setBusiType("va1107");
		//tradeDTO.setAcctTypeCode("04");
		tradeDTO.setOperId("andy");
		//tradeDTO.setIoType("02");
		tradeDTO.setOperMode("1");
		tradeDTO.setUserId(110000000007L);
		
		TractDTO tractDTO1 = new TractDTO();
		tractDTO1.setIoAmount(1L);
		//tractDTO1.setIoType("02");
		tractDTO1.setPayMode("虚拟账户");
		tractDTO1.setRefundId("refundId10");
		tractDTO1.setOldRltId(10L);
		tractDTO1.setResCode("1");
		tractDTO1.setRltSeq("rltseq1");
		tractDTO1.setNewRltId(2L);
		
		/*TractDTO tractDTO2 = new TractDTO();
		tractDTO2.setIoAmount(1L);
		//tractDTO2.setIoType("02");
		tractDTO2.setPayMode("虚拟账户");
		tractDTO2.setRefundId("refundId10");
		tractDTO2.setOldRltId(10L);
		tractDTO1.setRltSeq("rltseq1");
		tractDTO2.setNewRltId(2L);*/
		
		List<TractDTO> tractDTOList = new ArrayList<TractDTO>();
		tractDTOList.add(tractDTO1);
		//tractDTOList.add(tractDTO2);
		tradeDTO.setTractDTOList(tractDTOList);
		
        Result rs = (Result)changePayFacade.outcomeAccounts(tradeDTO);
        if ("1".equals(rs.getSuccess())) {
        	TradeDTO tradeDTOResult = (TradeDTO)rs.getData();
        	System.out.println("换货扣款出账参数对象tradeDTOResult.toString()="+tradeDTOResult.toString());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
        
	}
	
	//查询老订单入虚拟帐户明细接口
	@SuppressWarnings({ "unused", "unchecked" })
	private void getTractRefundDetailList() throws MalformedURLException {
		
		ChangePayFacade changePayFacade = getChangePayFacade();
		
		TractQueryDTO tractQueryDTO = new TractQueryDTO();
		tractQueryDTO.setOldRltId(1L);
		//tractQueryDTO.setRefundId("refundId1");
		
        Result rs = (Result)changePayFacade.getTractRefundDetailList(tractQueryDTO);
        if ("1".equals(rs.getSuccess())) {
        	List<TractDTO> tractDTOList = (List<TractDTO>)rs.getData();
        	for(TractDTO tractDTO : tractDTOList){
        		System.out.println("查询老订单入虚拟帐户明细tractDTO="+tractDTO.toString());	
        	}
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
        
	}
	
	//查询未完结退款列表接口
	@SuppressWarnings({ "unused", "unchecked" })
	private void getTractRefundNotEndDetailList() throws MalformedURLException {
		
		ChangePayFacade changePayFacade = getChangePayFacade();
		
		TractQueryDTO tractQueryDTO = new TractQueryDTO();
		tractQueryDTO.setOldRltId(4000947L);
		//tractQueryDTO.setRefundId("100");
		//tractQueryDTO.setBegTime(new Date());
		//tractQueryDTO.setEndTime(new Date());
		
        Result rs = (Result)changePayFacade.getTractRefundNotEndDetailList(tractQueryDTO);
        if ("1".equals(rs.getSuccess())) {
        	List<TractDTO> tractDTOList = (List<TractDTO>)rs.getData();
        	for(TractDTO tractDTO : tractDTOList){
        		System.out.println("查询未完结退款列表tractDTO="+tractDTO.toString());	
        	}
        }if(rs.getSuccess().equals("1")){
        	System.out.println("成功:"+rs.getData());
        }else{
        	System.out.println("失败:"+rs.getErrorMessage());
        }
        
	}

}
