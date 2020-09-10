package Singleton;

//best way to create a singleton which can prevent reflection and deserilzation
public class EnumSingleton {
    public static EnumSingleton getInstance(){
        return ContainerHolder.HOLDER.instance;
    }
    private enum ContainerHolder{
        HOLDER;
        private EnumSingleton instance;
        ContainerHolder(){
            instance = new EnumSingleton();
        }
    }
}
