package com.david;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.david.LongRunningProcess.logCurrentThread;

public class CompletableFutureOnlyNeedOneToFinishApp {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(logCurrentThread() + " -- starting app...");

        // Here we have 3 completable futures all taking different lengths of time to completed
        // if we have a scenario where we only care when any of them complete successfully
        // we can group them together and them initiate some processing when the 1st has completed
        // the rest of the threads will still complete in the background

        System.out.println(logCurrentThread() + " -- starting futures: " + LocalDateTime.now());

        CompletableFuture.anyOf(
                LongRunningProcess.computation_1_compFuture(),
                LongRunningProcess.computation_2_compFuture(),
                LongRunningProcess.computation_3_compFuture())
                .thenAccept(obj -> System.out.println("successful comp future completed " + obj.toString()));

        Thread.sleep(30000);
        System.out.println(logCurrentThread() + " -- MAIN finished");
    }

}
