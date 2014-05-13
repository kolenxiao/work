package com.xiu.uuc.manager;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.xiu.uuc.facade.dto.RcvAddressDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.facade.util.TypeEnum;
import com.xiu.uuc.manager.util.BaseTest;

public class AddressManagerImplTest extends BaseTest {
	
	private static Long userId = 338l;
	private static Long addressId = null;

	@Autowired
	private AddressManager addressManager;

	@Test
	@Rollback(false)
	public void testAddRcvAddressInfo() throws Exception {
		RcvAddressDTO rcvAddressDTO = new RcvAddressDTO();
		rcvAddressDTO.setUserId(userId);
		rcvAddressDTO.setCreateChannelId(TypeEnum.ChannelType.XIU_OFFICIAL.getKey());
		rcvAddressDTO.setProvinceCode("16");
		rcvAddressDTO.setRegionCode("1602");
		rcvAddressDTO.setCityCode("160207");
		rcvAddressDTO.setAddressInfo("福田区车公庙");
		rcvAddressDTO.setIsMaster("N");
		rcvAddressDTO.setRcverName("我的名字是："+System.currentTimeMillis());
		Result result = addressManager.addRcvAddressInfo(rcvAddressDTO);
		if(FacadeConstant.SUCCESS.equals(result.getSuccess())){
			RcvAddressDTO dto = (RcvAddressDTO)result.getData();
			addressId = dto.getAddressId();
		}
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	public void testModifyRcvAddressInfo() throws Exception {
		RcvAddressDTO rcvAddressDTO = new RcvAddressDTO();
		rcvAddressDTO.setAddressId(addressId);
		rcvAddressDTO.setProvinceCode("16");
		rcvAddressDTO.setRegionCode("1602");
		rcvAddressDTO.setCityCode("160207");
		rcvAddressDTO.setAddressInfo("福田区车公庙");
		rcvAddressDTO.setIsMaster("N");
		rcvAddressDTO.setRcverName("我的名字是："+System.currentTimeMillis());
		Result result = addressManager.modifyRcvAddressInfo(rcvAddressDTO);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	public void testModifyRcvAddressMaster() throws Exception {
		Result result = addressManager.modifyRcvAddressMaster(addressId);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	public void testDeleteRcvAddressInfo() throws Exception {
		Result result = addressManager.deleteRcvAddressInfo(addressId);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	public void testGetRcvAddressInfoById() throws Exception {
		Result result = addressManager.getRcvAddressInfoById(addressId);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	public void testGetRcvAddressListByUserId() throws Exception {
		Result result = addressManager.getRcvAddressListByUserId(userId);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	public void testGetRcvAddressCountByUserId() {
		Result result = addressManager.getRcvAddressCountByUserId(userId);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

}
