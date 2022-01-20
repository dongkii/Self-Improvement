package sample;

import java.util.*;

public class Programmers_Report_results {
    public static int[] solution(String[] id_list, String[] report, int k) {
        /// 이용자의 ID 배열
        // 정지 기준이 되는 신고횟수 k
        // 각 이용자가 신고한 이용자의 ID정보가 담긴 배열
    	// report 배열은 "유저id 신고자id" 형식
        
    	int[] answer = new int[id_list.length];     // 결과
    	
    	Map<String, HashSet<String>> reportList = new HashMap<String, HashSet<String>>();
    	Map<String, Integer> mailCount = new HashMap<String, Integer>();
    	
    	// 중복제거
    	Set<String> set = new HashSet<String>(Arrays.asList(report));
    	report = set.toArray(new String[0]);
    	
    	//신고 리스트 및 메일카운트 초기화
    	for(String user : id_list) {
    		mailCount.put(user, 0);
    		reportList.put(user, new HashSet<String>());
    	}
    	
    	// 신고당한ID[신고한ID, 신고한ID, ,,,]
    	for(String rept : report) {
    		String[] tmp = rept.split(" ");
    		reportList.get(tmp[1]).add(tmp[0]);
    	}
    	
    	
    	//  
    	for(int i = 0; i < id_list.length; i++) {
    		if(reportList.get(id_list[i]).size() >= k) {
    			for(String b : reportList.get(id_list[i])) {
    				mailCount.put(b, mailCount.get(b)+1);
    			}
    		}
    	}
    	
    	for(int j = 0; j < id_list.length; j++) {
    		answer[j] = mailCount.get(id_list[j]);
    	}
        
        return answer;
    }

    public static void main(String[] args) {
    	System.out.println("실행 시작");
    	
    	String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int[] result1 = {2, 1, 1, 0};
        result(solution(id_list, report, 2), result1, 1);
        
        String[] id_list2 = {"con", "ryan"};
        String[] report2 = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int[] result2 = {0, 0};
        result(solution(id_list2, report2, 3), result2, 2);
        
        System.out.println("실행 종료");
    }
    
    public static void result(int[] result, int[] answer, int num) {
    	String resultMsg = "";
    	int i = 0;
        for(i = 0; i < answer.length; i++) {
        	if(result[i] != answer[i]) {
                break;
            }
        }
        
        if(i == answer.length) {
        	resultMsg = num + " : 성공";
        } else {
        	resultMsg = num + " : 실패";
        }
        
        System.out.println(resultMsg);
    }
}