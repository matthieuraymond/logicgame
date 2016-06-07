package com.bob.lps.model;

/**
 * This is the interface to implement in order to addActor steps to the cycle.
 * Currently there are three steps: {@link DatabaseUpdateState},
 * {@link FiringRulesState} and {@link SolvingGoalState}.
 * 
 * @author Alexandre Camus
 * @see CycleHandler
 */
interface CycleState {

	/**
	 * Sends the request to the current state to operate its behavior.
	 * <p>
	 * This method should be the only way to perform the action of a state. It
	 * is called by the {@link CycleHandler#handlerMethod(String)
	 * handlerMethod()} of the class {@code CycleHandler} which manages the
	 * cycles.
	 * 
	 * @param STATE_CONTEXT
	 *            the manager of the state which is here the only instance of
	 *            {@code CycleHandler}.
	 * @param NAME
	 *            the name of the step. It has no use except to debug.
	 * @param events
	 *            an object {@code RuleSet} that contains the events/actions
	 *            that will be or have been performed during the very cycle.
	 */
	void handlerMethod(final CycleHandler STATE_CONTEXT, final String NAME, RuleSet events);

}
