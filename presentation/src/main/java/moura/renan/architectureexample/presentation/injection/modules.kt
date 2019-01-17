package moura.renan.architectureexample.presentation.injection

import android.app.Application
import android.content.Context
import moura.renan.data.mapper.NoteDataMapper
import moura.renan.data.repository.NoteRepositoryImpl
import moura.renan.domain.repository.NotesRepository
import moura.renan.domain.usecase.features.notes.*
import moura.renan.architectureexample.presentation.mapper.NoteViewMapper
import moura.renan.architectureexample.presentation.thread.UiThread
import moura.renan.architectureexample.presentation.view.details.NoteDetailViewModel
import moura.renan.architectureexample.presentation.view.main.NoteViewModel
import moura.renan.domain.executor.PostExecutionThread
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModules = module {

    single { Application() as Context }
    factory { NoteRepositoryImpl(get(),get()) as NotesRepository }

    factory { NoteDataMapper() }
    factory { NoteViewMapper() }

    single { UiThread() as PostExecutionThread }

    factory { GetNotesUseCase(get(),get()) }
    factory { SaveNote(get(),get()) }
    factory { UpdateNote(get(),get()) }
    factory { DeleteNote(get(),get()) }
    factory { DeleteAllNotes(get(),get()) }

    viewModel{ NoteViewModel(get(),get(),get()) }
    viewModel { NoteDetailViewModel(get(),get(),get(),get()) }
}