package com.jadgroup.urbandictionary.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jadgroup.urbandictionary.R;
import com.jadgroup.urbandictionary.adapters.DictionaryAdapter;
import com.jadgroup.urbandictionary.models.Album;
import com.jadgroup.urbandictionary.viewmodels.ViewModelMainActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context context;
    ViewModelMainActivity viewModelMainActivity;

    ProgressBar progress_circular;
    RecyclerView rv_dictionary;
    DictionaryAdapter dictionaryAdapter;

    EditText editTextSearch;
    Spinner spinnerSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initViewModel();
        initViews();
        initRecyclerView();
    }

    private void initViews() {
        initSpinner();
        progress_circular = findViewById(R.id.progress_circular);
        editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModelMainActivity.getAlbumList(charSequence.toString());
                showProgress();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initSpinner() {
        spinnerSort = findViewById(R.id.spinnerSort);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.sort_arrays,
                        R.layout.spinner_text);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(staticAdapter);
        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        if (dictionaryAdapter != null) {
                            dictionaryAdapter.sortList(true);
                        }
                        break;
                    case 2:
                        if (dictionaryAdapter != null) {
                            dictionaryAdapter.sortList(false);
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initViewModel() {
        viewModelMainActivity = ViewModelProviders.of((MainActivity) context)
                .get(ViewModelMainActivity.class);
        viewModelMainActivity.getAlbumLiveData().observe((LifecycleOwner) context, albumListObsever);
        viewModelMainActivity.getAlbumList("");
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
        dictionaryAdapter = new DictionaryAdapter();
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
}
