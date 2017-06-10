#include "RR.h"
#include <stdlib.h>
#include "queue.h"

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
