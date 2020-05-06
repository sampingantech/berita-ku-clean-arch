package beritaku.features.news

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import beritaku.features.news.detail.DetailViewModel
import beritaku.features.news.home.HomeViewModel
import com.anangkur.beritaku.core.util.Const
import com.anangkur.beritaku.data.Repository
import com.anangkur.beritaku.local.db.AppDatabase

class ViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
        with(modelClass) {
            when {
                isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository)
                isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object{
        @Volatile private var INSTANCE: ViewModelFactory? = null
        private fun getInstance(context: Context) =
            INSTANCE ?: synchronized(
                ViewModelFactory::class.java){
                INSTANCE
                    ?: ViewModelFactory(
                        Injection.provideRepository(
                            context.getSharedPreferences(Const.PREF_NAME, MODE_PRIVATE),
                            AppDatabase.getDatabase(context).getDao()
                        )
                    ).also { INSTANCE = it }
            }

        fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
            ViewModelProviders.of(this, getInstance(application)).get(viewModelClass)

        fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
            ViewModelProviders.of(this, getInstance(this.requireContext())).get(viewModelClass)
    }
}