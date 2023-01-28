package com.david;

import java.util.concurrent.CompletableFuture;

public class LongRunningProcess {

    public static String getStringFromDb_1() {
        System.out.println(logCurrentThread() + " -- starting getStringFromDb_1...will take 10 seconds");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(logCurrentThread() + " -- finished getStringFromDb_1");
        return "dbString";
    }

    public static int getIntFromRestCall_2(String dbString) {
        System.out.println(logCurrentThread() + " -- starting getIntFromRestCall_2 after getting str " + dbString + "...will take 5 seconds");
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(logCurrentThread() + " -- finished getIntFromRestCall_2");
        return 10;
    }

    public static void updateDbWithResultsCall_3(int numberToInsertToDb) {
        System.out.println(logCurrentThread() + " -- starting updateDbWithResultsCall_3 after getting int " + numberToInsertToDb + "...will take 7 seconds");
        try {
            Thread.sleep(7_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(logCurrentThread() + " -- finished updateDbWithResultsCall_3");
    }

    static String logCurrentThread() {
        return Thread.currentThread().getName();
    }

    public static CompletableFuture<String> computation_1_compFuture() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(logCurrentThread() + " -- starting computation_1_compFuture...will take 10 seconds");
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(logCurrentThread() + " -- finished computation_1_compFuture");
            return "dbString";
        });


    }

    public static CompletableFuture<String> computation_exception_compFuture() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(logCurrentThread() + " -- starting computation_exception_compFuture...will take 2 seconds");
            try {
                Thread.sleep(2_000);
                throw new RuntimeException("exception in com future");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            System.out.println(logCurrentThread() + " -- finished computation_1_compFuture");
//            return "dbString";
        });


    }

    public static CompletableFuture<String> computation_2_compFuture() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(logCurrentThread() + " -- starting computation_2_compFuture...will take 5 seconds");
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(logCurrentThread() + " -- finished computation_2_compFuture");
            return "10";
        });
    }

    public static CompletableFuture<Void> computation_3_compFuture() {
        return CompletableFuture.runAsync(() -> {
            System.out.println(logCurrentThread() + " -- starting computation_3_compFuture...will take 7 seconds");
            try {
                Thread.sleep(7_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(logCurrentThread() + " -- finished computation_3_compFuture");
        });
    }
}
