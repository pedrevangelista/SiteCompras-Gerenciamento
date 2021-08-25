-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 25-Mar-2019 às 22:34
-- Versão do servidor: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sutileza`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `celular` varchar(15) NOT NULL,
  `email` varchar(70) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `estado` varchar(30) NOT NULL,
  `endereco` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`cpf`, `nome`, `sexo`, `telefone`, `celular`, `email`, `senha`, `cidade`, `cep`, `estado`, `endereco`) VALUES
('12345678901', 'valdomiro dos santos', 'm', '123456789012345', '123456789012345', 'admin', 'admin', 'matchos laime', '123456789', 'fudido', 'rua cu bairro buceta 666'),
('14224244640', 'Lucas Khaled Rocha Brugger', 'm', '3135352752', '31989015810', 'khaledbrugger3@gmail.com', 'brugger5', 'Mateus Leme', '35670-000', 'Minas Gerais', 'Rua Maria das Dores Aguiar');

-- --------------------------------------------------------

--
-- Estrutura da tabela `grade`
--

CREATE TABLE `grade` (
  `id` int(11) NOT NULL,
  `id_grade` int(11) NOT NULL,
  `cor` varchar(30) NOT NULL,
  `tamanho` varchar(11) NOT NULL,
  `preco` float NOT NULL,
  `quantidade` int(11) NOT NULL,
  `promocao` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `grade`
--

INSERT INTO `grade` (`id`, `id_grade`, `cor`, `tamanho`, `preco`, `quantidade`, `promocao`) VALUES
(12, 19, 'preto/vermelho', '33', 89.9, 12, 10),
(12, 20, 'azul/verde', '33', 89.9, 9, 10),
(12, 21, 'azul/verde', '34', 89.9, 10, 10),
(12, 22, 'azul/verde', '35', 89.9, 10, 10),
(12, 23, 'azul/verde', '36', 89.9, 0, 10),
(12, 24, 'preto/vermelho', '34', 89.9, 9, 10),
(12, 25, 'preto/vermelho', '35', 89.9, 6, 10),
(13, 26, 'preto', '40', 104.9, 14, 0),
(13, 27, 'preto', '41', 104.9, 13, 0),
(13, 28, 'preto', '42', 104.9, 10, 0),
(13, 29, 'preto', '44', 104.9, 3, 0),
(13, 30, 'marrom', '39', 104.9, 3, 0),
(13, 31, 'marrom', '40', 104.9, 5, 0),
(14, 32, 'camuflado', 'M', 40, 20, 10),
(14, 33, 'camuflado', 'G', 40, 20, 10),
(14, 34, 'camuflado', 'P', 40, 20, 10),
(14, 35, 'camuflado', 'GG', 40, 10, 10),
(14, 36, 'camuflado', 'XG', 40, 5, 10),
(14, 37, 'camuflado', 'PP', 40, 5, 10),
(15, 38, 'Unica', 'M', 60, 12, 20),
(15, 39, 'Unica', 'G', 60, 12, 20),
(15, 40, 'Unica', 'P', 60, 9, 20),
(16, 41, 'Branco', 'M', 49.9, 15, 0),
(16, 42, 'Preto', 'M', 49.9, 12, 0),
(16, 43, 'Vermelho', 'M', 49.9, 13, 0),
(16, 44, 'Vermelho', 'G', 49.9, 6, 0),
(16, 45, 'Vermelho', 'P', 49.9, 15, 0),
(16, 46, 'Preto', 'P', 49.9, 15, 0),
(16, 47, 'Branco', 'P', 49.9, 15, 0),
(17, 48, 'Storm Tropper', 'Unico', 30, 16, 30),
(17, 49, 'Chewbacca', 'Unico', 30, 15, 30),
(18, 50, 'Unica', 'Unico', 59.9, 5, 0),
(19, 51, 'Unica', 'Unico', 5, 40, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `Id` int(11) NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Sexo` varchar(1) NOT NULL,
  `Descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`Id`, `Nome`, `Sexo`, `Descricao`) VALUES
(12, 'Tenis de Rodinha', 'U', 'Sapato de rodinha para as crianças serem felizes rolando por aí'),
(13, 'sapatenis', 'M', 'calçado sapatenis para os homens ficarem estilosos com sapato que é de velho mas ao mesmo tempo não é'),
(14, 'camisa do exército', 'M', 'depois do bolsonora se eleger, é o que nos resta usar...'),
(15, 'camisa flor e caveiras', 'M', 'flor e caveira? serio? isso é coisa de viado, mas ok. Se tá vendendo tá bom'),
(16, 'camisa ranço', 'F', 'essa é o pior produto falso da loja. Tenho ranço das camisas ranço'),
(17, 'meias fodas do star wars', 'U', 'Meu senhor de jesus cristo, essas meias são incríveis. Até comprei uma!'),
(18, 'meia foda do tubarão', 'U', 'duvido que isso exista, mas se existir eu quero!!'),
(19, 'pulseira de humanas', 'U', 'Um estudante de letras passou por aqui...');

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendas`
--

CREATE TABLE `vendas` (
  `id_venda` int(11) NOT NULL,
  `id_grade` int(11) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `data_compra` date NOT NULL,
  `preco` float NOT NULL,
  `enviado` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `vendas`
--

INSERT INTO `vendas` (`id_venda`, `id_grade`, `cpf`, `data_compra`, `preco`, `enviado`) VALUES
(1, 23, '14224244640', '2018-12-04', 80.91, 'n'),
(2, 31, '14224244640', '2018-12-04', 104.9, 'n'),
(3, 40, '12345678901', '2019-01-20', 48, 'n'),
(10, 20, '12345678901', '2019-01-21', 80.91, 'n');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cpf`);

--
-- Indexes for table `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`id_grade`);

--
-- Indexes for table `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `vendas`
--
ALTER TABLE `vendas`
  ADD PRIMARY KEY (`id_venda`),
  ADD KEY `id_grade` (`id_grade`),
  ADD KEY `cpf` (`cpf`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `grade`
--
ALTER TABLE `grade`
  MODIFY `id_grade` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `vendas`
--
ALTER TABLE `vendas`
  MODIFY `id_venda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `vendas`
--
ALTER TABLE `vendas`
  ADD CONSTRAINT `vendas_ibfk_1` FOREIGN KEY (`id_grade`) REFERENCES `grade` (`id_grade`),
  ADD CONSTRAINT `vendas_ibfk_2` FOREIGN KEY (`cpf`) REFERENCES `clientes` (`cpf`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
