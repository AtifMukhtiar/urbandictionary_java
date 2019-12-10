package com.jadgroup.urbandictionary.interfaces;

import com.jadgroup.urbandictionary.models.AlbumList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface AlbumAPIs {

    @Headers({"x-rapidapi-host:mashape-community-urban-dictionary.p.rapidapi.com", "x-rapidapi-key:PijukZnDjhmsh7JMpD0hdTVZhIvyp1MIlamjsn9Ig8j9tra8kp"})
    @GET("define")//?term=wat
    Call<AlbumList> getAlbumList(@Query("term") String term);

}
