package com.bob.lps.model;

/**
 * This is the class that represents the terminators among the DPost
 * declarations. It extends {@link DPostDeclaration}.
 * 
 * @author Alexandre Camus
 * 
 */
public class Terminator extends DPostDeclaration {

	/**
	 * Constructor of the class.
	 * 
	 * @param event
	 *            that is defined by this terminator.
	 * @param fluent
	 *            that is terminates by this terminator.
	 */
	public Terminator(SimpleSentence event, SimpleSentence fluent) {
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
	public Terminator(SimpleSentence event, SimpleSentence fluent, Clause body) {
		super(event, fluent, body);
	}
	
	/**
	 * Returns the terminator in the form of:
	 * "terminates(event, fluent)".
	 * 
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		String string;
		string = "terminates(" + this.event.toString() + ", " + this.fluent.toString() + ")";
		if (this.body != null) {
			string += " :- " + this.body.toString();
		}
		
		return string;
	}

}
