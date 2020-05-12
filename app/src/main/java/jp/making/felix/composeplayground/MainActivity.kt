package jp.making.felix.composeplayground

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.Drawable.createFromStream
import android.media.ImageReader
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Observer
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.imageFromResource
import androidx.ui.layout.Column
import androidx.ui.layout.Expanded
import androidx.ui.layout.Spacing
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Card
import androidx.ui.res.imageResource
import coil.Coil
import coil.request.GetRequest
import jp.making.felix.composeplayground.di.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

// 遊ぶためのサンプルなので、FatView orz
class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var viewModel: DataViewModel
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelFactory().create(DataViewModel::class.java)
        viewModel.test()
        val dogObserver = Observer<List<String>> { dog ->
            DogList(dogs = dog, context = baseContext)
        }
        viewModel.dogData.observe(this, dogObserver)
        setContent {
            MaterialTheme {
                DogList(dogs = viewModel.dogData.value ?: throw IllegalAccessError(), context = this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancel()
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun DogList(dogs: List<String>, context: Context) {
    VerticalScroller {
        Column {
            dogs.forEach { dogs ->
                Card(
                    modifier = Spacing(4.dp) wraps Expanded,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    val loarder = Coil.imageLoader(context = context)
                    val request = GetRequest.Builder(context = context)
                        .data(dogs)
                        .build()
                    DrawImage(image = loarder.execute(request).drawable)
                }
            }
        }
    }
}