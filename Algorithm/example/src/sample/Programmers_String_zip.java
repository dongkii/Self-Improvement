package sample;

import java.util.*;

public class Programmers_String_zip {
	public static int solution(String s) {
		System.out.println("입력값 : \"" + s + "\" 문자열 길이 : " + s.length());
		
		List<Integer> list = new ArrayList<Integer>(); 
		
		
        for(int i = 1; i < s.length()+1; i++) {
        	int share = s.length()/i;
        	int mod = s.length()%i;
        	
        	Stack<String> st = new Stack<String>();
        	String result = "";
        	
        	if(mod == 0) {
        		String prevStr = ""; 	// 값 비교를 위한 스트링
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
            	System.out.println(i + "개로 자를 때 : " + result);
        	}
        	
        	
        }
        
        Arrays.sort(list.toArray());
        
		int answer = list.get(0);
        
        System.out.println("기댓값 : " + answer);
        return answer;
    }
	
	public static void main(String[] args) {
		String[] inputStr = {"aabbaccc", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd"};
		int[] result = {7, 9, 8, 14, 17};
		
		for(int i = 0; i < inputStr.length; i++) {
			if(solution(inputStr[i]) == result[i]) {
				System.out.println("성공\n");
			} else {
				System.out.println("실패\n");
			}
		}
	}
}
