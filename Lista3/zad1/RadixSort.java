import java.util.Arrays;

class RadixSort extends Sort{

    @Override
    protected void doSort() {
        startSorting();
        int max = findMax();

        for(int exp = 1; max/exp > 0; exp *= 10){
            countSort(exp);
        }
        stopSorting();
    }
    /**
     * Pomocnicza funkcja sortujaca liczby algorytmem counting sort
     * dla zadanego wykladnika
     * @param exp
     */
    private void countSort(int exp){
        int result[] = new int[dataLength()];
        int countTab[] = new int[20];
        //Wyzerowanie tablicy countTab
        Arrays.fill(countTab, 0);

        /**
         * Przypisanie licznosci wystepujacym elementom
         */
        for(int i=0; i < dataLength(); i++){
            countTab[(copy(i)/exp) %10 +10]++;
            curStat.addCnt++;
        }
        /**
         * Po tej operacji element countTab[i] bedzie
         * zawierac pozycje w posortowanej tablicy ostatniego elementu
         * o kluczu i
         */
        if(order.equals(">=")){
            for(int i=19; i>0; i--){
                countTab[i-1] += countTab[i];
                curStat.addCnt++;
            }
        }else{
            for(int i=1; i<20; i++){
                countTab[i] += countTab[i-1];
                curStat.addCnt++;
            }
        }   
            
        /**
         * Obliczenie pozycji w tablicy wynikowej
         */
        for(int i = dataLength() -1; i>=0; i--){
            result[countTab[(copy(i)/exp)%10 + 10] -1] = copy(i);
            countTab[(copy(i)/exp)%10 + 10]--;
            curStat.addCnt++;
            curStat.setCnt++;
        }
        /**
         * Przepisanie wyniku do tablicy values[] w nadklasie
         */
        for(int i =0; i < dataLength(); i++){
            set(i, result[i]);
        }
    }
    /**
     * Funkcja znajdujaca maksymalna wartosc w tablicy
     */
    int findMax(){
        int max = copy(0);
        for(int i=0; i < dataLength(); i++){
            if(copy(i) > max)
                max = copy(i);
        }
        return max;
    }
}