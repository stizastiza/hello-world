#include "RR.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

//first of all: Queue. We puted it here to make sure everyone of us can compile it without changing CMakeLists.txt
// Wrote by us. 
typedef struct Node
{
    int data;
    struct Node *next;
}node;

typedef struct QueueList
{
    int sizeOfQueue;
    node *head;
    node *tail;
}Queue;

void queueInit(Queue *q)
{
    q->sizeOfQueue = 0;
    q->head = NULL;
    q->tail = NULL;
}
int queuePeek(Queue *q) {
    if (q->sizeOfQueue>0) {
        return q->head->data;
    }
}
int enqueue(Queue *q, int k)
{
    node *newNode = (node *)malloc(sizeof(node));

    newNode->next = NULL;
    newNode->data = k;

    if(q->sizeOfQueue == 0)
    {
        q->head = q->tail = newNode;
    }
    else
    {
        q->tail->next = newNode;
        q->tail = newNode;
    }

    q->sizeOfQueue++;
    return 0;
}

int dequeue(Queue *q)
{
    if(q->sizeOfQueue > 0)
    {
        node *temp = q->head;
        int var = temp->data;
        if(q->sizeOfQueue > 1)
        {
            q->head = q->head->next;
        }
        else
        {
            q->head = NULL;
            q->tail = NULL;
        }
        q->sizeOfQueue--;
        free(temp);
        return var;
    }
}
void clearQueue(Queue *q)
{
    node *temp;

    while(q->sizeOfQueue > 0)
    {
        temp = q->head;
        q->head = temp->next;
        free(temp);
        q->sizeOfQueue--;
    }

    q->head = q->tail = NULL;
}
int queueContains(Queue *q, int data)
{
    node *temp = q->head;
    while (temp != NULL) {
        if (temp->data == data) {
            return 1;
        }
        temp = temp->next;
    }
    return 0;
}


void schedule_RR(const TaskPool *task_pool, uint16_t quantum_max) {
    // TODO
    Task *CPU = NULL;

    //initialisation and allocation of my request Queue:
    Queue* reqQueue = (Queue*) malloc(sizeof(Queue));
    queueInit(reqQueue);

    // counter for loading:
    int counter = 0;
    int lastCount = 0;
    int time;
    enqueue(reqQueue, 0);

    while (!allDone(task_pool)) {
        time = dequeue(reqQueue);
        CPU = checkArrivals(task_pool, time);
        if (execTask(CPU, quantum_max) < 0) {
            printf("%sERROR:%s No Task selected to be executed.\n", COLOR_RED, COLOR_RESET);
            break;
        }
        counter = counter + quantum_max;
        //first work done. Now i go check for new arrivals into my reqQueue:
        for (int i = lastCount+1; i<=counter; i++) {
            Task *NewArrival = checkArrivals(task_pool, i);
            if (NewArrival != NULL) {
                enqueue(reqQueue, i);
            }
        }
        lastCount = counter;
        // and of course add not finished tasks:
       if (CPU->exec_ticks < CPU->total_ticks) {
            enqueue(reqQueue, time);
        }
    }
    clearQueue(reqQueue);
    printf("\n");
    return;
}
