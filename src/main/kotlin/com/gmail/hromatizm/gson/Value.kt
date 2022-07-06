package com.gmail.hromatizm.gson


import com.google.gson.annotations.SerializedName

data class Value(
    @SerializedName("body")
    val body: String,
    @SerializedName("datePublished")
    val datePublished: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: Image,
    @SerializedName("language")
    val language: String,
    @SerializedName("provider")
    val provider: ProviderX,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)