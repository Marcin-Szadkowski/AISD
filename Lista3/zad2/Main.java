import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Main {
    public static void main(String[] args){
        //-r operowanie na danych losowych -p losowa permutacja
        String mode = "-r";
        //do wczytywania danych
        Scanner scan = new Scanner(System.in);
        //Rozmiar danych
        int n =0;
        //k-ta statystyka pozycyjna
        int k =0;
        //czy zapis do pliku
        int file =0;
        Statistics stat = new Statistics();
        int[] data;
        
        Select select;

        //Pobranie parametru trybu doboru danych
        try{
            mode = args[0];
            if(args.length > 1){
                file = Integer.parseInt(args[1]);
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            System.exit(0); 
        }
        //Pobranie n i k
        if(scan.hasNextInt())
            n = scan.nextInt();
        if(scan.hasNextInt())
            k = scan.nextInt();
        scan.close();

        //Wygenerowanie danych
        if(mode.equals("-r")){
            data = genRandData(n);
        }else if(mode.equals("-p")){
            data = genRandPermutation(n);
        }else{
            data = new int[1];
        }
        //Wyswietlenie tablicy na standardowym wyjsciu bledow
        for(int i =0; i< n; i++){
            System.err.print(data[i] + "\t");
        }
        System.out.println();
        //Opcja z zapisem danych do pliku dla liczenia statystyk
        if(file == 1){
            PrintWriter writerR, writerS;
            try{
                writerR = new PrintWriter("ranSel.txt", "UTF-8");
                writerS = new PrintWriter("sel.txt", "UTF-8");
                writerR.println("n:\t c:\t s:\t");
                writerS.println("n:\t c:\t s:\t");
                //1000 prob
                for(int i =0 ; i <1000; i++){
                    if(mode.equals("-r"))
                        data = genRandData(n);
                    else
                        data = genRandPermutation(n);

                    int[] data2 = new int[n];
                    System.arraycopy(data, 0, data2, 0, n);
                    //Randomized select
                    select = new RandomizedSelect();
                    stat = select.find(data2, k);
                    writerR.println(stat.toStringFF());
                    //Select
                    Select select2 = new LinearSelect();
                    stat = select2.find(data, k);
                    writerS.println(stat.toStringFF());
                }
                writerR.close();
                writerS.close();
            }catch(FileNotFoundException ex){
                System.exit(0);
            }catch(UnsupportedEncodingException ex){
                System.exit(0);
            }
        }else{
            int[] data2 = new int[n];
            System.arraycopy(data, 0, data2, 0, n);
            //Randomized select
            select = new RandomizedSelect();
            Statistics stat2 = select.find(data2, k);            
            //Select
            Select select2 = new LinearSelect();
            stat = select2.find(data, k);

            System.out.println(stat2.toString());
            System.out.println(stat.toString());
           
            

            for(int i =0; i< n; i++){
                if(data2[i] == stat.kIndex)
                    System.out.print("["+data2[i]+"]\t");
                else
                    System.out.print(data2[i] + "\t");
            }
            System.out.println();
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
            data[i] = r.nextInt();
        }
        return data;
    }
    /**
     * Funkcja generujaca losowa permutacje
     * zbioru {1,2,..,n}
     * @param n rozmiar danych
     */
    private static int[] genRandPermutation(int n){
        List<Integer> list = new ArrayList<Integer>();
        int[] tab = new int[n];

        for(int i = 1; i<=n; i++){
            list.add(i);
        }
        java.util.Collections.shuffle(list);
        for(int i = 0; i<n; i++){
            tab[i] = list.get(i);
        }
        return tab;
    }
}