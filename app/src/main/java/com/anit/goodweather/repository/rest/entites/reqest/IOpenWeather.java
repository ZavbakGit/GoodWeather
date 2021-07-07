package com.anit.goodweather.repository.rest.entites.reqest;


import com.anit.goodweather.repository.rest.entites.WeatherRequestRestModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeather {
    @GET("data/2.5/weather")
    Call<WeatherRequestRestModel> loadWeather(@Query("q") String city,
                                              @Query("appid") String keyApi,
                                              @Query("units") String units);
}
