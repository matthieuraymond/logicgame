/**
 * 
 */
package com.bob.lps.model;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * This class represents a arithmetic expression node in the tree of proof. A
 * solution node is a node in a tree of proof.
 * 
 * @author Alexandre Camus
 * 
 */
public class ArithmeticSolutionNode extends AbstractSolutionNode {

	private boolean solutionFlag = false;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param clause
	 *            the arithmetic expression to be proved by this subtree.
	 * @param rules
	 *            the rules representing context of the proof.
	 * @param parentSolution
	 *            the solution of the parent node in the tree of proof.
	 */
	public ArithmeticSolutionNode(Arithmetic clause, RuleSet rules, SubstitutionSet parentSolution, AbstractSolutionNode parentNode) {
		super(clause, rules, parentSolution, parentNode);
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
	}

	/**
	 * Creates the getNext solution for the arithmetic expression of the node. If
	 * no solution exists, it will create a solution. Otherwise it will get a
	 * different solution or return {@code null} if there no other different
	 * solution.
	 * 
	 * @return a {@code SubstitutionSet} object representing the bindings of the
	 *         getNext solution or {@code null} if there is no getNext solution.
	 * @see AbstractSolutionNode#nextSolution()
	 */
	@Override
	public SubstitutionSet nextSolution() {
		setDeepestLeaf(this);
		if (this.solutionFlag) {
			
			return null;
		} else {
			this.solutionFlag = true;
		}
		
		SubstitutionSet solution = this.getParentSolution();
		Unifiable v1 = (Unifiable) ((Arithmetic) getClause()).getOperand1().replaceVariables(solution);
		Unifiable v2 = (Unifiable) ((Arithmetic) getClause()).getOperand2().replaceVariables(solution);
		String symbol = ((Arithmetic) getClause()).getSymbol();
		
		// If the left operand is not bound and not a constant, no solution
		if (!(v1 instanceof Constant)) {

            return null;
        }
			
		// If the right operand is not bound and not a constant, no solution
		if (!(v2 instanceof Constant)) {
			
			return null;
		}
		
		String value1 = v1.getName();
		String value2 = v2.getName();
		
		// If the value1 is not a number, no solution
		if (!value1.matches("[0-9]+(.[0-9]+)?")) {
			
			return null;
		}
		
		// If the value2 is not a number, no solution
		if (!value2.matches("[0-9]+(.[0-9]+)?")) {
			
			return null;
		}
		
		// Escape the code for Java to interpret it
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        boolean value = false;
		try {
			value = (boolean) engine.eval(value1+symbol+value2);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	    
		if (value) {
			
			return solution;
		} else {
			
			return null;
		}
	}

}
