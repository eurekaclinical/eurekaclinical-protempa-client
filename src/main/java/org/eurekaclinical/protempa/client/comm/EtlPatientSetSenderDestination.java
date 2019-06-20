package org.eurekaclinical.protempa.client.comm;

/*
 * #%L
 * Eureka Common
 * %%
 * Copyright (C) 2012 - 2014 Emory University
 * %%
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
 * #L%
 */

/**
 *
 * @author Andrew Post
 */
public class EtlPatientSetSenderDestination extends EtlDestination {
	private String aliasPropositionId;
	private String aliasFieldNameProperty;
	private String aliasFieldName;
	private String aliasPatientIdProperty;
	private String patientSetService;

	@Override
	public void accept(EtlDestinationVisitor etlDestinationVisitor) {
		etlDestinationVisitor.visit(this);
	}

	public void setAliasPropositionId(String aliasPropositionId) {
		this.aliasPropositionId = aliasPropositionId;
	}

	public String getAliasPropositionId() {
		return aliasPropositionId;
	}

	public String getAliasFieldNameProperty() {
		return aliasFieldNameProperty;
	}

	public void setAliasFieldNameProperty(String aliasFieldNameProperty) {
		this.aliasFieldNameProperty = aliasFieldNameProperty;
	}

	public String getAliasFieldName() {
		return aliasFieldName;
	}

	public void setAliasFieldName(String aliasFieldName) {
		this.aliasFieldName = aliasFieldName;
	}

	public String getAliasPatientIdProperty() {
		return aliasPatientIdProperty;
	}

	public void setAliasPatientIdProperty(String aliasPatientIdProperty) {
		this.aliasPatientIdProperty = aliasPatientIdProperty;
	}
	
	public String getPatientSetService() {
		return patientSetService;
	}

	public void setPatientSetService(String patientSetService) {
		this.patientSetService = patientSetService;
	}
	
}
