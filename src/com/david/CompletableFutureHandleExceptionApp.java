package com.david;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.david.LongRunningProcess.logCurrentThread;

public class CompletableFutureHandleExceptionApp {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(logCurrentThread() + " -- starting app...");

        // here we call a completable future that throws an exception
        // we can handle both success & failure scenarios this way

        System.out.println(logCurrentThread() + " -- starting futures: " + LocalDateTime.now());

        LongRunningProcess.computation_exception_compFuture().handle((succ, ex) -> {
            if (succ != null) {
                System.out.println("we've received a str result so comp future succeeded");
            }
            System.out.println("we've received an ex so comp future failed " + ex.getMessage());
            return null;
        });

        Thread.sleep(10000);
        System.out.println(logCurrentThread() + " -- MAIN finished");
    }

}
