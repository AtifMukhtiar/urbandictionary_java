package com.jadgroup.urbandictionary.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jadgroup.urbandictionary.R;
import com.jadgroup.urbandictionary.UrbanApplication;
import com.jadgroup.urbandictionary.adapters.DictionaryAdapter;
import com.jadgroup.urbandictionary.interfaces.AlbumAPIs;
import com.jadgroup.urbandictionary.models.Album;
import com.jadgroup.urbandictionary.models.AlbumList;
import com.jadgroup.urbandictionary.viewmodels.ViewModelMainActivity;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    ViewModelMainActivity viewModelMainActivity;

    ProgressBar progress_circular;
    RecyclerView rv_dictionary;

    @Inject
    DictionaryAdapter dictionaryAdapter;

    EditText editTextSearch;
    TextView txtView_thumbsUp;
    TextView txtView_thumbsDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        ((UrbanApplication) getApplication()).getComponent().inject(this);
        initViewModel();
        initViews();
        initRecyclerView();
    }

    private void initViews() {
        initRadio();
        progress_circular = findViewById(R.id.progress_circular);
        editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getAlbumList(charSequence.toString());
                showProgress();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtView_thumbsUp.setSelected(false);
                txtView_thumbsDown.setSelected(false);
            }
        });
    }

    private void initRadio() {
        txtView_thumbsUp = findViewById(R.id.txtView_thumbsUp);
        txtView_thumbsDown = findViewById(R.id.txtView_thumbsDown);
        txtView_thumbsUp.setOnClickListener(this);
        txtView_thumbsDown.setOnClickListener(this);
    }

    private void initViewModel() {
        viewModelMainActivity = ViewModelProviders.of((MainActivity) context)
                .get(ViewModelMainActivity.class);
        viewModelMainActivity.getAlbumLiveData().observe((LifecycleOwner) context, albumListObsever);
        getAlbumList("");
    }

    private final Observer<List<Album>> albumListObsever = new Observer<List<Album>>() {
        @Override
        public void onChanged(List<Album> albums) {
            if (dictionaryAdapter != null) {
                dictionaryAdapter.updateData(albums);
            }
            hideProgress();
        }
    };


    private void initRecyclerView() {
        rv_dictionary = findViewById(R.id.rv_dictionary);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rv_dictionary.setLayoutManager(layoutManager);
        rv_dictionary.setAdapter(dictionaryAdapter);
    }

    public void showProgress() {
        progress_circular.setVisibility(View.VISIBLE);
        rv_dictionary.setVisibility(View.GONE);
    }

    public void hideProgress() {
        progress_circular.setVisibility(View.GONE);
        rv_dictionary.setVisibility(View.VISIBLE);
    }

    @Inject
    Retrofit retroClient;

    public void getAlbumList(String term) {
        retroClient.create(AlbumAPIs.class).getAlbumList(term).enqueue(new Callback<AlbumList>() {
            @Override
            public void onResponse(Call<AlbumList> call, Response<AlbumList> response) {
                AlbumList albumList = response.body();
                Log.d("response : onResponse", albumList.getAlbums().size() + "");
                viewModelMainActivity.setAlbumLiveData(albumList.getAlbums());
            }

            @Override
            public void onFailure(Call<AlbumList> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtView_thumbsUp:
                txtView_thumbsUp.setSelected(true);
                txtView_thumbsDown.setSelected(false);
                dictionaryAdapter.sortList(true);
                break;
            case R.id.txtView_thumbsDown:
                txtView_thumbsUp.setSelected(false);
                txtView_thumbsDown.setSelected(true);
                dictionaryAdapter.sortList(false);

                break;
        }
    }// end of onClick
}
