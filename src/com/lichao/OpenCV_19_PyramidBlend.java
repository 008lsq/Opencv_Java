package com.lichao;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_19_PyramidBlend</p>
 * <p>Description: ͼ��������ں�</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��16�� ����11:37:24
 */
public class OpenCV_19_PyramidBlend {
	public static int level = 4;
	public static Mat smallestLevel;

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat ma = Imgcodecs.imread(".\\img\\apple.png");
		Mat mb = Imgcodecs.imread(".\\img\\orange.png");
		Mat mc = Imgcodecs.imread(".\\img\\mask.png");
		
		if (ma.empty() || mb.empty()) {
			return;
		}
		ImageUI uiApple = new ImageUI();
		uiApple.imshow("OpenCV_Apple_Input", ma);
		
		ImageUI uiOrange = new ImageUI();
		uiOrange.imshow("OpenCV_Orange_Input", mb);
		
		//����ƻ��������˹������
		List<Mat> la = buildLapacianPyramid(ma);
		Mat leftsmallestLevel = new Mat();
		smallestLevel.copyTo(leftsmallestLevel);
		
		//��������������˹������
		List<Mat> lb = buildLapacianPyramid(mb);
		Mat rightsmallestLevel = new Mat();
		smallestLevel.copyTo(rightsmallestLevel);
		
		Mat mask = new Mat();
		Imgproc.cvtColor(mc, mask, Imgproc.COLOR_BGR2GRAY);
		//Imgcodecs.imwrite(".\\screenshot\\mask_result.png", mask);
		//��mask������˹������
		List<Mat> maskPyramid = buildGoussianPyromid(mask);
		Mat samllmask = new Mat();
		smallestLevel.copyTo(samllmask);
		
		//�ں�����һ�ź�С��ͼƬ
		Mat currentImage = blend(leftsmallestLevel, rightsmallestLevel, samllmask);
		ImageUI samll = new ImageUI();
		samll.imshow("��С���������ͼ��", currentImage);
		
		//�ؽ�������˹�����������ص�ԭͼ
		List<Mat> ls = new ArrayList<Mat>();
		for (int i = 0; i < level; i++) {
			Mat a = la.get(i);
			Mat b = lb.get(i);
			Mat m = maskPyramid.get(i);
			ls.add(blend(a, b, m));
		}
		
		//�ؽ�ԭͼ
		Mat temp = new Mat();
		for (int i = level-1; i >=0; i--) {
			Imgproc.pyrUp(currentImage, temp, ls.get(i).size());
			Core.add(temp, ls.get(i), currentImage);
		}
		
		ImageUI blend = new ImageUI();
		blend.imshow("��˹������ͼ���ں�", currentImage);
	}

	/**
	 * ����ͼͼƬ�����ں�
	 * @param a
	 * @param b
	 * @param m
	 * @return
	 */
	private static Mat blend(Mat a, Mat b, Mat m) {
		int width = a.cols();
		int height = a.rows();
		Mat dst = Mat.zeros(a.size(), a.type());
		byte[] rgb1 = new byte[3];
		byte[] rgb2 = new byte[3];
		byte[] gray = new byte[1];
		int r1 = 0, g1 = 0, b1 =0;
		int r2 = 0, g2 = 0, b2 =0;
		int red = 0, green = 0, blue = 0;
		int w = 0;
		float w1 = 0, w2 = 0;//Ȩ��
		for (int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				a.get(row, col, rgb1);
				b.get(row, col, rgb2);
				m.get(row, col, gray);
				w = gray[0]&0xff;
				w2 = w / 255.0f;
				w1 = 1.0f - w2;
				//ƻ��
				b1 = rgb1[0]&0xff;
				g1 = rgb1[1]&0xff;
				r1 = rgb1[2]&0xff;
				//����
				b2 = rgb2[0]&0xff;
				g2 = rgb2[1]&0xff;
				r2 = rgb2[2]&0xff;
				
				red = (int)(r1 * w1 + r2 * w2);
				green = (int)(g1 * w1 + g2 * w2);
				blue = (int)(b1 * w1 + b2 * w2);
				//���֮��Ľ������С��
				dst.put(row, col, new byte[]{(byte)blue, (byte)green, (byte)red});
			}
		}
		return dst;
	}

	/**
	 * ��˹������
	 * @param mask
	 * @return
	 */
	private static List<Mat> buildGoussianPyromid(Mat image) {
		List<Mat> pyromid = new ArrayList<Mat>();
		Mat copy = image.clone();
		pyromid.add(image.clone());
		Mat dst = new Mat();
		for (int i = 0; i < level; i++) {
			//Size()�������Բ��ӣ�������Ϊ��֤����ͼƬ��Сһ��
			Imgproc.pyrDown(copy, dst, new Size(copy.cols()/2, copy.rows()/2));
			dst.copyTo(copy);
			pyromid.add(dst.clone());
		}
		smallestLevel = dst;
		return pyromid;
	}

	/**
	 * ������˹������
	 * @param ma
	 * @return
	 */
	private static List<Mat> buildLapacianPyramid(Mat image) {
		List<Mat> lp = new ArrayList<Mat>();
		Mat temp = new Mat();
		int level = 4;
		Mat copy = image.clone();
		Mat dst = new Mat();
		for (int i = 0; i < level; i++) {
			//Size()�������Բ��ӣ�������Ϊ��֤����ͼƬ��Сһ��
			Imgproc.pyrDown(copy, dst, new Size(copy.cols()/2, copy.rows()/2));
			Imgproc.pyrUp(dst, temp, copy.size());
			Mat lapaian = new Mat();
			Core.subtract(copy, temp, lapaian);
			lp.add(lapaian);
			copy = dst.clone();
		}
		smallestLevel = dst;
		return lp;
	}
}
