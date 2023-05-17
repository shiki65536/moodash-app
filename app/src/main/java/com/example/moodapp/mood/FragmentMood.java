package com.example.moodapp.mood;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.moodapp.R;
import com.example.moodapp.databinding.FragmentMoodBinding;

public class FragmentMood extends Fragment {
    FragmentMoodBinding binding;
    private final FragmentMoodChecker fragmentMoodChecker = new FragmentMoodChecker();
    private final FragmentMoodFeedback fragmentMoodFeedback = new FragmentMoodFeedback();
    public FragmentMood(){}
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMoodBinding binding = FragmentMoodBinding.inflate(inflater);
        replaceFragment(fragmentMoodChecker);
        initData();
        return binding.getRoot();
    }

    private void initData(){

        fragmentMoodChecker.steOnSwitchFragment(()->{
            replaceFragment(fragmentMoodFeedback);
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mood_content,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; }
}

