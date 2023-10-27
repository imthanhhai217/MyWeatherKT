package com.example.myweatherkt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myweatherkt.api.response.BaseResponse
import com.example.myweatherkt.models.CurrentWeatherResponse
import com.example.myweatherkt.repositories.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(val weatherRepository: WeatherRepository, application: Application) :
    AndroidViewModel(application) {

    val currentWeatherResult: MutableLiveData<BaseResponse<CurrentWeatherResponse>> =
        MutableLiveData()

    var currentWeatherResponse: CurrentWeatherResponse? = null

    fun getCurrentWeatherByCityName(cityName: String) {
        viewModelScope.launch {
            currentWeatherResult.postValue(BaseResponse.Loading())
            val response = weatherRepository.getCurrentWeatherByCityName(cityName);
            currentWeatherResult.postValue(handleCurrentWeatherResponse(response))
        }
    }

    private fun handleCurrentWeatherResponse(response: Response<CurrentWeatherResponse>): BaseResponse<CurrentWeatherResponse> {
        if (response.isSuccessful && response.code() == 200) {
            response.body()?.let { it ->
                currentWeatherResponse = it
                return BaseResponse.Success(it)
            }
        }
        return BaseResponse.Error(null, response.message())
    }

}