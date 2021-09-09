package com.bekzad.testapplicatoin.data.repository

import androidx.lifecycle.LiveData
import com.bekzad.testapplicatoin.data.domain.User
import com.bekzad.testapplicatoin.data.source.DataSource

class RepositoryImpl(private val dataSource: DataSource) {

    suspend fun getData(): LiveData<User> {
        return dataSource.getData()
    }

}