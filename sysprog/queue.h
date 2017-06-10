#ifndef QUEUE_H_INCLUDED
#define QUEUE_H_INCLUDED
/**
  Copied from: https://codereview.stackexchange.com/questions/141238/implementing-a-generic-queue-in-c
  + i had to implement my own function contains in there
*/
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

void queueInit(Queue *q, size_t memSize);
int enqueue(Queue *, const void *);
void dequeue(Queue *, void *);
void queuePeek(Queue *, void *);
void clearQueue(Queue *);
int getQueueSize(Queue *);
int queueContains(Queue *, void *);

#endif /* QUEUE_H_INCLUDED */
