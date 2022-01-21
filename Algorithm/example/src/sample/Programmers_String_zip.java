package sample;

import java.util.*;

public class Programmers_String_zip {
	public static int solution(String s) {
		System.out.println("�Է°� : \"" + s + "\" ���ڿ� ���� : " + s.length());
		
		List<Integer> list = new ArrayList<Integer>(); 
		
		
        for(int i = 1; i < s.length()+1; i++) {
        	int share = s.length()/i;
        	int mod = s.length()%i;
        	
        	Stack<String> st = new Stack<String>();
        	String result = "";
        	
        	if(mod == 0) {
        		String prevStr = ""; 	// �� �񱳸� ���� ��Ʈ��
        		int j = 0;
        		while(j < share) {
        			int tp = j+i;
        			String strTmp = s.substring(j, tp);
        			if(st.size() == 0 || prevStr.equals(strTmp)) {
        				st.push(strTmp);
        			} else {
        				if(st.size() == 1) {
        					result += st.pop();
        				} else {
        					result += st.size() + st.pop();
        				}
        				
        				st.clear();
        				st.push(strTmp);
        			}
        			prevStr = strTmp;
        		}
        		
    		    if(st.size() == 1) {
    				result += st.pop();
    			} else {
    				result += st.size() + st.pop();
    			}
    	        
            	list.add(result.length());
            	System.out.println(i + "���� �ڸ� �� : " + result);
        	}
        	
        	
        }
        
        Arrays.sort(list.toArray());
        
		int answer = list.get(0);
        
        System.out.println("��� : " + answer);
        return answer;
    }
	
	public static void main(String[] args) {
		String[] inputStr = {"aabbaccc", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd"};
		int[] result = {7, 9, 8, 14, 17};
		
		for(int i = 0; i < inputStr.length; i++) {
			if(solution(inputStr[i]) == result[i]) {
				System.out.println("����\n");
			} else {
				System.out.println("����\n");
			}
		}
	}
}
