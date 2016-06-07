/**
 * 
 */
package com.bob.lps.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the D set of the database. This is where all the
 * actions are defined.
 * <p>
 * The actions are stored as {@code Action} objects in a {@code ArrayList}.
 * 
 * @author Alexandre Camus
 * 
 */
public class DSet {

	List<Action> actions;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param actions
	 *            an array of the actions to be stored in the set or each action
	 *            as a parameter.
	 */
	public DSet(Action... actions) {
		this.actions = new ArrayList<Action>(Arrays.asList(actions));
	}
	
	/**
	 * Constructor of the class.
	 * 
	 * @param actions
	 *            an array of the actions to be stored in the set or each action
	 *            as a parameter.
	 */
	public DSet(List<Action> actions) {
		this.actions = actions;
	}
	
	/**
	 * Adds the specified action to the set.
	 * 
	 * @param action
	 *            to addActor in the set.
	 */
	public void addAction(Action action) {
		this.actions.add(action);
	}
	
	/**
	 * Gets the action corresponding to the specified name.
	 * 
	 * @param eventName the name of the action to get.
	 * @return the action as an {@code Action} object.
	 */
	public Action getAction(String eventName) {
		for(Action action : actions) {
			if (action.getPredicate().getName().equals(eventName)) {
				
				return action;
			}
		}
		
		return null;
	}
	
	

	/**
	 * Returns the set in the form of:
	 * "D set:
	 *  {
	 *  Action.toString()
	 *  Action.toString()
	 *  }
	 * 
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		String string = "D set: \n{\n";
		String delimiter = "";
		for (Action action : actions) {
			string += delimiter + action.toString();
			delimiter = "\n";
		}
		
		return string + "\n}";
	}
	
}
