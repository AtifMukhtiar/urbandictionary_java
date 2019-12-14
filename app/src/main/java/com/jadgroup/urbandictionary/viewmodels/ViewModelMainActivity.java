package com.jadgroup.urbandictionary.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jadgroup.urbandictionary.models.Album;

import java.util.List;

public class ViewModelMainActivity extends ViewModel {

    MutableLiveData<List<Album>> albumLiveData = new MutableLiveData<>();

    public void setAlbumLiveData(List<Album> albumLiveData) {
        this.albumLiveData.setValue(albumLiveData);
    }

    public LiveData<List<Album>> getAlbumLiveData() {
        return albumLiveData;
    }

}
