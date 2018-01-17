package com.lichao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_31_WaterShed</p>
 * <p>Description:��ˮ���㷨</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��17�� ����8:29:55
 */
public class OpenCV_31_WaterShed {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//1������ͼ��
		Mat src = Imgcodecs.imread(".\\img\\cards.png");
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("ԭʼͼ��", src);
		
		//ȥ������ɫ---��ɫ������ɫ�Ͳ���ȥ����
		int width = src.cols();
		int heght = src.rows();
		int dims = src.channels();
		byte[] data = new byte[width * heght * dims];
		src.get(0, 0, data);
		int index = 0;
		int r = 0, g = 0, b = 0;
		for (int row = 0; row < heght; row++) {
			for (int col = 0; col < width; col++) {
				index = row * width * dims + col * dims;
				b = data[index]&0xff;
				g = data[index + 1]&0xff;
				r = data[index + 2]&0xff;
				//�Ѱ�ɫ������Ϊ��ɫ
				if (b == 255 && g ==255 && r == 255) {
					data[index] = (byte)0;
					data[index + 1] = (byte)0;
					data[index + 2] = (byte)0;
				}
			}
		}
		src.put(0, 0, data);
		ImageUI background = new ImageUI();
		background.imshow("ȥ����", src);
		
		//2���Ҷ�
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		ImageUI ui_gray = new ImageUI();
		ui_gray.imshow("�Ҷ�ͼ��", gray);
		
		//3����ֵͼ��
		Mat binary = new Mat();
		//�Զ���ֵ��ֵ��
		Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
		ImageUI ui_binary = new ImageUI();
		ui_binary.imshow("��ֵ��ͼ��", binary);
		
		//4������任
		Mat dist = new Mat();
		Imgproc.distanceTransform(binary, dist, Imgproc.DIST_L2, 3);
		Core.normalize(dist, dist, 0, 255, Core.NORM_MINMAX);
		Mat dist_8u = new Mat();
		//ת��Ϊ��ɫͼ��
		dist.convertTo(dist_8u, CvType.CV_8U);
		ImageUI ui_dist = new ImageUI();
		ui_dist.imshow("����任���ͼ��", dist_8u);
		
		//5��Ѱ������
		//��ֵ��,ȥ������߽Ǻ�����(0.4~1.0)*255��Χ���ж�ֵ��
		Imgproc.threshold(dist, dist, 102, 255, Imgproc.THRESH_BINARY);
		//��̬ѧ����
		Mat erode = new Mat();
		//��ø�ʴ���ӣ���ͬ������ͼ��Ҫ����Size()������ֵ
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(13, 13));
		//��ʴ
		Imgproc.erode(dist, erode, kernel);
		Mat erode_8u = new Mat();
        erode.convertTo(erode_8u, CvType.CV_8U);
        ImageUI ui_erode = new ImageUI();
        ui_erode.imshow("��ʴ���ͼ��", erode_8u);
		
		//6������Marker
        //��������
  		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
  		Mat hierarchy = new Mat();
  		Imgproc.findContours(erode_8u, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
  		Mat markers = Mat.zeros(dist.size(), CvType.CV_32SC1);
  		//�����ɫ����
  		Scalar[] colors = new Scalar[contours.size()];
  		Random random = new Random();
  		for (int i = 0; i < contours.size(); i++) {
  			//��Marker������
  			Imgproc.drawContours(markers, contours, i, new Scalar(i+1), -1);
  			colors[i] = new Scalar(random.nextInt(255), random.nextInt(255), random.nextInt(255));
  		}
  		Imgproc.circle(markers, new Point(5, 5), 3, new Scalar(255), -1);
        
		//7����ˮ��任
		Imgproc.watershed(src, markers);
		//��ʾ���
		Mat mark = Mat.zeros(markers.size(), CvType.CV_8UC1);
		markers.convertTo(mark, CvType.CV_8U);
		Core.bitwise_not(mark, mark);
		ImageUI out = new ImageUI();
		out.imshow("��ˮ��任", mark);
		
		//��ɫ
		Mat dst = new Mat(src.size(), CvType.CV_8UC3);
		int[] idxv = new int[1];
		for (int row = 0; row < markers.rows(); row++) {
			for (int col = 0; col < markers.cols(); col++) {
				markers.get(row, col, idxv);
				if (idxv[0] > 0 && idxv[0] <= contours.size()) {
					double[] rgb = colors[idxv[0] - 1].val;
					dst.put(row, col, new byte[]{(byte)rgb[0], (byte)rgb[1], (byte)rgb[2]});
				} else {
					dst.put(row, col, new byte[]{(byte)0, (byte)0, (byte)0});
				}
			}
		}
		ImageUI fout = new ImageUI();
		fout.imshow("�ָ��Ե��ͼ��", dst);
	}
}
