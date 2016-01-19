package com.company.modules.utils;

/**
 * Ztree NODE模型
 * User: 庚
 * Date: 13-10-23
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
public class ZtreeNode {
    private String id;
    private String name;
    private String parentId;
    private Boolean isParent;
    private Boolean open=true;
    private int chidrenCount;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsParent() {
        return chidrenCount>0;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public int getChidrenCount() {
        return chidrenCount;
    }

    public void setChidrenCount(int chidrenCount) {
        this.chidrenCount = chidrenCount;
    }
}
