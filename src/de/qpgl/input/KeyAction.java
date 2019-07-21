package de.qpgl.input;

@FunctionalInterface
public interface KeyAction {
    void execute(int[] keyCodes);
}
