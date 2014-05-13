package com.xiu.uuc.facade.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiu.commons.core.BaseExternalService;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.common.util.JsonUtils;
import com.xiu.uuc.facade.AddressManageFacade;
import com.xiu.uuc.facade.dto.RcvAddressDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.manager.AddressManager;
import com.xiu.uuc.manager.util.CommonUtils;
import com.xiu.uuc.manager.util.ExceptionProcessor;

public class AddressManageFacadeImpl extends BaseExternalService implements AddressManageFacade {

    @Autowired
    private AddressManager addressManager;
    private static final Logger logger = LoggerFactory.getLogger(AddressManageFacadeImpl.class);
    private JsonUtils jsonUtils = new JsonUtils();

    @Override
    public Result addRcvAddressInfo(RcvAddressDTO rcvAddressDTO) {
        logger.info("addRcvAddressInfo entry: rcvAddressDTO={}", jsonUtils.toJson(rcvAddressDTO));
        long stime = System.currentTimeMillis();

        Result result = null;
        try {
            result = addressManager.addRcvAddressInfo(rcvAddressDTO);
            RcvAddressDTO resultDTO = (RcvAddressDTO) result.getData();
            logger.info("addRcvAddressInfo success: userId={}, rcverName={}, addressId={}, costTime={}ms",
                    new Object[] { rcvAddressDTO.getUserId(), rcvAddressDTO.getRcverName(), resultDTO.getAddressId(),
                            System.currentTimeMillis() - stime });

            return result;
        } catch (ParameterException e) {
            return ExceptionProcessor.getParameterExceptionResult(e);
        } catch (BusinessException e) {
            return ExceptionProcessor.getBusinessExceptionResult(e);
        } catch (Exception e) {
            return ExceptionProcessor.getExceptionResult(e);
        }
    }

    @Override
    public Result deleteRcvAddressInfo(Long addressId) {
        logger.info("deleteRcvAddressInfo entry: addressId={}", addressId);
        long stime = System.currentTimeMillis();

        Result result = null;
        try {
            result = addressManager.deleteRcvAddressInfo(addressId);
            logger.info("deleteRcvAddressInfo success: addressId={}, costTime={}ms",
                    new Object[] { addressId, System.currentTimeMillis() - stime });

            return result;
        } catch (ParameterException e) {
            return ExceptionProcessor.getParameterExceptionResult(e);
        } catch (BusinessException e) {
            return ExceptionProcessor.getBusinessExceptionResult(e);
        } catch (Exception e) {
            return ExceptionProcessor.getExceptionResult(e);
        }
    }

    @Override
    public Result modifyRcvAddressInfo(RcvAddressDTO rcvAddressDTO) {
        logger.info("modifyRcvAddressInfo entry: rcvAddressDTO={}", jsonUtils.toJson(rcvAddressDTO));
        long stime = System.currentTimeMillis();

        Result result = null;
        try {
            result = addressManager.modifyRcvAddressInfo(rcvAddressDTO);
            logger.info("modifyRcvAddressInfo success: addressId={}, costTime={}ms",
                    new Object[] { rcvAddressDTO.getAddressId(), System.currentTimeMillis() - stime });

            return result;
        } catch (ParameterException e) {
            return ExceptionProcessor.getParameterExceptionResult(e);
        } catch (BusinessException e) {
            return ExceptionProcessor.getBusinessExceptionResult(e);
        } catch (Exception e) {
            return ExceptionProcessor.getExceptionResult(e);
        }
    }

    @Override
    public Result modifyRcvAddressMaster(Long addressId) {
        logger.info("modifyRcvAddressMaster entry: addressId={}", addressId);
        long stime = System.currentTimeMillis();

        Result result = null;
        try {
            result = addressManager.modifyRcvAddressMaster(addressId);
            logger.info("modifyRcvAddressMaster success: addressId={}, costTime={}ms",
                    new Object[] { addressId, System.currentTimeMillis() - stime });

            return result;
        } catch (ParameterException e) {
            return ExceptionProcessor.getParameterExceptionResult(e);
        } catch (BusinessException e) {
            return ExceptionProcessor.getBusinessExceptionResult(e);
        } catch (Exception e) {
            return ExceptionProcessor.getExceptionResult(e);
        }
    }

    @Override
    public Result getRcvAddressInfoById(Long addressId) {
        logger.info("getRcvAddressInfoById entry: addressId={}", addressId);
        long stime = System.currentTimeMillis();

        Result result = null;
        try {
            result = addressManager.getRcvAddressInfoById(addressId);
            logger.info("getRcvAddressInfoById success: addressId={}, costTime={}ms",
                    new Object[] { addressId, System.currentTimeMillis() - stime });

            return result;
        } catch (ParameterException e) {
            return ExceptionProcessor.getParameterExceptionResult(e);
        } catch (BusinessException e) {
            return ExceptionProcessor.getBusinessExceptionResult(e);
        } catch (Exception e) {
            return ExceptionProcessor.getExceptionResult(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Result getRcvAddressListByUserId(Long userId) {
        logger.info("getRcvAddressListByUserId entry: userId={}", userId);
        long stime = System.currentTimeMillis();

        Result result = null;
        try {
            result = addressManager.getRcvAddressListByUserId(userId);
            List<RcvAddressDTO> resultList = (List<RcvAddressDTO>) result.getData();
            logger.info(
                    "getRcvAddressListByUserId success: userId={}, count={}, addressId[]={}, costTime={}ms",
                    new Object[] { userId, resultList.size(),
                            CommonUtils.collectByPropertyName(resultList, "addressId"),
                            System.currentTimeMillis() - stime });

            return result;
        } catch (ParameterException e) {
            return ExceptionProcessor.getParameterExceptionResult(e);
        } catch (BusinessException e) {
            return ExceptionProcessor.getBusinessExceptionResult(e);
        } catch (Exception e) {
            return ExceptionProcessor.getExceptionResult(e);
        }
    }

    @Override
    public Result getRcvAddressCountByUserId(Long userId) {
        logger.info("getRcvAddressCountByUserId entry: userId={}", userId);
        long stime = System.currentTimeMillis();

        Result result = null;
        try {
            result = addressManager.getRcvAddressCountByUserId(userId);
            logger.info("getRcvAddressCountByUserId success: userId={}, count={}, costTime={}ms", new Object[] {
                    userId, result.getData(), System.currentTimeMillis() - stime });

            return result;
        } catch (ParameterException e) {
            return ExceptionProcessor.getParameterExceptionResult(e);
        } catch (BusinessException e) {
            return ExceptionProcessor.getBusinessExceptionResult(e);
        } catch (Exception e) {
            return ExceptionProcessor.getExceptionResult(e);
        }
    }

}
