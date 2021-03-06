package com.chairunissa.challengefive.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.chairunissa.challengefive.R
import com.chairunissa.challengefive.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {


    private val arguments : DetailFragmentArgs by navArgs()
    lateinit var binding : FragmentDetailBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var title = arguments.movieDetail.title
        var subtitle = arguments.movieDetail.subtitle
        var overview = arguments.movieDetail.overview
        var date = arguments.movieDetail.date
        var image = arguments.movieDetail.image
        var rating = arguments.movieDetail.rating

        binding.tvTitle.setText(title)
        binding.tvTagline.setText(subtitle)
        binding.tvOverview.setText(overview)
        binding.tvDate.setText(date)
        binding.tvRate.setText(rating)
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w780/"+image)
            .into(binding.ivPoster)

        binding.ivBack.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToHomeFragment())
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }


}