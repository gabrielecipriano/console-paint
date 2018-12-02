package cpaint;

import java.util.Scanner;

public class ConsolePaint {
    public static void main(String[] args) {
        new ConsolePaint().executeWith(null);
    }

    public void executeWith(Scanner inputSource) {
        String name = inputSource.nextLine();
        System.out.printf("Hello %s!", name);
    }

}
