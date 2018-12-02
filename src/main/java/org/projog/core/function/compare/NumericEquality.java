/*
 * Copyright 2013 S. Webber
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
package org.projog.core.function.compare;

import org.projog.core.term.Term;

/* TEST
 %TRUE 1=:=1
 %TRUE 1.5=:=3.0/2.0
 %TRUE 6*6=:=9*4
 %FALSE 1=:=2
 %FALSE 1+1=:=1-1
 %QUERY X=1, Y=1, X=:=Y
 %ANSWER
 % X=1
 % Y=1
 %ANSWER
 %FALSE X=1, Y=2, X=:=Y

 %TRUE 7.0=:=7.0
 %TRUE 7=:=7.0
 %TRUE 7.0=:=7
 %TRUE 7=:=7
 %TRUE 7.1=:=7.1
 %FALSE 7.0=:=7.1
 %FALSE 7.1=:=7.2
 %FALSE 7.2=:=7.1
 %FALSE 7.1=:=7
 %FALSE 7=:=7.1
 %TRUE -7=:=-7
 %FALSE 7=:=-7
 %FALSE -7=:=7

 %FALSE 9223372036854775806 =:= 9223372036854775807
 %FALSE 9223372036854775807 =:= 9223372036854775806
 %TRUE 9223372036854775807 =:= 9223372036854775807
 %TRUE 9223372036854775806 =:= 9223372036854775806

 %FALSE -9223372036854775808 =:= -9223372036854775807
 %FALSE -9223372036854775807 =:= -9223372036854775808
 %TRUE -9223372036854775808 =:= -9223372036854775808
 %TRUE -9223372036854775807 =:= -9223372036854775807

 % Note that due to loss of precision when comparing decimal fractions the following queries evaluate to true:
 %TRUE 9223372036854775806 =:= 9223372036854775807.0
 %TRUE 9223372036854775806.0 =:= 9223372036854775807
 %TRUE 9223372036854775806.0 =:= 9223372036854775807.0
 */
/**
 * <code>X=:=Y</code> - numeric equality test.
 * <p>
 * Succeeds when the number argument <code>X</code> is equal to the number argument <code>Y</code>.
 * </p>
 */
public final class NumericEquality extends AbstractNumericComparisonPredicate {
   @Override
   public boolean evaluate(Term arg1, Term arg2) {
      return compare(arg1, arg2) == 0;
   }
}
