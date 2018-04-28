import java.util.function.Supplier;

public interface LambdaInterfaceFactory {
    static LambdaInterface create(Supplier<LambdaInterface> supplier){
        return supplier.get();
    }
}
