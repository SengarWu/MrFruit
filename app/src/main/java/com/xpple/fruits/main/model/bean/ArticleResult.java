package com.xpple.fruits.main.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */

public class ArticleResult {
    private List<Article> articles;
    private int dataCount;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }
}
