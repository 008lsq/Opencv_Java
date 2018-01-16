package com.lichao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_27_ContoursMeasure</p>
 * <p>Description:�������</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��16�� ����8:07:40
 */
public class OpenCV_27_ContoursMeasure {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\contours.png");//����ͼ��
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_27_Input", src);
		
		MeasureObject(src);
	}
	
	/**
	 * �������
	 * @param src
	 */
	public static void MeasureObject(Mat src) {
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		
		//˫��ģ��
		Mat dst = new Mat();
		Imgproc.bilateralFilter(gray, dst, 0, 50, 5);
		//��Ե��ȡ
		Mat edges = new Mat();
		Imgproc.Canny(dst, edges, 30, 200);
		
		ImageUI ui_binary = new ImageUI();
		ui_binary.imshow("OpenCV_Binary", edges);
		
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
		
		//��������Ϊʮ������ʾ��ʽ����������С������λ
		NumberFormat nf = new DecimalFormat("###.##");
		Mat result = src.clone();
		for (int i = 0; i < contours.size(); i++) {
			//�����ܳ�
			double arclength = Imgproc.arcLength(new MatOfPoint2f(contours.get(i).toArray()), true);
			//�������
			double area = Imgproc.contourArea(contours.get(i));
			//���㼸�ξ�
			Moments mm = Imgproc.moments(contours.get(i));
			int cx = (int)(mm.m10 / mm.m00);
			int cy = (int)(mm.m01 / mm.m00);
			//�������ĵ��λ�ã����ģ�, -1Ϊʵ��
			Imgproc.circle(result, new Point(cx, cy), 2, new Scalar(0, 255, 255), -1);
			
			//���Ļ�����
			String text = "Arc:" + nf.format(arclength) + ",Area:" + nf.format(area);
			//�������
			Imgproc.drawContours(result, contours, i, new Scalar(0, 0, 255), 2);
			//������ֵ�ͼ����
			int[] pt = new int[2];
			contours.get(i).get(0, 0, pt);
			Imgproc.putText(result, text, new Point(pt[0], pt[1]), Core.FONT_HERSHEY_PLAIN, 1, new Scalar(255, 0, 0));
			
			//��������---���ݱ�Ѱ��
			MatOfPoint2f approxCurve = new MatOfPoint2f();
			//�õ��պ϶����
			Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), approxCurve, 4, true);
			System.out.println(approxCurve.rows());
			if (approxCurve.rows() == 6) {//�õ��ı���
				Imgproc.drawContours(result, contours, i, new Scalar(0, 255, 0), 4);
			}
			
		}
		
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_MeasureObject", result);
	}
}
