package com.chairunissa.challengefive.model.network

import com.chairunissa.challengefive.model.get_movie.GetMovieResponseItem
import com.chairunissa.challengefive.model.movie_detail.MovieDetail
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.themoviedb.org/"
private const val API_KEY = "ad9cf320b31511270a9eac278fa5a04a"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService{

    @GET("3/movie/634649/recommendations?api_key=$API_KEY&language=en-US&page=1")
    fun allMovies(): Call<GetMovieResponseItem>

    @GET("3/movie/68726?api_key=$API_KEY&append_to_response=videos")
    fun getDetail(): Call<MovieDetail>
}

object MovieApi{
    private val logging: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val instance: MovieApiService by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(MovieApiService::class.java)
    }
    val retrofitService:MovieApiService by lazy{retrofit.create(MovieApiService::class.java)}
}