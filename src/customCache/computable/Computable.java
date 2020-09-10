package customCache.computable;

/**
 * honglinwei created on 4/1/20 inside the package - customCache.computable
 */
public interface Computable <A, V> {
    V compute(A arg) throws Exception;
}
