package com.curso.app.appsantamededeus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.curso.app.appsantamededeus.R;
import com.curso.app.appsantamededeus.model.Base64Custon;
import com.curso.app.appsantamededeus.model.Evento;

import java.text.SimpleDateFormat;
import java.util.List;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.MyViewHolder> {

    private List<Evento> eventos;
    private Context context;
    private View view;

    public EventosAdapter(List<Evento> ListEventos, Context c) {
        this.eventos = ListEventos;
        this.context = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_eventos, parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Evento evento = eventos.get(position);

        String data = evento.getDataSalva();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

        holder.data.setText("Data: " + Base64Custon.decodeBase64(evento.getDataSalva()));
        holder.evento.setText("Descrição: "+evento.getDecricao());
        holder.local.setText("Local:" + evento.getLocal());
        holder.hora.setText("Hora: "+ Base64Custon.decodeBase64(evento.getHoraInicio())+
                "  ATÉ  " + "Hora: "+ Base64Custon.decodeBase64(evento.getHoraFim()));
        holder.usuario.setText("Responsável: " + Base64Custon.decodeBase64(evento.getUsuario()));
        holder.id.setText(evento.getId());


    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView data, evento, local, usuario, hora, id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

         evento = itemView.findViewById(R.id.textEvento);
         data = itemView.findViewById(R.id.textDataEvento);
         local = itemView.findViewById(R.id.adpterLocal);
         usuario = itemView.findViewById(R.id.adpterUser);
         hora = itemView.findViewById(R.id.adpterHora);
         id = itemView.findViewById(R.id.adpterId);


        }
    }


}
