import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Data {
    public static int length=0;
    public static int[] rawData = new int[length];
    public static int[] resultList= new int[length];

    public static void init() throws IOException {
        length = 30000;
        rawData = new int[30000];
        resultList = new int[30000];
        Scanner scanner = new Scanner(new File("random.txt"));
        int count = 0;
        while(scanner.hasNextInt()) {
            rawData[count] = scanner.nextInt();
            resultList[count] = rawData[count];
            count++;
        }
    }

    public static void fresh() {
        for(int i = 0;i<length;i++) {
            resultList[i] = rawData[i];
        }
    }

    public static void outputFile(String filename)throws IOException{
        FileWriter fout = new FileWriter(filename);
        BufferedWriter bufferedWriter= new BufferedWriter(fout);
        for(int i = 0;i<length;i++) {
            //System.out.println("Wirter number"+i+": "+rawData[i]);
            bufferedWriter.write(""+resultList[i]+" ");
        }
        bufferedWriter.flush();
    }
}
