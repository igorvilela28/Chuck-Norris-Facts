import com.igorvd.chuckfacts.features.jokes.JokesViewModel
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
val jokesViewModelModule = module {
    viewModel { JokesViewModel(get(), get()) }
}