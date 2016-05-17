package com.lps.game;

public enum Type {
    FLUENT, AND, NOT, IMPLY, CONSEQUENT;

    public static boolean isValid(Type[] types) {
        Type previous = null;
        boolean implied = false;

        for (int i = 0; i < types.length; ++i) {

            if (types[i] == null) continue;

            if (previous != null) {
                switch(previous) {
                    case FLUENT:
                        if (types[i] != AND && types[i] != IMPLY) return false;
                        break;
                    case AND:
                        if (types[i] != FLUENT && types[i] != NOT) return false;
                        break;
                    case NOT:
                        if (types[i] != FLUENT && types[i] != NOT) return false;
                        break;
                    case IMPLY:
                        implied = true;
                        if (types[i] != CONSEQUENT) return false;
                        break;
                    case CONSEQUENT:
                        return false;
                }
            }

            previous = types[i];
        }

        if (previous != null) {
            return implied && previous == CONSEQUENT;
        } else {

            return true;
        }
    }
}
