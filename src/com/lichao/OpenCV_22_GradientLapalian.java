package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_22_GradientLapalian</p>
 * <p>Description: ͼ���ݶȶ��׵���(������˹����)</p>
 * <p>Company:</p>
 * @author Administrator
 * @date 2018��1��16�� ����3:00:31
 */
public class OpenCV_22_GradientLapalian {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test.png");//����ͼ��
		
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_22_Input", src);
		
		//���ӣ��и���ѡ��CV_32SC1����
		Mat kernel = new Mat(3, 3, CvType.CV_32SC1);
		int[] data = new int[]{0, 1, 0, 1, -4, 1, 0, 1, 0};
		
		
	}
}
