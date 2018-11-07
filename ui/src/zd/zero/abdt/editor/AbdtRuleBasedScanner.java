package zd.zero.abdt.editor;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * @author Zaerald Denze Lungos
 *
 */
public class AbdtRuleBasedScanner extends RuleBasedScanner {

    public AbdtRuleBasedScanner() {
        WordRule rule = new WordRule( new IWordDetector() {
            @Override
            public boolean isWordStart( char c ) {
                return Character.isJavaIdentifierStart( c );
            }

            @Override
            public boolean isWordPart( char c ) {
                return Character.isJavaIdentifierPart( c );
            }
        } );

        Token keyword = new Token( new TextAttribute( new Color( Display.getCurrent(), 255, 0, 0 ), null, SWT.BOLD ) );
        Token comment = new Token( new TextAttribute( new Color( Display.getCurrent(), 0, 255, 0 ) ) );
        Token string = new Token( new TextAttribute( new Color( Display.getCurrent(), 0, 0, 255 ) ) );


        rule.addWord( "Start", keyword );
        rule.addWord( "start", keyword );
        rule.addWord( "End", keyword );
        rule.addWord( "end", keyword );
        rule.addWord( "var", keyword );
        rule.addWord( "function", keyword );


        setRules( new IRule[] { rule, new SingleLineRule( "#", null, comment ),
            new MultiLineRule( "#", "#", comment, (char) 0, true ), new SingleLineRule( "\"", "\"", string, '\\' ),
            new SingleLineRule( "'", "'", string, '\\' ), new WhitespaceRule( Character::isWhitespace ) } );
    }
}
