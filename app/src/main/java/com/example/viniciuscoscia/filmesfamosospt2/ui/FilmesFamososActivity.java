package com.example.viniciuscoscia.filmesfamosospt2.ui;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.viniciuscoscia.filmesfamosospt2.R;
import com.example.viniciuscoscia.filmesfamosospt2.utils.NetworkUtils;

public abstract class FilmesFamososActivity extends AppCompatActivity {

    private View view;

    protected boolean hasInternetConnection() {
        if(!NetworkUtils.hasInternetConnection(this)){
            showErrorMessage();
            return false;
        }
        return true;
    }

    private void showErrorMessage() {
        try {
            Snackbar snackbar = Snackbar.make(view, getString(R.string.noInternetConnection), Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(getApplication().getString(R.string.retry), onClick -> noInternetConnectionAction());
            snackbar.show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, getString(R.string.noInternetConnection), Toast.LENGTH_LONG).show();
        }
    }

    protected void setErrorMessageView(int viewId) {
        view = findViewById(viewId);
    }

    protected void setErrorMessageView(View view) {
        this.view = view;
    }

    protected void noInternetConnectionAction(){}
}
