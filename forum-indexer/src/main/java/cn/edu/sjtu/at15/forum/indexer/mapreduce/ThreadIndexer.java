package cn.edu.sjtu.at15.forum.indexer.mapreduce;

import cn.edu.sjtu.at15.forum.common.entity.ForumThread;
import cn.edu.sjtu.at15.forum.indexer.index.DocumentIndex;
import cn.edu.sjtu.at15.forum.indexer.index.InvertedIndex;
import cn.edu.sjtu.at15.forum.tokenizer.TermWithPosition;
import cn.edu.sjtu.at15.forum.tokenizer.Tokenizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by at15 on 15-11-22.
 */
public class ThreadIndexer {

    public static Map<String, DocumentIndex> processDocument(ForumThread thread) {
        List<TermWithPosition> termWithPositionList = Tokenizer.tokenize(thread);
        Map<String, DocumentIndex> documentIndexMap = new HashMap<String, DocumentIndex>();
        DocumentIndex documentIndex;
        for (TermWithPosition t : termWithPositionList) {
            if (!documentIndexMap.containsKey(t.getWord())) {
                documentIndex = new DocumentIndex();
                // FIXME: what about the relation between thread and main thread
                documentIndex.setUrl(thread.getUrl());
                // The rank is already the total rank for this term.
                documentIndex.setRank(t.getRank());
                documentIndexMap.put(t.getWord(), documentIndex);
            }
            documentIndex = documentIndexMap.get(t.getWord());
            documentIndex.addPosition(t.getPosition());
        }
        return documentIndexMap;
    }
}
