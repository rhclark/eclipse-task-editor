/*******************************************************************************
 * Copyright (c) 2011 Sebastian Benz.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Sebastian Benz - initial API and implementation
 ******************************************************************************/
/*
* generated by Xtext
*/
package de.sebastianbenz.task.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

import com.google.inject.Inject;

import de.sebastianbenz.task.Tag;
import de.sebastianbenz.task.impl.CodeImplCustom;
import de.sebastianbenz.task.tagging.TagProvider;
import de.sebastianbenz.task.tagging.Tags;
import de.sebastianbenz.task.ui.highlighting.BrushRegistry;

/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#contentAssist on how to customize content assistant
 */
public class TaskProposalProvider extends AbstractTaskProposalProvider {

	@Inject
	private TagProvider tagProvider;
	
	@Inject
	private BrushRegistry brushRegistry;
	
	protected void proposeTags(EObject model, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		Tag done = Tags.from("done");
		createProposal(model, context, acceptor, done);
		for (Tag tag : tagProvider.in(model.eResource())) {
			createProposal(model, context, acceptor, tag);
		}
	}
	
	@Override
	public void completeNote_Intend(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		proposeTags(model, context, acceptor);
	}
	
	@Override
	public void completeTask_Intend(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		proposeTags(model, context, acceptor);
	}
	
	@Override
	public void completeProject_Intend(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		proposeTags(model, context, acceptor);
	}
	
	@Override
	public void completeNote_Text(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		proposeTags(model, context, acceptor);
	}
	
	@Override
	public void complete_TASK_TEXT(EObject model, RuleCall ruleCall,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		proposeTags(model, context, acceptor);
	}
	
	@Override
	public void completeProject_Text(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		proposeTags(model, context, acceptor);
	}
	
	@Override
	public void complete_Code(EObject model, RuleCall ruleCall,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		if(!context.getPrefix().trim().equals(CodeImplCustom.PREFIX)){
			return;
		}
		for (String key : brushRegistry.getLanguages()) {
			if(!key.equals(BrushRegistry.DEFAULT_CONFIGURATION)){
				acceptor.accept(createCompletionProposal(context.getPrefix() + key, new StyledString(key), null, 1000, context.getPrefix(), context));
			}
		}
	}
	
	protected boolean isValidProposal(String proposal, String prefix, ContentAssistContext context) {
		if (proposal == null)
			return false;
		if (!context.getMatcher().isCandidateMatchingPrefix(proposal, prefix))
			return false;
		return true;
	}

	protected void createProposal(EObject model, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor, Tag tag) {
		String prefix = context.getPrefix();
		if(!prefix.equals("") && !prefix.contains("@" + tag.getName())){
			acceptor.accept(
					createCompletionProposal(
							prefix + tag,
							getStyledDisplayString(model, tag.getName(), tag.getName()), 
							getLabelProvider().getImage(tag), 
							0, 
							"", 
							context));
		}
	}
	
}
