package com.example.customcalendarjava;

import java.util.concurrent.Callable;

public abstract class Callback<T> implements Callable<Void> {

    public abstract Void call();
}