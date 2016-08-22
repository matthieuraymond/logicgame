/**
 * 
 */
package com.bob.lps.model;

/**
 * This class represents a equality node in the tree of proof. A solution node
 * is a node in a tree of proof.
 * 
 * @author Alexandre Camus
 * 
 */
public class EqualSolutionNode extends AbstractSolutionNode {
	
	/**
	 * Constructor of the class.
	 * 
	 * @param clause
	 *            the equal clause to be proved by this subtree.
	 * @param rules
	 *            the rules representing context of the proof.
	 * @param parentSolution
	 *            the solution of the parent node in the tree of proof.
	 */
	public EqualSolutionNode(Equal clause, RuleSet rules, SubstitutionSet parentSolution, AbstractSolutionNode parentNode) {
		super(clause, rules, parentSolution, parentNode);
	}
	
	/**
	 * Creates the getNext solution for the equality of the node. If no solution
	 * exists, it will create a solution. Otherwise it will get a different
	 * solution or return {@code null} if there no other different solution.
	 * 
	 * @return a {@code SubstitutionSet} object representing the bindings of the
	 *         getNext solution or {@code null} if there is no getNext solution.
	 * @see AbstractSolutionNode#nextSolution()
	 */
	@Override
	public SubstitutionSet nextSolution() {
		Unifiable v1 = ((Equal) getClause()).getOperand1();
		Unifiable v2 = ((Equal) getClause()).getOperand2();
		SubstitutionSet solution = this.getParentSolution();
		
		// Test if the two operands are equivalent
		if (v1.unify(v2, new SubstitutionSet(solution)) != null) {

			return solution;
		}
		
		return null;
	}

}
