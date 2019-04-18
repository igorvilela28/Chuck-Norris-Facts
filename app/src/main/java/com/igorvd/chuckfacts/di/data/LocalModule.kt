import com.igorvd.chuckfacts.data.jokes.local.JokeCategoryLocalDataSource
import com.igorvd.chuckfacts.data.jokes.local.JokeCategoryLocalDataSourceImpl
import com.igorvd.chuckfacts.data.jokes.local.JokeLocalDataSource
import com.igorvd.chuckfacts.data.jokes.local.JokeLocalDataSourceImpl
import org.koin.dsl.module

val localDataSourceModule = module {

    factory {
        JokeLocalDataSourceImpl(get()) as JokeLocalDataSource
    }

    factory {
        JokeCategoryLocalDataSourceImpl(get()) as JokeCategoryLocalDataSource
    }
}