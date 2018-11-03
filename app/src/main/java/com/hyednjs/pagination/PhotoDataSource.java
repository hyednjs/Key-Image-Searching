package com.hyednjs.pagination;

import android.util.Log;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

public class PhotoDataSource extends PageKeyedDataSource<Integer, Photo> {

    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 1;
    private String searchText;

    public PhotoDataSource(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
        try {
            callback.onResult(FlickrUtil.searchPhotos(searchText,PAGE_SIZE, FIRST_PAGE), null, FIRST_PAGE + 1);
        } catch (FlickrException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {
        try {
            callback.onResult(FlickrUtil.searchPhotos(searchText,PAGE_SIZE, (int)params.key),  (int)params.key + 1);
        } catch (FlickrException e) {
            e.printStackTrace();
        }
    }
}
