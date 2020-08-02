package com.dmeyc.dmestoreyfm.bean.common;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2018/1/8
 * Email:jockie911@gmail.com
 */

public class PaginatorBean implements Parcelable {

    private int limit;
    private int page;
    private int totalCount;
    private int offset;
    private boolean lastPage;
    private boolean hasPrePage;
    private boolean hasNextPage;
    private int startRow;
    private int prePage;
    private int nextPage;
    private int totalPages;
    private boolean firstPage;
    private int endRow;
    private List<Integer> slider;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isHasPrePage() {
        return hasPrePage;
    }

    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public List<Integer> getSlider() {
        return slider;
    }

    public void setSlider(List<Integer> slider) {
        this.slider = slider;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.limit);
        dest.writeInt(this.page);
        dest.writeInt(this.totalCount);
        dest.writeInt(this.offset);
        dest.writeByte(this.lastPage ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasPrePage ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasNextPage ? (byte) 1 : (byte) 0);
        dest.writeInt(this.startRow);
        dest.writeInt(this.prePage);
        dest.writeInt(this.nextPage);
        dest.writeInt(this.totalPages);
        dest.writeByte(this.firstPage ? (byte) 1 : (byte) 0);
        dest.writeInt(this.endRow);
        dest.writeList(this.slider);
    }

    public PaginatorBean() {
    }

    protected PaginatorBean(Parcel in) {
        this.limit = in.readInt();
        this.page = in.readInt();
        this.totalCount = in.readInt();
        this.offset = in.readInt();
        this.lastPage = in.readByte() != 0;
        this.hasPrePage = in.readByte() != 0;
        this.hasNextPage = in.readByte() != 0;
        this.startRow = in.readInt();
        this.prePage = in.readInt();
        this.nextPage = in.readInt();
        this.totalPages = in.readInt();
        this.firstPage = in.readByte() != 0;
        this.endRow = in.readInt();
        this.slider = new ArrayList<Integer>();
        in.readList(this.slider, Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<PaginatorBean> CREATOR = new Parcelable.Creator<PaginatorBean>() {
        @Override
        public PaginatorBean createFromParcel(Parcel source) {
            return new PaginatorBean(source);
        }

        @Override
        public PaginatorBean[] newArray(int size) {
            return new PaginatorBean[size];
        }
    };
}
