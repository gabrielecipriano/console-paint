package cpaint;

import java.util.Scanner;

import static java.lang.String.format;

public class ConsolePaint {
    public static void main(String[] args) {
        new ConsolePaint().executeWith(new Scanner(System.in));
    }

    public void executeWith(Scanner inputSource) {
        String command = inputSource.nextLine();
        System.out.printf("I should have executed command: %s%n", command);
    }

}
