package moura.renan.architectureexample.domain.model

data class NoteDomain(
    val id: Int,

    val title: String,

    val description: String,

    val priority: Int
)