package com.bob.lps.model;

/**
 * This interface represents a subdivision among the predicate expressions. It
 * extends the {@code PCExpression} interface and creates the subdivision of the
 * predicate expressions that can be a query.
 * 
 * @author Alexandre Camus
 * 
 */
public interface Clause extends PCExpression {

	/**
	 * Creates a solver which is a node in the tree proof. This method must be
	 * implemented in every implementation of {@code Clause}.
	 * <p>
	 * This function is recursive over all objects that can be proved and
	 * creates the tree of proof for a clause.
	 * 
	 * @param rules
	 *            the {@code RuleSet} object containing the rules of knowledge.
	 * @param parentSolution
	 *            the solution known so far at the parent node.
	 * @return the node of the tree of proof.
	 * @see AbstractSolutionNode#nextSolution()
	 */
	public AbstractSolutionNode getSolver(RuleSet rules, SubstitutionSet parentSolution, AbstractSolutionNode parentNode);
	
}
