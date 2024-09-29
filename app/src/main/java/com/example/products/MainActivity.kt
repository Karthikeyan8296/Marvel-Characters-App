package com.example.products

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.products.API.NetworkResponse
import com.example.products.API.RetrofitInstance
import com.example.products.ui.theme.ProductsTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : ComponentActivity() {

    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ProductsScreen(viewModel = productViewModel)
                }
            }
        }
        productViewModel.getProducts()
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProductsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ProductsScreen(ProductViewModel())
        }
    }
}