package com.zsw.zeng.helloapplication.slide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zsw.zeng.helloapplication.R;
import com.zsw.zeng.helloapplication.widget.ListPopWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MyFragment2 extends Fragment {
    private SimpleDraweeView draweeView1;
    private SimpleDraweeView draweeView2;
    private Button btnAdd;
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private File tempFile;
    int flag = 0;// 按钮标识  1表示是第一个上传按钮， 2表示第二个上传按钮

    private static final String PHOTO_FILE_NAME = "temp_photo.png";
    private static final String CUT_FILE_NAME = "cut_photo.png";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my2, container, false);
        draweeView1 = (SimpleDraweeView) view.findViewById(R.id.image);
        draweeView2 = (SimpleDraweeView) view.findViewById(R.id.image2);
        btnAdd = (Button) view.findViewById(R.id.btn_add);
        setListener();

        return view;
    }

    private void setListener() {
        draweeView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                photoPopwindow(draweeView2);
            }
        });
        draweeView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 2;
                photoPopwindow(draweeView2);
            }
        });
    }

    private void photoPopwindow(SimpleDraweeView draweeView) {
        List<String> list = new ArrayList<String>();
        list.add("拍照");
        list.add("从相册选择");
        ListPopWindow listPopWindow = new ListPopWindow(getActivity(), list);
        listPopWindow.setListPopWindow(new ListPopWindow.PopWindowCallBack() {
            @Override
            public void setCallBack(int position) {
                Log.e("qqq", position + "");
                Intent intent;

                switch (position) {
                    case 0:
                        // 照相机
                        intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        // 判断存储卡是否可以用，可用进行存储
                        if (hasSdcard()) {
                            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                    Uri.fromFile(new File(Environment
                                            .getExternalStorageDirectory(), PHOTO_FILE_NAME)));
                        }
                        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
                        break;
                    case 1:
                        // 图库选择
                        // 激活系统图库，选择一张图片
                        intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                        break;
                }
            }
        }).showView(draweeView);
    }

    private Bitmap bitmap;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (hasSdcard()) {
                tempFile = new File(Environment.getExternalStorageDirectory(),
                        PHOTO_FILE_NAME);
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(getContext(), "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT);
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            if (data != null) {
                bitmap = data.getParcelableExtra("data");
                if (bitmap != null) {
                    Uri uri1 = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null, null));
                    switch (flag){
                        case 1:
                            draweeView1.setImageURI(uri1);
                            break;
                        case 2:
                            draweeView2.setImageURI(uri1);
                            break;
                    }

                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 剪切图片
     *
     * @param uri
     * @function:
     * @author:Jerry
     * @date:2013-12-30
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "PNG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

}
