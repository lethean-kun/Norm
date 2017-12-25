package cn.com.zhiding.util;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {
	
	public static void main(String [] args){
		List<Integer> inList = new ArrayList<Integer>();
		for(int i = 0;i < 100000;i++){
			inList.add(i);
		}
		int a;
		int b;
		int c;
		long start1 = System.currentTimeMillis();

		for(int i = 0;i<inList.size();i++){
			a =1;
		}
		long end1 = System.currentTimeMillis();
		System.out.println("循环a用时："+(end1-start1)+"ms");
		long start2 = System.currentTimeMillis();
		for(int i = 0,j=inList.size();i<j;i++){
			b =1;
		}
		long end2 = System.currentTimeMillis();
		System.out.println("循环b用时："+(end2-start2)+"ms");
		long start3 = System.currentTimeMillis();
		for(Integer in:inList){
			c =1;
		}
		long end3 = System.currentTimeMillis();
		System.out.println("循环c用时："+(end3-start3)+"ms");
	}

}
