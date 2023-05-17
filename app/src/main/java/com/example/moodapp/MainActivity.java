package com.example.moodapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.moodapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final FragmentLaunch fragmentLaunch = new FragmentLaunch();
    private final FragmentLogin fragmentLogin = new FragmentLogin();
    private final FragmentSignup fragmentSignup = new FragmentSignup();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        replaceFragment(fragmentLaunch);

        fragmentLogin.switchFragment(() -> {
            replaceFragment(fragmentSignup);
        });

        fragmentLaunch.switchFragment(()->{
            replaceFragment(fragmentLogin);
        });

        fragmentSignup.switchFragment(()->{
            replaceFragment(fragmentLogin);
        });

    }
    public void replaceFragment(Fragment nextFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view, nextFragment);
        fragmentTransaction.commit();
    }
}
