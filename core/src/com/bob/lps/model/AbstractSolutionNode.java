/**
 * 
 */
package com.bob.lps.model;

/**
 * This class is the abstract class that gathers all the solution-node classes.
 * A solution node is a node in a tree of proof.
 * 
 * @author Alexandre Camus
 * 
 */
public abstract class AbstractSolutionNode {

	private RuleSet rules;
	private Rule currentRule = null;
	private Clause clause = null;
	private AbstractSolutionNode deepestLeaf = this;
	public static int nodesCreated = 0;
	
	// saving the parent solution allows backtracking to the original state
	private SubstitutionSet parentSolution;
	private AbstractSolutionNode parentNode;
	
	//These variables allow the solution node to iterate over the rule set.
	private int ruleNumber = 0;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param clause
	 *            the clause to be proved by this subtree.
	 * @param rules
	 *            the rules representing context of the proof.
	 * @param parentSolution
	 *            the solution of the parent node in the tree of proof.
	 */
	public AbstractSolutionNode(Clause clause, RuleSet rules, SubstitutionSet parentSolution, AbstractSolutionNode parentNode) {
		this.rules = rules;
		this.parentSolution = parentSolution;
		this.parentNode = parentNode;
		this.clause = clause;
	}
	
	/**
	 * Creates the getNext solution for the clause of the node. If no solution
	 * exists, it will create a solution. Otherwise it will get a different
	 * solution or return {@code null} if there no other different solution.
	 * 
	 * @return a {@code SubstitutionSet} object representing the bindings of the
	 *         getNext solution or {@code null} if there is no getNext solution.
	 */
	public abstract SubstitutionSet nextSolution();
	
	/**
	 * Resets the subtree to the new state of the database and resets all the
	 * counters that prevent from infinite evaluation of the node.
	 * 
	 * @param newParentSolution
	 *            the new parent solution of the subtree.
	 * @param newRuleSet
	 *            the new state of the database.
	 * 
	 * @see AndSolutionNode#reset(SubstitutionSet, RuleSet)
	 * @see ArithmeticSolutionNode#reset(SubstitutionSet,
	 *      RuleSet)
	 * @see NotSolutionNode#reset(SubstitutionSet, RuleSet)
	 * @see SimpleSentenceSolutionNode#reset(SubstitutionSet,
	 *      RuleSet)
	 */
	protected void reset(SubstitutionSet newParentSolution, RuleSet newRuleSet) {
		this.parentSolution = newParentSolution;
		this.ruleNumber = 0;
		this.rules = (newRuleSet == null) ? this.rules : newRuleSet;
	}
	
	/**
	 * Gets the number of the current rule.
	 * 
	 * @return the number of the current rule.
	 */
	public int currentRuleCount() {
		return this.ruleNumber - 1;
	}
	
	/**
	 * Checks if there is another rule in the context rules.
	 * 
	 * @return true if the current rule number is strictly below the number of
	 *         rules.
	 */
	public boolean hasNextRule() {
		return this.ruleNumber < this.rules.getRuleCount();
	}
	
	/**
	 * Gets the getNext rule to work with.
	 * 
	 * @return a {@code Rule} object representing the getNext rule.
	 */
	public Rule nextRule() {
		if (this.hasNextRule()) {
			this.currentRule = this.rules.getRuleStandardizedApart(this.ruleNumber++);
		} else {
			this.currentRule = null;
		}
		
		return this.currentRule;
	}
	
	/**
	 * Gets the parent solution.
	 * 
	 * @return a {@code SubstitutionSet} object that represents the parent
	 *         solution.
	 */
	public SubstitutionSet getParentSolution() {
		return this.parentSolution;
	}
	
	/**
	 * Gets the parent node.
	 * 
	 * @return an {@code AbstractSolutionNode} object that represents the parent
	 *         node.
	 */
	public AbstractSolutionNode getParentNode() {
		return this.parentNode;
	}
	
	/**
	 * Gets the context rules of the tree.
	 * 
	 * @return a {@code RuleSet} object containing the context rules.
	 */
	public RuleSet getRuleSet() {
		return this.rules;
	}
	
	/**
	 * Gets the rule on which the node is currently working.
	 * 
	 * @return a {@code Rule} object representing the current rule.
	 */
	public Rule getCurrentRule(){
		return this.currentRule;
	}
	
	/**
	 * Gets the clause to prove in the context rules.
	 * 
	 * @return a {@code Clause} object representing the clause to prove.
	 */
	public Clause getClause() {
		return this.clause;
	}
	
	/**
	 * Sets the deepest leaf to the specified leaf.
	 * 
	 * @param leaf
	 *            the deepest leaf of the tree (the one that failed).
	 */
	public void setDeepestLeaf(AbstractSolutionNode leaf) {
		this.deepestLeaf = leaf;
	}
	
	/**
	 * Gets the deepest leaf of the tree.
	 * 
	 * @return an {@code AbstractSolutionNode} object representing the deepest
	 *         leaf of the tree.
	 */
	public AbstractSolutionNode getDeepestLeaf() {
		return this.deepestLeaf;
	}

}
