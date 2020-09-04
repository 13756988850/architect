package a1.model;

import a1.annotation.Column;
import a1.annotation.Table;

/**
 * @description 订单表
 * @author: 韩冰
 * @create 2020-09-03 17:50
 * @Version 1.0
 **/
@Table("t_order")
public class Order {

    @Column
    private Long id;

    @Column("order_name")
    private String orderName;

    @Column("shop_id")
    private String shopId;

    @Column("user_name")
    private String userName;

    @Column("user_id")
    private String userId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
