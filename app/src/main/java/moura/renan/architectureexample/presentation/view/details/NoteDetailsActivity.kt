package moura.renan.architectureexample.presentation.view.details

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_note_details.*
import moura.renan.architectureexample.R
import moura.renan.architectureexample.presentation.extensions.getString
import moura.renan.architectureexample.presentation.extensions.toast
import moura.renan.architectureexample.presentation.model.NoteView
import moura.renan.architectureexample.presentation.state.ResourceState
import org.koin.android.viewmodel.ext.android.viewModel

class NoteDetailsActivity : AppCompatActivity() {

    private val viewModel: NoteDetailViewModel by viewModel()
    private var noteToUpdate: NoteView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)

        noteToUpdate = intent?.extras?.get("NOTE_VIEW_EDIT") as NoteView?

        actionBar?.setDisplayHomeAsUpEnabled(true)

        populateFields(noteToUpdate)

        viewModel.observeLiveData().observe(this, Observer {
            when (it?.status) {
                ResourceState.SUCCESS -> {
                    progressBar.visibility = View.INVISIBLE
                    finish()

                }
                ResourceState.ERROR -> {
                    progressBar.visibility = View.INVISIBLE
                }
                ResourceState.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }

            }
        })

    }

    private fun checkIfIsNewNote( menu : Menu?){
        menu?.findItem(R.id.deleteNote)?.isVisible = noteToUpdate != null && menu != null
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_note_details, menu)
        checkIfIsNewNote(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.saveNote -> {
                updateNote()
                true
            }
            R.id.deleteNote -> {
                deleteNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteNote(){
        viewModel.deleteNote(noteToUpdate)
    }

    private fun populateFields(noteView: NoteView?) {
        noteView?.let {
            etNoteTitle.setText(noteView.title)
            etNoteDescription.setText(noteView.description)
        }
    }

    private fun updateNote() {
        val note = createNoteFromView()
        if(note.isNew()) {
            viewModel.createNote(note)
        } else {
            viewModel.updateNote(note)
        }

    }


    private fun createNoteFromView(): NoteView {
        return NoteView(
            id = noteToUpdate?.id ?: 0,
            title = etNoteTitle.getString(),
            description = etNoteDescription.getString(),
            priority = ratingPriority.rating
        )


    }
}