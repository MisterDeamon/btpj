package com.jack.security.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wajiangk on 9/6/2016.
 */
public class SecurityMenu extends BaseEntity implements Serializable{

    private String id;
    private String description;
    private String name;
    private String rightSn;
    private String url;
    private String parentId;
    private String icon;
    private int priority;
    private int isParent;

    private List<SecurityMenu> childrenMenus;

    public SecurityMenu() {
    }

    public SecurityMenu(String id, String description, String name, String rightSn, String url, String parentId, String icon, int priority, int isParent) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.rightSn = rightSn;
        this.url = url;
        this.parentId = parentId;
        this.icon = icon;
        this.priority = priority;
        this.isParent = isParent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRightSn() {
        return rightSn;
    }

    public void setRightSn(String rightSn) {
        this.rightSn = rightSn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getIsParent() {
        return isParent;
    }

    public void setIsParent(int isParent) {
        this.isParent = isParent;
    }

    public List<SecurityMenu> getChildrenMenus() {
        return childrenMenus;
    }

    public void setChildrenMenus(List<SecurityMenu> childrenMenus) {
        this.childrenMenus = childrenMenus;
    }

    @Override
    public String toString() {
        return "SecurityMenu{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", rightSn='" + rightSn + '\'' +
                ", url='" + url + '\'' +
                ", parentId='" + parentId + '\'' +
                ", icon='" + icon + '\'' +
                ", priority=" + priority +
                ", isParent=" + isParent +
                '}';
    }
}
