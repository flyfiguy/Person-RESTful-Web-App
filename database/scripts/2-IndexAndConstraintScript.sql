CREATE INDEX idx_user_first_name ON "PERSON" (first_name, last_name);

ALTER TABLE person
    ADD CONSTRAINT uc_profiles_user UNIQUE (address_id);