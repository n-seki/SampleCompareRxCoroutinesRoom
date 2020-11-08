package nseki.com.app.samplecomparerxcoroutinesroom

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class BooksViewModel(
    private val booksDao: BooksDao
) : ViewModel() {

    private val foundId = MutableLiveData<String>()

    val bookFlowable: LiveData<BookEntity> = foundId.switchMap { id ->
        booksDao.findByIdFlowable(id).toLiveData()
    }

    val bookFlow: LiveData<BookEntity> = foundId.switchMap { id ->
        booksDao.findByIdFlow(id).catch {
            Log.d("book-flow", it.message.toString())
        }.asLiveData()
    }

    fun find(id: String) {
        foundId.value = id
    }

    class Factory @Inject constructor(
        private val booksDao: BooksDao
    ) {
        fun create(): BooksViewModel {
            return BooksViewModel(booksDao)
        }
    }
}
