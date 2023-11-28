//package com.example.b07project;
//
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.activity.OnBackPressedCallback;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class BackButton extends AppCompatActivity {
//    protected void onCreate(Bundle SavedInstanceState) {
//        super.onCreate(SavedInstanceState);
//        setContentView(R.layout.activity_complaints);
//
//            @Override
//            public boolean onCreateOptionsMenu(Menu menu) {
//                getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
//                return true;
//            }
//
//            @Override
//            public boolean onOptionsItemSelected(MenuItem item) {
//                if (item.getItemId() == R.id.backButton) {
//                    // Handle the back button click here
//                    onBackPressed();
//                    return true;
//                }
//                return super.onOptionsItemSelected(item);
//            }
//    }
//}
