# forum-search

[![Build Status](https://travis-ci.org/at15/forum-search.svg?branch=master)](https://travis-ci.org/at15/forum-search)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/790be2c9fe3b469c8a9660c6b8d67a43)](https://www.codacy.com/app/at15/forum-search)
[![codecov.io](https://codecov.io/github/at15/forum-search/coverage.svg?branch=master)](https://codecov.io/github/at15/forum-search?branch=master)
![codecov.io](https://codecov.io/github/at15/forum-search/branch.svg?branch=master)

Craw forum data and store in hadoop, use mapreduce to tokenize and index the threads, provide search based on the built index.

## Document

see `README.md` in every module, the doc folder is currently empty.

## Modules

The project is made by the following modules

- crawler  craw forum data
- uploader upload craw data to remote hdfs (I prefer to use the hdfs -put on local environment)
- indexer tokenize using HanLP
- manager (not implemented) control crawler, uploader and indexer
- manager ui, (not implemented) show the current status of crawler, uploader and indexer
- search api (not implemented) provide restful api for search, may use dropwizard or spring boot
- search ui (not implemented) provide a web interface for search & advanced search

## Copyright

- use **HanLP** for tokenize Chinese https://github.com/hankcs/HanLP

TODO: Other packages' license