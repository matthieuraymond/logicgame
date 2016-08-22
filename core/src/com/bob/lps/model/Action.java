/**
 * 
 */
package com.bob.lps.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents actions. Actions are either predicates to reason with
 * or agents that create and delete fluents in the database.
 * 
 * @author Alexandre Camus
 * 
 */
public class Action {

	private SimpleSentence action;
	private List<Initiator> initiators;
	private List<Terminator> terminators;
	private Clause conditions;
	private Clause conflicts;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param action
	 *            the {@code SimpleSentence} object that is the generic
	 *            predicate representing the action.
	 * @param initiators
	 *            the {@code List} containing all the DPost declarations
	 *            that initiates fluents.
	 * @param terminators
	 *            the {@code List} containing all the DPost declarations
	 *            that terminates fluents.
	 * @param conditions
	 *            the preconditions that must be satisfied before performing the
	 *            action.
	 */
	public Action(SimpleSentence action, List<Initiator> initiators, List<Terminator> terminators, Clause conditions, Clause conflicts) {
		this.action = action;
		this.initiators = initiators;
		this.terminators = terminators;
		this.conditions = conditions;
		this.conflicts = conflicts;
	}
	
	/**
	 * Gets the name of the action. It is the name of the {@code SimpleSentence}
	 * predicate.
	 * 
	 * @return the name of {@code SimpleSentence} representing the action.
	 */
	public String getName() {
		return this.action.getName();
	}
	
	/**
	 * Gets the predicate representing the action. The predicate is represented
	 * as a {@code SimpleSentence}.
	 * 
	 * @return the {@code SimpleSentence} object representing the action.
	 */
	public SimpleSentence getPredicate() {
		return this.action;
	}
		
	/**
	 * Checks if the action can be performed. If this needs bindings for the
	 * variables of the action, it will return every version of the action with
	 * its allowed binding.
	 * <p>
	 * The method gets every solution for the conditions of the action and
	 * applies the bindings got to the generic {@code SimpleSentence}.
	 * 
	 * @param event
	 *            the bound action to com.bob.test.game if it can be performed.
	 * @param rulesAndEvents
	 *            the rules and the events to check the conditions.
	 * @param rulesAndNextEvents
	 *            the rules and the getNext events so far to check the conflicts.
	 * @return true if this bound action can be performed. False otherwise.
	 */
	public boolean actionsAllowed(SimpleSentence event, RuleSet rulesAndEvents, RuleSet rulesAndNextEvents) {
		SubstitutionSet bindings = this.action.unify(event, new SubstitutionSet());
		if (this.conditions != null) {
			Clause boundConditions = (Clause) this.conditions.replaceVariables(bindings);
			AbstractSolutionNode conditionsRoot = boundConditions.getSolver(rulesAndEvents, new SubstitutionSet(), null);
			
			if (conditionsRoot.nextSolution() == null) {

				return false;
			}
		}
		
		if (this.conflicts != null) {
			Clause boundConflicts = (Clause) this.conflicts.replaceVariables(bindings);
			AbstractSolutionNode conflictsRoot = boundConflicts.getSolver(rulesAndNextEvents, new SubstitutionSet(), null);
			
			if (conflictsRoot.nextSolution() == null) {

				return false;
			}
		}

		return true;
	}
	
	/**
	 * Gets all the fluents that will be initiated. It simply take all the
	 * initiators of the action, take every fluent they initiates and binds the
	 * fluents according to the event put in parameter.
	 * 
	 * @param event
	 *            the version of the action that is performed on the database.
	 * @param rules
	 *            the set of rules to check the body of the initators.
	 * @return the {@code List} of the bound fluents according to the
	 *         parameter.
	 */
	public List<SimpleSentence> fluentsToInitiate(SimpleSentence event, RuleSet rules) {
		List<SimpleSentence> fluents = new ArrayList<SimpleSentence>();
		
		for(Initiator initiator : this.initiators) {
			SimpleSentence fluent = initiator.getGroundFluent(event, rules);
			if (fluent != null) {
				fluents.add(fluent);
			}
		}
		
		return fluents;
	}
	
	/**
	 * Gets all the fluents that will be terminated. It simply take all the
	 * terminators of the action, take every fluent they terminates and binds the
	 * fluents according to the event put in parameter.
	 * 
	 * @param event
	 *            the version of the action that is performed on the database.
	 * @param rules
	 *            the set of rules to check the body of the terminators.
	 * @return the {@code List} of the bound fluents according to the
	 *         parameter.
	 */
	public List<SimpleSentence> fluentsToTerminate(SimpleSentence event, RuleSet rules) {
		List<SimpleSentence> fluents = new ArrayList<SimpleSentence>();
		
		for(Terminator terminator : this.terminators) {
			SimpleSentence fluent = terminator.getGroundFluent(event, rules);
			if (fluent != null) {
				fluents.add(fluent);
			}
		}
		
		return fluents;
	}

	/**
	 * Returns the action under the form of:
	 * "if (conditions + conflicts) actionName=[[initiators], [terminators]]"
	 * 
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		String conditions = (this.conflicts == null) ? 
				(this.conditions == null) ? "true" : this.conditions.toString() : 
				(this.conditions == null) ? this.conflicts.toString() : this.conditions.toString() + " & " + this.conflicts.toString();
		
		return "if (" + conditions + ") " + this.getName() + "=[" + this.initiators.toString() + ", " + this.terminators.toString() + "]";
	}

}
