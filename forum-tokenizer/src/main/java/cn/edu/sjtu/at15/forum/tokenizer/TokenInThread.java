package cn.edu.sjtu.at15.forum.tokenizer;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gpl on 15/11/17.
 */
public class TokenInThread {
    public static final String SEP = ";";
    public static final String POS_SEP = ",";

    private List<Integer> positions;
    private String term;
    private Integer rank = 0;
    private String url;

    public TokenInThread() {
        positions = new ArrayList<Integer>();
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addToken(Token token) {
        setTerm(token.getTerm());
        setUrl(token.getUrl());
        setRank(token.getRank() + getRank());
        positions.add(token.getPosition());
    }

    public List<Integer> getPositions() {
        return positions;
    }

    public String getPositionsAsString() {
        return StringUtils.join(positions, POS_SEP);
    }

    public String toInfo() {
        return url + SEP + rank + SEP + getPositionsAsString();
    }

    @Override
    public String toString() {
        return toInfo();
    }
}
