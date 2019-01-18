package moura.renan.data.dao

import android.arch.persistence.room.*
import io.reactivex.Flowable
import moura.renan.data.model.Note

@Dao
abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(note: Note)

    @Delete
    abstract fun delet(note: Note)

    @Query("DELETE FROM NOTE_TABLE")
    abstract fun deleteAllNotes()


    @Query("SELECT * FROM NOTE_TABLE ORDER BY priority DESC")
    abstract fun getAllNotes() : Flowable<List<Note>>

}