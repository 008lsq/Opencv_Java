package com.util;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * <p>Title: HistogramUtil</p>
 * <p>Description: ����ֱ��ͼ������</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��15�� ����1:50:22
 */
public class HistogramUtil {

	/**
	 * ���Ʋ�ɫͼ��Ҷ�ֱ��ͼ---����
	 * @param src  ԭʼͼƬ
	 */
	public static void showGrayHistogramRectangle(Mat src) {
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_GrayHistogram_Input", gray);
		
		List<Mat> images = new ArrayList<>();
		images.add(gray);
		Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
		Mat hist = new Mat();
		//ͳ��ֱ��ͼ
		Imgproc.calcHist(images, new MatOfInt(0), mask, hist, new MatOfInt(256), new MatOfFloat(0, 255));
		//��һ��
		Core.normalize(hist, hist, 0, 255, Core.NORM_MINMAX);
		//��ʾֱ��ͼ
		int height = hist.rows();
		Mat plot = Mat.zeros(400, 600, src.type());
		float[] hisdata = new float[256];
		hist.get(0, 0, hisdata);
		int offsetX = 50;
		int offsetY = 350;
		Imgproc.line(plot, new Point(offsetX, 0), new Point(offsetX, offsetY), new Scalar(255, 255, 255));//Y
		Imgproc.line(plot, new Point(offsetX, offsetY), new Point(600, offsetY), new Scalar(255, 255, 255));//X
		for (int i = 0; i < height - 1; i++) {
			int y1 = (int)hisdata[i];
			//���ƾ���
			Rect rect = new Rect();
			rect.x = offsetX + i;
			rect.y = offsetY - y1;
			rect.width = 1;
			rect.height = y1;
			Imgproc.rectangle(plot, rect.tl(), rect.br(), new Scalar(0, 0, 255));
		}
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_GrayHistogramRectangle_OutPut", plot);
	} 
	
	/**
	 * ���Ʋ�ɫͼ��Ҷ�ֱ��ͼ---���
	 * @param src  ԭʼͼƬ
	 */
	public static void showGrayHistogramLine(Mat src) {
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_GrayHistogram_Input", gray);
		
		List<Mat> images = new ArrayList<>();
		images.add(gray);
		Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
		Mat hist = new Mat();
		//ͳ��ֱ��ͼ
		Imgproc.calcHist(images, new MatOfInt(0), mask, hist, new MatOfInt(256), new MatOfFloat(0, 255));
		//��һ��
		Core.normalize(hist, hist, 0, 255, Core.NORM_MINMAX);
		//��ʾֱ��ͼ
		int height = hist.rows();
		Mat plot = Mat.zeros(400, 600, src.type());
		float[] hisdata = new float[256];
		hist.get(0, 0, hisdata);
		int offsetX = 50;
		int offsetY = 350;
		Imgproc.line(plot, new Point(offsetX, 0), new Point(offsetX, offsetY), new Scalar(255, 255, 255));//Y
		Imgproc.line(plot, new Point(offsetX, offsetY), new Point(600, offsetY), new Scalar(255, 255, 255));//X
		for (int i = 0; i < height - 1; i++) {
			int y1 = (int)hisdata[i];
			int y2 = (int)hisdata[i + 1];
			//�������
			Imgproc.line(plot, new Point(offsetX+i, offsetY-y1), new Point(offsetX+i+1, offsetY-y2), new Scalar(0, 0, 255));
		}
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_GrayHistogramLine_OutPut", plot);
	} 
	
	/**
	 * ���Ʋ�ɫͼ���ɫֱ��ͼ---RGB�����ֱ𻭳���
	 * @param src
	 */
	public static void showColorHistogram(Mat src) {
		int offsetX = 50;
		int offsetY = 350;
		Scalar[] colorTab = new Scalar[]{new Scalar(255, 0, 0), new Scalar(0, 255, 0), new Scalar(0, 0, 255)};//������ɫ
		String[] colorArr = new String[]{"Blue", "Green", "Red"};
		int index = 0;
		List<Mat> mv = new ArrayList<>();
		//������ͨ�������ݷֱ�ŵ����Ե�Mat��
		Core.split(src, mv);
		for (Mat color : mv) {
			List<Mat> images = new ArrayList<>();
			images.add(color);
			Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
			Mat hist = new Mat();
			//ͳ��ֱ��ͼ
			Imgproc.calcHist(images, new MatOfInt(0), mask, hist, new MatOfInt(256), new MatOfFloat(0, 255));
			//��һ��
			Core.normalize(hist, hist, 0, 255, Core.NORM_MINMAX);
			//��ʾֱ��ͼ
			Mat plot = Mat.zeros(400, 600, src.type());
			int height = hist.rows();
			float[] hisdata = new float[256];
			hist.get(0, 0, hisdata);
			Imgproc.line(plot, new Point(offsetX, 0), new Point(offsetX, offsetY), new Scalar(255, 255, 255));//Y
			Imgproc.line(plot, new Point(offsetX, offsetY), new Point(600, offsetY), new Scalar(255, 255, 255));//X
			for (int i = 0; i < height - 1; i++) {
				int y1 = (int)hisdata[i];
				//���ƾ���
				Rect rect = new Rect();
				//�������� *2
				rect.x = offsetX + i * 2;
				rect.y = offsetY - y1;
				rect.width = 1;
				rect.height = y1;
				Imgproc.rectangle(plot, rect.tl(), rect.br(), colorTab[index]);
			}
			ImageUI out = new ImageUI();
			out.imshow("OpenCV_" + colorArr[index] + "_OutPut", plot);
			index++;
		}
	}
	
	/**
	 * ���Ʋ�ɫͼ���ɫֱ��ͼ---RGB����һ������
	 * @param src
	 */
	public static void showColorHistogramInOne(Mat src) {
		Mat plot = Mat.zeros(400, 600, src.type());
		int offsetX = 50;
		int offsetY = 350;
		Imgproc.line(plot, new Point(offsetX, 0), new Point(offsetX, offsetY), new Scalar(255, 255, 255));//Y
		Imgproc.line(plot, new Point(offsetX, offsetY), new Point(600, offsetY), new Scalar(255, 255, 255));//X
		Scalar[] colorTab = new Scalar[]{new Scalar(255, 0, 0), new Scalar(0, 255, 0), new Scalar(0, 0, 255)};//������ɫ
		int index = 0;
		List<Mat> mv = new ArrayList<>();
		//������ͨ�������ݷֱ�ŵ����Ե�Mat��
		Core.split(src, mv);
		for (Mat color : mv) {
			List<Mat> images = new ArrayList<>();
			images.add(color);
			Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
			Mat hist = new Mat();
			//ͳ��ֱ��ͼ
			Imgproc.calcHist(images, new MatOfInt(0), mask, hist, new MatOfInt(256), new MatOfFloat(0, 255));
			//��һ��
			Core.normalize(hist, hist, 0, 255, Core.NORM_MINMAX);
			//��ʾֱ��ͼ
			int height = hist.rows();
			float[] hisdata = new float[256];
			hist.get(0, 0, hisdata);
			for (int i = 0; i < height - 1; i++) {
				int y1 = (int)hisdata[i];
				int y2 = (int)hisdata[i + 1];
				//��������*2
				int x1 = i * 2;
				int x2 = (i + 1) * 2;
				//�������
				Imgproc.line(plot, new Point(offsetX+x1, offsetY-y1), new Point(offsetX+x2, offsetY-y2), colorTab[index]);
			}
			index++;
		}
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_GrayHistogramLine_OutPut", plot);
	}
	
	
}
