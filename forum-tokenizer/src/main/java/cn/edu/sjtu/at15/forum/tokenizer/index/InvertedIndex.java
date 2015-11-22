package cn.edu.sjtu.at15.forum.tokenizer.index;

import java.util.List;

/**
 * Created by at15 on 15-11-22.
 */
public class InvertedIndex {
    private String term;
    private Integer count;
    private List<DocumentIndex> documents;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<DocumentIndex> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentIndex> documents) {
        this.documents = documents;
    }
}
