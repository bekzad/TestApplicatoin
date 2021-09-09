package com.bekzad.testapplicatoin.ui.greetings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.bekzad.testapplicatoin.R
import com.bekzad.testapplicatoin.databinding.GreetingFragmentBinding
import com.bekzad.testapplicatoin.ui.GreetingViewModel

class GreetingFragment : Fragment() {

    private lateinit var binding: GreetingFragmentBinding
    private val viewModel by viewModels<GreetingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = GreetingFragmentBinding.inflate(inflater, container, false)
        setupContinueBtn()

        return binding.root
    }

    private fun setupContinueBtn() {
        binding.continueBtn.setOnClickListener {
            transitionToCompaniesFragment()
        }
    }

    private fun transitionToCompaniesFragment() {
        val companiesFragment = CompaniesFragment.newInstance()
        requireActivity().supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in_from_right,
                0,
                0,
                R.anim.slide_out_from_left)
            replace(R.id.container, companiesFragment)
            addToBackStack(null)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        // If we are using livedata object with our binding class we must set the lifecycleowner
        binding.lifecycleOwner = viewLifecycleOwner
        setUpDropDownMenu()

        viewModel.user.observe(viewLifecycleOwner, { user ->
            user?.apply {
                println(this.firstName)
                println(this.education)
            }
        })

    }

    private fun setUpDropDownMenu() {
        viewModel.dropDownMenu.observe(viewLifecycleOwner, { list ->
            list?.let {
                val dropdownAdapter =
                    ArrayAdapter(requireContext(), R.layout.education_list_item, it)
                binding.autoCompleteTv.setAdapter(dropdownAdapter)
            }
        })

    }

    companion object {
        fun newInstance() = GreetingFragment()
    }
}