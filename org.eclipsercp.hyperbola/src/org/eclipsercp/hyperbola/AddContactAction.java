package org.eclipsercp.hyperbola;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.hyperbola.model.ContactsEntry;
import org.eclipsercp.hyperbola.model.ContactsGroup;

public class AddContactAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction {
	private final IWorkbenchWindow window;
	public final static String ID = "org.exlipsercp.hyperbola.addContact";
	private IStructuredSelection selection;

	public AddContactAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText(ID);
		setText("&Add a contract to Your contacts list.");
		//setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.hyperbola", IImageKeys.ADD_CONTACT));
		window.getSelectionService().addSelectionListener(this);
	}

	public void dispose() {
		window.getSelectionService().addSelectionListener(this);

		// TODO Auto-generated method stub

	}

	public void selectionChanged(IWorkbenchPart part, ISelection incomming) {
		if (incomming instanceof IStructuredSelection) {
			selection = (IStructuredSelection) incomming;
			setEnabled(selection.size() == 1 && selection.getFirstElement() instanceof ContactsGroup);
		} else
			setEnabled(false);

	}
	
	public void run() {
		AddContactDialog d = new AddContactDialog(window.getShell());
		int code = d.open();
		if(code == Window.OK){
			Object item = selection.getFirstElement();
			ContactsGroup group = (ContactsGroup) item;
			ContactsEntry entry = new ContactsEntry(group, d.getNameText(), d.getNickname(), d.getServerText());
			group.addEntry(entry);
			
		}
		
	}

}
