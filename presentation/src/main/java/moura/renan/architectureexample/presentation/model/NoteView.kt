package moura.renan.architectureexample.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteView( val id: Int = 0,

                     val title: String,

                     val description: String,

                     val priority: Float = 0f) : Parcelable {




    fun isNew() : Boolean {
        return id == 0
    }
}