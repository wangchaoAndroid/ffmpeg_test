package com.json.ffmpegdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    // Used to load the 'native-lib' library on application startup.
    static {
//        System.loadLibrary("native-lib");
        System.loadLibrary("ffmpeg");
    }
    public static final String TAG = "MainActivity";
    private SurfaceHolder holder;
    private VideoManager videoManager;
    private File file;
    private SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        surfaceView = findViewById(R.id.surf_view);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        TextView tv = findViewById(R.id.sample_text);
        TextView tv1 = findViewById(R.id.sample_text1);
        TextView tv2 = findViewById(R.id.sample_text2);
//        tv.setText(stringFromJNI());
        videoManager = new VideoManager();
        String externalFilesDir = getExternalFilesDir(null).getAbsolutePath();
        file = new File(Environment.getExternalStorageDirectory(),"/Download/test02.mp4");
        Log.e(TAG, "onCreate: "+ file.exists() );
        Info info = videoManager.getInfo(file.getAbsolutePath());
        double rate = info.rate;
        int tns = info.duration;
        int thh = tns / 3600;
        int tmm = (tns % 3600) / 60;
        int tss = (tns % 60);
        tv.setText(String.format("视频帧率 --->%sfps",rate));
        tv1.setText(String.format("媒体文件总时长：%d时%d分%d秒",thh, tmm, tss));
        tv2.setText(String.format("媒体文件url:%s",info.fileName));

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if(videoManager != null){
            videoManager.play(holder.getSurface(),file.getAbsolutePath());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();
}
