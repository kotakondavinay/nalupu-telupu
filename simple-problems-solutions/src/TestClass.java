import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestClass {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*
         * Read input from stdin and provide input before running
         * Use either of these methods for input
         * */

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        //int N = Integer.parseInt(line);

        //Scanner
       // Scanner s = new Scanner(System.in);
       // int N = s.nextInt();
        int[] values = new int[256];
        long counter = 0;
        for (int i = 0; i < line.length(); i++) {
        	if(values[line.charAt(i)] == 0)
        		values[line.charAt(i)] = 1;
        	else
        		counter++;
        }
        System.out.println(counter);

        //System.out.println("Hello World!");

	}

}
