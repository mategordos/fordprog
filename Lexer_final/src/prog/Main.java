package prog;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;


    public class Main {
        public static void main( String[] args ) throws IOException {
            System.out.println("Adj meg egy stringet, amiből lexémákat olvashatunk ($ jellel zárva)");
            TokenFactory factory = new TokenFactory(new PushbackInputStream(System.in));
            for(var token: factory.getTokens()) {
                System.out.print(token.toString());
                System.out.print(" ");
            }
        }
    }

    //Ebben az osztályban egy Buildert használva létrehozzuk a táblát egy Map formájában, a Character.{placeholder} értékek
    // a beolvasott karakterek, a state.ValueOf pedig a hozzájuk rendelt érték.
    class Util {
        public static Table getTable() {
            var tableBuilder = new Table.Builder();

            tableBuilder.withStateInformation(
                    State.START,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(true)
                            .withCharacterToStateMapping(Character.ALPHA,      State.valueOf(2))
                            .withCharacterToStateMapping(Character.DIGIT,       State.valueOf(4))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,     State.valueOf(6))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,     State.valueOf(19))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,      State.valueOf(8))
                            .withCharacterToStateMapping(Character.ASTERISK,    State.valueOf(19))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,      State.valueOf(19))
                            .withCharacterToStateMapping(Character.COLON,       State.valueOf(12))
                            .withCharacterToStateMapping(Character.EQUALS,          State.valueOf(19))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,   State.valueOf(14))
                            .withCharacterToStateMapping(Character.RABRACKET,   State.valueOf(17))
                            .withCharacterToStateMapping(Character.SPACE,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.OTHER,       State.valueOf(19))
                            .withCharacterToStateMapping(Character.$,           State.valueOf(21))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.IDENTIFIER,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(true)
                            .withCharacterToStateMapping(Character.ALPHA,       State.valueOf(2))
                            .withCharacterToStateMapping(Character.DIGIT,       State.valueOf(2))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,     State.valueOf(3))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,     State.valueOf(3))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,      State.valueOf(3))
                            .withCharacterToStateMapping(Character.ASTERISK,    State.valueOf(3))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,      State.valueOf(3))
                            .withCharacterToStateMapping(Character.COLON,       State.valueOf(3))
                            .withCharacterToStateMapping(Character.EQUALS,          State.valueOf(3))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,   State.valueOf(3))
                            .withCharacterToStateMapping(Character.RABRACKET,   State.valueOf(3))
                            .withCharacterToStateMapping(Character.SPACE,       State.valueOf(3))
                            .withCharacterToStateMapping(Character.OTHER,       State.valueOf(3))
                            .withCharacterToStateMapping(Character.$,           State.valueOf(3))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.IDENTIFIER_END,
                    new StateInformation.Builder()
                            .setShouldBackup(true)
                            .setShouldReadInput(false)
                            .withCharacterToStateMapping(Character.ALPHA,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.DIGIT,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,      State.valueOf(1))
                            .withCharacterToStateMapping(Character.ASTERISK,    State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,      State.valueOf(1))
                            .withCharacterToStateMapping(Character.COLON,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.EQUALS,          State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,   State.valueOf(1))
                            .withCharacterToStateMapping(Character.RABRACKET,   State.valueOf(1))
                            .withCharacterToStateMapping(Character.SPACE,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.OTHER,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.$,           State.valueOf(1))
                            .build()
            );


            tableBuilder.withStateInformation(
                    State.NUMBER,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(true)
                            .withCharacterToStateMapping(Character.ALPHA,       State.valueOf(5))
                            .withCharacterToStateMapping(Character.DIGIT,       State.valueOf(4))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,     State.valueOf(5))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,     State.valueOf(5))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,      State.valueOf(5))
                            .withCharacterToStateMapping(Character.ASTERISK,    State.valueOf(5))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,      State.valueOf(5))
                            .withCharacterToStateMapping(Character.COLON,       State.valueOf(5))
                            .withCharacterToStateMapping(Character.EQUALS,          State.valueOf(5))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,   State.valueOf(5))
                            .withCharacterToStateMapping(Character.RABRACKET,   State.valueOf(5))
                            .withCharacterToStateMapping(Character.SPACE,       State.valueOf(5))
                            .withCharacterToStateMapping(Character.OTHER,       State.valueOf(5))
                            .withCharacterToStateMapping(Character.$,           State.valueOf(5))
                            .build()
            );


            tableBuilder.withStateInformation(
                    State.NUMBER_END,
                    new StateInformation.Builder()
                            .setShouldBackup(true)
                            .setShouldReadInput(false)
                            .withCharacterToStateMapping(Character.ALPHA,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.DIGIT,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,      State.valueOf(1))
                            .withCharacterToStateMapping(Character.ASTERISK,    State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,      State.valueOf(1))
                            .withCharacterToStateMapping(Character.COLON,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.EQUALS,          State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,   State.valueOf(1))
                            .withCharacterToStateMapping(Character.RABRACKET,   State.valueOf(1))
                            .withCharacterToStateMapping(Character.SPACE,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.OTHER,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.$,           State.valueOf(1))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.COMMENT,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(true)
                            .withCharacterToStateMapping(Character.ALPHA,       State.valueOf(6))
                            .withCharacterToStateMapping(Character.DIGIT,       State.valueOf(6))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,     State.valueOf(6))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,     State.valueOf(7))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,      State.valueOf(6))
                            .withCharacterToStateMapping(Character.ASTERISK,    State.valueOf(6))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,      State.valueOf(6))
                            .withCharacterToStateMapping(Character.COLON,       State.valueOf(6))
                            .withCharacterToStateMapping(Character.EQUALS,          State.valueOf(6))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,   State.valueOf(6))
                            .withCharacterToStateMapping(Character.RABRACKET,   State.valueOf(6))
                            .withCharacterToStateMapping(Character.SPACE,       State.valueOf(6))
                            .withCharacterToStateMapping(Character.OTHER,       State.valueOf(6))
                            .withCharacterToStateMapping(Character.$,           State.valueOf(19))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.COMMENT_END,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(false)
                            .withCharacterToStateMapping(Character.ALPHA,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.DIGIT,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,      State.valueOf(1))
                            .withCharacterToStateMapping(Character.ASTERISK,    State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,      State.valueOf(1))
                            .withCharacterToStateMapping(Character.COLON,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.EQUALS,          State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,   State.valueOf(1))
                            .withCharacterToStateMapping(Character.RABRACKET,   State.valueOf(1))
                            .withCharacterToStateMapping(Character.SPACE,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.OTHER,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.$,           State.valueOf(1))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.LEFT_PARENTHESIS,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(true)
                            .withCharacterToStateMapping(Character.ALPHA,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.DIGIT,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,     State.valueOf(20))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,     State.valueOf(20))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,      State.valueOf(20))
                            .withCharacterToStateMapping(Character.ASTERISK,    State.valueOf(9))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,      State.valueOf(20))
                            .withCharacterToStateMapping(Character.COLON,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.EQUALS,          State.valueOf(20))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,   State.valueOf(20))
                            .withCharacterToStateMapping(Character.RABRACKET,   State.valueOf(20))
                            .withCharacterToStateMapping(Character.SPACE,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.OTHER,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.$,           State.valueOf(19))
                            .build()
            );


            tableBuilder.withStateInformation(
                    State.COMMENT2,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(true)
                            .withCharacterToStateMapping(Character.ALPHA,       State.valueOf(9))
                            .withCharacterToStateMapping(Character.DIGIT,       State.valueOf(9))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,     State.valueOf(9))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,     State.valueOf(9))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,      State.valueOf(9))
                            .withCharacterToStateMapping(Character.ASTERISK,    State.valueOf(10))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,      State.valueOf(9))
                            .withCharacterToStateMapping(Character.COLON,       State.valueOf(9))
                            .withCharacterToStateMapping(Character.EQUALS,          State.valueOf(9))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,   State.valueOf(9))
                            .withCharacterToStateMapping(Character.RABRACKET,   State.valueOf(9))
                            .withCharacterToStateMapping(Character.SPACE,       State.valueOf(9))
                            .withCharacterToStateMapping(Character.OTHER,       State.valueOf(9))
                            .withCharacterToStateMapping(Character.$,           State.valueOf(19))
                            .build()
            );


            tableBuilder.withStateInformation(
                    State.COMMENT2_ASTERISK,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(true)
                            .withCharacterToStateMapping(Character.ALPHA,       State.valueOf(9))
                            .withCharacterToStateMapping(Character.DIGIT,       State.valueOf(9))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,     State.valueOf(9))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,     State.valueOf(9))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,      State.valueOf(9))
                            .withCharacterToStateMapping(Character.ASTERISK,    State.valueOf(10))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,      State.valueOf(11))
                            .withCharacterToStateMapping(Character.COLON,       State.valueOf(9))
                            .withCharacterToStateMapping(Character.EQUALS,          State.valueOf(9))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,   State.valueOf(9))
                            .withCharacterToStateMapping(Character.RABRACKET,   State.valueOf(9))
                            .withCharacterToStateMapping(Character.SPACE,       State.valueOf(9))
                            .withCharacterToStateMapping(Character.OTHER,       State.valueOf(9))
                            .withCharacterToStateMapping(Character.$,           State.valueOf(19))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.COMMENT2_END,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(false)
                            .withCharacterToStateMapping(Character.ALPHA,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.DIGIT,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,      State.valueOf(1))
                            .withCharacterToStateMapping(Character.ASTERISK,    State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,      State.valueOf(1))
                            .withCharacterToStateMapping(Character.COLON,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.EQUALS,          State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,   State.valueOf(1))
                            .withCharacterToStateMapping(Character.RABRACKET,   State.valueOf(1))
                            .withCharacterToStateMapping(Character.SPACE,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.OTHER,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.$,           State.valueOf(1))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.COLON,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(true)
                            .withCharacterToStateMapping(Character.ALPHA,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.DIGIT,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,        State.valueOf(20))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,        State.valueOf(20))
                            .withCharacterToStateMapping(Character.ASTERISK,                State.valueOf(20))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.COLON,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.EQUALS,                  State.valueOf(13))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,     State.valueOf(20))
                            .withCharacterToStateMapping(Character.RABRACKET,               State.valueOf(20))
                            .withCharacterToStateMapping(Character.SPACE,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.OTHER,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.$,                       State.valueOf(19))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.ASSIGN,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(false)
                            .withCharacterToStateMapping(Character.ALPHA,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.DIGIT,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,        State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,        State.valueOf(1))
                            .withCharacterToStateMapping(Character.ASTERISK,                State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.COLON,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.EQUALS,                  State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.RABRACKET,               State.valueOf(1))
                            .withCharacterToStateMapping(Character.SPACE,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.OTHER,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.$,                       State.valueOf(1))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.LEFT_ANGLED_BRACKET,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(true)
                            .withCharacterToStateMapping(Character.ALPHA,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.DIGIT,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,        State.valueOf(20))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,        State.valueOf(20))
                            .withCharacterToStateMapping(Character.ASTERISK,                State.valueOf(20))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.COLON,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.EQUALS,                  State.valueOf(15))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,     State.valueOf(20))
                            .withCharacterToStateMapping(Character.RABRACKET,               State.valueOf(16))
                            .withCharacterToStateMapping(Character.SPACE,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.OTHER,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.$,                       State.valueOf(19))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.LEQ,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(false)
                            .withCharacterToStateMapping(Character.ALPHA,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.DIGIT,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,        State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,        State.valueOf(1))
                            .withCharacterToStateMapping(Character.ASTERISK,                State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.COLON,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.EQUALS,                  State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.RABRACKET,               State.valueOf(1))
                            .withCharacterToStateMapping(Character.SPACE,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.OTHER,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.$,                       State.valueOf(1))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.NEQ,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(false)
                            .withCharacterToStateMapping(Character.ALPHA,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.DIGIT,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,        State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,        State.valueOf(1))
                            .withCharacterToStateMapping(Character.ASTERISK,                State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.COLON,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.EQUALS,                  State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.RABRACKET,               State.valueOf(1))
                            .withCharacterToStateMapping(Character.SPACE,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.OTHER,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.$,                       State.valueOf(1))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.RIGHT_ANGLED_BRACKET,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(true)
                            .withCharacterToStateMapping(Character.ALPHA,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.DIGIT,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,        State.valueOf(20))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,        State.valueOf(20))
                            .withCharacterToStateMapping(Character.ASTERISK,                State.valueOf(20))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.COLON,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.EQUALS,                  State.valueOf(18))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,     State.valueOf(20))
                            .withCharacterToStateMapping(Character.RABRACKET,               State.valueOf(20))
                            .withCharacterToStateMapping(Character.SPACE,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.OTHER,                   State.valueOf(20))
                            .withCharacterToStateMapping(Character.$,                       State.valueOf(19))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.GEQ,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(false)
                            .withCharacterToStateMapping(Character.ALPHA,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.DIGIT,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,        State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,        State.valueOf(1))
                            .withCharacterToStateMapping(Character.ASTERISK,                State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.COLON,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.EQUALS,                  State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.RABRACKET,               State.valueOf(1))
                            .withCharacterToStateMapping(Character.SPACE,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.OTHER,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.$,                       State.valueOf(1))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.ERROR,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(false)
                            .withCharacterToStateMapping(Character.ALPHA,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.DIGIT,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,        State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,        State.valueOf(1))
                            .withCharacterToStateMapping(Character.ASTERISK,                State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.COLON,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.EQUALS,                  State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.RABRACKET,               State.valueOf(1))
                            .withCharacterToStateMapping(Character.SPACE,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.OTHER,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.$,                       State.valueOf(1))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.FUTURE,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(true)
                            .withCharacterToStateMapping(Character.ALPHA,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.DIGIT,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,        State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,        State.valueOf(1))
                            .withCharacterToStateMapping(Character.ASTERISK,                State.valueOf(1))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,       State.valueOf(1))
                            .withCharacterToStateMapping(Character.COLON,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.EQUALS,                  State.valueOf(1))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,     State.valueOf(1))
                            .withCharacterToStateMapping(Character.RABRACKET,               State.valueOf(1))
                            .withCharacterToStateMapping(Character.SPACE,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.OTHER,                   State.valueOf(1))
                            .withCharacterToStateMapping(Character.$,                       State.valueOf(1))
                            .build()
            );

            tableBuilder.withStateInformation(
                    State.END,
                    new StateInformation.Builder()
                            .setShouldBackup(false)
                            .setShouldReadInput(true)
                            .withCharacterToStateMapping(Character.ALPHA,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.DIGIT,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.LEFT_CURLY_BRACE,     State.valueOf(20))
                            .withCharacterToStateMapping(Character.RIGHT_CURLY_BRACE,     State.valueOf(20))
                            .withCharacterToStateMapping(Character.LEFT_PARENTHESIS,      State.valueOf(20))
                            .withCharacterToStateMapping(Character.ASTERISK,    State.valueOf(20))
                            .withCharacterToStateMapping(Character.RIGHT_PARENTHESIS,      State.valueOf(20))
                            .withCharacterToStateMapping(Character.COLON,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.EQUALS,          State.valueOf(20))
                            .withCharacterToStateMapping(Character.LEFT_ANGLED_BRACKET,   State.valueOf(20))
                            .withCharacterToStateMapping(Character.RABRACKET,   State.valueOf(20))
                            .withCharacterToStateMapping(Character.SPACE,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.OTHER,       State.valueOf(20))
                            .withCharacterToStateMapping(Character.$,           State.valueOf(20))
                            .build()
            );

            return tableBuilder.build();
        }
    }


    //Ebben az osztályban azt vizsgáljuk meg, hogy az inputban lévő karaktersorozatok alkotnak-e lexémát
    //és ha igen, akkor a megfelelő lexémát kigyűjtjük a stringből
    class TokenFactory {
        private PushbackInputStream stream;

        public TokenFactory(PushbackInputStream inputStream) {
            this.stream = inputStream;
        }

        List<Token> getTokens() throws IOException {
            var tokens = new ArrayList<Token>();

            char buffer = '\0';
            var table = Util.getTable();

            var currentState = State.START;
            String tokenBuffer = "";

            while(true) {
                if (currentState == State.START) {
                    tokenBuffer = "";
                }

                if (table.shouldReadInputInState(currentState)) {
                    buffer = (char) this.stream.read();
                }

                Character input = Character.valueOf(buffer);
                currentState = table.getNextState(currentState, input);

                if (currentState == State.END) {
                    tokens.add(Token.END);
                    break;
                }

                if (currentState == State.ERROR) {
                    currentState = State.START;
                    continue;
                }

                if (table.shouldReadInputInState(currentState)) {
                    tokenBuffer += buffer;
                }

                switch (currentState) {
                    case IDENTIFIER_END -> tokens.add(Token.IDENTIFIER);
                    case NUMBER_END -> tokens.add(Token.CONSTANT);
                    case COMMENT_END -> {
                        tokenBuffer += buffer;
                        tokens.add(Token.COMMENT1);
                    }
                    case COMMENT2_END -> {
                        tokenBuffer += buffer;
                        tokens.add(Token.COMMENT2);
                    }
                    case ASSIGN -> {
                        tokenBuffer += buffer;
                        tokens.add(Token.ASSIGN);
                    }
                    case LEQ -> {
                        tokenBuffer += buffer;
                        tokens.add(Token.LEQ);
                    }
                    case NEQ -> {
                        tokenBuffer += buffer;
                        tokens.add(Token.NEQ);
                    }
                    case GEQ -> {
                        tokenBuffer += buffer;
                        tokens.add(Token.GEQ);
                    }
                }

                if (table.shouldBackupInState(currentState)) {
                    stream.unread(buffer);
                }

                if (!table.shouldReadInputInState(currentState)) {
                    currentState = State.START;
                }
            }


            return tokens;
        }
    }

    //Enum segítségével létrehozzuk a lexémákat reprezentáló tokeneket, változónév("lexéma") formájában
    enum Token {
        IDENTIFIER("azonosító"),
        CONSTANT("konstans"),
        COMMENT1("kommentár-1"),
        COMMENT2("kommentár-2"),
        ASSIGN(" := "),
        LEQ(" <= "),
        NEQ(" <> "),
        GEQ(" >= "),
        END("$");

        private final String placeholder;

        Token(String placeholder) {
            this.placeholder = placeholder;
        }

        @Override
        public String toString() {
            return this.placeholder;
        }
    }

    //Ebben az osztályban a tábla struktúráját készítjük el, illetve segédfüggvényeket készítünk el hozzá,
    //amelyek könnyítik a használatát
    class Table {
        private EnumMap<State, StateInformation> stateInformationMapping;

        public Table(EnumMap<State, StateInformation> stateInformationMapping) {
            this.stateInformationMapping = stateInformationMapping;
        }

        public boolean shouldReadInputInState(State state) {
            return stateInformationMapping.get(state).shouldReadInput();
        }

        public boolean shouldBackupInState(State state) {
            return stateInformationMapping.get(state).shouldBackup();
        }

        public State getNextState(
                State currentState, Character input) {
            return  stateInformationMapping.get(currentState).getNextState(input);
        }

        public static class Builder {
            private EnumMap<State, StateInformation> stateInformationMapping;

            public Builder() {
                this.stateInformationMapping = new EnumMap<>(State.class);
            }

            Table.Builder withStateInformation(
                    State state, StateInformation stateInformation) {
                this.stateInformationMapping.put(state, stateInformation);
                return this;
            }

            Table build() {
                if (this.stateInformationMapping.size() != State.values().length) {
                    throw new IllegalStateException("Some state hasn't got any information mapped to it.");
                }

                return new Table(this.stateInformationMapping);
            }
        }
    }

    class StateInformation {
        private boolean shouldBackup;
        private boolean shouldReadInput;
        private EnumMap<Character, State> nextStateMapping;


        private StateInformation(
                boolean shouldBackup,
                boolean shouldReadInput,
                EnumMap<Character, State> nextStateMapping
        ) {
            this.shouldBackup = shouldBackup;
            this.shouldReadInput = shouldReadInput;
            this.nextStateMapping = nextStateMapping;
        }

        public boolean shouldBackup() {
            return shouldBackup;
        }

        public boolean shouldReadInput() {
            return shouldReadInput;
        }

        public State getNextState(Character input) {
            return this.nextStateMapping.get(input);
        }

        public static class Builder {
            private boolean shouldBackup;
            private boolean shouldReadInput;
            private EnumMap<Character, State> nextStateMapping;

            public Builder() {
                nextStateMapping = new EnumMap<>(Character.class);
            }

            public StateInformation.Builder withCharacterToStateMapping(
                    Character character, State state) {
                this.nextStateMapping.put(character, state);

                return this;
            }

            public StateInformation.Builder setShouldReadInput(boolean shouldReadInput) {
                this.shouldReadInput = shouldReadInput;
                return this;
            }

            public StateInformation.Builder setShouldBackup(boolean shouldBackup) {
                this.shouldBackup = shouldBackup;
                return this;
            }

            public StateInformation build() {
                if (this.nextStateMapping.size() != Character.values().length) {
                    throw new IllegalStateException("Some character hasn't got any state mapped to it.");
                }

                return new StateInformation(
                        this.shouldBackup,
                        this.shouldReadInput,
                        this.nextStateMapping
                );
            }
        }
    }

    //Enum, ami tartalmazza az összes állapotot
    enum State {
        START,
        IDENTIFIER,
        IDENTIFIER_END,
        NUMBER,
        NUMBER_END,
        COMMENT,
        COMMENT_END,
        LEFT_PARENTHESIS,
        COMMENT2,
        COMMENT2_ASTERISK,
        COMMENT2_END,
        COLON,
        ASSIGN,
        LEFT_ANGLED_BRACKET,
        LEQ,
        NEQ,
        RIGHT_ANGLED_BRACKET,
        GEQ,
        ERROR,
        FUTURE,
        END;

        public static State valueOf(int stateId) {
            for(var state: State.values()) {
                if(state.ordinal() == stateId - 1) {
                    return state;
                }
            }

            throw new IllegalArgumentException(String.format("Can't construct State from value: %d", stateId));
        }
    }

    enum Character {
        ALPHA("Alpha"),
        DIGIT("Digit"),
        LEFT_CURLY_BRACE("Left curly brace"),
        RIGHT_CURLY_BRACE("Right curly brace"),
        LEFT_PARENTHESIS("Left parenthesis"),
        ASTERISK("Asterisk"),
        RIGHT_PARENTHESIS("Right parenthesis"),
        COLON("Colon"),
        EQUALS("Equals"),
        LEFT_ANGLED_BRACKET("Left angled bracket"),
        RABRACKET("Right angled bracket"),
        SPACE("Space"),
        OTHER("Other"),
        $("EOF");

        private final String placeholder;

        Character(String placeholder) {
            this.placeholder = placeholder;
        }

        public static Character valueOf(char c) {
            if (java.lang.Character.isAlphabetic(c)) {
                return ALPHA;
            }

            if (java.lang.Character.isDigit(c)) {
                return DIGIT;
            }

            return switch (c) {
                case '{' -> Character.LEFT_CURLY_BRACE;
                case '}' -> Character.RIGHT_CURLY_BRACE;
                case '(' -> Character.LEFT_PARENTHESIS;
                case '*' -> Character.ASTERISK;
                case ')' -> Character.RIGHT_PARENTHESIS;
                case ':' -> Character.COLON;
                case '=' -> Character.EQUALS;
                case '<' -> Character.LEFT_ANGLED_BRACKET;
                case '>' -> Character.RABRACKET;
                case ' ' -> Character.SPACE;
                case '$' -> Character.$;
                default -> Character.OTHER;
            };
        }

        @Override
        public String toString() {
            return this.placeholder;
        }
    }
