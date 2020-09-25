package com.curso.app.appsantamededeus.ui.agenda;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.curso.app.appsantamededeus.R;
import com.curso.app.appsantamededeus.activity.DetalheEventoctivity;
import com.curso.app.appsantamededeus.adapter.EventosAdapter;
import com.curso.app.appsantamededeus.adapter.RecyclerItemClickListener;
import com.curso.app.appsantamededeus.config.ConfiguracaoFirebase;
import com.curso.app.appsantamededeus.model.Evento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgendaFragment extends Fragment {

    private static final String TAG = "MyActivity";

    private EventosAdapter adapter;
    private RecyclerView listarEventos;
    private ArrayList<Evento> listEventos = new ArrayList<>();
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;
    private Evento eventoSelecionado;
    private AlertDialog dialog;

    public AgendaFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);

        listarEventos = (RecyclerView) view.findViewById(R.id.listaEventos);
        databaseReference = ConfiguracaoFirebase.getDatabase().child("calendario_eventos");

        //configurar Adapter
        adapter = new EventosAdapter(listEventos, getActivity());

        //configurar o recycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listarEventos.setLayoutManager( layoutManager);
        listarEventos.setHasFixedSize(true);
        listarEventos.setAdapter(adapter);

        recuperaEventos();

        //remover
        listarEventos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity(), listarEventos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                            }

                            @Override
                            public void onLongItemClick(final View view, final int position) {
                                    //Instaciar  AlertDialog
                                AlertDialog.Builder dialog =
                                        new AlertDialog.Builder(getActivity());

                                eventoSelecionado = listEventos.get(position);
                                //Configurar Titulo e msg
                                dialog.setIcon(android.R.drawable.ic_delete);
                            dialog.setTitle("Confirmar exclusão!");
                            dialog.setMessage("Deseja excluir o evento: "
                                    + listEventos.get(position).getDecricao() + "?");

                            //Configurar Acoes
                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.i(TAG, "Meus dados: " + eventoSelecionado.getDecricao());
                                        String usuario = ConfiguracaoFirebase.getIdUsuario();
                                      DatabaseReference reference = ConfiguracaoFirebase.getDatabase()
                                              .child("calendario_eventos")
                                              .child(eventoSelecionado.getId().toString())
                                              .child(eventoSelecionado.getUsuario().toString())
                                              .child(eventoSelecionado.getLocal().toString())
                                              .child(eventoSelecionado.getHoraInicio().toString())
                                              .child(eventoSelecionado.getHoraFim().toString())
                                              .child(eventoSelecionado.getDecricao().toString())
                                              .child(eventoSelecionado.getDataSalva().toString());

                                                reference.removeValue();


                                        Log.i(TAG, "Final de dados: " + eventoSelecionado.getId());
                                    }
                                });
                                dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                adapter.notifyDataSetChanged();

                                //Criar e exibir Alert
                                dialog.create();
                                dialog.show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        return view;
    }


    @Override
    public void onStop() {
        super.onStop();
        databaseReference.removeEventListener(valueEventListener);
    }

    public void recuperaEventos(){
       valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dados: dataSnapshot.getChildren()){
                    //FAzer aqui o delete de data quando for anterior
                    Evento evento = dados.getValue(Evento.class);
                    listEventos.add(evento);
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


}
