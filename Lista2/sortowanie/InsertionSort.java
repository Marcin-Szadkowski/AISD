package sortowanie;

/**
 * Klasa implementujaca algorytm Insertion Sort
 */
class InsertionSort extends Sort{

    public void doSort(){
        startSorting(); //Zaczynamy liczyc czas algorytmu
        for(int i =0; i < dataLength(); i++){
            int j = i;
            while( j > 0 ){
                if( compare(j-1, j) > 0)
                    swap(j -1, j);
                else
                    break;
                j--;
            }
        }
        stopSorting();
    }
}