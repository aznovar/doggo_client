package com.ru.appdoggo.domain.entities.chat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "messages_table")
data class MessageEntity(
    @PrimaryKey
    @SerializedName("message_id")
    @ColumnInfo(name = "message_id")
    var id: Long,
    @SerializedName("sender_id")
    @ColumnInfo(name = "sender_id")
    var senderId: Long,
    @SerializedName("receiver_id")
    @ColumnInfo(name = "receiver_id")
    var receiverId: Long,
    var message: String,
    @SerializedName("message_date")
    @ColumnInfo(name = "message_date")
    var date: Long,
    @SerializedName("message_type_id")
    @ColumnInfo(name = "message_type_id")
    var type: Int,
    var contact: ContactEntity? = null,
    var fromMe: Boolean = false
) {
    constructor() : this(0L, 0L, 0L, "", 0L, 0, null, false)
}

data class ContactEntity(
    @SerializedName("user_id")
    var id: Long,
    var name: String,
    var image: String
)

class ContactConverter {
    @TypeConverter
    fun toString(contact: ContactEntity?): String? {
        return if (contact == null) null else "${contact.id}||${contact.name}||${contact.image}"
    }

    @TypeConverter
    fun toContact(string: String?): ContactEntity? {
        return if (string == null) {
            null
        } else {
            val arr = string.split("||")
            ContactEntity(arr[0].toLong(), arr[1], arr[2])
        }
    }
}