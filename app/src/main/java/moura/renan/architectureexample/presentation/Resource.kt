package moura.renan.architectureexample.presentation

class Resource<out T> constructor(val status: ResourceState, val data: T? = null, val message: String? = null)

enum class ResourceState {
    LOADING, SUCCESS, ERROR
}