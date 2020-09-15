package com.luobo.gallery;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.OutputStream;

class SaveImage {
    private Context context;

    public SaveImage(Context context) {
        this.context = context;
    }

    public void saveImages(String fileName, Bitmap bitmap) {
        try {

            ContentResolver resolver = context.getApplicationContext().getContentResolver();

            Uri imageCollection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            ContentValues newImage = new ContentValues();
            //可选项
          /*//设置文件名
            newImage.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);

            //设置文件类型
            newImage.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                //android Q中不再使用DATA字段，而用RELATIVE_PATH代替
                //RELATIVE_PATH是相对路径不是绝对路径
                //DCIM是系统文件夹，关于系统文件夹可以到系统自带的文件管理器中查看，不可以写没存在的名字
                newImage.put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/");

            } else {
                newImage.put(MediaStore.Images.Media.DATA, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath());
            }*/

            Uri uri = resolver.insert(imageCollection, newImage);

            if (uri != null) {
                //若生成了uri，则表示该文件添加成功
                //使用流将内容写入该uri中即可
                OutputStream outputStream = context.getContentResolver().openOutputStream(uri);

                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                }
            }
        } catch (Exception e) {

        }
    }

}