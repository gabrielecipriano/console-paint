package cpaint;

import java.util.Scanner;

import static java.lang.String.format;

public class ConsolePaint {
    public static void main(String[] args) {
        new ConsolePaint().executeWith(null);
    }

    public void executeWith(Scanner inputSource) {
        System.out.println("What's your name?: ");
        String name = inputSource.nextLine();
        System.out.println(format("Hello %s!", name));
        System.out.println("What's your name again?: ");
        String repeatedName = inputSource.nextLine();
        System.out.print(format("Oh ok %s, nice to meet you", repeatedName));
    }

}
