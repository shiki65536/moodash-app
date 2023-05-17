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

import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.RegexUtils;
import com.example.moodapp.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class FragmentLogin extends Fragment {
    private FirebaseAuth auth;
    private FragmentLoginBinding loginBinding;
    public FragmentLogin(){}
    private onSwitchFragment onSwitchFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = loginBinding.getRoot();
        auth = FirebaseAuth.getInstance();

        EditText emailEditText= view.findViewById(R.id.login_email_input);
        EditText passwordEditText= view.findViewById(R.id.login_password_input);

        loginBinding.loginConfirmButton.setOnClickListener(v -> {
            String user_email = emailEditText.getText().toString();
            String user_password = passwordEditText.getText().toString();

            if (TextUtils.isEmpty(user_email) || TextUtils.isEmpty(user_password)){
                toastMsg("Please fill out all the information.");
                return;
            }

            if (!RegexUtils.isEmail(user_email)){
                toastMsg("Please enter the correct email address!");
                return;
            }

            if (user_password.length()<6){
                toastMsg("Please enter a password with at least six digits!");
                return;
            }

            loginUser(user_email,user_password);
        });

        loginBinding.loginRegister.setOnClickListener(v->{
            if (onSwitchFragment != null){
                onSwitchFragment.switchFragment();
            }
        });

        return view;
    }
    private void loginUser(String user_email, String user_password) {
        // call the object and provide it with email id and password
        auth.signInWithEmailAndPassword(user_email, user_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String msg = "Login Successful";
                toastMsg(msg);
                Intent intent = new Intent(getActivity(), FrontActivity.class);
                startActivity(intent);
            }

        }
        );

        auth.signInWithEmailAndPassword(user_email,user_password).addOnFailureListener(e -> {
            String msg = "Account or Password incorrect!";
            toastMsg(msg);
        });
    }
    public void toastMsg(String message){
        Context context = getContext();
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public interface onSwitchFragment{
        void switchFragment();
    }

    public void switchFragment(onSwitchFragment onSwitchFragment){
        this.onSwitchFragment = onSwitchFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loginBinding = null;
    } }