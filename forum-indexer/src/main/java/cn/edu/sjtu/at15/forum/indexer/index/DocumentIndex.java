package cn.edu.sjtu.at15.forum.indexer.index;

import java.util.List;

/**
 * Created by at15 on 15-11-22.
 */
public class DocumentIndex {
    private String url;
    private List<Integer> postions;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Integer> getPostions() {
        return postions;
    }

    public void setPostions(List<Integer> postions) {
        this.postions = postions;
    }
}
