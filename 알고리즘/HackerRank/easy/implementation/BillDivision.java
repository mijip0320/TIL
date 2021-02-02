package com.algorithm.easy.implementation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class BillDivision {
    // Complete the bonAppetit function below.
    static void bonAppetit(List<Integer> bill, int k, int b) {
    int answer = 0;
    int sum = 0;
    for(int i=0; i<bill.size(); i++){
        if(i != k){
            sum += bill.get(i);
        }
    }
    sum = sum/2;

    if(sum < b){
        answer = b-sum;
        System.out.println(answer);
    }else if(sum == b){
        System.out.println("Bon Appetit");
    }

    }

    public static void main(String[] args) {
    int k = 1;
    int b = 12;

    List<Integer> bill = new ArrayList<>();
    bill.add(3);
    bill.add(10);
    bill.add(2);
    bill.add(9);


    bonAppetit(bill, k, b);

    }
}
