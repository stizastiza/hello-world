#include "RR.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

//first of all: Queue. We had to copy it here, because it doesn`t let it
// be compiled with two extra files (cmake)
typedef struct Node
{
    void *data;
    struct Node *next;
}node;

typedef struct QueueList
{
    int sizeOfQueue;
    size_t memSize;
    node *head;
    node *tail;
}Queue;

void queueInit(Queue *q, size_t memSize)
{
    q->sizeOfQueue = 0;
    q->memSize = memSize;
    q->head = q->tail = NULL;
}

int enqueue(Queue *q, const void *data)
{
    node *newNode = (node *)malloc(sizeof(node));

    if(newNode == NULL)
    {
        return -1;
    }

    newNode->data = malloc(q->memSize);

    if(newNode->data == NULL)
    {
        free(newNode);
        return -1;
    }

    newNode->next = NULL;

    memcpy(newNode->data, data, q->memSize);

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

void dequeue(Queue *q, void *data)
{
    if(q->sizeOfQueue > 0)
    {
        node *temp = q->head;
        memcpy(data, temp->data, q->memSize);

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
        free(temp->data);
        free(temp);
    }
}

void queuePeek(Queue *q, void *data)
{
    if(q->sizeOfQueue > 0)
    {
        node *temp = q->head;
        memcpy(data, temp->data, q->memSize);
    }
}

void clearQueue(Queue *q)
{
    node *temp;

    while(q->sizeOfQueue > 0)
    {
        temp = q->head;
        q->head = temp->next;
        free(temp->data);
        free(temp);
        q->sizeOfQueue--;
    }

    q->head = q->tail = NULL;
}

int getQueueSize(Queue *q)
{
    return q->sizeOfQueue;
}
int queueContains(Queue *q, void *data)
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
    Queue reqQueue;
    queueInit(&reqQueue, sizeof(task_pool->task));
    // counter for loading:
    int counter = 0;

    while (!allDone(task_pool)) {
        // load tasks into Queue:
        for (int i = counter; i >= 0; i--) {
            if (!isDone(checkArrivals(task_pool, i)) && checkArrivals(task_pool, i) != NULL && queueContains(&reqQueue, checkArrivals(task_pool, i)) == 0) {
                enqueue(&reqQueue, checkArrivals(task_pool, i));
            }
        }
        // CPU peeks process from Queue and dequeues head at the same time:
        dequeue(&reqQueue, CPU);

        // execution:
        if (CPU->exec_ticks >= quantum_max) {
            for (int k = 0; k < quantum_max; k++) {
                if (execTask(CPU, 1) < 0) {
                    printf("%sERROR:%s No Task selected to be executed.\n", COLOR_RED, COLOR_RESET);
                    break;
                }
                counter++;
            }
        }
        else {
            for (int k = 0; k < CPU->exec_ticks; k++) {
                if (execTask(CPU, 1) < 0) {
                    printf("%sERROR:%s No Task selected to be executed.\n", COLOR_RED, COLOR_RESET);
                    break;
                }
                counter++;
            }
        }
    }
    clearQueue(&reqQueue);
    printf("\n");
    return;
}
