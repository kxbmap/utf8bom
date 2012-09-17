package com.github.kxbmap.intellij.utf8bom;

import javax.swing.*;

public class ProjectSettingPanelForm {
    private JPanel myPanel;
    private JPanel contents;
    private JCheckBox enableOnSave;
    private JTextField filePatternsText;

    JPanel getMyPanel() {
        return myPanel;
    }

    protected JCheckBox getEnableOnSave() {
        return enableOnSave;
    }

    protected JTextField getFilePatternsText() {
        return filePatternsText;
    }

    public JPanel getContents() {
        return contents;
    }
}
