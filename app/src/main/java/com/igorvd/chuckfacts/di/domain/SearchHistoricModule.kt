import com.igorvd.chuckfacts.data.jokes.repository.SearchHistoricRepositoryImpl
import com.igorvd.chuckfacts.domain.jokes.repository.SearchHistoricRepository
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
val searchHistoricRepositoryModule = module {
    factory {
        SearchHistoricRepositoryImpl(get()) as SearchHistoricRepository
    }
}