package com.lichao.bean;

import java.util.List;

/**
 * <p>Title: Result</p>
 * <p>Description: BAIDUƽ̨���ؽ��</p>
 * <p>Company:</p>
 * @author LICHAO
 * @date 2018��3��11�� ����9:37:46
 */
public class JsonRootBean {

    private int result_num; //������Ŀ
    private List<Result> result;
    private long log_id;
    public void setResult_num(int result_num) {
         this.result_num = result_num;
     }
     public int getResult_num() {
         return result_num;
     }

    public void setResult(List<Result> result) {
         this.result = result;
     }
     public List<Result> getResult() {
         return result;
     }

    public void setLog_id(long log_id) {
         this.log_id = log_id;
     }
     public long getLog_id() {
         return log_id;
     }

}