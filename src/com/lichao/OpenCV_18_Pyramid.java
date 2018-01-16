package com.lichao;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_18_Pyramid</p>
 * <p>Description: ͼ�������</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��16�� ����10:20:56
 */
public class OpenCV_18_Pyramid {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test.png");//����ͼ��
		
		if (src.empty()) {
			return;
		}
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_18_Input", src);
		
		//��������Ϊ3��
		int level = 3;
		Mat temp = src.clone();
		List<Mat> result = new ArrayList<Mat>();
		for (int i = 0; i < level; i++) {//��ͼ��С
			Mat dst = new Mat();
			Imgproc.pyrDown(temp, dst);
			ImageUI out = new ImageUI();
			out.imshow("��˹������" + i, dst);
			dst.copyTo(temp);
			result.add(dst);
		}
		
		for (int i = result.size() - 1; i >= 0; i--) {//Сͼ���
			Mat expand = new Mat();
			Mat lapalian = new Mat();
			if (i-1 < 0) {
				Imgproc.pyrUp(result.get(i), expand, src.size());
				Core.subtract(src, expand, lapalian);
				
				//Ϊ�˹ۿ��������ӣ�ʵ�ʲ����������
				Mat constant = new Mat(lapalian.size(), lapalian.type());
				constant.setTo(new Scalar(127, 127, 127));
				Core.add(constant, lapalian, lapalian);
				
				ImageUI out = new ImageUI();
				out.imshow("������˹������" + i, lapalian);
			} else {
				Imgproc.pyrUp(result.get(i), expand, result.get(i-1).size());
				Core.subtract(result.get(i-1), expand, lapalian);
				
				//Ϊ�˹ۿ��������ӣ�ʵ�ʲ����������
				Mat constant = new Mat(lapalian.size(), lapalian.type());
				constant.setTo(new Scalar(127, 127, 127));
				Core.add(constant, lapalian, lapalian);
				
				ImageUI out = new ImageUI();
				out.imshow("������˹������" + i, lapalian);
			}
			
		}
	}
}
