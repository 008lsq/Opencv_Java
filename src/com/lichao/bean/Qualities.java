package com.lichao.bean;

/**
 * ����������Ϣ
 */
public class Qualities {

    private Occlusion occlusion;  //�����������ڵ��ĸ��ʣ���Χ[0~1]��0��ʾ������1��ʾ������ 
    private int blur;  //����ģ���̶ȣ���Χ[0~1]��0��ʾ������1��ʾģ�� 
    private int illumination;  //ȡֵ��Χ��[0~255],��ʾ��������Ĺ��ճ̶� 
    private int completeness;  //���������ȣ�0��1, 0Ϊ�������ͼ��߽磬1Ϊ��������ͼ��߽��� 
    private Type type;  //��ʵ����/��ͨ�������Ŷ�
    public void setOcclusion(Occlusion occlusion) {
         this.occlusion = occlusion;
     }
     public Occlusion getOcclusion() {
         return occlusion;
     }

    public void setBlur(int blur) {
         this.blur = blur;
     }
     public int getBlur() {
         return blur;
     }

    public void setIllumination(int illumination) {
         this.illumination = illumination;
     }
     public int getIllumination() {
         return illumination;
     }

    public void setCompleteness(int completeness) {
         this.completeness = completeness;
     }
     public int getCompleteness() {
         return completeness;
     }

    public void setType(Type type) {
         this.type = type;
     }
     public Type getType() {
         return type;
     }

}