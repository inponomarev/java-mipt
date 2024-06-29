package edu.mipt.hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App {
    public static void main(String[] args) {
        System.out.println(parse("ООО \"Рога и Копыта\",\"рога, копыта\""));
        System.out.println(parse("\"OOO \"\"Берёзка\"\"\",веники берёзовые"));
    }

    enum State {START, QUOTED_BODY, QUOTE_READ, UNQUOTED_BODY}

    ;

    static List<String> parse(String line) {
        State state = State.START;
        List<String> result = new ArrayList<>();
        StringBuilder fieldValue = new StringBuilder();
        for (char c : line.toCharArray()) {
            switch (state) {
                case START:
                    fieldValue = new StringBuilder();
                    if (c == '"')
                        state = State.QUOTED_BODY;
                    else {
                        fieldValue.append(c);
                        state = State.UNQUOTED_BODY;
                    }
                    break;
                case QUOTED_BODY:
                    if (c == '"')
                        state = State.QUOTE_READ;
                    else {
                        fieldValue.append(c);
                        state = State.QUOTED_BODY;
                    }
                    break;
                case QUOTE_READ:
                    if (c == '"') {
                        fieldValue.append(c);
                        state = State.QUOTED_BODY;
                    } else if (c == ',') {
                        result.add(fieldValue.toString());
                        state = State.START;
                    } else {
                        //invalid input format
                        throw new IllegalStateException();
                    }
                    break;
                case UNQUOTED_BODY:
                    if (c == ',') {
                        result.add(fieldValue.toString());
                        state = State.START;
                    } else {
                        fieldValue.append(c);
                    }
                    break;
            }
        }
        result.add(fieldValue.toString());
        Integer integer = Integer.valueOf(5);

        return result;


    }

}
