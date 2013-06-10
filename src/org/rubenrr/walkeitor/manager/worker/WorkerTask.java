package org.rubenrr.walkeitor.manager.worker;

import android.util.Log;
import org.rubenrr.walkeitor.element.unit.Person;
import org.rubenrr.walkeitor.manager.command.Command;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Manages the tasks for the workers.
 *
 *  - This object can have the list of task that are pending to be done
 *  - and the list of Workers that are idle
 *
 * User: Ruben Rubio Rey
 * Date: 2/06/13
 * Time: 1:48 PM
 */
public class WorkerTask {
    BlockingQueue<Command> queue;
    TreeSet<Person> idleWorkers;

    public WorkerTask() {
        this.queue = new PriorityBlockingQueue();
        this.idleWorkers = new TreeSet<Person>();
    }

    public void addIdleWorker(Person worker) {

        Log.d("WorkerTask/Command", "New idle worker");

        if (this.queue.isEmpty()) {
            this.idleWorkers.add(worker);
            Log.d("WorkerTask/Command", "Added idle worker to the list " + this.idleWorkers.size());
        } else {
            Log.d("WorkerTask/Command", "Set task straight away to idle worker");
            this.setTaskToWorker();
        }


    }

    public void removeIdleWorker(Person worker) {
        this.idleWorkers.remove(worker);
    }

    /**
     * New task arrived. Let's allow the population of workers to fix it.
     *
     * @param command
     */
    public void addTask(Command command) {

        if (this.idleWorkers.isEmpty()) { // if empty we queue the task
            Log.d("WorkerTask/Command", "Task added to a queue " + command.toString());
            this.queue.add(command);
        } else { // we have an idle worker to solve the task
            this.setTaskToWorker(command);
        }

    }

    private void setTaskToWorker() {
        Command command = null;
        try {
            Log.d("WorkerTask/Command", "setTaskToWorker");
            command = this.queue.take();
            this.setTaskToWorker(command);
        } catch (InterruptedException e) {
            Log.e("Queue", "An error happened while trying to take queue", e);
        }
    }

    private void setTaskToWorker(Command command) {
        Person worker = this.idleWorkers.pollFirst();
        if (worker != null) {
            Log.d("WorkerTask/Command", " Assigning Task" + command.toString() + " to worker " + worker.toString());
            command.setUnit(worker);
            if ( ! command.execute() ) {
                // TODO what to do when the task cannot be solved
                // TODO need to add to the command properties related with how many times it has been tried.
                this.addIdleWorker(worker);
            }

        } else {
            Log.w("WorkerTask/Command", " Assigning Task failed, no workers available");
        }

    }

}
