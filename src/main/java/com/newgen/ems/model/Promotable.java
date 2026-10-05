package com.newgen.ems.model;

public interface Promotable {
    String nextRole();

    default void promote(){
        System.out.println("Cngratulations Your are being Promoted to "+nextRole());

    }
}
