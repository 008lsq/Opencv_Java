package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_23_Canny</p>
 * <p>Description:Canny��Ե��ȡ</p>
 * <p>Company:</p>
 * @author Administrator
 * @date 2018��1��16�� ����3:58:14
 */
public class OpenCV_23_Canny {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\lena.png");//����ͼ��
		
		if (src.empty()) {
			return;
		}
		
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_23_Input", src);
		
		//ʹ��OpenCV Canny API�÷�һ
		/*//1.��˹ģ��
		Mat dst = new Mat();
		Imgproc.GaussianBlur(src, dst, new Size(3, 3), 0);
		//2.�Ҷȴ���
		Mat gray = new Mat();
		Imgproc.cvtColor(dst, gray, Imgproc.COLOR_BGR2GRAY);
		//3.��Ե��ȡ
		Mat output = new Mat();
		Imgproc.Canny(gray, output, 50, 150, 3, true);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Canny_Output", output);*/
		
		//ʹ��OpenCV Canny API�÷���
		Mat dst = new Mat();
		Imgproc.GaussianBlur(src, dst, new Size(3, 3), 0);
		
		Mat gray = new Mat();
		Imgproc.cvtColor(dst, gray, Imgproc.COLOR_BGR2GRAY);
		
		Mat xgrad = new Mat();
		Imgproc.Sobel(gray, xgrad, CvType.CV_16S, 1, 0);
		
		Mat ygrad = new Mat();
		Imgproc.Sobel(gray, ygrad, CvType.CV_16S, 0, 1);
		//��Ե��ȡ
		Mat output = new Mat();
		Imgproc.Canny(xgrad, ygrad, output, 50, 150);
		
		//����ȡ��Ե������ɫ
		Mat edges = new Mat();
		src.copyTo(edges, output);
		
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Canny_Output", edges);
		
		//ʹ��OpenCV Canny API�÷���
		/*Mat dst = new Mat();
		Imgproc.GaussianBlur(src, dst, new Size(3, 3), 0);
		
		Mat output = new Mat();
		Imgproc.Canny(dst, output, 50, 150);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Canny_Output", output);*/
	}
}
