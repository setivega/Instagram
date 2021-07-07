package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("TZvIJ8fGLqariI2PKaPtNkJI13ggnZAFqSLgaAuu")
                .clientKey("NEgn1BJa6r7EfEQLHI0R0lZcpy2tQeiuiUaUYQkK")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
