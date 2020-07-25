package com.curso.app.appsantamededeus.ui.calendario;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.curso.app.appsantamededeus.R;
import com.curso.app.appsantamededeus.activity.MainActivity;
import com.curso.app.appsantamededeus.model.Evento;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarioFragment extends Fragment {


    public static final String TAG = "CalendarView";
    private CalendarView calendarView;
    private Button agendar;
    public CalendarioFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);

        calendarView = (CalendarView)view.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String date = dayOfMonth + "/"+ (month+1) + "/"+ year;
                Log.d(TAG, "Data selecionada: data: "+date);

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
       return view;
    }

   /* @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        agendar = (Button) view.findViewById(R.id.salvarData);

        agendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);


            }
        });

    }*/

}
