/**
 * 
 */
package com.coship.appstore.plan.filter;

import org.springframework.stereotype.Component;

/**
 * 渠道条件过滤器. 根据渠道匹配.
 * 
 * @author xiezhengrong/907948
 * 
 * @since 2014年4月9日
 */
@Component("channelConditionFilter")
public class ChannelConditionFilter extends AbstractConditionFilter {
    public ChannelConditionFilter() {
        this.conditionCode = "channelId";
    }
}
