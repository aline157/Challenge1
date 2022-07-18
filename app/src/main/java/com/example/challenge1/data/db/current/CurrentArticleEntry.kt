package com.example.challenge1.data.db.current

import androidx.room.ColumnInfo

data class CurrentArticleEntry(
    @ColumnInfo(name = "abstract")
    override var abstract: String,
    @ColumnInfo(name = "byline")
    override var byline: String,
    @ColumnInfo(name = "nytdsection")
    override var nytdsection: String,
    @ColumnInfo(name = "publishedDate")
    override var publishedDate: String,
    @ColumnInfo(name = "section")
    override var section: String,
    @ColumnInfo(name = "source")
    override var source: String,
    @ColumnInfo(name = "subsection")
    override var subsection: String,
    @ColumnInfo(name = "title")
    override var title: String,
    @ColumnInfo(name = "type")
    override var type: String,
    @ColumnInfo(name = "updated")
    override var updated: String,
    @ColumnInfo(name = "uri")
    override var uri: String,
    @ColumnInfo(name = "url")
    override var url: String,
    @ColumnInfo(name = "id")
    override var id: Long
):ArticleEntry{
    constructor() : this("","","","","","","","","",
        "","","",0)
}