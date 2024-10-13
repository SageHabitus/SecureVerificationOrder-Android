package com.seongjae.secureorder.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OcrDataModel(
    @SerialName("extractedText")
    val extractedText: String
)