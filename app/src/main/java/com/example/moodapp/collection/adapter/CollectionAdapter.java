package com.example.moodapp.collection.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moodapp.R;
import com.example.moodapp.database.QuoteCollection;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {
    private List<QuoteCollection> mData;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public CollectionAdapter(Context context, List<QuoteCollection> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_cardview, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuoteCollection quoteCollection = mData.get(position);
        holder.textView.setText(quoteCollection.quotation);

        holder.deleteView.setOnClickListener(v->{
            onItemClickListener.onItemClick(v,position);
        });

        if ((position + 1) % 2 == 0) {
            holder.cardView.setBackgroundResource(R.drawable.shape_r8_e7dfd780);
        } else {
            holder.cardView.setBackgroundResource(R.drawable.shape_r8_e7dfd730);
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<QuoteCollection> newData) {
        mData.clear();
        mData.addAll(newData);
        notifyDataSetChanged();
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<QuoteCollection> getData() {
        return mData;
    }

    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder {
        View cardView;
        TextView textView;

        AppCompatImageView deleteView;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            textView = itemView.findViewById(R.id.text_view);
            deleteView = itemView.findViewById(R.id.delete_btn);
        }
    }
}
