/**
 * 
 */
package com.bob.lps.model;

import java.util.Hashtable;

/**
 * This class represents constants or values. It implements {@link Unifiable}.
 * The class has a static attribute to differentiate constants of the same
 * names, even if they will be unified if they have the same name.
 * 
 * @author Alexandre Camus
 * 
 */
public class Constant implements Unifiable {

	private String printName = null;
	private static int nextId = 1;
	private int id;
	
	/**
	 * Constructor of the class. 
	 */
	public Constant() {
		this.id = nextId++;
	}
	
	/**
	 * Constructor of the class.
	 * 
	 * @param printName
	 *            the name of the constant.
	 */
	public Constant(String printName) {
		this();
		this.printName = printName;
	}
	
	/**
	 * Gets the name of the constant.
	 * 
	 * @return the name of the constant. It will return {@code null} if there is
	 *         no one.
	 * @see Unifiable#getName()
	 */
	public String getName() {
		return this.printName;
	}

	/**
	 * Replaces all the variables in the constant according to the specified
	 * bindings. This returns the constant itself as it is not a variable and
	 * contains no variable.
	 * <p>
	 * This method is recursive over all {@link PCExpression} implementations.
	 * This is a terminal case.
	 *
	 * @param s
	 *            the {@code SubstitutionSet} that contains the bindings of the
	 *            variables so far.
	 * @return a {@code Constant} object representing the constant.
	 * @see PCExpression#replaceVariables(SubstitutionSet)
	 */
	@Override
	public Constant replaceVariables(SubstitutionSet s) {
		// Constant doesn't need any replacement
		return this;
	}

	/**
	 * Standardizes the variables in order to be sure that there won't be any
	 * variable clashes. This returns the constant itself as it is not a
	 * variable and contains no variable.
	 * <p>
	 * This method is recursive over all {@code PCExpression} implementations.
	 * This is a terminal case.
	 *
	 * @param newVars
	 *            is a parameter to save over the recursion all the variable
	 *            replacements done so far.
	 * @return a {@code Constant} object representing the standardized constant.
	 * @see PCExpression#standardizeVariablesApart(Hashtable)
	 */
	@Override
	public Constant standardizeVariablesApart(Hashtable<Variable, Variable> newVars) {
		// Constant doesn't need any standardization
		return this;
	}

	/**
	 * Unifies the constant with the specified {@code expr} expression
	 * given the bindings {@code s}. This tries to get or addActor bindings in order
	 * to make logically equivalent the constant and the specified
	 * expression.
	 * <p>
	 * This method is recursive over all {@code Unifiable} implementations.
	 *
	 * @param expr
	 *            an expression to unify with the constant.
	 * @param s
	 *            the {@code SubstitutionSet} object representing the bindings
	 *            so far and/or the constraints applied.
	 * @return a {@code SubstitutionSet} object that contains all the bindings
	 *         needed to unify the constant to the specified expression.
	 * @see Unifiable#unify(Unifiable, SubstitutionSet)
	 */
	@Override
	public SubstitutionSet unify(Unifiable expr, SubstitutionSet s) {
		// Equal constants can be unified
		if (expr instanceof Constant) {
			if (this == expr) {

				return new SubstitutionSet(s);
			}

			if (this.printName.equals(expr.getName())) {

				return new SubstitutionSet(s);
			}

		// If this is a variable, use the unify method of Variable
		} else if (expr instanceof Variable) {

			return expr.unify(this, s);

		// Otherwise it can't be unified to a sentence or a different constant
		}

		return null;
	}

	/**
	 * Returns the constant under the form of:
	 * "constantName_id".
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		if (this.printName != null) {
			return this.printName;
		}
		
		return "constant_" + this.id;
	}
	
}
