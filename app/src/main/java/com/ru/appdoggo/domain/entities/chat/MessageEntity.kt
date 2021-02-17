package com.ru.appdoggo.domain.entities.chat

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "messages_table")
@TypeConverters(ContactConverter::class)
data class MessageEntity(
    @PrimaryKey
    @ColumnInfo(name = "message_id")
    var id: Long,
    @ColumnInfo(name = "sender_id")
    var senderId: Long,
    @ColumnInfo(name = "receiver_id")
    var receiverId: Long,
    var message: String,
    @SerializedName("messageDate")
    @ColumnInfo(name = "message_date")
    var date: Long,
    @SerializedName("messageTypeId")
    @ColumnInfo(name = "message_type_id")
    var type: Int,
    var contact: ContactEntity? = null,
    var fromMe: Boolean = false
) {
    constructor() : this(0L, 0L, 0L, "", 0L, 0, null, false)
}

data class ContactEntity(
    var id: Long,
    @SerializedName("username")
    var name: String
)

class ContactConverter {
    @TypeConverter
    fun toString(contact: ContactEntity?): String? {
        return if (contact == null) null else "${contact.id}||${contact.name}"
    }

    @TypeConverter
    fun toContact(string: String?): ContactEntity? {
        return if (string == null) {
            null
        } else {
            val arr = string.split("||")
            ContactEntity(arr[0].toLong(), arr[1])
        }
    }
}