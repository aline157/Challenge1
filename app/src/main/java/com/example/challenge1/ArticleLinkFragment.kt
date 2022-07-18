package com.example.challenge1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.challenge1.databinding.FragmentArticleLinkBinding

class ArticleLinkFragment : Fragment() {



    private lateinit var viewModel: ArticleLinkViewModel
    private var _binding: FragmentArticleLinkBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleLinkBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticleLinkViewModel::class.java)

        navController = Navigation.findNavController(view)
        val url = arguments?.getString("url")
        binding.articleLinkWebView.settings.javaScriptEnabled
        Log.e("linkFragment",url.toString())

        binding.articleLinkWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url.toString())
                return true
            }
        }
        binding.articleLinkWebView.loadUrl(url.toString())


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}