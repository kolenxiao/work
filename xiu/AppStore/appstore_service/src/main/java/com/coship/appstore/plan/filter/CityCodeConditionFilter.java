package com.coship.appstore.plan.filter;

import org.springframework.stereotype.Component;

/**
 * 区域码过滤条件, 针对OTT+DVB的盒子.
 * 
 * @author xiezhengrong/907948
 * 
 * @since 2014年4月9日
 */
@Component("cityCodeConditionFilter")
public class CityCodeConditionFilter extends AbstractConditionFilter {
    public CityCodeConditionFilter() {
        this.conditionCode = "cityCode";
    }
}
