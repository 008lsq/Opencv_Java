package com.lichao;

import java.util.Arrays;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_13_HistogramHistBack</p>
 * <p>Description: ֱ��ͼ����ͶӰ</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��15�� ����5:10:48
 */
public class OpenCV_13_HistogramHistBack {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\target.png");//����ͼ��
		Mat sample = Imgcodecs.imread(".\\img\\sample.png");//����Ȥ��ͼ��
		
		if (src.empty() || sample.empty()) {
			return;
		}
		
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_13_Input", src);
		
		ImageUI sui = new ImageUI();
		sui.imshow("OpenCV_sample_Input", sample);
		
		Mat hsv = new Mat();
		Imgproc.cvtColor(sample, hsv, Imgproc.COLOR_BGR2HSV);
		Mat mask = Mat.ones(sample.size(), CvType.CV_8UC1);
		Mat mHist = new Mat();
		//�ó���άֱ��ͼmHist
		Imgproc.calcHist(Arrays.asList(hsv), new MatOfInt(0, 1), mask, mHist, new MatOfInt(30, 32), new MatOfFloat(0, 179, 0, 255));
		
		Mat srcHSV = new Mat();
		Imgproc.cvtColor(src, srcHSV, Imgproc.COLOR_BGR2HSV);
		//ֱ��ͼ����ͶӰ
		Mat dst = new Mat();
		Imgproc.calcBackProject(Arrays.asList(srcHSV), new MatOfInt(0, 1), mHist, dst, new MatOfFloat(0, 179, 0, 255), 1);
		Core.normalize(mHist, mHist, 0, 255, Core.NORM_MINMAX);
		
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_calcBackProject_Input", dst);
	}
}
