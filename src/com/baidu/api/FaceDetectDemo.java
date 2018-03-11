package com.baidu.api;

import java.net.URLEncoder;
import com.baidu.aip.util.Base64Util;
import com.util.FileUtil;
import com.util.HttpUtil;

/**
* ����̽��
*/
public class FaceDetectDemo {

    /**
    * ��Ҫ��ʾ���������蹤����
    * FileUtil,Base64Util,HttpUtil,GsonUtils���
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * ����
    */
    public static String detect() {
        // ����url
        String url = "https://aip.baidubce.com/rest/2.0/face/v1/detect";
        try {
            // �����ļ�·��
            String filePath = ".\\img\\tangwei.png";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "max_face_num=" + 5 + "&face_fields=" + "age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities" + "&image=" + imgParam;

            // ע�������Ϊ�˼򻯱���ÿһ������ȥ��ȡaccess_token�����ϻ���access_token�й���ʱ�䣬 �ͻ��˿����л��棬���ں����»�ȡ��
            //String accessToken = "24.d182a312a98ae340a60587ff6c51c6f5.2592000.1523363473.282335-10910534";
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

 //���ز���api
//    log_id	uint64	��	��־id
//    result_num	uint32	��	������Ŀ
//    result	object[]	��	�������Զ���ļ���
//    +age	double	��	���䡣face_fields����ageʱ����
//    +beauty	double	��	�����֣���Χ0-100��Խ���ʾԽ����face_fields����beautyʱ����
//    +location	object	��	������ͼƬ�е�λ��
//    ++left	uint32	��	������������߽�ľ���
//    ++top	uint32	��	�����������ϱ߽�ľ���
//    ++width	uint32	��	��������Ŀ��
//    ++height	uint32	��	��������ĸ߶�
//    +face_probability	double	��	�������Ŷȣ���Χ0-1
//    +rotation_angle	int32	��	�������������ֱ�����˳ʱ����ת�ǣ�[-180,180]
//    +yaw	double	��	��ά��ת֮������ת��[-90(��), 90(��)]
//    +pitch	double	��	��ά��ת֮�����Ƕ�[-90(��), 90(��)]
//    +roll	double	��	ƽ������ת��[-180(��ʱ��), 180(˳ʱ��)]
//    +expression	uint32	��	���飬0����Ц��1��΢Ц��2����Ц��face_fields����expressionʱ����
//    +expression_probability	double	��	�������Ŷȣ���Χ0~1��face_fields����expressionʱ����
//    +faceshape	object[]	��	�������Ŷȡ�face_fields����faceshapeʱ����
//    ++type	string	��	���ͣ�square/triangle/oval/heart/round
//    ++probability	double	��	���Ŷȣ�0~1
//    +gender	string	��	male��female��face_fields����genderʱ����
//    +gender_probability	double	��	�Ա����Ŷȣ���Χ[0~1]��face_fields����genderʱ����
//    +glasses	uint32	��	�Ƿ���۾���0-���۾���1-��ͨ�۾���2-ī����face_fields����glassesʱ����
//    +glasses_probability	double	��	�۾����Ŷȣ���Χ[0~1]��face_fields����glassesʱ����
//    +landmark	object[]	��	4���ؼ���λ�ã��������ġ��������ġ��Ǽ⡢�����ġ�face_fields����landmarkʱ����
//    ++x	uint32	��	x����
//    ++y	uint32	��	y����
//    +landmark72	object[]	��	72��������λ�ã�face_fields����landmarkʱ����
//    ++x	uint32	��	x����
//    ++y	uint32	��	y����
//    +race	string	��	yellow��white��black��arabs��face_fields����raceʱ����
//    +race_probability	double	��	�������Ŷȣ���Χ[0~1]��face_fields����raceʱ����
//    +qualities	object	��	����������Ϣ��face_fields����qualitiesʱ����
//    ++occlusion	object	��	�����������ڵ��ĸ��ʣ���Χ[0~1]��0��ʾ������1��ʾ������
//    +++left_eye	double	��	�����ڵ�����
//    +++right_eye	double	��	�����ڵ�����
//    +++nose	double	��	�����ڵ�����
//    +++mouth	double	��	����ڵ�����
//    +++left_cheek	double	��	�������ڵ�����
//    +++right_cheek	double	��	�������ڵ�����
//    +++chin	double	��	�°��ڵ�����
//    ++blur	double	��	����ģ���̶ȣ���Χ[0~1]��0��ʾ������1��ʾģ��
//    ++illumination	-	��	ȡֵ��Χ��[0~255],��ʾ��������Ĺ��ճ̶�
//    ++completeness	-	��	���������ȣ�0��1, 0Ϊ�������ͼ��߽磬1Ϊ��������ͼ��߽���
//    ++type	object	��	��ʵ����/��ͨ�������Ŷ�
//    +++human	-	��	��ʵ�������Ŷȣ�[0~1]������0.5�����ж�Ϊ����
//    +++cartoon	-	��	��ͨ�������Ŷȣ�[0~1]

 
    public static void main(String[] args) {
//        FaceDetect.detect();
//   {"result_num":1,"result":[{"location":{"left":77,"top":144,"width":149,"height":154},"face_probability":1,"rotation_angle":1,"yaw":-0.45118400454521,"pitch":5.3421592712402,"roll":1.474733710289,"landmark":[{"x":115,"y":169},{"x":185,"y":171},{"x":150,"y":212},{"x":149,"y":249}],"landmark72":[{"x":76,"y":170},{"x":78,"y":194},{"x":81,"y":219},{"x":87,"y":244},{"x":100,"y":270},{"x":123,"y":292},{"x":148,"y":300},{"x":173,"y":294},{"x":197,"y":273},{"x":212,"y":247},{"x":218,"y":222},{"x":223,"y":198},{"x":225,"y":173},{"x":99,"y":171},{"x":106,"y":166},{"x":114,"y":165},{"x":123,"y":167},{"x":130,"y":174},{"x":122,"y":175},{"x":114,"y":176},{"x":105,"y":174},{"x":115,"y":169},{"x":88,"y":155},{"x":98,"y":145},{"x":111,"y":144},{"x":123,"y":147},{"x":134,"y":155},{"x":122,"y":155},{"x":111,"y":153},{"x":99,"y":153},{"x":170,"y":175},{"x":178,"y":169},{"x":186,"y":167},{"x":193,"y":168},{"x":200,"y":174},{"x":194,"y":176},{"x":186,"y":178},{"x":178,"y":177},{"x":185,"y":171},{"x":166,"y":157},{"x":177,"y":149},{"x":188,"y":147},{"x":200,"y":148},{"x":210,"y":158},{"x":200,"y":157},{"x":189,"y":156},{"x":178,"y":157},{"x":140,"y":175},{"x":137,"y":190},{"x":134,"y":205},{"x":129,"y":220},{"x":139,"y":221},{"x":160,"y":221},{"x":170,"y":221},{"x":166,"y":205},{"x":163,"y":191},{"x":160,"y":176},{"x":150,"y":212},{"x":122,"y":248},{"x":135,"y":241},{"x":149,"y":241},{"x":163,"y":242},{"x":174,"y":249},{"x":163,"y":257},{"x":148,"y":261},{"x":134,"y":257},{"x":135,"y":247},{"x":149,"y":248},{"x":162,"y":248},{"x":162,"y":250},{"x":149,"y":250},{"x":136,"y":249}],"age":31.849361419678,"beauty":58.011905670166,"expression":0,"expression_probablity":0.99998700618744,"faceshape":[{"type":"square","probability":0.10893177986145},{"type":"triangle","probability":0.0039200717583299},{"type":"oval","probability":0.73189455270767},{"type":"heart","probability":0.052113145589828},{"type":"round","probability":0.10314042121172}],"gender":"male","gender_probability":0.99999928474426,"glasses":0,"glasses_probability":0.99999630451202,"race":"yellow","race_probability":0.99998033046722,"qualities":{"occlusion":{"left_eye":0,"right_eye":0,"nose":0,"mouth":0,"left_cheek":0,"right_cheek":0,"chin":0},"blur":0,"illumination":0,"completeness":0,"type":{"human":0.99938035011292,"cartoon":0.00061963585903868}}}],"log_id":4130331474} 	
    	System.out.println(FaceDetectDemo.detect());
    }
}