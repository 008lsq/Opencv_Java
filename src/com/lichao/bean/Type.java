package com.lichao.bean;

/**
 * ��ʵ����/��ͨ�������Ŷ�
 */
public class Type {

    private double human;  //��ʵ�������Ŷȣ�[0~1]������0.5�����ж�Ϊ����
    private double cartoon;  //��ͨ�������Ŷȣ�[0~1]
    public void setHuman(double human) {
         this.human = human;
     }
     public double getHuman() {
         return human;
     }

    public void setCartoon(double cartoon) {
         this.cartoon = cartoon;
     }
     public double getCartoon() {
         return cartoon;
     }

}