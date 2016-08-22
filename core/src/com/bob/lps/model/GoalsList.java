/**
 * 
 */
package com.bob.lps.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This is a singleton class that represents the goals list of the framework. It
 * contains the method to solve the goals.
 * <p>
 * The constructor is private as you must not use it. Instead use the
 * {@code getInstance()} method to get the only object of the class (or to
 * create it).
 * 
 * @author Alexandre Camus
 * @see #solveGoals(RuleSet, RuleSet)
 */
public class GoalsList {
	
	private RuleSet nextEvents;
	private GoalSet goalsDefinitions;
	private Map<Goal, AbstractSolutionNode> goalsList;
	private static volatile GoalsList instance = null;

	public static void reset() {
		instance = null;
	}

	/**
	 * Constructor of the class.
	 */
	private GoalsList() {
		this.goalsDefinitions = new GoalSet();
		this.goalsList = new HashMap<Goal, AbstractSolutionNode>();
		this.nextEvents = new RuleSet();
	}
	
	/**
	 * This is the method to get an instance of the class.
	 * Use it as shown: {@code Database.getInstance()}
	 * 
	 * @return the only instance of the class {@code Database}.
	 */
	public static GoalsList getInstance() {
		if (GoalsList.instance == null) {
			synchronized (GoalsList.class) {
				if (GoalsList.instance == null) {
					GoalsList.instance = new GoalsList();
				}
			}
		}

		return GoalsList.instance;
	}
	
	/**
	 * Sets the goals definitions set.
	 * 
	 * @param goalsDefinitions
	 *            the set containing all the goals definitions.
	 */
	public void setGoalsDefinitions(GoalSet goalsDefinitions) {
		this.goalsDefinitions = goalsDefinitions;
	}
	
	/**
	 * Gets the goals definitions set.
	 * 
	 * @return a {@code GoalSet} object containing all the goals definitions.
	 */
	public GoalSet getGoalsDefinitions() {
		return this.goalsDefinitions;
	}

	/**
	 * Adds the specified event to the list of events that will be performed
	 * during the getNext cycle.
	 * 
	 * @param event
	 *            the event to addActor.
	 */
	public void addNextEvent(SimpleSentence event) {
		if (event.getSolver(this.nextEvents, new SubstitutionSet(), null).nextSolution() == null) {
			this.nextEvents.addRule(new Rule(event));
		}
	}
	
	/**
	 * Adds the specified goal to the list of goals.
	 * 
	 * @param goal
	 *            the goal to addActor.
	 * @param ruleSet
	 *            the rules to use to build the tree of proof.
	 */
	public void addGoal(SimpleSentence goal, RuleSet ruleSet) {
		// Create the bound goal from the generic definition
		Goal completeGoal = new Goal(this.goalsDefinitions.getGoal(goal.getName()), goal);
		
		// Create an entry in the list of goals to solve
		this.goalsList.put(completeGoal, completeGoal.getNextDefinition().getSolver(ruleSet, new SubstitutionSet(), null));
	}
	
	private boolean backtrack(AbstractSolutionNode leaf, Goal goal, RuleSet rulesAndEvents, RuleSet ruleSet, RuleSet events) {
		// If there is another definition use it to backtrack.
		if (goal.hasNextDefinition()) {
			leaf = goal.getNextDefinition().getSolver(rulesAndEvents, new SubstitutionSet(), null);
			this.goalsList.put(goal, leaf);
			
			return solveGoal(goal, ruleSet, events, rulesAndEvents);
		
		// If there is no other definition reset and wait for the getNext cycle.
		} else {
			goal.reset();
			leaf = goal.getNextDefinition().getSolver(rulesAndEvents, new SubstitutionSet(), null);
			this.goalsList.put(goal, leaf);
			
			return false;
		}
	}
	
	/**
	 * Solves the specified goal. It starts from the last node and carries on,
	 * hopping the new cycle will provide new materials for the proof.
	 * 
	 * @param goal
	 *            the goal to solve.
	 * @param ruleSet
	 *            the ruleSet to reset the tree with.
	 * @param events
	 *            the events that have been used to update the database.
	 * @return true if the goal is solved. False otherwise.
	 */
	public boolean solveGoal(Goal goal, RuleSet ruleSet, RuleSet events, RuleSet rulesAndEvents) {
		// Get the leaf of the tree
		AbstractSolutionNode leaf = this.goalsList.get(goal);
		
		// Reset the leaf to the new ruleSet and restart building the tree
		leaf.reset(leaf.getParentSolution(), rulesAndEvents);
		SubstitutionSet solution = leaf.nextSolution();
		leaf = leaf.getDeepestLeaf();
		this.goalsList.put(goal, leaf);
		
		// If there is a solution the goal is solved
		if (solution != null) {
			
			return true;
		}
		
		// Otherwise
		AbstractSolutionNode head = leaf;
		
		// If the leaf is a stuck and, the process is done on the head of the and
		if (head instanceof AndSolutionNode) {
			head = ((AndSolutionNode) head).getHeadSolutionNode();
		}
		
		// If the leaf is a stuck not, the process is done on the tail of the not
		if (head instanceof NotSolutionNode) {
			head = ((NotSolutionNode) head).getTailSolutionNode();
		}
		
		// If the leaf is an arithmetic expression, there is nothing to do except backtracking
		if (head instanceof ArithmeticSolutionNode) {
			
			return backtrack(leaf, goal, rulesAndEvents, ruleSet, rulesAndEvents);
		}
		
		// If the leaf is a simple sentence, get its nature
		if (head instanceof SimpleSentenceSolutionNode) {
			SimpleSentence simpleSentence = (SimpleSentence) head.getClause().replaceVariables(leaf.getParentSolution());
			switch (((SimpleSentenceSolutionNode) head).getType()) {
			case "fact":
				// Wait if it is a fact
				// Test if the limit is not exceeded
				if (((SimpleSentenceSolutionNode) head).limitExceed()) {
					
					return backtrack(leaf, goal, rulesAndEvents, ruleSet, events);
				}
				// Otherwise update the limit to consider this waiting cycle
				((SimpleSentenceSolutionNode) head).limitUpdate();
				
				return false;
			case "rule":
				// Backtrack if it is a rule
				
				return backtrack(leaf, goal, rulesAndEvents, ruleSet, events);
			case "undefined": // It might be an action or a fact
			case "action":
				// If it is an action, try to trigger it
			default:
				// Test if the limit is not exceeded
				if (((SimpleSentenceSolutionNode) head).limitExceed()) {
					
					return backtrack(leaf, goal, rulesAndEvents, ruleSet, rulesAndEvents);
				}
				// Otherwise update the limit to consider this waiting cycle
				((SimpleSentenceSolutionNode) head).limitUpdate();
				
				// Try to trigger the possible action
				// Get the corresponding action
				Action action = Database.getInstance().getDSet().getAction(simpleSentence.getName());
				
				// If it is an action addActor it to the getNext action to do
				if (action != null) {
					RuleSet rulesAndNextEvents = new RuleSet(ruleSet.getRules());
					rulesAndNextEvents.addRules(this.nextEvents.getRules());
					if (action.actionsAllowed(simpleSentence, rulesAndEvents, rulesAndNextEvents)) {
						this.addNextEvent(simpleSentence);
					}
					// Trigger action
					
					return false;
				}
				// Wait
				
				return false;
			}
		}
		
		// Otherwise, according to the strategy, get the getNext definition to check.
		return backtrack(leaf, goal, rulesAndEvents, ruleSet, rulesAndEvents);
	}

	/**
	 * Solves all the goal in the list.
	 * 
	 * @param events
	 *            the events that have been performed during the cycle.
	 */
	public void solveGoals(RuleSet ruleSet, RuleSet events) {
		RuleSet rulesAndEvents = new RuleSet(ruleSet.getRules());
		rulesAndEvents.addRules(events.getRules());
    	
    	Set<Goal> keys = this.goalsList.keySet();
    	// Try to solve every goal in the list, only and exactly once per cycle
    	for(Iterator<Goal> goals = keys.iterator(); goals.hasNext();) {
    		Goal goal = goals.next();
    		if (solveGoal(goal, ruleSet, events, rulesAndEvents)) {
    			goals.remove();
    		}
    	}
    	
    	CycleHandler.getInstance().setEvents(this.nextEvents);
    	this.nextEvents.getRules().clear();
	}

	/**
	 * Returns the set in the form of:
	 * "{
	 * 	[definitionChosen] => [currentNode]
	 * }".
	 * 
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		String res = "{\n";
		
		for(Goal goal : this.goalsList.keySet()) {
			AbstractSolutionNode tree = this.goalsList.get(goal);
			res += "[" + goal.getGoal().toString() + " :- ";
			res += tree.getClause().toString() + "] => [";
			res += tree.getDeepestLeaf().getClause().replaceVariables(tree.getDeepestLeaf().getParentSolution()).toString() + "]\n";
		}
		
		res += "}";
		return res;
	}

	/**
	 * Returns the active goals set
	 */
	public Set<Goal> getActiveGoals() {
		return goalsList.keySet();
	}
}
