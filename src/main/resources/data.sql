CREATE TABLE IF NOT EXISTS article (
    id BIGINT AUTO_INCREMENT primary key,
    title varchar(255) not null,
    content varchar(255) not null
);

INSERT INTO article (title, content, created_at, updated_at) VALUES('title1', 'contents1', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES('title2', 'contents2', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES('title3', 'contents3', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES('title4', 'contents4', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES('title5', 'contents5', NOW(), NOW());