package com.mouzu.project.interview.algorithm.sort;

import java.util.Arrays;

public class NormalSort {
    public static void main(String[] args) {
        int[] data = {4,7,1,9,3,5};
        for (int i = 0; i<data.length-1;i++){
            for (int j=i+1;j<data.length;j++){
                int temp;
                if(data[i]>data[j]){
                    temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
        System.out.println("排序后："+ Arrays.toString(data));
    }
}
