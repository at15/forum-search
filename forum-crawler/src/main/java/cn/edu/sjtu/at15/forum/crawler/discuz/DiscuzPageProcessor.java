package cn.edu.sjtu.at15.forum.crawler.discuz;

import cn.edu.sjtu.at15.forum.common.entity.ForumMainThread;
import cn.edu.sjtu.at15.forum.common.entity.ForumThread;
import org.jsoup.nodes.Document;
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

        // deal with pagination first for both thread and threadList, add the links if it does have pagination
        PaginationParser paginationParser = new PaginationParser(html);
        if (paginationParser.hasPagination()) {
            page.addTargetRequests(paginationParser.getPageLinks());
        }

        // use the parsed document
        Document document = paginationParser.getDocument();

        // deal with thread
        if (discuzUrl.isThread(currentUrl)) {
            LOGGER.debug("processing thread");
            page.putField("url", currentUrl);
            page.putField("main-thread", null);
            page.putField("thread", null);

            if (discuzUrl.isMainThread(currentUrl)) {
                MainThreadParser mainThreadParser = new MainThreadParser(document);
                ForumMainThread forumMainThread = mainThreadParser.getThread(currentUrl);
                page.putField("main-thread", forumMainThread);
            } else {
                String mainThreadUrl = discuzUrl.getMainThreadUrl(currentUrl);
                ThreadParser threadParser = new ThreadParser(document);
                ForumThread forumThread = threadParser.getThread(currentUrl, mainThreadUrl);
                page.putField("thread", forumThread);
            }
            return;
        }

        // try to parse as thread list
        ThreadListParser threadListParser = new ThreadListParser(html, discuzUrl.getBaseUrl());
        if (threadListParser.hasThreads()) {
            page.addTargetRequests(threadListParser.getThreadLinks());
            return;
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
