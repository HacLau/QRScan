package com.liu.qrscan;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.liu.qrscan.dataBean.MainActivityBean;
import com.liu.qrscan.databinding.ActivityMainBinding;
import com.liu.qrscan.util.ImageTools;
import com.liu.qrscan.util.QRCodeUtil;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Bitmap mBitmap;

    private final String TAG = getClass().getName();
    private ActivityMainBinding mBinding;
    private MainActivityBean mBean = new MainActivityBean();
    private final static int REQUEST_OPEN_ALBUM = 0x01;
    private final static int REQUEST_OPEN_CAMERA = 0x02;
    private final static int REQUEST_PERMISSION = 0x03;
    private String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
            ,Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.CAMERA,
            Manifest.permission.VIBRATE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mBinding.setBean(mBean);
        requestPermission();
        initView();
    }

    private void requestPermission() {
        // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
        ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_PERMISSION);
    }

    private void initView() {
        if (mBitmap == null)
            mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mBinding.ivPre.setImageBitmap(mBitmap);
        mBinding.btnTodo.setOnClickListener(v -> {
            hideSoftKeyBoard(v.getWindowToken());
            toCreateQR();
        });

        mBinding.btnToResult.setOnClickListener(v -> {
            hideSoftKeyBoard(v.getWindowToken());
            toDiscernQR();
        });

        mBinding.btnOpenAlbum.setOnClickListener(v -> {
            hideSoftKeyBoard(v.getWindowToken());
            openAlbum();
        });

        mBinding.btnOpenCamera.setOnClickListener(v -> {
            hideSoftKeyBoard(v.getWindowToken());
            openCamera();
        });
    }

    private void openCamera() {
        Intent intent = new Intent(this,QRScannerActivity.class);
        startActivityForResult(intent,REQUEST_OPEN_CAMERA);
    }

    private void toCreateQR() {
        Bitmap bitmap = QRCodeUtil.createQRImage(mBean.qrContent, mBitmap.getWidth(), mBitmap.getHeight());
        mBinding.ivPre.setImageBitmap(mBitmap);
    }


    private void toDiscernQR() {
        if (mBitmap != null) {
            String result = QRCodeUtil.decodeQRCodeByRGB(mBitmap);
            mBinding.tvQrResult.setText("二维码识别结果：" + result);
        }
    }

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUEST_OPEN_ALBUM);
    }

    private void hideSoftKeyBoard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_PERMISSION :
            if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(this, "权限开启失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_OPEN_ALBUM:
                    if (data != null) {
                        Uri uri = data.getData();
                        String picturePath = ImageTools.getPicturePath(this,uri);
                        Log.e(TAG,"picturePath = " + picturePath);
                        try {
                            mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                            mBitmap = ImageTools.rotateImage(mBitmap,ImageTools.readPictureDegree(picturePath));
//                            mBitmap = ImageTools.convertToBlackWhite(mBitmap);
                            mBitmap = ImageTools.toGrayscale(mBitmap);
                            String result = QRCodeUtil.decodeQRCodeByRGB(mBitmap);
                            mBinding.ivPre.setImageBitmap(mBitmap);
                            Log.e(TAG,"原始图像大小：" + mBitmap.getByteCount());
                            mBinding.tvQrResult.setText("二维码识别结果：" + result);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case REQUEST_OPEN_CAMERA:
                    Log.e(TAG,"REQUEST_OPEN_CAMERA");
                    String result = data.getStringExtra("result");
                    mBinding.tvQrResult.setText("二维码识别结果：" + result);
                default:
            }
        }
    }




}