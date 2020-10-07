abstract class Select{
    //Tablica danych
    protected int[] values;
    //k-ta statystyka pozycyjna
    int k;
    //Statystyki algorytmu
    protected Statistics curStat = new Statistics();

    /**
     * Glowna funkcja klasy Select
     * @param int[] tablica danych
     * @param k-ta statystyka pozycyjna
     */
    public final Statistics find(int[] data, int k){
        values = data;
        this.k = k;
        curStat.init(data.length);
        /**
         * Wywolanie funkcji szukajacej
         */
        doFind();
        return curStat;
    }
    protected final void setResult(int i){
        curStat.kIndex = i;
    }
    protected final int dataLength(){
        return values.length;
    }//Funkcja porownujaca elementy w tablicy o podanych indeksach
    protected final int compare(int i, int j){
        System.err.println("Porownanie indeksów "+i+ " oraz "+ j);
        //Zliczanie porownan
        curStat.compareCnt++;
        //System.err.println("Comparison");
        int v1 = values[i];
        int v2 = values[j];
        if (v1==v2)
            return 0;
        else 
            return (v1 < v2 ? -1 : 1);
    }
    //Funkcja, ktora porownuje podane elementy
    protected final int compareElements(int k, int w){
        System.err.println("Porownanie elelmentow: "+k+" oraz "+ w);
        //Zliacznie porownan
        curStat.compareCnt++;
        //System.err.println("Comparison");
        if(k==w)
            return 0;
        else
            return ( k < w ? -1 : 1);
    }
    //Funkcja zamieniajaca elementy w tablicy o podanych indeksach
    protected final void swap(int i, int j){
        System.err.println("Zamiana elementow "+ i +" oraz "+ j);
        //Zliczanie zamian
        curStat.swapCnt++;
        //System.err.println("Swap");
        int tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }
    protected final int get(int indeks){
        return values[indeks];
    }
    protected final int partition(int low, int high) { 
        int i = (low-1);
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
    protected abstract void doFind();
}