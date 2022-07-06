package com.gmail.hromatizm

import com.gmail.hromatizm.gson.ArticleResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface GetNewsService {
    @Headers(
        "X-RapidAPI-Key: 1c7464e447mshbe93968b68eae9ep158667jsnf3f13e19d9d5",
        "X-RapidAPI-Host: contextualwebsearch-websearch-v1.p.rapidapi.com"
    )

    @GET("/api/search/NewsSearchAPI?q=russia&pageNumber=13&pageSize=50&autoCorrect=true&fromPublishedDate=null&toPublishedDate=null")
    fun getNews(
    ): Call<ArticleResult>
}