package com.pedro.doggos.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pedro.doggos.feature_search.data.local.BreedDao
import com.pedro.doggos.feature_search.data.local.entity.BreedEntity

@Database(
    entities = [BreedEntity::class],
    version = 3
)

abstract class DoggoDatabase: RoomDatabase() {
    abstract val breedDao: BreedDao

    companion object {

        @Volatile
        private var INSTANCE: DoggoDatabase? = null

        fun getInstance(context: Context): DoggoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DoggoDatabase::class.java, "Doggo.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}