package com.example.moodapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.moodapp.databinding.FragmentLaunchBinding;

public class FragmentLaunch extends Fragment {

    private FragmentLaunchBinding binding;
    private FragmentLaunch.onSwitchFragment onSwitchFragment;
    public FragmentLaunch(){};

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLaunchBinding.inflate(inflater);
        initData();
        return binding.getRoot();
    }

    private void initData(){
        binding.launchStart.setOnClickListener(v->{
            if (onSwitchFragment != null){
                onSwitchFragment.switchFragment();
            }
        });
    }

    public interface onSwitchFragment{
        void switchFragment();
    }

    public void switchFragment(FragmentLaunch.onSwitchFragment onSwitchFragment){
        this.onSwitchFragment = onSwitchFragment;
    }
}

