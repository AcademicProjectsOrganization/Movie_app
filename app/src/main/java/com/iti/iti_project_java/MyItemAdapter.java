package com.iti.iti_project_java;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iti.iti_project_java.databinding.ItemLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyViewHolder> {

    ArrayList<Movie> items = new ArrayList<>();
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185";
    final private productListOnCLickListener cLickListener;

    public MyItemAdapter(ArrayList<Movie> items, productListOnCLickListener cLickListener) {
        this.items = items;
        this.cLickListener = cLickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ItemLayoutBinding binding = ItemLayoutBinding.bind(view);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.headerText.setText(items.get(position).getTitle());
        holder.binding.descText.setText(items.get(position).getRelease_date());
        Picasso.with(holder.binding.movieImage.getContext()).load(IMAGE_BASE_URL + items.get(position).getPoster_path()).into(holder.binding.movieImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cLickListener.onItemClick(items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items == null?0:items.size();
    }

    public void setData(ArrayList<Movie> data) {
        if (items != null) {
            items.clear();
            items.addAll(data);
            notifyDataSetChanged();
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ItemLayoutBinding binding;
        public MyViewHolder(@NonNull ItemLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    public interface productListOnCLickListener {
        void onItemClick(Movie product);
    }
}
