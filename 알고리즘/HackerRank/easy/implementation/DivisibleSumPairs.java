package com.example.Algorithm.easy.implementation;

public class DivisibleSumPairs {

    // Complete the divisibleSumPairs function below.
    static int divisibleSumPairs(int n, int k, int[] ar) {
        int count = 0;

        for(int i=0; i<ar.length-1; i++){
            int sum = 0;
            for(int j=i+1; j<ar.length; j++){
                sum = ar[i]+ar[j];
                if( sum % k == 0 ){
                    count++;
                }
            }
        }

        return count;

    }


    public static void main(String[] args){
        int n = 6;
        int k = 3;
        int[] ar = {1,3,2,6,1,2};

        int result = divisibleSumPairs(n, k, ar);

        System.out.println(result);
    }
}
