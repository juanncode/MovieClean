package com.gitlab.juancode.moviesclean.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.gitlab.juancode.moviesclean.R
import com.gitlab.juancode.moviesclean.databinding.FragmentDetailBinding
import com.gitlab.juancode.moviesclean.ui.common.loadImage

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.lifecycleOwner = this@DetailFragment

        binding?.txtTitle?.text = args.movie.title
        binding?.txtDescription?.text = args.movie.overview
        binding?.txtPopularity?.text = args.movie.voteAverage.toString()
        binding?.txtLanguage?.text = args.movie.originalLanguage.uppercase()
        binding?.txtPublishing?.text = args.movie.releaseDate
        binding?.imgPoster?.loadImage("https://image.tmdb.org/t/p/w780/${args.movie.backdropPath}"?:"")
    }
}