package com.jadgroup.urbandictionary;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.jadgroup.urbandictionary.models.AlbumList;
import com.jadgroup.urbandictionary.networks.RetroClient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(MockitoJUnitRunner.class)
public class DictionaryInstrumentedTest {


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.jadgroup.urbandictionary", appContext.getPackageName());
    }


    private final Object lock = new Object();

    @Test
    public void testApiResponseOK() throws InterruptedException {

        RetroClient.getClient().getAlbumList("").enqueue(new Callback<AlbumList>() {
            @Override
            public void onResponse(Call<AlbumList> call, Response<AlbumList> response) {
                assertEquals(200, response.code());
                System.out.println("test pass");
                synchronized (lock) {
                    lock.notify();
                }
            }

            @Override
            public void onFailure(Call<AlbumList> call, Throwable t) {
                fail();
                System.out.println("fail");
                synchronized (lock) {
                    lock.notify();
                }
            }
        });
        synchronized (lock) {
            lock.wait();
        }
    }

}
