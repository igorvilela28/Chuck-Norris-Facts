import com.igorvd.chuckfacts.di.data.databaseModule
import com.igorvd.chuckfacts.di.interactor.interactorModule
import com.igorvd.chuckfacts.di.network.networkModule
import kotlinx.coroutines.FlowPreview

@FlowPreview
val allModules = listOf(
        networkModule,
        remoteDataSourceModule,
        localDataSourceModule,
        jokeCategoryRepositoryModule,
        jokeRepositoryModule,
        searchHistoricRepositoryModule,
        databaseModule,
        interactorModule,
        jokesViewModelModule,
        searchJokeViewModelModule
)