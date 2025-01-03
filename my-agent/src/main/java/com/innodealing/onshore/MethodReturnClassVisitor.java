package com.innodealing.onshore;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodReturnClassVisitor extends ClassVisitor {

    public MethodReturnClassVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
        System.out.println("[===agent===] classVisitor");
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        // 目标方法名称和描述符可以根据需要过滤
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);

        if (name.equals("hello")) {
            System.out.println("[===agent===] hook hello");
            return new MethodReturnMethodVisitor(Opcodes.ASM5, methodVisitor, name, descriptor);
        }

        return methodVisitor;
    }
}
