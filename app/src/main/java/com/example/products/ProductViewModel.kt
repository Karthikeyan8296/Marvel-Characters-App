package com.example.products

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.products.API.NetworkResponse
import com.example.products.API.ProductResponse
import com.example.products.API.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ProductViewModel: ViewModel() {

    private val _productsResult = MutableLiveData<NetworkResponse<List<ProductResponse>>>()
    val productsResult: LiveData<NetworkResponse<List<ProductResponse>>> = _productsResult

    fun getProducts(){
        _productsResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProducts()
                if(response.isSuccessful && response.body() != null){
                    _productsResult.value = NetworkResponse.Success(response.body() ?: emptyList())
                    Log.d("TAG", response.body().toString())
                }else{
                    _productsResult.value = NetworkResponse.Failure("Failed to load product data")
                    Log.d("TAG", response.errorBody().toString())
                }
            }catch (e: IOException) {
                _productsResult.value = NetworkResponse.Failure(message = "No internet connection")
                Log.d(
                    TAG,
                    "you have searched this city: ${NetworkResponse.Failure(message = "No internet connection")}"
                )
            } catch (e: HttpException) {
                _productsResult.value = NetworkResponse.Failure(message = "Something went wrong")
                Log.d(
                    TAG,
                    "${NetworkResponse.Failure(message = "Something went wrong")}"
                )
            }
        }
    }


}