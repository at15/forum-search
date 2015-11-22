package cn.edu.sjtu.at15.forum.tokenizer;

import cn.edu.sjtu.at15.forum.common.entity.TermPosition;
import cn.edu.sjtu.at15.forum.tokenizer.hanlp.TermWithRank;

/**
 * Created by at15 on 15-11-22.
 */
public class TermWithPosition {
    private String word;
    private Float rank;
    private TermPosition position;

    public TermWithPosition(TermWithRank termWithRank, Integer postIndex) {
        word = termWithRank.getWord();
        rank = termWithRank.getRank();
        position = new TermPosition(postIndex, termWithRank.getOffset());
    }

    public String getWord() {
        return word;
    }

    public Float getRank() {
        return rank;
    }

    public TermPosition getPosition() {
        return position;
    }
}
