package nseki.com.app.samplecomparerxcoroutinesroom

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

class BooksViewModel @ViewModelInject constructor(
    private val booksDao: BooksDao
) : ViewModel() {

    companion object {
        private const val bookId = "12345"
    }

    private val foundId = MutableLiveData<String>()

    val bookFlowable: LiveData<String> = foundId.switchMap { id ->
        booksDao.findByIdFlowable(id).map { it.title }.toLiveData()
    }

    val bookFlow: LiveData<String> = foundId.switchMap { id ->
        booksDao.findByIdFlow(id)
            .map { it.title }
            .catch { emit("error: ${it.message}") }
            .asLiveData()
    }

    init {
        foundId.value = bookId
    }

    fun insertBook() = viewModelScope.launch(Dispatchers.IO) {
        val book = BookEntity(bookId, "Room is wonderful", 1024.0)
        booksDao.insert(book)
    }
}
