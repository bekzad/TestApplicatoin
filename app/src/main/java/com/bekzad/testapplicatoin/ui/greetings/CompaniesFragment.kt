package com.bekzad.testapplicatoin.ui.greetings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bekzad.testapplicatoin.databinding.CompaniesFragmentBinding
import com.bekzad.testapplicatoin.ui.GreetingViewModel

class CompaniesFragment : Fragment() {
    private lateinit var binding: CompaniesFragmentBinding
    private val viewModel by viewModels<GreetingViewModel>()
    private val adapter by lazy {
        CompaniesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = CompaniesFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        binding.companyRv.layoutManager = LinearLayoutManager(activity)
        binding.companyRv.adapter = adapter
        viewModel.user.observe(viewLifecycleOwner, { user ->
            user?.let {
                adapter.data = it.company
            }
        })
    }


    companion object {
        fun newInstance() = CompaniesFragment()
    }
}