package moura.renan.architectureexample.presentation.injection

import android.app.Application
import android.content.Context
import moura.renan.architectureexample.data.mapper.NoteDataMapper
import moura.renan.architectureexample.data.repository.NoteRepositoryImpl
import moura.renan.architectureexample.domain.repository.NotesRepository
import moura.renan.architectureexample.domain.usecase.features.notes.GetNotesUseCase
import moura.renan.architectureexample.domain.usecase.features.notes.UpdateNote
import moura.renan.architectureexample.presentation.mapper.NoteViewMapper
import moura.renan.architectureexample.presentation.view.details.NoteDetailViewModel
import moura.renan.architectureexample.presentation.view.main.NoteViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModules = module {

    single { Application() as Context }
    factory { GetNotesUseCase(get()) }
    factory { UpdateNote(get()) }
    factory { NoteRepositoryImpl(get(),get()) as NotesRepository }
    factory { NoteDataMapper() }
    factory { NoteViewMapper() }


    viewModel{ NoteViewModel(get(), get()) }
    viewModel { NoteDetailViewModel(get(),get()) }
}