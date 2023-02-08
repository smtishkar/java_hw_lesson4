public class test {
    public static void main(String[] args) {
        int a = 15;


        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";

        System.out.println(a);
        System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);

    }
    
}
