public class Teacher implements People {

    @Override
    public String work() {
        System.out.println("I am teacher...I am working");
        return "teacher";
    }
}
