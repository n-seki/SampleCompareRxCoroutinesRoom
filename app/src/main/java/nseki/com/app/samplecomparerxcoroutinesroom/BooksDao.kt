package nseki.com.app.samplecomparerxcoroutinesroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    @Query("SELECT * FROM Books WHERE id = :id")
    fun findByIdFlowable(id: String): Flowable<BookEntity>

    @Query("SELECT * FROM Books WHERE id = :id")
    fun findByIdFlow(id: String): Flow<BookEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: BookEntity)
}
