package moura.renan.architectureexample.presentation.mapper

interface EntityMapper<E, D> {

    fun mapFromEntity(entity: E): D
    fun mapToEntity(domain: D): E
}