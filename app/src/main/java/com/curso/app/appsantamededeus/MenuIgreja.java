package com.curso.app.appsantamededeus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.curso.app.appsantamededeus.config.ConfiguracaoFirebase;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MenuIgreja extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout mDrawer;
    private FirebaseAuth auth;
    private NavigationView navigationView;
    private  Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_igreja);
        // Configura barra de navegação
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Recupera authFireBase
        auth = ConfiguracaoFirebase.getAuth();

        // Cria a referência para toda a área do NavigationDrower
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Cria a referência para toda a área de navegação
        navigationView = (NavigationView)findViewById(R.id.nav_view);

        // Defini configurações do do NavgationDrower
       mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_calendario, R.id.nav_agenda, R.id.nav_musicas,
                R.id.nav_perfil, R.id.nav_mapa,R.id.nav_sair)
                .setDrawerLayout(mDrawer)
                .build();

        //Configura a área que irá carregar os fragments
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //Configuração do menu superior de navegação
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        //Configuração navegação para a NavigationView
        NavigationUI.setupWithNavController(navigationView, navController);

        //Desloga Usuario
        onClickSair(navController);

    }

    //Desloga usuario e encerra o app
    private void onClickSair(NavController navController) {
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                int id = destination.getId();

                switch (id){
                    case R.id.nav_sair:{
                        deslogarUsuario();
                        finish();
                        break;
                    }
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void deslogarUsuario(){
        try {
            auth.signOut();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

