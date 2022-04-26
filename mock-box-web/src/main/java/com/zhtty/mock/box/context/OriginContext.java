package com.zhtty.mock.box.context;

public class OriginContext {

    private final static ThreadLocal<Object> ORIGINAL_CONTEXT = ThreadLocal.withInitial(() -> null);

    /**
     * @param original
     */
    public static void setOriginal(Object original) {
        ORIGINAL_CONTEXT.set(original);
    }

    /**
     * @return
     */
    public static Object getOriginal() {
        return ORIGINAL_CONTEXT.get();
    }

}
