package cn.edu.sjtu.at15.forum.tokenizer;

import cn.edu.sjtu.at15.forum.common.entity.ForumThread;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gpl on 15/11/17.
 */
public class DiscuzThreadTokenizer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscuzThreadTokenizer.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    public static final Integer RANKBASE = 1000;

    public List<Term> segment(String str) {
//        return HanLP.segment(str);
        return IndexTokenizer.segment(str);
    }

    // set term, rank, position for a token
    public List<Token> tokenize(String str) {
        return tokenize(str, null);
    }

    public List<Token> tokenize(String str, String url) {
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
//            LOGGER.debug("offset is: " + term.offset);
            token.setPosition(term.offset);
            if (url != null) {
                token.setUrl(url);
            }
            tokens.add(token);
        }
        return tokens;
    }

    public List<Token> tokenizeThread(String json) {
        List<Token> tokens = new ArrayList<Token>();
        try {
            ForumThread thread = mapper.readValue(json, ForumThread.class);
            // TODO: title and post should be treated differently
            List<Token> title = tokenize(thread.getTitle(), thread.getUrl());
//            List<Token> authorPost = tokenize(thread.getAuthorPost(), thread.getUrl());
            tokens.addAll(title);
//            tokens.addAll(authorPost);
        } catch (IOException ex) {
            LOGGER.warn("wrong json format for thread ", ex);
            // TODO: return null or an empty list?
        }
        return tokens;
    }


    public static void main(String[] args) throws Exception {
        DiscuzThreadTokenizer tokenizer = new DiscuzThreadTokenizer();
        System.out.println(tokenizer.segment("我喜欢吃西瓜"));
    }
}
