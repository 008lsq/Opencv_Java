package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_30_MorePH</p>
 * <p>Description:������̬ѧ��������ñ����ñ�������ݶȡ��ڲ��ݶȡ��ⲿ�ݶ�</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��16�� ����11:13:47
 */
public class OpenCV_30_MorePH {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test.png");//����ͼ����̬ѧ���ݶ�Ҳ�����ڲ�ɫͼ��
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_30_Input", src);
		
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		
		Mat binary = new Mat();
		//�Զ���ֵ��ֵ��
		Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
		
		//��ñ��������������ԭͼ��Ĳ�ֵ
//		Mat dst = new Mat();
//		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15, 15));
//		Imgproc.morphologyEx(binary, dst, Imgproc.MORPH_TOPHAT, kernel);
		
		//��ñ�������ղ�����ԭͼ��Ĳ�ֵ
//		Mat dst = new Mat();
//		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15, 15));
//		Imgproc.morphologyEx(binary, dst, Imgproc.MORPH_BLACKHAT, kernel);
		
//		ImageUI out = new ImageUI();
//		out.imshow("OpenCV_Out_Output", dst);
		
		//�����ݶȣ����ͺ��ͼ���ȥ��ʴ���ͼ��õ���ֵͼ��(�����ò�ɫͼ��)
//		Mat dst = new Mat();
//		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
//		Imgproc.morphologyEx(src, dst, Imgproc.MORPH_GRADIENT, kernel);
		
		//�ڲ��ݶȣ�ԭͼ���ȥ��ʴ֮���ͼ��õ���ֵͼ��
//		Mat inter = new Mat();
//		Mat dst = new Mat();
//		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
//		Imgproc.erode(src, dst, kernel);
//		Core.subtract(src, dst, inter);
		
		//�ⲿ�ݶȣ�ͼ������֮���ټ�ȥԭ����ͼ��õ��Ĳ�ֵͼ��
		Mat exter = new Mat();
		Mat dst = new Mat();
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
		Imgproc.dilate(src, dst, kernel);
		Core.subtract(dst, src, exter);
		
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_Out_Output", exter);
	}
}
