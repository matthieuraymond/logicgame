/**
 * 
 */
package com.bob.lps.model;

/**
 * This class represents goals. Goals are macro predicates that needs
 * definitions.
 * 
 * @author Alexandre Camus
 * 
 */
public class Goal {

	private SimpleSentence goal;
	private RuleSet definitions;
	private int nextDefinition;

	/**
	 * Constructor of the class
	 * 
	 * @param goal
	 *            the predicate form of the goal.
	 * @param definitions
	 *            the definitions of the goal.
	 */
	public Goal(SimpleSentence goal, RuleSet definitions) {
		this.goal = goal;
		this.definitions = definitions;
		this.nextDefinition = 0;
	}
	
	/**
	 * Constructor of the class
	 * 
	 * @param genericGoal
	 *            the generic representation of the goal.
	 * @param goal
	 *            the actual form of the new goal.
	 */
	public Goal(Goal genericGoal, SimpleSentence goal) {
		this.goal = goal;
		this.nextDefinition = 0;
		this.definitions = new RuleSet();
		
		for(Rule definition : genericGoal.definitions.getRules()) {
			SubstitutionSet bindings = genericGoal.goal.unify(goal, new SubstitutionSet());
			this.definitions.addRule(definition.replaceVariables(bindings));
		}
	}

	/**
	 * Gets the name of the goal. It is the name of the {@code SimpleSentence}
	 * predicate.
	 * 
	 * @return the name of {@code SimpleSentence} representing the goal.
	 */
	public String getName() {
		return this.goal.getName();
	}
	
	/**
	 * Gets the predicate form of the goal.
	 * 
	 * @return a {@code SimpleSentence} object representing the goal.
	 */
	public SimpleSentence getGoal() {
		return this.goal;
	}
	
	/**
	 * Adds the specified rule-definition to the goal's definitions.
	 * <p>
	 * This method should be used only while creating the {@code Goal} object.
	 * 
	 * @param rule
	 *            the {@code Rule} object representing the definition to addActor.
	 */
	public void addDefinition(Rule rule) {
		SubstitutionSet s = rule.getHead().unify(this.goal, new SubstitutionSet());
		this.definitions.addRule(rule.replaceVariables(s));
	}
	
	/**
	 * Checks if the goal has a getNext definition.
	 * 
	 * @return a true if the definition counter is below the number of
	 *         definitions.
	 */
	public boolean hasNextDefinition() {
		return this.nextDefinition < this.definitions.getRuleCount();
	}

	/**
	 * Gets the getNext definition of the goal.
	 * 
	 * @return a {@code Clause} object representing the definition. If
	 *         there is no getNext definition, returns {@code null}.
	 */
	public Clause getNextDefinition() {
		if (hasNextDefinition()) {
			// Get the rule
			Rule rule = this.definitions.getRule(nextDefinition);
			// Next rule to get update
			updateNextRule();
	
			return rule.getBody();
		} else {
			
			return null;
		}
	}
	
	/**
	 * Updates the getNext rule to get. This is the method to modify in order to
	 * apply different strategies when choosing a definition of a goal.
	 * <p>
	 * For now the strategy is very simple: take the definitions in the order of
	 * declaration.
	 */
	public void updateNextRule() {
		this.nextDefinition++;
	}
	
	/**
	 * Resets the getNext rule to get. This is the method to modify in order to
	 * apply different strategies when every definition has been tried.
	 * <p>
	 * For now the strategy is very simple: restart from the beginning.
	 */
	public void reset() {
		this.nextDefinition = 0;
	}
	
	/**
	 * Returns the goal under the form of:
	 * "{
	 *  goal :- def1.
	 *  goal :- def2.
	 * }"
	 * 
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return this.definitions.toString();
	}
	
}
