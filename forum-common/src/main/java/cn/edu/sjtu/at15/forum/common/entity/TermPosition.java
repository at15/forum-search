package cn.edu.sjtu.at15.forum.common.entity;

/**
 * Created by at15 on 15-11-22.
 */
public class TermPosition {
    public final Integer postIndex;
    public final Integer offset;
    public static final Integer TITLE = -1;

    public TermPosition(Integer postIndex, Integer offset) {
        this.postIndex = postIndex;
        this.offset = offset;
    }

    public boolean isTitle() {
        return TITLE.equals(postIndex);
    }
}
