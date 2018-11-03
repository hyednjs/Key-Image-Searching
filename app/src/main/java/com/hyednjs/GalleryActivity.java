package com.hyednjs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.Photo;
import com.hyednjs.pagination.PhotoAdapter;
import com.hyednjs.pagination.PhotoDataSource;
import com.hyednjs.pagination.PhotoDataSourceFactory;

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LiveData<PagedList<Photo>> photoPagedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_gallery);

        String searchText = getIntent().getStringExtra("searchText");

        getSupportActionBar().setTitle(searchText);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);

        createPhotoPagedList(searchText);
        PhotoAdapter adapter = new PhotoAdapter();

        photoPagedList.observe(this, photos -> adapter.submitList(photos));

        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

        adapter.setOnClickListener((view, photo) -> {
            Intent photoDetailIntent = new Intent(this, PhotoDetailActivity.class);
            photoDetailIntent.putExtra("imageURL", photo.getLargeUrl());
            startActivity(photoDetailIntent);
        });
    }

    private void createPhotoPagedList(String searchText) {
        PhotoDataSourceFactory photoDataSourceFactory = new PhotoDataSourceFactory(searchText);

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(PhotoDataSource.PAGE_SIZE)
                        .build();

        photoPagedList = (new LivePagedListBuilder(photoDataSourceFactory, config)).build();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
