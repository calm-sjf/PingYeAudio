package com;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Luke on 2016/5/27.
 */
public class FileUtil {

	public static boolean writeFile2End(String path, String filename,
			String text) {
		String sdPath = getSDPath();
		if (sdPath == null)
			return false;
		text += "\r\n";
		FileOutputStream fos = null;
		try {
			path = sdPath + '/' + path;
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			fos = new FileOutputStream(path + "/" + filename, true);
			byte[] bytes = text.getBytes();
			fos.write(bytes);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (IOException e2) {

				}

			}

		}
		return true;
	}

	public static String getSDPath() {
		String sdPath = null;
		// 判断sd卡是否存在
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			sdPath = Environment.getExternalStorageDirectory().getPath();
		}
		return sdPath;
	}

	public static Bitmap getBimmapFromSD(String path) {
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			return BitmapFactory.decodeStream(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] getBytesFromFile(String path) {
		byte[] bytes = null;
		InputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileInputStream(path);
			bytes = new byte[fileOutputStream.available()];
			fileOutputStream.read(bytes);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
					fileOutputStream = null;
				} catch (IOException e) {

				}

			}
		}
		return bytes;
	}

}
