package a1.util;

import a1.annotation.Column;
import a1.annotation.Table;
import a1.model.Order;

import java.lang.reflect.Field;

/**
 * @description
 * @author: 韩冰
 * @create 2020-09-04 08:37
 * @Version 1.0
 **/
public class GenerateSqlUtil {
    public static void main(String[] args) throws Exception {
        Order order = new Order();
        order.setOrderName("吸汗带");
        order.setUserId("123");
        order.setId(555L);

        GenerateSqlUtil generateSqlUtil = new GenerateSqlUtil();
        String query = generateSqlUtil.query(order);
        System.out.println(query);
    }

    //select * from tab where a=1
    public String query(Object tab) throws Exception {
        StringBuffer sql = new StringBuffer("select ");
        StringBuffer where = new StringBuffer("where 1=1 ");
        Class<?> aClass = tab.getClass();
        Table tableAnnotation = aClass.getAnnotation(Table.class);
        if(tableAnnotation == null)
            throw new Exception("没有table注解");
        tableAnnotation.value();

        Field[] declaredFields = aClass.getDeclaredFields();
        String columnName;
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Column annotation = field.getAnnotation(Column.class);
            if (annotation == null) continue; //没写就不是表的列

            if(annotation.value().equals("")) { // 表字段名和属性名一致
                columnName = field.getName();
            }else {
                columnName = annotation.value();
            }
            sql.append(columnName).append(", ");


            Object value = field.get(tab);
            if(value != null) {
                where.append("and ").append(columnName).append("=");
                if(field.getType() == String.class || field.getType() == Character.class) {
                    where.append("'").append(value).append("' ");
                }else {
                    where.append(value).append(" ");
                }
            }
        }

        sql.delete(sql.length()-2, sql.length());
        sql.append(" ").append("from ").append(tableAnnotation.value()).append(" ").append(where);


        return sql.toString();
    }
}
