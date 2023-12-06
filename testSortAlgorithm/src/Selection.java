public class Selection extends SortingTemplate{

    public static void sort(double[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (a[min] > a[j]) {
                    min = j;
                }//if less a[j]
                exch(a, i, min);
            }//for int j
        }//for int i
    }//public static sort

    public static void main(String[] args) {
        double[] data = {5, 4, 2, 0, 1};
        sort(data);
    }
}
