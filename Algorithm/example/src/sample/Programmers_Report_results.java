package sample;

import java.util.*;

import org.omg.Messaging.SyncScopeHelper;

public class Programmers_Report_results {
    public static int[] solution(String[] id_list, String[] report, int k) {
        /// 이용자의 ID 배열
        // 정지 기준이 되는 신고횟수 k
        // 각 이용자가 신고한 이용자의 ID정보가 담긴 배열
    	// report 배열은 "유저id 신고자id" 형식
        int[] answer = {2, 1, 1, 1};
        
        HashSet<String> set = new HashSet<String>();
        for(int i=0; i<report.length; i++) {
        	set.add(report[i]);
        }
        
        return answer;
    }

    public static void main(String[] args) {
    	System.out.println("실행 시작");
    	
    	String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int[] result1 = {2, 1, 1, 1};
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