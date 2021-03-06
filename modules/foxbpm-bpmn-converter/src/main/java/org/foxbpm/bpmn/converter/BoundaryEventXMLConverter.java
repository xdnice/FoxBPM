/**
 * Copyright 1996-2014 FoxBPM ORG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author yangguangftlp
 */
package org.foxbpm.bpmn.converter;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.foxbpm.bpmn.constants.BpmnXMLConstants;
import org.foxbpm.model.BaseElement;
import org.foxbpm.model.BoundaryEvent;
import org.foxbpm.model.FlowElement;

/**
 * 边界世界节点转换处理类
 * 
 * @author yangguangftlp
 * @date 2014年10月15日
 */
public class BoundaryEventXMLConverter extends CatchEventXMLConverter {
	
	public FlowElement cretateFlowElement() {
		return new BoundaryEvent();
	}
	
	@Override
	public Class<? extends BaseElement> getBpmnElementType() {
		return BoundaryEvent.class;
	}
	
	@Override
	public void convertXMLToModel(Element element, BaseElement baseElement) {
		BoundaryEvent boundaryEvent = (BoundaryEvent) baseElement;
		boundaryEvent.setAttachedToRef(element.attributeValue(BpmnXMLConstants.ATTRIBUTE_ATTACHEDTOREF));
		if (BpmnXMLConstants.IS_FALSE.equalsIgnoreCase(element.attributeValue(BpmnXMLConstants.ATTRIBUTE_CANCELACTIVITY))) {
			boundaryEvent.setCancelActivity(false);
		}
		super.convertXMLToModel(element, baseElement);;
	}
	
	@Override
	public void convertModelToXML(Element element, BaseElement baseElement) {
		BoundaryEvent boundaryEvent = (BoundaryEvent) baseElement;
		if (null != boundaryEvent.getAttachedToRef()) {
			element.addAttribute(BpmnXMLConstants.ATTRIBUTE_ATTACHEDTOREF, boundaryEvent.getAttachedToRef());
		}
		if (!boundaryEvent.isCancelActivity()) {
			element.addAttribute(BpmnXMLConstants.ATTRIBUTE_CANCELACTIVITY, String.valueOf(false));
		}
		super.convertModelToXML(element, baseElement);
	}
	
	@Override
	public String getXMLElementName() {
		return BpmnXMLConstants.ELEMENT_BOUNDARYEVENT;
	}
	
	public Element cretateXMLElement() {
		return DocumentFactory.getInstance().createElement(BpmnXMLConstants.BPMN2_PREFIX + ':'
		        + BpmnXMLConstants.ELEMENT_BOUNDARYEVENT, BpmnXMLConstants.BPMN2_NAMESPACE);
	}
	
}
