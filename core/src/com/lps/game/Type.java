package com.lps.game;

public enum Type {
    FLUENT, AND, NOT, IMPLY, CONSEQUENT;

    public static boolean isValid(Type[] types) {
        Type last = null;
        boolean implied = false;
        boolean oneFluent = false;

        for (int i = 0; i < types.length; ++i) {

            if (types[i] == null) continue;

            if (last != null) {
                switch(last) {
                    case FLUENT:
                        if (types[i] != AND && types[i] != IMPLY) return false;
                        oneFluent = true;
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

            last = types[i];
        }

        if (last != null) {
            return implied && last == CONSEQUENT && oneFluent;
        } else {
            return true;
        }
    }
}
