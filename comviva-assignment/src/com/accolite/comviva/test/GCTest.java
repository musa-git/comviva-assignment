package com.accolite.comviva.test;

import java.util.ArrayList;
import java.util.List;

import com.accolite.comviva.gc.GC;
import com.accolite.comviva.model.A;
import com.accolite.comviva.model.B;
import com.accolite.comviva.model.C;

public class GCTest {

    public List<ArrayList<String>> map = new ArrayList<ArrayList<String>>();

    public void testGCLifecycle(){

        try {
            A a1 = new A(new B());
            GC.get(a1);
            GC.release(a1);
            GC.gc();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    public void testWithCommonObject(){
        try {
            B b1 = new B();
            A a1 = new A(b1);
            A a2 = new A(b1);

            GC.get(a1);
            GC.get(a2);
            GC.release(a1);

            GC.gc();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	new GCTest().testWithCommonObject();
	}



}
