public class Selection extends SortingTemplate{

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }//if less a[j]
                exch(a, i, min);
            }//for int j
        }//for int i
    }//public static sort

    public static void main(String[] args) {

    }
}
