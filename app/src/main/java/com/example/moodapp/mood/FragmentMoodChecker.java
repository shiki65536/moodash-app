package com.example.moodapp.mood;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.aigestudio.wheelpicker.WheelPicker;
import com.example.moodapp.viewmodel.ViewModelController;
import com.example.moodapp.databinding.FragmentMoodCheckerBinding;
import com.example.moodapp.mood.adapter.MoodAdapter;
import com.example.moodapp.utility.LoadingDialog;
import com.example.moodapp.database.MoodRecord;
import com.example.moodapp.utility.ModifySavedData;
import com.example.moodapp.utility.GetLocation;
import com.example.moodapp.viewmodel.MoodRecordViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class FragmentMoodChecker extends Fragment implements GetLocation.OnLocationUpdateListener {
    private FragmentMoodCheckerBinding binding;

    public FragmentMoodChecker() {
    }

    ;
    private final List<MoodRecord> moodBeanList = new ArrayList<>();//心情数据链表
    private String date;//date
    private MoodRecord moodBean;//mood chosen
    private OnSwitchFragment onSwitchFragment;//Switching page interfaces

    private LoadingDialog loadingDialog;//loading window

    private MoodAdapter moodAdapter;//Mood adapter

    private GetLocation getLocation;//Getting the location class

    private MoodRecordViewModel viewModel = ViewModelController.moodRecordViewModel;//viewModel for mood recording

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMoodCheckerBinding.inflate(inflater);
        initDate();
        initData();
        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initData() {

        //Getting location information
        getLocation = new GetLocation(requireContext(), this);
        launcher.launch(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION});
        loadingDialog = new LoadingDialog(requireContext());//Initialize the loading popup window
        loadingDialog.show();//Display the loading window


        /*
         * This is the test data, and if you can't get the location, you just use this string of data
         */
//        moodBeanList.add(new MoodRecord(date, "Happy", "", "", ""));
//        moodBeanList.add(new MoodRecord(date, "Excited", "", "", ""));
//        moodBeanList.add(new MoodRecord(date, "Relax", "", "", ""));
//        moodBeanList.add(new MoodRecord(date, "Celebrate", "", "", ""));
//        moodBeanList.add(new MoodRecord(date, "Sad", "", "", ""));
//        moodBeanList.add(new MoodRecord(date, "Angry", "", "", ""));
//        moodBeanList.add(new MoodRecord(date, "Bored", "", "", ""));
//        moodBeanList.add(new MoodRecord(date, "Hurt", "", "", ""));

        moodAdapter = new MoodAdapter(moodBeanList);//Initializing the adapter
        binding.recycleMood.setLayoutManager(new GridLayoutManager(requireContext(), 4));//arrangement
        binding.recycleMood.setAdapter(moodAdapter);

        binding.moodRecord.setOnClickListener(v -> {
            if (moodBean != null) {
                moodBean.date = binding.checkYear.getCurrentYear() + "-" + binding.checkMonth.getCurrentMonth() + "-" + binding.checkDay.getCurrentDay();//时间参数
                moodBean.memo = binding.moodContent.getText().toString();//The user's memo
                viewModel.insertMoodRecord(moodBean);//Saving data
                /*
                use viewmodel to extract all the expression data and get the mood id just saved,
                which is easy to use in the next place
                 */
                viewModel.getAllMoodRecords().observe(getViewLifecycleOwner(), moodRecords -> {
                    if (moodRecords.size() > 0) {
                        ModifySavedData.getInstance().setId(moodRecords.size());//Store id
                        onSwitchFragment.onSwitchFragment();//Switch to the next page
                    }
                });
            } else {
                /*
                Unselected mood will not save any data
                 */
                Toast.makeText(getContext(), "Please select today's mood", Toast.LENGTH_SHORT).show();
            }
        });


        moodAdapter.setOnItemClickListener((adapter, view, position) -> {
            moodBean = (MoodRecord) adapter.getData().get(position);//Get the selected mood data
            moodAdapter.setPosition(position);//Changing the background
            moodAdapter.notifyDataSetChanged();//Refreshing the adapter
        });

        //The calendar can be controlled to appear or hide
        binding.checkCalendar.setOnClickListener(v -> {
        if (binding.checkDateView.getVisibility() == View.GONE) {
            binding.checkDateView.setVisibility(View.VISIBLE);
        } else {
            binding.checkDateView.setVisibility(View.GONE);
        }
        });

        //Delete the contents of a record
        binding.checkDelete.setOnClickListener(v -> {
            binding.moodContent.setText("");//Assign a null value to the EditTextView
            Toast.makeText(getContext(), "Cleaned your message", Toast.LENGTH_SHORT).show();
        });


        //Because using a disjoint Picker,
        // need to listen for the year and month chosen so that day is correct; otherwise, February might have 30 days
        binding.checkYear.setOnWheelChangeListener(new WheelPicker.OnWheelChangeListener() {
            @Override
            public void onWheelScrolled(int offset) {

            }

            @Override
            public void onWheelSelected(int position) {
                binding.checkDay.setYearAndMonth(binding.checkYear.getSelectedYear(), binding.checkMonth.getSelectedMonth());//Monitor year and month
            }

            @Override
            public void onWheelScrollStateChanged(int state) {

            }
        });

        binding.checkMonth.setOnWheelChangeListener(new WheelPicker.OnWheelChangeListener() {
            @Override
            public void onWheelScrolled(int offset) {

            }

            @Override
            public void onWheelSelected(int position) {
                binding.checkDay.setYearAndMonth(binding.checkYear.getSelectedYear(), binding.checkMonth.getSelectedMonth());//Monitor year and month
            }

            @Override
            public void onWheelScrollStateChanged(int state) {

            }
        });

    }

    //Gets the current date
    private void initDate() {


        Calendar cd = Calendar.getInstance();
        int year = cd.get(Calendar.YEAR);
        int month = cd.get(Calendar.MONTH) + 1;
        int day = cd.get(Calendar.DATE);
        binding.checkYear.setYearStart(year);
        binding.checkMonth.setSelectedMonth(month);
        binding.checkDay.setYearAndMonth(year, month);
        binding.checkDay.setSelectedDay(day);
        date = year + "-" + month + "-" + day;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onLocationUpdate(double latitude, double longitude) {
        getLocation.stopLocationUpdates();
        /*
        This is the listening data to get the location information, and then it gets the information and stores it in the mood data
         */
        loadingDialog.dismiss();//Hide the popover
        String lat = String.valueOf(latitude);
        String lon = String.valueOf(longitude);
        moodBeanList.add(new MoodRecord(date, "Happy", lat, lon, ""));
        moodBeanList.add(new MoodRecord(date, "Excited", lat, lon, ""));
        moodBeanList.add(new MoodRecord(date, "Relax", lat, lon, ""));
        moodBeanList.add(new MoodRecord(date, "Celebrate", lat, lon, ""));
        moodBeanList.add(new MoodRecord(date, "Sad", lat, lon, ""));
        moodBeanList.add(new MoodRecord(date, "Angry", lat, lon, ""));
        moodBeanList.add(new MoodRecord(date, "Bored", lat, lon, ""));
        moodBeanList.add(new MoodRecord(date, "Hurt", lat, lon, ""));
        moodAdapter.notifyDataSetChanged();
    }

    //This is where you start fetching location information after you get permission
    ActivityResultLauncher<String[]> launcher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
            result -> {
                if (result.get(Manifest.permission.ACCESS_FINE_LOCATION) != null
                        && result.get(Manifest.permission.ACCESS_COARSE_LOCATION) != null) {
                    if (Objects.requireNonNull(result.get(Manifest.permission.ACCESS_FINE_LOCATION)).equals(true)
                            && Objects.requireNonNull(result.get(Manifest.permission.ACCESS_COARSE_LOCATION)).equals(true)) {
                        //The action after all permissions are obtained
                        getLocation.startLocationUpdates();//Getting started
                    } else {
                        //There are actions whose permissions have not been obtained
                    }
                }
            });


    //Interface for switching pages

    public interface OnSwitchFragment {
        void onSwitchFragment();
    }

    public void steOnSwitchFragment(OnSwitchFragment onSwitchFragment) {
        this.onSwitchFragment = onSwitchFragment;
    }

}
