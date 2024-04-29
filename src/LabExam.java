/* 
class LabExam{
    public static void main(String[] args) {
        
    
    char[][] answer = {
        { 'C','C','D','A','B','A','A','E','A','A'},
        {'D','B','D','C','A','E','D','A','A','D'},
        {'C','B','D','D','A','E','A','E','A','D'},
        {'D','B','D','B','D','D','E','E','A','C'},
        {'D','B','D','B','D','D','E','C','B','D'},
        {'D','B','D','C','D','D','E','E','A','D'},
        {'D','B','D','B','A','E','D','A','A','D'},
        {'A','B','D','B','A','E','D','A','B','C'}
    };
    char[] keys = {'D','B','D','C','C','E','E','A','A','D'};
    for(int i = 0; i<answer.length; i++){
        int correctcount = 0;
        for(int j = 0; j< answer[i].length; j++){
            if (answer[i][j] == keys[j]){
                correctcount++;
            }
            
        }
        System.out.println("Student "+ i + " corrrect count will be "+ correctcount);
    } 
}
}
*/
import java.util.LinkedList;

public class LabExam {
    public static void main(String[] args) {
        // Given matrix
        int[][] matrix = {
                {10, 21, 45, 32},
                {12,45,15,17},
                {8,9,19,42},
                {33,13,16,33},
                {4,23,11,22}
        };

       
        int numOfRows = matrix.length;
        int[] SumOfRows = new int[numOfRows];

        for (int i = 0; i < numOfRows; i++) {
            LinkedList<Integer> rowList = new LinkedList<>();
            int sum = 0;

            for (int j = 0; j < matrix[i].length; j++) {
                rowList.add(matrix[i][j]);
                sum += matrix[i][j];
            }

            SumOfRows[i] = sum;

           
            System.out.print("Row " + (i + 1) + ": ");
            for (int value : rowList) {
                System.out.print(value + " ");
            }
            System.out.println("Sum: " + sum);
        }

        
        System.out.print("Row Sums: ");
        for (int sum : SumOfRows) {
            System.out.print(sum + " ");
        }
    }
}

