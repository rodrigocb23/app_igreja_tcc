package com.curso.app.appsantamededeus.ui.musicas;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.curso.app.appsantamededeus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicasFragment extends Fragment {


    public MusicasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_musicas, container, false);
    }

}
