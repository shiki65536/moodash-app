package com.example.moodapp.utility;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.moodapp.R;

public class ConfirmDialog extends Dialog {
    public ConfirmDialog(@NonNull Context context) {
        super(context, R.style.DialogStyle);
    }

    private OnListener onListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirm);
        setCanceledOnTouchOutside(false);

        AppCompatTextView yesBtn = findViewById(R.id.confirm_yes);
        AppCompatTextView noBtn = findViewById(R.id.confirm_no);

        yesBtn.setOnClickListener(v -> {
            onListener.onListener();
            dismiss();
        });

        noBtn.setOnClickListener(v -> {
            dismiss();
        });
    }

    public interface OnListener {
        void onListener();
    }

    public void setOnListener(OnListener onListener) {
        this.onListener = onListener;
    }
}
