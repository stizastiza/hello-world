#include "FCFS.h"
#include <stdlib.h>

void schedule_FCFS(const TaskPool *task_pool) {
    // TODO
    Task *CPU = NULL;

    while (!allDone(task_pool)) {
        //Here: select a task for execution, based on his arrival time
        for (int i = 0; i<task_pool->size; i++) {
            CPU = task_pool->task[i];
        }
    }
    printf("\n");

    return;

}
