ALTER TABLE points_histories
    MODIFY COLUMN payment_type ENUM ('SURVEY', 'ADMIN', 'CANCEL') NOT NULL;