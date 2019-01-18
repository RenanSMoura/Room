package moura.renan.data.mapper

import moura.renan.data.factory.NoteDataFactory
import moura.renan.data.model.Note
import moura.renan.domain.model.NoteDomain
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NoteDataMapperTest {

    private val mapper = NoteDataMapper()

    @Test
    fun mapFromEntity() {
        val entity = NoteDataFactory.createEntityNote()
        val model = mapper.mapFromEntity(entity)
        assertEqualData(model, entity)
    }

    @Test
    fun mapToEntity() {
        val model = NoteDataFactory.createNote()
        val entity = mapper.mapToEntity(model)

        assertEqualData(model, entity)
    }

    private fun assertEqualData(model: NoteDomain, entity: Note) {
        assertEquals(model.id, entity.id)
        assertEquals(model.title, entity.title)
        assertEquals(model.description, entity.description)
        assertEquals(model.priority, entity.priority)
    }

}