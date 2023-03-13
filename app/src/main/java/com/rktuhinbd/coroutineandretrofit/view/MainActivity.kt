package com.rktuhinbd.coroutineandretrofit.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rktuhinbd.coroutineandretrofit.databinding.ActivityMainBinding
import com.rktuhinbd.coroutineandretrofit.network.Api
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val BASE_URL = "https://jsonplaceholder.typicode.com/"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = retrofit.getUsers()
            if (response.isSuccessful) {
                launch(Dispatchers.Main) {
                    if (!response.body().isNullOrEmpty()) {
                        val recyclerAdapter = response.body()?.let { RecyclerAdapter(it) }
                        binding.recyclerView.adapter = recyclerAdapter
                    }
                }
            }
        }
    }
}