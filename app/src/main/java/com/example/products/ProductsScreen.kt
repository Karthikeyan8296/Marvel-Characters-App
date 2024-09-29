package com.example.products

import android.widget.Scroller
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.products.API.NetworkResponse
import com.example.products.API.ProductResponse
import com.example.products.ui.components.ErrorScreen
import com.example.products.ui.components.LoadingScreen
import kotlin.random.Random

@Composable
fun ProductsScreen(viewModel: ProductViewModel) {

    val productResult = viewModel.productsResult.observeAsState()

    when (val result = productResult.value) {
        is NetworkResponse.Failure -> {
            ErrorScreen()
        }

        NetworkResponse.Loading -> {
            LoadingScreen()
        }

        is NetworkResponse.Success -> {
//            val products = result.data as? List<ProductResponse> ?: emptyList()
//            Column {
//                products.forEach { product ->
//                    AsyncImage(model = product.imageurl, contentDescription = null)
//                    Text(text = product.createdby)
//                }
//            }
            ProductList(products = result.data)

        }

        null -> {
            Text(text = "Something went wrong")
        }
    }
}

@Composable
fun ProductList(products: List<ProductResponse>) {
    LazyColumn{
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "THE MARVEL CHARACTERS",
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                )
            }
        }
        items(products) { product ->
            ProductItem(product) // Create a separate composable for each item
        }
    }
}

@Composable
fun ProductItem(product: ProductResponse) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(model = product.imageurl, contentDescription = null)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(top = 12.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Name: ${product.realname}", modifier = Modifier.weight(1f))
                    Text(
                        text = "Role: ${product.name}",
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Team: ${product.team}", modifier = Modifier.weight(1f))
                    Text(
                        text = "Since: ${product.firstappearance}",
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Text(
                text = if (product.bio.length > 300) product.bio.substring(0, 300)
                        + "...See More" else product.bio, textAlign = TextAlign.Justify
            )
        }
    }
}