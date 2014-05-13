<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>UUC 统一用户中心</title>
	</head>
	<body>
		<h2>
			UUC 统一用户中心 提供的功能接口
		</h2>
		
         
		<div>
			<a href="user_go_isLogonNameExist.action" target="_blank">检查是否已被注册</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_registerUser.action" target="_blank">用户注册</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="user_go_leadPartnerRegister.action" target="_blank">引导联盟用户注册</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_activateUser.action" target="_blank">激活用户</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_forbidUser.action" target="_blank">禁用用户</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_unForbidUser.action" target="_blank">解禁用户</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_deleteUser.action" target="_blank">删除用户</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<br>
		<div>
			<a href="user_go_getUserBasicInfoByUserId.action" target="_blank">通过用户ID得到用户基本信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_getUserBasicInfoList.action" target="_blank">得到用户基本信息列表</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_getUserDetailInfoByUserId.action" target="_blank">通过用户ID得到用户详细信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_getUserDetailInfoList.action" target="_blank">得到用户详细信息列表</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="user_go_getUserChannelList.action" target="_blank">得到用户渠道列表</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_getUserInTargetChannel.action" target="_blank">得到在目标渠道下的用户基本信息</a>&nbsp;&nbsp;&nbsp;&nbsp;			
		</div>
		<br>
		<div>
			<a href="user_go_modifyUserBaseInfo.action" target="_blank">修改用户基本信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_modifyUserDetailInfo.action" target="_blank">修改用户详细信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_modifyUserPassword.action" target="_blank">修改用户密码</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_resetUserPassword.action" target="_blank">重置用户密码</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_getPasswordByLogonNameAndChannelId.action" target="_blank">通过登录名和渠道标识得到登录密码</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_modifyUserAuthen.action" target="_blank">修改用户认证</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_isAuthentic.action" target="_blank">(邮箱/手机)是否通过验证</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<br>
        <div>
            <a href="address_go_addRcvAddressInfo.action" target="_blank">增加地址</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="address_go_modifyRcvAddressInfo.action" target="_blank">修改地址</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="address_go_modifyRcvAddressMaster.action" target="_blank">设为默认地址</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="address_go_deleteRcvAddress.action" target="_blank">删除地址</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="address_go_getRcvAddressInfo.action" target="_blank">查询单条地址</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="address_go_getRcvAddressList.action" target="_blank">查询地址列表</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="address_go_getRcvAddressCount.action" target="_blank">查询地址数目</a>&nbsp;&nbsp;   &nbsp;&nbsp;        
        </div>
        <br>
        <br>
        <br>
		
		<div>
			<a href="bankAcct_go_addBankAcctInfo.action" target="_blank">新增用户提现银行卡信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="bankAcct_go_modifyBankAcctInfo.action" target="_blank">修改用户提现银行卡信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="bankAcct_go_deleteBankAcctInfo.action" target="_blank">删除用户提现银行卡信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="bankAcct_go_getBankAcctInfo.action" target="_blank">查询用户提现银行卡信息详情</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="bankAcct_go_getBankAcctList.action" target="_blank">查询用户提现银行卡信息列表</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<br>
		<div>
			<a href="assets_go_getVirtualChangeList.action" target="_blank">虚拟账户积分明细查询（账目变更）</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="assets_go_getVirtualItemList.action" target="_blank">虚拟账户积分查询(账目)</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="assets_go_getVirtualAccountInfo.action" target="_blank">虚拟账户积分统计(账目)</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<br>
		<div>
			<a href="assets_go_addVirtualAccountMoney.action" target="_blank">虚拟账户金额充值</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="assets_go_decVirtualAccountMoney.action" target="_blank">虚拟账户金额支付</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="assets_go_decVirtualAccountMoneyByItemTypeCode.action" target="_blank">虚拟账户金额扣款</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="assets_go_addVirtualAccountIntegral.action" target="_blank">虚拟账户积分充值</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="assets_go_decVirtualAccountIntegral.action" target="_blank">虚拟账户积分支付</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<br>
		<div>
			<a href="assets_go_setUserAcctFreeze.action" target="_blank">设置用户账户冻结</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="assets_go_setUserAcctUnFreeze.action" target="_blank">设置用户账户解冻</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="assets_go_setUserAcctItemFreezeMoney.action" target="_blank">修改当前账目冻结金额</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="assets_go_checkIsFreezeUserAcct.action" target="_blank">校验当前用户账户是否被冻结</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<br>
		<div>
			<a href="auditAccountBalance.action">虚拟账户异动审计-账目平衡审计(域名)</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="auditAccountBalance.action">虚拟账户异动审计-账目平衡审计（Ip）</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="goQueryBusiLogList.action">查询业务操作日志列表</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>		
		<br>
		<br>
		<br>
		
		<div>
			<a href="sendLetters.jsp">发送信件</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="lettersList.jsp">所有信件</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="noRead.jsp">未读信件</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="read.jsp">已读信件</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="size.jsp">信件条数</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>		
	
	 

 <!-- 
		<br>
		<br>
		<div>	
			<a href="user_go_getUserBasicInfoList.action" target="_blank">得到用户基本信息列表</a>&nbsp;&nbsp;&nbsp;&nbsp;			
			<a href="user_go_getUserDetailInfoList.action" target="_blank">得到用户详细信息列表</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_getUserChannelList.action" target="_blank">得到用户渠道列表</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_getUserBasicInfoByUserId.action" target="_blank">通过用户ID得到用户基本信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_getUserDetailInfoByUserId.action" target="_blank">通过用户ID得到用户详细信息</a>&nbsp;&nbsp;&nbsp;&nbsp;		
		</div>
		<br>
		<div>
			<a href="user_go_isLogonNameExist.action" target="_blank">检查是否已被注册</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="user_go_getPasswordByLogonNameAndChannelId.action" target="_blank">通过登录名和渠道标识得到登录密码</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="address_go_getRcvAddressList.action" target="_blank">查询地址列表</a>&nbsp;&nbsp;&nbsp;&nbsp;	
			<a href="address_go_getRcvAddressInfo.action" target="_blank">查询单条地址</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<br>
		<br>
		<br>		
		<div>
			<a href="bankAcct_go_getBankAcctInfo.action" target="_blank">查询用户提现银行卡信息详情</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="bankAcct_go_getBankAcctList.action" target="_blank">查询用户提现银行卡信息列表</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<br>
		<div>
			<a href="assets_go_getVirtualChangeList.action" target="_blank">虚拟账户积分明细查询（账目变更）</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="assets_go_getVirtualItemList.action" target="_blank">虚拟账户积分查询(账目)</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="assets_go_getVirtualAccountInfo.action" target="_blank">虚拟账户积分统计(账目)</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="assets_go_checkIsFreezeUserAcct.action" target="_blank">校验当前用户账户是否被冻结</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<br>
		<div>
			<a href="auditAccountBalance.action" target="_blank">虚拟账户异动审计-账目平衡审计(域名)</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="auditAccountBalance.action" target="_blank">虚拟账户异动审计-账目平衡审计（Ip）</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="goQueryBusiLogList.action" target="_blank">查询业务操作日志列表</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>		
		<br>
		<br>
		<br>
		
		<div>
			<a href="lettersList.jsp">所有信件</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="noRead.jsp">未读信件</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="read.jsp">已读信件</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="size.jsp">信件条数</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<br>
		<br>

 -->
	</body>
</html>
