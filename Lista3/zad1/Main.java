
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

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
        //int[] data = {-10, 2, 222, 100, -2, 10, 4};
        
        getInput(args);
        
        //Jezeli nie wybrano zapisu do pliku
        if(fileName == null){
            int[] data;
            Scanner scan = new Scanner(System.in);
            int k = scan.nextInt();
            scan.nextLine();
            
            if(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] vars = line.split(" ");
                data = new int[k];
                for(int i = 0; i<k; i++){
                    data[i] = Integer.parseInt(vars[i]);
                }
                //Nastepuje sortowanie i pobranie statystyk
                stats = chooseSort(type, order, data);
                //Wyswietlenie danych
                System.err.println(stats.toString());
                for(int i =0; i < data.length; i++){
                    System.out.print(data[i]+ "\t");
                }
                System.out.println();
            }else{
                System.exit(0);
            }
            scan.close();
            
        }else{
            //Opcja z zapisem danych do pliku
            PrintWriter writer;
            try{
                writer = new PrintWriter(fileName, "UTF-8");
                writer.println("n:\t a:\t s:\t t:\t m:");
                int m = 50;
                for(int n = 10; n <= 100000; n *=10){
                    for(int i =0; i < k; i++){
                        stats = chooseSort(type, order, genRandData(n));
                        writer.println(stats.toStringFF());
                        stats = chooseSort(type, order, genRandData(m));
                        writer.println(stats.toStringFF());
                    }
                    m *= 10;
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
            case "radix":
                sort = new RadixSort();
                break;
            default:
                sort = new RadixSort();
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
        if( n < 45000)
            for(int i = 0; i < n; i++){
                data[i] = r.nextInt(n*n);
            }
        else
            for(int i = 0; i < n; i++){
                data[i] = r.nextInt(n);
            }
       
        return data;
    }
}