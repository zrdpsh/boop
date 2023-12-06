public class SortingTemplate {
    public static void main(String[] args) {
        //empt
    }

    protected static void exch(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }//exch method

    public static boolean isSorted(double[] a) {
        for (int i = 1; i < a.length; i++) {
            if(a[i] < a[i-1]) {
                return false;
            }//if less
        }//for int i
        return true;
    }//isSorted method
}
