package Model.ImageGeneration.Generation;

import Model.ImageGeneration.Preferences.CubeImagePreferences;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

//TODO: restrict the cache to a specified size / # of images
//TODO: make caching optional
//TODO: Implement LRU algorithm
class ImageCache {

    private static ImageCache instance;

    //The directory to store the cached images
    private static String cacheDirectory = "image_cache/";

    //The url of visual cube
    private static final String url = "http://68.183.24.81/visualcube/visualcube.php?";

    private ImageCache() {}

    static ImageCache getInstance() {
        if(instance == null) {
            instance = new ImageCache();
        }
        return instance;
    }

    BufferedImage get(CubeImagePreferences pref) {
        File cachedImage = new File(cacheDirectory + pref.hashCode() + ".png");
        if(cachedImage.exists()) {
            return ImageHandler.getImage(cachedImage);
        }

        try {
            final BufferedImage result = ImageHandler.getImage(new URL(url + pref.toString()));

            //TODO: Check if this is thread safe (probably isnt)
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ImageHandler.saveImageToFile(result, cachedImage);
                }
            }).start();

            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    void clear() {
        for(File file : new File(cacheDirectory).listFiles()) {
            file.delete();
        }
    }
}