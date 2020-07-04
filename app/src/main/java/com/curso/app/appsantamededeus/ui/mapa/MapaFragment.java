package com.curso.app.appsantamededeus.ui.mapa;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.curso.app.appsantamededeus.R;

public class MapaFragment extends Fragment {

    public MapaFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.activity_localizaca, container, false);
    }
}
