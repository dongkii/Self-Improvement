package sample;

import java.util.*;

public class Programmers_Report_results {
    public static int[] solution(String[] id_list, String[] report, int k) {
        /// 이용자의 ID 배열
        // 정지 기준이 되는 신고횟수 k
        // 각 이용자가 신고한 이용자의 ID정보가 담긴 배열
    	// report 배열은 "유저id 신고자id" 형식
        
        // 풀이
        // 신고 리스트를 셋으로 전환 (중복을 제거하기 위해)
        Set<String> set = new HashSet<String>(Arrays.asList(report));

        // 중복이 제거된 셋을 다시 배열로 전환
        String[] report_ = set.toArray(new String[0]);

        int[] reportCnt = new int[id_list.length];  // 신고당한 횟수 카운트용
        int[] answer = new int[id_list.length];     // 결과
        String[] result = new String[id_list.length];   // 신고한 사람 카운트용

        // 신고한 id의 카운트를 세기위한 이중포문
        for(int i = 0; i < id_list.length; i++) {
            for(int j = 0; j < report_.length; j++) {
                if(id_list[i].equals(report_[j].split(" ")[1])) {   // 신고한id의 인덱스를 구하기위해
                    reportCnt[i] += 1;      // 신고당한id 카운트
                    
                    // 신고한 사람을 저장
                    if(result[i] == null) {
                        result[i] = report_[j].split(" ")[0];
                    } else {
                        result[i] += " " + report_[j].split(" ")[0];
                    }
                }
            }
        }

        for(int t = 0; t < id_list.length; t++) {
            if(reportCnt[t] >= k) {
                for(int q = 0; q < result[t].split(" ").length; q++) {
                    for(int w = 0; w < id_list.length; w++) {
                        if(id_list[w].equals(result[t].split(" ")[q])) {
                            answer[w] += 1;
                        }
                    }
                }
            }
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