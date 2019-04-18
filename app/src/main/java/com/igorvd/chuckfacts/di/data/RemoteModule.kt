import com.igorvd.chuckfacts.data.jokes.remote.JokeCategoryRemoteDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeCategoryRemoteDataSourceImpl
import com.igorvd.chuckfacts.data.jokes.remote.JokeRemoteDataSource
import com.igorvd.chuckfacts.data.jokes.remote.JokeRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {
    factory {
        JokeCategoryRemoteDataSourceImpl(get(), get()) as JokeCategoryRemoteDataSource
    }

    factory {
        JokeRemoteDataSourceImpl(get(), get(), get()) as JokeRemoteDataSource
    }
}