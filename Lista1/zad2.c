/** Implementacja listy jednokierunkowej 
 * Program mierzy sredni dostep do losowego i konkretnego elementu
 **/ 
#include<stdlib.h>
#include<stdio.h>
#include <time.h>

struct Element{
    int key;
    struct Element* next;
};

typedef struct{
    struct Element* head;
} List;
//Funkcja dodajaca element o podanym kluczu na poczatek listy
void insert(List* list, int k){
    struct Element* new = (struct Element*)malloc(sizeof(struct Element));
    new->key = k;
    new->next = list->head;
    list->head = new;
}
//Funkcja zwraca pierwszy element na liscie o podanym kluczu
struct Element* find(List* list, int k){
    struct Element* result = list->head;
    do{
        if(result->key == k)
            break;
        result = result->next;
    }while(result != NULL);
    return result;
}
//Funkcja usuwajaca pierwszy element o podanym kluczu
void delete(List* list, int k){
    struct Element* temp = list->head;

    if(temp->key == k){
        list->head = temp->next;
        return;
    } 
    while(temp->next != NULL && temp->next->key != k ){
        temp = temp->next;
    }
    if(temp->next == NULL)
        return;
    temp->next = (temp->next)->next;
}
//Funkcja wypelniajaca liste losowymi wartosciami z przedzialu od 0 do 999
void listFill(List* list){
    srand((unsigned)time(NULL));
    for(int i= 0; i<1000; i++){
        insert(list, rand()%1000);
    }
}
//Funkcja scalająca dwie listy
List* merge(List* l1, List* l2){
    struct Element* temp = l1->head;

    while(temp->next != NULL){
        temp = temp->next;
    }
    temp->next = l2->head; //Dowiazanie listy l2 na koniec listy l1
    return l1;
}
int main(){
    clock_t start, end;
    double timeUsed;
    struct Element* temp = (struct Element*)malloc(sizeof(struct Element));

    List* list = (List*)malloc(sizeof(List));
    list->head = NULL;

    listFill(list); //Wypelnienie listy losowymi danymi
    //Zmierzymy sredni czas dostepu do 100 losowych elementow
    //Oraz do n-tego elementu, np.: 780-tego
    for(int i=0; i<1000; i++){
        start = clock();
        temp = list->head;
        for(int i=1; i<= 780; i++){
            temp = temp->next;
        }
        end = clock();
        timeUsed += (double)(end - start);
    }
    timeUsed /=1000;
    timeUsed /= CLOCKS_PER_SEC;

    printf("Czas dostepu dla 780-tego elementu: %f sec \n", timeUsed);
    //Wyzerowanei sumy czasu
    timeUsed = 0;
    srand((unsigned)time(NULL));
    for(int i =0; i<500; i++){       
        int n= rand()%1000;

        start = clock();
        temp = list->head;
        for(int i=0; i< n; i++){
            temp = temp->next;
        }
        end = clock();
        timeUsed += (double)(end - start);
    }
    timeUsed /= 500;
    timeUsed /= CLOCKS_PER_SEC;
    printf("Czas dostepu do 500 losowych elementów: %f sec \n", timeUsed);

    //Sprawdzenie funkcji merge
    List* l1 = (List*)malloc(sizeof(List));
    l1->head = NULL;
    List* l2 = (List*)malloc(sizeof(List));
    l2->head = NULL;

    insert(l1, 1);
    insert(l1, 2);
    insert(l1, 3);
    insert(l2, 4);
    insert(l2, 5);
    insert(l2, 6);
    l1 = merge(l1, l2);
    temp = l1->head;
    for(int i=0; i<6; i++){
        printf("%d\t",temp->key );
        temp = temp->next;
    }
    free(list);
    return 0;
}