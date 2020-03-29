package sortowanie;

/**
 * Klasa zbierajaca informacje na temat sortowania
 */
final class SortStatistics{
    //Rozmiar tablicy, liczba porownan, liczba zamian
    public long dataSize, compareCnt, swapCnt;
    //Czas sortowania
    public double time;
    private long startTime, stopTime;

    //Inicjalizacja danych
    public void init(int dataSize){
        this.dataSize = dataSize;
        compareCnt = swapCnt =0;
        time = 0;
    }
    //Funkcja wyswietlajaca informacje, gdy te potrzebne sa na ekranie
    public String toString(){
        return "n: "+dataSize + " c: "+compareCnt+" s: "+swapCnt+" t: "+time; 
    }
    //Funkcja wyswietlaja informacje, gdy te sa zapisywane do pliku
    public String toStringFF(){
        return ""+ dataSize+"\t"+compareCnt+"\t"+swapCnt+"\t"+time;
    }
    //Start pomiaru czasu
    public void startTime(){
        startTime = System.nanoTime();
    }
    //Koniec pomiaru czasu  
    public void stopTime(){
        stopTime = System.nanoTime();
        time = stopTime - startTime;
        time /= 1000000; //Wyswietlanie w milisekundach
    }
}