/*
 * Copyright 2020 S. Webber
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
package org.projog.api;

import static org.projog.core.KnowledgeBaseUtils.getOperands;

import org.projog.core.KnowledgeBase;
import org.projog.core.PredicateFactory;
import org.projog.core.ProjogException;
import org.projog.core.parser.ParserException;
import org.projog.core.parser.SentenceParser;
import org.projog.core.term.Term;

public class QueryPlan {
   private final PredicateFactory predicateFactory;
   private final Term parsedInput;

   QueryPlan(KnowledgeBase kb, String prologQuery) {
      try {
         SentenceParser sp = SentenceParser.getInstance(prologQuery, getOperands(kb));

         this.parsedInput = sp.parseSentence();
         this.predicateFactory = kb.getPredicates().getPreprocessedPredicateFactory(parsedInput);

         if (sp.parseSentence() != null) {
            throw new ProjogException("More input found after . in " + prologQuery);
         }
      } catch (ParserException pe) {
         throw pe;
      } catch (Exception e) {
         throw new ProjogException(e.getClass().getName() + " caught parsing: " + prologQuery, e);
      }
   }

   public QueryStatement createStatement() {
      return new QueryStatement(predicateFactory, parsedInput);
   }
}
