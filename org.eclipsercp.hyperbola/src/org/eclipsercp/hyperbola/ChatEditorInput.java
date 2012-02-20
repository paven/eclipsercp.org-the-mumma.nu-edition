package org.eclipsercp.hyperbola;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class ChatEditorInput implements IEditorInput {
	private String participant;

	public ChatEditorInput(String participant) {
		super();
		Assert.isNotNull(participant);
		this.participant = participant;
	}

	public boolean exists() {
		return false;
	}

	public String getToolTipText() {
		return participant;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return participant;
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public boolean equals(Object obj){
		if(super.equals(obj)) return true;
		if(!(obj instanceof ChatEditorInput))
			return false;
		ChatEditorInput other = (ChatEditorInput) obj;
		return participant.equals(other.participant);
	}
	public int hashCode() {
		return participant.hashCode();
	}

	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
