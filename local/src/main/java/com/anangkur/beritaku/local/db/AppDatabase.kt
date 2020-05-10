package com.anangkur.beritaku.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anangkur.beritaku.data.model.ArticleEntity
import com.anangkur.beritaku.local.dao.AppDao
import com.anangkur.beritaku.local.db.constant.Const
import com.anangkur.beritaku.local.model.ArticleCached
import javax.inject.Inject

@Database(entities = [ArticleCached::class], version = 1)
abstract class AppDatabase: RoomDatabase(){

    abstract fun getDao(): AppDao

    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (INSTANCE == null){
            synchronized(this){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        Const.DATABASE_NAME)
                        .build()
                }
                return INSTANCE!!
            }
        }
        return INSTANCE!!
    }
}