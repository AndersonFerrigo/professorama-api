CREATE TABLE `aluno`(
	`id` int not null PRIMARY KEY AUTO_INCREMENT,
	`nome` varchar(60) not null,
	`serie` varchar(20) not null,
	`perfil` varchar(20) not null,
	`usuario` varchar(50) not null,
	`senha` varchar(500) not null
);
	
CREATE TABLE `professor`(
	`id` int not null PRIMARY KEY AUTO_INCREMENT,
	`nome` varchar(60) not null,
	`perfil` varchar(20) not null,
	`materia` varchar(60) not null,
	`usuario` varchar(50) not null,
	`senha` varchar(50) not null
); 

CREATE TABLE `atividade`(
	`id` int not null PRIMARY KEY AUTO_INCREMENT,
	`materia` varchar(60) not null,
	`serie` varchar(60) not null,
	`data_inicio` varchar(20) not null,
	`data_entrega` varchar(20) not null,
	`descricao` text not null,
	`professor_id` int not null,
	 CONSTRAINT `professor_id` foreign key (`professor_id`) references `professor`(`id`)
	
); 
