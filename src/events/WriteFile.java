package events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import main.MainFrame;
import panels.PathPanel;

public class WriteFile implements ActionListener {

	private JTextArea area;

	@Override
	public void actionPerformed(ActionEvent e) {

		area = MainFrame.mainPanel.textAreaPanel.textArea;
		String path = MainFrame.mainPanel.namePanel.textField.getText();

		if (!path.equals("") && new File(path).isFile()) {
			areaInFile(area, path);
			PathPanel.sayFileSaved();
		} else {
			JFileChooser fileopen = new JFileChooser();
			int ret = fileopen.showDialog(null, "Save in file");
			if (ret == JFileChooser.APPROVE_OPTION) {

				path = fileopen.getSelectedFile().getAbsolutePath();
				areaInFile(area, path);

				MainFrame.mainPanel.namePanel.textField.setText(path);
				PathPanel.sayFileSaved();
			}
		}
	}

	private void areaInFile(JTextArea area, String path) {
		try {
			FileWriter writer = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(writer);
			area.write(bw);
			bw.close();
		} catch (Exception e2) {
		}

	}
}
