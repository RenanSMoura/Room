package moura.renan.architectureexample.data.dao

import android.arch.persistence.room.*
import io.reactivex.Flowable
import moura.renan.architectureexample.data.model.Note

@Dao
interface NoteDao {

    @Insert
    fun insert(note : Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delet(note : Note)

    @Query("DELETE FROM NOTE_TABLE")
    fun deleteAllNotes()


    @Query("SELECT * FROM NOTE_TABLE ORDER BY priority DESC")
    fun getAllNotes() : Flowable<List<Note>>

}