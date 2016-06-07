/**
 * 
 */
package com.bob.lps.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

/**
 * This class represents a table of truth. This can be the intensional clauses
 * of the database or any table of truth from which it is needed to create a
 * proof.
 * <p>
 * The rules are stored as {@code Rule} in an {@code List}.
 * 
 * @author Alexandre Camus
 * 
 */
public class RuleSet {

	private List<Rule> rules;
	private int intensional;
	private int extensional;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param rules
	 *            the rules of the set in an array or as independent arguments.
	 */
	public RuleSet(Rule... rules) {
		this.rules = new ArrayList<Rule>(Arrays.asList(rules));
	}
	
	/**
	 * Constructor of the class.
	 * 
	 * @param rules
	 *            an {@code List} object containing the rules of the set.
	 */
	public RuleSet(List<Rule> rules) {
		this.rules = new ArrayList<Rule>(rules);
	}
	
	/**
	 * Sets the position of the last intensional rule.
	 * 
	 * @param i
	 *            the index of the last intensional rule.
	 */
	public void setIntensional(int i) {
		this.intensional = i;
	}
	
	/**
	 * Gets the position of the last intensional rule.
	 * 
	 * @return the index of the last intensional rule.
	 */
	public int getIntensional() {
		return this.intensional;
	}
	
	/**
	 * Sets the position of the last extensional rule.
	 * 
	 * @param i
	 *            the index of the last extensional rule.
	 */
	public void setExtensional(int i) {
		this.extensional = i;
	}
	
	/**
	 * Gets the position of the last extensional rule.
	 * 
	 * @return the index of the last extensional rule.
	 */
	public int getExtensional () {
		return this.extensional;
	}
	
	/**
	 * Gets the standardized version of the rule number {@code index}. This uses
	 * the method {@link Rule#standardizeVariablesApart(Hashtable)
	 * standardizeVariablesApart()} on the rule at the specified index.
	 * 
	 * @param index
	 *            the position of the rule in the set.
	 * @return a {@code Rule} object representing the standardized rule which
	 *         was at the specified position.
	 */
	public Rule getRuleStandardizedApart(int index) {
        return rules.get(index).standardizeVariablesApart(new Hashtable<Variable, Variable>());
	}
	
	/**
	 * Gets the rule at the specified position.
	 * 
	 * @param index
	 *            the position of the rule
	 * @return the {@code Rule} object representing the specified rule.
	 */
	public Rule getRule(int index) {
		return this.rules.get(index);
	}
	
	/**
	 * Gets all the rules of the set.
	 * 
	 * @return an {@code List} object containing the rules of the set.
	 */
	public List<Rule> getRules() {
		return this.rules;
	}
	
	/**
	 * Gets the size of the set. The size of the set is simply the number of
	 * rules in the set.
	 * 
	 * @return the number of rules in the set.
	 */
	public int getRuleCount() {
		return this.rules.size();
	}
	
	/**
	 * Adds all the rules that are in the specified {@code List}.
	 * 
	 * @param rules
	 *            the {@code List} object containing the rules to addActor.
	 */
	public void addRules(List<Rule> rules) {
		for(Rule rule : rules) {
			this.rules.add(rule);
		}
	}
	
	/**
	 * Adds the specified rule.
	 * 
	 * @param rule
	 *            the {@code Rule} object representing the rule to addActor.
	 */
	public void addRule(Rule rule) {
		this.rules.add(rule);
	}

	/**
	 * Returns the set in the form of:
	 * "{
	 * 	rule1
	 *  rule2
	 *  ...
	 * }".
	 * 
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		String s = null;

		for (PCExpression r : this.rules) {
			if (s == null) {
				s = r.toString() + "\n";
			} else {
				s += r.toString() + "\n";
			}
		}

		if (s == null) {
			
			return "true.";
		} else {
			
			return "{\n" + s + "}";
		}
	}

}
