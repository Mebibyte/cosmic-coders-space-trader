// $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.constructorsOnlyInvokeFinalMethods
/* Comment
 * 
 */

package edu.gatech.spacetrader.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import edu.gatech.spacetrader.main.GamePanel;
import edu.gatech.spacetrader.screens.ConfigScreen;

/**
 * @author Glenn
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TextFieldFrame extends JFrame {
    /**
     * Field ALPHA_NUMERIC. Contains all alphanumeric characters and space.
     */
    public static final String ALPHA_NUMERIC = " abcdefghijklmnopqrstuvwxyz"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * Field screen.
     */
    private final ConfigScreen screen;

    /**
     * Field panel.
     */
    private final JPanel panel;

    /**
     * Field field.
     */
    private final JTextField field;

    /**
     * Field button.
     */
    private static final JButton BUTTON = new JButton("Submit");

    /**
     * Constructor for TextFieldFrame.
     * 
     * @param screen
     *            ConfigScreen
     * @param limit
     *            int
     * @param gPanel
     *            GamePanel
     * @param name
     *            String
     */
    public TextFieldFrame(ConfigScreen screen, GamePanel gPanel, int limit,
            String name) {
        super("Edit Name");
        setResizable(false);

        this.screen = screen;
        panel = new JPanel();
        field = new JTextField(new MaxLengthDocument(limit), name, limit + 2); // $codepro.audit.disable
                                                                               // numericLiterals
        panel.add(field);
        BUTTON.addActionListener(new SubmitListener());
        panel.add(BUTTON);
        add(panel);

        pack();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(gPanel);
        setVisible(true);
    }

    /**
     * @author Glenn
     * @version 1.0
     */
    private static class MaxLengthDocument extends PlainDocument {
        /**
         * Field limit.
         */
        private final int limit;

        /**
         * Constructor for MaxLengthDocument.
         * 
         * @param limit
         *            int
         */
        private MaxLengthDocument(int limit) {
            this.limit = limit;
        }

        /**
         * Method insertString.
         * 
         * @param offset
         *            int
         * @param str
         *            String
         * @param attr
         *            AttributeSet
         * @throws BadLocationException
         * @see javax.swing.text.Document#insertString(int, String,
         *      AttributeSet)
         */
        public void insertString(int offset, String str, AttributeSet attr)
                throws BadLocationException {
            if (str == null) {
                return;
            }

            for (int i = 0; i < str.length(); i++) {
                if (ALPHA_NUMERIC.indexOf(String.valueOf(str.charAt(i))) == -1) {
                    return;
                }
            }

            super.insertString(offset, str, attr);

            if (getLength() > limit) {
                super.remove(limit, getLength() - limit);
            }

            if (getLength() > 0) {
                BUTTON.setEnabled(true);
            }
        }

        /**
         * Method removeUpdate
         * 
         * @param chng
         *            DefaultDocumentEvent
         */
        protected void removeUpdate(AbstractDocument.DefaultDocumentEvent chng) {
            if (chng.getLength() == getLength()) {
                BUTTON.setEnabled(false);
            }
        }

        /**
         * Method toString.
         * 
        
         * @return String */
        public String toString() {
            return "MaxLengthDocument";
        }
    }

    /**
     * @author Glenn
     * @version 1.0
     */
    private class SubmitListener implements ActionListener {
        /**
         * Method actionPerformed.
         * 
         * @param event
         *            ActionEvent
        
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent) */
        @Override
        public void actionPerformed(ActionEvent event) {
            screen.changeName(field.getText());
        }

        /**
         * Method toString.
         * 
        
         * @return String */
        @Override
        public String toString() {
            return "SubmitListener";
        }
    }

    /**
     * Method resetFocus. Resets focus to the text field.
     */
    public void resetFocus() {
        field.requestFocusInWindow(); // $codepro.audit.disable
                                      // com.instantiations.assist.eclipse.analysis.unusedReturnValue
    }
}