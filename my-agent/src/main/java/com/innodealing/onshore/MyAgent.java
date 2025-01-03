package com.innodealing.onshore;

import java.lang.instrument.Instrumentation;

public class MyAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("[===agent===]this is an agent.");
        inst.addTransformer(new MethodReturnTransformer());
    }
}
