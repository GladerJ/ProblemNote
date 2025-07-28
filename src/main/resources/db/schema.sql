-- 创建数据库
CREATE DATABASE IF NOT EXISTS problem_note DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE problem_note;

-- 用户表（只需一个账户）
CREATE TABLE user (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      username VARCHAR(50) UNIQUE NOT NULL,
                      password VARCHAR(100) NOT NULL
);

-- 科目表
CREATE TABLE subject (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(100) UNIQUE NOT NULL
);

-- 标签表（每个科目下的标签）
CREATE TABLE tag (
                     id INT PRIMARY KEY AUTO_INCREMENT,
                     name VARCHAR(100) NOT NULL,
                     subject_id INT NOT NULL,
                     UNIQUE(name, subject_id),
                     FOREIGN KEY(subject_id) REFERENCES subject(id) ON DELETE CASCADE
);

-- 题目表（原question，现problem）
CREATE TABLE problem (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         content TEXT NOT NULL,
                         answer TEXT NOT NULL,
                         subject_id INT NOT NULL,
                         tag_id INT NOT NULL,
                         FOREIGN KEY(subject_id) REFERENCES subject(id) ON DELETE CASCADE,
                         FOREIGN KEY(tag_id) REFERENCES tag(id) ON DELETE CASCADE
);

CREATE TABLE collection (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(100) NOT NULL
);

CREATE TABLE collection_problem (
                                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                    collection_id BIGINT NOT NULL,
                                    problem_id INT NOT NULL, -- Changed from BIGINT to INT to match problem table
                                    FOREIGN KEY (collection_id) REFERENCES collection(id),
                                    FOREIGN KEY (problem_id) REFERENCES problem(id),
                                    UNIQUE KEY uk_collection_problem (collection_id, problem_id)
);
-- 初始化管理员账户（可选，密码建议后续加密）
INSERT INTO user (username, password) VALUES ('admin', 'admin123');