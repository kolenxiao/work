package com.coship.appstore.plan.filter;

/**
 * 条件过滤器中间过程记录POJO.
 * 
 * @author xiezhengrong/907948
 * 
 * @since 2014年4月11日
 */
public class ConditionFilterData {
    /**
     * 已处理的过滤器计数器.
     */
    private int filterCount = 0;

    public void incrementFilterCount() {
        filterCount++;
    }

    public int getFilterCount() {
        return filterCount;
    }

    public void setFilterCount(int filterCount) {
        this.filterCount = filterCount;
    }
}
