package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.common.entity.ForumMainThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by at15 on 15-11-19.
 */
public class DiscuzPageProcessor implements PageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscuzPageProcessor.class);
    private final DiscuzUrl discuzUrl;
    private Site site;

    public DiscuzPageProcessor(String baseUrl) {
        site = Site.me().setRetryTimes(3).setSleepTime(1000);
        discuzUrl = new DiscuzUrl(baseUrl);
    }

    public Site getSite() {
        return site;
    }

    public void process(Page page) {
        String currentUrl = page.getUrl().toString();
        String html = page.getRawText();
        LOGGER.debug("processing page : " + currentUrl);

        if (!discuzUrl.isInnerLink(currentUrl)) {
            LOGGER.debug("ignore outer link : " + currentUrl);
            return;
        }

        // deal with thread
        if (discuzUrl.isThread(currentUrl)) {
            LOGGER.debug("processing thread");
            String mainThreadUrl;
            if(discuzUrl.isMainThread(currentUrl)){
                mainThreadUrl = currentUrl;
                // TODO: parse using thread parser
            }else{
                mainThreadUrl = discuzUrl.getMainThreadUrl(currentUrl);
                // TODO: parse use thread parser
            }
            // TODO: there are threads and thread comments (sub thread), should be treated differently
            // put the logic in parseThread
//            ForumThread thread = parseThread(page);
            // store data for pipeline
            page.putField("url", currentUrl);
//            page.putField("thread", thread);
            return;
        }

        // try to parse as a list page
        try {
            ListParser listParser = new ListParser(html);
            // get all the pages for list from pagination
            page.addTargetRequests(listParser.getPageLinks());
            page.addTargetRequests(listParser.getThreadLinks());
            return;
        } catch (IllegalArgumentException ignore) {
            LOGGER.warn("not a list page : " + currentUrl);
        }

        // this is other type of pages, we just simply add all inner links
        for (String link : page.getHtml().links().all()) {
            // ignore outer link
            if (!discuzUrl.isInnerLink(link)) {
                continue;
            }
            LOGGER.debug(link);
            page.addTargetRequest(link);
        }
    }

    // TODO: should get all the comments as well, but we don't have much time, so.
//    protected static ForumThread parseThread(Page page) {
//        ForumThread thread = new ForumThread();
//        ThreadParser threadParser = new ThreadParser(page.getRawText());
//        thread.setUrl(page.getUrl().toString());
//        String title = threadParser.getTitle();
//        LOGGER.debug("thread title : " + title);
//        thread.setTitle(title);
//        String author = threadParser.getAuthor();
//        thread.setAuthor(author);
//        LOGGER.debug("thread author : " + author);
//        Integer viewCount = threadParser.getViewCount();
//        thread.setViewCount(viewCount);
//        LOGGER.debug("thread view count : " + viewCount);
//        Integer replyCount = threadParser.getReplyCount();
//        thread.setReplyCount(replyCount);
//        LOGGER.debug("thread reply count : " + replyCount);
//        String authorPost = threadParser.getAuthorPost();
//        thread.setAuthorPost(authorPost);
//        LOGGER.debug(authorPost);
//        return thread;
//    }

    public static void main(String[] args) throws Exception {
        Spider.create(new DiscuzPageProcessor("http://www.1point3acres.com/bbs/"))
//                .addUrl("http://www.1point3acres.com/bbs/forum.php?mod=guide&view=hot")
                .addUrl("http://www.1point3acres.com/bbs/thread-147944-1-1.html")
//                .addPipeline(new ConsolePipeline())
                .addPipeline(new JsonFilePipline("data"))
                .thread(5)
                .run();
    }
}
