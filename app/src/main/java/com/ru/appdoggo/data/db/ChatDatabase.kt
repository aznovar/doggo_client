package com.ru.appdoggo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ru.appdoggo.data.cache.message.MessagesDao
import com.ru.appdoggo.domain.entities.chat.MessageEntity

@Database(entities = [MessageEntity::class], version = 5, exportSchema = false)
abstract class ChatDatabase: RoomDatabase() {

    abstract val messagesDao: MessagesDao

    companion object {
        @Volatile
        private var INSTANCE: ChatDatabase? = null

        fun getInstance(context: Context): ChatDatabase {

            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatDatabase::class.java,
                    "chat_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }

            return instance
        }
    }
}