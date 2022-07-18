package com.example.challenge1.data.repository

import androidx.lifecycle.LiveData
import com.example.challenge1.data.db.current.ArticleEntry

interface NYTimesRepository {
    suspend fun getCurrentArticle(section: String, period: Int): LiveData<out List<ArticleEntry>>
}