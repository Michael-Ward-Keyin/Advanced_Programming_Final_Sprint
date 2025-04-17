
CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL, 
    email TEXT,
    phone_number TEXT,
    address TEXT,
    role TEXT CHECK (role IN ('Admin', 'Trainer', 'Member'))
);


CREATE TABLE IF NOT EXISTS memberships (
    membership_id SERIAL PRIMARY KEY,
    membership_type TEXT NOT NULL,
    membership_description TEXT,
    membership_cost DECIMAL(10, 2),
    member_id INTEGER REFERENCES users(user_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS workout_classes (
    class_id SERIAL PRIMARY KEY,
    class_type TEXT NOT NULL,
    class_description TEXT,
    trainer_id INTEGER REFERENCES users(user_id) ON DELETE SET NULL
);
