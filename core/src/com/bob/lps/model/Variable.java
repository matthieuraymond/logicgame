/**
 * 
 */
package com.bob.lps.model;

import java.util.Hashtable;

/**
 * This class represents variables. It implements {@link Unifiable}. The class
 * has a static attribute to differentiate variables of the same names.
 * 
 * @author Alexandre Camus
 * 
 */
public class Variable implements Unifiable {

	private String printName = null;
	private static int nextId = 1;
	private int id;
	
	/**
	 * Constructor of the class. 
	 */
	public Variable() {
		this.id = nextId++;
	}
	
	/**
	 * Constructor of the class.
	 * 
	 * @param printName
	 *            the name of the variable.
	 */
	public Variable(String printName) {
		this();
		this.printName = printName;
	}
	
	/**
	 * Constructor of the class.
	 * 
	 * @param v
	 *            the variable object to copy and to create a new variable.
	 */
	public Variable(Variable v) {
		this();
		this.printName = v.printName;
	}
	
	/**
	 * Gets the name of the variable.
	 * 
	 * @return the name of the variable. It will return {@code null} if there is
	 *         no one.
	 * @see Unifiable#getName()
	 */
	@Override
	public String getName() {
		return this.printName;
	}

	/**
	 * Replaces all the variables in the variable according to the specified
	 * bindings. This returns the clause to which the variable has been bound.
	 * <p>
	 * This method is recursive over all {@link PCExpression} implementations.
	 * This is a terminal case.
	 *
	 * @param s
	 *            the {@code SubstitutionSet} that contains the bindings of the
	 *            variables so far.
	 * @return a {@code PCExpression} object representing the clause to which
	 *         the variable is bound.
	 * @see PCExpression#replaceVariables(SubstitutionSet)
	 */
	@Override
	public PCExpression replaceVariables(SubstitutionSet s) {
		// If the variable is bound replace the variable by its binding.
		if (s.isBound(this)) {

			return s.getBinding(this).replaceVariables(s);

		// Otherwise don't replace it since it can't be
		} else {

			return this;
		}
	}

	/**
	 * Standardizes the variables in order to be sure that there won't be any
	 * variable clashes. This returns a copy of the variable but with a
	 * different id.
	 * <p>
	 * This method is recursive over all {@code PCExpression} implementations.
	 * This is a terminal case.
	 *
	 * @param newVars
	 *            is a parameter to save over the recursion all the variable
	 *            replacements done so far.
	 * @return a {@code Variable} object representing the standardized variable.
	 * @see PCExpression#standardizeVariablesApart(Hashtable)
	 */
	@Override
	public Variable standardizeVariablesApart(Hashtable<Variable, Variable> newVars) {
		// Get the standardize version of the variable
		Variable newVar = newVars.get(this);

		// If the variable hasn't already be standardized, standardize it
		if (newVar == null) {
			// To standardize it, just create a new one (different id) with same other parameters
			newVar = new Variable(this);
			newVars.put(this, newVar);
		}

		return newVar;
	}

	/**
	 * Unifies the variable with the specified {@code expr} expression
	 * given the bindings {@code s}. This tries to get or addActor bindings in order
	 * to make logically equivalent the variable and the specified
	 * expression.
	 * <p>
	 * This method is recursive over all {@code Unifiable} implementations.
	 *
	 * @param expr
	 *            an expression to unify with the variable.
	 * @param s
	 *            the {@code SubstitutionSet} object representing the bindings
	 *            so far and/or the constraints applied.
	 * @return a {@code SubstitutionSet} object that contains all the bindings
	 *         needed to unify the variable to the specified expression.
	 * @see Unifiable#unify(Unifiable, SubstitutionSet)
	 */
	@Override
	public SubstitutionSet unify(Unifiable expr, SubstitutionSet s) {
		// Equal variables are unified
		if (this == expr) {

			return s;

		// If the variable has a binding, try to unify the binding with expr
		} else if (s.isBound(this)) {

			return s.getBinding(this).unify(expr, s);

		// If expr is a variable and has a binding, try to unify the binding with this variable
		} else if (expr instanceof Variable && s.isBound((Variable) expr)) {

			return s.getBinding((Variable) expr).unify(this, s);

		// Otherwise bind the variable to expr and succeed
		} else {
			SubstitutionSet sNew = new SubstitutionSet(s);
			sNew.add(this, expr);

			return sNew;
		}
	}

	/**
	 * Returns the variable under the form of:
	 * "variableName_id".
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		if (this.printName != null) {
			return this.printName + "_" + this.id;
		}
		
		return "V" + this.id;
	}

}
