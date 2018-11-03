package com.hyednjs.pagination;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;

import java.util.List;

public class FlickrUtil {

    private final static String apiKey = "f1a9a0328df0676588fa6288baeb9d45";
    private final static String sharedSecret = "b7e3214b728e625f";

    public static List<Photo> searchPhotos(String searchText, int pageSize, int page) throws FlickrException {
        Flickr flickr = new Flickr(apiKey, sharedSecret, new REST());
        SearchParameters searchParams = new SearchParameters();

        searchParams.setText(searchText);
        searchParams.setAccuracy(70);
        return flickr.getPhotosInterface().search(searchParams, pageSize, page);
    }
}
