package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.crawler.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by at15 on 15-11-20.
 */
public class ListParser extends Parser {
    private Integer maxPage;
    private static final Pattern maxPagePattern = Pattern.compile("(.*)\\s(\\d+)\\s(.*)");
    private List<String> pageLinks = new ArrayList<String>();

    private List<String> threadLinks = new ArrayList<String>();

    // TODO: parse page links and thread links
    public ListParser(String html) throws IllegalArgumentException {
        super(html);
        String pagination = document.select("div.pg>label>span").text();
        if (pagination == null) {
            throw new IllegalArgumentException("this is not a list page");
        }
        Matcher m = maxPagePattern.matcher(pagination);
        maxPage = (m.find()) ? Integer.valueOf(m.group(2)) : 0;
    }

    public Integer getMaxPage() {
        return (maxPage != null) ? maxPage : 0;
    }

    public List<String> getPageLinks() {
        return pageLinks;
    }

    public List<String> getThreadLinks() {
        return threadLinks;
    }
}
