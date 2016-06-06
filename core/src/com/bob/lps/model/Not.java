/**
 * 
 */
package com.bob.lps.model;

import java.util.List;

/**
 * This class represents negative clauses. It is a unary operator. It extends
 * {@link AbstractOperator}.
 * 
 * @author Alexandre Camus
 * 
 */
public class Not extends AbstractOperator {
	
	/**
	 * Constructor of the class.
	 * 
	 * @param operands
	 *            the only operand of the not operator.
	 */
	public Not(Clause operands) {
		super(operands);
	}

	/**
	 * Generic constructor of the object. It allows the methods of the abstract
	 * class {@code AbstractOperator} to create the correct object very easily.
	 * 
	 * @param operands
	 *            the operands of the not-operator in an {@code ArrayList}
	 *            object.
	 * 
	 * @see AbstractOperator#create(List)
	 */
	@Override
	protected Not create(List<Clause> operands) {
		return new Not(operands.get(0));
	}

	/**
	 * Creates a solver which is a node in the tree proof. This is the version
	 * for the not operator.
	 * <p>
	 * This function is recursive over all objects that can be proved and
	 * creates the tree of proof for a clause.
	 *
	 * @param rules
	 *            the {@code RuleSet} object containing the rules of knowledge.
	 * @param parentSolution
	 *            the solution known so far at the parent node.
	 * @param parentNode
	 *            the parent node in the tree.
	 * @return the node of the tree of proof.
	 * @see Clause#getSolver(RuleSet, SubstitutionSet, AbstractSolutionNode)
	 */
	@Override
	public NotSolutionNode getSolver(RuleSet rules, SubstitutionSet parentSolution, AbstractSolutionNode parentNode) {
		return new NotSolutionNode(this, rules, parentSolution, parentNode);
	}

	/**
	 * Returns the negative clause under the form of:
	 * "!(operand)".
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return "!(" + this.getOperand(0).toString() + ")";
	}
	
}
