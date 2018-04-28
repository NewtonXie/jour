import freemarker.template.SimpleDate;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.crypto.Data;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LambdaTest {

    /**
     * 处理空值
     */
    @Test
    public void optional(){
//        Optional<String> fullname = Optional.ofNullable("null");
//        System.out.println(fullname.isPresent());
//        System.out.println(fullname.orElseGet(()->"[none]"));
//        System.out.println(fullname.map(s->"hey"+s+"!").orElse("hey"));
        Optional<String> fullname = Optional.ofNullable("xys");
        System.out.println(fullname.isPresent());
        System.out.println(fullname.orElseGet(()->"[none]"));
        System.out.println(fullname.map(s->"hey "+s+"!").orElse("hey"));
    }
    @Test
    public void script() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.println(engine.getClass().getName());
        System.out.println("js:"+engine.eval("function f(){return 1;} f()+1"));
    }
    @Test
    public void asList(){
        Arrays.asList("D","d","a","b","c").forEach(e-> {
            System.out.println(e);
        });
        Arrays.asList("Ddfdfd","d","a","b","c","Ddfdfd").sort((e1,e2)-> {
            int result = e1.compareTo(e2);
            System.out.println(e1+" "+e2);
            System.out.println(result);
            return result;
        });
    }
    @Test
    public void lambadaInterface(){
        System.out.println(LambdaInterfaceFactory.create(LambdaImpl::new).defaultMethod());
        System.out.println(LambdaInterfaceFactory.create(LambdaImplOverride::new).defaultMethod());
    }
    @Test
    public void base64(){
        final String encoder =  Base64.getEncoder().encodeToString("Base64".getBytes(StandardCharsets.UTF_8));
        System.out.println(encoder);
        System.out.println(new String(Base64.getDecoder().decode(encoder),StandardCharsets.UTF_8));
    }
    @Test
    public void parallelArrays(){
        long[] arrayOfLong = new long[20];
        Arrays.parallelSetAll(arrayOfLong,index-> ThreadLocalRandom.current().nextInt(100));
        Arrays.stream(arrayOfLong).limit(20).forEach(i-> System.out.print(i+" "));

        System.out.println();
        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(i-> System.out.print(i+" "));
    }
    @Test
    public void calendar(){

        System.out.println(System.currentTimeMillis());
        System.out.println(TimeZone.getDefault());


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.set(2018,2-1,25,12,00,00);
        System.out.println(simpleDateFormat.format(cal.getTime()));
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        System.out.println("时间："+(cal.getTimeInMillis()-calendar.getTimeInMillis()));
        Date date = new Date(calendar.getTimeInMillis());
        System.out.println(date);
    }

}
class LambdaImpl implements LambdaInterface{

    @Override
    public void method() {

    }
}

/**
 * 接口中的静态方法
 */
class LambdaImplOverride implements LambdaInterface{

    @Override
    public void method() {
    }

    @Override
    public String defaultMethod() {
        return "静态方法重写";
    }
}

class ParameterNames{
    public static void main(String[] args) throws NoSuchMethodException {
        //参数名称
        Method method = ParameterNames.class.getMethod("main", String[].class);
        for (final Parameter parameter:method.getParameters()){
            System.out.println("parameter:"+parameter.getName());
        }
        //类型推断
    }
}
