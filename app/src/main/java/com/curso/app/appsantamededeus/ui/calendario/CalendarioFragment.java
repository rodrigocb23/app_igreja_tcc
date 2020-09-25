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
import android.widget.Toast;

import com.curso.app.appsantamededeus.R;
import com.curso.app.appsantamededeus.activity.MainActivity;
import com.curso.app.appsantamededeus.model.Evento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                try {
                   Date data = sdf1.parse(date);
                    final Date dateHoje = new Date();
                    if(!data.before(dateHoje)||data==dateHoje){

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("date", date);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getActivity(),
                                "Data Inválida!",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
       return view;
    }

}
