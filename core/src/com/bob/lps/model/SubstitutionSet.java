/**
 * 
 */
package com.bob.lps.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the bindings of different variables to unify to
 * expression or in the context of a solution to a clause.
 * <p>
 * The bindings are stored as {@code Unifiable} in a {@code Map} sorted by
 * {@code Variable} object.
 * 
 * @author Alexandre Camus
 * 
 */
public class SubstitutionSet {

	private Map<Variable, Unifiable> bindings = new HashMap<Variable, Unifiable>();

	/**
	 * Constructor of the class.
	 */
	public SubstitutionSet() {
	}

	/**
	 * Constructor of the class.
	 * 
	 * @param s the {@code SubstitutionSet} object to copy.
	 */
	public SubstitutionSet(SubstitutionSet s) {
		this.bindings = new HashMap<Variable, Unifiable>(s.bindings);
	}

	/**
	 * Adds the specified binding to the substitution set.
	 * 
	 * @param v
	 *            the variable that is bound by the binding.
	 * @param expr
	 *            the expression to which the variable is bound.
	 */
	public void add(Variable v, Unifiable expr) {
		this.bindings.put(v, expr);
	}

	/**
	 * Deletes all the bindings.
	 */
	public void clear() {
		this.bindings.clear();
	}

	/**
	 * Gets the binding of the specified variable.
	 * 
	 * @param v
	 *            the variable of the binding to get.
	 * @return a {@code Unifiable} object that represent the expression to which
	 *         the variable is bound.
	 */
	public Unifiable getBinding(Variable v) {
		return this.bindings.get(v);
	}

	/**
	 * Checks if the specified variable has a binding.
	 * 
	 * @param v
	 *            the variable to check if it is bound.
	 * @return true if the variable is bound, otherwise false.
	 */
	public boolean isBound(Variable v) {
		return this.bindings.containsKey(v);
	}

	/**
	 * Returns the substitution set in the form of:
	 * "Bindings: [variable=[expression], variable=[expression], ...]".
	 * 
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return "Bindings: [" + this.bindings.toString() + "]";
	}

}
