package com.example.todoproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Item::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: ItemDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): ItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "todo_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(ItemDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class ItemDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.itemDao())
                    }
                }
            }
        }

        fun populateDatabase(itemDao: ItemDao) {
            itemDao.deleteAll()
        }
    }
}