package de.qpgl.input;

import de.mathlib.Utils;

import java.util.LinkedList;
import java.util.List;

public class KeyBind {

    //---------- VARIABLES ----------

    private KeyCodeAction[] keyCodeActions;
    private KeyAction action;
    private String name;

    //---------- CONSTRUCTOR ----------
    public KeyBind(String name, KeyCodeAction keyCodeAction, KeyAction action) {
        this(name, new KeyCodeAction[]{keyCodeAction}, action);
    }

    public KeyBind(String name, KeyAction action, KeyCodeAction... keyCodeActions) {
        this(name, keyCodeActions, action);
    }

    public KeyBind(String name, KeyCodeAction[] keyCodeActions, KeyAction action) {
        this.name = name;
        this.keyCodeActions = keyCodeActions;
        this.action = action;
    }

    /**
     * Checks the action conditions and executes the action if they are fulfilled
     */
    public void checkAction() {
        for (KeyCodeAction keyCodeAction : keyCodeActions) {
            if(keyCodeAction.isFulfilled()) {
                action.execute(getKeyCodes());
                return;
            }
        }
    }

    /**
     * Returns an array of all keyCodes involved in any action with any condition
     * @return keyCode array
     */
    private int[] getKeyCodes() {
        List<Integer> keyCodes = new LinkedList<>();
        for (KeyCodeAction keyCodeAction : keyCodeActions) {
            keyCodes.addAll(keyCodeAction.getKeyCodes());
        }
        return Utils.listToArray(keyCodes);
    }

}
