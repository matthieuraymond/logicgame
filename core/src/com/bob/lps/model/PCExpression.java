/**
 * 
 */
package com.bob.lps.model;

import java.util.Hashtable;

/**
 * This interface represents the predicate expressions.
 * 
 * @author Alexandre Camus
 * 
 */
public interface PCExpression {
	
	/**
	 * Replaces all the variables in the predicate expression according to the
	 * specified bindings.
	 * <p>
	 * This method is recursive over all {@link PCExpression} implementations.
	 * 
	 * @param s
	 *            the {@code SubstitutionSet} that contains the bindings of the
	 *            variables so far.
	 * @return a {@code PCExpression} object representing the bound predicate
	 *         expression.
	 * @see SubstitutionSet
	 */
	public PCExpression replaceVariables(SubstitutionSet s);
	
	/**
	 * Standardizes the variables in order to be sure that there won't be any
	 * variable clashes.
	 * <p>
	 * This method is recursive over all {@code PCExpression} implementations.
	 * 
	 * @param newVars
	 *            is a parameter to save over the recursion all the variable
	 *            replacements done so far.
	 * @return a {@code PCExpression} object representing the standardized
	 *         predicate expression.
	 */
	public PCExpression standardizeVariablesApart(Hashtable<Variable, Variable> newVars);

}
