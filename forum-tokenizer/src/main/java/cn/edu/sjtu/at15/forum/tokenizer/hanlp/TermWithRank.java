package cn.edu.sjtu.at15.forum.tokenizer.hanlp;

import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.common.Term;

/**
 * Created by at15 on 15-11-22.
 *
 * @TODO rank or score ...
 */
public class TermWithRank extends Term {
    protected Float score;

    public TermWithRank(String word, Nature nature) {
        super(word, nature);
    }

    public TermWithRank(String word, Nature nature, Float score) {
        super(word, nature);
        this.score = score;
    }

    public TermWithRank(Term term, Float score) {
        super(term.word, term.nature);
        this.score = score;
    }

    public String getWord() {
        return word;
    }

    public Integer getOffset() {
        return offset;
    }

    public Float getRank() {
        return score;
    }

    public void setRank(Float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return super.toString() + " " + score;
    }
}
