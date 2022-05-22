package me.dio.bankline.data

import android.util.Log
import androidx.lifecycle.liveData
import me.dio.bankline.data.remote.BankLineApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

object BankLineRepository {
    private val TAG = javaClass.simpleName

    private val restAPI by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BankLineApi::class.java)
    }
    fun findBankStatement(accountHolderId: Int) = liveData {
        emit(State.Wait)
        try {
            emit(State.Success(data = restAPI.findBankStatement(accountHolderId)))
        }catch (e:Exception){
            Log.e(TAG, e.message, e)
            emit(State.Error(e.message))
        }
    }

}