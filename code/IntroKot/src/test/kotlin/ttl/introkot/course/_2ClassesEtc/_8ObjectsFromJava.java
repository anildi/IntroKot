package ttl.introkot.course._2ClassesEtc;

import ttl.introkot.course._2ClassesEtc.MyClass;

/**
 * @author whynot
 */
class ObjectsFromJava {

    public static void main(String[] args) {
        MyClass.Companion.fakeStaticFun();

//        String x = MyClass.getLabel();
        MyClass.fakeStaticFun();        //Need @JVMStatic on the function to make this work
//        MyClass.Companion.getLabel();
//        MyClass.getLabel();
        var x = MyClass.label;

        MyClass mc = new MyClass();
        MyClass.A.INSTANCE.callFooFromA(mc);
//        MyClass.A.callFooFromA(mc);
    }

}

