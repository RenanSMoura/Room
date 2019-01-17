package moura.renan.data.dao

import android.arch.persistence.room.*
import io.reactivex.Flowable
import moura.renan.data.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note : Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: Note)

    @Delete
    fun delet(note : Note)

    @Query("DELETE FROM NOTE_TABLE")
    fun deleteAllNotes()


    @Query("SELECT * FROM NOTE_TABLE ORDER BY priority DESC")
    fun getAllNotes() : Flowable<List<Note>>

}