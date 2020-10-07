/**
 * Klasa zbierajaca statystyki na temat algorytmu
 */
final class Statistics{
    //Rozmiar tablicy, liczba porownan, liczba zamian
    public long dataSize, compareCnt, swapCnt;
    public int kIndex;

    //Inicjalizacja danych
    public void init(int dataSize){
        this.dataSize = dataSize;
        compareCnt = swapCnt = 0;
    }
    //Funkcja wyswietlajaca informacje, gdy te potrzebne sa na ekranie
    public String toString(){
        return "n: "+dataSize + " c: "+compareCnt+" s: "+swapCnt+" k: "+kIndex; 
    }
    //Funkcja wyswietlaja informacje, gdy te sa zapisywane do pliku
    public String toStringFF(){
        return ""+ dataSize+"\t"+compareCnt+"\t" +swapCnt;
    }
   
}