CREATE TABLE IF NOT EXISTS article (
    id BIGINT AUTO_INCREMENT primary key,
    title varchar(255) not null,
    content varchar(255) not null
);

CREATE TABLE comment(
    id BIGINT AUTO_INCREMENT primary key,
    body VARCHAR(1000) NOT NULL
);

INSERT INTO article (title, content, created_at, updated_at) VALUES('제목1', '내용1', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES('제목2', '내용2', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES('제목3', '내용3', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES('제목4', '내용4', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES('제목5', '내용5', NOW(), NOW());

INSERT INTO comment (id, body, created_at, updated_at) VALUES(1, '댓글1', NOW(), NOW());
INSERT INTO comment (id, body, created_at, updated_at) VALUES(2, '댓글2', NOW(), NOW());