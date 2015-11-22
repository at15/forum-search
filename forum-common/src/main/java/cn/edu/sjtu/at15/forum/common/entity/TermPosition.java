package cn.edu.sjtu.at15.forum.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by at15 on 15-11-22.
 */
public class TermPosition {
    public Integer postIndex;
    public Integer offset;
    @JsonIgnore
    public static final Integer TITLE = -1;

    public TermPosition() {

    }

    public TermPosition(Integer postIndex, Integer offset) {
        this.postIndex = postIndex;
        this.offset = offset;
    }

    public void setPostIndex(Integer postIndex) {
        this.postIndex = postIndex;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPostIndex() {
        return postIndex;
    }

    public Integer getOffset() {
        return offset;
    }

    @JsonIgnore
    public boolean isTitle() {
        return TITLE.equals(postIndex);
    }

    @Override
    public String toString() {
        return postIndex + "-" + offset;
    }


}
