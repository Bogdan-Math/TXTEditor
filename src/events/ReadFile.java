package events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import main.MainFrame;
import panels.PathPanel;

public class ReadFile implements ActionListener {

	private JTextArea area;

	@Override
	public void actionPerformed(ActionEvent arg0) {

		area = MainFrame.mainPanel.textAreaPanel.textArea;
		String path = MainFrame.mainPanel.namePanel.textField.getText();

		if (!path.equals("") && new File(path).isFile()) {
			fileInArea(area, path);
			PathPanel.sayFileOpened();
		} else {
			JFileChooser fileopen = new JFileChooser();
			int ret = fileopen.showDialog(null, "Open file");
			if (ret == JFileChooser.APPROVE_OPTION) {

				path = fileopen.getSelectedFile().getAbsolutePath();
				fileInArea(area, path);

				MainFrame.mainPanel.namePanel.textField.setText(path);
				PathPanel.sayFileOpened();
			}
		}
	}

	private void fileInArea(JTextArea area, String path) {
		try {
			FileReader reader = new FileReader(path);
			BufferedReader br = new BufferedReader(reader);
			area.read(br, null);
			br.close();
		} catch (Exception e2) {
		}
	}
}
