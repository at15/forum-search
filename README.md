# forum-search

[![Build Status](https://travis-ci.org/at15/forum-search.svg?branch=master)](https://travis-ci.org/at15/forum-search)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/790be2c9fe3b469c8a9660c6b8d67a43)](https://www.codacy.com/app/at15/forum-search)
[![codecov.io](https://codecov.io/github/at15/forum-search/coverage.svg?branch=master)](https://codecov.io/github/at15/forum-search?branch=master)
![codecov.io](https://codecov.io/github/at15/forum-search/branch.svg?branch=master)

爬取论坛数据, 存储在hadoop中, 进行分词和索引, 提供web界面用于搜索

## 文档

- [传送门](doc)

## 组成

项目主要分成以下几个部分

- crawler 按时爬取论坛数据
- uploader 将爬虫爬到的数据上传到hdfs
- indexer 分词, 生成索引, 存储到hdfs(?hbase)中
- manager 调度crawler, uploader, indexer
- manager ui, 显示crawler, uploader, indexer的状态
- search api 提供搜索的restful api
- search ui 提供搜索界面

### 各部分的细节

- crawler 待定
- uploader 调用hadoop的api上传
- indexer 在map reduce里进行分词和索引
- manger 和 search 使用 dropwizard
- ui 部分为spa(单页应用), 通过ajax调用api, api设置CORS

## 版权

使用了 HanLP 作为分词 https://github.com/hankcs/HanLP

TODO: 其他包的license...