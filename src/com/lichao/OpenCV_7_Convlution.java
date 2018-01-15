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
 * <p>Title: OpenCV_7_Convlution</p>
 * <p>Description:��ֵģ��</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��15�� ����9:20:31
 */
public class OpenCV_7_Convlution {
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\lenanoise.png");
		if (src.empty()) {
			return;
		}
		
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_7_Input", src);
		
		//��ֵģ��
		/*Mat dst = new Mat();
		//���5 * 5,����һ��������������Щƽ̨���ܴ���ż��
		Imgproc.blur(src, dst, new Size(5, 5));*/
		
		//��ֵģ��������ȥ��
		/*Mat dst = new Mat();
		Imgproc.medianBlur(src, dst, 5);
		
		//�Զ���ģ��---��
		Mat result = new Mat();
		//������
		Mat k = new Mat(3, 3, CvType.CV_32FC1);
		float[] data = new float[]{0, -1, 0, -1, 5, -1, 0, -1, 0};
		k.put(0, 0, data);
		Imgproc.filter2D(dst, result, CvType.CV_8U, k);
		
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_medianBlur_Output", dst);
		
		ImageUI outfilter = new ImageUI();
		outfilter.imshow("OpenCV_filter2D_Output", result);*/
		
		Mat result = new Mat();
		//ģ��
//		Mat k = new Mat(3, 3, CvType.CV_32FC1);
//		float[] data = new float[]{1.0f/9, 1.0f/9, 1.0f/9, 1.0f/9, 1.0f/9, 1.0f/9, 1.0f/9, 1.0f/9, 1.0f/9};
		//��Ե��ȡ
		Mat k = new Mat(2, 2, CvType.CV_32FC1);
		float[] data = new float[]{0, 1, -1, 0};
		k.put(0, 0, data);
		Imgproc.filter2D(src, result, CvType.CV_8U, k);
		
		ImageUI outfilter = new ImageUI();
		outfilter.imshow("OpenCV_filter2D_Output", result);
	}
}
