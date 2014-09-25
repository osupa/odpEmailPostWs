package com.parssoft.documentmanager.aws.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.Tables;
import com.parssoft.documentmanager.aws.utils.AwsCredentialsHandler;
import com.parssoft.documentmanager.aws.utils.RegionConfiguration;
import com.parssoft.documentmanager.model.Email;
import com.parssoft.documentmanager.utils.GenericUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamoDBService {

	/**
	 * Our DynamoDB table
	 */
	public static final String DYNAMO_DB_TABLE_NAME = "parssoft.docman.document";

	private static AmazonDynamoDBClient dynamoDB;

	private static final Logger log = LoggerFactory.getLogger(DynamoDBService.class);

	public DynamoDBService() {
		dynamoDB = new AmazonDynamoDBClient(AwsCredentialsHandler.getCredentials());
		dynamoDB.setRegion(RegionConfiguration.getRegion());
	}

	public DynamoDBMapper getMapper() {
		AmazonDynamoDBClient client =
				new AmazonDynamoDBClient(AwsCredentialsHandler.getCredentials());
		DynamoDBMapper mapper = new DynamoDBMapper(client);

		return mapper;
	}

	/**
	 * Persist document object
	 * 
	 * @param document
	 */
	public void persistMetaData(Email document) {
		try {
			DynamoDBMapper mapper = getMapper();

			if (!Tables.doesTableExist(dynamoDB, DYNAMO_DB_TABLE_NAME)) {
				// note the below only creates fields for attributes that are not null when create command is executed
				CreateTableRequest generateCreateTableRequest =
						mapper.generateCreateTableRequest(Email.class);

				generateCreateTableRequest.setTableName(DYNAMO_DB_TABLE_NAME);

				generateCreateTableRequest.withProvisionedThroughput(new ProvisionedThroughput()
								.withReadCapacityUnits(1L).withWriteCapacityUnits(1L));

				dynamoDB.createTable(generateCreateTableRequest);
				Tables.waitForTableToBecomeActive(dynamoDB, DYNAMO_DB_TABLE_NAME);

				// create initial table from object below - all fields are filled in
				Email createTableInstance = new Email();
//				createTableInstance = createTableInstance.createTablePrep(mapper);
				mapper.save(createTableInstance);
				// TODO - test out delete process after table has been created
//				mapper.delete(createTableInstance);
			}
			mapper.save(document);
		} catch (AmazonServiceException ase) {
			GenericUtilities.logAmazonServiceException(log, ase);
		}
	}

	/**
	 * Retrieve document by ID - return object and mapper
	 *
	 * @param idSought
	 * @return
	 */
	public Object[] getDocumentById(String idSought) {
		try {
			DynamoDBMapper mapper = getMapper();
			Email document = mapper.load(Email.class, idSought);

			// we need a handle to the mapper, so, we'll return both here
			return (new Object[] {document, mapper});
		} catch (AmazonServiceException ase) {
			GenericUtilities.logAmazonServiceException(log, ase);
		}
		return null;
	}

	/**
	 * Update the document record
	 * @param document 
	 */
	public void updateProcessState(Email document) {
		try {
			DynamoDBMapper mapper = getMapper();
			mapper.save(document);
		} catch (AmazonServiceException ase) {
			GenericUtilities.logAmazonServiceException(log, ase);
		}
	}
}