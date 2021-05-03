package CECS277_Project;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.List;
import java.awt.dnd.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class FilePanel extends JPanel {
	private static final long serialVersionUID = 4085105696717794672L;
	private JScrollPane scrollPane = new JScrollPane();
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JList<String> myList = new JList<>();
	private File[] fileList;
	private File selectedFile, selectedDirectory, copiedFile;
	private Boolean showDetails;
	private JPopupMenu popMenu;

	public FilePanel() {
		showDetails = false;
		scrollPane.setViewportView(myList);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
		myList.setModel(model);
		this.setDropTarget(new MyDropTarget());
		myList.setDragEnabled(true);
		myList.addMouseListener(new mouseListener());
		myList.addKeyListener(new keyListener());
		add(scrollPane);
	}
	

	public void FillList(File file) {
		selectedDirectory = file;
		fileList = file.listFiles();
		File[] sorted = new File[fileList.length];
		int counter = 0;
		for (int i = 0; i < fileList.length; i++) { //Add folders First
			if (fileList[i].isDirectory()) {
				sorted[counter] = fileList[i];
				counter++;// Only increment counter when something is added
			}
		}
		for (int i = 0; i < fileList.length; i++) { //Add files last
			if (!fileList[i].isDirectory()) {
				sorted[counter] = fileList[i];
				counter++;// Only increment counter when something is added
			}
		}
		fileList = sorted;
		model.clear();
		myList.removeAll();
		myList.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
		
		if (showDetails) {
			for (File subfile : sorted) {
				if (!subfile.isDirectory()) {
					model.addElement(getDetails(subfile));
				} else {
					model.addElement(subfile.getName());
				}
			}
		} else {
			for (File subfile : sorted) {
				model.addElement(subfile.getName());
			}
		}
	}
	

	/*******************************/
	/*******FILE OPERATIONS*********/
	/*******************************/
	
	public void deleteFile() {
		if (selectedFile.delete()) {
			System.out.println("Deleted the file: " + selectedFile.getName());
		} else {
			System.out.println("Failed to delete the file.");
		}
		FillList(selectedDirectory);
	}

	public void runFile(File file) {
		Desktop desktop = Desktop.getDesktop();
		if (file.exists())
			try {
				desktop.open(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	
	// Must take in an entire pathname to function
	public void renameFile(String pathname) {
		File newFile = new File(pathname);
		if (selectedFile.renameTo(newFile)) {
			System.out.println("File succesfully renamed to: " + newFile.getName());
		} else {
			System.out.println("Something went wrong renaming the file.");
		}
		FillList(selectedDirectory);
	}
	
	public void pasteFile(String s) throws IOException {
        /*
         * Copies the copiedFile into a newly created File
         * determined by String s. This will typically be
         * the selectedDirectory+"\\"+File(s).getName()
         */
        File newFile = new File(s);
        try {
            FileInputStream ins = new FileInputStream(copiedFile);
            FileOutputStream outs = new FileOutputStream(newFile);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = ins.read(buffer)) > 0) {
                outs.write(buffer, 0, length);
            }
            ins.close();
            outs.close();

        } catch (FileNotFoundException ioe) {
            ioe.printStackTrace();
        }
    }
	
	/*******************************/
	/*************GET&SET***********/
	/*******************************/
	
	
	public File getFile() {
		return selectedFile;
	}

	public JList<String> getmyList() {
		return myList;
	}

	public File[] getfileList() {
		return fileList;
	}

	private String getDetails(File file) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return String.format("%-40s %-20s %20s", file.getName(), (file.length()) + "B",
				sdf.format(file.lastModified()));
	}

	public void setShowDetails(Boolean toggle) {
		showDetails = toggle;
		FillList(selectedDirectory); // Redraw the list after changing the boolean
	}

	public File setCopiedFile(File file) {
		copiedFile = file;
		System.out.println("File copied successfully." + file.getName());
		return copiedFile;
	}

	public void setSelectedFile(File f) {
		selectedFile = f;
	}
	
	
	/*******************************/
	/**********POPUP CANCER ********/
	/*******************************/
	
	
	public void buildpopMenu() {
		popMenu = new JPopupMenu();
		JMenuItem rename = new JMenuItem("Rename");// Complete
		rename.addActionListener(new popRename());

		JMenuItem copy = new JMenuItem("Copy");// I think Complete
		copy.addActionListener(new popCopy());

		JMenuItem paste = new JMenuItem("Paste");// Need some Clarifiaction
		paste.addActionListener(new popPaste());

		JMenuItem delete = new JMenuItem("delete");
		delete.addActionListener(new popDelete());

		popMenu.add(rename);
		popMenu.add(copy);
		popMenu.add(paste);
		popMenu.addSeparator();
		popMenu.add(delete);
		setSize(200, 400);
		setVisible(true);
	}

	private class popRename implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			DataBack dbdlg = new DataBack();
			dbdlg.setFileNameTextField(getFile().getPath());
			dbdlg.setVisible(true);
			String newFile = dbdlg.getFileNameTextField();
			renameFile(newFile);
		}
	}

	private class popCopy implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setCopiedFile(getFile());
			System.out.println("Copy File is set to: " + copiedFile.getName());
		}
	}

	private class popPaste implements ActionListener {// NEEDS TO BE FIXED
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				System.out.println("File was succesfully pasted");
				pasteFile(selectedDirectory.getPath() + "\\" + copiedFile.getName());
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	private class popDelete implements ActionListener {// NEEDS TO BE FIXED
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Delete: " + getFile());
			deleteDLG dlg = new deleteDLG();
			dlg.setFileNameTextField(getFile().getPath());
			dlg.setVisible(true);
			if (dlg.onOK()) {
				deleteFile();
			}
		}
	}

	
	/*******************************/
	/********** LISTENERS **********/
	/*******************************/

	
	private class mouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent me) {
			if (me.getClickCount() == 1 && (me.getButton() == 1) && myList.locationToIndex(me.getPoint()) != -1) {
				selectedFile = fileList[myList.getSelectedIndex()];
				System.out.println("Selected file is: " + selectedFile.getName());
			}
			// SOMEHOW GET THIS SHIT TO WORK IN APP
			if (me.getClickCount() == 1 && (me.getButton() == 3) && myList.locationToIndex(me.getPoint()) != -1) {
				myList.setSelectedIndex(myList.locationToIndex(me.getPoint()));
				selectedFile = fileList[myList.locationToIndex(me.getPoint())];
				System.out.println("We right clicked on " + selectedFile.getName());
				System.out.println("Selected file is: " + selectedFile.getName());
				buildpopMenu();
				popMenu.show(me.getComponent(), me.getX(), me.getY());
			}
			if (me.getClickCount() == 2 && (me.getButton() == 1) && myList.locationToIndex(me.getPoint()) != -1) {
				selectedFile = fileList[myList.getSelectedIndex()];
				runFile(selectedFile);
			}
		}
	}

	private class keyListener extends KeyAdapter {
		public void keyReleased(KeyEvent ke) {
			if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
				runFile(new File(myList.getSelectedValue()));
			}
		}
	}

	/******************************/
	/******** DRAG AND DROP *******/
	/**Thanks 4 this 1 professor***/
	/******************************/

	class MyDropTarget extends DropTarget {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2681176772640578293L;

		/**************************************************************************
		 *
		 * @param evt the event that caused this drop operation to be invoked
		 */
		@SuppressWarnings("unchecked")
		public void drop(DropTargetDropEvent evt) {
			try {
				evt.acceptDrop(DnDConstants.ACTION_COPY);
				List<Object> result = new ArrayList<Object>();
				//If dragging from an external Source
				if (evt.getTransferable().isDataFlavorSupported(DataFlavor.stringFlavor)) {
					String temp = (String) evt.getTransferable().getTransferData(DataFlavor.stringFlavor);
					String[] next = temp.split("\\n");
					// add the strings to the listmodel
					for (int i = 0; i < next.length; i++) {
						model.addElement(next[i]);
						setCopiedFile(new File(next[i]));
						File pastedFile = new File(selectedDirectory.getPath() + "\\" + copiedFile.getName());
						pasteFile(pastedFile.getPath());
					}
				} else { 
					//If dragging from an internal Frame.
					result = (List<Object>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
					for (Object o : result) {
						model.addElement(o.toString());
						setCopiedFile(new File(o.toString()));
						File pastedFile = new File(selectedDirectory.getPath() + "\\" + copiedFile.getName());
						pasteFile(pastedFile.getPath());
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}