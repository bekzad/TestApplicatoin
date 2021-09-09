package com.bekzad.testapplicatoin.data.source

import com.bekzad.testapplicatoin.data.domain.Company
import com.bekzad.testapplicatoin.data.domain.User
import com.bekzad.testapplicatoin.data.domain.UserHolder
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class FakeServer {

    fun getData(): String {

//        sleep(3000)

        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<UserHolder> = moshi.adapter(UserHolder::class.java)
        val jsonString = jsonAdapter.toJson(createData())
        return jsonString
    }

    fun createData(): UserHolder {
        val photoLink = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/6.png"
        val company1 = Company("TestCompany", "Developer")
        val company2 = Company("Google", "Developer")

        val companies = listOf(company1, company2)
        val user = User("User", photoLink, "User_Second",
            1, companies)
        return UserHolder(user)
    }
}