package main.java.automaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Automaton {

    public class AutomatonTransition {
        private String currentState;
        private Map<String, String> symbolState;

        public AutomatonTransition(String currentState, Map<String, String> symbolState) {
            this.currentState = currentState;
            this.symbolState = symbolState;
        }

        public AutomatonTransition() {
            currentState = "";
            symbolState = new HashMap<>();
        }

        public String getCurrentState() {
            return currentState;
        }

        public void setCurrentState(String currentState) {
            this.currentState = currentState;
        }

        public Map<String, String> getSymbolState() {
            return symbolState;
        }

        public void setSymbolState(Map<String, String> symbolState) {
            this.symbolState = symbolState;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Current State: ").append(currentState).append(", ");
            builder.append("Symbol State: ").append(symbolState);
            return builder.toString();
        }
    }

    private List<String> alphabet;
    private List<String> states;
    private String initialState;
    private List<String> finalStates;
    private List<AutomatonTransition> transitions;

    public Automaton() {
        alphabet = new ArrayList<>();
        states = new ArrayList<>();
        finalStates = new ArrayList<>();
        transitions = new ArrayList<>();
    }

    public Automaton(List<String> alphabet, List<String> states, String initialState, List<String> finalStates,
            List<AutomatonTransition> transitions) {
        this.alphabet = alphabet;
        this.states = states;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.transitions = transitions;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(List<String> alphabet) {
        this.alphabet = alphabet;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(List<String> finalStates) {
        this.finalStates = finalStates;
    }

    public List<AutomatonTransition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<AutomatonTransition> transitions) {
        this.transitions = transitions;
    }

    public void addTransition(String currentState, Map<String, String> symbolState) {
        AutomatonTransition transition = new AutomatonTransition(currentState, symbolState);
        transitions.add(transition);
    }

    public void addState(String state) {
        states.add(state);
    }

    public void addAlphabet(String letter) {
        alphabet.add(letter);
    }

    public void addFinalState(String finalState) {
        finalStates.add(finalState);
    }

    public void showAutomaton() {
        System.out.println("Automaton:");
        System.out.println("States: " + states);
        System.out.println("Initial State: " + initialState);
        System.out.println("Final States: " + finalStates);
        System.out.println("Transitions: ");
        for (AutomatonTransition transition : transitions) {
            System.out.println("Current State: " + transition.getCurrentState());
            System.out.println("Symbol State: " + transition.getSymbolState());
        }
    }

    public String getNextState(String currentState, String symbol) {
        for (AutomatonTransition transition : transitions) {
            if (transition.getCurrentState().equals(currentState)) {
                return transition.getSymbolState().get(symbol);
            }
        }
        return null;
    
    }

    public boolean isFinalState(String currentState) {
        return finalStates.contains(currentState);
    }
    
    public String[] TransitionDOT() {
        String[] dot = new String[transitions.size()];
        for (int i = 0; i < transitions.size(); i++) {
            AutomatonTransition transition = transitions.get(i);
            dot[i] = transition.getCurrentState() + " -> " + transition.getSymbolState().get(alphabet.get(0)) + " [label=\"" + alphabet.get(0) + "\"];\n";
            dot[i] += transition.getCurrentState() + " -> " + transition.getSymbolState().get(alphabet.get(1)) + " [label=\"" + alphabet.get(1) + "\"];\n";
        }
        return dot;
    }
    
}