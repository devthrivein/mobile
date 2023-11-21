package com.example.thrivein.data.dummy

import com.example.thrivein.data.model.Article
import java.util.Date

object ArticleDummy {

    fun dummyArticles(): List<Article> {
        var articles = arrayListOf<Article>()

        for (i in 1..5) {
            articles.add(
                Article(
                    id = i.toString(),
                    title = "Analysts project 32% upside for $i",
                    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt........",
                    bannerUrl = "https://www.littlethings.info/wp-content/uploads/2014/04/dummy-image-green-e1398449160839.jpg",
                    uploadedDate = Date(),
                )
            )
        }

        return articles
    }
}