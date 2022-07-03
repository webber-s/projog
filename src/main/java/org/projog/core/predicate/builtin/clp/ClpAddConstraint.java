/*
 * Copyright 2022 S. Webber
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.projog.core.predicate.builtin.clp;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

import org.projog.clp.Constraint;
import org.projog.clp.EqualTo;
import org.projog.clp.Expression;
import org.projog.clp.LessThan;
import org.projog.clp.LessThanOrEqualTo;
import org.projog.clp.NotEqualTo;
import org.projog.core.kb.KnowledgeBaseServiceLocator;
import org.projog.core.predicate.AbstractSingleResultPredicate;
import org.projog.core.term.Term;

/* TEST
%?- X in 7..9, X#=8
% X=8
%?- X in 7..9, X#\=8
% X={7, 9}
%?- X in 7..9, X#>8
% X=9
%?- X in 7..9, X#>=8
% X=8..9
%?- X in 7..9, X#<8
% X=7
%?- X in 7..9, X#=<8
% X=7..8

%?- 16#=X+7
% X=9

%?- 16#=X-7
% X=23

% TODO shouldn't need X#>0
%?- X#>0, 16#=X*8
% X=2

%FAIL X #> 0, 16#=X*7

%FAIL 7#=8
%TRUE 8#=8
%FAIL 9#=8
%TRUE 7#\=8
%FAIL 8#\=8
%TRUE 9#\=8
%FAIL 7#>8
%FAIL 8#>8
%TRUE 9#>8
%FAIL 7#>=8
%TRUE 8#>=8
%TRUE 9#>=8
%TRUE 7#<8
%FAIL 8#<8
%FAIL 9#<8
%TRUE 7#=<8
%TRUE 8#=<8
%FAIL 9#=<8

%?- X#=a
%ERROR Cannot find CLP expression: a/0
%?- X#=1/2
%ERROR Cannot find CLP expression: //2
*/
/**
 * CLP comparison predicates.
 * <p>
 * <ul>
 * <li><code>#=</code> equal to</li>
 * <li><code>#\=</code> not equal to</li>
 * <li><code>#&gt;</code> greater than</li>
 * <li><code>#&gt;=</code> greater than or equal to</li>
 * <li><code>#&lt;</code> less than</li>
 * <li><code>#=&lt;</code> less than or equal to</li>
 * </ul>
 */
public final class ClpAddConstraint extends AbstractSingleResultPredicate {
   public static ClpAddConstraint equalTo() {
      return new ClpAddConstraint(EqualTo::new);
   }

   public static ClpAddConstraint notEqualTo() {
      return new ClpAddConstraint(NotEqualTo::new);
   }

   public static ClpAddConstraint lessThan() {
      return new ClpAddConstraint(LessThan::new);
   }

   public static ClpAddConstraint greaterThan() {
      return new ClpAddConstraint((left, right) -> new LessThan(right, left));
   }

   public static ClpAddConstraint lessThanOrEqualTo() {
      return new ClpAddConstraint(LessThanOrEqualTo::new);
   }

   public static ClpAddConstraint greaterThanOrEqualTo() {
      return new ClpAddConstraint((left, right) -> new LessThanOrEqualTo(right, left));
   }

   private final BiFunction<Expression, Expression, Constraint> constraintGenerator;
   private ClpExpressions expressions;

   private ClpAddConstraint(BiFunction<Expression, Expression, Constraint> constraintGenerator) {
      this.constraintGenerator = constraintGenerator;
   }

   @Override
   public boolean evaluate(Term x, Term y) {
      Set<ClpVariable> vars = new HashSet<>();
      Expression left = expressions.toExpression(x, vars);
      Expression right = expressions.toExpression(y, vars);
      Constraint rule = constraintGenerator.apply(left, right);
      for (ClpVariable c : vars) {
         c.addConstraint(rule);
      }
      return new CoreConstraintStore(rule).resolve();
   }

   @Override
   protected void init() {
      expressions = KnowledgeBaseServiceLocator.getServiceLocator(getKnowledgeBase()).getInstance(ClpExpressions.class);
   }
}
