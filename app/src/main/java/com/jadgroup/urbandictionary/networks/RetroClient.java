package com.jadgroup.urbandictionary.networks;

import com.jadgroup.urbandictionary.interfaces.AlbumAPIs;
import com.jadgroup.urbandictionary.keys.KeysString;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    static AlbumAPIs albumAPIs = null;

    public static AlbumAPIs getClient() {
        if (albumAPIs == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(KeysString.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            albumAPIs = retrofit.create(AlbumAPIs.class);
        }
        return albumAPIs;
    }
}
