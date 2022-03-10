package com.pedro.doggos.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedro.doggos.feature_home.data.local.BreedDao
import com.pedro.doggos.feature_home.data.local.entity.BreedEntity

@Database(
    entities = [BreedEntity::class],
    version = 1
)

abstract class DoggoDatabase: RoomDatabase() {
    abstract val breedDao: BreedDao
}