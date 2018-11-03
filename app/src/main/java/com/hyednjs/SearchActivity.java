package com.hyednjs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }

    public void onSearch(View v) {
        EditText editText = (EditText)findViewById(R.id.editText);
        String searchText = editText.getText().toString();
        Intent galleryIntent = new Intent(this, GalleryActivity.class);
        galleryIntent.putExtra("searchText", searchText);
        startActivity(galleryIntent);
    }
}
