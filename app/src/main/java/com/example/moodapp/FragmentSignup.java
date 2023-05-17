package com.example.moodapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.RegexUtils;
import com.example.moodapp.databinding.FragmentSignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FragmentSignup extends Fragment {
    private FirebaseAuth auth;
    private FragmentSignupBinding signupBinding;
    public FragmentSignup(){}
    private FragmentSignup.onSwitchFragment onSwitchFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        signupBinding = FragmentSignupBinding.inflate(inflater, container, false);
        View view = signupBinding.getRoot();
        auth = FirebaseAuth.getInstance();

        EditText nameEditText= view.findViewById(R.id.signup_name_input);
        EditText emailEditText= view.findViewById(R.id.signup_email_input);
        EditText passwordEditText= view.findViewById(R.id.signup_password_input);
        EditText passwordCheck= view.findViewById(R.id.signup_password_check);



        signupBinding.signupConfirmButton.setOnClickListener(v -> {
            String user_name = nameEditText.getText().toString();
            String user_email = emailEditText.getText().toString();
            String user_password = passwordEditText.getText().toString();
            String user_password_check = passwordCheck.getText().toString();

            if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(user_email)  ||  TextUtils.isEmpty(user_password) ||TextUtils.isEmpty(user_password_check)) {
                String msg = "Please fill out all the information.";
                toastMsg(msg);
            }
            else if (user_password.length() < 6) {
                String msg = "The password should be for than 6 words.";
                toastMsg(msg);
            } else if (!RegexUtils.isEmail(user_email)){
                String msg = "Please enter the correct email address!";
                toastMsg(msg);
            } else if (!user_password.equals(user_password_check)){
                String msg = "Password incorrect !";
                toastMsg(msg);
            }
            else {
                registerUser(user_email, user_password, user_name);
            }

        });

        signupBinding.registerLogin.setOnClickListener(v->{
            if (onSwitchFragment != null){
                onSwitchFragment.switchFragment();
            }
        });


        return view;
    }

    private void registerUser(String user_email, String user_password, String user_name) {
        // To create username and password
        auth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    String msg = "Registration Successful";
                    toastMsg(msg);
                    Intent intent = new Intent(getActivity(), FrontActivity.class);
                    startActivity(intent);
                } else {
                    String msg = "Registration Unsuccessful";
                    toastMsg(msg);
                } }
        });
    }
    public void toastMsg(String message){
        Context context = getContext();
        Toast.makeText(context, message,Toast.LENGTH_SHORT).show();
    }

    public interface onSwitchFragment{
        void switchFragment();
    }

    public void switchFragment(FragmentSignup.onSwitchFragment onSwitchFragment){
        this.onSwitchFragment = onSwitchFragment;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        signupBinding = null; }
}