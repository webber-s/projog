package org.projog.core.function.io;

import static org.projog.core.term.TermUtils.getAtomName;

import org.projog.core.FileHandles;
import org.projog.core.ProjogException;
import org.projog.core.function.AbstractSingletonPredicate;
import org.projog.core.term.Term;

/* TEST
 %LINK prolog-io
 */
/**
 * <code>open(X,Y,Z)</code> - opens a file.
 * <p>
 * <code>X</code> is an atom representing the name of the file to open. <code>Y</code> is an atom that should have
 * either the value <code>read</code> to open the file for reading from or <code>write</code> to open the file for
 * writing to. <code>Z</code> is instantiated by <code>open</code> to a special term that must be referred to in
 * subsequent commands in order to access the stream.
 * </p>
 */
public final class Open extends AbstractSingletonPredicate {
   private static final String READ = "read";
   private static final String WRITE = "write";

   @Override
   public boolean evaluate(Term fileNameAtom, Term operationAtom, Term variableToAssignTo) {
      String operation = getAtomName(operationAtom);
      String fileName = getAtomName(fileNameAtom);
      Term handle;
      if (READ.equals(operation)) {
         handle = openInput(fileName);
      } else if (WRITE.equals(operation)) {
         handle = openOutput(fileName);
      } else {
         throw new ProjogException("Second argument is not '" + READ + "' or '" + WRITE + "' but: " + operation);
      }
      variableToAssignTo.unify(handle);
      return true;
   }

   private Term openInput(String fileName) {
      try {
         FileHandles fh = getKnowledgeBase().getFileHandles();
         return fh.openInput(fileName);
      } catch (Exception e) {
         throw new ProjogException("Unable to open input for: " + fileName, e);
      }
   }

   private Term openOutput(String fileName) {
      try {
         FileHandles fh = getKnowledgeBase().getFileHandles();
         return fh.openOutput(fileName);
      } catch (Exception e) {
         throw new ProjogException("Unable to open output for: " + fileName + " " + e, e);
      }
   }
}