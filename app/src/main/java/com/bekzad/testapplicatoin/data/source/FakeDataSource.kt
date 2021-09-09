package com.bekzad.testapplicatoin.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bekzad.testapplicatoin.data.domain.User
import com.bekzad.testapplicatoin.data.domain.UserHolder
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class FakeDataSource : DataSource {

    override suspend fun getData(): LiveData<User> {
        val livedata = MutableLiveData<User>()

        val fakeServer = FakeServer()
        val jsonString = fakeServer.getData()

        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<UserHolder> = moshi.adapter(UserHolder::class.java)
        val userHolder = jsonAdapter.fromJson(jsonString)

        livedata.value = userHolder?.user

        return livedata
    }

}