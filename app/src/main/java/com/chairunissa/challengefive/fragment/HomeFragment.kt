package com.chairunissa.challengefive.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chairunissa.challengefive.R
import com.chairunissa.challengefive.adapter.HomeAdapter
import com.chairunissa.challengefive.database.UserDatabase
import com.chairunissa.challengefive.databinding.FragmentHomeBinding
import com.chairunissa.challengefive.viewmodel.ViewModel


class HomeFragment : Fragment() {


    private val sharedPref = "sharedpreferences"

    private val secondViewModel: ViewModel by viewModels()
    lateinit var binding : FragmentHomeBinding
    lateinit var username : String
    private val mId = MutableLiveData<Int>()


    private var user_db : UserDatabase?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user_db = UserDatabase.getInstance(requireContext())
        mId.postValue(68726)

        val sharedPreferences : SharedPreferences = requireActivity().getSharedPreferences(sharedPref, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()

        username = sharedPreferences.getString("username","null").toString()

        binding.tvUsername.setText("Welcome, $username")
        getIdUsername()

        setupSecondObserver()

        secondViewModel.getDetailMovies(mId)
        binding.ivProfile.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
        }

    }

    private fun getIdUsername() {
        val sharedPreferences : SharedPreferences = requireActivity().getSharedPreferences(sharedPref, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        Thread{
            var result = user_db?.UserDao()?.getId(username)
            activity?.runOnUiThread {
                if(result!=null){
                    var id = result

                    editor.putInt("id",id)
                    editor.apply()
                    Log.d("ID",id.toString())
                }else{
                    Log.d("ID","Null Id")
                }
            }
        }.start()
    }


    //Get Movie List
    private fun setupSecondObserver() {
        Log.d("Tag","Fragment activity : datanya ->")
        secondViewModel.getMovies().observe(requireActivity()){
            if(it==null){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.INVISIBLE
            }
            Log.d("Tag","Fragment activity : datanya -> $it")
            val adapter = HomeAdapter(it)
            val layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,false)
            binding.rvMain.layoutManager = layoutManager
            binding.rvMain.adapter = adapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


}