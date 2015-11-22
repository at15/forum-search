# 项目说明

## 模块

- common 通用的entity和工具类, 比如对帖子,回复的定义
- crawler 爬虫, 打包后后为可执行jar
- indexer 索引构建工具, 包含索引的定义和map reduce任务
- tokenizer 分词器, 分词并返回权重,基于 HanLP
- uploader 上传爬虫文件到hdfs
- search-api 返回搜索结果

各个模块的使用方法见对应的scripts文件夹和单元测试

目前单元测试覆盖率 42% 

## 爬虫

- 基于webmagic
- 支持主贴和回复贴分开处理, 分别保存为 main-url.json, sub-url.json
- 爬取时存储帖子标题,作者,回复, 具体格式见 `forum.common.entity.ForumMainThread`
- 可以爬取所有DisuzzX3的论坛
- 可以自定义parser之后爬取其他网站,比如github
- 支持自定义线程, 调用jar时最后一个参数 (必须指定)

## 分词

- 基于HanLP
- 对HanLP做了patch用于提高分词和权重的效率, 避免调用两次分词. 相关issue https://github.com/hankcs/HanLP/issues/85
- patch内容在 `hanlp` package 下

## 上传

- 跟第一次作业内容相似,略

## 构建索引

索引结构如下

### 单索引文件(已弃用)

````
我 13 [{url:http://a.com/1.html, rank:2, pos:[{p:0,o:12},{p:1,o:13}]}]
````

- 我 为term
- 13 为对应的页面数量, 也可以作为term的整个rank
- 后面为json格式/自定义格式 的数组

数组内容为多个页面内该词的位置

````
[{url:http://a.com/1.html, rank:2, pos:[{p:0,o:12},{p:1,o:13}]}]
````
- url 页面地址, 等价于id
- rank 用于排序,通过tokenize部分得到
- pos term在该页面的所有位置,用于高亮和进一步权重计算, p 为帖子的位置, -1 为主贴, 0-n 为回复贴, o 为在帖子内容里的offset

###  多索引文件

使用MultipartsOutput在reducer中输出到多个文件

单词索引

```
我 13 DasdxA.term.idx
你 23 asdbxr.term.idx
```

- 我 和 13 同上
- DasDxA.term.idx 为term名称base64编码后的文件名

DasDxA.term.idx 文件内容

````
http://a.com/1.html {url:http://a.com/1.html, pos:[{p:0,o:12},{p:1,o:13}]}
http://a.com/2.html {url:http://a.com/2.html, pos:[{p:0,o:12}]}
````

- key 为url
- value 为该页面里单词出现的位置

文件内容和分词结果根据url从hdfs中获得, url和term统一使用base64编码作为文件名.

## 查询

- 启动时载入单词索引到内存, 提高查询速度
- 使用dropwizard提供json api, 根据单词索引再去查询hdfs对应的idx文件, 获取对应的文件

## 遇到的问题

- 爬虫爬的速度慢, 加线程, 但线程太多对被爬网站影响比较大
- 以term划分文件数量太大, hdfs会出现问题, 可以改成输出到hbase里, 或者合并多个索引文
- hadoop处理速度慢, 换spark, 还没试