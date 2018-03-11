package com.lichao.bean;

import java.util.List;

/**
 * <p>Title: Result</p>
 * <p>Description: BAIDUƽ̨����������Ϣ���</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��3��11�� ����9:37:46
 */
public class Result {

    private Location location;  //������ͼƬ�е�λ��
    private int face_probability;  //�������Ŷȣ���Χ0-1
    private int rotation_angle;  //�������������ֱ�����˳ʱ����ת�ǣ�[-180,180] 
    private double yaw;  //��ά��ת֮������ת��[-90(��), 90(��)] 
    private double pitch;  //��ά��ת֮�����Ƕ�[-90(��), 90(��)] 
    private double roll;  //ƽ������ת��[-180(��ʱ��), 180(˳ʱ��)] 
    private List<Landmark> landmark;  //4���ؼ���λ�ã��������ġ��������ġ��Ǽ⡢�����ġ�face_fields����landmarkʱ����
    private List<Landmark72> landmark72;  // 72��������λ�ã�face_fields����landmarkʱ���� 
    private double age;  //���䡣face_fields����ageʱ���� 
    private double beauty;  //�����֣���Χ0-100��Խ���ʾԽ����face_fields����beautyʱ����
    private int expression;  //���飬0����Ц��1��΢Ц��2����Ц��face_fields����expressionʱ���� 
    private double expression_probablity;  //�������Ŷȣ���Χ0~1��face_fields����expressionʱ���� 
    private List<Faceshape> faceshape;  //�������Ŷȡ�face_fields����faceshapeʱ���� 
    private String gender;  //male��female��face_fields����genderʱ���� 
    private double gender_probability;  //�Ա����Ŷȣ���Χ[0~1]��face_fields����genderʱ���� 
    private int glasses;  //�Ƿ���۾���0-���۾���1-��ͨ�۾���2-ī����face_fields����glassesʱ���� 
    private double glasses_probability;  //�۾����Ŷȣ���Χ[0~1]��face_fields����glassesʱ���� 
    private String race;  //yellow��white��black��arabs��face_fields����raceʱ���� 
    private double race_probability;  //�������Ŷȣ���Χ[0~1]��face_fields����raceʱ���� 
    private Qualities qualities;  //face_fields����qualitiesʱ���� 
    public void setLocation(Location location) {
         this.location = location;
     }
     public Location getLocation() {
         return location;
     }

    public void setFace_probability(int face_probability) {
         this.face_probability = face_probability;
     }
     public int getFace_probability() {
         return face_probability;
     }

    public void setRotation_angle(int rotation_angle) {
         this.rotation_angle = rotation_angle;
     }
     public int getRotation_angle() {
         return rotation_angle;
     }

    public void setYaw(double yaw) {
         this.yaw = yaw;
     }
     public double getYaw() {
         return yaw;
     }

    public void setPitch(double pitch) {
         this.pitch = pitch;
     }
     public double getPitch() {
         return pitch;
     }

    public void setRoll(double roll) {
         this.roll = roll;
     }
     public double getRoll() {
         return roll;
     }

    public void setLandmark(List<Landmark> landmark) {
         this.landmark = landmark;
     }
     public List<Landmark> getLandmark() {
         return landmark;
     }

    public void setLandmark72(List<Landmark72> landmark72) {
         this.landmark72 = landmark72;
     }
     public List<Landmark72> getLandmark72() {
         return landmark72;
     }

    public void setAge(double age) {
         this.age = age;
     }
     public double getAge() {
         return age;
     }

    public void setBeauty(double beauty) {
         this.beauty = beauty;
     }
     public double getBeauty() {
         return beauty;
     }

    public void setExpression(int expression) {
         this.expression = expression;
     }
     public int getExpression() {
         return expression;
     }

    public void setExpression_probablity(double expression_probablity) {
         this.expression_probablity = expression_probablity;
     }
     public double getExpression_probablity() {
         return expression_probablity;
     }

    public void setFaceshape(List<Faceshape> faceshape) {
         this.faceshape = faceshape;
     }
     public List<Faceshape> getFaceshape() {
         return faceshape;
     }

    public void setGender(String gender) {
         this.gender = gender;
     }
     public String getGender() {
         return gender;
     }

    public void setGender_probability(double gender_probability) {
         this.gender_probability = gender_probability;
     }
     public double getGender_probability() {
         return gender_probability;
     }

    public void setGlasses(int glasses) {
         this.glasses = glasses;
     }
     public int getGlasses() {
         return glasses;
     }

    public void setGlasses_probability(double glasses_probability) {
         this.glasses_probability = glasses_probability;
     }
     public double getGlasses_probability() {
         return glasses_probability;
     }

    public void setRace(String race) {
         this.race = race;
     }
     public String getRace() {
         return race;
     }

    public void setRace_probability(double race_probability) {
         this.race_probability = race_probability;
     }
     public double getRace_probability() {
         return race_probability;
     }

    public void setQualities(Qualities qualities) {
         this.qualities = qualities;
     }
     public Qualities getQualities() {
         return qualities;
     }
}