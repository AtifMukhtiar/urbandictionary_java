package com.jadgroup.urbandictionary.dagger.networks;


import com.jadgroup.urbandictionary.ui.MainActivity;
import com.jadgroup.urbandictionary.viewmodels.ViewModelMainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworksModule.class})
public interface NetoworksComponent {
    public void inject(MainActivity mainActivity);
}


