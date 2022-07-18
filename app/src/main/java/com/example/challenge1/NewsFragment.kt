package com.example.challenge1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge1.databinding.FragmentMoreBinding
import com.example.challenge1.databinding.FragmentNewsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.android.x.closestKodein

class NewsFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: NewsViewModelFactory by instance()

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>? =null

    private lateinit var viewModel: NewsViewModel
    private lateinit var navController: NavController
    private var progressBar: ProgressBar? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,viewModelFactory).get(NewsViewModel::class.java)

        binding.progressBar.visibility= View.VISIBLE

        navController = Navigation.findNavController(view)
        bindUI()

        binding.NYItemsListRefresh.setOnRefreshListener {
//            binding.newsLottieAnimationView.setVisibility(View.VISIBLE)
            binding.progressBar.visibility= View.VISIBLE
            refreshUI()
        }
    }

    private fun bindUI()= launch(Dispatchers.Main){
        val currentArticle = viewModel.article.await()

        currentArticle.observe(viewLifecycleOwner, {
            if (it == null) return@observe

//            binding.newsLottieAnimationView.setVisibility(View.INVISIBLE)
            binding.progressBar.visibility= View.INVISIBLE
            layoutManager = LinearLayoutManager(requireContext())
            binding.newsRecyclerView.layoutManager = layoutManager
            adapter = NewsRecyclerAdapter(it,navController)
            binding.newsRecyclerView.adapter = adapter

        })
    }

    private fun refreshUI()= launch(Dispatchers.Main){
        val currentArticle = viewModel.article.await()

        currentArticle.observe(viewLifecycleOwner, {
            if (it == null) return@observe

//            binding.newsLottieAnimationView.setVisibility(View.INVISIBLE)
            binding.progressBar.visibility= View.INVISIBLE
            adapter = NewsRecyclerAdapter(it,navController)
            binding.newsRecyclerView.adapter = adapter
            binding.NYItemsListRefresh.isRefreshing = false

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}