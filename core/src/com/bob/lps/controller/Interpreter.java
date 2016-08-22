package com.bob.lps.controller;

import com.bob.lps.model.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the controller class. It is a static class. It translates Prolog-like
 * declaration into objects for the program.
 * 
 * @author Alexandre Camus
 * @deprecated
 */
public class Interpreter {

	/**
	 * Checks whether the input string matches the constant pattern. A constant
	 * must start with a number or a non-capital letter. It is a alpha-numeric
	 * word and can have the symbol _.
	 * <p>
	 * This method is used to throw exceptions in {@link #stringToConstant(String)
	 * stringToConstant()}.
	 * 
	 * @param string
	 *            that is the constant.
	 * @return true if the string correspond to the constant pattern.
	 */
	public static boolean isConstant(String string) {
		// Delete all the spaces before checking any matching
		string = string.replaceAll(" ", "");

		return string.matches("[a-z0-9][A-Za-z0-9_]*");
	}
    
	/**
	 * Converts the input string into a {@code Constant} object. The input
	 * string should be a constant.
	 * 
	 * @param string
	 *            to be converted into the {@code Constant} object.
	 * @return the {@code Constant} object representing the constant string
	 *         input.
	 * @throws RemoteException
	 *             if the input does not correspond to a constant according to
	 *             the method {@code isConstant()}.
	 * @see Constant
	 * @see #isConstant(String)
	 */
	public static Constant stringToConstant(String string) throws RemoteException {
		// Delete any spaces before converting
		string = string.replaceAll(" ", "");

		// Create the Constant to be returned
		Constant res;

		if (isConstant(string)) {
			res = new Constant(string);

			return res;
		} else {
			throw new RemoteException("It is not a Constant-like string.");
		}
	}
    
    
	/**
	 * Checks whether the input string matches the variable pattern. A variable
	 * must start a capital letter. It is a alpha-numeric word and can have the
	 * symbol _.
	 * <p>
	 * This method is used to throw exceptions in
	 * {@link #stringToVariable(String, HashMap) stringToVariable()}.
	 * 
	 * @param string
	 *            that is the variable.
	 * @return true if the string correspond to the variable pattern.
	 */
	public static boolean isVariable(String string) {
		// Delete all the spaces before checking any matching
		string = string.replaceAll(" ", "");

		return string.matches("[A-Z][A-Za-z0-9_]*");
	}
    
	/**
	 * Converts the input string into a {@code Variable} object. The input
	 * string should be a variable.
	 * <p>
	 * The variable {@code variables} is used to pass the variables created to
	 * the whole clause to avoid to create two different variables for the same
	 * one. Initialize it with {@code null} or a brand new {@code HashMap}.
	 * 
	 * @param string
	 *            to be converted into the {@code Variable} object.
	 * @param variables
	 *            the variables already created to be reused if necessary and it
	 *            will be updated with the new created variables.
	 * @return the {@code Variable} object representing the variable string
	 *         input.
	 * @throws RemoteException
	 *             if the input does not correspond to a variable according to
	 *             the method {@code isVariable()}.
	 * @see Variable
	 * @see #isVariable(String)
	 */
	public static Variable stringToVariable(String string, HashMap<String, Variable> variables) throws RemoteException {
		// Delete any spaces before converting
		string = string.replaceAll(" ", "");

		// Create the Variable to be returned
		Variable res;

		if (variables == null) {
			variables = new HashMap<String, Variable>();
		}

		if (isVariable(string)) {
			if (variables.containsKey(string)) {
				res = variables.get(string);
			} else {
				res = new Variable(string);
				variables.put(string, res);
			}

			return res;
		} else {
			throw new RemoteException("It is not a Variable-like string.");
		}
	}
    
	/**
	 * Checks whether the input string matches the simple sentence pattern. It
	 * is a predicate or an event. This string has the form of:
	 * 'constant(parameters)' where 'parameters' are variables or constants
	 * separate by a comma. Constants and variables are checks thanks to their
	 * own methods: {@link #isConstant(String) isConstant()} and
	 * {@link #isVariable(String) isVariable()}.
	 * <p>
	 * See a Prolog documentation for more information about Prolog predicate.
	 * This method is used to throw exceptions in
	 * {@link #stringToSimpleSentence(String, HashMap) stringToSimpleSentence()}.
	 * <p>
	 * This method is used to throw exceptions in 
	 * {@link #stringToSimpleSentence(String, HashMap) stringToSimpleSentence()}.
	 * 
	 * @param string
	 *            to be checked whether it is a simple sentence or not.
	 * @return true if it matches the general form of a simple sentence,
	 *         otherwise false.
	 * @see <a href="http://en.wikipedia.org/wiki/Prolog">Prolog
	 *      documentation</a>
	 */
	public static boolean isSimpleSentence(String string) {
		// Delete all the spaces before checking any matching
		string = string.replaceAll(" ", "");
		
		// Initialize the search over the string
		int searchIndexStart = 0;
		int searchIndexEnd = string.indexOf('(');
		
		// Check if the name is a constant
		if (searchIndexEnd == -1 || !isConstant(string.substring(searchIndexStart, searchIndexEnd))) {
			
			return false;
		}
		
		// Go to the getNext part of the string to check
		searchIndexStart = searchIndexEnd + 1;
		
		// If the last character is not a closing parenthesis it is not a simple sentence.
		if (string.charAt(string.length() - 1) != ')') {
			
			return false;
		}
		
		// If the last character follows immediately the opening bracket it is a simple sentence.
		if (string.length() - 1 == searchIndexStart) {
			 
			return true;
		}
		
		// If not search the parameters and so search for commas while you can find one
		// If there is no comma anymore, then it is the final parameter
		searchIndexEnd = string.indexOf(',', searchIndexStart);
		String searchString;
		while(searchIndexEnd != -1) {
			searchString = string.substring(searchIndexStart, searchIndexEnd);
			
			// If the search string is a parameter, then go to the getNext comma and start searching from it
			if (isConstant(searchString) || isVariable(searchString) || isSimpleSentence(searchString)) {
				searchIndexStart = searchIndexEnd + 1;
				searchIndexEnd = string.indexOf(',', searchIndexStart);
			
			// If it is not a parameter, then go to the getNext comma and carry on
			} else {
				searchIndexEnd = string.indexOf(',', searchIndexEnd + 1);
			}
		}
		
		// Check the last parameter
		searchString = string.substring(searchIndexStart, string.length() - 1);
		// If it is actually a parameter, succeed, if not fail
		
		return isConstant(searchString) || isVariable(searchString) || isSimpleSentence(searchString);
	}

	/**
	 * Converts the input string into a {@code SimpleSentence} object. The input
	 * string should be a simple sentence. It uses the methods
	 * {@link #stringToAnd(String, HashMap) stringToAnd()},
	 * {@link #stringToNegation(String, HashMap) stringToNegation()} and
	 * {@link #stringToSimpleSentence(String, HashMap) stringToSimpleSentence()}
	 * recursively to create the whole clause.
	 * <p>
	 * The variable {@code variables} is used to pass the variables created to
	 * the whole clause to avoid to create two different variables for the same
	 * one. Initialize it with {@code null} or a brand new {@code HashMap}.
	 * 
	 * @param string
	 *            to be converted into the {@code SimpleSentence} object.
	 * @param variables
	 *            the variables already created to be reused if necessary and it
	 *            will be updated with the new created variables.
	 * @return the {@code SimpleSentence} object representing the simple
	 *         sentence string input.
	 * @throws RemoteException
	 *             if the input does not correspond to a simple sentence
	 *             according to the method {@code isSimpleSentence}.
	 * @see SimpleSentence
	 * @see #isSimpleSentence(String)
	 */
	public static SimpleSentence stringToSimpleSentence(String string, HashMap<String, Variable> variables) throws RemoteException {
		// Delete any spaces before converting
		string = string.replaceAll(" ", "");

		if (isSimpleSentence(string)) {

			if (variables == null) {
				variables = new HashMap<String, Variable>();
			}

			// Initialize the search over the string
			int searchIndexStart = 0;
			int searchIndexEnd = string.indexOf('(');

			// Store the name is a constant
			Constant name = new Constant(string.substring(searchIndexStart, searchIndexEnd));

			// Go to the getNext part of the string to check
			searchIndexStart = searchIndexEnd + 1;

			// If there is no parameter.
			if (string.length() - 1 == searchIndexStart) {

				return new SimpleSentence(name);
			}

			// If not search the parameters and so search for commas while you can find one
			// If there is no comma anymore, then it is the final parameter
			ArrayList<Unifiable> parameters = new ArrayList<Unifiable>();
			searchIndexEnd = string.indexOf(',', searchIndexStart);
			String searchString;
			while (searchIndexEnd != -1) {
				searchString = string.substring(searchIndexStart, searchIndexEnd);

				// If the search string is a constant
				if (isConstant(searchString)) {
					searchIndexStart = searchIndexEnd + 1;
					searchIndexEnd = string.indexOf(',', searchIndexStart);
					parameters.add(stringToConstant(searchString));

					// If the search string is a variable
				} else if (isVariable(searchString)) {
					searchIndexStart = searchIndexEnd + 1;
					searchIndexEnd = string.indexOf(',', searchIndexStart);
					parameters.add(stringToVariable(searchString, variables));

					// If the search string is a simple sentence
				} else if (isSimpleSentence(searchString)) {
					searchIndexStart = searchIndexEnd + 1;
					searchIndexEnd = string.indexOf(',', searchIndexStart);
					parameters.add(stringToSimpleSentence(searchString, variables));

					// If it is not a parameter, then go to the getNext comma and
					// carry on
				} else {
					searchIndexEnd = string.indexOf(',', searchIndexEnd + 1);
				}
			}

			// Store the last parameter
			searchString = string.substring(searchIndexStart, string.length() - 1);
			if (isConstant(searchString)) {
				parameters.add(stringToConstant(searchString));

				// If the search string is a variable
			} else if (isVariable(searchString)) {
				parameters.add(stringToVariable(searchString, variables));

				// If the search string is a simple sentence
			} else if (isSimpleSentence(searchString)) {
				parameters.add(stringToSimpleSentence(searchString, variables));
			}

			return new SimpleSentence(name, parameters.toArray(new Unifiable[parameters.size()]));

		} else {
			throw new RemoteException("It is not a Prolog-like predicate.");
		}
	}
    
	/**
	 * Checks whether the input string matches the DPost declaration pattern. A
	 * DPost declaration is a simple sentence which name is 'initiates' or
	 * 'terminates'. It has two parameters: an event which is a simple sentence
	 * and a fluent which is also a simple sentence.
	 * <p>
	 * This method is used to throw exceptions in
	 * {@link #stringToDPost(String, HashMap) stringToDPost()}.
	 * <p>
	 * See a file_return documentation for more details on DPost declaration.
	 * 
	 * @param string
	 *            to be checked whether or not it matches the DPost declaration
	 *            syntax.
	 * @return true if the string is a DPost declaration, otherwise false.
	 */
	public static boolean isDPostDeclaration(String string) {
		// Delete all the spaces before checking any matching
		string = string.replaceAll(" ", "");
		
		// Initialize the search over the string
		int searchIndexStart = 0;
		int searchIndexEnd = string.indexOf('(');
		
		// Check if the name is 'initiates' or 'terminates'
		if (searchIndexEnd == -1 || !string.substring(searchIndexStart, searchIndexEnd).matches("initiates|terminates")) {
			
			return false;
		}
		
		// Go to the getNext part of the string to check
		searchIndexStart = searchIndexEnd + 1;
		
		// If the last character is not a closing parenthesis it is not a DPost declaration.
		if (string.charAt(string.length() - 1) != ')') {
			
			return false;
		}
		
		// If the last character follows immediately the opening bracket it is not a DPost declaration.
		if (string.length() - 1 == searchIndexStart) {
			 
			return false;
		}
		
		// If not search the two simple sentences and so search for commas while you can find one
		// If there is no comma anymore, then it is not a DPost declaration
		searchIndexEnd = string.indexOf(',', searchIndexStart);
		String searchString;
		while(searchIndexEnd != -1) {
			searchString = string.substring(searchIndexStart, searchIndexEnd);
			
			// If the search string is a simple sentence, then go to the getNext comma and start searching from it
			if (isSimpleSentence(searchString)) {
				searchIndexStart = searchIndexEnd + 1;
				searchIndexEnd = string.indexOf(',', searchIndexStart);
				
				// Check the last parameter
				searchString = string.substring(searchIndexStart, string.length() - 1);
				// If it is actually a parameter, succeed, if not fail
				
				return isSimpleSentence(searchString);
			
			// If it is not a simple sentence, then go to the getNext comma and carry on
			} else {
				searchIndexEnd = string.indexOf(',', searchIndexEnd + 1);
			}
		}
		
		// If we found no simple sentence, it is not a DPost declaration
		
		return false;
	}
    
	/**
	 * Converts the input string into a {@code DPostDeclaration} object. The
	 * input string should be a DPost declaration. It creates an
	 * {@code Initiator} or a {@code Terminator} object depending on the input.
	 * <p>
	 * The variable {@code variables} is used to pass the variables created to
	 * the whole clause to avoid to create two different variables for the same
	 * one. Initialize it with {@code null} or a brand new {@code HashMap}.
	 * 
	 * @param string
	 *            to be converted into the {@code DPostDeclaration} object.
	 * @param variables
	 *            the variables already created to be reused if necessary and it
	 *            will be updated with the new created variables.
	 * @return the {@code DPostDeclaration} object representing the simple
	 *         sentence string input. It will be either a {@code Initiator}
	 *         object or an {@code Terminator} object.
	 * @throws RemoteException
	 *             if the input does not correspond to a DPostDeclaration
	 *             according to the method {@code isDPostDeclaration()}.
	 * @see SimpleSentence
	 * @see #isSimpleSentence(String)
	 */
	public static DPostDeclaration stringToDPost(String string, HashMap<String, Variable> variables) throws RemoteException {
		// Delete any spaces before converting
		string = string.replaceAll(" ", "");

		if (isDPostDeclaration(string)) {
			if (variables == null) {
				variables = new HashMap<String, Variable>();
			}

			SimpleSentence simpleSentence = stringToSimpleSentence(string, variables);

			if (simpleSentence.getName().equals("initiates")) {

				return new Initiator(
						(SimpleSentence) simpleSentence.getTerm(1),
						(SimpleSentence) simpleSentence.getTerm(2));
			} else {

				return new Terminator(
						(SimpleSentence) simpleSentence.getTerm(1),
						(SimpleSentence) simpleSentence.getTerm(2));
			}
		} else {
			throw new RemoteException("It is not a DPost declaration.");
		}
	}

	/**
	 * Checks whether the input string matches an and-clause. An and-clause is a
	 * n-ary operator. Each operand is separated by a '&'. An operand can be a
	 * simple sentence or a negative clause.
	 * <p>
	 * This method is used to throw exceptions in
	 * {@link #stringToAnd(String, HashMap) stringToAnd()}.
	 * 
	 * @param string
	 *            to be checked whether or not it is an and-clause.
	 * @return true if the string is an and-clause, otherwise false.
	 */
	public static boolean isAnd(String string) {
		// Delete all the spaces before checking any matching
		string = string.replaceAll(" ", "");

		int searchIndexStart = 0;
		int searchIndexEnd = string.indexOf('&', searchIndexStart);
		String searchString;

		// While it finds a & it search for an operand
		// When there is no & anymore, it is the final operand
		while (searchIndexEnd != -1) {
			searchString = string.substring(searchIndexStart, searchIndexEnd);
			
			// If the search string is a valid operand, then go to the getNext
			// & and start searching from it
			if (isSimpleSentence(searchString) || isNegation(searchString) || isEquality(searchString)) {
				searchIndexStart = searchIndexEnd + 1;
				searchIndexEnd = string.indexOf('&', searchIndexStart);

			// If it is not a valid operand, then it is not a valid operator
			} else {
				searchIndexEnd = string.indexOf('&', searchIndexEnd + 1);
			}
		}

		// Check the last operand
		searchString = string.substring(searchIndexStart);

		// If it is actually an operand, succeed, if not fail
		return isSimpleSentence(searchString) || isNegation(searchString) || isEquality(searchString);
	}
	
	/**
	 * Checks whether the input string matches an negative clause. A
	 * negative clause is a unary operator. Its only operand is a simple
	 * sentence or an and-clause.
	 * <p>
	 * This method is used to throw exceptions in
	 * {@link #stringToNegation(String, HashMap) stringToNegation()}.
	 * 
	 * @param string
	 * 			  to be checked whether or not it is a negative clause.
	 * @return true if the string is a negative clause, otherwise false.
	 */
	public static boolean isNegation(String string) {
		// Delete all the spaces before checking any matching
		string = string.replaceAll(" ", "");
		
		// If it doesn't start with a negation it is not a negative clause
		if (string.charAt(0) != '!') {
			
			return false;
		}
		
		if (string.charAt(1) == '(') {
			if (string.charAt(string.length() -1) != ')') {
				
				return false;
			}
			string = string.substring(2, string.length() - 1);
		} else {
			string = string.substring(1);
		}
		
		return isSimpleSentence(string) || isNegation(string) || isAnd(string) || isEquality(string);
	}
	
	/**
	 * Checks whether the input string matches an equality clause. An equality
	 * clause is a binary operator. Its both operands are a variable and a
	 * unifiable sentence.
	 * <p>
	 * This method is used to throw exceptions in
	 * {@link #stringToEquality(String, HashMap) stringToEquality()}.
	 * 
	 * @param string
	 *            to be checked whether or not it is an equality clause.
	 * @return true if the string is an equality clause, otherwise false.
	 */
	public static boolean isEquality(String string) {
		// Delete all the spaces before checking any matching
		string = string.replaceAll(" ", "");
		
		String[] operands = string.split("==");
		
		if (operands.length != 2) {
			
			return false;
		}
		
		boolean operand1 = isVariable(operands[0]);
		boolean operand2 = isConstant(operands[1]) || isVariable(operands[1]) || isSimpleSentence(operands[1]);
		
		return operand1 && operand2;
	}
	
	/**
	 * Converts the input string into a {@code And} object. The input string
	 * should be an and-clause. It uses the methods
	 * {@link #stringToAnd(String, HashMap) stringToAnd()},
	 * {@link #stringToNegation(String, HashMap) stringToNegation()} and
	 * {@link #stringToSimpleSentence(String, HashMap) stringToSimpleSentence()}
	 * recursively to create the whole clause.
	 * <p>
	 * The variable {@code variables} is used to pass the variables created to
	 * the whole clause to avoid to create two different variables for the same
	 * one. Initialize it with {@code null} or a brand new {@code HashMap}.
	 * 
	 * @param string
	 *            to be converted into the {@code And} object.
	 * @param variables
	 *            the variables already created to be reused if necessary and it
	 *            will be updated with the new created variables.
	 * @return the {@code And} object representing the and-clause string input.
	 * @throws RemoteException
	 *             if the input does not correspond to an and-clause according
	 *             to the method {@code isAnd()}.
	 * @see And
	 * @see #isAnd(String)
	 */
	public static And stringToAnd(String string, HashMap<String, Variable> variables) throws RemoteException {
		// Delete any spaces before converting
		string = string.replaceAll(" ", "");

		if (isAnd(string)) {
			// Split the string to get the name and the parameters of the sentence
			ArrayList<Clause> operands = new ArrayList<Clause>();
	
			int searchIndexStart = 0;
			int searchIndexEnd = string.indexOf('&', searchIndexStart);
			String searchString;
	
			// While it finds a & it search for an operand
			// When there is no & anymore, it is the final operand
			while (searchIndexEnd != -1) {
				searchString = string.substring(searchIndexStart, searchIndexEnd);
				
				// If the search string is a valid operand, then go to the getNext
				// & and start searching from it
				if (isSimpleSentence(searchString)) {
					searchIndexStart = searchIndexEnd + 1;
					searchIndexEnd = string.indexOf('&', searchIndexStart);
					operands.add(stringToSimpleSentence(searchString, variables));
				} else if (isNegation(searchString)) {
					searchIndexStart = searchIndexEnd + 1;
					searchIndexEnd = string.indexOf('&', searchIndexStart);
					operands.add(stringToNegation(searchString, variables));
				} else if (isEquality(searchString)) {
					searchIndexStart = searchIndexEnd + 1;
					searchIndexEnd = string.indexOf('&', searchIndexStart);
					operands.add(stringToEquality(searchString, variables));
	
				// If it is not a valid operand, then it is not a valid operator
				} else {
					searchIndexEnd = string.indexOf('&', searchIndexEnd + 1);
				}
			}
	
			// Check the last operand
			searchString = string.substring(searchIndexStart);
			if (isSimpleSentence(searchString)) {
				operands.add(stringToSimpleSentence(searchString, variables));
			} else if (isNegation(searchString)) {
				operands.add(stringToNegation(searchString, variables));
			} else if (isEquality(searchString)) {
				operands.add(stringToEquality(searchString, variables));
			}
	
			// If it is actually an operand, succeed, if not fail
			return new And(operands);
		} else {
			throw new RemoteException("It is not a AND-clause.");
		}
	}
	
	/**
	 * Converts the input string into a {@code Not} object. The input string
	 * should be a negative clause. It uses the methods
	 * {@link #stringToAnd(String, HashMap) stringToAnd()},
	 * {@link #stringToNegation(String, HashMap) stringToNegation()} and
	 * {@link #stringToSimpleSentence(String, HashMap) stringToSimpleSentence()}
	 * recursively to create the whole clause.
	 * <p>
	 * The variable {@code variables} is used to pass the variables created to
	 * the whole clause to avoid to create two different variables for the same
	 * one. Initialize it with {@code null} or a brand new {@code HashMap}.
	 * 
	 * @param string
	 *            to be converted into the {@code Not} object.
	 * @param variables
	 *            the variables already created to be reused if necessary and it
	 *            will be updated with the new created variables.
	 * @return the {@code Not} object representing the negative clause string
	 *         input.
	 * @throws RemoteException
	 *             if the input does not correspond to a negative clause
	 *             according to the method {@code isNot()}.
	 * @see Not
	 * @see #isNegation(String)
	 */
	public static Not stringToNegation(String string, HashMap<String, Variable> variables) throws RemoteException {
		// Delete any spaces before converting
		string = string.replaceAll(" ", "");

		if (variables == null) {
			variables = new HashMap<String, Variable>();
		}

		if (isNegation(string)) {
			if (string.charAt(1) == '(') {
				string = string.substring(2, string.length() - 1);
			} else {
				string = string.substring(1);
			}

			if (isSimpleSentence(string)) {

				return new Not(stringToSimpleSentence(string, variables));
			} else if (isEquality(string)) {

				return new Not(stringToEquality(string, variables));
			} else if (isNegation(string)) {

				return new Not(stringToNegation(string, variables));
			} else {

				return new Not(stringToAnd(string, variables));
			}
		} else {
			throw new RemoteException("It is not a negative clause.");
		}
	}
	
	/**
	 * Converts the input string into a {@code Equal} object. The input string
	 * should be an equality clause. It uses the methods
	 * {@link #stringToConstant(String) stringToConstant()},
	 * {@link #stringToVariable(String, HashMap) stringToVariable()} and
	 * {@link #stringToSimpleSentence(String, HashMap) stringToSimpleSentence()}
	 * recursively to create the whole clause.
	 * <p>
	 * The variable {@code variables} is used to pass the variables created to
	 * the whole clause to avoid to create two different variables for the same
	 * one. Initialize it with {@code null} or a brand new {@code HashMap}.
	 * 
	 * @param string
	 *            to be converted into the {@code Equal} object.
	 * @param variables
	 *            the variables already created to be reused if necessary and it
	 *            will be updated with the new created variables.
	 * @return the {@code Equal} object representing the equality clause string
	 *         input.
	 * @throws RemoteException
	 *             if the input does not correspond to an equality clause
	 *             according to the method {@code isEquality()}.
	 * @see Equal
	 * @see #isEquality(String)
	 */
	public static Equal stringToEquality(String string, HashMap<String, Variable> variables) throws RemoteException {
		// Delete any spaces before converting
		string = string.replaceAll(" ", "");
		
		if (variables == null) {
			variables = new HashMap<String, Variable>();
		}
		
		if (isEquality(string)) {
			String[] operands = string.split("==");
			
			Variable operand1 = stringToVariable(operands[0], variables);
			Unifiable operand2;
			if (isConstant(operands[1])) {
				operand2 = stringToConstant(operands[1]);
			} else if (isVariable(operands[1])) {
				operand2 = stringToVariable(operands[1], variables);
			} else {
				operand2 = stringToSimpleSentence(operands[1], variables);
			}
			
			return new Equal(operand1, operand2);
		} else {
			throw new RemoteException("It is not an equality clause.");
		}
	}
	
	/**
	 * Converts the input string into a {@code Clause} object. The input string
	 * should be a clause. It uses the methods
	 * {@link #stringToAnd(String, HashMap) stringToAnd()},
	 * {@link #stringToNegation(String, HashMap) stringToNegation()} and
	 * {@link #stringToSimpleSentence(String, HashMap) stringToSimpleSentence()}
	 * recursively to create the whole clause.
	 * <p>
	 * The variable {@code variables} is used to pass the variables created to
	 * the whole clause to avoid to create two different variables for the same
	 * one. Initialize it with {@code null} or a brand new {@code HashMap}.
	 * 
	 * @param string
	 *            to be converted into the {@code Clause} object.
	 * @param variables
	 *            the variables already created to be reused if necessary and it
	 *            will be updated with the new created variables.
	 * @return the {@code Clause} object representing the clause string input.
	 * @throws RemoteException
	 *             if the input does not correspond to a clause according to the
	 *             method {@code isNot()}, {@code isAnd()} or
	 *             {@code isSimpleSentence()}.
	 * @see Clause
	 * @see #isNegation(String)
	 * @see #isAnd(String)
	 * @see #isSimpleSentence(String)
	 */
	public static Clause stringToClause(String string, HashMap<String, Variable> variables) throws RemoteException {
		// Delete any spaces before converting
		string = string.replaceAll(" ", "");
		
		if (variables == null) {
			variables = new HashMap<String, Variable>();
		}
		
		if (isSimpleSentence(string)) {
			
			return stringToSimpleSentence(string, variables);
		} else if (isNegation(string)) {
			
			return stringToNegation(string, variables);
		} else if (isEquality(string)) {
			
			return stringToEquality(string, variables);
		} else if (isAnd(string)) {
			
			return stringToAnd(string, variables);
		} else {
			throw new RemoteException("It is not a clause.");
		}
	}
	
	/**
	 * Checks whether the input string matches a reactive rule. A reactive rule
	 * is a binary imply. Its first operand is a clause. Its second operand is a
	 * simple sentence.
	 * <p>
	 * This method is used to throw exceptions in
	 * {@link #stringToReactiveRule(String, HashMap) stringToReactiveRule()}.
	 * 
	 * @param string
	 *            to be checked whether or not it is a reactive rule.
	 * @return true if the string is a reactive rule, otherwise false.
	 */
	public static boolean isReactiveRule(String string) {
		// Delete all the spaces before checking any matching
		string = string.replaceAll(" ", "");
		
		String[] conditionsAndGoal = string.split("->");
		
		if (conditionsAndGoal.length != 2) {
			
			return false;
		}
		
		if (conditionsAndGoal[0].charAt(0) == '(') {
			conditionsAndGoal[0] = conditionsAndGoal[0].substring(1, conditionsAndGoal[0].length() - 1);
		}
		
		boolean conditions = isSimpleSentence(conditionsAndGoal[0]) || isNegation(conditionsAndGoal[0]) || isAnd(conditionsAndGoal[0]);
		
		return conditions && isSimpleSentence(conditionsAndGoal[1]);
	}
	
	/**
	 * Converts the input string into a {@code ReactiveRule} object. The input
	 * string should be a reactive rule. It uses the methods
	 * {@link #stringToClause(String, HashMap) stringToClause()} and
	 * {@link #stringToSimpleSentence(String, HashMap) stringToSimpleSentence()}
	 * recursively to create the reactive rule.
	 * <p>
	 * The variable {@code variables} is used to pass the variables created to
	 * the whole clause to avoid to create two different variables for the same
	 * one. Initialize it with {@code null} or a brand new {@code HashMap}.
	 * 
	 * @param string
	 *            to be converted into the {@code ReactiveRule} object.
	 * @param variables
	 *            the variables already created to be reused if necessary and it
	 *            will be updated with the new created variables.
	 * @return the {@code ReactiveRule} object representing the reactive rule
	 *         string input.
	 * @throws RemoteException
	 *             if the input does not correspond to a reactive rule according
	 *             to the method {@code isReactiveRule()}
	 * @see ReactiveRule
	 * @see #isReactiveRule(String)
	 */
	public static ReactiveRule stringToReactiveRule(String string, HashMap<String, Variable> variables) throws RemoteException {
		// Delete any spaces before converting
		string = string.replaceAll(" ", "");
		
		if (variables == null) {
			variables = new HashMap<String, Variable>();
		}
		
		if (isReactiveRule(string)) {
			String[] conditionsAndGoal = string.split("->");
			
			if (conditionsAndGoal[0].charAt(0) == '(') {
				conditionsAndGoal[0] = conditionsAndGoal[0].substring(1, conditionsAndGoal[0].length() - 1);
			}
			
			Clause conditions = stringToClause(conditionsAndGoal[0], variables);
			SimpleSentence goal = stringToSimpleSentence(conditionsAndGoal[1], variables);
			
			return new ReactiveRule(conditions, goal);
		} else {
			throw new RemoteException("It is not a reactive rule.");
		}
	}
	
	/**
	 * Checks whether the input string matches a rule. A rule is a binary
	 * backward imply. Its first operand is a simple sentence. Its second
	 * operand is a clause.
	 * <p>
	 * This method is used to throw exceptions in
	 * {@link #stringToRule(String, HashMap) stringToRule()}.
	 * 
	 * @param string
	 *            to be checked whether or not it is a rule.
	 * @return true if the string is a rule, otherwise false.
	 */
	public static boolean isRule(String string) {
		// Delete all the spaces before checking any matching
		string = string.replaceAll(" ", "");
		
		int imply = string.indexOf(":-");
		if (imply == -1) {
			
			return isSimpleSentence(string);
		}
		
		String body = string.substring(imply + 2);
		boolean isBody = isNegation(body) || isSimpleSentence(body) || isAnd(body);
		
		return isSimpleSentence(string.substring(0, imply)) && isBody;
	}
	
	/**
	 * Converts the input string into a {@code Rule} object. The input string
	 * should be a rule. It uses the methods
	 * {@link #stringToClause(String, HashMap) stringToClause()} and
	 * {@link #stringToSimpleSentence(String, HashMap) stringToSimpleSentence()}
	 * recursively to create the rule.
	 * <p>
	 * The variable {@code variables} is used to pass the variables created to
	 * the whole clause to avoid to create two different variables for the same
	 * one. Initialize it with {@code null} or a brand new {@code HashMap}.
	 * 
	 * @param string
	 *            to be converted into the {@code Rule} object.
	 * @param variables
	 *            the variables already created to be reused if necessary and it
	 *            will be updated with the new created variables.
	 * @return the {@code Rule} object representing the reactive rule string
	 *         input.
	 * @throws RemoteException
	 *             if the input does not correspond to a reactive rule according
	 *             to the method {@code isRule()}
	 * @see Rule
	 * @see #isRule(String)
	 */
	public static Rule stringToRule(String string, HashMap<String, Variable> variables) throws RemoteException {
		// Delete any spaces before converting
		string = string.replaceAll(" ", "");
		
		if (variables == null) {
			variables = new HashMap<String, Variable>();
		}
		
		if (isRule(string)) {
			String[] headAndBody = string.split(":-");
			
			if (headAndBody.length == 1) {
				
				return new Rule(stringToSimpleSentence(string, null));
			}
			
			SimpleSentence head = stringToSimpleSentence(headAndBody[0], variables);
			Clause body = stringToClause(headAndBody[1], variables);
			
			return new Rule(head, body);
		} else {
			throw new RemoteException("It is not a rule.");
		}
	}
}