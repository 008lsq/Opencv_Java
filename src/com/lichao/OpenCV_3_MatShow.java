package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_3_MatShow</p>
 * <p>Description: Matͼ����ʾ������</p>
 * <p>Company:</p>
 * @author Administrator
 * @date 2018��1��13�� ����9:53:26
 */
public class OpenCV_3_MatShow {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\lena.png", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_3_MatShow", src);
	}
}
