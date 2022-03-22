package com.lj.pojo;

import java.util.List;

public class  Page<T> {
    public static final Integer PAGESIZE =4;
    //当前页码
    private Integer pageNo;
    //总页数
    private Integer pageTotal;
    //当前页显示数量，有两种定义方式，客户端定义以及服务器定义，这里先服务器定义好之后客户端发请求之后会修改
    private Integer pageSize=PAGESIZE;
    //总记录数
    private Integer pageTotalCount;
    //当前页数据
    private List<T> pageItems;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page() {
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", pageItems=" + pageItems +
                ", url='" + url + '\'' +
                '}';
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        //再给pageNo赋值的时候确保输入页面在范围呢，如果大于最大就跳转到最后一页，如果小于最小就跳到第一页
        if(pageNo<1){
            pageNo=1;
        }
        if(pageNo>pageTotal){
            pageNo=pageTotal;
        }
        this.pageNo=pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getPageItems() {
        return pageItems;
    }

    public void setPageItems(List<T> pageItems) {
        this.pageItems = pageItems;
    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<T> pageItems, String url) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.pageItems = pageItems;
        this.url = url;
    }
}
