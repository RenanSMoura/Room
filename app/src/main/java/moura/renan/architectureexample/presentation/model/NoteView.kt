package moura.renan.architectureexample.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteView( val id: Int = 0,

                     val title: String,

                     val description: String,

                     val priority: Int = 0) : Parcelable