package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.common.entity.ForumMainThread;
import cn.edu.sjtu.at15.forum.common.entity.ForumThread;
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
            page.putField("url", currentUrl);
            page.putField("main-thread", null);
            page.putField("thread", null);

            PaginationParser paginationParser;
            if (discuzUrl.isMainThread(currentUrl)) {
                MainThreadParser mainThreadParser = new MainThreadParser(html);
                ForumMainThread forumMainThread = mainThreadParser.getThread(currentUrl);
                page.putField("main-thread", forumMainThread);
                paginationParser = new PaginationParser(mainThreadParser.getDocument());
            } else {
                String mainThreadUrl = discuzUrl.getMainThreadUrl(currentUrl);
                ThreadParser threadParser = new ThreadParser(html);
                ForumThread forumThread = threadParser.getThread(currentUrl, mainThreadUrl);
                page.putField("thread", forumThread);
                paginationParser = new PaginationParser(threadParser.getDocument());
            }

            // add the reset pages
            if (paginationParser.hasPagination()) {
                page.addTargetRequests(paginationParser.getPageLinks());
            }
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

    public static void main(String[] args) throws Exception {
        Spider.create(new DiscuzPageProcessor("http://www.1point3acres.com/bbs/"))
//                .addUrl("http://www.1point3acres.com/bbs/forum.php?mod=guide&view=hot")
                .addUrl("http://www.1point3acres.com/bbs/thread-147944-1-1.html")
//                .addUrl("http://www.1point3acres.com/bbs/thread-147944-2-1.html")
//                .addPipeline(new ConsolePipeline())
                .addPipeline(new JsonFilePipeline("data"))
                .thread(5)
                .run();
    }
}
