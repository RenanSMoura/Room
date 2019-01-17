package moura.renan.architectureexample.presentation.injection

import android.app.Application
import android.content.Context
import moura.renan.architectureexample.data.mapper.NoteDataMapper
import moura.renan.architectureexample.data.repository.NoteRepositoryImpl
import moura.renan.architectureexample.domain.repository.NotesRepository
import moura.renan.architectureexample.domain.usecase.features.notes.*
import moura.renan.architectureexample.presentation.mapper.NoteViewMapper
import moura.renan.architectureexample.presentation.view.details.NoteDetailViewModel
import moura.renan.architectureexample.presentation.view.main.NoteViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModules = module {

    single { Application() as Context }
    factory { NoteRepositoryImpl(get(),get()) as NotesRepository }

    factory { NoteDataMapper() }
    factory { NoteViewMapper() }


    factory { GetNotesUseCase(get()) }
    factory { SaveNote(get()) }
    factory { UpdateNote(get()) }
    factory { DeleteNote(get()) }
    factory { DeleteAllNotes(get()) }

    viewModel{ NoteViewModel(get(),get(),get()) }
    viewModel { NoteDetailViewModel(get(),get(),get(),get()) }
}