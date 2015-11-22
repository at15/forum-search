package cn.edu.sjtu.at15.forum.tokenizer;

import com.hankcs.hanlp.seg.common.Term;

/**
 * Created by at15 on 15-11-22.
 * <p/>
 * A warpper for HanLP's term class
 *
 * @TODO: warp TermWithRank instead
 */
public class TermWrapper {
    private Term term;

    public TermWrapper(Term term) {
        this.term = term;
    }

    public String getWord() {
        return term.word;
    }

    // TODO: offset is not so important I think ...
    public Integer getOffset() {
        return term.offset;
    }

    // TODO: get rank

}
