package com.lj.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//购物车模型
public class Cart {

    private Map<Integer,CartItem> items=new LinkedHashMap<Integer,CartItem>();

    /**
    * @Description:添加商品给购物车
            * @Param: []
            * @return: void
            * @Author: longjian
            * @Date:15:24 2022/2/20
            */
    public void addItem(CartItem cartItem){
        //先看购物车中是否已经存在此商品
        CartItem item = items.get(cartItem.getId());
        //等于空说明没有添加过此商品，直接添加
        if(item==null){
            items.put(cartItem.getId(),cartItem);
        }
        //否则就是数量累加
        else{
            item.setCount(item.getCount()+1);
            item.setTotalprice(item.getTotalprice().add(item.getPrice()));
        }
    }
/** 
* @Description: 删除商品
        * @Param: []
        * @return: void
        * @Author: longjian
        * @Date:15:26 2022/2/20
        */

    public void deleteItem(Integer id){
        items.remove(id);
    }
    /** 
    * @Description: 清空购物车
            * @Param: []
            * @return: void
            * @Author: longjian
            * @Date:15:26 2022/2/20
            */
    
    public void clear(){
        items.clear();
    }
/**
* @Description: 修改商品数量
        * @Param: [id, count]id是物品id，count是修改后的数量
        * @return: void
        * @Author: longjian
        * @Date:15:29 2022/2/20
        */

    public void updateItem(Integer id,Integer count){
        //先看购物车中是否已经存在此商品
        CartItem item = items.get(id);
        //不等于空直接修改数量，并且修改金额
        if(item!=null){
            item.setCount(count);
            //单价x数量,BigDecimal不能直接用符号进行计算
            item.setTotalprice(item.getPrice().multiply(new BigDecimal(item.getCount())));
            items.put(id,item);

        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalcount=" + getTotalcount() +
                ", totalprice=" + getTotalprice()+
                ", items=" + items +
                '}';
    }

    public Integer getTotalcount() {
        Integer totalcount=0;
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            //累加
            totalcount+=entry.getValue().getCount();
        }
        return totalcount;
    }

    public BigDecimal getTotalprice() {
        BigDecimal totalprice=new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            //累加
            totalprice=totalprice.add(entry.getValue().getTotalprice());
        }
        return totalprice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
