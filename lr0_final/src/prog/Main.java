package prog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main( String[] args ) throws IOException {
        ArrayList<String> actions = new ArrayList<>();
        System.out.println("Adj meg egy bemenet inputot! (a,b,c,d)");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String input = reader.readLine();

        for(var c: input.toCharArray()) {
            if(!(c == 'a' || c == 'b' || c == 'c' || c == 'd')) {
                System.out.println("Az input csak ezeket tartalmazhatja: a b c d");

                System.exit(-1);
            }
        }

        State state = new State(input);
        Table table = new Table();
        ReductionRuleset reductionRuleset = new ReductionRuleset();

        while (!state.isInAcceptState()) {
            String action = table.getNThRow(state.top().stateOrdinal()).getAction();
            actions.add(action);
            if (action.charAt(0) == 's') {
                int newState = table.getNThRow(state.top().stateOrdinal()).getGotoOf(state.front());
                char newSymbol = state.front();
                state.popFromWord();
                state.pushToStack(new Rule(newSymbol, newState));
            }

            if (action.charAt(0) == 'r') {
                ReductionRule reductionRule = reductionRuleset.getRule(action);

                for(int i = 0; i < reductionRule.lengthOfRule(); ++i) {
                    state.popFromStack();
                }

                int stateAfterReduction = state.top().stateOrdinal();
                char newSymbol = reductionRule.substituteeCharacter();

                int newState = table.getNThRow(stateAfterReduction).getGotoOf(newSymbol);
                state.pushToStack(new Rule(newSymbol, newState));
            }

            if(state.isInErrorState()) {
                for (var act : actions) {
                    System.out.println(act);
                }
                System.out.println("Elutasítva");
                System.exit(1);
            }
        }

        for (var act : actions) {
            System.out.println(act);
        }

        System.out.println("Elfogadva");
    }
}

class TableRow {
    private final String action;
    private Map<Character, Integer> gotoTable;

    public TableRow(String action, int[] states) {
        this.action = action;
        this.gotoTable = new HashMap<>();

        gotoTable.put('S', states[0]);
        gotoTable.put('A', states[1]);
        gotoTable.put('a', states[2]);
        gotoTable.put('b', states[3]);
        gotoTable.put('c', states[4]);
        gotoTable.put('d', states[5]);
    }

    public String getAction() {
        return action;
    }

    public int getGotoOf(Character character) {
        return gotoTable.get(character);
    }
}

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

class ReductionRuleset {
    private Map<String, ReductionRule> rules;

    public ReductionRuleset() {
        this.rules = new HashMap<>();
        rules.put("r1", new ReductionRule('S', "aAd"));
        rules.put("r2", new ReductionRule('A', "bA"));
        rules.put("r3", new ReductionRule('A', "c"));
    }

    public ReductionRule getRule(String key) {
        return rules.get(key);
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

    public boolean isInAcceptState() {
        return top().stateOrdinal() == 1 && front() == '#';
    }

    public boolean isInErrorState () {
        return top().stateOrdinal() == -1;
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

class Table {
    public Table() {
        this.table = new HashMap<>();

        table.put(0,
                new TableRow(
                        "s", new int[] {
                        1, -1, 2, -1, -1, -1
                }
                )
        );
        table.put(1,
                new TableRow(
                        "accept", new int[] {-1, -1, -1, -1, -1, -1}
                )
        );
        table.put(2,
                new TableRow(
                        "s", new int[] {-1, 3, -1, 4, 4, -1}
                )
        );
        table.put(3,
                new TableRow(
                        "s", new int[] {-1, -1, -1, -1, -1, 6}
                )
        );
        table.put(4,
                new TableRow(
                        "s", new int[] {-1, 7, -1, 4, 5, -1}
                )
        );
        table.put(5,
                new TableRow(
                        "r3", new int[] {-1, -1, -1, -1, -1, -1}
                )
        );
        table.put(6,
                new TableRow(
                        "r1", new int[] {-1, -1, -1, -1, -1, -1}
                )
        );
        table.put(7,
                new TableRow(
                        "r2", new int[] {-1, -1, -1, -1, -1, -1}
                )
        );

    }

    public TableRow getNThRow(int n) {
        return table.get(n);
    }

    private Map<Integer, TableRow> table;
}
