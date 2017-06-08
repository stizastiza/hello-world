#include "FCFS.h"
#include <stdlib.h>

void schedule_FCFS(const TaskPool *task_pool) {
    // TODO
    Task *CPU = NULL;

    while (!allDone(task_pool)) {
        //Here: select a task for execution, based on his arrival time
        int i = 0;
        CPU = checkArrivals(task_pool, i);
        while (!isDone(CPU)) {
            //wait simulation
        }
        if (execTask(CPU, 1) < 0) {
            printf("%sERROR:%s No Task selected to be executed.\n", COLOR_RED, COLOR_RESET);
            break;
        }
        i++;
    }
    printf("\n");

    return;

}
