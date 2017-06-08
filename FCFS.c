#include "FCFS.h"
#include <stdlib.h>

void schedule_FCFS(const TaskPool *task_pool) {
    // TODO
    Task *CPU = NULL;
    int curr = 0;
    while (!allDone(task_pool)) {
        //Here: select a task for execution, based on his arrival time
            for (int i = 0; i < task_pool->size; i++) {
                    if (!isDone(task_pool->task[i])) {
                        curr = i;
                        break;
                    }
            }

        CPU = checkArrivals(task_pool, curr);
        curr = 0;

        if (execTask(CPU, 1) < 0) {
            printf("%sERROR:%s No Task selected to be executed.\n", COLOR_RED, COLOR_RESET);
            break;
        }
    }
    printf("\n");

    return;

}

