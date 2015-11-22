package cn.edu.sjtu.at15.forum.tokenizer.hanlp;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.summary.KeywordExtractor;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;

import java.util.*;

/**
 * Created by at15 on 15-11-22.
 */
public class TextRankTokenizer extends KeywordExtractor {
    /**
     * 阻尼系数(Damping factor)，一般取值为0.85
     */
    private final static float d = 0.85f;
    /**
     * 最大迭代次数
     */
    private final static int max_iter = 200;
    private final static float min_diff = 0.001f;

    public static void main(String[] args) {
        getTermAndRank("有一个著名的程序员说程序员都喜欢写程序,而且所有程序员都喜欢养狗");
    }

    public static List<TermWithRank> getTermAndRank(String document) {
        TextRankTokenizer textRankTokenizer = new TextRankTokenizer();
        List<Term> termList = IndexTokenizer.segment(document);
        List<TermWithRank> termWithRankList = new ArrayList<TermWithRank>();
        LinkedHashMap<String, Float> termScores = textRankTokenizer.getTermRank(termList);
        // merge the scores to the terms
        Float rank;
        for (Term t : termList) {
            // if this term got no rank, it is zero
            rank = 0.0f;
            if (termScores.containsKey(t.word)) {
                rank = termScores.get(t.word);
            }
            termWithRankList.add(new TermWithRank(t, rank));
        }
        System.out.println(termWithRankList);
        return termWithRankList;
    }


    // NOTE: the length of termList and return is not the same in most cases
    public LinkedHashMap<String, Float> getTermRank(List<Term> termList) {
        List<String> wordList = new ArrayList<String>();
        for (Term t : termList) {
            if (shouldInclude(t)) {
                wordList.add(t.word);
            }
        }
        System.out.println(wordList);
        Map<String, Set<String>> words = new TreeMap<String, Set<String>>();
        Queue<String> que = new LinkedList<String>();
        for (String w : wordList) {
            if (!words.containsKey(w)) {
                words.put(w, new TreeSet<String>());
            }
            que.offer(w);
            if (que.size() > 5) {
                que.poll();
            }

            for (String w1 : que) {
                for (String w2 : que) {
                    if (w1.equals(w2)) {
                        continue;
                    }

                    words.get(w1).add(w2);
                    words.get(w2).add(w1);
                }
            }
        }
//        System.out.println(words);
        Map<String, Float> score = new HashMap<String, Float>();
        for (int i = 0; i < max_iter; ++i) {
            Map<String, Float> m = new HashMap<String, Float>();
            float max_diff = 0;
            for (Map.Entry<String, Set<String>> entry : words.entrySet()) {
                String key = entry.getKey();
                Set<String> value = entry.getValue();
                m.put(key, 1 - d);
                for (String element : value) {
                    int size = words.get(element).size();
                    if (key.equals(element) || size == 0) continue;
                    m.put(key, m.get(key) + d / size * (score.get(element) == null ? 0 : score.get(element)));
                }
                max_diff = Math.max(max_diff, Math.abs(m.get(key) - (score.get(key) == null ? 0 : score.get(key))));
            }
            score = m;
            if (max_diff <= min_diff) break;
        }
        // sort the map
        List<Map.Entry<String, Float>> entryList = new ArrayList<Map.Entry<String, Float>>(score.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Float>>() {
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        // System.out.println(entryList);
        // must use linked HashMap to keep the order
        LinkedHashMap<String, Float> result = new LinkedHashMap<String, Float>();
        Map.Entry<String, Float> entry;
        for (int i = 0; i < entryList.size(); ++i) {
            entry = entryList.get(i);
            result.put(entry.getKey(), entry.getValue());
        }
//        System.out.println(result);
        return result;
    }


}
