package com.example.codeexp.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BitmapUtils {

    @Nullable
    public static Bitmap covertNV21ToBitmap(@NonNull byte[] data, int width, int height) {
        if (data != null && data.length != 0) {
            Bitmap bitmap = null;
            YuvImage yuvImage = new YuvImage(data, ImageFormat.NV21, width, height, (int[])null);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            yuvImage.compressToJpeg(new Rect(0, 0, width, height), 100, out);
            bitmap = BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.size());
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        } else {
            return null;
        }
    }

    @Nullable
    public static Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return rotatedBitmap;
    }

    @Nullable
    public static Bitmap flipBitmap(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);
        Bitmap flippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return flippedBitmap;
    }


}
