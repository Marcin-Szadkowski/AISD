/** Implementacja kolejki FIFO
 *  przy użyciu struktur
 **/
#include<stdlib.h>
#include<stdio.h>

//Wezel w kolejce
typedef struct {
    int data;
    struct Node* next;
}Node ;
//Kolejka
typedef struct {
    Node* tail;
    Node* head;
    int size;
} Queue;

//Funkcja dodajaca element na koniec kolejki
void enqueue(Queue* queue, int data){
    Node* node = (Node*)malloc(sizeof(Node));
    node->data = data;
    node->next = queue->tail;
    queue->tail = node;
    if(queue->head == NULL)
        queue->head = node;
    ++queue->size; //zwiekszenie rozmiaru kolejki
}
//Ustawienia poczatkowe kolejki
void initialize(Queue* queue){
    queue->tail = NULL;
    queue->head = NULL;
    queue->size = 0;
}
//Funkcja zwracajaca element, ktory najdluzej czeka w kolejce
Node* dequeue(Queue* queue){
    Node* result;
    Node* node;
    node = queue->tail;
    if(node->next != NULL){
        while(node->next != queue->head){
            node = node->next;
        }
        result = node->next;       
    }else{
        result = node;
    }
    node->next= NULL;
    queue->head = node;
    --queue->size; //zmniejszenie rozmiaru kolejki
    return result;
}
int main(){
    Queue* queue = (Queue*)malloc(sizeof(Queue));
    initialize(queue);
    
    enqueue(queue, 4);
    enqueue(queue, 5);

    printf("%d \n", queue->tail->data );
    printf("%d \n", queue->head->data );
    printf("Rozmiar kolejki: %d \n", queue->size);
    Node* node = dequeue(queue);

    printf("%d \n", queue->tail->data );
    printf("%d \n", queue->head->data );
    printf("%d \n", node->data);
    printf("Rozmiar kolejki: %d \n", queue->size);

    free(queue);
    return 0;
}
