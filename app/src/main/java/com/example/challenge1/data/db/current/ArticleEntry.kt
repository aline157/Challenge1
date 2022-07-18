package com.example.challenge1.data.db.current

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

interface ArticleEntry {
    val abstract: String
    val byline: String
    val nytdsection: String
    val publishedDate: String
    val section: String
    val source: String
    val subsection: String
    val title: String
    val type: String
    val updated: String
    val uri: String
    val url: String
    val id: Long
}