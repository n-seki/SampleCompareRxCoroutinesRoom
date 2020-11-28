package nseki.com.app.samplecomparerxcoroutinesroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import nseki.com.app.samplecomparerxcoroutinesroom.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<BooksViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.insertButton.setOnClickListener {
            viewModel.insertBook()
        }

        viewModel.bookFlowable.observe(this, Observer { Log.d("book-flowable", it.toString()) })
        viewModel.bookFlow.observe(this, Observer { Log.d("book-flow", it.toString()) })
    }
}
