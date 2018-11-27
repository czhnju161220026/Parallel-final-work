public class NormalMergeSort {
    private static void merge(int[] array,int begin,int mid,int end) {
        int []b = new int[end-begin+1];
        int i = begin,j=mid+1;
        int p = 0;
        while(i <= mid && j <= end) {
            if(array[i] < array[j]) {
                b[p] = array[i];
                p++;
                i++;
            }
            else {
                b[p] = array[j];
                p++;
                j++;
            }
        }
        if(i>mid) {
            while(j<=end) {
                b[p] = array[j];p++;j++;
            }
        }
        else {
            while(i<=mid) {
                b[p] = array[i];p++;i++;
            }
        }
        for(i = begin;i<=end;i++) {
            array[i] = b[i-begin];
        }
    }
    private static void myMergeSort(int[] array,int begin, int end) {
        if(begin < end) {
            int mid = (begin+end)/2;
            myMergeSort(array,begin,mid);
            myMergeSort(array,mid+1,end);
            merge(array,begin,mid,end);
        }
    }
    public static float sort() {
        long startTime = System.nanoTime();
        myMergeSort(Data.resultList,0,Data.length-1);
        long endTime = System.nanoTime();
        return (float) (endTime-startTime)/1000000;
    }
}
