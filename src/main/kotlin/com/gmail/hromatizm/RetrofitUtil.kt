package com.gmail.hromatizm

import com.gmail.hromatizm.gson.ArticleResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CountDownLatch

object RetrofitUtil {
    private val service = Retrofit.Builder()
        .baseUrl("https://contextualwebsearch-websearch-v1.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GetNewsService::class.java)

    private val callResult: Call<ArticleResult> = service.getNews()

    fun getArticleList(): Response<ArticleResult>? {
        var articleResult: Response<ArticleResult>? = null
        val countDownLatch = CountDownLatch(1)
        callResult.enqueue(object : Callback<ArticleResult> {
            override fun onResponse(call: Call<ArticleResult>, response: Response<ArticleResult>) {
                println("onResponse")
                articleResult = response
                countDownLatch.countDown()
            }

            override fun onFailure(call: Call<ArticleResult>, t: Throwable) {
                println("onFailure")
                countDownLatch.countDown()
            }
        })
        countDownLatch.await()
        return articleResult
    }
}