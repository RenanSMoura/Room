package moura.renan.architectureexample

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import moura.renan.architectureexample.presentation.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.setApplication(application)
        noteViewModel.fetchNotes()

    }
}
