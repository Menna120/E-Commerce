package com.route.data.dto.product

import com.google.gson.annotations.SerializedName


data class ProductRequestModel(

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("results")
	val results: Int? = null
)

data class Category(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class Metadata(

	@field:SerializedName("numberOfPages")
	val numberOfPages: Int? = null,

	@field:SerializedName("nextPage")
	val nextPage: Int? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null
)

data class Brand(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class DataItem(

	@field:SerializedName("sold")
	val sold: Double? = null,

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("availableColors")
	val availableColors: List<Any?>? = null,

	@field:SerializedName("imageCover")
	val imageCover: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("ratingsQuantity")
	val ratingsQuantity: Int? = null,

	@field:SerializedName("ratingsAverage")
	val ratingsAverage: Double? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

//	@field:SerializedName("id")
//	val id: String? = null,

	@field:SerializedName("subcategory")
	val subcategory: List<SubcategoryItem?>? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("brand")
	val brand: Brand? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("priceAfterDiscount")
	val priceAfterDiscount: Int? = null
)

data class SubcategoryItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)
