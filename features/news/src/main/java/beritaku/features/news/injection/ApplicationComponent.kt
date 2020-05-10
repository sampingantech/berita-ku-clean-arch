package beritaku.features.news.injection

import beritaku.features.news.detail.DetailActivity
import beritaku.features.news.home.HomeActivity
import beritaku.features.news.injection.module.*
import dagger.Component

@Component(modules = [
    LocalModule::class,
    DataModule::class,
    DomainModule::class,
    PresentationModule::class,
    RemoteModule::class,
    AppModule::class
])
interface ApplicationComponent {
    fun inject(activity: HomeActivity)
    fun inject(activity: DetailActivity)
}