package moura.renan.architectureexample.presentation.extensions

import android.content.Context
import android.content.Intent
import android.widget.Toast
import moura.renan.architectureexample.presentation.model.NoteView
import moura.renan.architectureexample.presentation.view.details.NoteDetailsActivity


fun Context.launchNoteDetailsActivity(noteView : NoteView){
    startActivity(Intent(this,NoteDetailsActivity::class.java).apply {
        putExtra("NOTE_VIEW_EDIT",noteView)
    })
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()