package com.example.challenge1.data.network

import androidx.lifecycle.LiveData
import com.example.challenge1.data.network.responce.MostViewedSectionResponce

interface NYTimesDataSource{
    val downloadedCurrentArticle: LiveData<MostViewedSectionResponce>

    suspend fun fetchCurrentArticle(
        section: String,
        period: Int
    )
}