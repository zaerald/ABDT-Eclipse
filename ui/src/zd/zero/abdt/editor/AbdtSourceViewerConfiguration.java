package zd.zero.abdt.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

/**
 * @author Zaerald Denze Lungos
 *
 */
public class AbdtSourceViewerConfiguration extends SourceViewerConfiguration {

    @Override
    public IPresentationReconciler getPresentationReconciler( ISourceViewer sourceViewer ) {
        PresentationReconciler presentationReconciler = new PresentationReconciler();
        DefaultDamagerRepairer damageRepairer = new DefaultDamagerRepairer( new AbdtRuleBasedScanner() );
        presentationReconciler.setRepairer( damageRepairer, IDocument.DEFAULT_CONTENT_TYPE );
        presentationReconciler.setDamager( damageRepairer, IDocument.DEFAULT_CONTENT_TYPE );

        return presentationReconciler;
    }

}
