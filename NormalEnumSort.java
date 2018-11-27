public class NormalEnumSort {
    private static void myEnumSort(int []srcArray,int[] targetArray,int length) {
        for(int i = 0;i<length;i++) {
            int index = 0;
            for(int j = 0;j<length;j++) {
                if(srcArray[j]<srcArray[i]) {
                    index++;
                }
            }
            targetArray[index] = srcArray[i];
        }
    }
    public static float sort() {
        long startTime = System.nanoTime();
        myEnumSort(Data.rawData, Data.resultList, Data.length);
        long endTime = System.nanoTime();
        return (float) (endTime-startTime)/1000000;
    }
}
