package com.example.kotlincorotinus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlincorotinus.databinding.ActivityMainBinding
import com.example.kotlincorotinus.models.Users
import com.example.kotlincorotinus.retrofit.ApiClient
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    lateinit var binding: ActivityMainBinding
    lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job = Job()

        launch {
            val users = getMyUser(1)
            withContext(Dispatchers.Main) {
                binding.tv1.text = users.name
            }
        }
    }

    suspend fun getMyUser(id: Int): Users {
        return async(Dispatchers.IO) {
            ApiClient.apiService.getUserById(id)
        }.await()
    }
//    bu ilovadan chiqanda sorvni tohtatib beruvchi
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main+job

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}