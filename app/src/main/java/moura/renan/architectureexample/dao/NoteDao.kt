package moura.renan.architectureexample.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import moura.renan.architectureexample.model.Note

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
    fun getAllNotes() : LiveData<List<String>>

}