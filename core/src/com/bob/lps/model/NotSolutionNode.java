/**
 * 
 */
package com.bob.lps.model;

/**
 * This class represents a negative node in the tree of proof.
 * A solution node is a node in a tree of proof.
 * 
 * @author Alexandre Camus
 * 
 */
public class NotSolutionNode extends AbstractSolutionNode {

	private AbstractSolutionNode tailSolutionNode = null;
	private boolean solutionFlag = false;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param clause
	 *            the negative clause to be proved by this subtree.
	 * @param rules
	 *            the rules representing context of the proof.
	 * @param parentSolution
	 *            the solution of the parent node in the tree of proof.
	 * @param parentNode
	 *            the parent node in the tree of proof.
	 */
	public NotSolutionNode(Not clause, RuleSet rules, SubstitutionSet parentSolution, AbstractSolutionNode parentNode) {
		super(clause, rules, parentSolution, parentNode);
		this.tailSolutionNode = clause.getOperand(0).getSolver(rules, parentSolution, this);
	}
	
	/**
	 * Resets the subtree to the new state of the database and resets all the
	 * counters that prevent from infinite evaluation of the node.
	 * 
	 * @param newParentSolution
	 *            the new parent solution of the subtree.
	 * @param newRuleSet
	 *            the new state of the database.
	 * 
	 * @see AbstractSolutionNode#reset(SubstitutionSet,
	 *      RuleSet)
	 */
	@Override
	protected void reset(SubstitutionSet newParentSolution, RuleSet newRuleSet) {
		super.reset(newParentSolution, newRuleSet);
		this.solutionFlag = false;
		this.tailSolutionNode.reset(this.getParentSolution(), this.getRuleSet());
	}

	/**
	 * Gets the solution node of the not clause's tail.
	 * <p>
	 * This is based on the representation of an not clause. See {@link Not}
	 * class for more details.
	 *
	 * @return a {@code AbstractSolutionNode} representing the solution node of
	 *         the tail.
	 */
	protected AbstractSolutionNode getTailSolutionNode() {
		return this.tailSolutionNode;
	}

	/**
	 * Creates the getNext solution for the negative clause of the node. If no solution
	 * exists, it will create a solution. Otherwise it will get a different
	 * solution or return {@code null} if there no other different solution.
	 *
	 * @return a {@code SubstitutionSet} object representing the bindings of the
	 *         getNext solution or {@code null} if there is no getNext solution.
	 * @see AbstractSolutionNode#nextSolution()
	 */
	@Override
	public SubstitutionSet nextSolution() {
		// The node can be evaluated once like in Prolog
		if (this.solutionFlag) {
			setDeepestLeaf(this);
			
			return null;
		} else {
			this.solutionFlag = true;
		}
		
		// Otherwise
		setDeepestLeaf(this);
		
		// Check if the tail has a solution
		// If it does, the negation is false
		if (this.tailSolutionNode.nextSolution() != null) {
			
			return null;
		// If it doesn't, the negation is true
		} else {
			
			return this.getParentSolution();
		}
	}

}
