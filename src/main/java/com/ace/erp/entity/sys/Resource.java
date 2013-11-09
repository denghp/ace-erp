package com.ace.erp.entity.sys;

import com.ace.erp.entity.Treeable;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with smart-erp.
 * (C) Copyright haiping Corporation 2013 All Rights Reserved.
 * User: denghp
 * Date: 10/16/13
 * Time: 1:02 AM
 */
public class Resource implements Treeable<Integer> {

    private Integer id;

    /**
     * 标题
     */
    private String name;

    /**
     * 资源标识符 用于权限匹配的 如sys:resource
     */
    private String identity;

    /**
     * 点击后前往的地址
     * 菜单才有
     */
    private String url;

    /**
     * 父路径
     */
    private Integer parentId;

    private String parentIds;

    private Integer weight;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否有叶子节点
     */
    private boolean hasChildren;

    /**
     * 是否显示
     */
    private Boolean show = Boolean.FALSE;

    //表示此数据在哪一级
    private Integer level;

    //表示此节点是否展开
    private boolean expanded = Boolean.FALSE;

    // 表示是否加载完成，设置为True表示加载完成，不需要在加载,
    //一般设置此值为True，这样在点击树节点就不会再次调用后台数据，加载数据，避免数据重复
    private boolean loaded = Boolean.FALSE;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String makeSelfAsNewParentIds() {
        return getParentIds() + getId() + getSeparator();
    }

    public String getTreetableIds() {
        String selfId = makeSelfAsNewParentIds().replace("/", "-");
        return selfId.substring(0, selfId.length() - 1);
    }

    public String getTreetableParentIds() {
        String parentIds = getParentIds().replace("/", "-");
        return parentIds.substring(0, parentIds.length() - 1);
    }

    @Override
    public String getSeparator() {
        return "/";
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getIcon() {
        if (!StringUtils.isEmpty(icon)) {
            return icon;
        }
        if (isRoot()) {
            return getRootDefaultIcon();
        }
        if (isLeaf()) {
            return getLeafDefaultIcon();
        }
        return getBranchDefaultIcon();
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    @Override
    public boolean isRoot() {
        if (getParentId() != null && getParentId() == 0) {
            return true;
        }
        return false;
    }


    @Override
    public boolean isLeaf() {
        if (isRoot()) {
            return false;
        }
        if (isHasChildren()) {
            return false;
        }

        return true;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }


    /**
     * 根节点默认图标 如果没有默认 空即可
     *
     * @return
     */
    @Override
    public String getRootDefaultIcon() {
        return "ztree_setting";
    }

    /**
     * 树枝节点默认图标 如果没有默认 空即可
     *
     * @return
     */
    @Override
    public String getBranchDefaultIcon() {
        return "ztree_folder";
    }

    /**
     * 树叶节点默认图标 如果没有默认 空即可
     *
     * @return
     */
    @Override
    public String getLeafDefaultIcon() {
        return "ztree_file";
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", isHasChildren='" + hasChildren + '\'' +
                ", parentId =" + parentId +
                ", parentIds = " + parentIds +
                ", level = " + level +
                '}';
    }

}
