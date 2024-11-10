package com.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.entity.Jiangxiang;



public class DrawLotteryUtil {

	public static int drawGift(List<Jiangxiang> giftList){
		//System.out.println("giftList=="+giftList.size());
        if(null != giftList && giftList.size()>0){
            List<Double> orgProbList = new ArrayList<Double>(giftList.size());
            
            for(Jiangxiang jiangxiang:giftList){
                //按顺序将概率添加到集合中
                orgProbList.add(jiangxiang.getProb());
                
            }
            	//System.out.println("orgProbList=="+orgProbList);
            return draw(orgProbList);

        }
        return -1;
    }
	
	
	
    public static int draw(List<Double> giftProbList){
    	//System.out.println("giftProbList====="+giftProbList);
        List<Double> sortRateList = new ArrayList<Double>();

        // 计算概率总和
        Double sumRate = 0D;
        for(Double prob : giftProbList){
            sumRate += prob;
        }
        
        //System.out.println("sumRate==="+sumRate);

        if(sumRate != 0){
            double rate = 0D;   //概率所占比例
            //System.out.println("giftProbList===="+giftProbList);
            for(Double prob : giftProbList){
                rate += prob;   
                // 构建一个比例区段组成的集合(避免概率和不为1)
                sortRateList.add(rate / sumRate);
            }
            
            //System.out.println("sortRateList===="+sortRateList);

            // 随机生成一个随机数，并排序
            double random = Math.random();
            //System.out.println("random===="+random);
            sortRateList.add(random);
            Collections.sort(sortRateList);
            //System.out.println("sortRateList===="+sortRateList);

            // 返回该随机数在比例集合中的索引
            return sortRateList.indexOf(random);
        }


        return -1;
    }

    
    
    public static void main(String[] args) {
        Jiangxiang iphone = new Jiangxiang();
        iphone.setName("10000元");
        iphone.setProb(0.7D);
        
        Jiangxiang wj = new Jiangxiang();
        wj.setName("未中奖");
        wj.setProb(0.9D);

        Jiangxiang thanks = new Jiangxiang();
        thanks.setName("28888元");
        thanks.setProb(0.04D);

        Jiangxiang vip = new Jiangxiang();
        vip.setName("66666元");
        vip.setProb(0.03D);
        
        Jiangxiang vip1 = new Jiangxiang();
        vip1.setName("88888元");
        vip1.setProb(0.02D);

        Jiangxiang vip2 = new Jiangxiang();
        vip2.setName("100000元");
        vip2.setProb(0.01D);
        
        List<Jiangxiang> list = new ArrayList<Jiangxiang>();
        list.add(iphone);
        list.add(vip);
        list.add(thanks);
        list.add(vip1);
        list.add(vip2);
        list.add(wj);

        for(int i=0;i<20;i++){
        	//System.out.println("list==="+list);
            //int index = drawGift(list);
            //System.out.println("index===="+index);
         //System.out.println("奖品："+list.get(index).getName()+"***抽中的概率："+list.get(index).getProb());
        }
    }
	


}
