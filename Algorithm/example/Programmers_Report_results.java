import java.io.*;
import java.util.*;

public class Programmers_Report_results {
    public static int[] solution(String[] id_list, String[] report, int k) {
        // 이용자의 ID 배열
        // 정지 기준이 되는 신고횟수 k
        // 각 이용자가 신고한 이용자의 ID정보가 담긴 배열

        int[] answer = {};
        return answer;
    }

    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int[] case1 = solution(id_list, report, 2);
        
        String[] id_list2 = {"con", "ryan"};
        String[] report2 = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int[] case2 = solution(id_list2, report2, 3);
    }
}