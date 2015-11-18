package com.shijie99.basic.vo;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TemplateVO {
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		final String st = new String("1111");
		for(int i = 0; i < 1000; i++) {
			es.submit(new Runnable() {
				@Override
				public void run() {
					synchronized (st) {
						
					}
				}
			});
		}
	}
}
