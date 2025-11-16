DROP TABLE IF EXISTS address;
CREATE TABLE address (
    address_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    road_addr VARCHAR(255) NULL,
    detail_addr VARCHAR(255) NULL
);

DROP TABLE IF EXISTS region;
CREATE TABLE region (
    region_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    region_name VARCHAR(255) NULL
);

DROP TABLE IF EXISTS cuisine;
CREATE TABLE cuisine (
    cuisine_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cuisine_name ENUM('KOREAN','WESTERN','CHINESE','JAPANESE','OTHER') NULL
);

DROP TABLE IF EXISTS term;
CREATE TABLE term (
    term_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    term_name VARCHAR(255) NULL,
    term_text TEXT NULL,
    term_type ENUM('REQUIRED','OPTIONAL') NULL
);

DROP TABLE IF EXISTS user;
CREATE TABLE user (
    user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NULL,
    user_gender ENUM('MALE','FEMALE','OTHER') NULL,
    user_birth DATETIME NULL,
    user_point BIGINT NULL DEFAULT 0,
    is_active BIT NULL DEFAULT b'1',
    is_social BIT NULL DEFAULT b'0',
    social_id VARCHAR(255) NULL,
    user_social_provider VARCHAR(255) NULL,
    user_profile_url VARCHAR(255) NULL,
    user_nickname VARCHAR(255) NULL,
    user_email VARCHAR(255) NULL,
    user_phone VARCHAR(50) NULL,
    address_id BIGINT NOT NULL,
    created_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (address_id) REFERENCES address(address_id)
);

DROP TABLE IF EXISTS store;
CREATE TABLE store (
    store_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    store_name VARCHAR(255) NULL,
    store_type ENUM('RESTAURANT','CAFE','ETC') NULL,
    store_rating DECIMAL(3,1) NULL,
    address_id BIGINT NOT NULL,
    region_id BIGINT NOT NULL,
    created_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (region_id) REFERENCES region(region_id),
    FOREIGN KEY (address_id) REFERENCES address(address_id)
);

DROP TABLE IF EXISTS mission;
CREATE TABLE mission (
    mission_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    mission_point BIGINT NULL,
    mission_code BIGINT NULL,
    mission_cost BIGINT NULL,
    mission_due INT NULL,
    mission_status ENUM('IN_PROGRESS','SUCCESS','FAILED','NOT_YET') NULL,
    store_id BIGINT NOT NULL,
    created_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (store_id) REFERENCES store(store_id)
);

DROP TABLE IF EXISTS user_mission;
CREATE TABLE user_mission (
    user_mission_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_mission_status ENUM('IN_PROGRESS','SUCCESS','FAILED') NULL,
    user_id BIGINT NOT NULL,
    mission_id BIGINT NOT NULL,
    created_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (mission_id) REFERENCES mission(mission_id)
);

DROP TABLE IF EXISTS user_term;
CREATE TABLE user_term (
    user_term_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    term_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (term_id) REFERENCES term(term_id)
);

DROP TABLE IF EXISTS user_preference_cuisine;
CREATE TABLE user_preference_cuisine (
    user_preference_cusine_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    cuisine_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (cuisine_id) REFERENCES cuisine(cuisine_id)
);

DROP TABLE IF EXISTS review;
CREATE TABLE review (
    review_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    review_text VARCHAR(255) NULL,
    review_rating DECIMAL(3,1) NULL,
    user_id BIGINT NOT NULL,
    store_id BIGINT NOT NULL,
    created_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (store_id) REFERENCES store(store_id),
    CHECK (review_rating >= 0.0 AND review_rating <= 5.0)
);

DROP TABLE IF EXISTS review_image;
CREATE TABLE review_image (
    review_image_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    review_image_url VARCHAR(255) NULL,
    review_id BIGINT NOT NULL,
    FOREIGN KEY (review_id) REFERENCES review(review_id)
);

DROP TABLE IF EXISTS review_comment;
CREATE TABLE review_comment (
    review_comment_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    review_comment_text VARCHAR(255) NULL,
    review_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (review_id) REFERENCES review(review_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

DROP TABLE IF EXISTS question;
CREATE TABLE question (
    question_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    question_title VARCHAR(255) NULL,
    question_text VARCHAR(255) NULL,
    question_type ENUM('GENERAL','PAYMENT','ETC') NULL,
    created_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    is_answered BIT NULL DEFAULT b'0',
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

DROP TABLE IF EXISTS question_image;
CREATE TABLE question_image (
    question_image_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    question_image_url VARCHAR(255) NULL,
    question_id BIGINT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(question_id)
);

DROP TABLE IF EXISTS question_comment;
CREATE TABLE question_comment (
    question_comment_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    question_comment_text VARCHAR(255) NULL,
    question_id BIGINT NOT NULL,
    created_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (question_id) REFERENCES question(question_id)
);
