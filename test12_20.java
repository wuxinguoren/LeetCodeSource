package test;

import java.util.ArrayList;
import java.util.List;

public class test12_20 {
	//������������ֵ����1/2��
    public static int majorityElement(int[] nums) {
    	int cur = 0; int k =0;
    	for(int i=0;i<nums.length;i++){
    		if(k==0){
    			cur=nums[i];
    			if(i==nums.length-1){
    				return cur;
    			}
    			k++;
    			i++;
    		}
    		if(nums[i]==cur){
    			k++;
    		}else{
    			k--;
    		}
    	}
    	return cur;
    }
   //fail solution.
/*	ArrayList<Integer> temp = new ArrayList<Integer>();
	boolean isAgain = true;
	int j = nums.length-1;
	for(int i=0;i<=j;i++,j--){
		if(i==j)
		{
			temp.add(nums[i]);
		}else if(nums[i]==nums[j])
		{
			temp.add(nums[i]);
			temp.add(nums[i]);
		}
	}
	
	while(isAgain&&temp.size()>1){
		isAgain = false;
		for(int i=0;i<temp.size()-1;i++){
			if(temp.get(i)!=temp.get(i+1)){
				temp.remove(i);
				temp.remove(i);
				isAgain = true;
				i--;
			}
		}
	}
	
	if(temp.size()==0)
		return 0;
	
	return temp.get(0);*/
 
	public static void main(String args[]){
		String character = "";    
		int[] temp1 = {1,1,1,2,2};
		System.out.print(majorityElement(temp1));
	}
	
	//the 32-bit integer ��11' has binary representation 00000000000000000000000000001011, so the function should return 3.
	   //Reverse bits of a given 32 bits unsigned integer.
    public int hammingWeight(int n) {
        int count = 0;
        for(int i = 0; i < 32; i++) {
            if((n&1) == 1) {  // n&1 ��ʾȡ��ĩλ����ż��
                count++;
            }
            n = n>>1;
        }
        return count;       
    }
    public int reverseBits(int n) {
    	int result = 0;
        for(int i = 0; i < 32; i++) {
            result <<=1;
        	if((n&1) == 1) {  // n&1 ��ʾȡ��ĩλ����ż��
                result++;
            }
            n >>=1;
        }
        return result;           
    }
    
	//rotate k steps to right.
	//way1
/*    public static void rotate(int[] nums, int k) {
    	k = k%nums.length;
        int[] temp = new int[k];
        int j = nums.length-1;
        for(int i =k-1;i>=0;i--){
        	temp[i] = nums[j];
        	j--;
        }
        
        j=nums.length-1-k;
        for(int i = nums.length-1; i>=k ; i--){
        	nums[i] = nums[j];
        	j--;
        }
        
        for(int i =0;i<k;i++){
        	nums[i]=temp[i];
        }
    }*/
	//way2
	public static void rotate(int[] nums, int k) {
		k = k%nums.length;
		int temp =0;
		int j = nums.length-k-1;
		for(int i =0;i<(nums.length-k-1);i++){
			if(i<j){
				temp=nums[j];
				nums[j]=nums[i];
				nums[i]=temp;
				j--;
			}else{
				break;
			}
		}
		j=nums.length-1;
		for(int i =nums.length-k;i<nums.length-1;i++){
			if(i<j){
				temp=nums[j];
				nums[j]=nums[i];
				nums[i]=temp;
				j--;
			}else{
				break;
			}
		}
		
		j=nums.length-1;
		for(int i =0;i<nums.length-1;i++){
			if(i<j){
				temp=nums[j];
				nums[j]=nums[i];
				nums[i]=temp;
				j--;
			}else{
				break;
			}
		}
		
		
		System.out.print("finish");
	}

	// Z��״�ַ���ȡ��
    public static String convert(String s, int numRows) {
    	if(numRows==1)
    		return s;
    	
        String result = "";
    	String[] temp = new String[numRows];
    	for(int i=0;i<numRows;i++){
    		temp[i]="";
    	}
    	boolean isPlus = true;
    	int curRows =0;
    	for(int i=0;i<s.length();i++){
    		temp[curRows] += s.substring(i, i+1);
    		if(isPlus){
    			if(curRows==numRows-1){
    				isPlus=false;
    				curRows--;
    			}else{
    				curRows++;
    			}
    		}else{
    			
    			if(curRows==0){
    				isPlus=true;
    				curRows++;
    			}else{
    				curRows--;
    			}
    			
    		}
    	}
    	
    	for(int i=0;i<numRows;i++){
    		result += temp[i];
    	}
    	
    	return result;
    }
    
	//��תint����..��Ҫ�ǿ���overflow����32λint�����
    public static int reverse(int x) {
        int result = 0;
        boolean isPo;
        if(x>=0)
        {
        	isPo=true;
        }else{
        	isPo=false;
        }
        while(x!=0){
        	if(isPo&&result>(Integer.MAX_VALUE-x%10)/10)
        	{
        		return 0;
        	}
        	if(!isPo&&result<(Integer.MIN_VALUE-x%10)/10)
        	{
        		return 0;
        	}
        	result *=10;
        	result += (x%10);
        	x/=10;
        }
        return result;
    }
    
	// A -- 1
	// ...   
	// Z -- 26
	// A -- 27
    public static int titleToNumber(String s) {
        int result =0;
        int j = 0;
        for(int i =s.length()-1;i>=0;i--){
        	result += ((int)s.charAt(i)-64)*Math.pow(26, j);
        	j++;
        }
        
        return result;
    }
    //������ת��
    public static String convertToTitle(int n) {
    	if(n==26)
    	{
    		return "Z";
    	}
        String result = "";
        int start = 1;
        
        while(n>Math.pow(26, start)){
        	start++;
        }
        int temp =0;
        for(int i =1;i<=start;i++){
        	temp = (int) ((n%Math.pow(26, i))/(Math.pow(26, i-1)));
        	if(temp==0){
        		if(i==start)
        		{
        			break;
        		}
        		temp=26;
        	}
        	result = Character.toString((char)(temp+64))+result;
        	n-=temp*Math.pow(26, i-1);
        }
        

        
        return result;
    }
    
	//����!n�����м�����
	//ֻ��Ҫͳ��5�ĸ�������ÿһ�εõ�һ��0.������һ��5�Ĵ��ڡ�
	//������Ϊ2��5�������0.����10���Բ�⡣Ȼ���г����2������ͳ�ƿ���Լ������5�ĸ�����
    public int trailingZeroes(int n) {
        return (n/=5) > 0 ? n + trailingZeroes(n) : 0;        
    }
    
    
    
}
