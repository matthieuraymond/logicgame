package com.bob.lps.model;

/**
 * This class implements the {@code CycleState} interface. It performs the
 * update step of the database.
 * 
 * @author Alexandre Camus
 * @see CycleState
 * @see Database
 */
class DatabaseUpdateState implements CycleState {
	
	/**
	 * Empty constructor of the class.
	 */
	public DatabaseUpdateState() {
	}

	/**
	 * This is the implementation of the
	 * {@link CycleState#handlerMethod(CycleHandler, String, RuleSet)
	 * CycleState#handlerMethod()}. Here it performs the update of the database.
	 * 
	 * @param STATE_CONTEXT
	 *            the manager of the state which is here the only instance of
	 *            {@code CycleHandler}.
	 * @param NAME
	 *            the name of the step. It has no use except to debug.
	 * @param events
	 *            an object {@code RuleSet} that contains the events/actions
	 *            that will be or have been performed during the very cycle.
	 * @see CycleHandler#handlerMethod(String)
	 */
	@Override
    public void handlerMethod(final CycleHandler STATE_CONTEXT, final String NAME, RuleSet events) {
		this.updates(events);
		STATE_CONTEXT.setState(new FiringRulesState());
    }

	/**
	 * This is the private action of the state. It cannot be used except in the
	 * context of the {@code handlerMethod()}. Here this is the update of the
	 * database.
	 * 
	 * @param events
	 *            an object {@code RuleSet} that contains the events/actions
	 *            that will be or have been performed during the very cycle.
	 * @see Database#updates(RuleSet)
	 * @see #handlerMethod(CycleHandler, String, RuleSet)
	 */
	private void updates(RuleSet events) {
		Database.getInstance().updates(events);
	}
}
