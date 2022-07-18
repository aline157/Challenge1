package com.example.challenge1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge1.data.repository.NYTimesRepository


class NewsViewModelFactory(
    private val nyTimesRepository: NYTimesRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(nyTimesRepository) as T
    }
}