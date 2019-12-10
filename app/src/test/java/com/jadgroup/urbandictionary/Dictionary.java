package com.jadgroup.urbandictionary;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.jadgroup.urbandictionary.models.Album;
import com.jadgroup.urbandictionary.models.AlbumList;
import com.jadgroup.urbandictionary.networks.RetroClient;
import com.jadgroup.urbandictionary.viewmodels.ViewModelMainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Dictionary {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void testApiResponseOK() {

        RetroClient.getClient().getAlbumList("").enqueue(new Callback<AlbumList>() {
            @Override
            public void onResponse(Call<AlbumList> call, Response<AlbumList> response) {
                assertEquals(response.code(), 200);
                System.out.println("passs");
            }

            @Override
            public void onFailure(Call<AlbumList> call, Throwable t) {
                fail();
                System.out.println("fail");
            }
        });

        System.out.println("outside");
    }

}
