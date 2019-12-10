package com.jadgroup.urbandictionary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jadgroup.urbandictionary.R;
import com.jadgroup.urbandictionary.models.Album;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Album> albumLsit = new ArrayList<>();

    private boolean isThumbsUp = true;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dictionary_adapter_item, parent, false);
        return new DictionaryViewModel(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DictionaryViewModel viewModel = (DictionaryViewModel) holder;
        Album album = albumLsit.get(position);
        viewModel.txtViewWord.setText(album.getWord());
        viewModel.txtViewAuthor.setText(album.getAuthor());
        viewModel.txtViewDefination.setText(album.getDefinition());
        viewModel.txtViewThumbsUp.setText(String.valueOf(album.getThumbsUp()));
        viewModel.txtViewThumbsDown.setText(String.valueOf(album.getThumbsDown()));
    }

    @Override
    public int getItemCount() {
        return albumLsit.size();
    }

    public void updateData(List<Album> albumLsit) {
        this.albumLsit.clear();
        this.albumLsit.addAll(albumLsit);
        notifyDataSetChanged();
    }

    public void sortList(boolean isThumbsUp) {
        this.isThumbsUp = isThumbsUp;
        Collections.sort(albumLsit, albumComparator);
        notifyDataSetChanged();
    }

    class DictionaryViewModel extends RecyclerView.ViewHolder {
        TextView txtViewWord;
        TextView txtViewAuthor;
        TextView txtViewDefination;
        TextView txtViewThumbsUp;
        TextView txtViewThumbsDown;

        public DictionaryViewModel(@NonNull View rootView) {
            super(rootView);
            txtViewWord = rootView.findViewById(R.id.txtViewWord);
            txtViewAuthor = rootView.findViewById(R.id.txtViewAuthor);
            txtViewDefination = rootView.findViewById(R.id.txtViewDefination);
            txtViewThumbsUp = rootView.findViewById(R.id.txtViewThumbsUp);
            txtViewThumbsDown = rootView.findViewById(R.id.txtViewThumbsDown);
        }
    }

    public Comparator<Album> albumComparator = new Comparator<Album>() {

        public int compare(Album album1, Album album2) {

            if (isThumbsUp) {
                return album2.getThumbsUp() - album1.getThumbsUp();
            }
            return album2.getThumbsDown() - album1.getThumbsDown();
        }

    };
}
