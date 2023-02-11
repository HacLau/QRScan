package com.liu.qrscan;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.liu.qrscan.dataBean.MainActivityBean;
import com.liu.qrscan.databinding.ActivityMainBinding;
import com.liu.qrscan.util.QRCodeUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Bitmap mBitmap;

    private final String TAG = getClass().getName();
    private ActivityMainBinding mBinding;
    private MainActivityBean mBean = new MainActivityBean();
    private final static int REQUEST_OPEN_ALBUM = 0x01;
    private final static int REQUEST_PERMISSION = 0x02;
    private String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
            ,Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.CAMERA};
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

    }

    private void toCreateQR() {
        Bitmap bitmap = QRCodeUtil.createQRImage(mBean.qrContent, 400, 400);
        mBinding.ivPre.setImageBitmap(bitmap);
    }


    private void toDiscernQR() {
        mBinding.ivPre.setDrawingCacheEnabled(true);
        Bitmap drawingCache = mBinding.ivPre.getDrawingCache();
        String result = QRCodeUtil.decodeQRCode(drawingCache);
        mBinding.ivPre.setDrawingCacheEnabled(false);
        mBinding.tvQrResult.setText(result);
    }

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUEST_OPEN_ALBUM);
    }

    private Bitmap getLogoBit() {
        return  BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
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
                        String picturePath = getPicturePath(uri);
                        Log.e(TAG,"picturePath = " + picturePath);
                        try {
                            mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                            mBitmap = rotateImage(mBitmap,readPictureDegree(picturePath));
                            String result = QRCodeUtil.decodeQRCode(mBitmap);
                            mBinding.ivPre.setImageBitmap(mBitmap);
                            mBinding.tvQrResult.setText("二维码识别结果：" + result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
            }
        }
    }


    /**
     * 对图片进行旋转，拍照后应用老是显示图片横向，而且是逆时针90度，现在给他设置成显示顺时针90度
     *
     * @param bitmap    图片
     * @param degree 顺时针旋转的角度
     * @return 返回旋转后的位图
     */
    public Bitmap rotateImage(Bitmap bitmap, float degree) {
        //create new matrix
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bmp;
    }

    /**
     * 根据图片路径去比对旋转的角度
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            //需要权限 不然会报bug
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;

    }


    private String getPicturePath(Uri uri) {
        String picture = "";
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        if(cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            picture = cursor.getString(column_index);
        }
        cursor.close();
        return picture;
    }

}