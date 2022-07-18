package com.example.challenge1

import androidx.lifecycle.ViewModel
import com.example.challenge1.data.repository.NYTimesRepository
import com.example.challenge1.internal.lazyDefered


class NewsViewModel(
    private val nyTimesRepository: NYTimesRepository
): ViewModel()  {
//    val section = ArticleSections.EMAILED
//    val period = ArticlePeriod.SEVEN
    val section = "emailed"
    val period = 7



    val article by lazyDefered {
        nyTimesRepository.getCurrentArticle(section, period)
    }
}