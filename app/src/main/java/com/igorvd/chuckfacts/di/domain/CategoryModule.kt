import com.igorvd.chuckfacts.data.jokes.repository.JokeCategoryRepositoryImpl
import com.igorvd.chuckfacts.domain.jokes.repository.JokeCategoryRepository
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module


@FlowPreview
val jokeCategoryRepositoryModule = module {
    factory {
        JokeCategoryRepositoryImpl(get(), get()) as JokeCategoryRepository
    }
}