package com.lewk.umbra.utils;

@FunctionalInterface
public interface Callback<A> {

    void call(A argument);

}
