package com.example.moodapp.collection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.moodapp.R;
import com.example.moodapp.utility.ConfirmDialog;
import com.example.moodapp.databinding.FragmentCollectionBinding;
import com.example.moodapp.database.QuoteCollection;
import com.example.moodapp.viewmodel.QuoteCollectionViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import com.example.moodapp.collection.adapter.CollectionAdapter;



public class FragmentCollection extends Fragment {

    private FragmentCollectionBinding binding;

    private CollectionAdapter collectionAdapter;
    private ConfirmDialog confirmDialog;

    private QuoteCollectionViewModel quoteCollectionViewModel;

    private List<QuoteCollection> quoteCollections = new ArrayList<>();

    private int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCollectionBinding.inflate(inflater);
        initData();
        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initData() {
        quoteCollectionViewModel = new ViewModelProvider(requireActivity()).get(QuoteCollectionViewModel.class);
        confirmDialog = new ConfirmDialog(requireContext());

        collectionAdapter = new CollectionAdapter(requireContext(), new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(collectionAdapter);

        quoteCollectionViewModel.getAllQuotes().observe(getViewLifecycleOwner(), quoteCollections -> {
            if (quoteCollections.size() == 0) {
                Toast.makeText(requireContext(), "No records.", Toast.LENGTH_SHORT).show();
            }
            this.quoteCollections.clear();
            this.quoteCollections.addAll(quoteCollections);
            collectionAdapter.setData(quoteCollections);
        });

        collectionAdapter.setOnItemClickListener((view, position) -> {
            this.position = position;
            if (view.getId() == R.id.delete_btn) {
                confirmDialog.show();
            }
        });

        confirmDialog.setOnListener(()->{
//            quoteCollections.remove(position);
            quoteCollectionViewModel.deleteQuoteCollection(quoteCollections.get(position));
            Toast.makeText(requireContext(), "Quote Deleted.", Toast.LENGTH_SHORT).show();
//            collectionAdapter.setData(quoteCollections);
            if (quoteCollections.size() == 0){
                Toast.makeText(requireContext(), "No records.", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
