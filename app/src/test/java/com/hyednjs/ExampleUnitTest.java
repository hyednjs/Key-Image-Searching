package com.hyednjs;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void flickrTest() {
        String apiKey = "f1a9a0328df0676588fa6288baeb9d45";
        String sharedSecret = "b7e3214b728e625f";
        Flickr flickr = new Flickr(apiKey, sharedSecret, new REST());
        SearchParameters params = new SearchParameters();
        PhotoList<Photo> list;

        params.setText("golden gate bridge");
        params.setAccuracy(70);
        try {
            list = flickr.getPhotosInterface().search(params, 20, 1);
            for (Photo p : list) {
                System.out.println(p.getTitle());
                System.out.println(p.getMediumUrl());
            }
        } catch (FlickrException e) {
            e.printStackTrace();
        }
    }
}