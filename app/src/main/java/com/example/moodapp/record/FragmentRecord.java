package com.example.moodapp.record;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.moodapp.R;
import com.example.moodapp.database.MoodRecord;
import com.example.moodapp.database.MoodRecordViewModel;
import com.example.moodapp.databinding.FragmentRecordBinding;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class FragmentRecord extends Fragment {
    private FragmentRecordBinding recordBinding;
    private MoodRecordViewModel moodRecordViewModel;
    private List<MoodRecord> allMoodRecords;
    private List<MoodRecord> filteredMoodRecords;
    private EditText etStartDate, etEndDate;
    private DatePickerDialog pickerStart, pickerEnd;
    private RadioGroup rgGraphType;
    private PieChart pieChart;
    private BarChart barChart;
    private Date startDate, endDate;
    private boolean isPieChart;
    private CallbackManager callbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recordBinding = FragmentRecordBinding.inflate(getLayoutInflater());
        View view = recordBinding.getRoot();

        pieChart = recordBinding.pieChart;
        barChart = recordBinding.barChart;

        // Clear the filteredMoodRecords list before adding new records
//        filteredMoodRecords.clear();
        initViewModel();
        initDatePicker();
        initRadioGroup();
        initShareButton();
        return view;
    }

    private void initShareButton() {
        recordBinding.btnFacebookShareLink.setOnClickListener(v -> {
            // Call a method to share the graph to Facebook
            shareToFacebook();
        });
    }
    private void shareToFacebook() {
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://carrotpeace.github.io/Moodash/"))
                .setQuote("Check out an amazing mood tracker app!")
                .setShareHashtag(new com.facebook.share.model.ShareHashtag.Builder()
                        .setHashtag("#Mood")
                        .build())
                .build();

        // Create a callback manager
        CallbackManager callbackManager = CallbackManager.Factory.create();

        // Create a callback to handle the share result
        FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(requireContext(), "Shared successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(requireContext(), "Sharing cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(requireContext(), "Error sharing: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        // Create a ShareDialog and set the share content and callback
        ShareDialog shareDialog = new ShareDialog(requireActivity());
        shareDialog.registerCallback(callbackManager, shareCallback);
        shareDialog.show(content);
    }


    private void initViewModel() {
//        MoodRecordRepository mRepository = new MoodRecordRepository(
//                requireActivity().getApplication());
//        allMoodRecords = mRepository.getMoodRecords();
        moodRecordViewModel = new ViewModelProvider(requireActivity()).get(MoodRecordViewModel.class);
        moodRecordViewModel.getAllMoodRecords().observe(getViewLifecycleOwner(), moodRecords -> {
            if (moodRecords != null && moodRecords.size() > 0) {
                allMoodRecords = moodRecords;
                filterMoodRecords(startDate, endDate);
                updateGraph(filteredMoodRecords);
                for (MoodRecord moodRecord : allMoodRecords) {
                    Log.d("tag", "xxxxxxxxx" + moodRecord.mood);
                }
            }
            else {
                Toast.makeText(requireContext(), "No records found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDatePicker() {
        // Set the default start and end dates (e.g., one month from the current date)
        Calendar calendar = Calendar.getInstance();
        endDate = calendar.getTime();
        calendar.add(Calendar.MONTH, -1);
        startDate = calendar.getTime();

        // Set the initial values for the EditTexts
        recordBinding.etStartDate.setText(formatDate(startDate));
        recordBinding.etEndDate.setText(formatDate(endDate));
        recordBinding.etStartDate.setInputType(InputType.TYPE_NULL);
        recordBinding.etEndDate.setInputType(InputType.TYPE_NULL);

        // Set the click listeners for the EditTexts
        recordBinding.etStartDate.setOnClickListener(v -> showStartDatePicker());
        recordBinding.etEndDate.setOnClickListener(v -> showEndDatePicker());
    }

    private void showStartDatePicker() {
        final Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(startDate);
        int day = calendarStart.get(Calendar.DAY_OF_MONTH);
        int month = calendarStart.get(Calendar.MONTH);
        int year = calendarStart.get(Calendar.YEAR);

        DatePickerDialog pickerStart = new DatePickerDialog(requireContext(), (view, yearSelected, monthOfYear, dayOfMonth) -> {
            startDate = getDateFromPicker(yearSelected, monthOfYear, dayOfMonth);
            recordBinding.etStartDate.setText(formatDate(startDate));
            filterMoodRecords(startDate, endDate);
            updateGraph(filteredMoodRecords);
        }, year, month, day);
        pickerStart.show();
    }

    private void showEndDatePicker() {
        final Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(endDate);
        int day = calendarEnd.get(Calendar.DAY_OF_MONTH);
        int month = calendarEnd.get(Calendar.MONTH);
        int year = calendarEnd.get(Calendar.YEAR);

        DatePickerDialog pickerEnd = new DatePickerDialog(requireContext(), (view, yearSelected, monthOfYear, dayOfMonth) -> {
            Calendar selectedStartDate = Calendar.getInstance();
            selectedStartDate.setTime(startDate);

            Calendar selectedEndDate = Calendar.getInstance();
            selectedEndDate.set(yearSelected, monthOfYear, dayOfMonth);

            if (selectedEndDate.compareTo(selectedStartDate) >= 0) {
                endDate = getDateFromPicker(yearSelected, monthOfYear, dayOfMonth);
                recordBinding.etEndDate.setText(formatDate(endDate));
                filterMoodRecords(startDate, endDate);
                updateGraph(filteredMoodRecords);
            } else {
                Toast.makeText(requireContext(), "End date cannot be earlier than start date", Toast.LENGTH_SHORT).show();
            }
        }, year, month, day);
        pickerEnd.show();
    }

    private Date getDateFromPicker(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd", Locale.getDefault());
        return sdf.format(date);
    }

    private void initRadioGroup() {
        recordBinding.rgGraphType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbPieChart) {
                isPieChart = true;
            } else if (checkedId == R.id.rbBarChart) {
                isPieChart = false;
            }
            updateGraph(filteredMoodRecords);
        });
    }

    private void filterMoodRecords(Date startDate, Date endDate) {
        filteredMoodRecords = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd", Locale.getDefault());

        if (allMoodRecords != null) {
            for (MoodRecord moodRecord : allMoodRecords) {
                try {
                    Date recordDate = dateFormat.parse(moodRecord.date);
                    if (recordDate.after(startDate) && recordDate.before(endDate)) {
                        filteredMoodRecords.add(moodRecord);
                    }
                } catch (ParseException e) {
                    // Handle the exception if the date parsing fails
                    e.printStackTrace();
                }
            }
        }
        else {
            Log.d("NullRecord", "allMoodRecords is null");
            Toast.makeText(requireContext(), "No records found", Toast.LENGTH_SHORT).show();
        }


    }

    private void updateGraph(List<MoodRecord> filteredMoodRecords) {
        List<PieEntry> pieEntries = new ArrayList<>();
        List<BarEntry> barEntries = new ArrayList<>();

        HashSet<String> uniqueMoods = new HashSet<>();

        if (filteredMoodRecords == null || filteredMoodRecords.isEmpty()) {
            recordBinding.barChart.setVisibility(View.GONE);
            recordBinding.pieChart.setVisibility(View.GONE);
            recordBinding.tvNoData.setVisibility(View.VISIBLE);
            return;
        } else {
            recordBinding.tvNoData.setVisibility(View.GONE);
        }

        if (isPieChart) {
            // Convert MoodRecord objects to PieEntry objects for pie chart
            for (MoodRecord moodRecord : filteredMoodRecords) {
                String mood = moodRecord.mood;

                // Check if the mood is already counted
                if (uniqueMoods.contains(mood)) {
                    continue; // Skip the duplicate mood
                }
                uniqueMoods.add(mood);

                // Count the number of times the mood appears in the selected period
                int count = getMoodCount(filteredMoodRecords, mood);

                PieEntry pieEntry = new PieEntry(count, mood);
                pieEntries.add(pieEntry);
            }

            drawPieChart(pieEntries);
            recordBinding.barChart.setVisibility(View.GONE);
            recordBinding.pieChart.setVisibility(View.VISIBLE);
        } else {
            // Convert MoodRecord objects to BarEntry objects for bar chart
            for (MoodRecord moodRecord : filteredMoodRecords) {
                String mood = moodRecord.mood;

                // Check if the mood is already counted
                if (uniqueMoods.contains(mood)) {
                    continue; // Skip the duplicate mood
                }
                uniqueMoods.add(mood);

                // Assuming you have a method to get the count of a specific mood in the selected period
                int count = getMoodCount(filteredMoodRecords, mood);

                BarEntry barEntry = new BarEntry(getXAxisLabels(filteredMoodRecords).indexOf(mood), count);
                barEntries.add(barEntry);
            }
            drawBarChart(barEntries);
            recordBinding.pieChart.setVisibility(View.GONE);
            recordBinding.barChart.setVisibility(View.VISIBLE);
        }
    }

    private void drawBarChart(List<BarEntry> barEntries) {
        BarChart barChart = this.barChart;

        BarDataSet barDataSet = new BarDataSet(barEntries, "Mood");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getXAxisLabels(filteredMoodRecords))); // Set the x-axis value formatter

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        barChart.setVisibility(View.VISIBLE);
        barChart.animateXY(1000, 1000);

        Description description = new Description();
        description.setText("Mood");
        barChart.setDescription(description);

        barChart.invalidate();
    }

    private void drawPieChart(List<PieEntry> pieEntries) {
        PieChart pieChart = this.pieChart;

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Mood");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        pieChart.setVisibility(View.VISIBLE);
        pieChart.animateXY(1000, 1000);

        Description description = new Description();
        description.setText("Mood");
        pieChart.setDescription(description);

        pieChart.invalidate();
    }

    private int getMoodCount(List<MoodRecord> filteredMoodRecords, String mood) {
        int count = 0;
        for (MoodRecord moodRecord : filteredMoodRecords) {
            if (moodRecord.mood.equals(mood)) {
                count++;
            }
        }
        return count;
    }

    private List<String> getXAxisLabels(List<MoodRecord> filteredMoodRecords) {
        List<String> xAxisLabels = new ArrayList<>();

        // Retrieve distinct mood labels
        Set<String> distinctMoods = new HashSet<>();
        for (MoodRecord moodRecord : filteredMoodRecords) {
            distinctMoods.add(moodRecord.mood);
        }

        // Add distinct mood labels to the x-axis labels
        xAxisLabels.addAll(distinctMoods);

        return xAxisLabels;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recordBinding = null;
    }
}

