package sample;

import java.util.*;

public class Programmers_String_zip {

	public static int solution(String s) {
		int[] len = new int[s.length()];	// 각 문자열을 i개로 잘랐을 때 문자열 길이를 체크하기 위한 배열
		
        for(int i = 1; i < s.length()+1; i++) {        	
        	String result = "";		// 압축된 문자열을 저장하기 위한 변수
			String prevStr = ""; 	// 값 비교를 위한 스트링
        	int cnt = 0;

        	for(int j = 0; j < s.length(); j+=i) {
                String strTmp = "";
                if(j+i > s.length()) {			// 서브스트링의 EndIndex가 문자열의 길이를 넘어가지 않도록
                    strTmp = s.substring(j);	// j 부터 마지막 인덱스까지
				} else {
                    strTmp = s.substring(j, j+i);	// j 부터 j+i 까지
                }
        		
        		if(cnt == 0 || prevStr.equals(strTmp)) {	// 이전값과 비교해서 이전값과 같으면 cnt 값 증가
                   cnt++;
        		} else {	// 이전 서브스트링 값과 다르면
        			if(cnt == 1) {	// 1은 생략
        				result += prevStr;
        			} else {		// 압축된 문자열 갯수 + 압축된 문자열
        				result += cnt + prevStr;
        			}
        			cnt = 1;	// result에 이전 데이터 값이 저장되니 cnt는 1로 초기화
        		}
        		prevStr = strTmp;
        	}
                
            if(cnt == 1) {		// 위에서 마지막 for문이 실행된 후 result에 저장되지 못한 문자열 저장
                result += prevStr;
			} else {
            	result += cnt + prevStr;
            }
    	        
            len[i-1] = result.length();		// len 배열에 result 문자열의 길이 저장
        }        
        
        Arrays.sort(len);		// 압축된 문자열중 가장 짧은 길이를 가져오기위해 sort 후 0번째 인덱스(오름차순이라 가장작은값이 0번째에)
        
		int answer = len[0];
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