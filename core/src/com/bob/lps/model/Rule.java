/**
 * 
 */
package com.bob.lps.model;

import java.util.Hashtable;


/**
 * This class represents the rules of the table of truth.
 * 
 * @author Alexandre Camus
 * 
 */
public class Rule implements PCExpression, Cloneable {

	private SimpleSentence head;
	private Clause body;
	
	/**
	 * Constructor of the class in case of a simple fact.
	 * 
	 * @param head
	 *            the fact that is declared true.
	 */
	public Rule(SimpleSentence head) {
		this(head, null);
	}

	/**
	 * Constructor of the class in case of a backtracking rule.
	 * 
	 * @param head
	 *            the element that is defined by the rule.
	 * @param body
	 *            the definition of the element.
	 */
	public Rule(SimpleSentence head, Clause body) {
		this.head = head;
		this.body = body;
	}
	
	/**
	 * Gets the head of the rule. The head is either the fact that is declared
	 * as true or the element that is defined.
	 * 
	 * @return a {@code SimpleSentence} object which is the head of the rule.
	 */
	public SimpleSentence getHead() {
		return this.head;
	}
	
	/**
	 * Gets the body of the rule. The body is either {@code null} for a fact or
	 * a clause for a complete rule.
	 * 
	 * @return a {@code Clause} object representing the body.
	 */
	public Clause getBody() {
		return this.body;
	}

	/**
	 * Replaces all the variables in the rule according to the specified
	 * bindings.
	 * <p>
	 * This method is recursive over all {@link PCExpression} implementations.
	 * 
	 * @param s
	 *            the {@code SubstitutionSet} that contains the bindings of the
	 *            variables so far.
	 * @return a {@code Rule} object representing the bound rule.
	 * @see PCExpression#replaceVariables(SubstitutionSet)
	 */
	@Override
	public Rule replaceVariables(SubstitutionSet s) {
		// Create the bound head
		SimpleSentence newHead = this.getHead().replaceVariables(s);

		// If the body of this rule isn't null, create the bound one
		Clause newBody = null;
		if (this.getBody() != null) {
			newBody = (Clause) this.getBody().replaceVariables(s);
		}

		// Create the bound rule
        return new Rule(newHead, newBody);
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
	 * @return a {@code Rule} object representing the standardized rule.
	 * @see PCExpression#standardizeVariablesApart(Hashtable)
	 */
	@Override
	public Rule standardizeVariablesApart(Hashtable<Variable, Variable> newVars) {
		// Create the standardized head
		SimpleSentence newHead = this.getHead().standardizeVariablesApart(newVars);

		// If the body of this rule isn't null, create the standardized one
		Clause newBody = null;
		if (this.getBody() != null) {
			newBody = (Clause) this.getBody().standardizeVariablesApart(newVars);
		}

		// Create the standardized rule

        return new Rule(newHead, newBody);
	}

	/**
	 * Returns the rule in the form of:
	 * "head :- body".
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		if (this.body == null) {
			return this.head.toString() + ".";
		} else {
			return this.head.toString() + " :- " + this.body.toString() + ".";
		}
	}
	
}
