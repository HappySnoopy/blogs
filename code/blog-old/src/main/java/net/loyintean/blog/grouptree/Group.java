/**
 * All Rights Reserved
 */
package net.loyintean.blog.grouptree;

import java.util.LinkedList;
import java.util.List;

/**
 * @author linjun
 * @since 2017年10月23日
 */
public class Group {

    private Integer id;

    private String name;

    private Integer parentId;

    private List<Group> childrenList = new LinkedList<>();

    public void addChild(Group child) {
        this.childrenList.add(child);
    }

    /**
     * @return the {@link #id}
     */
    public Integer getId() {
        return this.id == null ? -1 : this.id.intValue();
    }

    /**
     * @param id
     *        the {@link #id} to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the {@link #name}
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *        the {@link #name} to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the {@link #parentId}
     */
    public Integer getParentId() {
        return this.parentId == null ? -1 : this.parentId.intValue();
    }

    /**
     * @param parentId
     *        the {@link #parentId} to set
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @author linjun
     * @since 2017年10月23日
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Group [");
        if (this.id != null) {
            builder.append("id=").append(this.id).append(", ");
        }
        if (this.name != null) {
            builder.append("name=").append(this.name).append(", ");
        }
        if (this.parentId != null) {
            builder.append("parentId=").append(this.parentId).append(", ");
        }
        if (this.childrenList != null) {
            builder.append("childrenList=").append(this.childrenList);
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * @return the {@link #childrenList}
     */
    public List<Group> getChildrenList() {
        return this.childrenList;
    }
}
