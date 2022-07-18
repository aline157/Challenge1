package com.example.challenge1.data.repository

import androidx.lifecycle.LiveData
import com.example.challenge1.data.db.CurrentArticleDao
import com.example.challenge1.data.db.current.ArticleEntry
import com.example.challenge1.data.db.current.CurrentArticleEntry
import com.example.challenge1.data.network.NYTimesDataSource
import com.example.challenge1.data.network.responce.MostViewedSectionResponce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime


class NYTimesRepositoryImpl(
    private val  currentArticleDao: CurrentArticleDao,
    private val nyTimesDataSource: NYTimesDataSource
) : NYTimesRepository {
    init {
        nyTimesDataSource.apply {
            downloadedCurrentArticle.observeForever{newCurrentArticle ->
                persistFetchedCurrentArticle(newCurrentArticle)
            }
        }
    }
    override suspend fun getCurrentArticle(section: String, period: Int): LiveData<out List<CurrentArticleEntry>> {
        return withContext(Dispatchers.IO){
            initArticleData(section, period)
            return@withContext currentArticleDao.getArticleMetric()
        }
    }

    private fun persistFetchedCurrentArticle(fetchedArticle: MostViewedSectionResponce){
        fun deleteOldArticleData() {
            currentArticleDao.deleteAllArticles()
        }

        GlobalScope.launch(Dispatchers.IO) {
            deleteOldArticleData()

            currentArticleDao.insert(fetchedArticle.results)
        }
    }

    private suspend fun initArticleData(section: String, period: Int){
        nyTimesDataSource.fetchCurrentArticle(
            section,period
        )
    }
    private suspend fun fetchCurrentArticle(){
        nyTimesDataSource.downloadedCurrentArticle
    }

//    private fun isFetchCurrentNeeded(lastFetchedTime: ZonedDateTime):Boolean{
//        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
//        return lastFetchedTime.isBefore(thirtyMinutesAgo)
//    }


}