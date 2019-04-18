import com.igorvd.chuckfacts.data.jokes.repository.JokeRepositoryImpl
import com.igorvd.chuckfacts.domain.jokes.repository.JokeRepository
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
val jokeRepositoryModule = module {

    factory {
        JokeRepositoryImpl(get(), get()) as JokeRepository
    }
}