package a1.refelection;

import a1.annotation.Study;
import a1.model.Person;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description
 * @author: 韩冰
 * @create 2020-09-03 17:01
 * @Version 1.0
 **/
public class RefelectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
//        Class<?> aClass1 = Class.forName("a1.model.Person");

        String name = aClass.getName();//包名+类名 a1.model.Person
        String simpleName = aClass.getSimpleName();//类名 Person
        //System.out.println(simpleName);//sout

        //类属性
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());//属性名称
        }

        //属性具体值
        person.setName("abbott");
        person.setAge(20);
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);//设置属性为可见，避免privte
            System.out.println(declaredField.get(person));
        }

        //实例的另一种写法
        Object p = aClass.newInstance();//相当于在反射中实例化
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (declaredField.getName().equals("name")) { //判断是否为对应属性
                declaredField.set(p, "cclang"); //给属性赋值
            }else{
                declaredField.set(p, 18);
            }

            System.out.println(declaredField.get(p));

        }

        System.out.println("==========反射获取当前所有的方法======================");
        //反射获取当前的方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println("-----------------" + method.getName()); // 方法名
            System.out.println(method.getReturnType()); // 返回值类型

            //参数类型
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println(parameterType);
                System.out.println(parameterType == int.class);
            }
        }

        System.out.println("==========使用单个方法======================");

        // 一个参数
        Method method2 = aClass.getMethod("demo1", String.class);
        method2.invoke(p,"使用单个方法");

        //多个参数
        Method method3 = aClass.getMethod("demo2", new Class[]{int.class, String.class});
        method3.invoke(p,199,"我滴个神啊");

        Method method = aClass.getMethod("show");
        Object invoke = method.invoke(p);
        System.out.println(invoke);

//        aClass.getMethod("setName",declaredFields[0].getType());
//        aClass.getMethod("setName","","");


        //获取注解
        System.out.println("==========获取注解======================");
        Study study = aClass.getAnnotation(Study.class);
        String[] mores = study.mores();
        String name1 = study.name();
        System.out.println(mores + " | " + name);
        
        
        for (Method method1 : methods) {
            Study annotation = method1.getAnnotation(Study.class);
            if(annotation == null) {
                continue;
            }

            String name2 = annotation.name();
            String[] mores1 = annotation.mores();
            System.out.println("--------" + method1.getName());
            System.out.println(name2);
            System.out.println(mores1);
        }


        for (Field declaredField : declaredFields) {
            Study annotation = declaredField.getAnnotation(Study.class);
            if(annotation == null) {
                continue;
            }
            System.out.println(annotation.name());
        }


    }
}
