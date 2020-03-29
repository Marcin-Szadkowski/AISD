package sortowanie;

/**
 * Klasa implementujaca algorytm Dual Pivot Quick Sort
 */
class DualPivotQuickSort extends Sort{
    public void doSort(){
        startSorting(); //Start mierzenia czasu
        dpQuickSort(0, dataLength() -1);
        stopSorting();  //Koniec mierzenia czasu
    }
    /**
     * Rekurencyjna funkcja sortujaca
     */
    private void dpQuickSort(int low, int high){
        if(low < high){
            int[] pivots = new int[2];
            pivots = partition(low, high);
            dpQuickSort(low, pivots[1] - 1);
            dpQuickSort(pivots[1]+1, pivots[0]-1);
            dpQuickSort(pivots[0] + 1, high);
        }
    }
    /**
     * Funkcja dzielaca tablice
     * @param low
     * @param high
     * @return
     */
    private int[] partition(int low, int high){
        //Tablica na zwracane pivoty
        int[] pivots = new int[2];
        if(compare(low, high) > 0)
            swap(low, high);
        int j = low +1;
        int g = high -1, k = low +1;
        //Lewym pivotem jest element o indeksie low
        //Prawy pivotem jest element o indeksie high
        while( k <= g ){
            //Jezeli element jest mnijeszy niz lewy pivot to zamien
            if(compare(k, low) < 0){
                swap(k, j);
                j++;
            }
            //Jezeli element jest >= od prawego pivota
            else if(compare(k, high) >= 0){
                while(compare(g, high) > 0 && k < g)
                    g--;
                swap(k, g);
                g--;
                if(compare(k, low) < 0){
                    swap(k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;
        //Powrot na pozycje
        swap(low, j);
        swap(high, g);
        pivots[0] = g;
        pivots[1] = j;

        return pivots;
    }
}