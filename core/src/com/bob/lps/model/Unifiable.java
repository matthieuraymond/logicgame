/**
 * 
 */
package com.bob.lps.model;

/**
 * This interface represents a subdivision among the predicate expressions. It
 * extends the {@code PCExpression} interface and creates the subdivision of the
 * predicate expressions that can be unified.
 * 
 * @author Alexandre Camus
 * 
 */
public interface Unifiable extends PCExpression {

	/**
	 * Gets the name of the expression.
	 * 
	 * @return the name of the expression. It will return {@code null} if there is
	 *         no one.
	 */
	public String getName();

	/**
	 * Unifies the expression with the specified {@code expr} expression
	 * given the bindings {@code s}. This tries to get or addActor bindings in order
	 * to make logically equivalent the expression and the specified
	 * expression.
	 * <p>
	 * This method is recursive over all {@code Unifiable} implementations.
	 * 
	 * @param expr
	 *            an expression to unify with the expression.
	 * @param s
	 *            the {@code SubstitutionSet} object representing the bindings
	 *            so far and/or the constraints applied.
	 * @return a {@code SubstitutionSet} object that contains all the bindings
	 *         needed to unify the expression to the specified expression.
	 * @see SubstitutionSet
	 */
	public SubstitutionSet unify(Unifiable expr, SubstitutionSet s);
	
}
