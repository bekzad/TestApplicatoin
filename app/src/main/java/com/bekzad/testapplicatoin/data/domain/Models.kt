package com.bekzad.testapplicatoin.data.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Company(
    val name: String,
    val position: String
)

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "first_name") val firstName: String,
    val photo: String,
    @Json(name = "second_name") val secondName: String,
    val education: Int,
    val company: List<Company>)


@JsonClass(generateAdapter = true)
data class UserHolder(
    val user: User
)

