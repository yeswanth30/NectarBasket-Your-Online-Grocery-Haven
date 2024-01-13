package com.nectar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class PdfViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pdf_viewer);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        WebView pdfWebView = findViewById(R.id.pdfWebView);
        WebSettings webSettings = pdfWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String pdfContent = getIntent().getStringExtra("pdfContent");

        pdfWebView.loadData(pdfContent, "application/pdf", "UTF-8");
    }
}
