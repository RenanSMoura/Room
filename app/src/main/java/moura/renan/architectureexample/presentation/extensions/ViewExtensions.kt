package moura.renan.architectureexample.presentation.extensions

import android.widget.EditText

fun EditText.getString () : String {
    return this.text.toString()
}