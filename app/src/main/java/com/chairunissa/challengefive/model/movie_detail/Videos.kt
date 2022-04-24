package com.chairunissa.challengefive.model.movie_detail


import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("results")
    val results: List<Result>
)