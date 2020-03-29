package sortowanie;

/**
 * Klasa implementujaca algorytm Merge sort
 */
class MergeSort extends Sort{
    
    public void doSort(){
        startSorting(); //Start pomiaru czasu
        mergeSort(0, dataLength()-1 );
        stopSorting(); //Koniec pomiaru czasu
    }
    /**
     * Rekurencyjna funkcja sortujaca
     */
    void mergeSort(int l, int r) { 
        if (l < r){ 
            //Wyznaczamy srodek
            int m = (l+r)/2; 

            //Rekurencyjnie sortujemy dwie połówki
            mergeSort(l, m); 
            mergeSort(m+1, r); 
            //Scalamy posortowane czesci
            merge(l, m, r); 
        } 
    }
    /**
     * Funkcja scalajaca czesci tablicy
     */ 
    void merge(int l, int m, int r) { 
        //Obliczamy rozmiary tablic do polaczenia
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        //Tablice pomocnicze
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        //Przepisujemy dane do tablic pomocniczych
        for (int i=0; i<n1; ++i) 
            L[i] = copy(l+i);
        for (int j=0; j<n2; ++j) 
            R[j] = copy(m + 1 + j);
  
        int i = 0, j = 0; 
  
        //Nastepuje wlasciwe scalanie
        int k = l; 
        while (i < n1 && j < n2) { 
            if (compareElements(L[i], R[j]) < 0) { 
                set(k, L[i]); 
                i++; 
            } 
            else{ 
                set(k, R[j]);
                j++; 
            } 
            k++; 
        }  
        //Przepisujemy elementy z tablicy L
        while (i < n1) { 
            set(k, L[i]);
            i++; 
            k++; 
        }   
        //Przepisujemy elementy z tablicy R
        while (j < n2) { 
            set(k, R[j]);
            j++; 
            k++; 
        } 
    } 
}