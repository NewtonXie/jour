@FunctionalInterface
/**
 * 函数式接口
 * FunctionalInterface注解表示只能包含有一个抽象方法的接口
 */
public interface LambdaInterface  {
    void method();
    default String defaultMethod(){
        return "default默认方法";
    }
}
