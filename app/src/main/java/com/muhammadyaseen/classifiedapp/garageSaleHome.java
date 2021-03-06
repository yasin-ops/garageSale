package com.muhammadyaseen.classifiedapp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class garageSaleHome extends AppCompatActivity {
    NavController navController;

    private FirebaseAuth firebaseAuth;

    FirebaseDatabase firebaseDatabase;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_sale_home);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        final DrawerLayout drawerLayout=findViewById(R.id.garage_Sale_DrawerLayout);


        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);


            }
        });

        navigationView=findViewById(R.id.garageNavigationView);
        navigationView.setItemIconTintList(null);
        navController= Navigation.findNavController(this,R.id.garageNav_Host_fragment);


        NavigationUI.setupWithNavController(navigationView,navController);

        final TextView textTittle=findViewById(R.id.garage_Menu_tittle);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                textTittle.setText(destination.getLabel());


            }
        });
        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuid=item.getItemId();


                if(menuid==R.id.garageSaleHomeFragment)
                {
                    navController.navigate(R.id.garageSaleHomeFragment);
                    drawerLayout.closeDrawers();
                    return true;

                }

                if(menuid==R.id.postFragment)
                {
                    navController.navigate(R.id.postFragment);
                    drawerLayout.closeDrawers();
                    return true;

                }




                if(menuid==R.id.myPostFragment){
                    navController.navigate(R.id.myPostFragment);
                    drawerLayout.closeDrawers();
                    return true;

                }

                if(menuid==R.id.aboutGarageFragment){
                    navController.navigate(R.id.aboutGarageFragment);
                    drawerLayout.closeDrawers();
                    return true;

                }

                return false;
            }

        });


    }


}



