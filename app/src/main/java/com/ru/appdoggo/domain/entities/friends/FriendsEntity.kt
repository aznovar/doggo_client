package com.ru.appdoggo.domain.entities.friends

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "friends_table")
data class FriendsEntity(
    @SerializedName("username")
    var name: String,

    @PrimaryKey
    var id: Long,

    var type: Int
)