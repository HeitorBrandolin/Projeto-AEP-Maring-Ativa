-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 17-Nov-2022 às 09:55
-- Versão do servidor: 10.4.25-MariaDB
-- versão do PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `maringaativa`
--
CREATE DATABASE IF NOT EXISTS `maringaativa` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `maringaativa`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `agenda`
--

CREATE TABLE `agenda` (
  `IDAGENDA` int(11) NOT NULL,
  `DISPONIBILIDADE` enum('DISPONIVEL','INDISPONIVEL') NOT NULL,
  `TEMPO_ALOCACAO` varchar(20) NOT NULL,
  `ID_CLIENTE` int(11) NOT NULL,
  `ID_CENTRO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `centro`
--

CREATE TABLE `centro` (
  `IDCENTRO` int(11) NOT NULL,
  `NOME` varchar(30) DEFAULT NULL,
  `DESCRICAO` varchar(200) DEFAULT NULL,
  `AVISO` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `centro`
--

INSERT INTO `centro` (`IDCENTRO`, `NOME`, `DESCRICAO`, `AVISO`) VALUES
(1, 'Ginásio Chico Neto', 'Lorem ipsum mi quam proin feugiat nullam dictumst eu, metus vulputate justo felis ullamcorper ultrices morbi sapien nisl, molestie ante quis vehicula socios qu varius lacinia. consectetur lacus nisi q', NULL),
(2, 'Centro Esportivo Mandacaru', 'Lorem ipsum vehicula neque augue tellus aliquam', 'Lorem ipsum');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `IDCLIENTE` int(11) NOT NULL,
  `NOME_CLIENTE` varchar(30) NOT NULL,
  `CPF` varchar(15) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `SENHA` varchar(20) NOT NULL,
  `TELEFONE` varchar(20) NOT NULL,
  `DATA_NASC` varchar(10) NOT NULL,
  `SEXO` enum('M','F') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco_centro`
--

CREATE TABLE `endereco_centro` (
  `IDENDERECO_CENTRO` int(11) NOT NULL,
  `CEP_CENTRO` varchar(10) NOT NULL,
  `RUA_CENTRO` varchar(50) NOT NULL,
  `BAIRRO_CENTRO` varchar(30) NOT NULL,
  `ESTADO_CENTRO` char(2) NOT NULL,
  `CIDADE_CENTRO` varchar(30) NOT NULL,
  `NENDE_CENTRO` varchar(8) NOT NULL,
  `COMPLEMENTO_CENTRO` varchar(30) DEFAULT NULL,
  `ID_CENTRO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `endereco_centro`
--

INSERT INTO `endereco_centro` (`IDENDERECO_CENTRO`, `CEP_CENTRO`, `RUA_CENTRO`, `BAIRRO_CENTRO`, `ESTADO_CENTRO`, `CIDADE_CENTRO`, `NENDE_CENTRO`, `COMPLEMENTO_CENTRO`, `ID_CENTRO`) VALUES
(1, '87020-020', 'Rua Prof. Lauro Eduardo Werneck', 'Zona 7', 'PR', 'Maringá', '0', NULL, 1),
(2, '87080-560', 'R. Lázaro Benedito Carnielli', 'Jardim Canada', 'PR', 'Maringá', '000', 'Lorem ipsum neque augue tellus', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco_cliente`
--

CREATE TABLE `endereco_cliente` (
  `IDENDERECO_CLIENTE` int(11) NOT NULL,
  `CEP_CLIENTE` varchar(10) NOT NULL,
  `RUA_CLIENTE` varchar(30) NOT NULL,
  `BAIRRO_CLIENTE` varchar(30) NOT NULL,
  `ESTADO_CLIENTE` char(2) NOT NULL,
  `CIDADE_CLIENTE` varchar(20) NOT NULL,
  `NENDE_CLIENTE` varchar(8) NOT NULL,
  `COMPLEMENTO_CLIENTE` varchar(10) DEFAULT NULL,
  `ID_CLIENTE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`IDAGENDA`),
  ADD KEY `ID_CLIENTE` (`ID_CLIENTE`),
  ADD KEY `ID_CENTRO` (`ID_CENTRO`);

--
-- Índices para tabela `centro`
--
ALTER TABLE `centro`
  ADD PRIMARY KEY (`IDCENTRO`);

--
-- Índices para tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`IDCLIENTE`),
  ADD UNIQUE KEY `CPF` (`CPF`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`);

--
-- Índices para tabela `endereco_centro`
--
ALTER TABLE `endereco_centro`
  ADD PRIMARY KEY (`IDENDERECO_CENTRO`),
  ADD UNIQUE KEY `ID_CENTRO` (`ID_CENTRO`);

--
-- Índices para tabela `endereco_cliente`
--
ALTER TABLE `endereco_cliente`
  ADD PRIMARY KEY (`IDENDERECO_CLIENTE`),
  ADD KEY `ID_CLIENTE` (`ID_CLIENTE`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `agenda`
--
ALTER TABLE `agenda`
  MODIFY `IDAGENDA` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `centro`
--
ALTER TABLE `centro`
  MODIFY `IDCENTRO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `IDCLIENTE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `endereco_centro`
--
ALTER TABLE `endereco_centro`
  MODIFY `IDENDERECO_CENTRO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `endereco_cliente`
--
ALTER TABLE `endereco_cliente`
  MODIFY `IDENDERECO_CLIENTE` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `agenda`
--
ALTER TABLE `agenda`
  ADD CONSTRAINT `agenda_ibfk_1` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `cliente` (`IDCLIENTE`),
  ADD CONSTRAINT `agenda_ibfk_2` FOREIGN KEY (`ID_CENTRO`) REFERENCES `centro` (`IDCENTRO`);

--
-- Limitadores para a tabela `endereco_centro`
--
ALTER TABLE `endereco_centro`
  ADD CONSTRAINT `endereco_centro_ibfk_1` FOREIGN KEY (`ID_CENTRO`) REFERENCES `centro` (`IDCENTRO`);

--
-- Limitadores para a tabela `endereco_cliente`
--
ALTER TABLE `endereco_cliente`
  ADD CONSTRAINT `ID_CLIENTE` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `cliente` (`IDCLIENTE`) ON DELETE CASCADE,
  ADD CONSTRAINT `endereco_cliente_ibfk_1` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `cliente` (`IDCLIENTE`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
