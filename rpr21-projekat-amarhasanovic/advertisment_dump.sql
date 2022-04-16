BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "users" (
	"id"	INTEGER,
	"first_name"	TEXT,
	"last_name"	TEXT,
	"username"	TEXT UNIQUE,
	"email"	TEXT,
	"password"	TEXT,
	"address" TEXT,
	"phone_number" INTEGER,
	"picture"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "advertisment" (
	"id"	INTEGER,
	"title"	TEXT,
	"description"	TEXT,
	"type"	TEXT,
	"user_creator"	INTEGER,
	FOREIGN KEY("user_creator") REFERENCES "users"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);

COMMIT;
