package org.tikitaka.s_cheduler;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v4.app.Fragment;

import org.tikitaka.s_cheduler.Fragment.BoardFragment;
import org.tikitaka.s_cheduler.Fragment.HomeFragment;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        bottomNavigationView.setSelectedItemId(R.id.nav_today);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){

                    switch (menuItem.getItemId()) {

                        case R.id.nav_board:
                            selectedFragment=new BoardFragment();
                            break;

                        case R.id.nav_today:
                            selectedFragment=new HomeFragment();
                            break;

                        case R.id.nav_add:
                            selectedFragment=null;
                            startActivity(new Intent(MainActivity.this,AddActivity.class));
                            break;

                    }

                    if (selectedFragment !=null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    }

                    return true;
                }
            };
    }
