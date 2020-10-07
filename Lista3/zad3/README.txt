Przykładowe uruchomienie programu z własnymi danymi:

1:$java Main
2:5
3:2
4:7 2 1 3 8

Gdzie -
2: rozmiar tablicy; 
3: szukany element 
4: kolejne wartosci w tablicy oddzielone spacja.
W wyniku dostaniemy: n: 5 c: 5 Y/N: 1. Gdzie 
n-rozmiar danych
c - liczba porównań
Y/N - 1 gdy element jest w tablicy, 0 gdy go nie ma.

Program rownież można uruchomić z zapisem do pliku. Wywolanie
$java Main --stat plik.txt
uruchomi w programie test algorytmu dla danych n = {1000, 2000,...,100000} (100 razy dla każdego n).

---------------------Opracowane dane------------------------
W pliku daneBinary.txt znajdują się średnie wartości porównań dla n = {1000, 2000,...,100000}.
Na podstawie wzoru złożoności obliczeniowej dla algorytmu tzn. T(n) = T(n/2) + c oraz na podstawie średniej liczby porównań
możemy wywnioskować, że c ~ 2. Wniosek taki wyprowadziłem na podstawie analizy ciągów średnich porównań dla problemów 2 razy większych/mniejszych. Ponadto zgromadzone dane zbliżone są do wyniku \Theta(log(n)) jaki uzyskamy rozwiązując rekurencję za pomocą Master Theorem.



