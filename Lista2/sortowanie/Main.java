package sortowanie;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * --type dpquick <--- aby wywolac sortowanie Dual Pivot Quick Sort
 */
class Main{
    static int k = 0;
    static String type = null;
    static String order = null;
    static String fileName = null;
    static SortStatistics stats;
    public static void main(String[] args){
        int[] data = {-10, 2, 222, 100, -2, 10, 4};
        
        getInput(args);
        
        //Jezeli nie wybrano zapisu do pliku
        if(fileName == null){
            //Nastepuje sortowanie i pobranie statystyk
            stats = chooseSort(type, order, data);
            //Wyswietlenie danych
            System.err.println(stats.toString());
            for(int i =0; i < data.length; i++){
                System.out.print(data[i]+ "\t");
            }
            System.out.println();
        }else{
            //Opcja z zapisem danych do pliku
            PrintWriter writer;
            try{
                writer = new PrintWriter(fileName, "UTF-8");
                writer.println("n:\t c:\t s:\t t:");
                for(int n = 100; n <= 10000; n +=100){
                    for(int i =0; i < k; i++){
                        stats = chooseSort(type, order, genRandData(n));
                        writer.println(stats.toStringFF());
                    }
                }
                writer.close();              
            } catch(FileNotFoundException ex){

            } catch(UnsupportedEncodingException ex){

            }    
        }
    }
    //Funkcja odczytujaca dane z parametrow wejsciowych
    private static void getInput(String[] args){
        ArrayList<String> list = new ArrayList<String>();

        for(int i = 0; i < args.length; i++){
            list.add(args[i]);
        }
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String param = iterator.next();
            try{
                switch(param){
                    case "--type":
                        type = iterator.next();
                        break;
                    case "--comp":
                        order = iterator.next();
                        break;
                    case "--stat":
                        fileName = iterator.next();
                        k = Integer.parseInt(iterator.next());
                        break;
                }
            } catch(NoSuchElementException ex){
                System.err.println("Nieprawidlowe parametry");
                System.exit(0);
            } catch(NumberFormatException ex){
                System.err.println("Blad podczas parsowania");
                System.exit(0);
            }           
        }
    }
    /**
     * Funkcja, ktora wybiera implementacje odpowiedniego algorytmu
     * na podstawie parametrow wejsciowych
     */
    private static SortStatistics chooseSort(String type, String order, int[] data){
        Sort sort;
        SortStatistics stats;

        switch(type){
            case "insert":
                sort = new InsertionSort();
                break;
            case "merge":
                sort = new MergeSort();
                break;
            case "quick":
                sort = new QuickSort();
                break;
            case "qpquick":
                sort = new DualPivotQuickSort();
                break;
            default:
                sort = new MergeSort();
                break;
        }
        stats = sort.sort(data, order);
        return stats;
    }
    /**
     * Funkcja generujaca tablice losowych wartosci 
     * typu int o rozmiarze n
     * @param n
     * @return
     */
    private static int[] genRandData(int n){
        int[] data = new int[n];
        Random r = new Random();

        for(int i = 0; i < n; i++){
            data[i] = r.nextInt();
        }
        return data;
    }
}