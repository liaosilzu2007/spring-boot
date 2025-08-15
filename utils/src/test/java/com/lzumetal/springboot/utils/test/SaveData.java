package com.lzumetal.springboot.utils.test;

/**
 * @author liaosi
 */
public class SaveData {

    private String target;

    private InnerClass innerClass;

    private int num = 0;

    public SaveData(String target) {
        this.target = target;
        this.innerClass = new InnerClass();
    }

    private class InnerClass {

        public void saveContent(String content) {
            System.out.println("数据保存至【" + target + "】,保存内容为：" + content);
            num++;
            System.out.println("今日已保存数据条数：" + num);
        }

    }


    public void save(String content) {
        innerClass.saveContent(content);
    }


    public static void main(String[] args) {
        SaveData saveToDatabase = new SaveData("数据库");
        saveToDatabase.save("一条订单记录");
        saveToDatabase.save("个人信息变更");
    }

}
