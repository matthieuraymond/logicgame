/**
 * 
 */
package com.bob.lps.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This singleton class represents the set of all reactive rules of the file_return
 * framework.
 * <p>
 * The reactive rules are stored as {@code ReactiveRule} in an {@code List}.
 * <p>
 * The constructor is private as you must not use it. Instead use the
 * {@code getInstance()} method to get the only object of the class (or to
 * create it).
 * 
 * @author Alexandre Camus
 * 
 */
public class ReactiveRuleSet {
	
	List<ReactiveRule> reactiveRules;
	private static volatile ReactiveRuleSet instance = null;

	public static void reset() {
		instance = null;
	}

	/**
	 * Constructor of the class. It is private as it must not be
	 * called. Use the method {@code getInstance()} instead.
	 */
	private ReactiveRuleSet(ReactiveRule... reactiveRules) {
		this.reactiveRules = new ArrayList<ReactiveRule>(Arrays.asList(reactiveRules));
	}
	
	/**
	 * This is the method to get an instance of the class.
	 * Use it as shown: {@code ReactiveRuleSet.getInstance()}
	 * 
	 * @return the only instance of the class {@code ReactiveRuleSet}.
	 */
	public static ReactiveRuleSet getInstance() {
		if (ReactiveRuleSet.instance == null) {
			synchronized (ReactiveRuleSet.class) {
				if (ReactiveRuleSet.instance == null) {
					ReactiveRuleSet.instance = new ReactiveRuleSet();
				}
			}
		}

		return ReactiveRuleSet.instance;
	}

	/**
	 * Adds the specified reactive rule.
	 * 
	 * @param rule
	 *            the {@code ReactiveRule} object representing the reactive rule
	 *            to addActor.
	 */
	public void addRule(ReactiveRule rule) {
		this.reactiveRules.add(rule);
	}
	
	/**
	 * Fires all the reactive rules it contains thanks to there own
	 * {@link ReactiveRule#fireRule(RuleSet) fireRule()} method. It returns all
	 * the goals in order to push them into the list of the goals to solve.
	 * 
	 * @param ruleSet
	 *            the table of truth to which the conditions are submitted to be
	 *            checked.
	 * @param events
	 *            the events/actions that have been performed on the database
	 *            during this cycle. 
	 * @return an {@code List} object containing all the fired goals.
	 */
	public List<SimpleSentence> fireRules(RuleSet ruleSet, List<String> events) {
		List<SimpleSentence> goals = new ArrayList<SimpleSentence>();
		
		for(ReactiveRule rule : this.reactiveRules) {
			if (events.containsAll(rule.getActions())) {
				goals.addAll(rule.fireRule(ruleSet));
			}
		}
		
		return goals;
	}
	
	/**
	 * Returns the set in the form of:
	 * "Reactive rules: [(conditions) -> goal, (conditions) -> goal, ...]".
	 * 
	 * @see Object#toString()
	 */
	public String toString() {
		return this.reactiveRules.toString();
	}

}
