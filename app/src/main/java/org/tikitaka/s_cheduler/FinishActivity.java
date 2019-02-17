package org.tikitaka.s_cheduler;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class FinishActivity extends AppCompatActivity {

    private Button btnBack;
    private Button btnFinish;
    private ImageView imageView;
    private ImageButton btnClose,btnRotate, btnRetouch, btnCrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_camera);

        imageView=(ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        String image_path= intent.getStringExtra("imagePath");
        Uri fileUri = Uri.parse(image_path);
        imageView.setImageURI(fileUri);

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


}

