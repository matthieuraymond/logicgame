/**
 * 
 */
package com.bob.lps.model;

import java.util.Hashtable;

/**
 * This class represents arithmetic expressions. It is a binary operator. It
 * extends {@link Clause}.
 * <p>
 * Arithmetic expressions are only comparisons between numbers. Their operands
 * must be bound before evaluation.
 * 
 * @author Alexandre Camus
 * 
 */
public class Arithmetic implements Clause {
	
	private Unifiable operand1;
	private Unifiable operand2;
	private String symbol;

	/**
	 * Constructor of the class
	 * 
	 * @param operand1
	 *            the left operand of the arithmetic expression.
	 * @param symbol
	 *            the symbol of the arithmetic expression.
	 * @param operand2
	 *            the right operand of the arithmetic expression.
	 */
	public Arithmetic(Unifiable operand1, String symbol, Unifiable operand2) {
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.symbol = symbol;
	}
	
	/**
	 * Gets the left operand of the arithmetic expression.
	 * 
	 * @return the left operand of the arithmetic expression.
	 */
	public Unifiable getOperand1() {
		return this.operand1;
	}
	
	/**
	 * Gets the right operand of the arithmetic expression.
	 * 
	 * @return the right operand of the arithmetic expression.
	 */
	public Unifiable getOperand2() {
		return this.operand2;
	}
	
	/**
	 * Gets the symbol of the arithmetic expression.
	 * 
	 * @return the symbol of the arithmetic expression.
	 */
	public String getSymbol() {
		return this.symbol;
	}
	
	/**
	 * Creates a solver which is a node in the tree proof. This is the version
	 * for the arithmetic expression.
	 * <p>
	 * This function is recursive over all objects that can be proved and
	 * creates the tree of proof for a clause.
	 * 
	 * @param rules
	 *            the {@code RuleSet} object containing the rules of knowledge.
	 * @param parentSolution
	 *            the solution known so far at the parent node.
	 * @return the node of the tree of proof.
	 * @see Clause#getSolver(RuleSet, SubstitutionSet, AbstractSolutionNode)
	 */
	@Override
	public ArithmeticSolutionNode getSolver(RuleSet rules, SubstitutionSet parentSolution, AbstractSolutionNode parentNode) {
		return new ArithmeticSolutionNode(this, rules, parentSolution, parentNode);
	}

	/**
	 * Replaces all the variables in the arithmetic expression according to the
	 * specified bindings.
	 * <p>
	 * This method is recursive over all {@link PCExpression} implementations.
	 * 
	 * @param s
	 *            the {@code SubstitutionSet} that contains the bindings of the
	 *            variables so far.
	 * @return a {@code Arithmetic} object representing the bound arithmetic
	 *         expression.
	 * @see PCExpression#replaceVariables(SubstitutionSet)
	 */
	@Override
	public Arithmetic replaceVariables(SubstitutionSet s) {
		Unifiable newOperand1 = (Unifiable) this.operand1.replaceVariables(s);
		Unifiable newOperand2 = (Unifiable) this.operand2.replaceVariables(s);

		return new Arithmetic(newOperand1, this.symbol, newOperand2);
	}

	/**
	 * Standardizes the variables in order to be sure that there won't be any
	 * variable clashes.
	 * <p>
	 * This method is recursive over all {@code PCExpression} implementations.
	 *
	 * @param newVars
	 *            is a parameter to save over the recursion all the variable
	 *            replacements done so far.
	 * @return a {@code Arithmetic} object representing the standardized
	 *         arithmetic expression.
	 * @see PCExpression#standardizeVariablesApart(Hashtable)
	 */
	@Override
	public Arithmetic standardizeVariablesApart(Hashtable<Variable, Variable> newVars) {
		Unifiable newOperand1 = (Unifiable) this.operand1.standardizeVariablesApart(newVars);
		Unifiable newOperand2 = (Unifiable) this.operand2.standardizeVariablesApart(newVars);

		return new Arithmetic(newOperand1, this.symbol, newOperand2);
	}

	/**
	 * Returns the arithmetic expression under the form of:
	 * "operand1 'symbol' operand2".
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return this.operand1.toString() + this.symbol + this.operand2.toString();
	}

}
