package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Field;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;

public class Main {
    public static void main(String[] args) {
        try (MongoClient mongo = MongoClients.create(Constants.MONGODB_URL)) {
            MongoDatabase db = mongo.getDatabase(Constants.DB_NAME);
            MongoCollection<Document> patients = db.getCollection(Constants.PATIENTS);
            MongoCollection<Document> medicalRecords = db.getCollection(Constants.MEDICAL_RECORDS);

            //1.a. Get the oldest patient
            System.out.println("1.a. Get the oldest patient");
            patients.aggregate(
                    Arrays.asList(
                            sort(eq(Constants.BIRTH_DATE, 1)),
                            limit(1)
                    )).into(new ArrayList<>()).forEach(System.out::println);

            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            //1.b. Get the name of the patient who has paid the most
            System.out.println("1.b. Get the name of the patient who has paid the most");
            patients.aggregate(
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
            System.out.println("1.c. Get the medRecords of a given patient, showing the name of the patient and the total payment");
            patients.aggregate(
                    Arrays.asList(
                            match(eq(Constants.ID, new ObjectId("67a35d375f473a4f387a3491"))),
                            unwind("$"+Constants.PAYMENTS),
                            group((new Document("patientId", Constants.ID)
                                    .append("name", Constants.NAME)), sum("paymentsSum", "$payments.amount")),
                            lookup(Constants.MEDICAL_RECORDS, Constants.ID + "." + "patientId",Constants.PATIENT,"PatientMedicalRecords")
                    )
            ).into(new ArrayList<>()).forEach(System.out::println);

            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            //1.d. Get the number of medications of each medRecord
//            [
//            {
//                $project:
//                /**
//                 * specifications: The fields to
//                 *   include or exclude.
//                 */
//                {
//                    _id: 0,
//                            numberOfMedications: {
//                    $size: "$medications"
//                }
//                }
//            }
//          ]
            System.out.println("1.d. Get the number of medications of each medRecord");
            medicalRecords.aggregate(
                    Arrays.asList(
                            addFields(new Field<>("numberOfMedications",
                                    new Document("$size", "$medications"))),
                            project(
                            fields(exclude(Constants.ID),include("numberOfMedications"))))
            ).into(new ArrayList<>()).forEach(System.out::println);


            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            //1.e. Get the name of the patients who have been prescribed Ibuprofen
            System.out.println("1.e. Get the name of the patients who have been prescribed Ibuprofen");
//            [
//            {
//                $match:
//                /**
//                 * query: The query in MQL.
//                 */
//                {
//                    medications: "Insulin"
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
//                    path: "$patient"
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
//                    from: "patients",
//                            localField: "patient",
//                        foreignField: "_id",
//                        as: "Patient"
//                }
//            },
//            {
//                $project:
//                /**
//                 * specifications: The fields to
//                 *   include or exclude.
//                 */
//                {
//                    _id: 0,
//                            "Patient.name": 1
//                }
//            }
//            ]
            medicalRecords.aggregate(
                    Arrays.asList(
                            match(eq(Constants.MEDICATIONS,"Insulin")),
                            unwind("$" + Constants.PATIENT),
                            lookup(Constants.PATIENTS,Constants.PATIENT,Constants.ID,"Patient"),
                            project(
                                    fields(exclude(Constants.ID),include("Patient.name"))))
            ).into(new ArrayList<>()).forEach(System.out::println);


            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            //1.f. Get the average number of medications per medRecord
//            [
//            {
//                $project:
//                /**
//                 * newField: The new field name.
//                 * expression: The new field expression.
//                 */
//                {
//                    numberOfMedications: {
//                        $size: "$medications"
//                    }
//                }
//            },
//            {
//                $group:
//                /**
//                 * specifications: The fields to
//                 *   include or exclude.
//                 */
//                {
//                    _id: null,
//                            averageMedications: {
//                    $avg: "$numberOfMedications"
//                }
//                }
//                    }
//            ]
            System.out.println("1.f. Get the average number of medications per medRecord");
            medicalRecords.aggregate(
                    Arrays.asList(
                            addFields(new Field<>("numberOfMedications",
                                    new Document("$size", "$medications"))),
                            group(null,avg("averageMedications","$numberOfMedications")))
            ).into(new ArrayList<>()).forEach(System.out::println);


            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            //1.g. Get the medication most prescribed
//            [
//            {
//                $unwind:
//                /**
//                 * path: Path to the array field.
//                 * includeArrayIndex: Optional name for index.
//                 * preserveNullAndEmptyArrays: Optional
//                 *   toggle to unwind null and empty values.
//                 */
//                {
//                    path: "$medications"
//                }
//            },
//            {
//                $group:
//                /**
//                 * _id: The id of the group.
//                 * fieldN: The first field name.
//                 */
//                {
//                    _id: "$medications",
//                            sumOfPrescriptions: {
//                    $sum: 1
//                }
//                }
//            },
//            {
//                $sort:
//                /**
//                 * Provide any number of field/order pairs.
//                 */
//                {
//                    sumOfPrescriptions: -1
//                }
//            },
//            {
//                $limit:
//                /**
//                 * Provide the number of documents to limit.
//                 */
//                1
//            }
//            ]
            System.out.println("1.g. Get the medication most prescribed");
            medicalRecords.aggregate(
                    Arrays.asList(
                            unwind("$" + Constants.MEDICATIONS),
                            group("$"+Constants.MEDICATIONS,sum("sumOfPrescriptions",1)),
                            sort(descending("sumOfPrescriptions")),
                            limit(1))
            ).into(new ArrayList<>()).forEach(System.out::println);


            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            //1.h. Get the most prescribed medication per patient
//[
//            {
//                $unwind:
//                /**
//                 * path: Path to the array field.
//                 * includeArrayIndex: Optional name for index.
//                 * preserveNullAndEmptyArrays: Optional
//                 *   toggle to unwind null and empty values.
//                 */
//                {
//                    path: "$medications"
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
//                        patient: "$patient",
//                                medication: "$medications"
//                    },
//                    count: {
//                        $sum: 1
//                    }
//                }
//            },
//            {
//                $sort:
//                /**
//                 * Provide any number of field/order pairs.
//                 */
//                {
//                    "_id.patient": 1,
//                        // Sort by patient (ascending)
//                        count: -1
//                }
//            },
//            {
//                $group:
//                /**
//                 * _id: The id of the group.
//                 * fieldN: The first field name.
//                 */
//                {
//                    _id: "$_id.patient",
//                            // Regroup by patient only
//                            mostPrescribed: {
//                    $first: "$_id.medication"
//                },
//                    // Keep first medication (highest count)
//                    total: {
//                        $first: "$count"
//                    }
//                }
//            }
//        ]

            System.out.println("1.h. Get the most prescribed medication per patient");
            medicalRecords.aggregate(
                    Arrays.asList(
                            unwind("$" + Constants.MEDICATIONS),
                            group( new Document("patient","$"+ Constants.PATIENT)
                                    .append("medication","$"+ Constants.MEDICATIONS),
                                    sum("count", 1)
                            ),
                            sort(orderBy(ascending("_id.patient"), descending("count"))),
                            group(
                                    "$_id.patient",
                                    first("mostPrescribedMedication", "$_id.medication"),
                                    first("totalPrescriptions", "$count")
                            ))
            ).into(new ArrayList<>()).forEach(System.out::println);


            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            //1.i. Get the doctor who prescribes more medications

//            [
//            {
//                $unwind:
//                /**
//                 * path: Path to the array field.
//                 * includeArrayIndex: Optional name for index.
//                 * preserveNullAndEmptyArrays: Optional
//                 *   toggle to unwind null and empty values.
//                 */
//                {
//                    path: "$medications"
//                }
//            },
//            {
//                $group:
//                /**
//                 * _id: The id of the group.
//                 * fieldN: The first field name.
//                 */
//                {
//                    _id: "$doctor",
//                            numberOfPrescriptions: {
//                    $sum: 1
//                }
//                }
//            },
//            {
//                $sort:
//                /**
//                 * Provide any number of field/order pairs.
//                 */
//                {
//                    numberOfPrescriptions: -1
//                }
//            },
//            {
//                $limit:
//                /**
//                 * Provide the number of documents to limit.
//                 */
//                1
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
//                    from: "doctors",
//                            localField: "_id",
//                        foreignField: "_id",
//                        as: "Doctor"
//                }
//            }
//            ]
            System.out.println("1.i. Get the doctor who prescribes more medications");
            medicalRecords.aggregate(
                    Arrays.asList(
                            unwind("$" + Constants.MEDICATIONS),
                            group("$"+Constants.DOCTOR,sum("numberOfPrescriptions",1)),
                            sort(descending("sumOfPrescriptions")),
                            limit(1),
                            lookup(Constants.DOCTORS,Constants.ID,Constants.ID,"Doctor"))
            ).into(new ArrayList<>()).forEach(System.out::println);

            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            System.out.println("Ej 2 (Houses)");

            MongoCollection<Document> houses = db.getCollection("house_pricing");

            System.out.println("1. Average Price by Bedrooms");
            houses.aggregate(
                    Arrays.asList(
                            group("$bedrooms",
                                    avg("averagePrice", new Document("$toDouble", "$price"))),
                                    project(fields(
                                                    include("_id", "averagePrice"),
                                                    computed("averagePrice", new Document("$round", Arrays.asList("$averagePrice", 0)))))
                                    )).into(new ArrayList<>()).forEach(System.out::println);


            System.out.println();
            System.out.println("------------------------------");
            System.out.println();


            System.out.println("2. Air Conditioning Count by Furnishing");
            houses.aggregate(
                    Arrays.asList(
                            match(eq("airconditioning", "yes")),
                            group("$furnishingstatus", sum("count", 1)),
                            sort(descending("count"))
                    )).into(new ArrayList<>()).forEach(System.out::println);

            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            System.out.println("3. Popular Parking in Preferred Areas");
            houses.aggregate(
                    Arrays.asList(
                            match(eq("prefarea", "yes")),
                            group("$parking", sum("count", 1)),
                            sort(descending("count")),
                            limit(1)
                    )).into(new ArrayList<>()).forEach(System.out::println);

            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            System.out.println("4. Avg Area (Basement + Guestroom)");
            houses.aggregate(
                    Arrays.asList(
                            match(and(
                                    eq("basement", "yes"),
                                    eq("guestroom", "yes")
                            )),
                            group(null,
                                    avg("avgArea", new Document("$toDouble", "$area"))),
                                    project(fields(
                                                    excludeId(),
                                                    computed("avgArea", new Document("$round", Arrays.asList("$avgArea", 0)))
                                            )
                                    ))).into(new ArrayList<>()).forEach(System.out::println);

            System.out.println();
            System.out.println("------------------------------");
            System.out.println();

            System.out.println("5. Price Stats by Stories");
            houses.aggregate(
                    Arrays.asList(
                            addFields(new Field<>("pricePerSqUnit",
                                    new Document("$divide",
                                            Arrays.asList(
                                                    new Document("$toDouble", "$price"),
                                                    new Document("$toDouble", "$area")
                                            )
                                    )
                            )),
                            group("$furnishingstatus",
                                    avg("avgPricePerSqUnit", "$pricePerSqUnit"),
                                    min("minEfficiency", "$pricePerSqUnit"),
                                    max("maxEfficiency", "$pricePerSqUnit")
                            ),
                            project(fields(
                                    include("_id"),
                                    computed("avgPricePerSqUnit",
                                            new Document("$round", Arrays.asList("$avgPricePerSqUnit", 2))),
                                    computed("minEfficiency",
                                            new Document("$round", Arrays.asList("$minEfficiency", 2))),
                                    computed("maxEfficiency",
                                            new Document("$round", Arrays.asList("$maxEfficiency", 2)))
                            )),
                            sort(descending("avgPricePerSqUnit"))
                    )).into(new ArrayList<>()).forEach(System.out::println);
        }
    }
}