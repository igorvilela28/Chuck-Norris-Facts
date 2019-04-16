import com.igorvd.chuckfacts.features.search.SearchJokeViewModel
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
val searchJokeViewModelModule = module {
    viewModel { SearchJokeViewModel(get(), get(), get()) }
}