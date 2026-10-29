CREATE DATABASE IF NOT EXISTS fitpocket;
USE fitpocket;
CREATE TABLE USER (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(255) UNIQUE,
    userPassword VARCHAR(255) NOT NULL,
    isAdmin BOOLEAN NOT NULL
);

CREATE TABLE PROFILE (
    userId INT PRIMARY KEY,
    description VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    link VARCHAR(255),
    location VARCHAR(255),
    phone BIGINT,
    FOREIGN KEY (userId)
        REFERENCES USER (userId)
);

CREATE TABLE CATEGORY (
    categoryId INT AUTO_INCREMENT,
    userId INT NOT NULL,
    categoryName VARCHAR(255) NOT NULL,
    PRIMARY KEY (categoryId),
    FOREIGN KEY (userId)
        REFERENCES USER (userId)
);

CREATE TABLE CURRENCY (
    currencyId INT AUTO_INCREMENT,
    currencyName VARCHAR(255) NOT NULL,
    currencySymbol VARCHAR(255) NOT NULL,
    PRIMARY KEY (currencyId)
);

CREATE TABLE BUDGET (
    budgetId INT AUTO_INCREMENT,
    userId INT NOT NULL,
    budgetName VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    currencyId INT,
    creationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (budgetId),
    FOREIGN KEY (userId)
        REFERENCES USER (userId),
	FOREIGN KEY (currencyId)
		REFERENCES CURRENCY (currencyId)
);

CREATE TABLE EXPENSE (
    expenseId INT AUTO_INCREMENT,
    budgetId INT NOT NULL,
    expenseName VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    categoryId INT NOT NULL,
    amount DOUBLE NOT NULL,
    isRecurrent BOOLEAN NOT NULL,
    creationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (expenseId),
    FOREIGN KEY (budgetId)
        REFERENCES BUDGET (budgetId),
    FOREIGN KEY (categoryId)
        REFERENCES CATEGORY (categoryId)
);

CREATE TABLE INCOME (
    incomeId INT AUTO_INCREMENT,
    budgetId INT NOT NULL,
    incomeName VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    categoryId INT NOT NULL,
    amount DOUBLE NOT NULL,
    isRecurrent BOOLEAN NOT NULL,
    creationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (incomeId),
    FOREIGN KEY (budgetId)
        REFERENCES BUDGET (budgetId),
    FOREIGN KEY (categoryId)
        REFERENCES CATEGORY (categoryId)
);
