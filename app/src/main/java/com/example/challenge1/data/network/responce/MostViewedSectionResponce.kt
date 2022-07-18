package com.example.challenge1.data.network.responce


import com.example.challenge1.data.db.entity.Result
import com.google.gson.annotations.SerializedName

data class MostViewedSectionResponce(
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    val results: List<Result>
)