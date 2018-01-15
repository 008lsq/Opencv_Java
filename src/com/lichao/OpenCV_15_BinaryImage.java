package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_15_BinaryImage</p>
 * <p>Description: ͼ���ֵ��</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��15�� ����8:00:31
 */
public class OpenCV_15_BinaryImage {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test1.png");//����ͼ��
		
		if (src.empty()) {
			return;
		}
		
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_15_Input", gray);
		
		//���÷���һ�ֶ���ֵ:ʹ����ֵ127�����ⷽ��������ѧ
		//�Զ���ʹ��ȫ����ֵ����Imgproc.THRESH_OTSUʱ��127���ֵ��Ч������ȫ����ֵ����ȥ����
		//�Զ���������ֵ��Imgproc.THRESH_TRIANGLE��������ϸ������
		Mat dst = new Mat();
		Imgproc.threshold(gray, dst, 127, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Binary_Output", dst);
	}
}
