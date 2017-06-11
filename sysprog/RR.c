#include "RR.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

//first of all: Queue. We had to copy it here, because it doesn`t let it
// be compiled with two extra files (cmake)
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

void dequeue(Queue *q, int k)
{
    if(q->sizeOfQueue > 0)
    {
        node *temp = q->head;
        k = temp->data;

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



/*
 * Idea:
 * counter counts common time to work with arrivals,
 * queue reqQueue is filled with tasks ordered by their arrival time
 * 
 */
void schedule_RR(const TaskPool *task_pool, uint16_t quantum_max) {
    // TODO
    Task *CPU = NULL;
    //initialisation and allocation of my request Queue:
    Queue* reqQueue = (Queue*) malloc(sizeof(Queue));
    queueInit(reqQueue);
    // counter for loading:
    int counter = 0;
    int time;
    while (!allDone(task_pool)) {
        // load tasks into Queue:
        for (int i = counter; i >= 0; i--) {
            if (!isDone(checkArrivals(task_pool, i)) && checkArrivals(task_pool, i) != NULL && queueContains(reqQueue, i) == 0) {
                enqueue(reqQueue, i);
            }
        }
        // CPU peeks process from Queue and dequeues head at the same time:
        dequeue(reqQueue, time);
        CPU = checkArrivals(task_pool, time);

        // execution:
        if (CPU->exec_ticks >= quantum_max) {
            CPU->exec_ticks = CPU->exec_ticks - quantum_max;
            for (int k = 0; k < quantum_max; k++) {
                counter++;
                if (execTask(CPU, 1) < 0) {
                    printf("%sERROR:%s No Task selected to be executed.\n", COLOR_RED, COLOR_RESET);
                    break;
                }
            }
        }
        else {
            for (int k = 0; k < CPU->exec_ticks; k++) {
                counter++;
                if (execTask(CPU, 1) < 0) {
                    printf("%sERROR:%s No Task selected to be executed.\n", COLOR_RED, COLOR_RESET);
                    break;
                }
            }
        }
    }
    clearQueue(reqQueue);
    printf("\n");
    return;
}
