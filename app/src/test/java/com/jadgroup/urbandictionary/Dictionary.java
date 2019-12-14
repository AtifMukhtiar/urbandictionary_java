package com.jadgroup.urbandictionary;

import com.jadgroup.urbandictionary.models.Album;
import com.jadgroup.urbandictionary.models.AlbumList;
import com.jadgroup.urbandictionary.networks.RetroClient;
import com.jadgroup.urbandictionary.viewmodels.ViewModelMainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doAnswer;

@RunWith(MockitoJUnitRunner.class)
public class Dictionary {

    @Mock
    ViewModelMainActivity viewModelMainActivity;

    Album requestAlbum = new Album();
    Album responseAlbum = new Album();

    @Test
    public void testMutableData() {


        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();

                ArrayList<Album> albumArrayList = (ArrayList<Album>) arguments[0];
                responseAlbum = albumArrayList.get(0);
                return null;
            }
        }).when(viewModelMainActivity).setAlbumLiveData(ArgumentMatchers.<Album>anyList());

        List<Album> albumsList = new ArrayList<>();
        requestAlbum.setAuthor("Atif");
        albumsList.add(requestAlbum);
        viewModelMainActivity.setAlbumLiveData(albumsList);


        System.out.println("Request : " + requestAlbum.getAuthor());
        System.out.println("Response : " + responseAlbum.getAuthor());
        assertEquals(requestAlbum.getAuthor(), responseAlbum.getAuthor());
    }


}
