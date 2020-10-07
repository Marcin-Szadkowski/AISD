class BinarySearch{
    //Tablica danych
    private int[] values;
    //Obiekt przechowujacy statystyki metody
    Statistics curStat = new Statistics();

    /**
     * Metoda inicjujaca poszukiwania
     * @param data tablica do przeszukania
     * @param key szukany klucz
     * @return statystyki metody
     */
    public Statistics doSearch(int[] data, int key){
        //Przypisanie danych
        values = data;
        //Inicjalizacja obiektu Statistics
        curStat.init(data.length);
        //Obliczenie wyniku
        curStat.result = binarySearch(0, data.length -1 , key);
        //Zwroc statystyki
        return curStat;
    }
    /**
     * Rekurencyjna metoda poszukiwania klucza
     */
    int binarySearch(int low, int high, int key){
        if(high >= low){
            int middle = low + (high - low) / 2;

            //Jezeli element pod indeksem middle jest rowny kluczowi to zwroc 1
            if(compareElements(values[middle], key) == 0)
                return 1;
            if(compareElements(values[middle], key) > 0 )
                return binarySearch(low, middle -1, key);
            else
                return binarySearch(middle + 1, high, key);
        }
        //Nie znaleziono elementu, zwroc 0
        return 0;
    }
     //Funkcja, ktora porownuje podane elementy
     protected final int compareElements(int k, int w){
        //Zliacznie porownan
        curStat.compareCnt++;
        //System.err.println("Comparison");
        if(k==w)
            return 0;
        else
            return ( k < w ? -1 : 1);

    }
}