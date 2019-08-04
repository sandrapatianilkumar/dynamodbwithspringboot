package com.springboot.dynamodemo.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.ConsistentReads;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.SaveBehavior;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.springboot.dynamodemo.model.CatalogItem;

@Service
public class DynamoService {

	private DynamoDBMapper mapper;

	@PostConstruct
	public void intialise() {
		mapper = new DynamoDBMapper(dynamodb, createDynamoDBMapperConfig());
	}

	public final AmazonDynamoDB dynamodb = AmazonDynamoDBClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("key", "secret")))
			.withEndpointConfiguration(new EndpointConfiguration("http://localhost:8000", "us-west-2")).build();

	private DynamoDBMapperConfig createDynamoDBMapperConfig() {
		return DynamoDBMapperConfig.builder().withConsistentReads(ConsistentReads.CONSISTENT)
				.withSaveBehavior(SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)
				.withTableNameOverride(new TableNameOverride("ProductCatalog")).build();
	}

	public CatalogItem save(CatalogItem catalogItem) {
		mapper.save(catalogItem);
		return catalogItem;
	}

	public CatalogItem load(String id) {
		CatalogItem catalogItem = mapper.load(CatalogItem.class, id);
		return catalogItem;
	}

}
