package com.lichao.bean;

/**
 * ����
 */
public class Faceshape {

    private String type;  //���ͣ�square/triangle/oval/heart/round 
    private double probability;  //���Ŷȣ�0~1
    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setProbability(double probability) {
         this.probability = probability;
     }
     public double getProbability() {
         return probability;
     }

}