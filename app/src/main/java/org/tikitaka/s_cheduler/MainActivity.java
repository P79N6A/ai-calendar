package org.tikitaka.s_cheduler;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.tikitaka.s_cheduler.Fragment.BoardFragment;
import org.tikitaka.s_cheduler.Fragment.HomeFragment;
import org.tikitaka.s_cheduler.Helper.FullScreenDialog;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            DialogFragment dialog = FullScreenDialog.newInstance();
            ((FullScreenDialog) dialog).setCallback(new FullScreenDialog.Callback() {
                @Override
                public void onActionClick(String name) {
                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show(getSupportFragmentManager(), "tag");
        }

        });
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
