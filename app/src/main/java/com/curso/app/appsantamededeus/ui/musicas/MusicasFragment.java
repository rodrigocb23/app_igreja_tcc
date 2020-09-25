package com.curso.app.appsantamededeus.ui.musicas;


import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.curso.app.appsantamededeus.R;
import com.google.android.material.textfield.TextInputEditText;

import java.net.URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicasFragment extends Fragment {

    public MusicasFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_musicas, container, false);

        final TextView viewById = view.findViewById(R.id.nomeMusica);
        Button botaoPesquisar = view.findViewById(R.id.botaoMusica);

        final WebView web = (WebView) view.findViewById(R.id.webViewMusica);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        web.setWebViewClient(new WebViewClient());

        botaoPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.loadUrl("https://www.cifraclub.com.br/?q=" + Uri.encode(viewById.getText().toString()));
            }
        });


    return view;
    }

}
