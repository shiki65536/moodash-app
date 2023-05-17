package com.example.moodapp.mood;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.moodapp.viewmodel.ViewModelController;
import com.example.moodapp.database.MoodRecord;
import com.example.moodapp.database.QuoteCollection;
import com.example.moodapp.databinding.FragmentMoodFeedbackBinding;

import com.example.moodapp.utility.LoadingDialog;
import com.example.moodapp.utility.ModifySavedData;
import com.example.moodapp.viewmodel.MoodRecordViewModel;
import com.example.moodapp.viewmodel.QuoteCollectionViewModel;
import com.example.moodapp.viewmodel.QuoteNImageViewModel;

public class FragmentMoodFeedback extends Fragment {
    private FragmentMoodFeedbackBinding binding;

    public FragmentMoodFeedback(){}
    private MoodRecord moodBean;

    private String author;
    private String content;//Quote

    private Boolean image = false;//Determine if the image is returned
    private Boolean quote = false;//Determine if the quote is returned

    private final MoodRecordViewModel moodRecordViewModel = ViewModelController.moodRecordViewModel;//Access the mood viewModel

    private final QuoteCollectionViewModel quoteCollectionViewModel = ViewModelController.quoteCollectionViewModel;//Access the mood viewModel

    private QuoteCollection quoteCollection;//quote's entity

    private QuoteNImageViewModel viewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMoodFeedbackBinding.inflate(inflater);
        initData();
        return binding.getRoot();
    }
    @SuppressLint("SetTextI18n")
    private void initData(){
        viewModel = new ViewModelProvider(requireActivity()).get(QuoteNImageViewModel.class);
        //Initialize the popup and display the loading popup
        LoadingDialog loadingDialog = new LoadingDialog(requireContext());
        loadingDialog.show();
        //Find the data stored on the previous page to get the user's mood
        moodRecordViewModel.findByIDFuture(ModifySavedData.getInstance().getId()).thenApply(moodRecord -> {
            if (moodRecord != null){
                moodBean = moodRecord;
                String mood = moodBean.mood;//get mood

                /**
                 * Get the retrofit of the image and return the image data
                 * @param
                 * @param
                 */

                viewModel.getImage(mood);

                /**
                 * Get the retrofit of the image and return the quote data
                 * @param
                 * @param
                 */
                viewModel.getQuote(mood);
            }
            return moodRecord;
        });

        /*
         * Use retrofit to get the image quote isplay
         */

        viewModel.quoteResponse.observe(getViewLifecycleOwner(),quoteBeans -> {
            if (quoteBeans.size()>0){
                quote = true;
                if (image){
                    loadingDialog.dismiss();
                }
                //Take out the characters of the quote and the author separately
                content = quoteBeans.get(0).getQuote();
                author = quoteBeans.get(0).getAuthor();
                //Displayed on the UI page
                binding.maximContent.setText(content+"          ————"+author);
            }else {
                quote = true;
                if (image){
                    loadingDialog.dismiss();
                    Toast.makeText(requireContext(),"Loading failed. Please try again later",Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*
         * Use retrofit to get the image url to display
         */

        viewModel.imageResponse.observe(getViewLifecycleOwner(),imageResponse -> {
            if (imageResponse != null){
                image = true;//true after fetching the image
                if (quote){
                    loadingDialog.dismiss();//Hide the popover
                }
                Glide.with(this)
                        .load(imageResponse.getResults().get(0).getUrls().getRegular())
                        .diskCacheStrategy((DiskCacheStrategy.ALL))
                        .into(binding.maximIcon);
            }else {
                image = true;
                if (quote){
                    loadingDialog.dismiss();
                    Toast.makeText(requireContext(),"Loading failed. Please try again later",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Return to the home page without saving
        binding.maximNotSave.setOnClickListener(v->{
            requireActivity().onBackPressed();
        });

        //saving quote
        binding.maximSave.setOnClickListener(v->{
            if (content != null){
                quoteCollection = new QuoteCollection(content);//Put the quote into the entity
                quoteCollectionViewModel.insertQuoteCollection(quoteCollection);//Call the viewModel to store the quote data
                Toast.makeText(requireContext(),"Record Successfully!",Toast.LENGTH_SHORT).show();
                binding.maximIcon.setImageResource(0);
                binding.maximContent.setText("");
                requireActivity().onBackPressed();
            }else {
                //If the quote is empty, it cannot be stored
                Toast.makeText(requireContext(),"Record Failed!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}