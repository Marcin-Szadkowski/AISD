package sortowanie;

/**
 * Klasa abstrakcyjna, po ktorej dziedzica klasy,
 * ktore implementuja dokladna metode sortowania
 */
abstract class Sort{
    //Tablica danych
    private int[] values;
    //Porzadek sortowania
    private String order = "<=";
    //Statystyki sortowania
    private SortStatistics curStat = new SortStatistics();

    /**
     * Glowna funkcja klasy sort. Zwraca statyski na temat sortowania
     * @param data
     * @param order
     * @return
     */
    public final SortStatistics sort(int[] data, String order){
        values = data;
        this.order = order;
        curStat.init(data.length);
        /**
         * Wywolanie funkcji sortujacej,
         * ktorej dokladna implementacja znajduje sie w podklasie
         */
        doSort();
        return curStat;
    }
    protected final int dataLength(){
        return values.length;
    }
    //Funkcja porownujaca elementy w tablicy o podanych indeksach
    protected final int compare(int i, int j){
        //Zliczanie porownan
        curStat.compareCnt++;
        //System.err.println("Comparison");
        int v1 = values[i];
        int v2 = values[j];
        if (v1==v2)
            return 0;
        else 
            if(order.equals("<="))
                return (v1 < v2 ? -1 : 1);
            else
                return ( v1 > v2 ? -1 : 1);
    }
    //Funkcja zamieniajaca elementy w tablicy o podanych indeksach
    protected final void swap(int i, int j){
        //Zliczanie zamian
        curStat.swapCnt++;
        //System.err.println("Swap");
        int tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }
    //Funkcja, ktora porownuje podane elementy
    protected final int compareElements(int k, int w){
        //Zliacznie porownan
        curStat.compareCnt++;
        //System.err.println("Comparison");
        if(k==w)
            return 0;
        else
            if(order.equals("<="))
                return ( k < w ? -1 : 1);
            else
                return (k > w ? -1 : 1);
    }
    //Funkcja zwracaja wartoscz tablicy o podanym indeksie
    protected final int copy(int i){
        return values[i];
    }
    protected final void set(int i, int value){
        curStat.swapCnt++;
        //System.err.println("Swap");
        values[i] = value;
    }
    protected final void startSorting(){
        curStat.startTime();
    }
    protected final void stopSorting(){
        curStat.stopTime();
    }
    protected abstract void doSort();
}