package com.gmail.hromatizm.gson


import com.google.gson.annotations.SerializedName

data class ArticleResult(
    @SerializedName("value")
    val value: List<Value>
)