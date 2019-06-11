/*
 * #%L
 * Eureka Common
 * %%
 * Copyright (C) 2012 - 2013 Emory University
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
package org.eurekaclinical.protempa.client;

import com.google.inject.Inject;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;


import org.eurekaclinical.protempa.client.comm.EtlCohortDestination;
import org.eurekaclinical.protempa.client.comm.EtlDestination;
import org.eurekaclinical.protempa.client.comm.EtlI2B2Destination;
import org.eurekaclinical.protempa.client.comm.EtlPatientSetExtractorDestination;
import org.eurekaclinical.protempa.client.comm.EtlPatientSetSenderDestination;
import org.eurekaclinical.protempa.client.comm.EtlTabularFileDestination;
import org.eurekaclinical.protempa.client.comm.ValidationRequest;
import org.eurekaclinical.protempa.client.json.ObjectMapperProvider;
import org.eurekaclinical.eureka.client.comm.DestinationType;
import org.eurekaclinical.eureka.client.comm.Job;
import org.eurekaclinical.eureka.client.comm.JobFilter;
import org.eurekaclinical.eureka.client.comm.SourceConfig;
import org.eurekaclinical.eureka.client.comm.Statistics;

import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.eurekaclinical.common.comm.Role;
import org.eurekaclinical.common.comm.clients.ClientException;
import org.eurekaclinical.common.comm.clients.EurekaClinicalClient;
import org.eurekaclinical.eureka.client.comm.AOUParticipantDestination;
import org.eurekaclinical.eureka.client.comm.CohortDestination;
import org.eurekaclinical.eureka.client.comm.Destination;
import org.eurekaclinical.eureka.client.comm.I2B2Destination;
import org.eurekaclinical.eureka.client.comm.JobSpec;
import org.eurekaclinical.eureka.client.comm.PatientSetExtractorDestination;
import org.eurekaclinical.eureka.client.comm.PatientSetSenderDestination;
import org.eurekaclinical.eureka.client.comm.SourceConfigParams;
import org.eurekaclinical.eureka.client.comm.SystemPhenotype;
import org.eurekaclinical.eureka.client.comm.TabularFileDestination;
import org.eurekaclinical.protempa.client.comm.JobRequest;
import org.eurekaclinical.standardapis.exception.HttpStatusException;
import org.protempa.PropositionDefinition;

/**
 * @author hrathod
 */
public class EurekaClinicalProtempaClient extends EurekaClinicalClient{

	private static final GenericType<List<Job>> JobListType = new GenericType<List<Job>>() {
	};
	private static final GenericType<Job> JobType = new GenericType<Job>() {
	};
	private static final GenericType<Statistics> JobStatsType = new GenericType<Statistics>() {
	};
	private static final GenericType<List<SourceConfig>> SourceConfigListType
			= new GenericType<List<SourceConfig>>() {
			};
        private static final GenericType<List<SourceConfigParams>> SourceConfigParamsList = new GenericType<List<SourceConfigParams>>() {
        };
	private static final GenericType<List<Destination>> DestinationListType
			= new GenericType<List<Destination>>() {
			};
	private static final GenericType<List<CohortDestination>> CohortDestinationListType
			= new GenericType<List<CohortDestination>>() {
			};
	private static final GenericType<List<I2B2Destination>> I2B2DestinationListType
			= new GenericType<List<I2B2Destination>>() {
			};
	private static final GenericType<List<PatientSetExtractorDestination>> PatientSetExtractorDestinationListType
			= new GenericType<List<PatientSetExtractorDestination>>() {
			};
	private static final GenericType<List<PatientSetSenderDestination>> PatientSetSenderDestinationListType
			= new GenericType<List<PatientSetSenderDestination>>() {
			};
	private static final GenericType<List<TabularFileDestination>> TabularFileDestinationListType
			= new GenericType<List<TabularFileDestination>>() {
			};
	private static final GenericType<List<PropositionDefinition>> PropositionDefinitionList
			= new GenericType<List<PropositionDefinition>>() {
			};
	private static final GenericType<List<String>> PropositionSearchResultsList
			= new GenericType<List<String>>() {
			};
	private static final GenericType<List<Role>> RoleList = new GenericType<List<Role>>() {
	};
        private static final GenericType<List<SystemPhenotype>> SystemPhenotypeList = new GenericType<List<SystemPhenotype>>() {
        };
        private static final GenericType<List<AOUParticipantDestination>> AOUParticipantDestinationType = new GenericType<List<AOUParticipantDestination>>() {
        };
    private final URI resourceUrl;

	@Inject
	public EurekaClinicalProtempaClient(String inEtlUrl) {
		super(ObjectMapperProvider.class);
		this.resourceUrl = URI.create(inEtlUrl);
		
	}

	@Override
	protected URI getResourceUrl() {
		return this.resourceUrl;
	}

	public List<SourceConfig> getSourceConfigs() throws
			ClientException {
		final String path = "/api/protected/sourceconfig";
		return doGet(path, SourceConfigListType);
	}

	public SourceConfig getSourceConfig(String sourceConfigId) throws
			ClientException {
		String path = UriBuilder.fromPath("/api/protected/sourceconfig/")
				.segment(sourceConfigId)
				.build().toString();
		return doGet(path, SourceConfig.class);
	}
        
        public List<SourceConfigParams> getSourceConfigParams() throws ClientException {
                String path = "/api/protected/sourceconfig/parameters/list";
                return doGet(path, SourceConfigParamsList);
        }


	public List<Destination> getDestinations() throws
			ClientException {
		final String path = "/api/protected/destinations";
		return doGet(path, DestinationListType);
	}

	public List<CohortDestination> getCohortDestinations() throws
			ClientException {
		final String path = "/api/protected/destinations/";
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("type", DestinationType.COHORT.name());
		return doGet(path, queryParams, CohortDestinationListType);
	}

	public List<I2B2Destination> getI2B2Destinations() throws
			ClientException {
		final String path = "/api/protected/destinations/";
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("type", DestinationType.I2B2.name());
		return doGet(path, queryParams, I2B2DestinationListType);
	}

	public List<PatientSetExtractorDestination> getPatientSetExtractorDestinations() throws ClientException {
		final String path = "/api/protected/destinations/";
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("type", DestinationType.PATIENT_SET_EXTRACTOR.name());
		return doGet(path, queryParams, PatientSetExtractorDestinationListType);
	}
	
	public List<PatientSetSenderDestination> getPatientSetSenderDestinations() throws ClientException {
		final String path = "/api/protected/destinations/";
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("type", DestinationType.PATIENT_SET_SENDER.name());
		return doGet(path, queryParams, PatientSetSenderDestinationListType);
	}
	
	public List<TabularFileDestination> getTabularFileDestinations() throws ClientException {
		final String path = "/api/protected/destinations/";
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("type", DestinationType.TABULAR_FILE.name());
		return doGet(path, queryParams, TabularFileDestinationListType);
	}

	public Destination getDestination(String destId) throws
			ClientException {
		String path = UriBuilder.fromPath("/api/protected/destinations/")
				.segment(destId)
				.build().toString();
		return doGet(path, Destination.class);
	}

	public Long createDestination(Destination dest) throws ClientException {
		String path = "/api/protected/destinations";
		URI destURI = doPostCreate(path, dest);
                return extractId(destURI);
	}

	public void updateDestination(Destination dest) throws ClientException {
		String path = "/api/protected/destinations";
		doPut(path, dest);
	}

	public void deleteDestination(String etlDestId) throws ClientException {
		String path = UriBuilder.fromPath("/api/protected/destinations/")
				.segment(etlDestId)
				.build().toString();
		doDelete(path);
	}

	public Long submitJob(JobSpec inJobSpec) throws ClientException {
		final String path = "/api/protected/jobs";
		URI jobUri = doPostCreate(path, inJobSpec);
                
		return extractId(jobUri);
	}

	public List<Job> getJobStatus(JobFilter inFilter) throws ClientException {
		final String path = "/api/protected/jobs/status";
		return doGet(path, JobListType);
	}

	public List<Job> getJobs() throws ClientException {
		final String path = "/api/protected/jobs";
		return doGet(path, JobListType);
	}

	public List<Job> getJobsDesc() throws ClientException {
		final String path = "/api/protected/jobs";
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("order", "desc");
		return doGet(path, queryParams, JobListType);
	}

	public List<Job> getLatestJob() throws ClientException {
		final String path = "/api/protected/jobs/latest";
		return doGet(path, JobListType);
	}

	public Job getJob(Long inJobId) throws ClientException {
		final String path = "/api/protected/jobs/" + inJobId;
		return doGet(path, JobType);
	}

	public Statistics getJobStats(Long inJobId, String inPropId) throws ClientException {
		UriBuilder uriBuilder = UriBuilder.fromPath("/api/protected/jobs/{arg1}/stats/");
		if (inPropId != null) {
			uriBuilder = uriBuilder.segment(inPropId);
		}
		return doGet(uriBuilder.build(inJobId).toString(), JobStatsType);
	}

	public void validatePropositions(ValidationRequest inRequest) throws
			ClientException {
		final String path = "/api/protected/validate";
		doPost(path, inRequest);
	}

	/**
	 * Gets a proposition definition with a specified id for a specified user.
	 *
	 * @param sourceConfigId the source config id of interest.
	 * @param inKey the proposition id of interest.
	 * @return the proposition id, if found, or <code>null</code> if not.
	 *
	 * @throws ClientException if an error occurred looking for the proposition
	 * definition.
	 */
	public PropositionDefinition getPropositionDefinition(
			String sourceConfigId, String inKey) throws ClientException {
		MultivaluedMap<String, String> formParams = new MultivaluedMapImpl();
		formParams.add("key", inKey);
		String path = UriBuilder.fromPath("/api/protected/conceptsbyconfigid/")
				.segment(sourceConfigId)
				.build().toString();
		List<PropositionDefinition> propDefs = doPost(path, formParams, PropositionDefinitionList);
		if (propDefs.isEmpty()) {
			throw new ClientException(ClientResponse.Status.NOT_FOUND, null);
		} else {
			return propDefs.get(0);
		}
	}

	/**
	 * Gets all of the proposition definitions given by the key IDs for the
	 * given user.
	 *
	 * @param sourceConfigId the ID of the source configuration to use
	 * @param inKeys the keys (IDs) of the proposition definitions to get
	 * @param withChildren whether to get the children of specified proposition
	 * definitions as well
	 * @return a {@link List} of {@link PropositionDefinition}s
	 *
	 * @throws ClientException if an error occurred looking for the proposition
	 * definitions
	 */
	public List<PropositionDefinition> getPropositionDefinitions(
			String sourceConfigId, List<String> inKeys, Boolean withChildren) throws ClientException {
		MultivaluedMap<String, String> formParams = new MultivaluedMapImpl();
		for (String key : inKeys) {
			formParams.add("key", key);
		}
		formParams.add("withChildren", withChildren.toString());
		String path = UriBuilder.fromPath("/api/protected/conceptsbyconfigid/")
				.segment(sourceConfigId)
				.build().toString();
		return doPost(path, formParams, PropositionDefinitionList);
	}

	public void upload(String fileName, String sourceId,
			String fileTypeId, InputStream inputStream)
			throws ClientException {
		String path = UriBuilder
				.fromPath("/api/protected/file/upload/")
				.segment(sourceId)
				.segment(fileTypeId)
				.build().toString();
		FormDataMultiPart part = new FormDataMultiPart();
        part.bodyPart(
                new FormDataBodyPart(
                        FormDataContentDisposition
                                .name("file")
                                .fileName(fileName)
                                .build(),
                        inputStream, MediaType.APPLICATION_OCTET_STREAM_TYPE));
		doPostMultipart(path, part);
	}

	public List<String> getPropositionSearchResults(String sourceConfigId,
			String inSearchKey) throws ClientException {

		String path = UriBuilder.fromPath("/api/protected/conceptsbyconfigid/search/")
				.segment(sourceConfigId)
				.segment(inSearchKey)
				.build().toString();
		return doGet(path, PropositionSearchResultsList);
	}

	public List<PropositionDefinition> getPropositionSearchResultsBySearchKey(String sourceConfigId,
			String inSearchKey) throws ClientException {

		String path = UriBuilder.fromPath("/api/protected/conceptsbyconfigid/propsearch/")
				.segment(sourceConfigId)
				.segment(inSearchKey)
				.build().toString();
		return doGet(path, PropositionDefinitionList);
	}

        public List<SystemPhenotype> getSystemPhenotypes() throws ClientException {
            final String path = UriBuilder.fromPath("/api/protected/concepts/").build().toString();
            return doGet(path, SystemPhenotypeList);
        }

        public List<SystemPhenotype> getSystemPhenotypes(List<String> inKeys, boolean summarize) throws ClientException {
            if (inKeys == null) {
                throw new IllegalArgumentException("inKeys cannot be null");
            }
            MultivaluedMap<String, String> formParams = new MultivaluedMapImpl();
            for (String key : inKeys) {
                formParams.add("key", key);
            }
            formParams.add("summarize", Boolean.toString(summarize));
            String path = UriBuilder.fromPath("/api/protected/concepts/")
                    .build().toString();
            return doPost(path, formParams, SystemPhenotypeList);
        }

        public SystemPhenotype getSystemPhenotype(String inKey, boolean summarize) throws ClientException {
            List<SystemPhenotype> result = getSystemPhenotypes(Collections.singletonList(inKey), summarize);
            if (result.isEmpty()) {
                throw new HttpStatusException(Response.Status.NOT_FOUND);
            } else {
                return result.get(0);
            }
        }        
        
	public ClientResponse getOutput(String destinationId) throws ClientException {
		String path = UriBuilder.fromPath("/api/protected/output/")
				.segment(destinationId)
				.build().toString();
		return doGetResponse(path);
	}

	public void deleteOutput(String destinationId) throws ClientException {
		String path = UriBuilder.fromPath("/api/protected/output/")
				.segment(destinationId)
				.build().toString();
		doDelete(path);
	}
	
	public List<Role> getRoles() throws ClientException {
		final String path = "/api/protected/roles";
		return doGet(path, RoleList);
	}

    public Role getRole(Long inRoleId) throws ClientException {
        final String path = "/api/protected/roles/" + inRoleId;
        return doGet(path, Role.class);
    }
    
    public Role getRoleByName(String name) throws ClientException {
        return doGet("/api/protected/roles/byname/" + name, Role.class);
    }
    
    public List<AOUParticipantDestination> getAouParticipantDestinations() throws ClientException {
        final String path = "/api/protected/destinations/";
        MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
        queryParams.add("type", DestinationType.AOU_PARTICIPANT.name());
        return doGet(path, queryParams, AOUParticipantDestinationType);
    }

}
