package com.lichao;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_11_HistogramHist</p>
 * <p>Description:ֱ��ͼ���⻯</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��15�� ����3:01:09
 */
public class OpenCV_11_HistogramHist {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\flower.png");
		if (src.empty()) {
			return;
		}
		
		//��ɫͼ����ֱ�ӽ��о��⻯
		/*Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_11_Input", gray);
		
		//���⻯
		Mat dst = new Mat();
		Imgproc.equalizeHist(gray, dst);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_equalizeHist_Output", dst);*/
		
		//��ɫͼ����⻯
		Mat hls = new Mat();
		Imgproc.cvtColor(src, hls, Imgproc.COLOR_BGR2HLS);
		List<Mat> mv = new ArrayList<>();
		Core.split(hls, mv);
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_11_Input", src);
		
		Mat dst = new Mat();
		Imgproc.equalizeHist(mv.get(0), dst);
		List<Mat> result = new ArrayList<>();
		result.add(mv.get(1));
		result.add(mv.get(2));
		result.add(dst);
		Core.merge(result, hls);
		Imgproc.cvtColor(hls, src, Imgproc.COLOR_HSV2BGR);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_equalizeHist_Output", src);
	}
}
