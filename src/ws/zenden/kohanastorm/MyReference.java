package ws.zenden.kohanastorm;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import org.apache.xmlbeans.XmlToken;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyReference implements PsiReference  {
        protected  PsiElement element;
        protected  TextRange textRange;
        protected  Project project;
        protected  String path;
        protected  VirtualFile appDir;

        public MyReference(String path, PsiElement element, TextRange textRange,  Project project, VirtualFile appDir ) {
            this.element = element;
            this.textRange = textRange;
            this.project = project;
            this.path = path;
            this.appDir = appDir;
        }

        @Override
        public String toString() {
            return getCanonicalText();
        }

        public PsiElement getElement() {
            return this.element ;
        }

        public TextRange getRangeInElement() {
            return  textRange;
        }

        public PsiElement handleElementRename(String newElementName)
                throws IncorrectOperationException {
            // TODO: Implement this method
            throw new IncorrectOperationException();
        }

        public PsiElement bindToElement(PsiElement element) throws IncorrectOperationException {
            // TODO: Implement this method
            throw new IncorrectOperationException();
        }

        public boolean isReferenceTo(PsiElement element) {
            return resolve() == element;
        }

        public Object[] getVariants() {
            // TODO: Implement this method
            return new Object[0];
        }

        public boolean isSoft() {
            return false;
        }


    @Nullable
    public PsiElement resolve() {
        String uri = path;
        String dir = "views/";
        if ( path.startsWith("smarty:") && !path.endsWith(".tpl")) {
            path = path.replace("smarty:","");
            path += ".tpl";
        }
        VirtualFile targetFile = appDir.findFileByRelativePath( dir + path );

        if (targetFile == null) {
            path += ".php";
            targetFile = appDir.findFileByRelativePath(dir + path);

        }

        if ( targetFile != null ){
            return  PsiManager.getInstance(project).findFile( targetFile);
        }

        return null;
    }

    @Override
    public String getCanonicalText() {
        return  path;
    }
}
