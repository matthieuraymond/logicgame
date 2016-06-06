/**
 * 
 */
package com.bob.lps.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

/**
 * This class is the abstract class that gathers all the operator classes. It
 * implements {@link Clause}.
 * 
 * @author Alexandre Camus
 * 
 */
public abstract class AbstractOperator implements Clause {

	protected List<Clause> operands;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param operands
	 *            the operands of the operator in an array or as independent
	 *            variables.
	 */
	public AbstractOperator(Clause... operands) {
        this.operands = Arrays.asList(operands);
	}
	
	/**
	 * Constructor of the class.
	 * 
	 * @param operands
	 *            the operands of the operator in an {@code List} object.
	 */
	public AbstractOperator(List<Clause> operands) {
		this.operands = operands;
	}

	/**
	 * Sets all the operands to the specified operands.
	 * 
	 * @param operands
	 *            the new operands in an {@code List} object.
	 */
	public void setOperands(List<Clause> operands) {
		this.operands = operands;
	}
	
	/**
	 * Gets the size of the operator in terms of operands.
	 * 
	 * @return the number of operands of the operator.
	 */
	public int operandCount() {
		return this.operands.size();
	}
	
	/**
	 * Gets the operand number {@code index}.
	 * 
	 * @param index
	 *            of the operand to get.
	 * @return return the operand number {@code index}.
	 */
	public Clause getOperand(int index) {
		return this.operands.get(index);
	}
	
	/**
	 * Gets the first positive operand of the operator.
	 * 
	 * @return the first operand.
	 */
	public Clause getFirstOperand() {
		return this.operands.get(0);
	}
	
	/**
	 * Gets the tail of the operator. An operator is seen as its head (the first
	 * operand) and its tail (all the other operands).
	 * 
	 * @return the tail of the operator as an object of the same class if there
	 *         are several tail operands or as the operand itself if there is
	 *         only one.
	 */
	public Clause getOperatorTail() {
		List<Clause> tail = new ArrayList<Clause>(this.operands);
		tail.remove(0);
		Clause tailOperator;
		if (tail.size() == 1) {
			tailOperator = tail.get(0);
		} else {
			tailOperator = this.create(tail);
		}
		
		return tailOperator;
	}
	
	/**
	 * Gets all the operands of the operator.
	 * 
	 * @return an {@code List} object containing all the operands of the
	 *         operator.
	 */
	public List<Clause> getOperands() {
		return this.operands;
	}
	
	/**
	 * Checks if the operator has operands or not.
	 * 
	 * @return true if the operator is empty.
	 */
	public boolean isEmpty() {
		return this.operands.isEmpty();
	}
	
	/**
	 * Generic constructor of sub-objects. It allows the methods of the abstract
	 * class to create correct sub-objects very easily.
	 * 
	 * @see And#create(List)
	 * @see Not#create(List)
	 */
	protected abstract AbstractOperator create(List<Clause> operands);

	/**
	 * Replaces all the variables in the clause according to the specified
	 * bindings.
	 * <p>
	 * This method is recursive over all {@link PCExpression} implementations.
	 *
	 * @param s
	 *            the {@code SubstitutionSet} that contains the bindings of the
	 *            variables so far.
	 * @return an object of the same class representing the bound clause.
	 * @see PCExpression#replaceVariables(SubstitutionSet)
	 */
	@Override
	public AbstractOperator replaceVariables(SubstitutionSet s) {
		// Create the operands of the new bound operator
		List<Clause> newOperands = new ArrayList<Clause>();

		// Bind each operand recursively
		for (int i = 0; i < this.operandCount(); i++) {
			newOperands.add((Clause) this.getOperand(i).replaceVariables(s));
		}

		// Create the bound operator
        return this.create(newOperands);
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
	 * @return an object of the same class representing the standardized clause.
	 * @see PCExpression#standardizeVariablesApart(Hashtable)
	 */
	@Override
	public AbstractOperator standardizeVariablesApart(Hashtable<Variable, Variable> newVars) {
		// Create the operands of the new standardized operator
		List<Clause> newOperands = new ArrayList<Clause>();
		
		// Standardize each operand recursively
		for(int i = 0; i < this.operandCount(); i++) {
			newOperands.add((Clause) this.getOperand(i).standardizeVariablesApart(newVars));
		}
		
		// Create the new standardized operator
        return this.create(newOperands);
	}

}
