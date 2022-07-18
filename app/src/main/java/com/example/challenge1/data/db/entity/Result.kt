package com.example.challenge1.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken


@Entity(tableName = "article_info")
data class Result(
    var abstract: String,
    var byline: String,
    var nytdsection: String,
    @SerializedName("published_date")
    var publishedDate: String,
    var section: String,
    var source: String,
    var subsection: String,
    var title: String,
    var type: String,
    var updated: String,
    var uri: String,
    var url: String,
    @PrimaryKey(autoGenerate = false)
    var id: Long

){
    constructor() : this("","","","","","","","","","",
        "","",0)
}

