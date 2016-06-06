/**
* @author Alexandre Camus
*/
grammar JLPSSyntax;

options {
  language = Java;
}

@header {
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
import com.bob.lps.model.DPostDeclaration;
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
}

@lexer::header {
package com.bob.lps.controller.syntax;
}

@members {
HashMap<String, Variable> variables = new HashMap<String, Variable>();
}

/**
*
* Unifiable
*
**/
unifiable returns [Unifiable unifiable]
  :   constant {$unifiable = $constant.constant;}
  |   variable {$unifiable = $variable.variable;}
  |   simpleSentence {$unifiable = $simpleSentence.simpleSentence;}
  ;

constant returns [Constant constant]
  :   CONSTANT {$constant = new Constant($CONSTANT.text);}
  ;

variable returns [Variable variable]
  :   VARIABLE
      {if (!this.variables.containsKey($VARIABLE.text)) {
          $variable = new Variable($VARIABLE.text);
          this.variables.put($VARIABLE.text, $variable);
      } else {
          $variable = this.variables.get($VARIABLE.text);
      }}
  ;

parameters returns [ArrayList<Unifiable> parameters]
  :   {$parameters = new ArrayList<Unifiable>();}
      par1 = unifiable {$parameters.add($par1.unifiable);}
      (',' par2 = unifiable {$parameters.add($par2.unifiable);})*
  ;

simpleSentence returns [SimpleSentence simpleSentence]
  :   {Constant name; Unifiable[] parameters = new Unifiable[0];}
      constant {name = $constant.constant;}
      '('
        (parameters {parameters = $parameters.parameters.toArray(parameters);})?
      ')'
      {$simpleSentence = new SimpleSentence(name, parameters);}
  ;

/**
*
* DPost declarations
*
**/
initiator returns [Initiator initiator, String factName]
  :   'initiates'
      '('
        event = simpleSentence ',' fact = simpleSentence
      ')'
      {Clause body = null;}
      (':-' and {body = $and.clause;})?
      {$initiator = new Initiator($event.simpleSentence, $fact.simpleSentence, body);}
      {$factName = $fact.simpleSentence.getName();}
  ;

terminator returns [Terminator terminator, String factName]
  :   'terminates'
      '('
        event = simpleSentence ',' fact = simpleSentence
        {Clause body = null;}
      ')'
      (':-' and {body = $and.clause;})?
      {$terminator = new Terminator($event.simpleSentence, $fact.simpleSentence, body);}
      {$factName = $fact.simpleSentence.getName();}
  ;

/**
*
* Clauses definitions
*
*/
arithmetic returns [Arithmetic expr]
  :   '{' op1 = unifiable SYMBOL op2 = unifiable '}'
      {$expr = new Arithmetic($op1.unifiable, $SYMBOL.text, $op2.unifiable);}
  ;

equal returns [Equal equal]
  :   op1 = variable '==' op2 = unifiable
      {$equal = new Equal($op1.variable, $op2.unifiable);}
  ;

term returns [Clause clause]
  :   arithmetic {$clause = $arithmetic.expr;}
  |   simpleSentence {$clause = $simpleSentence.simpleSentence;}
  |   equal {$clause = $equal.equal;}
  |   '(' and ')' {$clause = $and.clause;}
  ;

negation returns [Clause clause]
  :   {boolean flag = false;}
      ('!' {flag = true;})? term
      {$clause = (flag) ? new Not($term.clause) : $term.clause;}
  ;

and returns [Clause clause]
  :   {boolean flag = false;
      ArrayList<Clause> operands = new ArrayList<Clause>();}
      op1 = negation {operands.add($op1.clause);} ('&' op2 = negation {flag = true; operands.add($op2.clause);})*
      {$clause = (flag) ? new And(operands) : $op1.clause;}
  ;

truth returns [Clause clause = null]
  :   'true'
  ;

/**
*
* Rules definitions
*
**/
reactiveRule returns [ReactiveRule rule]
  :   (conditions = and | conditions = truth) '->' simpleSentence '.'
      {$rule = new ReactiveRule($conditions.clause, $simpleSentence.simpleSentence);}
  ;

rule returns [Rule rule]
  :   {Clause body = null;}
      simpleSentence (':-' and {body = $and.clause;})? '.'
      {$rule = new Rule($simpleSentence.simpleSentence, body);}
  ;

fluent returns [SimpleSentence rule]
  :   simpleSentence '.' {$rule = $simpleSentence.simpleSentence;}
  ;

/**
*
* Sets definitions
*
**/
lext returns [boolean w, FactSet set, HashSet<String> facts]
  :   {$set = new FactSet(); $w = true; $facts = new HashSet<String>();}
      ('Lext' | 'lext' | 'facts' | 'Facts') '{'
      (fluent {$set.addFact($fluent.rule); $w = false; $facts.add($fluent.rule.getName());})*
      {this.variables = new HashMap<String, Variable>();}
      {Database.getInstance().setFactsDatabase($set);}
      '}'
  ;

lint returns [boolean w, RuleSet set]
  :   {$set = new RuleSet(); $w = true;}
      ('Lint' | 'lint' | 'rules' | 'Rules') '{'
      (rule {$set.addRule($rule.rule); $w = false;})*
      {this.variables = new HashMap<String, Variable>();}
      '}'
      {Database.getInstance().setRulesDatabase($set);}
  ;

preconditions returns [Clause conditions, Clause conflicts]
  :   ('Preconditions' | 'preconditions') '['
      (('conditions' | 'Conditions') ':' (cond = and | cond = truth) '.' {$conditions = $cond.clause;})?
      (('conflicts' | 'Conflicts') ':' (conf = and | conf = truth) '.' {$conflicts = $conf.clause;})?
      ']'
  ;

postconditions returns [ArrayList<Terminator> terminators, ArrayList<Initiator> initiators, HashSet<String> facts]
  :   {$terminators = new ArrayList<Terminator>(); $initiators = new ArrayList<Initiator>(); $facts = new HashSet<String>();}
      ('Postconditions' | 'postconditions') '['
      (terminator '.' {$terminators.add($terminator.terminator); $facts.add($terminator.factName);}
      | initiator '.'{$initiators.add($initiator.initiator); $facts.add($initiator.factName);})*
      ']'
  ;

action returns [Action action, HashSet<String> facts, String actionName]
  :   {ArrayList<Terminator> terminators = new ArrayList<Terminator>();
      ArrayList<Initiator> initiators = new ArrayList<Initiator>();
      Clause conditions = null;
      Clause conflicts = null;
      $facts = new HashSet<String>();
      }
      simpleSentence '=' '{' {$actionName = $simpleSentence.simpleSentence.getName();}
      (preconditions {conditions = $preconditions.conditions; conflicts = $preconditions.conflicts;})?
      (postconditions {initiators = $postconditions.initiators; terminators = $postconditions.terminators; $facts = $postconditions.facts;})?
      '}'
      {$action = new Action($simpleSentence.simpleSentence, initiators, terminators, conditions, conflicts);}
      {this.variables = new HashMap<String, Variable>();}
  ;

macroaction returns [Rule macroaction]
  :   rule
      {$macroaction = $rule.rule;}
  ;

d returns [boolean w, DSet set, HashSet<String> facts, HashSet<String> actions]
  :   {$set = new DSet(); $w = true; ArrayList<Rule> macros = new ArrayList<Rule>();
      $facts = new HashSet<String>(); $actions = new HashSet<String>();}
      ('domain theory' | 'Domain Theory' | 'domainTheory' | 'DomainTheory' | 'dset' | 'Dset' | 'dSet' | 'DSet') '{'
      (action {$w = false; $set.addAction($action.action); $facts = $action.facts; $actions.add($action.actionName);})*
      (('macroactions' | 'Macroactions') '{'
      (macroaction {$w = false; macros.add($macroaction.macroaction);})*
      '}')?
      '}'
      {Database.getInstance().setdSet($set);}
      {Database.getInstance().addRulesDatabase(macros);}
  ;

database returns [boolean wlint, boolean wlext, boolean wdset, HashSet<String> facts]
  :   {$wlint =  true; $wlext = true; $wdset = true; $facts = new HashSet<String>();}
      ('database' | 'Database') '{'
      (lext {$wlext = $lext.w; $facts = $lext.facts;})?
      (lint {$wlint = $lint.w;})?
      '}'
  ;

reactiveRules returns [boolean w, ReactiveRuleSet set]
  :   {$set = ReactiveRuleSet.getInstance(); $w = true;}
      ('ReactiveRules' | 'reactiveRules' | 'reactiverules') '{'
      (reactiveRule {$set.addRule($reactiveRule.rule); $w = false;})*
      {this.variables = new HashMap<String, Variable>();}
      '}'
  ;

goals returns [boolean w, GoalSet set]
  :   {$set = new GoalSet(); $w = true;}
      ('Goals' | 'goals') '{'
      (rule {$set.addDefinition($rule.rule); $w = false;})*
      {this.variables = new HashMap<String, Variable>();}
      '}'
      {GoalsList.getInstance().setGoalsDefinitions($set);}
  ;

events returns [boolean w, RuleSet set]
  :   {$set = new RuleSet(); $w = true;}
      ('Events' | 'events') '{'
      (fluent {$set.addRule(new Rule($fluent.rule)); $w = false;})*
      {this.variables = new HashMap<String, Variable>();}
      '}'
      {CycleHandler.getInstance().setEvents($set);}
  ;

file returns [boolean[\] w, HashSet<String> facts, HashSet<String> actions]
  :   {$w = new boolean[6]; $w[0] = true; $w[1] = true; $w[2] = true; $w[3] = true; $w[4] = true; $w[5] = true;
      $facts = new HashSet<String>(); $actions = new HashSet<String>();}
      (database {$w[0] = $database.wlext; $w[1] = $database.wlint; $facts = $database.facts;})?
      (d {$w[2] = $d.w; $facts.addAll($d.facts); $actions = $d.actions;})?
      (reactiveRules {$w[3] = $reactiveRules.w;})?
      (goals {$w[4] = $goals.w;})?
      (events {$w[5] = $events.w;})?
      EOF
  ;

/**
*
* Tokens
*
**/
SYMBOL : ('<' | '>' | '<=' | '>=' | '==' | '!=' | '+' | '-');
CONSTANT : ('a'..'z' | '0'..'9') ('A'..'Z' | 'a'..'z' | '0'..'9' | '_' | '-' | '.')*;
VARIABLE : ('A'..'Z') ('A'..'Z' | 'a'..'z' | '0'..'9' | '_' | '.')*;
WS : (' ' | '\t' | '\n' | '\r' | '\f')+  {$channel = HIDDEN;};
COMMENT_LINE : '//' .* ('\n' | '\r') {$channel = HIDDEN;};
COMMENT : '/*' .* '*/' {$channel = HIDDEN;};
