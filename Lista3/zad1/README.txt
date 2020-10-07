Przykładowe uruchomienie programu z własnymi danymi
1:$java Main --type radix --comp "<="
2:5
3:1 9 2 3 7
Gdzie 
2: rozmiar tablicy
3: wartości tablicy oddzielone spacja
Otrzymujemy posortowaną tablicę oraz wynik: n: 5 a: 29 s: 10 t: 20.702909 m: 83920,
gdzie
a - liczba dodawań
s - liczba zamian
t - czas w milisekundach
m - wykorzystana pamięc w bajtach

Program można również uruchomić z zapisem do pliku tak jak w zadaniu na poprzedniej liście
$java Main --type radix --comp "<=" --stat file.txt 90

-------------------Opracowane dane-----------------------
W plikach danedpQuick.txt, daneMerge.txt znajdują się średnie wartości
porównań, zamian, czasu oraz zużycia pamięci dla odpowiednio algorytmow
Dual Pivot Quick Sort i MergeSort. 
W plikach daneRadix.txt, daneRadixNkw.txt znajdują się średnie wartości
dodawań, zamian, czasu oraz zużycia pamięci dla algorytmu Radix Sort dla
opowiednio zakrsu danych 1,...,n oraz 1,...,n^2.
Możemy zauważyć wyraźny wzrost liczby operacji dla algorytmu Radix Sort, gdy losujemy dane 
z zakresu do n^2. Algorytm jest efektywniejszy, gdy zakres nie wykracza poza n.
