package moura.renan.architectureexample.data.dao

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import moura.renan.architectureexample.data.model.Note


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        private var instance: NoteDatabase? = null

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance!!).execute()
            }
        }



        fun getInstance(context: Context): NoteDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build()
            }
            return instance
        }

    }

    class PopulateDbAsyncTask  constructor(db: NoteDatabase) : AsyncTask<Void, Void, Void>() {
        private val noteDao: NoteDao = db.getNoteDao()

        override fun doInBackground(vararg voids: Void): Void? {
            noteDao.insert(Note(1,"Title 1", "Description 1",1))
            noteDao.insert(Note(2,"Title 2", "Description 2" ,1))
            noteDao.insert(Note(3,"Title 3", "Description 3",1))
            return null
        }
    }
}

