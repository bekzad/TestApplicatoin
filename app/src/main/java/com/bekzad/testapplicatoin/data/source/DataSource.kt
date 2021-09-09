package com.bekzad.testapplicatoin.data.source

import androidx.lifecycle.LiveData
import com.bekzad.testapplicatoin.data.domain.User

interface DataSource {

    suspend fun getData(): LiveData<User>

}