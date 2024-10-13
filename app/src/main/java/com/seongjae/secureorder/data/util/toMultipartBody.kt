package com.seongjae.secureorder.data.util

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

fun File.toMultipartBody(partName: String = "file"): MultipartBody.Part {
    val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), this)
    return MultipartBody.Part.createFormData(partName, this.name, requestBody)
}