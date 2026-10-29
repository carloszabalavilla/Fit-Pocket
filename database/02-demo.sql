USE fitpocket;
INSERT INTO USER (userName,userPassword,isAdmin) VALUES ('demo','49b21bdf0da32d1fe882e6c992b42bc305524d6ed85276eed144ea078b15e80f',false);
INSERT INTO PROFILE (userId,description,email) VALUES (1,'Perfil ficticio para demo local','demo@example.invalid');
INSERT INTO CATEGORY (userId,categoryName) VALUES (1,'General');
INSERT INTO CURRENCY (currencyName,currencySymbol) VALUES ('Euro','EUR');
INSERT INTO BUDGET (userId,budgetName,description,currencyId) VALUES (1,'Presupuesto de ejemplo','Datos ficticios',1);
INSERT INTO INCOME (budgetId,incomeName,description,categoryId,amount,isRecurrent) VALUES (1,'Ingreso de ejemplo','Datos ficticios',1,1500,false);
INSERT INTO EXPENSE (budgetId,expenseName,description,categoryId,amount,isRecurrent) VALUES (1,'Gasto de ejemplo','Datos ficticios',1,125,false);
