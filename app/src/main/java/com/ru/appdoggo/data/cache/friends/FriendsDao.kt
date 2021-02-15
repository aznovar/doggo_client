package com.ru.appdoggo.data.cache.friends

import androidx.room.*
import com.ru.appdoggo.domain.entities.friends.FriendsEntity

@Dao
interface FriendsDao : FriendsCache {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(friendEntity: FriendsEntity): Long

    @Update
    fun update(friendEntity: FriendsEntity)

    @Transaction
    override fun saveFriend(entity: FriendsEntity) {
        if (insert(entity) == -1L) {
            update(entity)
        }
    }

    @Query("SELECT * from friends_table WHERE id = :key")
    override fun getFriend(key: Long): FriendsEntity?

    @Query("SELECT * from friends_table WHERE type = 0")
    override fun getFriends(): List<FriendsEntity>

    @Query("SELECT * from friends_table WHERE type = 1")
    override fun getFriendRequests(): List<FriendsEntity>
}