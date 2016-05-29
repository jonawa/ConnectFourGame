package ConvertArrays;


import java.util.*;

public class BAPArrayas {
    public static void main(String[] args) {
        int [][] x = {{0,0,1,1},{1,1,2,1},{2,1,2,1},{1,2,1,2},{1,2,1,2}};
        
        System.out.println("x:");
        ShowArray(x);
        
<<<<<<< HEAD
        int[] y;
        y = ConvertArray (x);
        
        System.out.println(Arrays.toString(y)); 
        
=======
>>>>>>> 8fcd55fc3b8f83f3da7638cbf75ca8480cf8e465
        }
    
public static int [] ConvertArray (int [][] array){
    
<<<<<<< HEAD
  int height = array.length;
  int width = array[0].length;

  int[][] MatrixOfOnes = new int[height][width];
  int[][] MatrixOfTwoes = new int[height][width];

  for (int i = 0; i < height; i++) {
    for (int j = 0; j < width; j++) {    
          if (array[i][j] == 1) {MatrixOfOnes[i][j] = 1;}
     else if (array[i][j] == 2) {MatrixOfTwoes[i][j] = 1;}
    }
  }
  MatrixOfOnes = Transpose(MatrixOfOnes);
  MatrixOfTwoes = Transpose(MatrixOfTwoes);
  
    int [] BinaryCodeOnes = new int [MatrixOfOnes.length];
    int [] BinaryCodeTwoes = new int [MatrixOfTwoes.length];
    
    for (int k = 0; k < MatrixOfOnes.length ; k++) {
        for (int l = MatrixOfOnes[0].length-1; l >=0 ; l--) {
            BinaryCodeOnes[k] += ( ((int) Math.pow(2, MatrixOfOnes[0].length - l - 1) ) * MatrixOfOnes[k][l]);
            BinaryCodeTwoes[k] += ( ((int) Math.pow(2, MatrixOfOnes[0].length - l -1) ) * MatrixOfTwoes[k][l]);
        }
    }
    int [] BinaryCode = new int [BinaryCodeOnes.length + BinaryCodeTwoes.length];
    System.arraycopy(BinaryCodeOnes, 0, BinaryCode, 0, BinaryCodeOnes.length);
    System.arraycopy(BinaryCodeTwoes, 0, BinaryCode, BinaryCodeOnes.length, BinaryCodeTwoes.length);
    return BinaryCode;
    }
    
 public static int[][] Transpose (int[][] array) {
  if (array == null || array.length == 0)//empty or unset array, nothing do to here
    return array;

  int width = array.length;
  int height = array[0].length;

  int[][] array_new = new int[height][width];

  for (int i = 0; i < width; i++) {
    for (int j = 0; j < height; j++) {
      array_new[j][i] = array[i][j];
    }
  }
  return array_new;
=======
    	int width = array.length;
    	int height = array[0].length;

    	int[][] array_1 = new int[height][width];
    	int[][] array_2 = new int[height][width];

    	for (int i = 0; i < width; i++) {
    		for (int j = 0; j < height; j++) {    
    				if (array[i][j] == 1) 
    					{array_1[i][j] = 1;}
    				else if (array[i][j] == 2) 
    					{ array_2 [i][j] = 1;}
    			}
    	}
    	array_1 = Transpose(array_1);
    	array_2 = Transpose(array_2);
  
    	int l = array_1[0].length;
    	int sum1 = 0;
    	int sum2 = 0;
    	int [] A_1 = new int [array_1.length];
    	int [] A_2 = new int [array_2.length];
  
        for (int k = 0; k < array_1.length ; k++) {
            while ((l-1) >= 0 ) {
            	sum1 += ( ((int) Math.pow(2, l) ) *array_1[k][l]);
            	sum2 += ( ((int) Math.pow(2, l) ) *array_2[k][l]);
                l--;        
            }
            A_1 [k] = sum1;
            A_2 [k] = sum2;
            sum1 = 0;
            sum2 = 0;
        }
  
        ShowArray(array_1);
        ShowArray(array_2);
  
        System.out.println(Arrays.toString(A_1));
        System.out.println(Arrays.toString(A_2));  
  }
    
    public static int[][] Transpose (int[][] array) {
    	
	  if (array == null || array.length == 0)//empty or unset array, nothing do to here
		  return array;
	
	  int width = array.length;
	  int height = array[0].length;
	
	  int[][] array_new = new int[height][width];
	
	  for (int i = 0; i < width; i++) {
		  for (int j = 0; j < height; j++) {
			  array_new[j][i] = array[i][j];
	    }
	  }
	  return array_new;
>>>>>>> 8fcd55fc3b8f83f3da7638cbf75ca8480cf8e465
}    
    
    public static void ShowArray (int [][] A){
        for (int i = 0; i < A.length; i++){
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println("");
        }
    }
    }
