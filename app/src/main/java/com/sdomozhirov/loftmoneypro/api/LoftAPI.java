package com.sdomozhirov.loftmoneypro.api;

import com.sdomozhirov.loftmoneypro.cells.GetMoneyResponse;
import com.sdomozhirov.loftmoneypro.cells.PutMoneyResponse;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoftAPI {

    @GET("./items")
    Single<GetMoneyResponse> getItems(@Query("type") String type);

    @POST("./items/add")
    @FormUrlEncoded
    Single<PutMoneyResponse> putItems(@Field("price") String price, @Field("name") String name, @Field("type") String type);

}
