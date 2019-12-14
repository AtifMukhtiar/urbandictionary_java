package com.jadgroup.urbandictionary;

import android.app.Application;

import com.jadgroup.urbandictionary.dagger.networks.DaggerNetoworksComponent;
import com.jadgroup.urbandictionary.dagger.networks.NetoworksComponent;
import com.jadgroup.urbandictionary.dagger.networks.NetworksModule;
import com.jadgroup.urbandictionary.keys.KeysString;

public class UrbanApplication extends Application {

    NetoworksComponent netoworksComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        netoworksComponent = DaggerNetoworksComponent.builder()
                .networksModule(new NetworksModule(KeysString.BASE_URL))
                .build();
    }

    public NetoworksComponent getComponent() {
        return netoworksComponent;
    }
}
