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
public interface EtlDestinationVisitor {

    void visit(EtlCohortDestination etlCohortDestination);

    void visit(EtlI2B2Destination etlI2B2Destination);

    void visit(EtlNeo4jDestination etlNeo4jDestination);

    void visit(EtlPatientSetExtractorDestination etlPatientSetExtractorDestination);

    void visit(EtlPatientSetSenderDestination etlPatientSetSenderDestination);

    void visit(EtlTabularFileDestination etlTabularFileDestination);
    
    void visit(EtlPatientListDestination etlPatientListDestination);
    
    void visit(EtlOmopDestination etlOmopDestination);
    
    void visit(EtlPhenotypeSearchDestination etlPhenotypeSearchDestination);
    
    void visit(EtlCovidOmopDestination etlCovidOmopDestination);
}
