/**
 * 
 */
package com.bob.lps.model;

/**
 * This class represents a and node in the tree of proof.
 * A solution node is a node in a tree of proof.
 * 
 * @author Alexandre Camus
 * 
 */
public class AndSolutionNode extends AbstractSolutionNode {

	private AbstractSolutionNode headSolutionNode = null;
	private AbstractSolutionNode tailSolutionNode = null;
	
	private Clause operatorTail = null;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param clause
	 *            the and clause to be proved by this subtree.
	 * @param rules
	 *            the rules representing context of the proof.
	 * @param parentSolution
	 *            the solution of the parent node in the tree of proof.
	 * @param parentNode
	 *            the parent node in the tree of proof.
	 */
	public AndSolutionNode(And clause, RuleSet rules, SubstitutionSet parentSolution, AbstractSolutionNode parentNode) {
		super(clause, rules, parentSolution, parentNode);
		this.headSolutionNode = clause.getFirstOperand().getSolver(rules, parentSolution, this);
		this.operatorTail = clause.getOperatorTail();
	}
	
	/**
	 * Gets the solution node of the and clause's head.
	 * <p>
	 * This is based on the representation of an and clause. See {@link And}
	 * class for more details.
	 * 
	 * @return a {@code AbstractSolutionNode} representing the solution node of
	 *         the head.
	 */
	protected AbstractSolutionNode getHeadSolutionNode() {
		return this.headSolutionNode;
	}
	
	/**
	 * Gets the solution node of the and clause's tail.
	 * <p>
	 * This is based on the representation of an and clause. See {@link And}
	 * class for more details.
	 * 
	 * @return a {@code AbstractSolutionNode} representing the solution node of
	 *         the tail.
	 */
	protected AbstractSolutionNode getTailSolutionNode() {
		return this.tailSolutionNode;
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
		this.headSolutionNode.reset(this.getParentSolution(), this.getRuleSet());
		this.tailSolutionNode = null;
	}

	/**
	 * Creates the getNext solution for the and clause of the node. If no solution
	 * exists, it will create a solution. Otherwise it will get a different
	 * solution or return {@code null} if there no other different solution.
	 *
	 * @return a {@code SubstitutionSet} object representing the bindings of the
	 *         getNext solution or {@code null} if there is no getNext solution.
	 * @see AbstractSolutionNode#nextSolution()
	 */
	@Override
	public SubstitutionSet nextSolution() {
		SubstitutionSet solution;
		
		// First try to create a new solution with the same head solution but a different tail solution
		if (this.tailSolutionNode != null) {
			solution = tailSolutionNode.nextSolution();

			if (solution != null) {
				setDeepestLeaf(this.tailSolutionNode.getDeepestLeaf());
				
				return solution;
			}
		}
		
		// If there are no new solutions with the previous try,
		// try to get an alternative head solution and then one corresponding tail solution
		boolean enterWhile = false;
		while ((solution = this.headSolutionNode.nextSolution()) != null) {
		// It creates a new solution node for the tail with the parent substitution set.
		// And then get a solution with this substitution set. If it is not null it returns it.
			enterWhile = true;
			this.tailSolutionNode = this.operatorTail.getSolver(this.getRuleSet(), solution, this);
			SubstitutionSet tailSolution = this.tailSolutionNode.nextSolution();
			setDeepestLeaf(this.tailSolutionNode.getDeepestLeaf());
			if (tailSolution != null) {
				
				return tailSolution;
			}
		}
		
		// If both above cases fail, there is no solution
		if (!enterWhile) {
			setDeepestLeaf(this);
		}
		
		return null;
	}

}
