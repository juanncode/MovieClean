package com.gitlab.juancode.moviesclean.ui.main

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gitlab.juancode.moviesclean.NavHostActivity
import com.gitlab.juancode.moviesclean.R
import com.gitlab.juancode.moviesclean.databinding.FragmentMainBinding
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

        movieAdapter = MovieAdapter()
        binding.moviesRecycler.adapter = movieAdapter

        viewModel.message.observe(viewLifecycleOwner, {
        })
    }


}