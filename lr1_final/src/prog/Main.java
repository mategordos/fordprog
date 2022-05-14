package prog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Main {
    public static void main( String[] args ) throws IOException, ParseException {
        System.out.println("Add meg a bemeneti stringet(a,b)");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String input = reader.readLine();

        for(var c: input.toCharArray()) {
            if(!(c == 'a' || c == 'b')) {
                System.out.println("Az input csak ezeket tartalmazhatja: a b");

                System.exit(-1);
            }
        }

        State state = new State(input);
        Table table = new Table();
        ReductionRuleset reductionRuleset = new ReductionRuleset();

        while (true) {
            String action = table.getNThRow(state.top().stateOrdinal()).getGotoOf(state.front());

            if (action.charAt(0) == 's') {
                char newSymbol = state.front();
                int newState = action.charAt(1) - '0';
                state.popFromWord();
                state.pushToStack(new Rule(newSymbol, newState));
            } else if (action.charAt(0) == 'r') {
                ReductionRule reductionRule = reductionRuleset.getRule(action);

                for(int i = 0; i < reductionRule.lengthOfRule(); ++i) {
                    state.popFromStack();
                }

                int stateAfterReduction = state.top().stateOrdinal();
                char newSymbol = reductionRule.substituteeCharacter();

                int newState = NumberFormat.getInstance().parse(
                        table.getNThRow(stateAfterReduction).getGotoOf(newSymbol)
                ).intValue();
                state.pushToStack(new Rule(newSymbol, newState));
            } else if (action.charAt(0) == 'e') {
                System.out.println("Elutasítva");
                break;
            } else if (action.charAt(0) == 'a') {
                System.out.println("Elfogadva");
                break;
            } else {
                state.pushToStack(new Rule(state.front(),
                        (Integer) NumberFormat.getInstance().parse(action)));
            }
        }

    }
}

//Állapotok leírása, segédfüggvények melyek segítik
class State {
    private final Stack<Rule> first;
    private final LinkedList<Character> second;

    public State(String input) {
        this.first = new Stack<>();
        this.second = new LinkedList<>();

        first.push(new Rule('#', 0));

        for(Character c: input.toCharArray()) {
            this.second.add(c);
        }

        second.add('#');
    }

    public Rule top() {
        return first.peek();
    }

    public char front() {
        return second.getFirst();
    }

    public void popFromWord() {
        this.second.remove(0);
    }

    public void pushToStack(Rule rule) {
        first.push(rule);
    }

    public void popFromStack() {
        first.pop();
    }
}

class ReductionRule {
    private Character substituteeSymbol;
    private String subsitutedSymbols;

    public ReductionRule(Character substituteeSymbol, String subsitutedSymbols) {
        this.substituteeSymbol = substituteeSymbol;
        this.subsitutedSymbols = subsitutedSymbols;
    }

    public Character substituteeCharacter() {
        return substituteeSymbol;
    }

    public int lengthOfRule() {
        return subsitutedSymbols.length();
    }
}

//Táblázatnak értékadás ("e" string reprezentálja az üres mezőket)
class Table {
    public Table() {
        this.table = new HashMap<>();

        table.put(0,
                new TableRow(
                        new String[] {"s3", "s4", "e", "1", "2"}
                )
        );
        table.put(1,
                new TableRow(
                        new String[] {"e", "e", "a", "e", "e"}
                )
        );
        table.put(2,
                new TableRow(
                        new String[] {"s6", "s7", "e", "e", "5"}
                )
        );
        table.put(3,
                new TableRow(
                        new String[] {"s3", "s4", "e", "e", "8"}
                )
        );
        table.put(4,
                new TableRow(
                        new String[] {"r3", "r3", "e", "e", "e"}
                )
        );
        table.put(5,
                new TableRow(
                        new String[] {"e", "e", "r1", "e", "e"}
                )
        );
        table.put(6,
                new TableRow(
                        new String[] {"s6", "s7", "e", "e", "e"}
                )
        );
        table.put(7,
                new TableRow(
                        new String[] {"e", "e", "r3", "e", "e"}
                )
        );
        table.put(8,
                new TableRow(
                        new String[] {"r2", "r2", "e", "e", "e"}
                )
        );
        table.put(9,
                new TableRow(
                        new String[] {"e", "e", "r2", "e", "e"}
                )
        );
    }

    public TableRow getNThRow(int n) {
        return table.get(n);
    }

    private Map<Integer, TableRow> table;
}

//Táblázat struktúrája, a sorok megadása
class TableRow {
    private Map<Character, String> gotoTable;

    public TableRow(String[] states) {
        this.gotoTable = new HashMap<>();

        gotoTable.put('a', states[0]);
        gotoTable.put('b', states[1]);
        gotoTable.put('#', states[2]);
        gotoTable.put('S', states[3]);
        gotoTable.put('A', states[4]);
    }

    public String getGotoOf(Character character) {
        return gotoTable.get(character);
    }
}

//A redukciós szabályok megadása
class ReductionRuleset {
    private Map<String, ReductionRule> rules;

    public ReductionRuleset() {
        this.rules = new HashMap<>();
        rules.put("r1", new ReductionRule('S', "AA"));
        rules.put("r2", new ReductionRule('A', "aA"));
        rules.put("r3", new ReductionRule('A', "b"));
    }

    public ReductionRule getRule(String key) {
        return rules.get(key);
    }
}

//Általános leírás, mint szabály osztályban
class Rule {
    private Character inputCharacter;
    private Integer newStateOrdinal;

    public Rule(Character inputCharacter, Integer newStateOrdinal) {
        this.inputCharacter = inputCharacter;
        this.newStateOrdinal = newStateOrdinal;
    }

    public int stateOrdinal() {
        return this.newStateOrdinal;
    }
}

