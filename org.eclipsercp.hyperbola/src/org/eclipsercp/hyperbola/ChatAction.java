package org.eclipsercp.hyperbola;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.hyperbola.model.ContactsEntry;

public class ChatAction extends Action implements ISelectionListener,
		IWorkbenchAction {
	
	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.hyperbola.chat";
	private IStructuredSelection selection;
	

	public ChatAction(IWorkbenchWindow window){
		this.window = window;
		setId(ID);
		setText("&Chat");
		setToolTipText("Chat with the slected contact.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.CHAT));
		window.getSelectionService().addSelectionListener(this);
	}
	
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);

	}

	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if(incoming instanceof IStructuredSelection){
			selection = (IStructuredSelection) incoming;
			setEnabled(selection.size() == 1 && selection.getFirstElement() instanceof ContactsEntry); // true if - One selected, that on is a Contact.
		} else {
			setEnabled(false);
		}
	}
	
	public void run() {
		Object item = selection.getFirstElement();
		ContactsEntry entry = (ContactsEntry) item;
		IWorkbenchPage page = window.getActivePage();
		ChatEditorInput input  = new ChatEditorInput(entry.getName());
		try {
			page.openEditor(input, ChatEditor.ID);
		} catch (PartInitException e){
			System.out.println("P_LOG " + e.getMessage());
		}
	}

}
