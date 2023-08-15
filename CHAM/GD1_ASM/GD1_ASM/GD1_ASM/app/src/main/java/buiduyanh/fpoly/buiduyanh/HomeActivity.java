package buiduyanh.fpoly.buiduyanh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import buiduyanh.fpoly.buiduyanh.Fragment.HomeFragment;
import buiduyanh.fpoly.buiduyanh.Fragment.KhoaHocFragment;

public class HomeActivity extends AppCompatActivity {

    NavigationView navigationView;
    public static DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findById();

        getSupportFragmentManager().beginTransaction().replace(R.id.container_main,new HomeFragment()).commit();
        navigationView.setItemIconTintList(null);




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.ic_home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_main,new HomeFragment()).commit();

                    drawerLayout.close();
                } else if (item.getItemId() == R.id.ic_course) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_main,new KhoaHocFragment()).commit();

                    drawerLayout.close();
                }

                return true;
            }
        });
    }

    private void findById(){
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

    }
}