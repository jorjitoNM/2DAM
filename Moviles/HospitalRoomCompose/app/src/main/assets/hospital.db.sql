BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "PatientMedicalRecordsCrossRef" (
	"patientId"	INTEGER NOT NULL,
	"recordId"	INTEGER NOT NULL,
	PRIMARY KEY("patientId","recordId")
);
CREATE TABLE IF NOT EXISTS "android_metadata" (
	"locale"	TEXT
);
CREATE TABLE IF NOT EXISTS "medicalRecords" (
	"recordId"	INTEGER NOT NULL,
	"description"	TEXT NOT NULL,
	"date"	TEXT NOT NULL,
	"doctorId"	INTEGER NOT NULL,
	"patientId"	INTEGER NOT NULL,
	"medications"	TEXT NOT NULL,
	PRIMARY KEY("recordId" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "medications" (
	"id"	INTEGER NOT NULL,
	"medicationName"	TEXT NOT NULL,
	"dosage"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "patients" (
	"patientId"	INTEGER NOT NULL,
	"name"	TEXT NOT NULL,
	"birthDate"	TEXT NOT NULL,
	"phone"	TEXT NOT NULL,
	"paid"	INTEGER NOT NULL,
	PRIMARY KEY("patientId" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "room_master_table" (
	"id"	INTEGER,
	"identity_hash"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "users" (
	"id"	INTEGER NOT NULL,
	"username"	TEXT NOT NULL,
	"password"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "PatientMedicalRecordsCrossRef" VALUES (1,1);
INSERT INTO "PatientMedicalRecordsCrossRef" VALUES (2,2);
INSERT INTO "PatientMedicalRecordsCrossRef" VALUES (3,3);
INSERT INTO "PatientMedicalRecordsCrossRef" VALUES (4,4);
INSERT INTO "PatientMedicalRecordsCrossRef" VALUES (5,5);
INSERT INTO "PatientMedicalRecordsCrossRef" VALUES (1,6);
INSERT INTO "PatientMedicalRecordsCrossRef" VALUES (2,7);
INSERT INTO "PatientMedicalRecordsCrossRef" VALUES (3,8);
INSERT INTO "android_metadata" VALUES ('es_ES');
INSERT INTO "medicalRecords" VALUES (1,'Annual checkup','2023-01-15',1,1,'Ibuprofen, Amoxicillin');
INSERT INTO "medicalRecords" VALUES (2,'Flu symptoms','2023-02-20',2,2,'Amoxicillin');
INSERT INTO "medicalRecords" VALUES (3,'High blood pressure','2023-03-10',1,3,'Lisinopril');
INSERT INTO "medicalRecords" VALUES (4,'Diabetes management','2023-04-05',3,4,'Metformin');
INSERT INTO "medicalRecords" VALUES (5,'Cholesterol check','2023-05-12',2,5,'Atorvastatin');
INSERT INTO "medicalRecords" VALUES (6,'Acid reflux','2023-06-18',3,1,'Omeprazole');
INSERT INTO "medicalRecords" VALUES (7,'Asthma follow-up','2023-07-22',1,2,'Albuterol');
INSERT INTO "medicalRecords" VALUES (8,'Nerve pain','2023-08-30',2,3,'Gabapentin');
INSERT INTO "medications" VALUES (1,'Ibuprofen','400mg');
INSERT INTO "medications" VALUES (2,'Amoxicillin','500mg');
INSERT INTO "medications" VALUES (3,'Lisinopril','10mg');
INSERT INTO "medications" VALUES (4,'Metformin','850mg');
INSERT INTO "medications" VALUES (5,'Atorvastatin','20mg');
INSERT INTO "medications" VALUES (6,'Omeprazole','40mg');
INSERT INTO "medications" VALUES (7,'Albuterol','90mcg');
INSERT INTO "medications" VALUES (8,'Gabapentin','300mg');
INSERT INTO "patients" VALUES (1,'John Doe','1985-04-12','555-1234',1);
INSERT INTO "patients" VALUES (2,'Jane Smith','1990-08-25','555-5678',0);
INSERT INTO "patients" VALUES (3,'Alice Johnson','1975-11-30','555-8765',1);
INSERT INTO "patients" VALUES (4,'Bob Brown','1982-03-15','555-4321',0);
INSERT INTO "patients" VALUES (5,'Charlie Davis','1995-07-22','555-6543',1);
INSERT INTO "room_master_table" VALUES (42,'7dd5e0e637cf33788fdbbf4a3dc8cc69');
INSERT INTO "users" VALUES (1,'Juan','1234');
INSERT INTO "users" VALUES (2,'admin','admin');
COMMIT;
