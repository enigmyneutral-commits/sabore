-- Saborê PostgreSQL Complete Database Schema & Migration (Neon DB)
-- Version: 1.0.0

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 1. Users Table
CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(255) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    display_name VARCHAR(255) NOT NULL,
    avatar_url TEXT,
    is_pro BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 2. Recipes Table (Private by default)
CREATE TABLE IF NOT EXISTS recipes (
    id VARCHAR(255) PRIMARY KEY,
    owner_id VARCHAR(255) NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    category VARCHAR(100) NOT NULL,
    visibility VARCHAR(50) DEFAULT 'PRIVATE', -- PRIVATE, PUBLIC, DRAFT, ARCHIVED
    prep_time_minutes INT DEFAULT 0,
    cook_time_minutes INT DEFAULT 0,
    servings INT DEFAULT 1,
    difficulty VARCHAR(50) DEFAULT 'easy',
    image_url TEXT,
    rating NUMERIC(3,2) DEFAULT 0.00,
    rating_count INT DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_recipes_owner ON recipes(owner_id);
CREATE INDEX IF NOT EXISTS idx_recipes_visibility ON recipes(visibility);
CREATE INDEX IF NOT EXISTS idx_recipes_category ON recipes(category);

-- 3. Ingredients Table
CREATE TABLE IF NOT EXISTS ingredients (
    id SERIAL PRIMARY KEY,
    recipe_id VARCHAR(255) NOT NULL REFERENCES recipes(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    quantity VARCHAR(100),
    unit VARCHAR(50),
    notes TEXT,
    sort_order INT DEFAULT 0
);

CREATE INDEX IF NOT EXISTS idx_ingredients_recipe ON ingredients(recipe_id);

-- 4. Recipe Steps Table
CREATE TABLE IF NOT EXISTS steps (
    id SERIAL PRIMARY KEY,
    recipe_id VARCHAR(255) NOT NULL REFERENCES recipes(id) ON DELETE CASCADE,
    position INT NOT NULL,
    instruction TEXT NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_steps_recipe ON steps(recipe_id);

-- 5. Favorites Table
CREATE TABLE IF NOT EXISTS favorites (
    user_id VARCHAR(255) NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    recipe_id VARCHAR(255) NOT NULL REFERENCES recipes(id) ON DELETE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, recipe_id)
);

-- 6. Collections Table
CREATE TABLE IF NOT EXISTS collections (
    id VARCHAR(255) PRIMARY KEY,
    owner_id VARCHAR(255) NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    is_public BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 7. Collection Recipes Table
CREATE TABLE IF NOT EXISTS collection_recipes (
    collection_id VARCHAR(255) NOT NULL REFERENCES collections(id) ON DELETE CASCADE,
    recipe_id VARCHAR(255) NOT NULL REFERENCES recipes(id) ON DELETE CASCADE,
    PRIMARY KEY (collection_id, recipe_id)
);

-- 8. Follows Table (Community Social)
CREATE TABLE IF NOT EXISTS follows (
    follower_id VARCHAR(255) NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    followed_id VARCHAR(255) NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (follower_id, followed_id)
);

-- 9. Ratings & Reviews Table
CREATE TABLE IF NOT EXISTS ratings (
    user_id VARCHAR(255) NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    recipe_id VARCHAR(255) NOT NULL REFERENCES recipes(id) ON DELETE CASCADE,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, recipe_id)
);

-- 10. Comments Table
CREATE TABLE IF NOT EXISTS comments (
    id VARCHAR(255) PRIMARY KEY,
    recipe_id VARCHAR(255) NOT NULL REFERENCES recipes(id) ON DELETE CASCADE,
    author_id VARCHAR(255) NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    body TEXT NOT NULL,
    status VARCHAR(50) DEFAULT 'ACTIVE',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 11. Subscriptions Table (Saborê Pro / Efí Pay)
CREATE TABLE IF NOT EXISTS subscriptions (
    user_id VARCHAR(255) PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    product_id VARCHAR(100) NOT NULL,
    status VARCHAR(50) NOT NULL, -- ACTIVE, CANCELLED, EXPIRED
    expiry_time TIMESTAMP WITH TIME ZONE,
    source VARCHAR(50) DEFAULT 'EFI_PAY',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 12. Audit Events Table (Privacy & Visibility Changes)
CREATE TABLE IF NOT EXISTS audit_events (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(255) REFERENCES users(id) ON DELETE SET NULL,
    action VARCHAR(100) NOT NULL,
    entity_type VARCHAR(100) NOT NULL,
    entity_id VARCHAR(255),
    details TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_audit_user ON audit_events(user_id);
