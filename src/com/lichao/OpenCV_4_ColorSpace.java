package com.lichao;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_4_ColorSpace</p>
 * <p>Description: RGBɫ�ʿռ�</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��13�� ����10:14:57
 */
public class OpenCV_4_ColorSpace {
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\lena.png");//BGR
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_4_Input", src);
		/*Mat dst = new Mat();
		//Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2GRAY);//GRAY
		//Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2HLS);//HLS
		//Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2YUV);//YUV
		Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2HSV);//HSV
		Mat binary = new Mat();
		//��ɫֵ����inRange,���˲�ͬ����ɫȡ��ֵͬ
		Core.inRange(src, new Scalar(30, 45, 45), new Scalar(180, 255, 255), binary);
		
		ImageUI output = new ImageUI();
		output.imshow("OpenCV_4_Output", binary);*/
		
		//ͨ������  split
		List<Mat> mv = new ArrayList<Mat>();
		Core.split(src, mv);
		int index = 1;
		for (Mat m : mv) {
			if (index == 1) {
				m.setTo(new Scalar(0, 0, 0));//����1ͨ��
			}
			ImageUI output = new ImageUI();
			output.imshow("channel-" + index, m);
			index++;
		}
		
		//ͨ���ϲ�  merge
		/*Mat dst = new Mat(src.size(), src.type());
		Core.merge(mv, dst);
		ImageUI output_merge = new ImageUI();
		output_merge.imshow("merge_result", dst);*/
		
		//ͨ������mixChannels
		List<Mat> channels = new ArrayList<Mat>();
		channels.add(new Mat(src.size(), CvType.CV_8UC1));
		Core.mixChannels(mv, channels, new MatOfInt(2, 0));
		ImageUI output_mix = new ImageUI();
		output_mix.imshow("mix_result", channels.get(0));
	}
}
