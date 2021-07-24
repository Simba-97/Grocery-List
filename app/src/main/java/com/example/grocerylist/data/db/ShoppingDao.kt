package com.example.grocerylist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.grocerylist.data.db.entities.ShoppingItems

@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItems)

    @Delete
    suspend fun delete(item: ShoppingItems)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItems>>
}