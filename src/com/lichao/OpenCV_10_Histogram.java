package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import com.util.HistogramUtil;

/**
 * 
 * <p>Title: OpenCV_10_Histogram</p>
 * <p>Description: ��������ֱ��ͼ����</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��15�� ����11:56:04
 */
public class OpenCV_10_Histogram {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\lena.png");
		if (src.empty()) {
			return;
		}
		//�Ҷ�ֱ��ͼ---���ο�
		//HistogramUtil.showGrayHistogramRectangle(src);
		//�Ҷ�ֱ��ͼ---���
		//HistogramUtil.showGrayHistogramLine(src);
		//3����ɫֱ��ͼ---���ο�
		HistogramUtil.showColorHistogram(src);
		//1����ɫֱ��ͼ---���
		HistogramUtil.showColorHistogramInOne(src);
	}
}
