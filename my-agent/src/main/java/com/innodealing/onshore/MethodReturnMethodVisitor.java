package com.innodealing.onshore;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodReturnMethodVisitor extends MethodVisitor {

    private final String methodName;
    private final String methodDescriptor;

    public MethodReturnMethodVisitor(int api, MethodVisitor methodVisitor, String methodName, String methodDescriptor) {
        super(api, methodVisitor);
        this.methodName = methodName;
        this.methodDescriptor = methodDescriptor;
        System.out.println("[===agent===] MethodReturnMethodVisitor");
    }

    @Override
    public void visitInsn(int opcode) {
        // 在方法返回时插入逻辑，例如获取返回值
        if (opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) {
            // 在方法返回前插入代码，例如打印返回值
            mv.visitCode();
            mv.visitInsn(Opcodes.DUP); // 假设返回值在局部变量表的第1位
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/innodealing/onshore/Logger", "logReturn", "(Ljava/lang/Object;)V", false);
        }
        super.visitInsn(opcode);
    }
}