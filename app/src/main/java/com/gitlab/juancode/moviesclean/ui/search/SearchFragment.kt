package com.gitlab.juancode.moviesclean.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gitlab.juancode.moviesclean.R
import com.gitlab.juancode.moviesclean.data.toServiceMovie
import com.gitlab.juancode.moviesclean.databinding.SearchFragmentBinding
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class SearchFragment : Fragment(), AndroidScopeComponent {
    override val scope: Scope by fragmentScope()

    lateinit var binding : SearchFragmentBinding
    lateinit var navController: NavController
    private val viewModel: SearchViewModel by viewModel()
    lateinit var searchAdapter: SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SearchFragment

        searchAdapter = SearchAdapter{
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it.id)
            navController.navigate(action)
        }
        binding.recyclerMovies.adapter = searchAdapter

        binding.editSearch.addTextChangedListener { text ->
            if (text.toString().isNotEmpty()) {
                viewModel.loadMovies(text.toString())
            }
        }

        viewModel.model.observe(viewLifecycleOwner, { model ->
            if (model != SearchViewModel.SearchModel.Loading) {
                binding.progressSearch.visibility = View.GONE
                binding.recyclerMovies.visibility = View.VISIBLE
            }

            when(model) {
                is SearchViewModel.SearchModel.Data -> {
                    searchAdapter.movies = model.data
                }
                SearchViewModel.SearchModel.Loading -> {
                    binding.progressSearch.visibility = View.VISIBLE
                    binding.recyclerMovies.visibility = View.GONE
                }
                SearchViewModel.SearchModel.Close -> navController.popBackStack()
            }
        })

    }

}