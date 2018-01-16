package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_29_OpenClose</p>
 * <p>Description: ͼ����̬ѧ---���ղ���</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��16�� ����10:32:10
 */
public class OpenCV_29_OpenClose {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\morph02.png");//����ͼ��
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_29_Input", src);
		
		//demoOne(src);
		//detectLine(src);
		removeLines(src);
	}
	
	/**
	 * ȥ����
	 * @param src
	 */
	public static void removeLines(Mat src) {
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		
		Mat binary = new Mat();
		//�Զ���ֵ��ֵ��
		Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
		
		Mat dst = new Mat();
		//�ṹԪ�أ�����Size()����
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
		//������:�ȸ�ʴ������
		Imgproc.morphologyEx(binary, dst, Imgproc.MORPH_OPEN, kernel);
		
		ImageUI ui_dilate = new ImageUI();
		ui_dilate.imshow("OpenCV_Open_DetectLine", dst);
	}
	
	/**
	 * �߶���ȡ
	 * @param src
	 */
	public static void detectLine(Mat src) {
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		
		Mat binary = new Mat();
		//�Զ���ֵ��ֵ��
		Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
		
		Mat dst = new Mat();
		//����X�����ߣ�����Size()����
		//Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(40, 1));
		//����Y�����ߣ�����Size()����
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(1, 30));
		//������:�ȸ�ʴ������
		Imgproc.morphologyEx(binary, dst, Imgproc.MORPH_OPEN, kernel);
		
		ImageUI ui_dilate = new ImageUI();
		ui_dilate.imshow("OpenCV_Open_DetectLine", dst);
	}
	
	/**
	 * ȥ����ͼ��
	 * @param src
	 */
	public static void demoOne(Mat src) {
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		
		Mat binary = new Mat();
		//�Զ���ֵ��ֵ��
		Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
		
		Mat dst = new Mat();
		//�ṹԪ��
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
		//������:�ȸ�ʴ������
		//Imgproc.morphologyEx(binary, dst, Imgproc.MORPH_OPEN, kernel);
		//��ο�����
		Imgproc.morphologyEx(binary, dst, Imgproc.MORPH_OPEN, kernel, new Point(0, 0), 5);
		
		//�ղ���:�������ٸ�ʴ
		Mat close = new Mat();
		Imgproc.morphologyEx(binary, close, Imgproc.MORPH_CLOSE, kernel, new Point(0, 0), 2);
		
		ImageUI ui_dilate = new ImageUI();
		ui_dilate.imshow("OpenCV_Open_Close", close);
	}
}
