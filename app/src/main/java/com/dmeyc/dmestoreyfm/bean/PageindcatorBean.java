package com.dmeyc.dmestoreyfm.bean;

public class PageindcatorBean {

private int endRow;
private boolean firstPage;
private boolean hasNextPage;
private boolean hasPrePage;
private boolean lastPage;
private int limit;
private int nextPage;
private int offset;
private int page;
private int prePage;
private int startRow;
private int totalCount;
private int totalPages;


    public boolean isFirstPage() {
        return firstPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public boolean isHasPrePage() {
        return hasPrePage;
    }

    public boolean isLastPage() {
        return lastPage;
    }


    public int getEndRow() {
        return endRow;
    }

    public int getLimit() {
        return limit;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getOffset() {
        return offset;
    }

    public int getPage() {
        return page;
    }

    public int getPrePage() {
        return prePage;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }



    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
