package cn.edu.sjtu.at15.forum.tokenizer;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gpl on 15/11/17.
 */
public class DiscuzThreadTokenizer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscuzThreadTokenizer.class);
    public static final Integer RANKBASE = 1000;

    public List<Term> segment(String str) {
        return HanLP.segment(str);
    }

    public List<Token> tokenize(String str) {
        List<Term> terms = segment(str);
        List<Token> tokens = new ArrayList<Token>();
        Map<String, Integer> termRanks = new HashMap<String, Integer>();
        Integer termCount = terms.size();
        // count the occurrence for term
        for (Term term : terms) {
            if (!termRanks.containsKey(term.word)) {
                termRanks.put(term.word, 1);
            } else {
                termRanks.put(term.word, termRanks.get(term.word) + 1);
            }
        }
        // generate token and term
        for (Term term : terms) {
            Token token = new Token();
            token.setTerm(term.word);
            // TODO: this won't work for string longer than RANKBASE, the result would be zero
            token.setRank(RANKBASE * termRanks.get(term.word) / termCount);
            token.setPosition(term.offset);
            tokens.add(token);
        }
        return tokens;
    }

    // TODO: what's the difference when call HanLP and StandardTokenizer
    @Deprecated
    public List<Term> hanlp(String str) {
        return HanLP.segment(str);
    }

    @Deprecated
    public List<Term> standard(String str) {
        return StandardTokenizer.segment(str);
    }

    public static void main(String[] args) throws Exception {
        DiscuzThreadTokenizer tokenizer = new DiscuzThreadTokenizer();
        System.out.println(tokenizer.standard("我喜欢吃西瓜"));
        System.out.println(tokenizer.hanlp("我喜欢吃西瓜"));
    }
}
