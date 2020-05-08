package beritaku.features.news.injection

import beritaku.features.news.detail.DetailActivity
import beritaku.features.news.home.HomeActivity
import beritaku.features.news.injection.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    DataModule::class,
    DomainModule::class,
    LocalModule::class,
    PresentationModule::class,
    RemoteModule::class
])
interface ApplicationComponent {
    fun inject(activity: HomeActivity)
    fun inject(activity: DetailActivity)
}