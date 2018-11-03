package com.hyednjs.pagination;

import com.flickr4java.flickr.photos.Photo;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class PhotoDataSourceFactory extends DataSource.Factory {

    private String searchText;

    @NonNull
    @Override
    public DataSource create() {
        PhotoDataSource photoDataSource = new PhotoDataSource(searchText);
        return photoDataSource;
    }

    public PhotoDataSourceFactory(String searchText) {
        this.searchText = searchText;
    }
}
