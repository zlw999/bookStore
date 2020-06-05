package com.atguigu.bookstore.beans;

import java.util.List;

public class Page<T> {
    private List<T> list; //每页中的数据存放的集合
    public static final int PAGE_SIZE = 4; //每页显示的数据的条数
    private int pageNo; //当前页码
//    private int totalPageNo; //总页数，通过计算得到
    private int totalRecord; //总记录数，通过查询数据库得到

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    public int getPageNo() {
        //如果当前页小于1
        if(pageNo < 1){
            return 1;
        //如果当前页大于总页数
        }else if(pageNo > getTotalPageNo()){
            return getTotalPageNo();
        }else{
            return pageNo;
        }
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    //总页数由总记录数和每页显示的条数计算得到
    public int getTotalPageNo() {
        if(totalRecord % PAGE_SIZE == 0){
            return totalRecord / PAGE_SIZE;
        }else {
            return totalRecord / PAGE_SIZE + 1;
        }
    }

//    public void setTotalPageNo(int totalPageNo) {
//        this.totalPageNo = totalPageNo;
//    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
}
