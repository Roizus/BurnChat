package es.dev_burnchat.burnchat;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alvaro on 9/2/16.
 */
public class FileUtilities {

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    public static Uri getOutputMediaFileUri(int mediaType) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        if (isExternalStorageAvailable()) {
            // get the URI

            // 1. Obteniendo el nombre del directorio externo.
            String appName = "BurnChat";

            File appDir=null;
            switch(mediaType){
                case MEDIA_TYPE_IMAGE:
                appDir=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),appName);
                break;
                case MEDIA_TYPE_VIDEO:
                    appDir=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),appName);
                break;
            }

            // 2. Create our subdirectory
            if(!appDir.exists()){
                Log.d("appDir",appDir.getAbsolutePath()+"notexist");
                if(!appDir.mkdirs()){
                    Log.d("appDir","Directory"+appDir.getAbsolutePath()+"notcreated");
                    return null;
                }
            }
            String fileName="";
            Date now=new Date();
            String timestamp=new SimpleDateFormat(
                    "yyyyMMdd_HHmmss",new Locale("es","ES")).format(now);
            switch(mediaType){
                case MEDIA_TYPE_IMAGE:
                fileName="IMG_"+timestamp+".jpg";
                break;
                case MEDIA_TYPE_VIDEO:
                fileName="VID_"+timestamp+".mp4";
                break;
            }
            String pathFile=appDir.getAbsolutePath()+
                    File.separator+
                    fileName;
            File mediaFile=new File(pathFile);
            Log.d("AppDir","File:"+mediaFile.getAbsolutePath());
            return Uri.fromFile(mediaFile);

        }
        else {
            return null;
        }
    }


    public static boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        else {
            return false;
        }
    }
}
