package com.lichao;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_14_TemplateMatch</p>
 * <p>Description: ģ��ƥ��</p>
 * <p>Company:</p>
 * @author Administrator
 * @date 2018��1��15�� ����7:11:41
 */
public class OpenCV_14_TemplateMatch {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\test1.png");//����ͼ��
		Mat tpl = Imgcodecs.imread(".\\img\\tpl.png");//ģ��ͼ��
		
		if (src.empty() || tpl.empty()) {
			return;
		}
		
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_src_Input", src);
		//ģ��ƥ��Ľ��result��С����������д��ԭͼ��-ģ��ͼ��+1
		int width = src.cols() - tpl.cols() + 1;
		int height = src.rows() - tpl.rows() + 1;
		Mat result = new Mat(height, width, CvType.CV_32FC1);
		//ģ��ƥ���㷨һ��������
		int method = Imgproc.TM_CCOEFF_NORMED;
		
		Imgproc.matchTemplate(src, tpl, result, method);
		MinMaxLocResult minMaxLocResult = Core.minMaxLoc(result);
		Point maxloc = minMaxLocResult.maxLoc;
		Point minloc = minMaxLocResult.minLoc;
		
		Point matchloc = null;
		if (method == Imgproc.TM_SQDIFF || method == Imgproc.TM_SQDIFF_NORMED) {
			matchloc = minloc;
		} else {
			matchloc = maxloc;
		}
		//������8λ���
		Mat result8u = new Mat();
		Core.normalize(result, result, 0, 255, Core.NORM_MINMAX);
		result.convertTo(result8u, CvType.CV_8UC1);
		ImageUI out = new ImageUI();
		out.imshow("OpenCV_matchTemplate_Output", result8u);
		
		Mat copy = src.clone();
		Imgproc.rectangle(copy, matchloc, new Point(matchloc.x + tpl.cols(), matchloc.y + tpl.rows()), new Scalar(0, 0, 255), 2, 8, 0);
		ImageUI outResult = new ImageUI();
		outResult.imshow("OpenCV_Result_Output", copy);
	}
}
