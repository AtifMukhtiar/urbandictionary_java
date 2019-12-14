package com.jadgroup.urbandictionary.dagger.networks;

import com.jadgroup.urbandictionary.adapters.DictionaryAdapter;
import com.jadgroup.urbandictionary.keys.KeysString;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworksModule {
    String baseUrl;

    public NetworksModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Singleton
    @Provides
    public Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(KeysString.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public DictionaryAdapter getDictionaryAdapter(){
        return new DictionaryAdapter();
    }
}
