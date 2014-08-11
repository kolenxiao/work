/*
 * 文件名称：DpStaffAction.java




 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：luhuanwen/905323
 * 创建时间：2011-9-1
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.action.ap;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.exception.ServiceException;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.sce.dp.ap.entity.DpStaff;
import com.coship.sdp.sce.dp.ap.service.DpStaffService;
import com.coship.sdp.sce.dp.audit.entity.DpAuditRecord;
import com.coship.sdp.sce.dp.audit.service.DpAuditRecordService;
import com.coship.sdp.sce.dp.common.AuditRecordTypeConstants;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 实现对AP(开发者)信息的管理，包括查询所有的AP信息列表，待审的AP信息列表，删除AP， 重置ap密码，查看AP信息，,审核开发者信息
 *
 * @author luhuanwen/905323
 * @version [版本号, 2011-9-1]
 * @since [产品/模块版本]
 */
public class DpStaffAction extends BaseAction {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = -6819749400375865813L;

    /**
     * 日志对象.
     */
    private OpLoggerService opLoggerService;

    /**
     * 模块标识
     */
    private static final String MODULE = DpStaffAction.class.getName();

    /**
     * 重置密码错误资源信息key
     */
    private static final String RESET_PASSWORD_ERROR = "sdp.sce.dp.admin.dpstaff.resetpwd.error";

    /**
     * 查询开发者列表资源信息key
     */
    private static final String QUERY_MSG_ERROR = "sdp.sce.dp.admin.dpstaff.queryData.error";

    /**
     * 列出所有开发者信息列表标识
     */
    private static final int LIST_ALL_STAFF_FLAG = 0;

    /**
     * 用户对象,存放用户信息
     */
    private User user;

    /**
     * 审核意见列表
     */
    private List<DpAuditRecord> list;

    /**
     * 分页集合类
     */
    private Page<DpStaff> page;

    /**
     * 用户id
     */
    private String id = null;

    /**
     * 要删除的dpStaff对象的id集合
     */
    private String ids;

    /**
     * AP实体
     */
    private DpStaff dpStaff;

    /**
     * 审核记录实体
     */
    private DpAuditRecord dpAuditRecord;

    /**
     * 标记查询,0查询所有的AP信息，1查询待审核的AP信息
     */
    private int flag;

    /**
     * 页面传入跳转标示参数
     */
    private String forward;

    /**
     * ap服务类
     */
    private DpStaffService dpStaffService;

    /**
     * ap审核服务类
     */
    private DpAuditRecordService dpAuditRecordService;

    /**
     * 分类管理接口
     */
    private DpTypeService dpTypeService;

    /**
     * 跳转到开发者列表页面 flag=0查询所有AP flag=1查询待审核的AP
     * @return 跳转页面结果字符串
     */
    public String doList() {
        Debug.logVerbose("DpStaffAction.doList() start...");

        // 封装查询条件
        if (null == dpStaff) {
            dpStaff = new DpStaff();
        }

        // 分页数据封装
        this.page = new Page<DpStaff>();
        page.setPageSize(this.limit);
        page.setCurrentPage(this.start);

        try {
            // 获取分页数据
            page = dpStaffService.searchDpStaff(page, dpStaff, flag);

            if (flag == DpStaffAction.LIST_ALL_STAFF_FLAG) {
                // 跳转到所有状态的开发者列表
                return "dpStaffList";
            } else {
                // 跳转到待审核的开发者列表
                return "dpStaffWaitList";
            }

        } catch (Exception e) {
            Debug.logError(e, "doList()" + e.getMessage(), MODULE);

            // 国际化的异常信息
            exception_msg = getText(QUERY_MSG_ERROR);
            return ERROR;
        }
    }

    /**
     * 重置开发者密码
     * @return 重置结果页面字符串
     */
    public String passwordReset() {
        Debug.logVerbose("DpStaffAction.passwordReset() start...");
        this.user = (User) request.getSession().getAttribute("user");
        try {
            DpStaff dpStaffTemp = dpStaffService.findDpStaff(id);
            DpStaff dpStaffDto = new DpStaff();
            BeanUtils.copyProperties(dpStaffTemp, dpStaffDto);
            if (null != dpStaffTemp) {
                // 生成随机密码
                String resetPwd = UUID.randomUUID().toString();
                dpStaffDto.setPassWord(resetPwd);

                // 执行重置密码逻辑
                dpStaffService.resetDpStaffPwd(dpStaffDto);

                // 日志参数
                List<String> logParamList = initLogParame(user.getUserName(),
                        "sdp.sce.dp.admin.dpstaffinfo",
                        "sdp.sce.dp.admin.log.reset.password", user.getId());
                opLoggerService.info(this
                        .getText(Constants.APINFOMATION_MANAGEMENT), this
                        .getText("sdp.sce.dp.admin.log.dpstaff.operate.log",
                                logParamList), this
                        .getText("sdp.sce.dp.admin.log.reset.password"));

                // 返回成功页面
                this.setResult("success", true);
            }
        } catch (Exception e) {
            Debug.logError(e, "passwordReset()" + e.getMessage(), MODULE);
            this.setResult("exception", true);
            exception_msg = getText(RESET_PASSWORD_ERROR);
            this.setResult("exception_msg", exception_msg);
        }
        this.write(JSONObject.fromObject(this.getResult()).toString());
        Debug.logVerbose("DpStaffAction.passwordReset() end...");
        return null;
    }

    /**
     * 跳转到查看和审核Ap信息页面
     * @return 重置结果页面字符串
     */
    public String doDisplay() {
        Debug.logVerbose("DpStaffAction.doDisplay() start...");
        String returnPath = "dpStaffDetail";
        Debug.logVerbose("doDisplay start dpStaffId is " + id);
        try {
            // 获取开发者信息
            this.dpStaff = dpStaffService.findDpStaff(id);

            // 支付信息未填写
            String bankTypeId = dpStaff.getBankName();
            DpType dpType = null;
            if (StringUtils.isNotEmpty(bankTypeId)) {
                dpType = dpTypeService.findType(bankTypeId);
            }

            if (dpType != null) {
                dpStaff.setSaveBankName(dpType.getTypeName());
            }
            // 查询审核信息
            list = dpAuditRecordService.listAuditRecord(id,
                    AuditRecordTypeConstants.DP_AUDIT_RECORD_TYPE);

            // 如果是审核，跳转到审核页面
            if ("audit".equals(forward)) {
                returnPath = "dpStaffDeal";
            }
        } catch (Exception e) {
            Debug.logError(e, "doDisplay()" + e.getMessage(), MODULE);
        }
        Debug.logVerbose("DpStaffAction.doDisplay() end...");
        return returnPath;
    }

    /**
     * 更新AP的审核状态以及将审核记录保存到数据库
     *
     * @return
     * @throws Exception
     */
    public String doModify() {
        Debug.logVerbose("DpStaffAction.doModify start...");
        this.user = (User) request.getSession().getAttribute("user");

        dpAuditRecord.setAuditDate(new Date());
        dpAuditRecord
                .setAuditFlag(AuditRecordTypeConstants.DP_AUDIT_RECORD_TYPE);
        dpAuditRecord.setAuditRecordId(id);
        dpAuditRecord.setAssessor(user.getUserName());
        dpAuditRecord.setAuditResult(dpStaff.getStaffStatus());

        dpStaff.setId(id);
        try {

            // 审核开发者信息
            dpStaffService.auditDpStaffAndSaveRecore(dpStaff, dpAuditRecord);

            // 日志参数
            List<String> logParamList = initLogParame(user.getUserName(),
                    "sdp.sce.dp.admin.dpstaffinfo",
                    "sdp.sce.dp.admin.log.audit.operate", dpStaff.getUserName());
            opLoggerService.info(this
                    .getText(Constants.APINFOMATION_MANAGEMENT), this.getText(
                    "sdp.sce.dp.admin.log.dpstaff.audit.operate.log",
                    logParamList), this
                    .getText("sdp.sce.dp.admin.log.audit.operate"));
        } catch (Exception e) {
            Debug.logError(e, "doModify()" + e.getMessage(), MODULE);
            exception_msg = getText(RESET_PASSWORD_ERROR);
        }

        // 跳转到列表页面,将查询条件对象置空
        dpStaff = null;
        Debug.logVerbose("DpStaffAction.doModify end...");

        return doList();
    }

    /**
     * 根据ids删除对应的dpStaff
     *
     * @return
     * @throws Exception
     */
    public String doDelete() {
        Debug.logVerbose("doDelete start...", MODULE);
        try {
            this.user = (User) request.getSession().getAttribute("user");

            // 删除开发者信息
            this.dpStaffService.deleteDpStaffByIds(ids);

            // 日志参数
            List<String> logParamList = initLogParame(user.getUserName(),
                    "sdp.sce.dp.admin.dpstaffinfo",
                    "sdp.sce.dp.admin.log.delete.operate", ids);

            opLoggerService.info(this
                    .getText(Constants.APINFOMATION_MANAGEMENT), this.getText(
                    "sdp.sce.dp.admin.log.dpstaff.operate.log", logParamList),
                    this.getText("sdp.sce.dp.admin.log.delete.operate"));
            this.setResult("success", true);
        } catch (ServiceException e) {
            this.setResult("success", false);
            this.setResult("msg", getText("sdp.sce.dp.admin.dpstaff.user")+e.getMessage()
                    +getText(Constants.WARNING_DPSTAFF_BIND_APPINFO));
            Debug.logWarning(e, e.getMessage()
                    +getText(Constants.WARNING_DPSTAFF_BIND_APPINFO), MODULE);
        } catch (Exception e) {
            this.setResult("success", false);
            this.setResult("msg", this
                    .getText("sdp.sce.dp.admin.dpstaff.dpstaff.bind.dpappinfo"));
            Debug.logError(
                    this.getText("sdp.sce.dp.admin.dpstaff.dpstaff.bind.dpappinfo"),
                    MODULE);
            Debug.logError(e, e.getMessage(), MODULE);
        }

        // 返回json格式字符串
        this.write(JSONObject.fromObject(this.getResult()).toString());
        return null;
    }

    public List<DpAuditRecord> getList() {
        return list;
    }

    public void setList(List<DpAuditRecord> list) {
        this.list = list;
    }

    public OpLoggerService getOpLoggerService() {
        return opLoggerService;
    }

    public void setOpLoggerService(OpLoggerService opLoggerService) {
        this.opLoggerService = opLoggerService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getIds() {
        return ids;
    }

    public Page<DpStaff> getPage() {
        return page;
    }

    public void setPage(Page<DpStaff> page) {
        this.page = page;
    }

    public DpStaff getDpStaff() {
        return dpStaff;
    }

    public void setDpStaff(DpStaff dpStaff) {
        this.dpStaff = dpStaff;
    }

    public DpAuditRecord getDpAuditRecord() {
        return dpAuditRecord;
    }

    public void setDpAuditRecord(DpAuditRecord dpAuditRecord) {
        this.dpAuditRecord = dpAuditRecord;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public void setDpStaffService(DpStaffService dpStaffService) {
        this.dpStaffService = dpStaffService;
    }

    public void setDpAuditRecordService(
            DpAuditRecordService dpAuditRecordService) {
        this.dpAuditRecordService = dpAuditRecordService;
    }

    public DpTypeService getDpTypeService() {
        return dpTypeService;
    }

    public void setDpTypeService(DpTypeService dpTypeService) {
        this.dpTypeService = dpTypeService;
    }

}
