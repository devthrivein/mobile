package com.example.thrivein.data.network.response.auth

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(

    @field:SerializedName("store")
	val store: Store? = null,

    @field:SerializedName("user")
	val user: User? = null,
) : Parcelable

@Parcelize
data class User(

	@field:SerializedName("avatarUrl")
	val avatarUrl: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("user_id")
	val userId: Long? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("token")
	val token: String? = null,
) : Parcelable

@Parcelize
data class Store(

	@field:SerializedName("store_phone")
	val storePhone: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("store_email")
	val storeEmail: String? = null,

	@field:SerializedName("store_name")
	val storeName: String? = null,

	@field:SerializedName("type")
	val type: String? = null,
) : Parcelable
