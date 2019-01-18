package moura.renan.data.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import moura.renan.data.model.Note


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        private var instance: NoteDatabase? = null
        private val lock = Any()


        fun getInstance(context: Context): NoteDatabase? {
            if (instance == null) {
                synchronized(lock) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note_database")
                            .fallbackToDestructiveMigration().build()
                    }
                    return instance as NoteDatabase
                }

            }
            return instance as NoteDatabase
        }

    }
}

