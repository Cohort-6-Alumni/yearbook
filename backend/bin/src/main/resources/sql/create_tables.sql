BEGIN;
-- Enable the uuid-ossp extension
-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS public."User"
(
    "userId" uuid DEFAULT gen_random_uuid() NOT NULL,
    "firstName" character varying NOT NULL,
    "lastName" character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL,
    "createdOn" timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    role character varying NOT NULL DEFAULT 'user'::character varying,
    CONSTRAINT "User_pkey" PRIMARY KEY ("userId"),
    CONSTRAINT "User_email_key" UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS public."Profile"
(
    "profileId" uuid DEFAULT gen_random_uuid() NOT NULL,
    "userId" uuid NOT NULL,
    bio text,
    interests text,
    hobbies text,
    "favoriteCodingSnack" character varying,
    "favoriteQuote" text,
    "mostLikelyTo" text,
    "mostMemorableBootcampMoment" text,
    "adviceForFutureCohort" text,
    "biggestChallenge" text,
    "howYouOvercameIt" text,
    "lastWords" text,
    CONSTRAINT "Profile_pkey" PRIMARY KEY ("profileId"),
    CONSTRAINT "Profile_userId_fkey" FOREIGN KEY ("userId")
        REFERENCES public."User" ("userId") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

-- Trigger function to prevent updates to UUID columns
CREATE OR REPLACE FUNCTION prevent_uuid_update()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW."profileId" <> OLD."profileId" THEN
        RAISE EXCEPTION 'Cannot update profileId';
    END IF;
    IF NEW."userId" <> OLD."userId" THEN
        RAISE EXCEPTION 'Cannot update userId';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Apply the trigger to the Profile table
CREATE TRIGGER prevent_uuid_update_trigger
BEFORE UPDATE ON public."Profile"
FOR EACH ROW
EXECUTE FUNCTION prevent_uuid_update();

COMMIT;