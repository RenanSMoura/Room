package moura.renan.architectureexample.presentation.view.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import moura.renan.architectureexample.R
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val noteAdapter = NoteListAdapter()
    private val noteViewModel: NoteViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteViewModel.fetchNotes()
        setUpRecyclerView()
        noteViewModel.getNotes().observe(this, Observer { list ->
            list?.let {
                it.data?.let { listView ->
                    noteAdapter.updateList(listView)

                }
            }
        })


    }

    private fun setUpRecyclerView() {
        recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        recyclerViewNotes.adapter = noteAdapter
    }
}
