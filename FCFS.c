#include "FCFS.h"
#include <stdlib.h>

void schedule_FCFS(const TaskPool *task_pool) {
    // TODO
    // remember all the times in the list
    int timeList[task_pool->size];
    for (int k = 0; k < task_pool->size; k++) {
        timeList[k] = task_pool->task[k]->total_ticks;
    }

    Task *CPU = NULL;
    int curr = 0;
    while (!allDone(task_pool)) {
        //Here: select a task for execution, based on his arrival time
            for (int i = 0; i < task_pool->size; i++) {
                if (compareToOthers(timeList, task_pool->task[i]->total_ticks, task_pool->size) == 1) {
                    if (!isDone(task_pool->task[i])) {
                        curr = i;
                        break;
                    }
                }
                else {
                    continue;
                }
            }

        CPU = task_pool->task[curr];
        curr = 0;

        if (execTask(CPU, 1) < 0) {
            printf("%sERROR:%s No Task selected to be executed.\n", COLOR_RED, COLOR_RESET);
            break;
        }
    }
    printf("\n");

    return;

}
    // compare my @param time with other times in my timeList. If its smaller, then i delete it from timeList and
    // return 1. Otherwise timeList stayes the same and @return = 0
int compareToOthers(int* timeList, int time, int poolSize) {
        int r;
    for (int i = 0; i < poolSize; i++) {
        if (timeList[i] != (-1)) {
            if (timeList[i] < time) {
                return 0;
            }
            if (timeList[i] == time) {
                int r = i;
            }
        }
    }
    // IS SMALLER THEN OTHERS:
    timeList[r] = -1;
    return 1;
}
