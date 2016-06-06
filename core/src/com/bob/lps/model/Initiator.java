package com.bob.lps.model;

/**
 * This is the class that represents the initiators among the DPost
 * declarations. It extends {@link DPostDeclaration}.
 * 
 * @author Alexandre Camus
 * 
 */
public class Initiator extends DPostDeclaration {

	/**
	 * Constructor of the class.
	 * 
	 * @param event
	 *            that is defined by this initiator.
	 * @param fluent
	 *            that is initiates by this initiator.
	 */
	public Initiator(SimpleSentence event, SimpleSentence fluent) {
		super(event, fluent);
	}
	
	/**
	 * Constructor of the class.
	 * 
	 * @param event
	 *            that is defined by this terminator.
	 * @param fluent
	 *            that is terminates by this terminator.
	 * @param body
	 *  		  the body of the postcondition.
	 */
	public Initiator(SimpleSentence event, SimpleSentence fluent, Clause body) {
		super(event, fluent, body);
	}
	
	/**
	 * Returns the initiator in the form of:
	 * "initiates(event, fluent)".
	 * 
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		String string;
		string = "initiates(" + this.event.toString() + ", " + this.fluent.toString() +")";
		if (this.body != null) {
			string += " :- " + this.body.toString();
		}
		
		return string;
	}

}
