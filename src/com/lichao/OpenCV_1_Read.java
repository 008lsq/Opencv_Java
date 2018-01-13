package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * <p>Title: OpenCV_1_Read </p>
 * <p>Description: ͼ���ȡ�뱣��</p>
 * <p>Company: </p>
 * @author LICHAO
 * @date 2018-1-10 ����7:10:14
 */
public class OpenCV_1_Read {

	public static void main(String[] args) {
		//����jar��
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//CV_LOAD_IMAGE_COLOR:��ɫͼ��,��ͨ��  CV_LOAD_IMAGE_GRAYSCALE:��ɫͼ��,��ͨ��
		Mat src = Imgcodecs.imread(".\\img\\lena.png", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		//���ζ���
		Rect rect = new Rect(10, 10, 200, 200);
		//tl:���Ͻǵĵ� br:���½ǵĵ� scarlar:��ʾ��ɫ
		Imgproc.rectangle(src, rect.tl(), rect.br(), new Scalar(0, 0, 255), 2, Imgproc.LINE_8, 0);
		//����ͼ��
		Imgcodecs.imwrite(".\\screenshot\\lena_resut.png", src);
	}
}
