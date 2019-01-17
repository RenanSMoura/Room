package moura.renan.architectureexample.presentation.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.item_note_view.view.*
import moura.renan.architectureexample.R
import moura.renan.architectureexample.presentation.extensions.launchNoteDetailsActivity
import moura.renan.architectureexample.presentation.model.NoteView

class ItemNoteView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_note_view,this)
    }

    fun setNote(note : NoteView) {
        textTitle.text = note.title
        textContent.text = note.description
        textPriority.text = note.priority.toString()

        setOnClickListener {
            context.launchNoteDetailsActivity(note)
        }
    }

}