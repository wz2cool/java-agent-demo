package com.innodealing.onshore;

import org.objectweb.asm.*;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class MethodReturnTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        // 我们在此处理字节码增强
        if (className.contains("BasicController")) {
            System.out.println("[===agent===] className:" + className);
            return enhanceClass(classfileBuffer);
        }
        return classfileBuffer;
    }

    private byte[] enhanceClass(byte[] classfileBuffer) {
        // 使用 ASM 字节码库进行字节码修改
        ClassReader classReader = new ClassReader(classfileBuffer);
        ClassWriter classWriter = new ClassWriter(classReader, 0);
        ClassVisitor classVisitor = new MethodReturnClassVisitor(Opcodes.ASM5, classWriter);
        classReader.accept(classVisitor, 0);
        return classWriter.toByteArray();
    }
}
