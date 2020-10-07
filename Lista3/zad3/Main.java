import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

class Main {
    static String fileName = null;
    static BinarySearch search = new BinarySearch();

    public static void main(String[] args){
        //Pobranie parametrow
        getInput(args);

        if(fileName == null){
            int[] data;
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt(); //rozmiar danych
            int k = scan.nextInt(); //szukany element
            scan.nextLine();

            if(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] vars = line.split(" ");
                data = new int[n];
                for(int i = 0; i< n; i++){
                    data[i] = Integer.parseInt(vars[i]);
                }
                Statistics stats = new Statistics();
                stats = search.doSearch(data, k);
                System.out.println(stats.toString());
            }
            scan.close();
        }else{
            //Opcja z zapisem do pliku
            PrintWriter writer;
            Statistics stats = new Statistics();
            Random rand = new Random();
            try{
                writer = new PrintWriter(fileName, "UTF-8");
                writer.println("n:\t c:\t");
                for(int n = 1000; n <= 100000; n +=1000){
                    for(int j =0; j<100; j++){
                        int[] data = genRandData(n);
                        int key = rand.nextInt(n);
                        stats = search.doSearch(data, key);
                        writer.println(stats.toStringFF());
                    }
                }
                writer.close();      
            }catch(FileNotFoundException ex){

            } catch(UnsupportedEncodingException ex){
            }
               
        }
            
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
            data[i] = r.nextInt(n) + 1;
        }
        Arrays.sort(data);
        return data;
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
                    case "--stat":
                        fileName = iterator.next();
                        break;
                } 
            }catch(NumberFormatException ex){
                System.err.println("Blad podczas parsowania");
                System.exit(0);
            }           
        }
    }
}