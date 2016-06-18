package com.bob.game.inputs;

public enum Type {
    FLUENT, AND, NOT, IMPLY, CONSEQUENT;

    public static boolean isValid(Type[] types) {
        Type last = null;
        boolean implied = false;
        boolean oneFluent = false;

        for (Type type : types) {

            if (type == null) continue;

            if (last != null) {
                switch (last) {
                    case FLUENT:
                        if (type != AND && type != IMPLY) return false;
                        oneFluent = true;
                        break;
                    case AND:
                        if (type != FLUENT && type != NOT) return false;
                        break;
                    case NOT:
                        if (type != FLUENT && type != NOT) return false;
                        break;
                    case IMPLY:
                        implied = true;
                        if (type != CONSEQUENT) return false;
                        break;
                    case CONSEQUENT:
                        return false;
                }
            }

            last = type;
        }

        if (last != null) {
            return (implied && last == CONSEQUENT && oneFluent) || (!implied && !oneFluent && last == CONSEQUENT);
        } else {
            return true;
        }
    }
}
