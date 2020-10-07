/**
 * Klasa zbierajaca statystyki na temat algorytmu
 */
final class Statistics{
    //Rozmiar tablicy, liczba porownan, liczba zamian
    public long dataSize, compareCnt;
    int result;

    //Inicjalizacja danych
    public void init(int dataSize){
        this.dataSize = dataSize;
        compareCnt = result = 0;
    }
    //Funkcja wyswietlajaca informacje, gdy te potrzebne sa na ekranie
    public String toString(){
        return "n: "+dataSize + " c: "+compareCnt+" Y/N: "+ result; 
    }
    //Funkcja wyswietlaja informacje, gdy te sa zapisywane do pliku
    public String toStringFF(){
        return ""+ dataSize+"\t"+compareCnt;
    }
   
}