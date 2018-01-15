package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_9_EPF</p>
 * <p>Description: ��Ե�����˲�EPF</p>
 * <p>Company:</p>
 * @author Administrator
 * @date 2018��1��15�� ����11:30:50
 */
public class OpenCV_9_EPF {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\example.png");
		if (src.empty()) {
			return;
		}
		
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_9_Input", src);
		
		//˫��ģ��
		/*Mat dst = new Mat();
		//˫���˲�
		Imgproc.bilateralFilter(src, dst, 0, 100, 15);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_bilateralFilter_OutPut", dst);*/
		
		//��ֵǨ��
		Mat dst = new Mat();
		Imgproc.pyrMeanShiftFiltering(src, dst, 10, 50);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_pyrMeanShift_OutPut", dst);
	}
}
