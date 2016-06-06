/**
 * 
 */
package com.bob.lps.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

/**
 * This class represents the reactive rules of a file_return framework.
 * 
 * @author Alexandre Camus
 * 
 */
public class ReactiveRule implements PCExpression {

	private Clause causes;
	private SimpleSentence goal;
	private List<String> actions;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param conditions
	 *            the conditions of the reactive rule. This is the left part of
	 *            the implication.
	 * @param goal
	 *            the goal to fire if the conditions are true. This is the right
	 *            part of the implication.
	 */
	public ReactiveRule(Clause conditions, SimpleSentence goal) {
		this.causes = conditions;
		this.goal = goal;
		
		this.actions = new ArrayList<String>();
		
		Stack<Clause> conditionsParser = new Stack<Clause>();
		conditionsParser.push(this.causes);
		while(!conditionsParser.empty()) {
			// If it is a and, just split all the operands
			if (conditionsParser.peek() instanceof And) {
				And currentOperand = (And) conditionsParser.pop();
				conditionsParser.addAll(currentOperand.getOperands());
			
			// If it is a not, no need to go deeper, it needs a complete solver to get more information.
			} else if (conditionsParser.peek() instanceof Not) {
				conditionsParser.pop();
			
			// Else it is a simple sentence, so just check if it is an action or not
			} else {
				SimpleSentence currentOperand = (SimpleSentence) conditionsParser.pop();
				Action action = Database.getInstance().getDSet().getAction(currentOperand.getName());
				if (action != null) {
					this.actions.add(action.getName());
				}
			}
		}
	}
	
	/**
	 * Gets the actions that are in the condition clause.
	 * 
	 * @return an {@code ArrayList} containing all the actions in the condition
	 *         clause.
	 */
	public List<String> getActions() {
		return this.actions;
	}
	
	/**
	 * Fires the goals of the rule. Each goal represents a different bindings of
	 * the variables of the conditions that make it true.
	 * 
	 * @param database
	 *            the table of truth to which the conditions are submitted to be
	 *            checked.
	 * @return an {@code List} object containing all the goals created.
	 */
	public List<SimpleSentence> fireRule(RuleSet database) {
		List<SimpleSentence> goals = new ArrayList<SimpleSentence>();
		ReactiveRule standardizedRule = this.standardizeVariablesApart(new Hashtable<Variable, Variable>());
		AbstractSolutionNode root = standardizedRule.causes.getSolver(database, new SubstitutionSet(), null);
		SubstitutionSet solution;
		
		while((solution = root.nextSolution()) != null) {
			goals.add(standardizedRule.goal.replaceVariables(solution));
		}
		
		return goals;
			
	}

	/**
	 * Replaces all the variables in the reactive rule according to the
	 * specified bindings.
	 * <p>
	 * This method is recursive over all {@link PCExpression} implementations.
	 * 
	 * @param s
	 *            the {@code SubstitutionSet} that contains the bindings of the
	 *            variables so far.
	 * @return a {@code ReactiveRule} object representing the bound reactive
	 *         rule.
	 * @see PCExpression#replaceVariables(SubstitutionSet)
	 */
	@Override
	public ReactiveRule replaceVariables(SubstitutionSet s) {
		// Create the bound goal
		SimpleSentence newGoal = this.goal.replaceVariables(s);

		// If the conditions of this rule isn't null, create the bound one
		Clause newConditions = null;
		if (this.causes != null) {
			newConditions = (Clause) this.causes.replaceVariables(s);
		}

		// Create the bound reactive rule
        return new ReactiveRule(newConditions, newGoal);
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
	 * @return a {@code ReactiveRule} object representing the standardized
	 *         reactive rule.
	 * @see PCExpression#standardizeVariablesApart(Hashtable)
	 */
	@Override
	public ReactiveRule standardizeVariablesApart(Hashtable<Variable, Variable> newVars) {
		// Create the standardized goal
		SimpleSentence newGoal = this.goal.standardizeVariablesApart(newVars);

		// If the conditions of this rule isn't null, create the standardized one
		Clause newConditions = null;
		if (this.causes != null) {
			newConditions = (Clause) this.causes.standardizeVariablesApart(newVars);
		}

		// Create the standardized reactive rule
        return new ReactiveRule(newConditions, newGoal);
	}

	/**
	 * Returns the reactive rule in the form of:
	 * "(conditions) -> goal".
	 *
	 * @see Object#toString()
	 */
	public String toString() {
		return "(" + this.causes.toString() + ") -> " + this.goal.toString();
	}

}
