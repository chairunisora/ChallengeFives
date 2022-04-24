package com.chairunissa.challengefive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.chairunissa.challengefive.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    private val viewModel: ViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        Log.d("TAG","Test")
//        setupObserver()
    }

//    private fun setupObserver() {
//        viewModel.getCars().observe(this){
//            print("Main activity : datanya -> $it")
//        }
//    }

//    private fun updateUi() {
//        viewModel.mCount.observe(this){
//            resultCount ->
//            binding.tvNumber.setText(resultCount.toString())
//        }
//    }

}