package nseki.com.app.samplecomparerxcoroutinesroom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: String,
    val title: String,
    val amount: Double
)
