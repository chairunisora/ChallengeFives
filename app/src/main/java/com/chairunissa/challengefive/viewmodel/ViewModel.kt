package com.chairunissa.challengefive.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chairunissa.challengefive.model.get_movie.DetailMovieModel
import com.chairunissa.challengefive.model.get_movie.GetMovieResponseItem
import com.chairunissa.challengefive.model.get_movie.Result
import com.chairunissa.challengefive.model.movie_detail.MovieDetail
import com.chairunissa.challengefive.model.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : ViewModel() {

    private val mId = MutableLiveData<Int>()
    private val detail: MutableLiveData<MovieDetail> by lazy {
        MutableLiveData<MovieDetail>().also {
            getAllMovies()
        }
    }
    private val movies: MutableLiveData<List<Result>> by lazy {
        MutableLiveData<List<Result>>().also {
            getDetailMovies(mId)
        }
    }


    fun getDetail(): LiveData<MovieDetail> {
        return detail
    }

    fun getMovies(): LiveData<List<Result>> {
        return movies
    }


    private fun getAllMovies() {
        MovieApi.retrofitService.allMovies().enqueue(object : Callback<GetMovieResponseItem> {
            override fun onResponse(
                call: Call<GetMovieResponseItem>,
                response: Response<GetMovieResponseItem>
            ) {
                movies.value = response.body()?.results
            }

            override fun onFailure(call: Call<GetMovieResponseItem>, t: Throwable) {
                Log.d("Tag", t.message.toString())
            }

        })
    }

    fun getDetailMovies(idMovies: MutableLiveData<Int>) {
        MovieApi.retrofitService.getDetail().enqueue(object : Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                detail.value = response.body()
                print(response.body().toString())
                Log.d("Response Detail Success", response.body().toString())
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                print("ERROR MESSAGE : " + t.message.toString())
                Log.d("Response Detail", t.message.toString())
            }

        })
    }
}