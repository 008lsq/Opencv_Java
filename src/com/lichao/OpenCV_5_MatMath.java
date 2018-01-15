package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_5_MatMath</p>
 * <p>Description: MAT��������������߼�</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��14�� ����8:21:57
 */
public class OpenCV_5_MatMath {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test.png");
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_5_Input", src);
		
		//����һ��ͼ��
		Mat blackImg = Mat.zeros(src.size(), src.type());
		
		//���ȵ��ڣ����--add  ���--subtract
		/*Mat dst = new Mat();
		blackImg.setTo(new Scalar(30, 30, 30));
		//Core.add(src, blackImg, dst);
		Core.subtract(src, blackImg, dst);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Add_Output", dst);*/
		
		//�Աȶȣ����--multiply  ���--divide
		/*Mat dst = new Mat();
		blackImg.setTo(new Scalar(2, 2, 2));
		//Core.multiply(src, blackImg, dst);
		Core.divide(src, blackImg, dst);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_multiply_Output", dst);*/
		
		//Ȩ�ؼӼ�����������ʵ�ֲ�ͬЧ��
		/*Mat dst = new Mat();
		blackImg.setTo(new Scalar(2, 2, 2));
		Core.addWeighted(src, 0.5, blackImg, 0.5, 0.0, dst, src.type());
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Weight_Output", dst);*/
		
		/*Mat mask = Mat.zeros(src.size(), src.type());
		Rect rect = new Rect(120, 80, 200, 200);
		mask.submat(rect).setTo(new Scalar(255, 255, 255));*/
		
		//�������bitwise_and
		/*Mat dst = new Mat();
		Core.bitwise_and(src, mask, dst);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_bitwise_Output", dst);*/
		
		//�������bitwise_or
		/*Mat dst = new Mat();
		Core.bitwise_or(src, mask, dst);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_bitwise_Output", dst);
		
		//�������bitwise_or
		Mat dst = new Mat();
		Core.bitwise_not(src, dst);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_bitwise_Output", dst);*/
		
		Mat mask = Mat.zeros(src.size(), CvType.CV_8UC1);
		Rect rect = new Rect(120, 80, 200, 200);
		mask.submat(rect).setTo(new Scalar(255, 255, 255));
		
		/*Mat dst = new Mat();
		Core.bitwise_not(src, dst, mask);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_bitwise_Output", dst);*/
		
		Mat red = new Mat(src.size(), src.type());
		red.setTo(new Scalar(0, 0, 255));
		Mat dst = new Mat();
		Core.bitwise_and(src, red, dst, mask);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_bitwise_Output", dst);
	}
}
