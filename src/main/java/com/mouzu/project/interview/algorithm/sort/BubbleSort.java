package com.mouzu.project.interview.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] data = {4,7,1,9,3,5};
        boolean flag = false;
        for (int i = 0; i<data.length-1;i++){
            for (int j=0;j<data.length-i-1;j++){
                int temp;
                if(data[j]>data[j+1]){
                    temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
        System.out.println("排序后："+ Arrays.toString(data));
    }
}
