package com.example.firsttryapp;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SusoApp extends Application {

ExecutorService srv = Executors.newCachedThreadPool();


}