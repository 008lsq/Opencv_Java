package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class OpenCV_2_Mat {
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//��һ�ִ�������  create
		/*Mat src = new Mat();
		src.create(300, 300, CvType.CV_8UC3);
		src.setTo(new Scalar(0, 0, 255));
		Imgcodecs.imwrite(".\\screenshot\\mat.jpg", src);*/
		
		//�ڶ��ִ������� ֱ�ӳ�ʼ��
		//zeros:0��ɫ   ones:1��ɫ    CV_8UC1:��ͨ��     CV_8UC3:��ͨ��
		/*Mat src = Mat.zeros(300, 300, CvType.CV_8UC1);
		Imgcodecs.imwrite(".\\screenshot\\mat.jpg", src);*/
		
		//�������С��ȡ
		Mat src = Imgcodecs.imread(".\\img\\lena.png", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		int type = src.type();
		int width = src.cols();
		int heigth = src.rows();
		int channels = src.channels();
		int depth = src.depth();
		System.out.println("type:" + type);
		System.out.println("width:" + width);
		System.out.println("heigth:" + heigth);
		System.out.println("channels:" + channels);
		System.out.println("depth:" + depth);
		if (CvType.CV_8UC3 == type) {
			System.out.println("type is CV_8UC3");
		} else if (CvType.CV_8UC1 == type) {
			System.out.println("type is CV_8UC1");
		} else {
			System.out.println("UnKnow...");
		}
		
		//Mat����ת��  �任�ɸ�����ͼ��
		Mat dst = new Mat(src.size(), CvType.CV_32FC1);
		src.convertTo(dst, CvType.CV_32F);
		
		//��ȡ���޸�ÿ������ֵ: 1��һ��һ��һ������ȡ  2��һ��ȫ��ȡ����
		/*byte[] onepixel = new byte[channels];//һ��һ��һ������ȡ,�ܺ�ʱ
		int r = 0, g = 0, b = 0;
		int gray = 0;
		for (int row = 0; row < heigth; row++) {
			for (int col = 0; col < width; col++) {
				src.get(row, col, onepixel);
				if (channels == 3) {//3ͨ��
					b = onepixel[0]&0xff;
					g = onepixel[1]&0xff;
					r = onepixel[2]&0xff;
					
					//�޸�
					b = 255 - b;
					g = 255 - g;
					r = 255 - r;
					//�޸ĺ󣬷Ž�ȥ
					onepixel[0] = (byte)b;
					onepixel[1] = (byte)g;
					onepixel[2] = (byte)r;
				} else {//��ͨ��
					gray = onepixel[0]&0xff;
					gray = 255 - gray;
					onepixel[0] = (byte)gray;
				}
				src.put(row, col, onepixel);
			}
		}*/
		
		byte[] data = new byte[channels * width * heigth];//һ��ȫ��ȡ����
		src.get(0, 0, data);
		int r = 0, g = 0, b = 0;
		int gray = 0;
		for (int row = 0; row < heigth; row++) {
			for (int col = 0; col < width; col++) {
				if (channels == 3) {
					b = data[row * channels * width + col * channels]&0xff;
					g = data[row * channels * width + col * channels + 1]&0xff;
					r = data[row * channels * width + col * channels + 2]&0xff;
					
					b = 255 - b;
					g = 255 - g;
					r = 255 - r;
					
					data[row * channels * width + col * channels] = (byte)b;
					data[row * channels * width + col * channels + 1] = (byte)g;
					data[row * channels * width + col * channels + 2] = (byte)r;
				} else {
					gray = data[row * channels * width + col * channels]&0xff;
					gray = 255 - gray;
					data[row * channels * width + col * channels] = (byte)gray;
				}
			} 
			
		}
		src.put(0, 0, data);
		Imgcodecs.imwrite(".\\screenshot\\changPixel.png", src);
		//�ͷ��ڴ�  ��ֹOOM
		src.release();
		dst.release();
	}
}
