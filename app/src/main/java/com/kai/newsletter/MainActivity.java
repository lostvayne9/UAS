package com.kai.newsletter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kai.justapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void profil(View view) {
        Intent intent = new Intent(MainActivity.this, profil.class);
        startActivity(intent);
    }

    public void galeri(View view) {
        Intent intent = new Intent(MainActivity.this, galeri.class);
        startActivity(intent);
    }

    public void kopi(View view) {
        Intent intent = new Intent(MainActivity.this, kopi.class);
        startActivity(intent);
    }
    public void berita(View view) {
        Intent intent = new Intent(MainActivity.this, berita.class);
        startActivity(intent);
    }
    public void feed(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:msidiq377@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Just App");
        if (intent.resolveActivity(getPackageManager()) !=null) {
            startActivity(intent);
        }
    }
}
