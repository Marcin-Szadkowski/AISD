import java.util.Arrays;

/**
 * Klasa implementujaca algorytm SELECT z wykladu
 */
class LinearSelect extends Select {

    @Override
    protected void doFind() {
        //Zwroc wynik dla statystyki
        setResult(linearSelect(values, 0, dataLength()-1, k));
    }
    /**
     * Rekurencyjna funkcja implementujaca algorytm SELECT
     * @param tab
     * @param low
     * @param high
     * @param k
     * @return k-ty najmniejszy element
     */
    private int linearSelect(int[] tab, int low, int high, int k){
        //Sprawdzenie czy k jest z przedzialu
        if(k > 0 && k <= high - low +1){
            //Obliczenie liczby elementow w tablicy
            int n = high - low +1;
            //Pomocniczy indeks do podzialu tablicy na podtablice 
            int i;

            //Tablica median
            int[] medians = new int[(n+4)/5];
            for(i=0; i<n/5; i++){
                medians[i] = findMedian(tab, low + i*5, 5);
            }
            if(i*5 < n){
                medians[i] = findMedian(tab, low + i*5, n%5);
                i++;
            }
            int medianOfMedians;
            //Wyznaczenie mediany median
            if(i == 1)
                medianOfMedians = medians[i - 1];
            else
                medianOfMedians = linearSelect(medians, 0, i - 1, i/2+1);
            
            int position = partition(tab, low, high, medianOfMedians);

            if(position - low == k - 1)
                return tab[position];
            if(position -low > k-1)
                return linearSelect(tab, low, position -1, k);
            
            return linearSelect(tab, position +1 , high, k - position + low -1);
        }
        //W przeciwnym wypadku zwroc -1
        return -1;
    }
    /**
     * Funkcja znajdujaca mediane tablicy
     * @param tab tablica
     * @param indeks poczatkowy
     * @param liczba elementow (dla tego algorytmu to zawsze 5)
     */
    private int findMedian(int[] tab, int i, int n){
        insertionSort(tab, i, i+n);
       
        System.err.println("Mediana: "+ tab[i+n/2]);             
        return tab[i + n/2];
    }
    /**
     * Pomocniczna funkcja do sortowanie podtablic
     * @param i indeks poczatkowy
     * @param j indeks koncowy
     */
    private void insertionSort(int[] tab, int i, int j){
        int left = i;
        for(; i< j; i++){
            int k = i;
            while( k > left ){
                if(compareElements(tab[k-1], tab[k]) > 0)
                    swap(tab, k-1, k);
                else
                    break;
                k--;
            }
        }
    }
    /**
     * Funkcja dokonujaca podzialu tablicy na podstawie podanego x (w programie mediany)
     * @param tab tablica
     * @param low indeks poczatkowy
     * @param high indeks koncowy
     * @param x indeks wzgledem ktorego dzielimy
     * @return
     */
    private int partition(int[] tab, int low, int high, int x){
        int i;
        for(i=low ; i< high; i++){
            if(tab[i] == x)
                break;
        }
        swap(tab, i, high);

        i = low;
        for(int j = low; j< high; j++){
            if(tab[j] <= x ){
                swap(tab, i, j);
                i++;
            }
        }
        swap(tab, i, high);
        return i;
    }
    /**
     * Zamiana elementow w tablicy
     * @param tab
     * @param k
     * @param l
     * @return
     */
    private int[] swap(int[] tab, int k, int l){
        System.err.println("Zamiana indeksow: "+k+" oraz "+l);
        curStat.swapCnt++;
        int temp = tab[k];
        tab[k] = tab[l];
        tab[l] = temp;
        return tab;
    }
}