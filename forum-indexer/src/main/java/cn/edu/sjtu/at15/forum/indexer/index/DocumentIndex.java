package cn.edu.sjtu.at15.forum.indexer.index;

import cn.edu.sjtu.at15.forum.common.entity.TermPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by at15 on 15-11-22.
 */
public class DocumentIndex {
    private String url;
    private Float rank;
    private List<TermPosition> positions;

    public DocumentIndex() {
        positions = new ArrayList<TermPosition>();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TermPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<TermPosition> positions) {
        this.positions = positions;
    }

    public void addPosition(TermPosition termPosition) {
        positions.add(termPosition);
    }

    public Float getRank() {
        return rank;
    }

    public void setRank(Float rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return url + " " + rank + positions.toString();
    }
}
