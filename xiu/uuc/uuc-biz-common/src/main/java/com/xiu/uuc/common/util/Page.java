package com.xiu.uuc.common.util;

/**
 * @ClassName: Page 
 * @Description: 页面对象 用于列表分页 
 * @author menglei
 * @date Jul 22, 2011 4:29:35 PM 
 */
public class Page {
	/**
     * 当前页
     */
    private int currentPage;
    
    /**
     * 每页显示的条数
     */
	private int pageSize;
	
	/**
	 * 总记录数
	 */
    private int totalRecord;
    
    /**
     * 总页数
     */
    private int totalPage;
    
    /**
     * 起始行
     */
    private int startRec;
    
    /**
     * 结束行
     */
    private int endRec;
    
    /**
     * page对象
     */
    public static Page page = new Page();

    public void setCurrentPage(int currentPage) {
		if (currentPage > totalPage && totalPage != 0) {
			this.currentPage = totalPage;
		} else if (currentPage < 1) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
	}

    public int getCurrentPage(){
        return currentPage;
    }
    
    public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setStartRec(int startRec) {
		this.startRec = startRec;
	}

	public void setEndRec(int endRec) {
		this.endRec = endRec;
	}

	public void setTotalRecord(int totalRecord){
        this.totalRecord = totalRecord;
    }

    public int getTotalRecord(){
        return totalRecord;
    }

    public void setTotalPage(){
        if(totalRecord % pageSize != 0){
            totalPage = totalRecord / pageSize + 1;
        }else{
            totalPage = totalRecord / pageSize;
        }
    }

    public int getTotalPage(){
        return totalPage;
    }

    public void setStartRec(){
        if(totalPage == 0){
            startRec = 0;
        }else{
            startRec = (currentPage - 1) * pageSize + 1;
        }
    }

    public int getStartRec(){
        return startRec;
    }

    public void setEndRec(){
        if(totalPage <= 1){
            endRec = totalRecord;
        }else if(currentPage == totalPage){
            endRec = totalRecord;
        }else{
            endRec = (startRec + pageSize) - 1;
        }
    }

    public int getEndRec(){
        return endRec;
    }
    
    /**
     * 传入当前页，总记录数，每页显示的条数，计算出起始行和结束行，存放在page对象中
     * 
     * @Title: getPage 
     * @param currentPage 当前页
     * @param totalRecord 总记录数
     * @param pageSize 每页显示的记录数
     * @return Page    返回类型 
     * @throws
     */
    public static Page getPage(int currentPage,int totalRecord,int pageSize){
    	
    	// 填充总记录数
		page.setTotalRecord(totalRecord);
		
		// 填充每页显示的条数
		page.setPageSize(pageSize);
    	
    	// 计算总页数
		page.setTotalPage();
    	
		// 填充当前页
		page.setCurrentPage(currentPage);
		
		// 计算起始行
		page.setStartRec();
		
		// 计算结束行
		page.setEndRec();
		
		return page;
    }
}
