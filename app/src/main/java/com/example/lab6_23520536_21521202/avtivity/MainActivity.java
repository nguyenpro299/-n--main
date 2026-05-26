package com.example.lab6_23520536_21521202.avtivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.lab6_23520536_21521202.ChatFragment; // Nhớ import ChatFragment
import com.example.lab6_23520536_21521202.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton; // Import FAB

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Cấu hình Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            NavigationUI.setupWithNavController(bottomNav, navController);
        }

        // 2. Cấu hình nút AI Chat (FAB)
        FloatingActionButton fabChat = findViewById(R.id.fab_ai_chat);
        fabChat.setOnClickListener(view -> {
            // Mở ChatFragment đè lên nav_host_fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, new ChatFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }
}