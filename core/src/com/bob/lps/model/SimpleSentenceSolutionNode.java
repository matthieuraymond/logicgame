/**
 * 
 */
package com.bob.lps.model;

/**
 * This class represents a simple sentence node in the tree of proof. A solution
 * node is a node in a tree of proof.
 * 
 * @author Alexandre Camus
 * 
 */
public class SimpleSentenceSolutionNode extends AbstractSolutionNode {

	private AbstractSolutionNode child = null;
	private String type = "undefined";
	private int limit;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param clause
	 *            the simple sentence to be proved by this subtree.
	 * @param rules
	 *            the rules representing context of the proof.
	 * @param parentSolution
	 *            the solution of the parent node in the tree of proof.
	 */
	public SimpleSentenceSolutionNode(SimpleSentence clause, RuleSet rules, SubstitutionSet parentSolution, AbstractSolutionNode parentNode) {
		super(clause, rules, parentSolution, parentNode);
		if (Database.getInstance().getLimits().containsKey(clause.getName())) {
			this.limit = Database.getInstance().getLimits().get(clause.getName());
		}
	}
	
	/**
	 * Sets the type of the specified rule. The type is either "fact", "rule" or
	 * "action".
	 * 
	 * @param ruleIndex
	 *            the index in the set of rules, of the rule to type.
	 */
	public void setType(int ruleIndex) {
		if (ruleIndex < getRuleSet().getExtensional()) {
			this.type = "fact";
		} else if (ruleIndex < getRuleSet().getIntensional()) {
			this.type = "rule";
		} else {
			this.type = "action";
		}
	}
	
	/**
	 * Gets the type of the specified rule. The type is either "fact", "rule" or
	 * "action".
	 * 
	 * @return the type this node.
	 */
	public String getType() {
		return this.type;
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
		this.child = null;
	}

	/**
	 * Creates the getNext solution for the simple sentence of the node. If no solution
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
		
		// If there is a child in the proof tree, try to get an alternative solution
		if (this.child != null) {
			solution = this.child.nextSolution();
			if (solution != null) {
				setDeepestLeaf(this.child.getDeepestLeaf());
				
				return solution;
			}
		} 
		
		// If the previous try fails, this proof tree has no alternate solution.
		// Create a new one by deleting the current child.
		this.child = null;
		
		Rule rule;
		
		// Then try to find a new solution for the current node
		while (this.hasNextRule()) {
			// Get a rule
			rule = this.nextRule();
			
			SimpleSentence head = rule.getHead();
			
			// Unify its head
			solution = ((SimpleSentence) this.getClause()).unify(head, this.getParentSolution());
			
			if (((SimpleSentence) this.getClause()).isMatched()) {
				setType(currentRuleCount());
			}
			
			// If there is a solution to the unification
			if (solution != null) {
				// Get the body of the rule
				Clause tail = rule.getBody();
				
				// If there is no body, solution is the whole solution
				if (tail == null) {
					setDeepestLeaf(this);
					
					return solution;
				}
				
				// Otherwise create a node for the body of the rule and get a new solution for this new node
				this.child = tail.getSolver(this.getRuleSet(), solution, this);
				SubstitutionSet childSolution = this.child.nextSolution();
				
				// If the new node has a solution return it
				if (childSolution != null) {
					setDeepestLeaf(this.child.getDeepestLeaf());
					
					return childSolution;
				}
			}
		}
		
		// If there is no solution for the current node after trying with every rule
		// Fail and return null
		setDeepestLeaf(this);
		
		return null;
	}
	
	/**
	 * Checks if the limit of waited cycles is exceeded or not.
	 * 
	 * @return true if not.
	 */
	public boolean limitExceed() {
		return !(this.limit > 0);
	}

	/**
	 * Updates the limit every cycle. 
	 */
	public void limitUpdate() {
		this.limit--;
	}
	
}
