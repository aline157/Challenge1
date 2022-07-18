package com.example.challenge1.data.network

import com.example.challenge1.data.network.responce.MostViewedSectionResponce
import com.example.challenge1.data.network.ConnectivityInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val API_KEY = "91ViFFYPWK8aUPEznUzaAmgtbnUwtbGk"


//https://api.nytimes.com/svc/mostpopular/v2/emailed/7.json?api-key=91ViFFYPWK8aUPEznUzaAmgtbnUwtbGk
interface NYApiService {
    @GET(value = "{section}/{period}.json")
    fun getMostViewed(@Path("section") section: String,
                      @Path("period") period: Int): Deferred<MostViewedSectionResponce>

    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ):NYApiService{
            val requestInterceptor = Interceptor{chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api-key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return  Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NYApiService::class.java)
        }
    }
}