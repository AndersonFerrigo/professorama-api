CREATE TABLE `aluno`(
	`id` int not null PRIMARY KEY AUTO_INCREMENT,
	`nome` varchar(60) not null,
	`nivel_escolar` varchar(20) not null,
	`ano_letivo` varchar(20) not null,
	`usuario` varchar(50) not null,
	`senha` varchar(50) not null
);
	
CREATE TABLE `professor`(
	`id` int not null PRIMARY KEY AUTO_INCREMENT,
	`nome` varchar(60) not null,
	`usuario` varchar(50) not null,
	`senha` varchar(50) not null
); 

CREATE TABLE `atividade`(
	`id` int not null PRIMARY KEY AUTO_INCREMENT,
	`materia` varchar(60) not null,
	`data_inicio` varchar(20) not null,
	`data_entrega` varchar(20) not null,
	`descricao` varchar(200) not null,
	`senha` varchar(50) not null,
	`professor_id` int DEFAULT null,		
	`aluno_id` int DEFAULT null 
); 
