package com.anangkur.beritaku.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anangkur.beritaku.data.model.ArticleEntity
import com.anangkur.beritaku.local.dao.AppDao
import com.anangkur.beritaku.local.db.constant.Const

@Database(entities = [ArticleEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase(){

    abstract fun getDao(): AppDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        Const.DATABASE_NAME
                    ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}