package zd.zero.abdt.editor;

import org.eclipse.ui.editors.text.TextEditor;

/**
 * @author Zaerald Denze Lungos
 *
 */
public class AbdtEditor extends TextEditor {

    public AbdtEditor() {
        setSourceViewerConfiguration( new AbdtSourceViewerConfiguration() );
    }

}
