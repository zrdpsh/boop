public class SelectionSort extends SortingTemplate{

    public static void sort(double[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }//if less a[j]
            }//for int j
            exch(a, i, min);
        }//for int i
    }//public static sort

    public static void main(String[] args) {
        System.out.println("executing");
    }
}
