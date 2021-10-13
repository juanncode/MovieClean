package com.gitlab.juancode.moviesclean.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.gitlab.juancode.moviesclean.R
import com.gitlab.juancode.moviesclean.databinding.FragmentDetailBinding
import com.gitlab.juancode.moviesclean.ui.common.loadImage
import com.gitlab.juancode.moviesclean.ui.main.MainViewModel
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

class DetailFragment : Fragment(), AndroidScopeComponent {
    override val scope: Scope by fragmentScope()

    private var binding: FragmentDetailBinding? = null

    lateinit var navController: NavController
    private val args: DetailFragmentArgs by navArgs()

    val viewModel: DetailViewModel by viewModel{
        parametersOf(args.idMovie)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        binding?.viewmodel = viewModel
        binding?.lifecycleOwner = this@DetailFragment

        viewModel.model.observe(viewLifecycleOwner, {
            with(it.movie) {
                binding?.txtTitle?.text = title
                binding?.txtDescription?.text = overview
                binding?.txtPopularity?.text = voteAverage.toString()
                binding?.txtLanguage?.text = originalLanguage.uppercase()
                binding?.txtPublishing?.text = releaseDate
                binding?.imgPoster?.loadImage("https://image.tmdb.org/t/p/w780/${backdropPath}"?:"")
            }

        })


    }
}