package es.dev_burnchat.burnchat;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Alvaro on 23/1/16.
 */
public class BurnApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this);


        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        Log.d("caca","caca");
    }
}
