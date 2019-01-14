package moura.renan.architectureexample.dao

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import moura.renan.architectureexample.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {




    abstract fun noteDatabase(): NoteDatabase

    companion object {
        var instance : NoteDatabase? = null
        fun getInstance(context : Context) : NoteDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,NoteDatabase::class.java,"note_database").fallbackToDestructiveMigration().build()
            }
            return  instance
        }
    }

}