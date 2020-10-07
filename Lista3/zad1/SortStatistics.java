
/**
 * Klasa zbierajaca informacje na temat sortowania
 */
final class SortStatistics{
    private static final long MEGABYTE = 1024L * 1024L;
    //Rozmiar tablicy, liczba porownan, liczba zamian
    public long dataSize, compareCnt, swapCnt, addCnt, setCnt;
    //Czas sortowania
    public double time;
    private long startTime, stopTime;
    //Zmiene do pomiaru pamieci
    Runtime runtime;
    private long beforeMem, usedMemory;

    //Inicjalizacja danych
    public void init(int dataSize){
        this.dataSize = dataSize;
        compareCnt = swapCnt = addCnt = setCnt = 0;
        time = 0;
    }
    //Funkcja wyswietlajaca informacje, gdy te potrzebne sa na ekranie
    public String toString(){
        return "n: "+dataSize + " a: "+addCnt+" s: "+setCnt+" t: "+time+" m: "+usedMemory; 
    }
    //Funkcja wyswietlaja informacje, gdy te sa zapisywane do pliku
    public String toStringFF(){
        return ""+ dataSize+"\t"+addCnt+"\t" +setCnt+"\t"+time+"\t" + usedMemory;
    }
    //Start pomiaru czasu i pamieci
    public void startTime(){
        startTime = System.nanoTime();
        runtime = Runtime.getRuntime();
        //garbage collector
        runtime.gc();
        beforeMem = runtime.totalMemory() - runtime.freeMemory();
    }
    //Koniec pomiaru czasu i zuzycia pamieci
    public void stopTime(){
        stopTime = System.nanoTime();
        time = stopTime - startTime;
        time /= 1000000; //Wyswietlanie w milisekundach
        usedMemory = runtime.totalMemory() - runtime.freeMemory() - beforeMem;
        //usedMemory = bytesToMegabytes(usedMemory);
    }
}