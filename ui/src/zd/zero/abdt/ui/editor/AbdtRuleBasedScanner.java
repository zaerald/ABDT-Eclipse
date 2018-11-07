package zd.zero.abdt.ui.editor;

import java.util.Arrays;

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

import zd.zero.abdt.ui.AbdtUIConstants;

/**
 * @author Zaerald Denze Lungos
 *
 */
public class AbdtRuleBasedScanner extends RuleBasedScanner {

    public AbdtRuleBasedScanner( ColorManager colorManager ) {

        final String[] keywords = { "Start", "start", "End", "end", "var", "function" };

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

        Token keywordToken =
            new Token( new TextAttribute( colorManager.getColor( AbdtUIConstants.ABDT_KEYWORD ), null, SWT.BOLD ) );
        Token commentToken = new Token( new TextAttribute( colorManager.getColor( AbdtUIConstants.ABDT_COMMENT ) ) );
        Token stringToken = new Token( new TextAttribute( colorManager.getColor( AbdtUIConstants.ABDT_STRING ) ) );

        Arrays.asList( keywords ).forEach( keyword -> rule.addWord( keyword, keywordToken ) );

        setRules( new IRule[] { rule, new SingleLineRule( "#", null, commentToken ),
            new MultiLineRule( "#", "#", commentToken, (char) 0, true ),
            new SingleLineRule( "\"", "\"", stringToken, '\\' ), new SingleLineRule( "'", "'", stringToken, '\\' ),
            new WhitespaceRule( Character::isWhitespace ) } );
    }
}
