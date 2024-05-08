ALTER TABLE points_histories
    ADD COLUMN payment_type ENUM ('SURVEY', 'ADMIN') NOT NULL;