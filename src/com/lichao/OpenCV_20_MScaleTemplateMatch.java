package com.lichao;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import com.util.ImageUI;

/**
 * 
 * <p>Title: OpenCV_20_MScaleTemplateMatch</p>
 * <p>Description: ��߶�ģ��ƥ��</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��1��16�� ����1:37:43
 */
public class OpenCV_20_MScaleTemplateMatch {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(".\\img\\traffic.jpg");//����ͼ��
		Mat tpl = Imgcodecs.imread(".\\img\\flag.png");//ģ��ͼ��
		
		if (src.empty() || tpl.empty()) {
			return;
		}
		
		ImageUI ui = new ImageUI();
		ui.imshow("OpenCV_20_Input", src);
		
		List<Mat> scales = buildGoussianPyromid(src, 3);
		List<Mat> templates = buildGoussianPyromid(tpl, 3);
		int method = Imgproc.TM_CCOEFF_NORMED;
		boolean found = false;
		for (int i = 0; i < scales.size(); i++) {
			//ƥ�䵽�ͽ���ѭ��
			if (found) break;
			for (int j = 0; j < templates.size(); j++) {
				MinMaxLocResult minMaxLocResult = matchImage(scales.get(i), templates.get(j), method);
				Point maxloc = minMaxLocResult.maxLoc;
				Point minloc = minMaxLocResult.minLoc;
				
				Point matchloc = null;
				double matchValue = 0.0;
				if (method == Imgproc.TM_SQDIFF || method == Imgproc.TM_SQDIFF_NORMED) {
					matchloc = minloc;
					matchValue = minMaxLocResult.minVal;
				} else {
					matchloc = maxloc;
					matchValue = minMaxLocResult.maxVal;
				}
				//������ֵ����0.75����Ϊ��ƥ��,ʹ��Imgproc.TM_CCOEFF_NORMEDģ��ƥ��ſ���
				if (matchValue > 0.75) {
					Mat copy_src = scales.get(i).clone();
					Mat copy_tpl = templates.get(j).clone();
					Imgproc.rectangle(copy_src, matchloc, new Point(matchloc.x + copy_tpl.cols(), matchloc.y + copy_tpl.rows()), new Scalar(0, 0, 255), 2, 8, 0);
					ImageUI outResult = new ImageUI();
					outResult.imshow("OpenCV_Result_Output", copy_src);
					found = true;
					break;
				}
			}
		}
		
	}
	
	/**
	 * ͼ��ƥ��
	 * @param src
	 * @param tpl
	 * @param method ƥ���㷨����
	 * @return
	 */
	public static MinMaxLocResult matchImage(Mat src, Mat tpl, int method) {
		int width = src.cols() - tpl.cols() + 1;
		int height = src.rows() - tpl.rows() + 1;
		Mat result = new Mat(height, width, CvType.CV_32FC1);
		
		Imgproc.matchTemplate(src, tpl, result, method);
		MinMaxLocResult minMaxLocResult = Core.minMaxLoc(result);
		return minMaxLocResult;
	}
	
	/**
	 * ������˹������
	 * @param image ͼ��
	 * @param level ���
	 * @return
	 */
	public static List<Mat> buildGoussianPyromid(Mat image, int level) {
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
		return pyromid;
	}
}
