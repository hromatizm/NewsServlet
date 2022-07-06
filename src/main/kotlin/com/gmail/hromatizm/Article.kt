package com.gmail.hromatizm

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "article")
class Article(
    @Id
    @Column
    val id: ULong,
    @Column
    val provider: String?,
    @Column(name = "date_published")
    val datePublished: String,
    @Column
    val language: String,
    @Column
    val url: String,
    @Column
    val title: String,
    @Column
    val description: String?,
    @Column
    val body: String,
    @Column(name = "image_url")
    val imageUrl: String?
) : Serializable