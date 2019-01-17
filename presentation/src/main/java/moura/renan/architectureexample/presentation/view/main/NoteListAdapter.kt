package moura.renan.architectureexample.presentation.view.main


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moura.renan.architectureexample.R
import moura.renan.architectureexample.presentation.custom.ItemNoteView
import moura.renan.architectureexample.presentation.model.NoteView

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.ViewHolder> (){

    private  var notesList: List<NoteView> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        return ViewHolder(view)
    }

    fun updateList(notes : List<NoteView>) {
        notesList = notes
        notifyDataSetChanged()
    }

    override fun getItemCount() = notesList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.noteView.setNote(notesList[position])
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val noteView : ItemNoteView = view.findViewById(R.id.itemNoteView)
    }
}
