package sample;

import java.util.*;

import org.omg.Messaging.SyncScopeHelper;

public class Programmers_Report_results {
    public static int[] solution(String[] id_list, String[] report, int k) {
        /// �̿����� ID �迭
        // ���� ������ �Ǵ� �Ű�Ƚ�� k
        // �� �̿��ڰ� �Ű��� �̿����� ID������ ��� �迭
    	// report �迭�� "����id �Ű���id" ����
        int[] answer = {2, 1, 1, 1};
        
        HashSet<String> set = new HashSet<String>();
        for(int i=0; i<report.length; i++) {
        	set.add(report[i]);
        }
        
        return answer;
    }

    public static void main(String[] args) {
    	System.out.println("���� ����");
    	
    	String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int[] result1 = {2, 1, 1, 1};
        result(solution(id_list, report, 2), result1, 1);
        
        String[] id_list2 = {"con", "ryan"};
        String[] report2 = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int[] result2 = {0, 0};
        result(solution(id_list2, report2, 3), result2, 2);
        
        System.out.println("���� ����");
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
        	resultMsg = num + " : ����";
        } else {
        	resultMsg = num + " : ����";
        }
        
        System.out.println(resultMsg);
    }
}