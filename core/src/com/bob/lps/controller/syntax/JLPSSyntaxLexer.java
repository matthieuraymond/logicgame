// $ANTLR 3.5.2 JLPSSyntax.g 2016-05-10 16:23:27

package com.bob.lps.controller.syntax;


import org.antlr.runtime.*;

@SuppressWarnings("all")
public class JLPSSyntaxLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public JLPSSyntaxLexer() {} 
	public JLPSSyntaxLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public JLPSSyntaxLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "JLPSSyntax.g"; }

	// $ANTLR start "T__10"
	public final void mT__10() throws RecognitionException {
		try {
			int _type = T__10;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:11:7: ( '!' )
			// JLPSSyntax.g:11:9: '!'
			{
			match('!'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__10"

	// $ANTLR start "T__11"
	public final void mT__11() throws RecognitionException {
		try {
			int _type = T__11;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:12:7: ( '&' )
			// JLPSSyntax.g:12:9: '&'
			{
			match('&'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__11"

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:13:7: ( '(' )
			// JLPSSyntax.g:13:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__12"

	// $ANTLR start "T__13"
	public final void mT__13() throws RecognitionException {
		try {
			int _type = T__13;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:14:7: ( ')' )
			// JLPSSyntax.g:14:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__13"

	// $ANTLR start "T__14"
	public final void mT__14() throws RecognitionException {
		try {
			int _type = T__14;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:15:7: ( ',' )
			// JLPSSyntax.g:15:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:16:7: ( '->' )
			// JLPSSyntax.g:16:9: '->'
			{
			match("->"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:17:7: ( '.' )
			// JLPSSyntax.g:17:9: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__16"

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:18:7: ( ':' )
			// JLPSSyntax.g:18:9: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:19:7: ( ':-' )
			// JLPSSyntax.g:19:9: ':-'
			{
			match(":-"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:20:7: ( '=' )
			// JLPSSyntax.g:20:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:21:7: ( '==' )
			// JLPSSyntax.g:21:9: '=='
			{
			match("=="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:22:7: ( 'Conditions' )
			// JLPSSyntax.g:22:9: 'Conditions'
			{
			match("Conditions"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:23:7: ( 'Conflicts' )
			// JLPSSyntax.g:23:9: 'Conflicts'
			{
			match("Conflicts"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:24:7: ( 'DSet' )
			// JLPSSyntax.g:24:9: 'DSet'
			{
			match("DSet"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:25:7: ( 'Database' )
			// JLPSSyntax.g:25:9: 'Database'
			{
			match("Database"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:26:7: ( 'Domain Theory' )
			// JLPSSyntax.g:26:9: 'Domain Theory'
			{
			match("Domain Theory"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:27:7: ( 'DomainTheory' )
			// JLPSSyntax.g:27:9: 'DomainTheory'
			{
			match("DomainTheory"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:28:7: ( 'Dset' )
			// JLPSSyntax.g:28:9: 'Dset'
			{
			match("Dset"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:29:7: ( 'Events' )
			// JLPSSyntax.g:29:9: 'Events'
			{
			match("Events"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:30:7: ( 'Facts' )
			// JLPSSyntax.g:30:9: 'Facts'
			{
			match("Facts"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:31:7: ( 'Goals' )
			// JLPSSyntax.g:31:9: 'Goals'
			{
			match("Goals"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:32:7: ( 'Lext' )
			// JLPSSyntax.g:32:9: 'Lext'
			{
			match("Lext"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:33:7: ( 'Lint' )
			// JLPSSyntax.g:33:9: 'Lint'
			{
			match("Lint"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:34:7: ( 'Macroactions' )
			// JLPSSyntax.g:34:9: 'Macroactions'
			{
			match("Macroactions"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:35:7: ( 'Postconditions' )
			// JLPSSyntax.g:35:9: 'Postconditions'
			{
			match("Postconditions"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:36:7: ( 'Preconditions' )
			// JLPSSyntax.g:36:9: 'Preconditions'
			{
			match("Preconditions"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "T__36"
	public final void mT__36() throws RecognitionException {
		try {
			int _type = T__36;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:37:7: ( 'ReactiveRules' )
			// JLPSSyntax.g:37:9: 'ReactiveRules'
			{
			match("ReactiveRules"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__36"

	// $ANTLR start "T__37"
	public final void mT__37() throws RecognitionException {
		try {
			int _type = T__37;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:38:7: ( 'Rules' )
			// JLPSSyntax.g:38:9: 'Rules'
			{
			match("Rules"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__37"

	// $ANTLR start "T__38"
	public final void mT__38() throws RecognitionException {
		try {
			int _type = T__38;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:39:7: ( '[' )
			// JLPSSyntax.g:39:9: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__38"

	// $ANTLR start "T__39"
	public final void mT__39() throws RecognitionException {
		try {
			int _type = T__39;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:40:7: ( ']' )
			// JLPSSyntax.g:40:9: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__39"

	// $ANTLR start "T__40"
	public final void mT__40() throws RecognitionException {
		try {
			int _type = T__40;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:41:7: ( 'conditions' )
			// JLPSSyntax.g:41:9: 'conditions'
			{
			match("conditions"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__40"

	// $ANTLR start "T__41"
	public final void mT__41() throws RecognitionException {
		try {
			int _type = T__41;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:42:7: ( 'conflicts' )
			// JLPSSyntax.g:42:9: 'conflicts'
			{
			match("conflicts"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__41"

	// $ANTLR start "T__42"
	public final void mT__42() throws RecognitionException {
		try {
			int _type = T__42;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:43:7: ( 'dSet' )
			// JLPSSyntax.g:43:9: 'dSet'
			{
			match("dSet"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__42"

	// $ANTLR start "T__43"
	public final void mT__43() throws RecognitionException {
		try {
			int _type = T__43;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:44:7: ( 'database' )
			// JLPSSyntax.g:44:9: 'database'
			{
			match("database"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__43"

	// $ANTLR start "T__44"
	public final void mT__44() throws RecognitionException {
		try {
			int _type = T__44;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:45:7: ( 'domain theory' )
			// JLPSSyntax.g:45:9: 'domain theory'
			{
			match("domain theory"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__44"

	// $ANTLR start "T__45"
	public final void mT__45() throws RecognitionException {
		try {
			int _type = T__45;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:46:7: ( 'domainTheory' )
			// JLPSSyntax.g:46:9: 'domainTheory'
			{
			match("domainTheory"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__45"

	// $ANTLR start "T__46"
	public final void mT__46() throws RecognitionException {
		try {
			int _type = T__46;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:47:7: ( 'dset' )
			// JLPSSyntax.g:47:9: 'dset'
			{
			match("dset"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__46"

	// $ANTLR start "T__47"
	public final void mT__47() throws RecognitionException {
		try {
			int _type = T__47;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:48:7: ( 'events' )
			// JLPSSyntax.g:48:9: 'events'
			{
			match("events"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__47"

	// $ANTLR start "T__48"
	public final void mT__48() throws RecognitionException {
		try {
			int _type = T__48;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:49:7: ( 'facts' )
			// JLPSSyntax.g:49:9: 'facts'
			{
			match("facts"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__48"

	// $ANTLR start "T__49"
	public final void mT__49() throws RecognitionException {
		try {
			int _type = T__49;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:50:7: ( 'goals' )
			// JLPSSyntax.g:50:9: 'goals'
			{
			match("goals"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__49"

	// $ANTLR start "T__50"
	public final void mT__50() throws RecognitionException {
		try {
			int _type = T__50;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:51:7: ( 'initiates' )
			// JLPSSyntax.g:51:9: 'initiates'
			{
			match("initiates"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__50"

	// $ANTLR start "T__51"
	public final void mT__51() throws RecognitionException {
		try {
			int _type = T__51;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:52:7: ( 'lext' )
			// JLPSSyntax.g:52:9: 'lext'
			{
			match("lext"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__51"

	// $ANTLR start "T__52"
	public final void mT__52() throws RecognitionException {
		try {
			int _type = T__52;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:53:7: ( 'lint' )
			// JLPSSyntax.g:53:9: 'lint'
			{
			match("lint"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__52"

	// $ANTLR start "T__53"
	public final void mT__53() throws RecognitionException {
		try {
			int _type = T__53;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:54:7: ( 'macroactions' )
			// JLPSSyntax.g:54:9: 'macroactions'
			{
			match("macroactions"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__53"

	// $ANTLR start "T__54"
	public final void mT__54() throws RecognitionException {
		try {
			int _type = T__54;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:55:7: ( 'postconditions' )
			// JLPSSyntax.g:55:9: 'postconditions'
			{
			match("postconditions"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__54"

	// $ANTLR start "T__55"
	public final void mT__55() throws RecognitionException {
		try {
			int _type = T__55;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:56:7: ( 'preconditions' )
			// JLPSSyntax.g:56:9: 'preconditions'
			{
			match("preconditions"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__55"

	// $ANTLR start "T__56"
	public final void mT__56() throws RecognitionException {
		try {
			int _type = T__56;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:57:7: ( 'reactiveRules' )
			// JLPSSyntax.g:57:9: 'reactiveRules'
			{
			match("reactiveRules"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__56"

	// $ANTLR start "T__57"
	public final void mT__57() throws RecognitionException {
		try {
			int _type = T__57;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:58:7: ( 'reactiverules' )
			// JLPSSyntax.g:58:9: 'reactiverules'
			{
			match("reactiverules"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__57"

	// $ANTLR start "T__58"
	public final void mT__58() throws RecognitionException {
		try {
			int _type = T__58;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:59:7: ( 'rules' )
			// JLPSSyntax.g:59:9: 'rules'
			{
			match("rules"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__58"

	// $ANTLR start "T__59"
	public final void mT__59() throws RecognitionException {
		try {
			int _type = T__59;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:60:7: ( 'terminates' )
			// JLPSSyntax.g:60:9: 'terminates'
			{
			match("terminates"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__59"

	// $ANTLR start "T__60"
	public final void mT__60() throws RecognitionException {
		try {
			int _type = T__60;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:61:7: ( 'true' )
			// JLPSSyntax.g:61:9: 'true'
			{
			match("true"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__60"

	// $ANTLR start "T__61"
	public final void mT__61() throws RecognitionException {
		try {
			int _type = T__61;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:62:7: ( '{' )
			// JLPSSyntax.g:62:9: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__61"

	// $ANTLR start "T__62"
	public final void mT__62() throws RecognitionException {
		try {
			int _type = T__62;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:63:7: ( '}' )
			// JLPSSyntax.g:63:9: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__62"

	// $ANTLR start "SYMBOL"
	public final void mSYMBOL() throws RecognitionException {
		try {
			int _type = SYMBOL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:301:8: ( ( '<' | '>' | '<=' | '>=' | '==' | '!=' ) )
			// JLPSSyntax.g:301:10: ( '<' | '>' | '<=' | '>=' | '==' | '!=' )
			{
			// JLPSSyntax.g:301:10: ( '<' | '>' | '<=' | '>=' | '==' | '!=' )
			int alt1=6;
			switch ( input.LA(1) ) {
			case '<':
				{
				int LA1_1 = input.LA(2);
				if ( (LA1_1=='=') ) {
					alt1=3;
				}

				else {
					alt1=1;
				}

				}
				break;
			case '>':
				{
				int LA1_2 = input.LA(2);
				if ( (LA1_2=='=') ) {
					alt1=4;
				}

				else {
					alt1=2;
				}

				}
				break;
			case '=':
				{
				alt1=5;
				}
				break;
			case '!':
				{
				alt1=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// JLPSSyntax.g:301:11: '<'
					{
					match('<'); 
					}
					break;
				case 2 :
					// JLPSSyntax.g:301:17: '>'
					{
					match('>'); 
					}
					break;
				case 3 :
					// JLPSSyntax.g:301:23: '<='
					{
					match("<="); 

					}
					break;
				case 4 :
					// JLPSSyntax.g:301:30: '>='
					{
					match(">="); 

					}
					break;
				case 5 :
					// JLPSSyntax.g:301:37: '=='
					{
					match("=="); 

					}
					break;
				case 6 :
					// JLPSSyntax.g:301:44: '!='
					{
					match("!="); 

					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SYMBOL"

	// $ANTLR start "CONSTANT"
	public final void mCONSTANT() throws RecognitionException {
		try {
			int _type = CONSTANT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:297:10: ( ( 'a' .. 'z' | '0' .. '9' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' | '-' | '.' )* )
			// JLPSSyntax.g:297:12: ( 'a' .. 'z' | '0' .. '9' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' | '-' | '.' )*
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// JLPSSyntax.g:297:34: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' | '-' | '.' )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '-' && LA2_0 <= '.')||(LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// JLPSSyntax.g:
					{
					if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop2;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CONSTANT"

	// $ANTLR start "VARIABLE"
	public final void mVARIABLE() throws RecognitionException {
		try {
			int _type = VARIABLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:298:10: ( ( 'A' .. 'Z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' | '-' | '.' )* )
			// JLPSSyntax.g:298:12: ( 'A' .. 'Z' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' | '-' | '.' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// JLPSSyntax.g:298:23: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' | '-' | '.' )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '-' && LA3_0 <= '.')||(LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||LA3_0=='_'||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// JLPSSyntax.g:
					{
					if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop3;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VARIABLE"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:299:4: ( ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+ )
			// JLPSSyntax.g:299:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
			{
			// JLPSSyntax.g:299:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '\t' && LA4_0 <= '\n')||(LA4_0 >= '\f' && LA4_0 <= '\r')||LA4_0==' ') ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// JLPSSyntax.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			_channel = HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "COMMENT_LINE"
	public final void mCOMMENT_LINE() throws RecognitionException {
		try {
			int _type = COMMENT_LINE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:300:14: ( '//' ( . )* ( '\\n' | '\\r' ) )
			// JLPSSyntax.g:300:16: '//' ( . )* ( '\\n' | '\\r' )
			{
			match("//"); 

			// JLPSSyntax.g:300:21: ( . )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0=='\n'||LA5_0=='\r') ) {
					alt5=2;
				}
				else if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '\uFFFF')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// JLPSSyntax.g:300:21: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop5;
				}
			}

			if ( input.LA(1)=='\n'||input.LA(1)=='\r' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			_channel = HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT_LINE"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// JLPSSyntax.g:301:9: ( '/*' ( . )* '*/' )
			// JLPSSyntax.g:301:11: '/*' ( . )* '*/'
			{
			match("/*"); 

			// JLPSSyntax.g:301:16: ( . )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0=='*') ) {
					int LA6_1 = input.LA(2);
					if ( (LA6_1=='/') ) {
						alt6=2;
					}
					else if ( ((LA6_1 >= '\u0000' && LA6_1 <= '.')||(LA6_1 >= '0' && LA6_1 <= '\uFFFF')) ) {
						alt6=1;
					}

				}
				else if ( ((LA6_0 >= '\u0000' && LA6_0 <= ')')||(LA6_0 >= '+' && LA6_0 <= '\uFFFF')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// JLPSSyntax.g:301:16: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop6;
				}
			}

			match("*/"); 

			_channel = HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	@Override
	public void mTokens() throws RecognitionException {
		// JLPSSyntax.g:1:8: ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | SYMBOL | CONSTANT | VARIABLE | WS | COMMENT_LINE | COMMENT )
		int alt7=59;
		alt7 = dfa7.predict(input);
		switch (alt7) {
			case 1 :
				// JLPSSyntax.g:1:10: T__10
				{
				mT__10(); 

				}
				break;
			case 2 :
				// JLPSSyntax.g:1:16: T__11
				{
				mT__11(); 

				}
				break;
			case 3 :
				// JLPSSyntax.g:1:22: T__12
				{
				mT__12(); 

				}
				break;
			case 4 :
				// JLPSSyntax.g:1:28: T__13
				{
				mT__13(); 

				}
				break;
			case 5 :
				// JLPSSyntax.g:1:34: T__14
				{
				mT__14(); 

				}
				break;
			case 6 :
				// JLPSSyntax.g:1:40: T__15
				{
				mT__15(); 

				}
				break;
			case 7 :
				// JLPSSyntax.g:1:46: T__16
				{
				mT__16(); 

				}
				break;
			case 8 :
				// JLPSSyntax.g:1:52: T__17
				{
				mT__17(); 

				}
				break;
			case 9 :
				// JLPSSyntax.g:1:58: T__18
				{
				mT__18(); 

				}
				break;
			case 10 :
				// JLPSSyntax.g:1:64: T__19
				{
				mT__19(); 

				}
				break;
			case 11 :
				// JLPSSyntax.g:1:70: T__20
				{
				mT__20(); 

				}
				break;
			case 12 :
				// JLPSSyntax.g:1:76: T__21
				{
				mT__21(); 

				}
				break;
			case 13 :
				// JLPSSyntax.g:1:82: T__22
				{
				mT__22(); 

				}
				break;
			case 14 :
				// JLPSSyntax.g:1:88: T__23
				{
				mT__23(); 

				}
				break;
			case 15 :
				// JLPSSyntax.g:1:94: T__24
				{
				mT__24(); 

				}
				break;
			case 16 :
				// JLPSSyntax.g:1:100: T__25
				{
				mT__25(); 

				}
				break;
			case 17 :
				// JLPSSyntax.g:1:106: T__26
				{
				mT__26(); 

				}
				break;
			case 18 :
				// JLPSSyntax.g:1:112: T__27
				{
				mT__27(); 

				}
				break;
			case 19 :
				// JLPSSyntax.g:1:118: T__28
				{
				mT__28(); 

				}
				break;
			case 20 :
				// JLPSSyntax.g:1:124: T__29
				{
				mT__29(); 

				}
				break;
			case 21 :
				// JLPSSyntax.g:1:130: T__30
				{
				mT__30(); 

				}
				break;
			case 22 :
				// JLPSSyntax.g:1:136: T__31
				{
				mT__31(); 

				}
				break;
			case 23 :
				// JLPSSyntax.g:1:142: T__32
				{
				mT__32(); 

				}
				break;
			case 24 :
				// JLPSSyntax.g:1:148: T__33
				{
				mT__33(); 

				}
				break;
			case 25 :
				// JLPSSyntax.g:1:154: T__34
				{
				mT__34(); 

				}
				break;
			case 26 :
				// JLPSSyntax.g:1:160: T__35
				{
				mT__35(); 

				}
				break;
			case 27 :
				// JLPSSyntax.g:1:166: T__36
				{
				mT__36(); 

				}
				break;
			case 28 :
				// JLPSSyntax.g:1:172: T__37
				{
				mT__37(); 

				}
				break;
			case 29 :
				// JLPSSyntax.g:1:178: T__38
				{
				mT__38(); 

				}
				break;
			case 30 :
				// JLPSSyntax.g:1:184: T__39
				{
				mT__39(); 

				}
				break;
			case 31 :
				// JLPSSyntax.g:1:190: T__40
				{
				mT__40(); 

				}
				break;
			case 32 :
				// JLPSSyntax.g:1:196: T__41
				{
				mT__41(); 

				}
				break;
			case 33 :
				// JLPSSyntax.g:1:202: T__42
				{
				mT__42(); 

				}
				break;
			case 34 :
				// JLPSSyntax.g:1:208: T__43
				{
				mT__43(); 

				}
				break;
			case 35 :
				// JLPSSyntax.g:1:214: T__44
				{
				mT__44(); 

				}
				break;
			case 36 :
				// JLPSSyntax.g:1:220: T__45
				{
				mT__45(); 

				}
				break;
			case 37 :
				// JLPSSyntax.g:1:226: T__46
				{
				mT__46(); 

				}
				break;
			case 38 :
				// JLPSSyntax.g:1:232: T__47
				{
				mT__47(); 

				}
				break;
			case 39 :
				// JLPSSyntax.g:1:238: T__48
				{
				mT__48(); 

				}
				break;
			case 40 :
				// JLPSSyntax.g:1:244: T__49
				{
				mT__49(); 

				}
				break;
			case 41 :
				// JLPSSyntax.g:1:250: T__50
				{
				mT__50(); 

				}
				break;
			case 42 :
				// JLPSSyntax.g:1:256: T__51
				{
				mT__51(); 

				}
				break;
			case 43 :
				// JLPSSyntax.g:1:262: T__52
				{
				mT__52(); 

				}
				break;
			case 44 :
				// JLPSSyntax.g:1:268: T__53
				{
				mT__53(); 

				}
				break;
			case 45 :
				// JLPSSyntax.g:1:274: T__54
				{
				mT__54(); 

				}
				break;
			case 46 :
				// JLPSSyntax.g:1:280: T__55
				{
				mT__55(); 

				}
				break;
			case 47 :
				// JLPSSyntax.g:1:286: T__56
				{
				mT__56(); 

				}
				break;
			case 48 :
				// JLPSSyntax.g:1:292: T__57
				{
				mT__57(); 

				}
				break;
			case 49 :
				// JLPSSyntax.g:1:298: T__58
				{
				mT__58(); 

				}
				break;
			case 50 :
				// JLPSSyntax.g:1:304: T__59
				{
				mT__59(); 

				}
				break;
			case 51 :
				// JLPSSyntax.g:1:310: T__60
				{
				mT__60(); 

				}
				break;
			case 52 :
				// JLPSSyntax.g:1:316: T__61
				{
				mT__61(); 

				}
				break;
			case 53 :
				// JLPSSyntax.g:1:322: T__62
				{
				mT__62(); 

				}
				break;
			case 54 :
				// JLPSSyntax.g:1:328: SYMBOL
				{
				mSYMBOL(); 

				}
				break;
			case 55 :
				// JLPSSyntax.g:1:335: CONSTANT
				{
				mCONSTANT(); 

				}
				break;
			case 56 :
				// JLPSSyntax.g:1:344: VARIABLE
				{
				mVARIABLE(); 

				}
				break;
			case 57 :
				// JLPSSyntax.g:1:353: WS
				{
				mWS(); 

				}
				break;
			case 58 :
				// JLPSSyntax.g:1:356: COMMENT_LINE
				{
				mCOMMENT_LINE(); 

				}
				break;
			case 59 :
				// JLPSSyntax.g:1:369: COMMENT
				{
				mCOMMENT(); 

				}
				break;

		}
	}


	protected DFA7 dfa7 = new DFA7(this);
	static final String DFA7_eotS =
		"\1\uffff\1\47\6\uffff\1\51\1\53\11\44\2\uffff\13\43\14\uffff\17\44\22"+
		"\43\3\uffff\17\44\22\43\2\44\1\u0096\2\44\1\u0099\3\44\1\u009d\1\u009e"+
		"\5\44\2\43\1\u00a6\2\43\1\u00a9\4\43\1\u00ae\1\u00af\6\43\1\u00b6\2\44"+
		"\1\uffff\2\44\1\uffff\1\44\1\u00bc\1\u00bd\2\uffff\4\44\1\u00c2\2\43\1"+
		"\uffff\2\43\1\uffff\1\43\1\u00c8\1\u00c9\1\43\2\uffff\4\43\1\u00cf\1\43"+
		"\1\uffff\4\44\1\u00d6\2\uffff\4\44\1\uffff\4\43\1\u00e0\2\uffff\5\43\1"+
		"\uffff\1\43\3\44\1\uffff\1\44\1\uffff\4\44\3\43\1\uffff\1\43\1\uffff\6"+
		"\43\2\44\1\u00fb\5\44\2\43\1\u0103\7\43\1\44\1\u010d\1\uffff\5\44\1\43"+
		"\1\u0114\1\uffff\1\43\1\u0116\6\43\1\u011d\1\uffff\5\44\1\u0123\1\uffff"+
		"\1\43\1\uffff\5\43\1\u012a\1\uffff\5\44\1\uffff\6\43\1\uffff\1\u0136\1"+
		"\u0137\3\44\1\u013b\1\u013c\4\43\2\uffff\1\44\1\u0142\1\u0143\2\uffff"+
		"\1\43\1\u0145\1\u0146\1\u0147\1\u0148\2\uffff\1\u0149\5\uffff";
	static final String DFA7_eofS =
		"\u014a\uffff";
	static final String DFA7_minS =
		"\1\11\1\75\6\uffff\1\55\1\75\1\157\1\123\1\166\1\141\1\157\1\145\1\141"+
		"\1\157\1\145\2\uffff\1\157\1\123\1\166\1\141\1\157\1\156\1\145\1\141\1"+
		"\157\2\145\6\uffff\1\52\5\uffff\1\156\1\145\1\164\1\155\2\145\1\143\1"+
		"\141\1\170\1\156\1\143\1\163\1\145\1\141\1\154\1\156\1\145\1\164\1\155"+
		"\2\145\1\143\1\141\1\151\1\170\1\156\1\143\1\163\1\145\1\141\1\154\1\162"+
		"\1\165\3\uffff\1\144\1\164\2\141\1\164\1\156\1\164\1\154\2\164\1\162\1"+
		"\164\2\143\1\145\1\144\1\164\2\141\1\164\1\156\1\164\1\154\3\164\1\162"+
		"\1\164\2\143\1\145\1\155\1\145\1\151\1\154\1\55\1\142\1\151\1\55\1\164"+
		"\2\163\2\55\1\157\1\143\1\157\1\164\1\163\1\151\1\154\1\55\1\142\1\151"+
		"\1\55\1\164\2\163\1\151\2\55\1\157\1\143\1\157\1\164\1\163\1\151\1\55"+
		"\1\164\1\151\1\uffff\1\141\1\156\1\uffff\1\163\2\55\2\uffff\1\141\1\157"+
		"\1\156\1\151\1\55\1\164\1\151\1\uffff\1\141\1\156\1\uffff\1\163\2\55\1"+
		"\141\2\uffff\1\141\1\157\1\156\1\151\1\55\1\156\1\uffff\1\151\1\143\1"+
		"\163\1\40\1\55\2\uffff\1\143\1\156\1\144\1\166\1\uffff\1\151\1\143\1\163"+
		"\1\40\1\55\2\uffff\1\164\1\143\1\156\1\144\1\166\1\uffff\1\141\1\157\1"+
		"\164\1\145\1\uffff\1\150\1\uffff\1\164\1\144\1\151\1\145\1\157\1\164\1"+
		"\145\1\uffff\1\150\1\uffff\1\145\1\164\1\144\1\151\1\145\1\164\1\156\1"+
		"\163\1\55\1\145\2\151\1\164\1\122\1\156\1\163\1\55\1\145\1\163\2\151\1"+
		"\164\1\122\1\145\1\163\1\55\1\uffff\2\157\1\164\1\151\1\165\1\163\1\55"+
		"\1\uffff\1\157\1\55\1\157\1\164\1\151\2\165\1\163\1\55\1\uffff\1\162\1"+
		"\156\1\151\1\157\1\154\1\55\1\uffff\1\162\1\uffff\1\156\1\151\1\157\2"+
		"\154\1\55\1\uffff\1\171\1\163\1\157\1\156\1\145\1\uffff\1\171\1\163\1"+
		"\157\1\156\2\145\1\uffff\2\55\1\156\2\163\2\55\1\156\3\163\2\uffff\1\163"+
		"\2\55\2\uffff\1\163\4\55\2\uffff\1\55\5\uffff";
	static final String DFA7_maxS =
		"\1\175\1\75\6\uffff\1\55\1\75\1\157\1\163\1\166\1\141\1\157\1\151\1\141"+
		"\1\162\1\165\2\uffff\1\157\1\163\1\166\1\141\1\157\1\156\1\151\1\141\1"+
		"\162\1\165\1\162\6\uffff\1\57\5\uffff\1\156\1\145\1\164\1\155\2\145\1"+
		"\143\1\141\1\170\1\156\1\143\1\163\1\145\1\141\1\154\1\156\1\145\1\164"+
		"\1\155\2\145\1\143\1\141\1\151\1\170\1\156\1\143\1\163\1\145\1\141\1\154"+
		"\1\162\1\165\3\uffff\1\146\1\164\2\141\1\164\1\156\1\164\1\154\2\164\1"+
		"\162\1\164\2\143\1\145\1\146\1\164\2\141\1\164\1\156\1\164\1\154\3\164"+
		"\1\162\1\164\2\143\1\145\1\155\1\145\1\151\1\154\1\172\1\142\1\151\1\172"+
		"\1\164\2\163\2\172\1\157\1\143\1\157\1\164\1\163\1\151\1\154\1\172\1\142"+
		"\1\151\1\172\1\164\2\163\1\151\2\172\1\157\1\143\1\157\1\164\1\163\1\151"+
		"\1\172\1\164\1\151\1\uffff\1\141\1\156\1\uffff\1\163\2\172\2\uffff\1\141"+
		"\1\157\1\156\1\151\1\172\1\164\1\151\1\uffff\1\141\1\156\1\uffff\1\163"+
		"\2\172\1\141\2\uffff\1\141\1\157\1\156\1\151\1\172\1\156\1\uffff\1\151"+
		"\1\143\1\163\1\124\1\172\2\uffff\1\143\1\156\1\144\1\166\1\uffff\1\151"+
		"\1\143\1\163\1\124\1\172\2\uffff\1\164\1\143\1\156\1\144\1\166\1\uffff"+
		"\1\141\1\157\1\164\1\145\1\uffff\1\150\1\uffff\1\164\1\144\1\151\1\145"+
		"\1\157\1\164\1\145\1\uffff\1\150\1\uffff\1\145\1\164\1\144\1\151\1\145"+
		"\1\164\1\156\1\163\1\172\1\145\2\151\1\164\1\122\1\156\1\163\1\172\1\145"+
		"\1\163\2\151\1\164\1\162\1\145\1\163\1\172\1\uffff\2\157\1\164\1\151\1"+
		"\165\1\163\1\172\1\uffff\1\157\1\172\1\157\1\164\1\151\2\165\1\163\1\172"+
		"\1\uffff\1\162\1\156\1\151\1\157\1\154\1\172\1\uffff\1\162\1\uffff\1\156"+
		"\1\151\1\157\2\154\1\172\1\uffff\1\171\1\163\1\157\1\156\1\145\1\uffff"+
		"\1\171\1\163\1\157\1\156\2\145\1\uffff\2\172\1\156\2\163\2\172\1\156\3"+
		"\163\2\uffff\1\163\2\172\2\uffff\1\163\4\172\2\uffff\1\172\5\uffff";
	static final String DFA7_acceptS =
		"\2\uffff\1\2\1\3\1\4\1\5\1\6\1\7\13\uffff\1\35\1\36\13\uffff\1\64\1\65"+
		"\1\66\1\67\1\70\1\71\1\uffff\1\1\1\11\1\10\1\13\1\12\41\uffff\1\72\1\73"+
		"\1\13\106\uffff\1\16\2\uffff\1\22\3\uffff\1\26\1\27\7\uffff\1\41\2\uffff"+
		"\1\45\4\uffff\1\52\1\53\6\uffff\1\63\5\uffff\1\24\1\25\4\uffff\1\34\5"+
		"\uffff\1\47\1\50\5\uffff\1\61\4\uffff\1\20\1\uffff\1\23\7\uffff\1\43\1"+
		"\uffff\1\46\32\uffff\1\17\7\uffff\1\42\11\uffff\1\15\6\uffff\1\40\1\uffff"+
		"\1\51\6\uffff\1\14\5\uffff\1\37\6\uffff\1\62\13\uffff\1\21\1\30\3\uffff"+
		"\1\44\1\54\5\uffff\1\32\1\33\1\uffff\1\56\1\57\1\60\1\31\1\55";
	static final String DFA7_specialS =
		"\u014a\uffff}>";
	static final String[] DFA7_transitionS = {
			"\2\45\1\uffff\2\45\22\uffff\1\45\1\1\4\uffff\1\2\1\uffff\1\3\1\4\2\uffff"+
			"\1\5\1\6\1\7\1\46\12\43\1\10\1\uffff\1\42\1\11\1\42\2\uffff\2\44\1\12"+
			"\1\13\1\14\1\15\1\16\4\44\1\17\1\20\2\44\1\21\1\44\1\22\10\44\1\23\1"+
			"\uffff\1\24\3\uffff\2\43\1\25\1\26\1\27\1\30\1\31\1\43\1\32\2\43\1\33"+
			"\1\34\2\43\1\35\1\43\1\36\1\43\1\37\6\43\1\40\1\uffff\1\41",
			"\1\42",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\50",
			"\1\52",
			"\1\54",
			"\1\55\15\uffff\1\56\15\uffff\1\57\3\uffff\1\60",
			"\1\61",
			"\1\62",
			"\1\63",
			"\1\64\3\uffff\1\65",
			"\1\66",
			"\1\67\2\uffff\1\70",
			"\1\71\17\uffff\1\72",
			"",
			"",
			"\1\73",
			"\1\74\15\uffff\1\75\15\uffff\1\76\3\uffff\1\77",
			"\1\100",
			"\1\101",
			"\1\102",
			"\1\103",
			"\1\104\3\uffff\1\105",
			"\1\106",
			"\1\107\2\uffff\1\110",
			"\1\111\17\uffff\1\112",
			"\1\113\14\uffff\1\114",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\116\4\uffff\1\115",
			"",
			"",
			"",
			"",
			"",
			"\1\120",
			"\1\121",
			"\1\122",
			"\1\123",
			"\1\124",
			"\1\125",
			"\1\126",
			"\1\127",
			"\1\130",
			"\1\131",
			"\1\132",
			"\1\133",
			"\1\134",
			"\1\135",
			"\1\136",
			"\1\137",
			"\1\140",
			"\1\141",
			"\1\142",
			"\1\143",
			"\1\144",
			"\1\145",
			"\1\146",
			"\1\147",
			"\1\150",
			"\1\151",
			"\1\152",
			"\1\153",
			"\1\154",
			"\1\155",
			"\1\156",
			"\1\157",
			"\1\160",
			"",
			"",
			"",
			"\1\161\1\uffff\1\162",
			"\1\163",
			"\1\164",
			"\1\165",
			"\1\166",
			"\1\167",
			"\1\170",
			"\1\171",
			"\1\172",
			"\1\173",
			"\1\174",
			"\1\175",
			"\1\176",
			"\1\177",
			"\1\u0080",
			"\1\u0081\1\uffff\1\u0082",
			"\1\u0083",
			"\1\u0084",
			"\1\u0085",
			"\1\u0086",
			"\1\u0087",
			"\1\u0088",
			"\1\u0089",
			"\1\u008a",
			"\1\u008b",
			"\1\u008c",
			"\1\u008d",
			"\1\u008e",
			"\1\u008f",
			"\1\u0090",
			"\1\u0091",
			"\1\u0092",
			"\1\u0093",
			"\1\u0094",
			"\1\u0095",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\u0097",
			"\1\u0098",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\u009a",
			"\1\u009b",
			"\1\u009c",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\u009f",
			"\1\u00a0",
			"\1\u00a1",
			"\1\u00a2",
			"\1\u00a3",
			"\1\u00a4",
			"\1\u00a5",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\1\u00a7",
			"\1\u00a8",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\1\u00aa",
			"\1\u00ab",
			"\1\u00ac",
			"\1\u00ad",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\1\u00b0",
			"\1\u00b1",
			"\1\u00b2",
			"\1\u00b3",
			"\1\u00b4",
			"\1\u00b5",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\1\u00b7",
			"\1\u00b8",
			"",
			"\1\u00b9",
			"\1\u00ba",
			"",
			"\1\u00bb",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"",
			"",
			"\1\u00be",
			"\1\u00bf",
			"\1\u00c0",
			"\1\u00c1",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\u00c3",
			"\1\u00c4",
			"",
			"\1\u00c5",
			"\1\u00c6",
			"",
			"\1\u00c7",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\1\u00ca",
			"",
			"",
			"\1\u00cb",
			"\1\u00cc",
			"\1\u00cd",
			"\1\u00ce",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\1\u00d0",
			"",
			"\1\u00d1",
			"\1\u00d2",
			"\1\u00d3",
			"\1\u00d4\63\uffff\1\u00d5",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"",
			"",
			"\1\u00d7",
			"\1\u00d8",
			"\1\u00d9",
			"\1\u00da",
			"",
			"\1\u00db",
			"\1\u00dc",
			"\1\u00dd",
			"\1\u00de\63\uffff\1\u00df",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"",
			"",
			"\1\u00e1",
			"\1\u00e2",
			"\1\u00e3",
			"\1\u00e4",
			"\1\u00e5",
			"",
			"\1\u00e6",
			"\1\u00e7",
			"\1\u00e8",
			"\1\u00e9",
			"",
			"\1\u00ea",
			"",
			"\1\u00eb",
			"\1\u00ec",
			"\1\u00ed",
			"\1\u00ee",
			"\1\u00ef",
			"\1\u00f0",
			"\1\u00f1",
			"",
			"\1\u00f2",
			"",
			"\1\u00f3",
			"\1\u00f4",
			"\1\u00f5",
			"\1\u00f6",
			"\1\u00f7",
			"\1\u00f8",
			"\1\u00f9",
			"\1\u00fa",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\u00fc",
			"\1\u00fd",
			"\1\u00fe",
			"\1\u00ff",
			"\1\u0100",
			"\1\u0101",
			"\1\u0102",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\1\u0104",
			"\1\u0105",
			"\1\u0106",
			"\1\u0107",
			"\1\u0108",
			"\1\u0109\37\uffff\1\u010a",
			"\1\u010b",
			"\1\u010c",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"",
			"\1\u010e",
			"\1\u010f",
			"\1\u0110",
			"\1\u0111",
			"\1\u0112",
			"\1\u0113",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"",
			"\1\u0115",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\1\u0117",
			"\1\u0118",
			"\1\u0119",
			"\1\u011a",
			"\1\u011b",
			"\1\u011c",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"",
			"\1\u011e",
			"\1\u011f",
			"\1\u0120",
			"\1\u0121",
			"\1\u0122",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"",
			"\1\u0124",
			"",
			"\1\u0125",
			"\1\u0126",
			"\1\u0127",
			"\1\u0128",
			"\1\u0129",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"",
			"\1\u012b",
			"\1\u012c",
			"\1\u012d",
			"\1\u012e",
			"\1\u012f",
			"",
			"\1\u0130",
			"\1\u0131",
			"\1\u0132",
			"\1\u0133",
			"\1\u0134",
			"\1\u0135",
			"",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\1\u0138",
			"\1\u0139",
			"\1\u013a",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\1\u013d",
			"\1\u013e",
			"\1\u013f",
			"\1\u0140",
			"",
			"",
			"\1\u0141",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"",
			"",
			"\1\u0144",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"\2\44\1\uffff\12\44\7\uffff\32\44\4\uffff\1\44\1\uffff\32\44",
			"",
			"",
			"\2\43\1\uffff\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
			"",
			"",
			"",
			"",
			""
	};

	static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
	static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
	static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
	static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
	static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
	static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
	static final short[][] DFA7_transition;

	static {
		int numStates = DFA7_transitionS.length;
		DFA7_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
		}
	}

	protected class DFA7 extends DFA {

		public DFA7(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 7;
			this.eot = DFA7_eot;
			this.eof = DFA7_eof;
			this.min = DFA7_min;
			this.max = DFA7_max;
			this.accept = DFA7_accept;
			this.special = DFA7_special;
			this.transition = DFA7_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | SYMBOL | CONSTANT | VARIABLE | WS | COMMENT_LINE | COMMENT );";
		}
	}

}
