package cpaint;

import java.util.Scanner;

public interface Console {
    void print(String input);

    default String getNextCommand(Scanner inputSource) {
        print("enter command: ");
        return inputSource.nextLine();
    }
}
