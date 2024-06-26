package ttl.introkot.course._3MoreNullness;

import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author whynot
 */
public class NullabilityJava {

    //This annotation can be read by the kotlin compiler
//    @Nullable
    @NotNull
    public String getStuff() {
        return ThreadLocalRandom.current().nextInt(10) > 5 ?  "boo" : "hoo";
//        return null;
    }

    //Add an @NotNull annotation to the argument to get an
    //error in NullabilityKot.testSetNullInJava
    public void doStuff(String stuff) {
       int length = stuff.length();
       //do something with length
    }
}
