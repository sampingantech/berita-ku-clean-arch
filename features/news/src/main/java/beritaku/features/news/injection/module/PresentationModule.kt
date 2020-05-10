package beritaku.features.news.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import beritaku.features.news.injection.ViewModelFactory
import beritaku.features.news.injection.ViewModelKey
import com.anangkur.beritaku.presentation.features.news.DetailViewModel
import com.anangkur.beritaku.presentation.features.news.HomeViewModel
import com.anangkur.beritaku.presentation.mapper.ArticleMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel
}