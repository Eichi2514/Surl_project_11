show databases;

DROP DATABASE IF EXISTS `surl_dev`;
CREATE DATABASE `surl_dev`;
USE `surl_dev`;

show tables;

desc article;
TRUNCATE article;
SELECT * FROM article;

desc member;
TRUNCATE member;
SELECT * FROM member;

desc surl;
TRUNCATE surl;
SELECT * FROM surl;