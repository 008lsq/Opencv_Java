package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * ���������
 * @author LiChao
 *
 */
public class MainTest {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);//����jar��
		Mat src = new Mat();
		src.create(300, 300, CvType.CV_8UC3);
		src.setTo(new Scalar(0, 0, 255));//red
		Imgcodecs.imwrite(".\\img\\test.jpg", src);
	}
}
