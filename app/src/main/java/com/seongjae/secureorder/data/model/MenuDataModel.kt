package com.seongjae.secureorder.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 편의상 하나의 DTO로 합침
 * 정석은 local, remote, data, ui 계층 별로 책임을 분리해야 함
 * 현재 필드명 구조상으로는 @serialName도 사실은 생략 가능
 */
@Serializable
@Entity(tableName = "menu")
data class MenuDataModel(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerialName("id")
    val id: String,

    @ColumnInfo(name = "name")
    @SerialName("name")
    val name: String,

    @ColumnInfo(name = "category")
    @SerialName("category")
    val category: String,

    @ColumnInfo(name = "instructions")
    @SerialName("instructions")
    val instructions: String,

    @ColumnInfo(name = "thumbnail")
    @SerialName("thumbnail")
    val thumbnail: String
)