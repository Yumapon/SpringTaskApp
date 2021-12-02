package com.yuma.javaarchitect.springtaskappspring.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "TaskAPIUserRole")
@Builder
@Data
public class APIUser {

    private String email;

    private String role;

    @DynamoDBHashKey(attributeName="id")
    public String getEmail() {
        return this.email;
    }

    @DynamoDBAttribute(attributeName="role")
    public String getRole() {
        return this.role;
    }

    @Override
    public String toString(){
        return "This user's ID is '" + email + "' and Role is '" + role + "'"; 
    }

}
