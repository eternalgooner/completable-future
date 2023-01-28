package com.david;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.david.LongRunningProcess.logCurrentThread;

public class CompletableFutureInSequenceApp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(logCurrentThread() + " -- starting app...");

        // we can pass our own thread pool into the completable future
        // if we don't, the ForkJoin common pool is used, this has pros & cons
        // it depends on weather the tasks are computational or IO blocking
        // the number of cores should also be taken into consideration
        ExecutorService executor = Executors.newFixedThreadPool(4);

        System.out.println(logCurrentThread() + " -- starting futures: " + LocalDateTime.now());

        CompletableFuture.supplyAsync(LongRunningProcess::getStringFromDb_1, executor)
                .thenApply(LongRunningProcess::getIntFromRestCall_2)
                .thenAccept(LongRunningProcess::updateDbWithResultsCall_3);

        Thread.sleep(30000);
        System.out.println(logCurrentThread() + " -- MAIN finished");
        executor.shutdown();
    }

}
