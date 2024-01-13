package com.nectar;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

public class PasswordToggleTextWatcher implements TextWatcher {

    private final EditText passwordEditText;
    private final ImageView passwordVisibilityToggle;

    public PasswordToggleTextWatcher(EditText passwordEditText, ImageView passwordVisibilityToggle) {
        this.passwordEditText = passwordEditText;
        this.passwordVisibilityToggle = passwordVisibilityToggle;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
        // Not used
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        // Not used
    }

    @Override
    public void afterTextChanged(Editable editable) {
        togglePasswordVisibility();
    }

    private void togglePasswordVisibility() {
        // Implement the visibility toggle logic here if needed
    }
}
