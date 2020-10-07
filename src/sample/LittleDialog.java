//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package sample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.undo.UndoManager;

public class LittleDialog extends JFrame {
    private JPanel panel = null;
    private JScrollPane jsp = null;
    private JTextArea textarea = null;
    private JPanel southPanel = null;
    private JTextField text = null;
    private JButton search = null;
    private String fileName = null;
    private String fileContent = null;
    private UndoManager um = null;

    public LittleDialog(String fileName, String fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.init();
    }

    private void init() {
        this.setTitle(this.fileName);
        this.um = new UndoManager();
        this.panel = new JPanel();
        this.textarea = new JTextArea();
        this.textarea.setText(this.fileContent);
        this.textarea.setSelectionColor(Color.BLUE);
        this.textarea.getDocument().addUndoableEditListener(this.um);
        this.jsp = new JScrollPane(this.textarea) {
            public void paint(Graphics g) {
                super.paint(g);
                LittleDialog.this.repaint();
            }

            public boolean getScrollableTracksViewportWidth() {
                return false;
            }

            public void setSize(Dimension d) {
                if (d.width < this.getParent().getSize().width) {
                    d.width = this.getParent().getSize().width;
                }

                d.width += 100;
                super.setSize(d);
            }
        };
        this.southPanel = new JPanel();
        this.text = new JTextField(10);
        this.search = new JButton("查找");
        this.search.addActionListener((e) -> {
            String word = this.text.getText().trim();
            if (!word.equals("")) {
                int numx = this.textarea.getCaretPosition();
                int i = this.textarea.getText().indexOf(word, numx);
                if (i >= 0) {
                    this.textarea.setSelectionStart(i);
                    this.textarea.setSelectionEnd(i + word.length());
                    this.textarea.requestFocus();
                } else {
                    int num = 0;
                    int j = this.textarea.getText().indexOf(word, num);
                    if (j < 0) {
                        JOptionPane.showMessageDialog((Component)null, "没有查du找zhi到" + word, "查找", 2);
                        return;
                    }

                    this.textarea.setSelectionStart(j);
                    this.textarea.setSelectionEnd(j + word.length());
                    this.textarea.requestFocus();
                }
            }

            System.out.println(this.textarea.getSelectedText());
        });
        this.southPanel.setLayout(new FlowLayout());
        this.southPanel.add(this.text);
        this.southPanel.add(this.search);
        this.panel.setLayout(new BorderLayout());
        this.panel.add(this.jsp, "Center");
        this.panel.add(this.southPanel, "South");
        this.setContentPane(this.panel);
        this.setLocationRelativeTo((Component)null);
        this.setLocation(200, 200);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(2);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LittleDialog("ss", "dsaoflsafdsaf");
    }
}
