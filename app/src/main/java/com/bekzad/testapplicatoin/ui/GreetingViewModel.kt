package com.bekzad.testapplicatoin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bekzad.testapplicatoin.data.domain.User
import com.bekzad.testapplicatoin.data.repository.RepositoryImpl
import com.bekzad.testapplicatoin.data.source.FakeDataSource
import kotlinx.coroutines.launch

class GreetingViewModel : ViewModel() {

    lateinit var user: LiveData<User>

    val dropDownMenu = MutableLiveData<List<String>>()

    // Repository needs to be injected here,
    // however to keep it simple I just created it here
    private val repository = RepositoryImpl(FakeDataSource())

    init {

        viewModelScope.launch {
            user = repository.getData()
        }

        dropDownMenu.value = listOf("High School", "Bachelor", "Master", "Doctoral")
    }
}