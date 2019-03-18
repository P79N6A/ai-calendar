package org.tikitaka.s_cheduler;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FinishActivity extends AppCompatActivity {


    private Button btnBack;
    private Button btnFinish;
    private ImageButton btnClose,btnRotate, btnRetouch, btnCrop;
    private Bitmap bitmap;

    private static final String CLOUD_VISION_API_KEY = "AIzaSyDFozAaAoxC33-ddqQqJxZkfnlLeiIT9Ec";
    public static final String FILE_NAME = "temp.jpg";

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int GALLERY_PERMISSIONS_REQUEST = 0;
    private static final int GALLERY_IMAGE_REQUEST = 1;
    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;

    private TextView mImageDetails;
    private ImageView mMainImage;
    private long startTimeMS;
    private float uploadDurationSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_camera);

        mImageDetails = (TextView) findViewById(R.id.image_details);
        mImageDetails.setMovementMethod(new ScrollingMovementMethod());
        //mImageDetails.setText("uploading image please wait");


        mMainImage=(ImageView) findViewById(R.id.main_image);
        Intent intent = getIntent();
        String image_path= intent.getStringExtra("imagePath");
        Uri fileUri = Uri.parse(image_path);
        mMainImage.setImageURI(fileUri);

        bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), fileUri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        callCloudVision(bitmap);

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
        finish();

    }

    public void goForward(View v) {
        Toast.makeText(getApplicationContext(),"일정 등록을 시작합니다",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(FinishActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public File getCameraFile() {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            uploadImage(data.getData());
        } else if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
            uploadImage(Uri.fromFile(getCameraFile()));
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, CAMERA_PERMISSIONS_REQUEST, grantResults)) {
                    if (PermissionUtils.requestPermission(
                            this,
                            CAMERA_PERMISSIONS_REQUEST,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA)) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getCameraFile()));
                        startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
                    }
                }
                break;
            case GALLERY_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, GALLERY_PERMISSIONS_REQUEST, grantResults)) {
                    if (PermissionUtils.requestPermission(this, GALLERY_PERMISSIONS_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select a photo"),
                                GALLERY_IMAGE_REQUEST);
                    }
                }
                break;
        }
    }

    public void uploadImage(Uri uri) {
        startTimeMS = System.currentTimeMillis();
        if (uri != null) {
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap =
                        scaleBitmapDown(
                        MediaStore.Images.Media.getBitmap(getContentResolver(), uri),
                        1200);

                callCloudVision(bitmap);
                mMainImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                Log.d(TAG, "Image picking failed because " + e.getMessage());
                Toast.makeText(this, "", Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d(TAG, "Image picker gave us a null image.");
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        }
    }

    private void callCloudVision(final Bitmap bitmap){
        // Switch text to loading
        mImageDetails.setText(R.string.loading_message);

        // Do the real work in an async task, because we need to use the network anyway
        new AsyncTask<Object, Void, String>() {
            @Override
            protected String doInBackground(Object... params) {
                try {
                    HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
                    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

                    Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
                    builder.setVisionRequestInitializer(new
                            VisionRequestInitializer(CLOUD_VISION_API_KEY));
                    Vision vision = builder.build();

                    BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                            new BatchAnnotateImagesRequest();
                    batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
                        AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

                        // Add the image
                        Image base64EncodedImage = new Image();
                        // Convert the bitmap to a JPEG
                        // Just in case it's a format that Android understands but Cloud Vision
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                        byte[] imageBytes = byteArrayOutputStream.toByteArray();

                        // Base64 encode the JPEG
                        base64EncodedImage.encodeContent(imageBytes);
                        annotateImageRequest.setImage(base64EncodedImage);

                        // add the features we want
                        annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                            Feature labelDetection = new Feature();
                            labelDetection.setType("TEXT_DETECTION");
                            labelDetection.setMaxResults(10);
                            add(labelDetection);
                        }});

                        // Add the list of one thing to the request
                        add(annotateImageRequest);
                    }});

                    Vision.Images.Annotate annotateRequest =
                            vision.images().annotate(batchAnnotateImagesRequest);
                    // Due to a bug: requests to Vision API containing large images fail when GZipped.
                    annotateRequest.setDisableGZipContent(true);
                    Log.d(TAG, "created Cloud Vision request object, sending request");

                    long sendMS = System.currentTimeMillis();
                    BatchAnnotateImagesResponse response = annotateRequest.execute();
                    uploadDurationSec = (System.currentTimeMillis() - sendMS) / 1000f;
                    return convertResponseToString(response);

                } catch (GoogleJsonResponseException e) {
                    Log.d(TAG, "failed to make API request because " + e.getContent());
                } catch (IOException e) {
                    Log.d(TAG, "failed to make API request because of other IOException " +
                            e.getMessage());
                }
                return "Cloud Vision API request failed. Check logs for details.";
            }

            protected void onPostExecute(String result) {
                mImageDetails.setText(result);
            }
        }.execute();
    }

    public Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    private String convertResponseToString(BatchAnnotateImagesResponse response) {
        long spentMS = System.currentTimeMillis() - startTimeMS;
        String message = "Recognition results:\n";
        StringBuilder builder = new StringBuilder(message);
        builder.append(String.format("(Total spent %.2f secs, including %.2f secs for upload)\n\n", spentMS / 1000f, uploadDurationSec));

        List<EntityAnnotation> labels = response.getResponses().get(0).getTextAnnotations();
        Log.i("JackTest", "total labels:" + labels.size());
        if (labels != null) {
            for (int i = 0; i < labels.size(); i++ ) {
                EntityAnnotation label = labels.get(i);
                if (i == 0) {
                    builder.append("Locale: ");
                    builder.append(label.getLocale());
                }
                builder.append(label.getDescription());
                builder.append("\n");
                //TODO: Draw rectangles later
                break;
            }
        } else {
            builder.append("nothing");
        }

        return builder.toString();
    }
}

