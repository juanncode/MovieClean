package com.gitlab.juancode.moviesclean.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gitlab.juancode.moviesclean.R
import com.gitlab.juancode.moviesclean.data.toServiceMovie
import com.gitlab.juancode.moviesclean.databinding.FragmentMainBinding
import com.gitlab.juancode.moviesclean.ui.common.Event
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class MainFragment : Fragment(), AndroidScopeComponent {
    override val scope: Scope by fragmentScope()

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModel()
    lateinit var navController: NavController
    lateinit var movieAdapter: MovieAdapter

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.forEach { isGrantedMap ->
                if (isGrantedMap.key == Manifest.permission.ACCESS_COARSE_LOCATION) {
                    viewModel.onCoarsePermissionRequested()
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        navController = view.findNavController()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@MainFragment

        movieAdapter = MovieAdapter {
            viewModel.onItemClicked(it)
        }

        binding.moviesRecycler.adapter = movieAdapter

        viewModel.modelMovie.observe(viewLifecycleOwner, { model ->
            when (model) {
                is MainViewModel.UiMovie.Data -> {
                    movieAdapter.movies = model.movies
                    binding.progressData.visibility = View.GONE
                }
                MainViewModel.UiMovie.Loading -> {
                    binding.progressData.visibility = View.VISIBLE
                }
            }
        })

        viewModel.requestPermission.observe(viewLifecycleOwner, Event.EventObserver {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.SEND_SMS
                )
            )
        })

        viewModel.navigateToMovie.observe(viewLifecycleOwner, Event.EventObserver {
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(it.toServiceMovie())
            navController.navigate(action)
        })

        viewModel.navigateToSearch.observe(viewLifecycleOwner, Event.EventObserver {
            val action = MainFragmentDirections.actionMainFragmentToSearchFragment()
            navController.navigate(action)
        })
    }

}