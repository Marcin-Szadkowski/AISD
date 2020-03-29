package sortowanie;

/**
 * Klasa implementujaca algorytm Quick sort
 * 
 */
class QuickSort extends Sort{
    public void doSort(){
        startSorting(); //Start pomiaru czasu
        quickSort(0, dataLength()-1);
        stopSorting(); //Koniec pomiaru czasu 
    }
    private int partition(int low, int high) { 
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) { 
            // Jezeli element jest mniejszy niz pivot to je zamien
            if (compare(j, high) < 0) { 
                i++; 
                swap(i, j);
            } 
        } 
        swap(i+1, high);
        return i+1; 
    }
    private void quickSort(int low, int high) { 
        if (low < high) 
        { 
            int pi = partition(low, high); 
            //Rekurencyjnie sortujemy tablice
            quickSort(low, pi-1); 
            quickSort(pi+1, high); 
        }
    }  
}