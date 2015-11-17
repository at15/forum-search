package cn.edu.sjtu.at15.forum.tokenizer;

/**
 * Created by gpl on 15/11/17.
 */
public class Token {
    public static final String SEP = ";";
    private String term;
    private Integer rank;
    private String url;
    private Integer position;

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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public boolean isValid() {
        return (term != null) &&
                (rank != null) &&
                (url != null) &&
                (position != null);
    }

    public String toInfo() {
        return url + SEP + rank + SEP + position;
    }

    public static Token fromInfo(String info, String term) {
        String[] s = info.split(SEP);
        Token t = new Token();
        t.setTerm(term);
        t.setUrl(s[0]);
        t.setRank(Integer.valueOf(s[1]));
        t.setPosition(Integer.valueOf(s[2]));
        return t;
    }
}
