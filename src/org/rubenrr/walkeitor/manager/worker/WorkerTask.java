package org.rubenrr.walkeitor.manager.worker;

import android.util.Log;
import org.rubenrr.walkeitor.element.unit.Person;
import org.rubenrr.walkeitor.manager.command.Command;

import java.util.ArrayList;
import java.util.List;
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
    List<Person> idleWorkers = new ArrayList<Person>();

    public WorkerTask() {
        this.queue = new PriorityBlockingQueue();
        this.idleWorkers = new ArrayList<Person>();
    }

    public void addIdleWorker(Person worker) {

        if (this.queue.isEmpty()) {
            this.idleWorkers.add(worker);
        } else {
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
            command = this.queue.take();
            this.setTaskToWorker(command);
        } catch (InterruptedException e) {
            Log.e("Queue", "An error happened while trying to take queue", e);
        }
    }

    private void setTaskToWorker(Command command) {
        Person worker = this.idleWorkers.get(0);
        this.idleWorkers.remove(worker);
        Log.d("WorkerTask/Command", " Assigning Task" + command.toString() + " to worker " + worker.toString());
        command.setUnit(worker);
        command.execute();
    }

}
