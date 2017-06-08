#include "FCFS.h"
#include <stdlib.h>

void schedule_FCFS(const TaskPool *task_pool) {
    // TODO
    Task *CPU = NULL;

    int i = 0;

    while (!allDone(task_pool)) {
        //Here: select a task for execution, based on his arrival time:
        if (task_pool->task[i]!=NULL) {
            for (int l = 0; l < task_pool->task[i]->total_ticks; l++) {
                //Now: run him exactly his total_length value times:
                CPU = task_pool->task[i];
            }
        }
        if (execTask(CPU, 1) < 0) {
            printf("%sERROR:%s No Task selected to be executed.\n", COLOR_RED, COLOR_RESET);
            break;
        }
        //Here: come to the next task
        i++;
    }
    printf("\n");

    return;

}
