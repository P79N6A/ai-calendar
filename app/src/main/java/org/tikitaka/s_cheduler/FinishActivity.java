package org.tikitaka.s_cheduler;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FinishActivity extends AppCompatActivity {

    private Button btnBack;
    private Button btnFinish;
    private ImageView imageView;
    private TextView textView;
    private ImageButton btnClose,btnRotate, btnRetouch, btnCrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        textView=(TextView)findViewById(R.id.textView);

        imageView = (ImageView) findViewById(R.id.imageView);

        btnClose= (ImageButton)findViewById(R.id.close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRotate= (ImageButton)findViewById(R.id.rotate);
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotate();
            }
        });

        btnRetouch= (ImageButton)findViewById(R.id.retouch);
        btnRetouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retouch();
            }
        });

        btnCrop= (ImageButton)findViewById(R.id.crop);
        btnCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crop();
            }
        });

        btnBack = (Button) findViewById(R.id.btnBack);

        btnFinish = (Button) findViewById(R.id.btnFinish);
    }


    private void rotate(){

    }

    private void retouch() {

    }

    private void crop() {

    }

    public void goBack(View v) {
        Intent intent = new Intent(FinishActivity.this,AddActivity.class);
        startActivity(intent);

    }

    public void goForward() {

    }


}

