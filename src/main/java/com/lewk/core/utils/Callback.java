package com.lewk.core.utils;

@FunctionalInterface
public interface Callback<A> {

    void call(A argument);

}
