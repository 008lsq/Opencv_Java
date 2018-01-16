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
 * <p>Title: OpenCV_28_DilateErode</p>
 * <p>Description:ͼ����̬ѧ---��ʴ������</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��16�� ����9:43:35
 */
public class OpenCV_28_DilateErode {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\morph.png");//����ͼ�����ͺ͸�ʴҲ�����ڲ�ɫͼ��
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_28_Input", src);
		
		Mat dst = new Mat();
		//��ȡ�ṹԪ��,���;���Imgproc.MORPH_RECT  
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
		//����
		//Imgproc.dilate(src, dst, kernel);
		//���Ͷ��
		//Imgproc.dilate(src, dst, kernel, new Point(0, 0), 3);
		
		//��ʴ
		//Imgproc.erode(src, dst, kernel);
		//��ʴ���
		Imgproc.erode(src, dst, kernel, new Point(0, 0), 3);
		
		ImageUI ui_dilate = new ImageUI();
		ui_dilate.imshow("OpenCV_Dilate_Output", dst);
		
	}
}
