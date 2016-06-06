// $ANTLR 3.5.2 JLPSSyntax.g 2016-05-10 16:23:26

package com.bob.lps.controller.syntax;

import java.util.HashMap;
import java.util.HashSet;

import com.bob.lps.model.Action;
import com.bob.lps.model.And;
import com.bob.lps.model.Arithmetic;
import com.bob.lps.model.Clause;
import com.bob.lps.model.Constant;
import com.bob.lps.model.CycleHandler;
import com.bob.lps.model.Database;
import com.bob.lps.model.DSet;
import com.bob.lps.model.Equal;
import com.bob.lps.model.FactSet;
import com.bob.lps.model.GoalSet;
import com.bob.lps.model.GoalsList;
import com.bob.lps.model.Initiator;
import com.bob.lps.model.Not;
import com.bob.lps.model.ReactiveRule;
import com.bob.lps.model.ReactiveRuleSet;
import com.bob.lps.model.Rule;
import com.bob.lps.model.RuleSet;
import com.bob.lps.model.SimpleSentence;
import com.bob.lps.model.Terminator;
import com.bob.lps.model.Unifiable;
import com.bob.lps.model.Variable;


import org.antlr.runtime.*;

import java.util.ArrayList;

/**
* @author Alexandre Camus
*/
@SuppressWarnings("all")
public class JLPSSyntaxParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMENT", "COMMENT_LINE", "CONSTANT", 
		"SYMBOL", "VARIABLE", "WS", "'!'", "'&'", "'('", "')'", "','", "'->'", 
		"'.'", "':'", "':-'", "'='", "'=='", "'Conditions'", "'Conflicts'", "'DSet'", 
		"'Database'", "'Domain Theory'", "'DomainTheory'", "'Dset'", "'Events'", 
		"'Facts'", "'Goals'", "'Lext'", "'Lint'", "'Macroactions'", "'Postconditions'", 
		"'Preconditions'", "'ReactiveRules'", "'Rules'", "'['", "']'", "'conditions'", 
		"'conflicts'", "'dSet'", "'database'", "'domain theory'", "'domainTheory'", 
		"'dset'", "'events'", "'facts'", "'goals'", "'initiates'", "'lext'", "'lint'", 
		"'macroactions'", "'postconditions'", "'preconditions'", "'reactiveRules'", 
		"'reactiverules'", "'rules'", "'terminates'", "'true'", "'{'", "'}'"
	};
	public static final int EOF=-1;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int T__14=14;
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int T__37=37;
	public static final int T__38=38;
	public static final int T__39=39;
	public static final int T__40=40;
	public static final int T__41=41;
	public static final int T__42=42;
	public static final int T__43=43;
	public static final int T__44=44;
	public static final int T__45=45;
	public static final int T__46=46;
	public static final int T__47=47;
	public static final int T__48=48;
	public static final int T__49=49;
	public static final int T__50=50;
	public static final int T__51=51;
	public static final int T__52=52;
	public static final int T__53=53;
	public static final int T__54=54;
	public static final int T__55=55;
	public static final int T__56=56;
	public static final int T__57=57;
	public static final int T__58=58;
	public static final int T__59=59;
	public static final int T__60=60;
	public static final int T__61=61;
	public static final int T__62=62;
	public static final int COMMENT=4;
	public static final int COMMENT_LINE=5;
	public static final int CONSTANT=6;
	public static final int SYMBOL=7;
	public static final int VARIABLE=8;
	public static final int WS=9;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public JLPSSyntaxParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public JLPSSyntaxParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return JLPSSyntaxParser.tokenNames; }
	@Override public String getGrammarFileName() { return "JLPSSyntax.g"; }


	HashMap<String, Variable> variables = new HashMap<String, Variable>();



	// $ANTLR start "unifiable"
	// JLPSSyntax.g:54:1: unifiable returns [Unifiable unifiable] : ( constant | variable | simpleSentence );
	public final Unifiable unifiable() throws RecognitionException {
		Unifiable unifiable = null;


		Constant constant1 =null;
		Variable variable2 =null;
		SimpleSentence simpleSentence3 =null;

		try {
			// JLPSSyntax.g:55:3: ( constant | variable | simpleSentence )
			int alt1=3;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==CONSTANT) ) {
				int LA1_1 = input.LA(2);
				if ( (LA1_1==SYMBOL||LA1_1==11||(LA1_1 >= 13 && LA1_1 <= 16)||LA1_1==62) ) {
					alt1=1;
				}
				else if ( (LA1_1==12) ) {
					alt1=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA1_0==VARIABLE) ) {
				alt1=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}

			switch (alt1) {
				case 1 :
					// JLPSSyntax.g:55:7: constant
					{
					pushFollow(FOLLOW_constant_in_unifiable57);
					constant1=constant();
					state._fsp--;

					unifiable = constant1;
					}
					break;
				case 2 :
					// JLPSSyntax.g:56:7: variable
					{
					pushFollow(FOLLOW_variable_in_unifiable67);
					variable2=variable();
					state._fsp--;

					unifiable = variable2;
					}
					break;
				case 3 :
					// JLPSSyntax.g:57:7: simpleSentence
					{
					pushFollow(FOLLOW_simpleSentence_in_unifiable77);
					simpleSentence3=simpleSentence();
					state._fsp--;

					unifiable = simpleSentence3;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return unifiable;
	}
	// $ANTLR end "unifiable"



	// $ANTLR start "constant"
	// JLPSSyntax.g:60:1: constant returns [Constant constant] : CONSTANT ;
	public final Constant constant() throws RecognitionException {
		Constant constant = null;


		Token CONSTANT4=null;

		try {
			// JLPSSyntax.g:61:3: ( CONSTANT )
			// JLPSSyntax.g:61:7: CONSTANT
			{
			CONSTANT4=(Token)match(input,CONSTANT,FOLLOW_CONSTANT_in_constant98); 
			constant = new Constant((CONSTANT4!=null?CONSTANT4.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return constant;
	}
	// $ANTLR end "constant"



	// $ANTLR start "variable"
	// JLPSSyntax.g:64:1: variable returns [Variable variable] : VARIABLE ;
	public final Variable variable() throws RecognitionException {
		Variable variable = null;


		Token VARIABLE5=null;

		try {
			// JLPSSyntax.g:65:3: ( VARIABLE )
			// JLPSSyntax.g:65:7: VARIABLE
			{
			VARIABLE5=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_variable119); 
			if (!this.variables.containsKey((VARIABLE5!=null?VARIABLE5.getText():null))) {
			          variable = new Variable((VARIABLE5!=null?VARIABLE5.getText():null));
			          this.variables.put((VARIABLE5!=null?VARIABLE5.getText():null), variable);
			      } else {
			          variable = this.variables.get((VARIABLE5!=null?VARIABLE5.getText():null));
			      }
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return variable;
	}
	// $ANTLR end "variable"



	// $ANTLR start "parameters"
	// JLPSSyntax.g:74:1: parameters returns [ArrayList<Unifiable> parameters] :par1= unifiable ( ',' par2= unifiable )* ;
	public final ArrayList<Unifiable> parameters() throws RecognitionException {
		ArrayList<Unifiable> parameters = null;


		Unifiable par1 =null;
		Unifiable par2 =null;

		try {
			// JLPSSyntax.g:75:3: (par1= unifiable ( ',' par2= unifiable )* )
			// JLPSSyntax.g:75:7: par1= unifiable ( ',' par2= unifiable )*
			{
			parameters = new ArrayList<Unifiable>();
			pushFollow(FOLLOW_unifiable_in_parameters158);
			par1=unifiable();
			state._fsp--;

			parameters.add(par1);
			// JLPSSyntax.g:77:7: ( ',' par2= unifiable )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==14) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// JLPSSyntax.g:77:8: ',' par2= unifiable
					{
					match(input,14,FOLLOW_14_in_parameters169); 
					pushFollow(FOLLOW_unifiable_in_parameters175);
					par2=unifiable();
					state._fsp--;

					parameters.add(par2);
					}
					break;

				default :
					break loop2;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return parameters;
	}
	// $ANTLR end "parameters"



	// $ANTLR start "simpleSentence"
	// JLPSSyntax.g:80:1: simpleSentence returns [SimpleSentence simpleSentence] : constant '(' ( parameters )? ')' ;
	public final SimpleSentence simpleSentence() throws RecognitionException {
		SimpleSentence simpleSentence = null;


		Constant constant6 =null;
		ArrayList<Unifiable> parameters7 =null;

		try {
			// JLPSSyntax.g:81:3: ( constant '(' ( parameters )? ')' )
			// JLPSSyntax.g:81:7: constant '(' ( parameters )? ')'
			{
			Constant name; Unifiable[] parameters = new Unifiable[0];
			pushFollow(FOLLOW_constant_in_simpleSentence206);
			constant6=constant();
			state._fsp--;

			name = constant6;
			match(input,12,FOLLOW_12_in_simpleSentence216); 
			// JLPSSyntax.g:84:9: ( parameters )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==CONSTANT||LA3_0==VARIABLE) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// JLPSSyntax.g:84:10: parameters
					{
					pushFollow(FOLLOW_parameters_in_simpleSentence227);
					parameters7=parameters();
					state._fsp--;

					parameters = parameters7.toArray(parameters);
					}
					break;

			}

			match(input,13,FOLLOW_13_in_simpleSentence239); 
			simpleSentence = new SimpleSentence(name, parameters);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return simpleSentence;
	}
	// $ANTLR end "simpleSentence"


	public static class initiator_return extends ParserRuleReturnScope {
		public Initiator initiator;
		public String factName;
	};


	// $ANTLR start "initiator"
	// JLPSSyntax.g:94:1: initiator returns [Initiator initiator, String factName] : 'initiates' '(' event= simpleSentence ',' fact= simpleSentence ')' ( ':-' and )? ;
	public final JLPSSyntaxParser.initiator_return initiator() throws RecognitionException {
		JLPSSyntaxParser.initiator_return retval = new JLPSSyntaxParser.initiator_return();
		retval.start = input.LT(1);

		SimpleSentence event =null;
		SimpleSentence fact =null;
		Clause and8 =null;

		try {
			// JLPSSyntax.g:95:3: ( 'initiates' '(' event= simpleSentence ',' fact= simpleSentence ')' ( ':-' and )? )
			// JLPSSyntax.g:95:7: 'initiates' '(' event= simpleSentence ',' fact= simpleSentence ')' ( ':-' and )?
			{
			match(input,50,FOLLOW_50_in_initiator268); 
			match(input,12,FOLLOW_12_in_initiator276); 
			pushFollow(FOLLOW_simpleSentence_in_initiator290);
			event=simpleSentence();
			state._fsp--;

			match(input,14,FOLLOW_14_in_initiator292); 
			pushFollow(FOLLOW_simpleSentence_in_initiator298);
			fact=simpleSentence();
			state._fsp--;

			match(input,13,FOLLOW_13_in_initiator306); 
			Clause body = null;
			// JLPSSyntax.g:100:7: ( ':-' and )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==18) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// JLPSSyntax.g:100:8: ':-' and
					{
					match(input,18,FOLLOW_18_in_initiator323); 
					pushFollow(FOLLOW_and_in_initiator325);
					and8=and();
					state._fsp--;

					body = and8;
					}
					break;

			}

			retval.initiator = new Initiator(event, fact, body);
			retval.factName = fact.getName();
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "initiator"


	public static class terminator_return extends ParserRuleReturnScope {
		public Terminator terminator;
		public String factName;
	};


	// $ANTLR start "terminator"
	// JLPSSyntax.g:105:1: terminator returns [Terminator terminator, String factName] : 'terminates' '(' event= simpleSentence ',' fact= simpleSentence ')' ( ':-' and )? ;
	public final JLPSSyntaxParser.terminator_return terminator() throws RecognitionException {
		JLPSSyntaxParser.terminator_return retval = new JLPSSyntaxParser.terminator_return();
		retval.start = input.LT(1);

		SimpleSentence event =null;
		SimpleSentence fact =null;
		Clause and9 =null;

		try {
			// JLPSSyntax.g:106:3: ( 'terminates' '(' event= simpleSentence ',' fact= simpleSentence ')' ( ':-' and )? )
			// JLPSSyntax.g:106:7: 'terminates' '(' event= simpleSentence ',' fact= simpleSentence ')' ( ':-' and )?
			{
			match(input,59,FOLLOW_59_in_terminator364); 
			match(input,12,FOLLOW_12_in_terminator372); 
			pushFollow(FOLLOW_simpleSentence_in_terminator386);
			event=simpleSentence();
			state._fsp--;

			match(input,14,FOLLOW_14_in_terminator388); 
			pushFollow(FOLLOW_simpleSentence_in_terminator394);
			fact=simpleSentence();
			state._fsp--;

			Clause body = null;
			match(input,13,FOLLOW_13_in_terminator412); 
			// JLPSSyntax.g:111:7: ( ':-' and )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==18) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// JLPSSyntax.g:111:8: ':-' and
					{
					match(input,18,FOLLOW_18_in_terminator421); 
					pushFollow(FOLLOW_and_in_terminator423);
					and9=and();
					state._fsp--;

					body = and9;
					}
					break;

			}

			retval.terminator = new Terminator(event, fact, body);
			retval.factName = fact.getName();
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "terminator"



	// $ANTLR start "arithmetic"
	// JLPSSyntax.g:121:1: arithmetic returns [Arithmetic expr] : '{' op1= unifiable SYMBOL op2= unifiable '}' ;
	public final Arithmetic arithmetic() throws RecognitionException {
		Arithmetic expr = null;


		Token SYMBOL10=null;
		Unifiable op1 =null;
		Unifiable op2 =null;

		try {
			// JLPSSyntax.g:122:3: ( '{' op1= unifiable SYMBOL op2= unifiable '}' )
			// JLPSSyntax.g:122:7: '{' op1= unifiable SYMBOL op2= unifiable '}'
			{
			match(input,61,FOLLOW_61_in_arithmetic464); 
			pushFollow(FOLLOW_unifiable_in_arithmetic470);
			op1=unifiable();
			state._fsp--;

			SYMBOL10=(Token)match(input,SYMBOL,FOLLOW_SYMBOL_in_arithmetic472); 
			pushFollow(FOLLOW_unifiable_in_arithmetic478);
			op2=unifiable();
			state._fsp--;

			match(input,62,FOLLOW_62_in_arithmetic480); 
			expr = new Arithmetic(op1, (SYMBOL10!=null?SYMBOL10.getText():null), op2);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return expr;
	}
	// $ANTLR end "arithmetic"



	// $ANTLR start "equal"
	// JLPSSyntax.g:126:1: equal returns [Equal equal] : op1= variable '==' op2= unifiable ;
	public final Equal equal() throws RecognitionException {
		Equal equal = null;


		Variable op1 =null;
		Unifiable op2 =null;

		try {
			// JLPSSyntax.g:127:3: (op1= variable '==' op2= unifiable )
			// JLPSSyntax.g:127:7: op1= variable '==' op2= unifiable
			{
			pushFollow(FOLLOW_variable_in_equal511);
			op1=variable();
			state._fsp--;

			match(input,20,FOLLOW_20_in_equal513); 
			pushFollow(FOLLOW_unifiable_in_equal519);
			op2=unifiable();
			state._fsp--;

			equal = new Equal(op1, op2);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return equal;
	}
	// $ANTLR end "equal"



	// $ANTLR start "term"
	// JLPSSyntax.g:131:1: term returns [Clause clause] : ( arithmetic | simpleSentence | equal | '(' and ')' );
	public final Clause term() throws RecognitionException {
		Clause clause = null;


		Arithmetic arithmetic11 =null;
		SimpleSentence simpleSentence12 =null;
		Equal equal13 =null;
		Clause and14 =null;

		try {
			// JLPSSyntax.g:132:3: ( arithmetic | simpleSentence | equal | '(' and ')' )
			int alt6=4;
			switch ( input.LA(1) ) {
			case 61:
				{
				alt6=1;
				}
				break;
			case CONSTANT:
				{
				alt6=2;
				}
				break;
			case VARIABLE:
				{
				alt6=3;
				}
				break;
			case 12:
				{
				alt6=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}
			switch (alt6) {
				case 1 :
					// JLPSSyntax.g:132:7: arithmetic
					{
					pushFollow(FOLLOW_arithmetic_in_term546);
					arithmetic11=arithmetic();
					state._fsp--;

					clause = arithmetic11;
					}
					break;
				case 2 :
					// JLPSSyntax.g:133:7: simpleSentence
					{
					pushFollow(FOLLOW_simpleSentence_in_term556);
					simpleSentence12=simpleSentence();
					state._fsp--;

					clause = simpleSentence12;
					}
					break;
				case 3 :
					// JLPSSyntax.g:134:7: equal
					{
					pushFollow(FOLLOW_equal_in_term566);
					equal13=equal();
					state._fsp--;

					clause = equal13;
					}
					break;
				case 4 :
					// JLPSSyntax.g:135:7: '(' and ')'
					{
					match(input,12,FOLLOW_12_in_term576); 
					pushFollow(FOLLOW_and_in_term578);
					and14=and();
					state._fsp--;

					match(input,13,FOLLOW_13_in_term580); 
					clause = and14;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return clause;
	}
	// $ANTLR end "term"



	// $ANTLR start "negation"
	// JLPSSyntax.g:138:1: negation returns [Clause clause] : ( '!' )? term ;
	public final Clause negation() throws RecognitionException {
		Clause clause = null;


		Clause term15 =null;

		try {
			// JLPSSyntax.g:139:3: ( ( '!' )? term )
			// JLPSSyntax.g:139:7: ( '!' )? term
			{
			boolean flag = false;
			// JLPSSyntax.g:140:7: ( '!' )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==10) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// JLPSSyntax.g:140:8: '!'
					{
					match(input,10,FOLLOW_10_in_negation610); 
					flag = true;
					}
					break;

			}

			pushFollow(FOLLOW_term_in_negation616);
			term15=term();
			state._fsp--;

			clause = (flag) ? new Not(term15) : term15;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return clause;
	}
	// $ANTLR end "negation"



	// $ANTLR start "and"
	// JLPSSyntax.g:144:1: and returns [Clause clause] :op1= negation ( '&' op2= negation )* ;
	public final Clause and() throws RecognitionException {
		Clause clause = null;


		Clause op1 =null;
		Clause op2 =null;

		try {
			// JLPSSyntax.g:145:3: (op1= negation ( '&' op2= negation )* )
			// JLPSSyntax.g:145:7: op1= negation ( '&' op2= negation )*
			{
			boolean flag = false;
			      ArrayList<Clause> operands = new ArrayList<Clause>();
			pushFollow(FOLLOW_negation_in_and655);
			op1=negation();
			state._fsp--;

			operands.add(op1);
			// JLPSSyntax.g:147:51: ( '&' op2= negation )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==11) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// JLPSSyntax.g:147:52: '&' op2= negation
					{
					match(input,11,FOLLOW_11_in_and660); 
					pushFollow(FOLLOW_negation_in_and666);
					op2=negation();
					state._fsp--;

					flag = true; operands.add(op2);
					}
					break;

				default :
					break loop8;
				}
			}

			clause = (flag) ? new And(operands) : op1;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return clause;
	}
	// $ANTLR end "and"



	// $ANTLR start "truth"
	// JLPSSyntax.g:151:1: truth returns [Clause clause = null] : 'true' ;
	public final Clause truth() throws RecognitionException {
		Clause clause =  null;


		try {
			// JLPSSyntax.g:152:3: ( 'true' )
			// JLPSSyntax.g:152:7: 'true'
			{
			match(input,60,FOLLOW_60_in_truth697); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return clause;
	}
	// $ANTLR end "truth"



	// $ANTLR start "reactiveRule"
	// JLPSSyntax.g:160:1: reactiveRule returns [ReactiveRule rule] : (conditions= and |conditions= truth ) '->' simpleSentence '.' ;
	public final ReactiveRule reactiveRule() throws RecognitionException {
		ReactiveRule rule = null;


		Clause conditions =null;
		SimpleSentence simpleSentence16 =null;

		try {
			// JLPSSyntax.g:161:3: ( (conditions= and |conditions= truth ) '->' simpleSentence '.' )
			// JLPSSyntax.g:161:7: (conditions= and |conditions= truth ) '->' simpleSentence '.'
			{
			// JLPSSyntax.g:161:7: (conditions= and |conditions= truth )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==CONSTANT||LA9_0==VARIABLE||LA9_0==10||LA9_0==12||LA9_0==61) ) {
				alt9=1;
			}
			else if ( (LA9_0==60) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// JLPSSyntax.g:161:8: conditions= and
					{
					pushFollow(FOLLOW_and_in_reactiveRule723);
					conditions=and();
					state._fsp--;

					}
					break;
				case 2 :
					// JLPSSyntax.g:161:27: conditions= truth
					{
					pushFollow(FOLLOW_truth_in_reactiveRule731);
					conditions=truth();
					state._fsp--;

					}
					break;

			}

			match(input,15,FOLLOW_15_in_reactiveRule734); 
			pushFollow(FOLLOW_simpleSentence_in_reactiveRule736);
			simpleSentence16=simpleSentence();
			state._fsp--;

			match(input,16,FOLLOW_16_in_reactiveRule738); 
			rule = new ReactiveRule(conditions, simpleSentence16);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return rule;
	}
	// $ANTLR end "reactiveRule"



	// $ANTLR start "rule"
	// JLPSSyntax.g:165:1: rule returns [Rule rule] : simpleSentence ( ':-' and )? '.' ;
	public final Rule rule() throws RecognitionException {
		Rule rule = null;


		Clause and17 =null;
		SimpleSentence simpleSentence18 =null;

		try {
			// JLPSSyntax.g:166:3: ( simpleSentence ( ':-' and )? '.' )
			// JLPSSyntax.g:166:7: simpleSentence ( ':-' and )? '.'
			{
			Clause body = null;
			pushFollow(FOLLOW_simpleSentence_in_rule773);
			simpleSentence18=simpleSentence();
			state._fsp--;

			// JLPSSyntax.g:167:22: ( ':-' and )?
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==18) ) {
				alt10=1;
			}
			switch (alt10) {
				case 1 :
					// JLPSSyntax.g:167:23: ':-' and
					{
					match(input,18,FOLLOW_18_in_rule776); 
					pushFollow(FOLLOW_and_in_rule778);
					and17=and();
					state._fsp--;

					body = and17;
					}
					break;

			}

			match(input,16,FOLLOW_16_in_rule784); 
			rule = new Rule(simpleSentence18, body);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return rule;
	}
	// $ANTLR end "rule"



	// $ANTLR start "fluent"
	// JLPSSyntax.g:171:1: fluent returns [SimpleSentence rule] : simpleSentence '.' ;
	public final SimpleSentence fluent() throws RecognitionException {
		SimpleSentence rule = null;


		SimpleSentence simpleSentence19 =null;

		try {
			// JLPSSyntax.g:172:3: ( simpleSentence '.' )
			// JLPSSyntax.g:172:7: simpleSentence '.'
			{
			pushFollow(FOLLOW_simpleSentence_in_fluent811);
			simpleSentence19=simpleSentence();
			state._fsp--;

			match(input,16,FOLLOW_16_in_fluent813); 
			rule = simpleSentence19;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return rule;
	}
	// $ANTLR end "fluent"


	public static class lext_return extends ParserRuleReturnScope {
		public boolean w;
		public FactSet set;
		public HashSet<String> facts;
	};


	// $ANTLR start "lext"
	// JLPSSyntax.g:180:1: lext returns [boolean w, FactSet set, HashSet<String> facts] : ( 'Lext' | 'lext' | 'facts' | 'Facts' ) '{' ( fluent )* '}' ;
	public final JLPSSyntaxParser.lext_return lext() throws RecognitionException {
		JLPSSyntaxParser.lext_return retval = new JLPSSyntaxParser.lext_return();
		retval.start = input.LT(1);

		SimpleSentence fluent20 =null;

		try {
			// JLPSSyntax.g:181:3: ( ( 'Lext' | 'lext' | 'facts' | 'Facts' ) '{' ( fluent )* '}' )
			// JLPSSyntax.g:181:7: ( 'Lext' | 'lext' | 'facts' | 'Facts' ) '{' ( fluent )* '}'
			{
			retval.set = new FactSet(); retval.w = true; retval.facts = new HashSet<String>();
			if ( input.LA(1)==29||input.LA(1)==31||input.LA(1)==48||input.LA(1)==51 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,61,FOLLOW_61_in_lext860); 
			// JLPSSyntax.g:183:7: ( fluent )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( (LA11_0==CONSTANT) ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// JLPSSyntax.g:183:8: fluent
					{
					pushFollow(FOLLOW_fluent_in_lext869);
					fluent20=fluent();
					state._fsp--;

					retval.set.addFact(fluent20); retval.w = false; retval.facts.add(fluent20.getName());
					}
					break;

				default :
					break loop11;
				}
			}

			this.variables = new HashMap<String, Variable>();
			Database.getInstance().setFactsDatabase(retval.set);
			match(input,62,FOLLOW_62_in_lext897); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "lext"


	public static class lint_return extends ParserRuleReturnScope {
		public boolean w;
		public RuleSet set;
	};


	// $ANTLR start "lint"
	// JLPSSyntax.g:189:1: lint returns [boolean w, RuleSet set] : ( 'Lint' | 'lint' | 'rules' | 'Rules' ) '{' ( rule )* '}' ;
	public final JLPSSyntaxParser.lint_return lint() throws RecognitionException {
		JLPSSyntaxParser.lint_return retval = new JLPSSyntaxParser.lint_return();
		retval.start = input.LT(1);

		Rule rule21 =null;

		try {
			// JLPSSyntax.g:190:3: ( ( 'Lint' | 'lint' | 'rules' | 'Rules' ) '{' ( rule )* '}' )
			// JLPSSyntax.g:190:7: ( 'Lint' | 'lint' | 'rules' | 'Rules' ) '{' ( rule )* '}'
			{
			retval.set = new RuleSet(); retval.w = true;
			if ( input.LA(1)==32||input.LA(1)==37||input.LA(1)==52||input.LA(1)==58 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,61,FOLLOW_61_in_lint940); 
			// JLPSSyntax.g:192:7: ( rule )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==CONSTANT) ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// JLPSSyntax.g:192:8: rule
					{
					pushFollow(FOLLOW_rule_in_lint949);
					rule21=rule();
					state._fsp--;

					retval.set.addRule(rule21); retval.w = false;
					}
					break;

				default :
					break loop12;
				}
			}

			this.variables = new HashMap<String, Variable>();
			match(input,62,FOLLOW_62_in_lint969); 
			Database.getInstance().setRulesDatabase(retval.set);
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "lint"


	public static class preconditions_return extends ParserRuleReturnScope {
		public Clause conditions;
		public Clause conflicts;
	};


	// $ANTLR start "preconditions"
	// JLPSSyntax.g:198:1: preconditions returns [Clause conditions, Clause conflicts] : ( 'Preconditions' | 'preconditions' ) '[' ( ( 'conditions' | 'Conditions' ) ':' (cond= and |cond= truth ) '.' )? ( ( 'conflicts' | 'Conflicts' ) ':' (conf= and |conf= truth ) '.' )? ']' ;
	public final JLPSSyntaxParser.preconditions_return preconditions() throws RecognitionException {
		JLPSSyntaxParser.preconditions_return retval = new JLPSSyntaxParser.preconditions_return();
		retval.start = input.LT(1);

		Clause cond =null;
		Clause conf =null;

		try {
			// JLPSSyntax.g:199:3: ( ( 'Preconditions' | 'preconditions' ) '[' ( ( 'conditions' | 'Conditions' ) ':' (cond= and |cond= truth ) '.' )? ( ( 'conflicts' | 'Conflicts' ) ':' (conf= and |conf= truth ) '.' )? ']' )
			// JLPSSyntax.g:199:7: ( 'Preconditions' | 'preconditions' ) '[' ( ( 'conditions' | 'Conditions' ) ':' (cond= and |cond= truth ) '.' )? ( ( 'conflicts' | 'Conflicts' ) ':' (conf= and |conf= truth ) '.' )? ']'
			{
			if ( input.LA(1)==35||input.LA(1)==55 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,38,FOLLOW_38_in_preconditions1004); 
			// JLPSSyntax.g:200:7: ( ( 'conditions' | 'Conditions' ) ':' (cond= and |cond= truth ) '.' )?
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==21||LA14_0==40) ) {
				alt14=1;
			}
			switch (alt14) {
				case 1 :
					// JLPSSyntax.g:200:8: ( 'conditions' | 'Conditions' ) ':' (cond= and |cond= truth ) '.'
					{
					if ( input.LA(1)==21||input.LA(1)==40 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					match(input,17,FOLLOW_17_in_preconditions1021); 
					// JLPSSyntax.g:200:42: (cond= and |cond= truth )
					int alt13=2;
					int LA13_0 = input.LA(1);
					if ( (LA13_0==CONSTANT||LA13_0==VARIABLE||LA13_0==10||LA13_0==12||LA13_0==61) ) {
						alt13=1;
					}
					else if ( (LA13_0==60) ) {
						alt13=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 13, 0, input);
						throw nvae;
					}

					switch (alt13) {
						case 1 :
							// JLPSSyntax.g:200:43: cond= and
							{
							pushFollow(FOLLOW_and_in_preconditions1028);
							cond=and();
							state._fsp--;

							}
							break;
						case 2 :
							// JLPSSyntax.g:200:56: cond= truth
							{
							pushFollow(FOLLOW_truth_in_preconditions1036);
							cond=truth();
							state._fsp--;

							}
							break;

					}

					match(input,16,FOLLOW_16_in_preconditions1039); 
					retval.conditions = cond;
					}
					break;

			}

			// JLPSSyntax.g:201:7: ( ( 'conflicts' | 'Conflicts' ) ':' (conf= and |conf= truth ) '.' )?
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==22||LA16_0==41) ) {
				alt16=1;
			}
			switch (alt16) {
				case 1 :
					// JLPSSyntax.g:201:8: ( 'conflicts' | 'Conflicts' ) ':' (conf= and |conf= truth ) '.'
					{
					if ( input.LA(1)==22||input.LA(1)==41 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					match(input,17,FOLLOW_17_in_preconditions1060); 
					// JLPSSyntax.g:201:40: (conf= and |conf= truth )
					int alt15=2;
					int LA15_0 = input.LA(1);
					if ( (LA15_0==CONSTANT||LA15_0==VARIABLE||LA15_0==10||LA15_0==12||LA15_0==61) ) {
						alt15=1;
					}
					else if ( (LA15_0==60) ) {
						alt15=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 15, 0, input);
						throw nvae;
					}

					switch (alt15) {
						case 1 :
							// JLPSSyntax.g:201:41: conf= and
							{
							pushFollow(FOLLOW_and_in_preconditions1067);
							conf=and();
							state._fsp--;

							}
							break;
						case 2 :
							// JLPSSyntax.g:201:54: conf= truth
							{
							pushFollow(FOLLOW_truth_in_preconditions1075);
							conf=truth();
							state._fsp--;

							}
							break;

					}

					match(input,16,FOLLOW_16_in_preconditions1078); 
					retval.conflicts = conf;
					}
					break;

			}

			match(input,39,FOLLOW_39_in_preconditions1090); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "preconditions"


	public static class postconditions_return extends ParserRuleReturnScope {
		public ArrayList<Terminator> terminators;
		public ArrayList<Initiator> initiators;
		public HashSet<String> facts;
	};


	// $ANTLR start "postconditions"
	// JLPSSyntax.g:205:1: postconditions returns [ArrayList<Terminator> terminators, ArrayList<Initiator> initiators, HashSet<String> facts] : ( 'Postconditions' | 'postconditions' ) '[' ( terminator '.' | initiator '.' )* ']' ;
	public final JLPSSyntaxParser.postconditions_return postconditions() throws RecognitionException {
		JLPSSyntaxParser.postconditions_return retval = new JLPSSyntaxParser.postconditions_return();
		retval.start = input.LT(1);

		ParserRuleReturnScope terminator22 =null;
		ParserRuleReturnScope initiator23 =null;

		try {
			// JLPSSyntax.g:206:3: ( ( 'Postconditions' | 'postconditions' ) '[' ( terminator '.' | initiator '.' )* ']' )
			// JLPSSyntax.g:206:7: ( 'Postconditions' | 'postconditions' ) '[' ( terminator '.' | initiator '.' )* ']'
			{
			retval.terminators = new ArrayList<Terminator>(); retval.initiators = new ArrayList<Initiator>(); retval.facts = new HashSet<String>();
			if ( input.LA(1)==34||input.LA(1)==54 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,38,FOLLOW_38_in_postconditions1125); 
			// JLPSSyntax.g:208:7: ( terminator '.' | initiator '.' )*
			loop17:
			while (true) {
				int alt17=3;
				int LA17_0 = input.LA(1);
				if ( (LA17_0==59) ) {
					alt17=1;
				}
				else if ( (LA17_0==50) ) {
					alt17=2;
				}

				switch (alt17) {
				case 1 :
					// JLPSSyntax.g:208:8: terminator '.'
					{
					pushFollow(FOLLOW_terminator_in_postconditions1134);
					terminator22=terminator();
					state._fsp--;

					match(input,16,FOLLOW_16_in_postconditions1136); 
					retval.terminators.add((terminator22!=null?((JLPSSyntaxParser.terminator_return)terminator22).terminator:null)); retval.facts.add((terminator22!=null?((JLPSSyntaxParser.terminator_return)terminator22).factName:null));
					}
					break;
				case 2 :
					// JLPSSyntax.g:209:9: initiator '.'
					{
					pushFollow(FOLLOW_initiator_in_postconditions1148);
					initiator23=initiator();
					state._fsp--;

					match(input,16,FOLLOW_16_in_postconditions1150); 
					retval.initiators.add((initiator23!=null?((JLPSSyntaxParser.initiator_return)initiator23).initiator:null)); retval.facts.add((initiator23!=null?((JLPSSyntaxParser.initiator_return)initiator23).factName:null));
					}
					break;

				default :
					break loop17;
				}
			}

			match(input,39,FOLLOW_39_in_postconditions1161); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "postconditions"


	public static class action_return extends ParserRuleReturnScope {
		public Action action;
		public HashSet<String> facts;
		public String actionName;
	};


	// $ANTLR start "action"
	// JLPSSyntax.g:213:1: action returns [Action action, HashSet<String> facts, String actionName] : simpleSentence '=' '{' ( preconditions )? ( postconditions )? '}' ;
	public final JLPSSyntaxParser.action_return action() throws RecognitionException {
		JLPSSyntaxParser.action_return retval = new JLPSSyntaxParser.action_return();
		retval.start = input.LT(1);

		SimpleSentence simpleSentence24 =null;
		ParserRuleReturnScope preconditions25 =null;
		ParserRuleReturnScope postconditions26 =null;

		try {
			// JLPSSyntax.g:214:3: ( simpleSentence '=' '{' ( preconditions )? ( postconditions )? '}' )
			// JLPSSyntax.g:214:7: simpleSentence '=' '{' ( preconditions )? ( postconditions )? '}'
			{
			ArrayList<Terminator> terminators = new ArrayList<Terminator>();
			      ArrayList<Initiator> initiators = new ArrayList<Initiator>();
			      Clause conditions = null;
			      Clause conflicts = null;
			      retval.facts = new HashSet<String>();
			      
			pushFollow(FOLLOW_simpleSentence_in_action1188);
			simpleSentence24=simpleSentence();
			state._fsp--;

			match(input,19,FOLLOW_19_in_action1190); 
			match(input,61,FOLLOW_61_in_action1192); 
			retval.actionName = simpleSentence24.getName();
			// JLPSSyntax.g:221:7: ( preconditions )?
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==35||LA18_0==55) ) {
				alt18=1;
			}
			switch (alt18) {
				case 1 :
					// JLPSSyntax.g:221:8: preconditions
					{
					pushFollow(FOLLOW_preconditions_in_action1203);
					preconditions25=preconditions();
					state._fsp--;

					conditions = (preconditions25!=null?((JLPSSyntaxParser.preconditions_return)preconditions25).conditions:null); conflicts = (preconditions25!=null?((JLPSSyntaxParser.preconditions_return)preconditions25).conflicts:null);
					}
					break;

			}

			// JLPSSyntax.g:222:7: ( postconditions )?
			int alt19=2;
			int LA19_0 = input.LA(1);
			if ( (LA19_0==34||LA19_0==54) ) {
				alt19=1;
			}
			switch (alt19) {
				case 1 :
					// JLPSSyntax.g:222:8: postconditions
					{
					pushFollow(FOLLOW_postconditions_in_action1216);
					postconditions26=postconditions();
					state._fsp--;

					initiators = (postconditions26!=null?((JLPSSyntaxParser.postconditions_return)postconditions26).initiators:null); terminators = (postconditions26!=null?((JLPSSyntaxParser.postconditions_return)postconditions26).terminators:null); retval.facts = (postconditions26!=null?((JLPSSyntaxParser.postconditions_return)postconditions26).facts:null);
					}
					break;

			}

			match(input,62,FOLLOW_62_in_action1228); 
			retval.action = new Action(simpleSentence24, initiators, terminators, conditions, conflicts);
			this.variables = new HashMap<String, Variable>();
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "action"



	// $ANTLR start "macroaction"
	// JLPSSyntax.g:228:1: macroaction returns [Rule macroaction] : rule ;
	public final Rule macroaction() throws RecognitionException {
		Rule macroaction = null;


		Rule rule27 =null;

		try {
			// JLPSSyntax.g:229:3: ( rule )
			// JLPSSyntax.g:229:7: rule
			{
			pushFollow(FOLLOW_rule_in_macroaction1263);
			rule27=rule();
			state._fsp--;

			macroaction = rule27;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return macroaction;
	}
	// $ANTLR end "macroaction"


	public static class d_return extends ParserRuleReturnScope {
		public boolean w;
		public DSet set;
		public HashSet<String> facts;
		public HashSet<String> actions;
	};


	// $ANTLR start "d"
	// JLPSSyntax.g:233:1: d returns [boolean w, DSet set, HashSet<String> facts, HashSet<String> actions] : ( 'domain theory' | 'Domain Theory' | 'domainTheory' | 'DomainTheory' | 'dset' | 'Dset' | 'dSet' | 'DSet' ) '{' ( action )* ( ( 'macroactions' | 'Macroactions' ) '{' ( macroaction )* '}' )? '}' ;
	public final JLPSSyntaxParser.d_return d() throws RecognitionException {
		JLPSSyntaxParser.d_return retval = new JLPSSyntaxParser.d_return();
		retval.start = input.LT(1);

		ParserRuleReturnScope action28 =null;
		Rule macroaction29 =null;

		try {
			// JLPSSyntax.g:234:3: ( ( 'domain theory' | 'Domain Theory' | 'domainTheory' | 'DomainTheory' | 'dset' | 'Dset' | 'dSet' | 'DSet' ) '{' ( action )* ( ( 'macroactions' | 'Macroactions' ) '{' ( macroaction )* '}' )? '}' )
			// JLPSSyntax.g:234:7: ( 'domain theory' | 'Domain Theory' | 'domainTheory' | 'DomainTheory' | 'dset' | 'Dset' | 'dSet' | 'DSet' ) '{' ( action )* ( ( 'macroactions' | 'Macroactions' ) '{' ( macroaction )* '}' )? '}'
			{
			retval.set = new DSet(); retval.w = true; ArrayList<Rule> macros = new ArrayList<Rule>();
			      retval.facts = new HashSet<String>(); retval.actions = new HashSet<String>();
			if ( input.LA(1)==23||(input.LA(1) >= 25 && input.LA(1) <= 27)||input.LA(1)==42||(input.LA(1) >= 44 && input.LA(1) <= 46) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,61,FOLLOW_61_in_d1330); 
			// JLPSSyntax.g:237:7: ( action )*
			loop20:
			while (true) {
				int alt20=2;
				int LA20_0 = input.LA(1);
				if ( (LA20_0==CONSTANT) ) {
					alt20=1;
				}

				switch (alt20) {
				case 1 :
					// JLPSSyntax.g:237:8: action
					{
					pushFollow(FOLLOW_action_in_d1339);
					action28=action();
					state._fsp--;

					retval.w = false; retval.set.addAction((action28!=null?((JLPSSyntaxParser.action_return)action28).action:null)); retval.facts = (action28!=null?((JLPSSyntaxParser.action_return)action28).facts:null); retval.actions.add((action28!=null?((JLPSSyntaxParser.action_return)action28).actionName:null));
					}
					break;

				default :
					break loop20;
				}
			}

			// JLPSSyntax.g:238:7: ( ( 'macroactions' | 'Macroactions' ) '{' ( macroaction )* '}' )?
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==33||LA22_0==53) ) {
				alt22=1;
			}
			switch (alt22) {
				case 1 :
					// JLPSSyntax.g:238:8: ( 'macroactions' | 'Macroactions' ) '{' ( macroaction )* '}'
					{
					if ( input.LA(1)==33||input.LA(1)==53 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					match(input,61,FOLLOW_61_in_d1360); 
					// JLPSSyntax.g:239:7: ( macroaction )*
					loop21:
					while (true) {
						int alt21=2;
						int LA21_0 = input.LA(1);
						if ( (LA21_0==CONSTANT) ) {
							alt21=1;
						}

						switch (alt21) {
						case 1 :
							// JLPSSyntax.g:239:8: macroaction
							{
							pushFollow(FOLLOW_macroaction_in_d1369);
							macroaction29=macroaction();
							state._fsp--;

							retval.w = false; macros.add(macroaction29);
							}
							break;

						default :
							break loop21;
						}
					}

					match(input,62,FOLLOW_62_in_d1381); 
					}
					break;

			}

			match(input,62,FOLLOW_62_in_d1391); 
			Database.getInstance().setdSet(retval.set);
			Database.getInstance().addRulesDatabase(macros);
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "d"


	public static class database_return extends ParserRuleReturnScope {
		public boolean wlint;
		public boolean wlext;
		public boolean wdset;
		public HashSet<String> facts;
	};


	// $ANTLR start "database"
	// JLPSSyntax.g:246:1: database returns [boolean wlint, boolean wlext, boolean wdset, HashSet<String> facts] : ( 'database' | 'Database' ) '{' ( lext )? ( lint )? '}' ;
	public final JLPSSyntaxParser.database_return database() throws RecognitionException {
		JLPSSyntaxParser.database_return retval = new JLPSSyntaxParser.database_return();
		retval.start = input.LT(1);

		ParserRuleReturnScope lext30 =null;
		ParserRuleReturnScope lint31 =null;

		try {
			// JLPSSyntax.g:247:3: ( ( 'database' | 'Database' ) '{' ( lext )? ( lint )? '}' )
			// JLPSSyntax.g:247:7: ( 'database' | 'Database' ) '{' ( lext )? ( lint )? '}'
			{
			retval.wlint =  true; retval.wlext = true; retval.wdset = true; retval.facts = new HashSet<String>();
			if ( input.LA(1)==24||input.LA(1)==43 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,61,FOLLOW_61_in_database1442); 
			// JLPSSyntax.g:249:7: ( lext )?
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==29||LA23_0==31||LA23_0==48||LA23_0==51) ) {
				alt23=1;
			}
			switch (alt23) {
				case 1 :
					// JLPSSyntax.g:249:8: lext
					{
					pushFollow(FOLLOW_lext_in_database1451);
					lext30=lext();
					state._fsp--;

					retval.wlext = (lext30!=null?((JLPSSyntaxParser.lext_return)lext30).w:false); retval.facts = (lext30!=null?((JLPSSyntaxParser.lext_return)lext30).facts:null);
					}
					break;

			}

			// JLPSSyntax.g:250:7: ( lint )?
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==32||LA24_0==37||LA24_0==52||LA24_0==58) ) {
				alt24=1;
			}
			switch (alt24) {
				case 1 :
					// JLPSSyntax.g:250:8: lint
					{
					pushFollow(FOLLOW_lint_in_database1464);
					lint31=lint();
					state._fsp--;

					retval.wlint = (lint31!=null?((JLPSSyntaxParser.lint_return)lint31).w:false);
					}
					break;

			}

			match(input,62,FOLLOW_62_in_database1476); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "database"


	public static class reactiveRules_return extends ParserRuleReturnScope {
		public boolean w;
		public ReactiveRuleSet set;
	};


	// $ANTLR start "reactiveRules"
	// JLPSSyntax.g:254:1: reactiveRules returns [boolean w, ReactiveRuleSet set] : ( 'ReactiveRules' | 'reactiveRules' | 'reactiverules' ) '{' ( reactiveRule )* '}' ;
	public final JLPSSyntaxParser.reactiveRules_return reactiveRules() throws RecognitionException {
		JLPSSyntaxParser.reactiveRules_return retval = new JLPSSyntaxParser.reactiveRules_return();
		retval.start = input.LT(1);

		ReactiveRule reactiveRule32 =null;

		try {
			// JLPSSyntax.g:255:3: ( ( 'ReactiveRules' | 'reactiveRules' | 'reactiverules' ) '{' ( reactiveRule )* '}' )
			// JLPSSyntax.g:255:7: ( 'ReactiveRules' | 'reactiveRules' | 'reactiverules' ) '{' ( reactiveRule )* '}'
			{
			retval.set = ReactiveRuleSet.getInstance(); retval.w = true;
			if ( input.LA(1)==36||(input.LA(1) >= 56 && input.LA(1) <= 57) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,61,FOLLOW_61_in_reactiveRules1515); 
			// JLPSSyntax.g:257:7: ( reactiveRule )*
			loop25:
			while (true) {
				int alt25=2;
				int LA25_0 = input.LA(1);
				if ( (LA25_0==CONSTANT||LA25_0==VARIABLE||LA25_0==10||LA25_0==12||(LA25_0 >= 60 && LA25_0 <= 61)) ) {
					alt25=1;
				}

				switch (alt25) {
				case 1 :
					// JLPSSyntax.g:257:8: reactiveRule
					{
					pushFollow(FOLLOW_reactiveRule_in_reactiveRules1524);
					reactiveRule32=reactiveRule();
					state._fsp--;

					retval.set.addRule(reactiveRule32); retval.w = false;
					}
					break;

				default :
					break loop25;
				}
			}

			this.variables = new HashMap<String, Variable>();
			match(input,62,FOLLOW_62_in_reactiveRules1544); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "reactiveRules"


	public static class goals_return extends ParserRuleReturnScope {
		public boolean w;
		public GoalSet set;
	};


	// $ANTLR start "goals"
	// JLPSSyntax.g:262:1: goals returns [boolean w, GoalSet set] : ( 'Goals' | 'goals' ) '{' ( rule )* '}' ;
	public final JLPSSyntaxParser.goals_return goals() throws RecognitionException {
		JLPSSyntaxParser.goals_return retval = new JLPSSyntaxParser.goals_return();
		retval.start = input.LT(1);

		Rule rule33 =null;

		try {
			// JLPSSyntax.g:263:3: ( ( 'Goals' | 'goals' ) '{' ( rule )* '}' )
			// JLPSSyntax.g:263:7: ( 'Goals' | 'goals' ) '{' ( rule )* '}'
			{
			retval.set = new GoalSet(); retval.w = true;
			if ( input.LA(1)==30||input.LA(1)==49 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,61,FOLLOW_61_in_goals1579); 
			// JLPSSyntax.g:265:7: ( rule )*
			loop26:
			while (true) {
				int alt26=2;
				int LA26_0 = input.LA(1);
				if ( (LA26_0==CONSTANT) ) {
					alt26=1;
				}

				switch (alt26) {
				case 1 :
					// JLPSSyntax.g:265:8: rule
					{
					pushFollow(FOLLOW_rule_in_goals1588);
					rule33=rule();
					state._fsp--;

					retval.set.addDefinition(rule33); retval.w = false;
					}
					break;

				default :
					break loop26;
				}
			}

			this.variables = new HashMap<String, Variable>();
			match(input,62,FOLLOW_62_in_goals1608); 
			GoalsList.getInstance().setGoalsDefinitions(retval.set);
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "goals"


	public static class events_return extends ParserRuleReturnScope {
		public boolean w;
		public RuleSet set;
	};


	// $ANTLR start "events"
	// JLPSSyntax.g:271:1: events returns [boolean w, RuleSet set] : ( 'Events' | 'events' ) '{' ( fluent )* '}' ;
	public final JLPSSyntaxParser.events_return events() throws RecognitionException {
		JLPSSyntaxParser.events_return retval = new JLPSSyntaxParser.events_return();
		retval.start = input.LT(1);

		SimpleSentence fluent34 =null;

		try {
			// JLPSSyntax.g:272:3: ( ( 'Events' | 'events' ) '{' ( fluent )* '}' )
			// JLPSSyntax.g:272:7: ( 'Events' | 'events' ) '{' ( fluent )* '}'
			{
			retval.set = new RuleSet(); retval.w = true;
			if ( input.LA(1)==28||input.LA(1)==47 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,61,FOLLOW_61_in_events1651); 
			// JLPSSyntax.g:274:7: ( fluent )*
			loop27:
			while (true) {
				int alt27=2;
				int LA27_0 = input.LA(1);
				if ( (LA27_0==CONSTANT) ) {
					alt27=1;
				}

				switch (alt27) {
				case 1 :
					// JLPSSyntax.g:274:8: fluent
					{
					pushFollow(FOLLOW_fluent_in_events1660);
					fluent34=fluent();
					state._fsp--;

					retval.set.addRule(new Rule(fluent34)); retval.w = false;
					}
					break;

				default :
					break loop27;
				}
			}

			this.variables = new HashMap<String, Variable>();
			match(input,62,FOLLOW_62_in_events1680); 
			CycleHandler.getInstance().setEvents(retval.set);
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "events"


	public static class file_return extends ParserRuleReturnScope {
		public boolean[] w;
		public HashSet<String> facts;
		public HashSet<String> actions;
	};


	// $ANTLR start "file"
	// JLPSSyntax.g:280:1: file returns [boolean[] w, HashSet<String> facts, HashSet<String> actions] : ( database )? ( d )? ( reactiveRules )? ( goals )? ( events )? EOF ;
	public final JLPSSyntaxParser.file_return file() throws RecognitionException {
		JLPSSyntaxParser.file_return retval = new JLPSSyntaxParser.file_return();
		retval.start = input.LT(1);

		ParserRuleReturnScope database35 =null;
		ParserRuleReturnScope d36 =null;
		ParserRuleReturnScope reactiveRules37 =null;
		ParserRuleReturnScope goals38 =null;
		ParserRuleReturnScope events39 =null;

		try {
			// JLPSSyntax.g:281:3: ( ( database )? ( d )? ( reactiveRules )? ( goals )? ( events )? EOF )
			// JLPSSyntax.g:281:7: ( database )? ( d )? ( reactiveRules )? ( goals )? ( events )? EOF
			{
			retval.w = new boolean[6]; retval.w[0] = true; retval.w[1] = true; retval.w[2] = true; retval.w[3] = true; retval.w[4] = true; retval.w[5] = true;
			      retval.facts = new HashSet<String>(); retval.actions = new HashSet<String>();
			// JLPSSyntax.g:283:7: ( database )?
			int alt28=2;
			int LA28_0 = input.LA(1);
			if ( (LA28_0==24||LA28_0==43) ) {
				alt28=1;
			}
			switch (alt28) {
				case 1 :
					// JLPSSyntax.g:283:8: database
					{
					pushFollow(FOLLOW_database_in_file1716);
					database35=database();
					state._fsp--;

					retval.w[0] = (database35!=null?((JLPSSyntaxParser.database_return)database35).wlext:false); retval.w[1] = (database35!=null?((JLPSSyntaxParser.database_return)database35).wlint:false); retval.facts = (database35!=null?((JLPSSyntaxParser.database_return)database35).facts:null);
					}
					break;

			}

			// JLPSSyntax.g:284:7: ( d )?
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==23||(LA29_0 >= 25 && LA29_0 <= 27)||LA29_0==42||(LA29_0 >= 44 && LA29_0 <= 46)) ) {
				alt29=1;
			}
			switch (alt29) {
				case 1 :
					// JLPSSyntax.g:284:8: d
					{
					pushFollow(FOLLOW_d_in_file1729);
					d36=d();
					state._fsp--;

					retval.w[2] = (d36!=null?((JLPSSyntaxParser.d_return)d36).w:false); retval.facts.addAll((d36!=null?((JLPSSyntaxParser.d_return)d36).facts:null)); retval.actions = (d36!=null?((JLPSSyntaxParser.d_return)d36).actions:null);
					}
					break;

			}

			// JLPSSyntax.g:285:7: ( reactiveRules )?
			int alt30=2;
			int LA30_0 = input.LA(1);
			if ( (LA30_0==36||(LA30_0 >= 56 && LA30_0 <= 57)) ) {
				alt30=1;
			}
			switch (alt30) {
				case 1 :
					// JLPSSyntax.g:285:8: reactiveRules
					{
					pushFollow(FOLLOW_reactiveRules_in_file1742);
					reactiveRules37=reactiveRules();
					state._fsp--;

					retval.w[3] = (reactiveRules37!=null?((JLPSSyntaxParser.reactiveRules_return)reactiveRules37).w:false);
					}
					break;

			}

			// JLPSSyntax.g:286:7: ( goals )?
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==30||LA31_0==49) ) {
				alt31=1;
			}
			switch (alt31) {
				case 1 :
					// JLPSSyntax.g:286:8: goals
					{
					pushFollow(FOLLOW_goals_in_file1755);
					goals38=goals();
					state._fsp--;

					retval.w[4] = (goals38!=null?((JLPSSyntaxParser.goals_return)goals38).w:false);
					}
					break;

			}

			// JLPSSyntax.g:287:7: ( events )?
			int alt32=2;
			int LA32_0 = input.LA(1);
			if ( (LA32_0==28||LA32_0==47) ) {
				alt32=1;
			}
			switch (alt32) {
				case 1 :
					// JLPSSyntax.g:287:8: events
					{
					pushFollow(FOLLOW_events_in_file1768);
					events39=events();
					state._fsp--;

					retval.w[5] = (events39!=null?((JLPSSyntaxParser.events_return)events39).w:false);
					}
					break;

			}

			match(input,EOF,FOLLOW_EOF_in_file1780); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "file"

	// Delegated rules



	public static final BitSet FOLLOW_constant_in_unifiable57 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variable_in_unifiable67 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simpleSentence_in_unifiable77 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CONSTANT_in_constant98 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLE_in_variable119 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unifiable_in_parameters158 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_14_in_parameters169 = new BitSet(new long[]{0x0000000000000140L});
	public static final BitSet FOLLOW_unifiable_in_parameters175 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_constant_in_simpleSentence206 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_12_in_simpleSentence216 = new BitSet(new long[]{0x0000000000002140L});
	public static final BitSet FOLLOW_parameters_in_simpleSentence227 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_simpleSentence239 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_50_in_initiator268 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_12_in_initiator276 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_simpleSentence_in_initiator290 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_initiator292 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_simpleSentence_in_initiator298 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_initiator306 = new BitSet(new long[]{0x0000000000040002L});
	public static final BitSet FOLLOW_18_in_initiator323 = new BitSet(new long[]{0x2000000000001540L});
	public static final BitSet FOLLOW_and_in_initiator325 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_59_in_terminator364 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_12_in_terminator372 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_simpleSentence_in_terminator386 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_terminator388 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_simpleSentence_in_terminator394 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_terminator412 = new BitSet(new long[]{0x0000000000040002L});
	public static final BitSet FOLLOW_18_in_terminator421 = new BitSet(new long[]{0x2000000000001540L});
	public static final BitSet FOLLOW_and_in_terminator423 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_61_in_arithmetic464 = new BitSet(new long[]{0x0000000000000140L});
	public static final BitSet FOLLOW_unifiable_in_arithmetic470 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_SYMBOL_in_arithmetic472 = new BitSet(new long[]{0x0000000000000140L});
	public static final BitSet FOLLOW_unifiable_in_arithmetic478 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_62_in_arithmetic480 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variable_in_equal511 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_equal513 = new BitSet(new long[]{0x0000000000000140L});
	public static final BitSet FOLLOW_unifiable_in_equal519 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_arithmetic_in_term546 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simpleSentence_in_term556 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_equal_in_term566 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_12_in_term576 = new BitSet(new long[]{0x2000000000001540L});
	public static final BitSet FOLLOW_and_in_term578 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_term580 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_negation610 = new BitSet(new long[]{0x2000000000001140L});
	public static final BitSet FOLLOW_term_in_negation616 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_negation_in_and655 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_11_in_and660 = new BitSet(new long[]{0x2000000000001540L});
	public static final BitSet FOLLOW_negation_in_and666 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_60_in_truth697 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_in_reactiveRule723 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_truth_in_reactiveRule731 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_reactiveRule734 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_simpleSentence_in_reactiveRule736 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_reactiveRule738 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simpleSentence_in_rule773 = new BitSet(new long[]{0x0000000000050000L});
	public static final BitSet FOLLOW_18_in_rule776 = new BitSet(new long[]{0x2000000000001540L});
	public static final BitSet FOLLOW_and_in_rule778 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_rule784 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simpleSentence_in_fluent811 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_fluent813 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_lext844 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_lext860 = new BitSet(new long[]{0x4000000000000040L});
	public static final BitSet FOLLOW_fluent_in_lext869 = new BitSet(new long[]{0x4000000000000040L});
	public static final BitSet FOLLOW_62_in_lext897 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_lint924 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_lint940 = new BitSet(new long[]{0x4000000000000040L});
	public static final BitSet FOLLOW_rule_in_lint949 = new BitSet(new long[]{0x4000000000000040L});
	public static final BitSet FOLLOW_62_in_lint969 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_preconditions996 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_38_in_preconditions1004 = new BitSet(new long[]{0x0000038000600000L});
	public static final BitSet FOLLOW_set_in_preconditions1013 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_preconditions1021 = new BitSet(new long[]{0x3000000000001540L});
	public static final BitSet FOLLOW_and_in_preconditions1028 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_truth_in_preconditions1036 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_preconditions1039 = new BitSet(new long[]{0x0000028000400000L});
	public static final BitSet FOLLOW_set_in_preconditions1052 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_preconditions1060 = new BitSet(new long[]{0x3000000000001540L});
	public static final BitSet FOLLOW_and_in_preconditions1067 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_truth_in_preconditions1075 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_preconditions1078 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_39_in_preconditions1090 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_postconditions1117 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_38_in_postconditions1125 = new BitSet(new long[]{0x0804008000000000L});
	public static final BitSet FOLLOW_terminator_in_postconditions1134 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_postconditions1136 = new BitSet(new long[]{0x0804008000000000L});
	public static final BitSet FOLLOW_initiator_in_postconditions1148 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_postconditions1150 = new BitSet(new long[]{0x0804008000000000L});
	public static final BitSet FOLLOW_39_in_postconditions1161 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simpleSentence_in_action1188 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_action1190 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_action1192 = new BitSet(new long[]{0x40C0000C00000000L});
	public static final BitSet FOLLOW_preconditions_in_action1203 = new BitSet(new long[]{0x4040000400000000L});
	public static final BitSet FOLLOW_postconditions_in_action1216 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_62_in_action1228 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rule_in_macroaction1263 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_d1298 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_d1330 = new BitSet(new long[]{0x4020000200000040L});
	public static final BitSet FOLLOW_action_in_d1339 = new BitSet(new long[]{0x4020000200000040L});
	public static final BitSet FOLLOW_set_in_d1352 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_d1360 = new BitSet(new long[]{0x4000000000000040L});
	public static final BitSet FOLLOW_macroaction_in_d1369 = new BitSet(new long[]{0x4000000000000040L});
	public static final BitSet FOLLOW_62_in_d1381 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_62_in_d1391 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_database1434 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_database1442 = new BitSet(new long[]{0x44190021A0000000L});
	public static final BitSet FOLLOW_lext_in_database1451 = new BitSet(new long[]{0x4410002100000000L});
	public static final BitSet FOLLOW_lint_in_database1464 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_62_in_database1476 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_reactiveRules1503 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_reactiveRules1515 = new BitSet(new long[]{0x7000000000001540L});
	public static final BitSet FOLLOW_reactiveRule_in_reactiveRules1524 = new BitSet(new long[]{0x7000000000001540L});
	public static final BitSet FOLLOW_62_in_reactiveRules1544 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_goals1571 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_goals1579 = new BitSet(new long[]{0x4000000000000040L});
	public static final BitSet FOLLOW_rule_in_goals1588 = new BitSet(new long[]{0x4000000000000040L});
	public static final BitSet FOLLOW_62_in_goals1608 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_events1643 = new BitSet(new long[]{0x2000000000000000L});
	public static final BitSet FOLLOW_61_in_events1651 = new BitSet(new long[]{0x4000000000000040L});
	public static final BitSet FOLLOW_fluent_in_events1660 = new BitSet(new long[]{0x4000000000000040L});
	public static final BitSet FOLLOW_62_in_events1680 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_database_in_file1716 = new BitSet(new long[]{0x0302F4105E800000L});
	public static final BitSet FOLLOW_d_in_file1729 = new BitSet(new long[]{0x0302801050000000L});
	public static final BitSet FOLLOW_reactiveRules_in_file1742 = new BitSet(new long[]{0x0002800050000000L});
	public static final BitSet FOLLOW_goals_in_file1755 = new BitSet(new long[]{0x0000800010000000L});
	public static final BitSet FOLLOW_events_in_file1768 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_file1780 = new BitSet(new long[]{0x0000000000000002L});
}
