package com.jadgroup.urbandictionary.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jadgroup.urbandictionary.models.Album;
import com.jadgroup.urbandictionary.models.AlbumList;
import com.jadgroup.urbandictionary.networks.RetroClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelMainActivity extends ViewModel {

    MutableLiveData<List<Album>> albumLiveData = new MutableLiveData<>();

    public void setAlbumLiveData(List<Album> albumLiveData) {
        this.albumLiveData.setValue(albumLiveData);
    }

    public LiveData<List<Album>> getAlbumLiveData() {
        return albumLiveData;
    }


    public void getAlbumList(String term) {
        RetroClient.getClient().getAlbumList(term).enqueue(new Callback<AlbumList>() {
            @Override
            public void onResponse(Call<AlbumList> call, Response<AlbumList> response) {
                if (response.body() != null) {
                    AlbumList albumList = response.body();
                    Log.d("response : onResponse", albumList.getAlbums().size() + "");
                    setAlbumLiveData(albumList.getAlbums());
                }
            }

            @Override
            public void onFailure(Call<AlbumList> call, Throwable t) {
                Log.d("response : onFailure", "");
            }
        });
    }
}
