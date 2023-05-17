package com.example.moodapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.moodapp.databinding.FragmentHomeBinding;
import com.example.moodapp.utility.LoadingDialog;
import com.example.moodapp.utility.GetLocation;
import com.example.moodapp.viewmodel.WeatherViewModel;
import java.util.Objects;



public class FragmentHome extends Fragment implements GetLocation.OnLocationUpdateListener {
    private FragmentHomeBinding homeBinding;

    private WeatherViewModel model;
    public FragmentHome(){}
    private GetLocation getLocation;
    private LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        homeBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment

        initData();
        return homeBinding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    private void initData(){
        //Initialize viewModel
        model = new ViewModelProvider(requireActivity())
                .get(WeatherViewModel.class);

        //Listening to weather data
        model.weatherResponse.observe(getViewLifecycleOwner(), weatherResponse -> {
            loadingDialog.dismiss();
            if (weatherResponse != null){
                homeBinding.homeCity.setText(weatherResponse.getName()); // city name
                String description = weatherResponse.getWeather().get(0).getDescription();
                String capitalizedDescription = description.substring(0, 1).toUpperCase() + description.substring(1);
                homeBinding.homeDescription.setText(capitalizedDescription);
                homeBinding.homeTemp.setText((int) (weatherResponse.getMain().getTemp()-273.15) + "\u2103");
                homeBinding.homeFeelsLike.setText("Feels like: "+(int) (weatherResponse.getMain().getFeels_like()-273.15) +"\u2103" );
                homeBinding.homePressure.setText("Pressure: "+weatherResponse.getMain().getPressure() +"hPa");
                homeBinding.homeHumidity.setText("Humidity: "+weatherResponse.getMain().getHumidity()+"%");
                String iconId = weatherResponse.getWeather().get(0).getIcon(); // icon id
                // display weather icon
                Glide.with(this)
                        .load("https://openweathermap.org/img/wn/"+iconId+"@2x.png")
                        .disallowHardwareConfig()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(homeBinding.homeIcon);

            }
        });

        // instantiated the popup message
        loadingDialog = new LoadingDialog(requireContext());
        loadingDialog.show();

        getLocation = new GetLocation(requireContext(),this);
        // Get permission to start getting local location
        launcher.launch(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION});
    }

    private void switchFragment(View view,int id){
        NavController navController = Navigation.findNavController(view);
        navController.navigate(id);
    }

    // Get location permission
    ActivityResultLauncher<String[]> launcher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
            result -> {
                if (result.get(Manifest.permission.ACCESS_FINE_LOCATION) != null
                        && result.get(Manifest.permission.ACCESS_COARSE_LOCATION) != null) {
                    if (Objects.requireNonNull(result.get(Manifest.permission.ACCESS_COARSE_LOCATION)).equals(true)
                            && Objects.requireNonNull(result.get(Manifest.permission.ACCESS_FINE_LOCATION)).equals(true)) {
                        loadingDialog.show();
                        getLocation.startLocationUpdates();
                    }
                }
            });

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homeBinding = null;
    }

    @Override
    public void onLocationUpdate(double latitude, double longitude) {
        // Initiate a network request to obtain weather information
        model.getWeather(latitude,longitude);
    }
}



