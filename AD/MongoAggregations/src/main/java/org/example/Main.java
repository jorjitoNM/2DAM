package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BsonField;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class Main {
    public static void main(String[] args) {
        try (MongoClient mongo = MongoClients.create(Constants.MONGODB_URL)) {
            MongoDatabase db = mongo.getDatabase(Constants.DB_NAME);
            MongoCollection<Document> collection = db.getCollection(Constants.PATIENTS);

            //1.a. Get the oldest patient
            collection.aggregate(
                    Arrays.asList(
                            sort(eq(Constants.BIRTH_DATE, 1)),
                            limit(1)
                    )).into(new ArrayList<>()).forEach(System.out::println);

            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            //1.b. Get the name of the patient who has paid the most
            collection.aggregate(
                    Arrays.asList(
                            unwind("$" + Constants.PAYMENTS),
                            group("$" + Constants.NAME, sum("paymentsSum", "$payments.amount")),
                            sort(descending("paymentsSum")),
                            limit(1)
                    )
            ).into(new ArrayList<>()).forEach(System.out::println);

            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            //1.c. Get the medRecords of a given patient, showing the name of the patient and the total payment
//            [
//            {
//                $match:
//                /**
//                 * query: The query in MQL.
//                 */
//                {
//                    _id: ObjectId("67a35d375f473a4f387a3491")
//                }
//            },
//            {
//                $unwind:
//                /**
//                 * path: Path to the array field.
//                 * includeArrayIndex: Optional name for index.
//                 * preserveNullAndEmptyArrays: Optional
//                 *   toggle to unwind null and empty values.
//                 */
//                {
//                    path: "$payments"
//                }
//            },
//            {
//                $group:
//                /**
//                 * _id: The id of the group.
//                 * fieldN: The first field name.
//                 */
//                {
//                    _id: {
//                        patientId: "$_id",
//                                name: "$name"
//                    },
//                    paymentsSum: {
//                        $sum: "$payments.amount"
//                    }
//                }
//            },
//            {
//                $lookup:
//                /**
//                 * from: The target collection.
//                 * localField: The local join field.
//                 * foreignField: The target join field.
//                 * as: The name for the results.
//                 * pipeline: Optional pipeline to run on the foreign collection.
//                 * let: Optional variables to use in the pipeline field stages.
//                 */
//                {
//                    from: "medicalRecords",
//                            localField: "_id.patientId",
//                        foreignField: "patient",
//                        as: "PatientWithMedicalRecords"
//                }
//            }
//          ]
            collection.aggregate(
                    Arrays.asList(
                            match(eq(Constants.ID, new ObjectId("67a35d375f473a4f387a3491"))),
                            unwind("$"+Constants.PAYMENTS),
                            group((new Document("patientId", Constants.ID)
                                    .append("name", Constants.NAME)), sum("paymentsSum", "$payments.amount")),
                            lookup(Constants.MEDICAL_RECORDS, Constants.ID + "." + "patientId",Constants.PATIENT,"PatientMedicalRecords")
                    )
            ).into(new ArrayList<>()).forEach(System.out::println);
        }
    }
}