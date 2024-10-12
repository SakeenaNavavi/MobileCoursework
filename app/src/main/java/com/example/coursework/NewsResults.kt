package com.example.coursework

class NewsResults {
    constructor()

    private var articles: List<News>? = null

    constructor(articles: List<News>?) {
        this.articles = articles
    }

    fun getArticles(): List<News>? {
        return articles
    }

    fun setArticles(articles: List<News>?) {
        this.articles = articles
    }
}