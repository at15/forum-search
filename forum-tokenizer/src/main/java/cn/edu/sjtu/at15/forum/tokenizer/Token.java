package cn.edu.sjtu.at15.forum.tokenizer;

/**
 * Created by gpl on 15/11/17.
 */
public class Token {
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
}
