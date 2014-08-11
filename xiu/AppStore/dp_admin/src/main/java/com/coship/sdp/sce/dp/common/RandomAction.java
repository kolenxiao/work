/*
 * 文 件 名:  RandomAction.java
 * 版    权:  Shenzhen Coship Electronics Co.,Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <产生随机数，用于用户登录页面的校验码生成。>
 * 修 改 人:  闵咏兵
 * 修改时间:  2010-6-12
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.coship.sdp.sce.dp.common;

import java.io.ByteArrayInputStream;

import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.utils.RandomNumUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 产生随机数，用于用户登录页面的校验码生成
 * @author huangliufei
 * @since [iag系统]
 */
public class RandomAction extends ActionSupport
{
    private static final long serialVersionUID = -4631539943601191208L;

    /** 字节流，用于生成图片 */
    private transient ByteArrayInputStream inputStream;

    /**
     * 生成登录页面验证码图片
     * @return String
     */
    public String rand() throws Exception
    {

        RandomNumUtil rdnu = RandomNumUtil.instance();
        // 取得带有随机字符串的图片
        this.setInputStream(rdnu.getImage());
        // 取得随机字符串放入HttpSession
        ActionContext.getContext().getSession()
                .put(Constants.SESSION_AUTHEN_CODE, rdnu.getString());
        return SUCCESS;
    }

    public ByteArrayInputStream getInputStream()
    {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream)
    {
        this.inputStream = inputStream;
    }
}
