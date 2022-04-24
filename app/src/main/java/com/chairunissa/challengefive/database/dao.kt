package com.chairunissa.challengefive.database

import androidx.room.*

@Dao
interface dao {

    @Query("SELECT username From entity Where username=(:user) AND password=(:pass)")
    fun login(user:String,pass:String) : String

    @Query("Select id From entity Where username=(:username)")
    fun getId(username:String) : Int

    @Query("SELECT * From entity Where id=(:id)")
    fun getUser(id:Int):entity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user : entity): Long

    @Update
    fun updateUser(user: entity):Int

    @Delete
    fun deleteUser(user: entity):Int
}