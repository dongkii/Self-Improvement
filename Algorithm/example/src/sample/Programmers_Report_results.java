package sample;

public class Programmers_Report_results {
    public static int[] solution(String[] id_list, String[] report, int k) {
        /// �̿����� ID �迭
        // ���� ������ �Ǵ� �Ű�Ƚ�� k
        // �� �̿��ڰ� �Ű��� �̿����� ID������ ��� �迭

        int[] answer = {};
        return answer;
    }

    public static void main(String[] args) {
    	System.out.println("���� ����");
    	
    	String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int[] case1 = solution(id_list, report, 2);
        
        String[] id_list2 = {"con", "ryan"};
        String[] report2 = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int[] case2 = solution(id_list2, report2, 3);
        
        System.out.println("���� ����");
    }
}