import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 static int carDistribution(int n, int[] ar) {
     int[] counter = new int[6];
     for(int num : ar)
         counter[num]++;
     int max = counter[1];
     int maxNum = 1;
     for(int i=2; i<6; i++)
         if(counter[i]>max){
             max = counter[i];
             maxNum = i;
         }
     return maxNum;
     
 }
 public static void main(String[] args) {
     Scanner in = new Scanner(System.in);
     int n = in.nextInt();
     int[] ar = new int[n];
     for(int ar_i = 0; ar_i < n; ar_i++){
        ar[ar_i] = in.nextInt();
     }
     int result = carDistribution(n, ar);
     System.out.println(result);
     }
}
