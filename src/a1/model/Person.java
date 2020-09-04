package a1.model;

import a1.annotation.Study;

/**
 * @description
 * @author: 韩冰
 * @create 2020-09-03 16:54
 * @Version 1.0
 **/
@Study(mores = {"hard","easy"})
public class Person {
    @Study(name="aa",mores = {"hard","easy"})
    private int age;

    @Study(mores = {"hard","easy"})
    private String name;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getName() {
        return name;
    }

    @Study(name = "cris", mores = {"hard","easy"})
    public void setName(String name) {
        this.name = name;
    }

    public String show() {
        return age + " " + name;
    }

    public void demo1(String name) {
        this.name = name;
    }

    public void demo2(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
