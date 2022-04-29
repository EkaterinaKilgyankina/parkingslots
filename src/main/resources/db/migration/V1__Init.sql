CREATE TABLE "spot"
(
    "id"       bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "number"    text NOT NULL,
    "available_status" boolean
);




