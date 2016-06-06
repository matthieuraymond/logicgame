package com.bob.lps.model;

/**
 * This is an abstract class to represent the DPost declarations.
 * 
 * @author Alexandre Camus
 *
 */
public abstract class DPostDeclaration {
	
	protected SimpleSentence event;
	protected SimpleSentence fluent;
	protected Clause body;
	
	/**
	 * Constructor of the object.
	 * 
	 * @param event
	 *            is the general form of event (with often free variables).
	 * @param fluent
	 *            is also the general form fluent. It is the terminated or
	 *            created by the event.
	 */
	protected DPostDeclaration(SimpleSentence event, SimpleSentence fluent) {
		this.event = event;
		this.fluent = fluent;
		this.body = null;
	}
	
	/**
	 * Constructor of the object.
	 * 
	 * @param event
	 *            is the general form of event (with often free variables).
	 * @param fluent
	 *            is also the general form fluent. It is the terminated or
	 *            created by the event.
	 * @param body
	 * 			  is the body of the postcondition.
	 */
	protected DPostDeclaration(SimpleSentence event, SimpleSentence fluent, Clause body) {
		this.event = event;
		this.fluent = fluent;
		this.body = body;
	}

	/**
	 * Gets the event of the declaration.
	 * 
	 * @return the event contained in the declaration.
	 */
	public SimpleSentence getEvent() {
		return this.event;
	}

	/**
	 * Gets the fluent of the declaration.
	 * 
	 * @return the fluent contained in the declaration.
	 */
	public SimpleSentence getFluent() {
		return this.fluent;
	}
	
	/**
	 * Gets the bound fluent according to the bindings of the specified event.
	 * <p>
	 * This method is used to apply certain values to the variable of the event
	 * and to pass these values to the fluent.
	 * 
	 * @param event
	 *            that is bound and corresponds to this declaration.
	 * @param rules
	 * 			  the database.
	 * @return the bound fluent according to the bindings.
	 */
	public SimpleSentence getGroundFluent(SimpleSentence event, RuleSet rules) {
		SubstitutionSet variablesBinding = this.event.unify(event, new SubstitutionSet());
		if (this.body != null) {
			variablesBinding = this.body.getSolver(rules, variablesBinding, null).nextSolution();
			if (variablesBinding == null) {
				
				return null;
			}
		}

        return this.fluent.replaceVariables(variablesBinding);
	}
	
}
