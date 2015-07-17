-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 17-Jul-2015 às 03:39
-- Versão do servidor: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `_rotinakids`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `atividade`
--

CREATE TABLE IF NOT EXISTS `atividade` (
`pk_atividade` tinyint(4) NOT NULL,
  `nome_atividade` varchar(150) NOT NULL,
  `descricao_atividade` varchar(200) NOT NULL,
  `grupoEtario_atividade` tinyint(1) NOT NULL,
  `qtdMoedas_atividade` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `atividade`
--

INSERT INTO `atividade` (`pk_atividade`, `nome_atividade`, `descricao_atividade`, `grupoEtario_atividade`, `qtdMoedas_atividade`) VALUES
(3, 'Guardar os brinquedos', 'guardar os brinquedos na caixa', 0, 10),
(6, 'lavar louca', 'lavar os pratos', 1, 15),
(7, 'Tirar o Lixo da Casa', 'retirar o lixo da porta', 2, 30),
(8, 'Guardar Sapatos', 'guardar os sapatos na sapateira', 0, 20);

-- --------------------------------------------------------

--
-- Estrutura da tabela `atividade_atribuida`
--

CREATE TABLE IF NOT EXISTS `atividade_atribuida` (
`pk_atividadeAtribuida` tinyint(2) NOT NULL,
  `data_atividade_atividadeAtribuida` varchar(10) NOT NULL,
  `cod_filho` tinyint(4) NOT NULL,
  `status_atividade_atividadeAtribuida` tinyint(1) NOT NULL,
  `cod_atividade` tinyint(4) NOT NULL,
  `qntdMoedas_atividade` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `atividade_atribuida`
--

INSERT INTO `atividade_atribuida` (`pk_atividadeAtribuida`, `data_atividade_atividadeAtribuida`, `cod_filho`, `status_atividade_atividadeAtribuida`, `cod_atividade`, `qntdMoedas_atividade`) VALUES
(17, '17/07/2015', 14, 1, 7, 30),
(20, '17/07/2015', 15, 1, 3, 10),
(21, '17/07/2015', 15, 0, 8, 20);

-- --------------------------------------------------------

--
-- Estrutura da tabela `banco_moedas`
--

CREATE TABLE IF NOT EXISTS `banco_moedas` (
`pk_bancoMoedas` tinyint(2) NOT NULL,
  `cod_filho` tinyint(4) NOT NULL,
  `saldo` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `banco_moedas`
--

INSERT INTO `banco_moedas` (`pk_bancoMoedas`, `cod_filho`, `saldo`) VALUES
(2, 14, 30),
(3, 15, 30),
(4, 16, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `filho`
--

CREATE TABLE IF NOT EXISTS `filho` (
  `pk_filho` tinyint(4) NOT NULL,
  `cod_responsavel` tinyint(4) NOT NULL,
  `idade_filho` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `filho`
--

INSERT INTO `filho` (`pk_filho`, `cod_responsavel`, `idade_filho`) VALUES
(14, 1, 7),
(15, 1, 3),
(16, 1, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `responsavel`
--

CREATE TABLE IF NOT EXISTS `responsavel` (
  `pk_responsavel` tinyint(4) NOT NULL,
  `login` varchar(20) NOT NULL,
  `senha` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `responsavel`
--

INSERT INTO `responsavel` (`pk_responsavel`, `login`, `senha`) VALUES
(1, 'amanda', 'amanda'),
(5, 'allef', 'allef'),
(6, 'mario', 'mario');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
`pk_usuario` tinyint(4) NOT NULL COMMENT 'Chave Primaria',
  `nome_usuario` varchar(50) NOT NULL,
  `cpf` varchar(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`pk_usuario`, `nome_usuario`, `cpf`) VALUES
(1, 'Amanda Barbosa', '09509509515'),
(5, 'allef', '56'),
(6, 'mario', '4545'),
(14, 'Jose Antonio Barbosa', '1411414'),
(15, 'Luiza Maria', '141414'),
(16, 'Marcos Andre', '171717');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `atividade`
--
ALTER TABLE `atividade`
 ADD PRIMARY KEY (`pk_atividade`);

--
-- Indexes for table `atividade_atribuida`
--
ALTER TABLE `atividade_atribuida`
 ADD PRIMARY KEY (`pk_atividadeAtribuida`), ADD UNIQUE KEY `fk_qtdMoedas_atividade` (`qntdMoedas_atividade`), ADD KEY `fk_codFilho` (`cod_filho`), ADD KEY `fk_codAtividade` (`cod_atividade`);

--
-- Indexes for table `banco_moedas`
--
ALTER TABLE `banco_moedas`
 ADD PRIMARY KEY (`pk_bancoMoedas`), ADD KEY `fk_codFilhoMoedas` (`cod_filho`);

--
-- Indexes for table `filho`
--
ALTER TABLE `filho`
 ADD PRIMARY KEY (`pk_filho`), ADD KEY `fk_codResponsavelFilho` (`cod_responsavel`);

--
-- Indexes for table `responsavel`
--
ALTER TABLE `responsavel`
 ADD PRIMARY KEY (`pk_responsavel`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
 ADD PRIMARY KEY (`pk_usuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `atividade`
--
ALTER TABLE `atividade`
MODIFY `pk_atividade` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `atividade_atribuida`
--
ALTER TABLE `atividade_atribuida`
MODIFY `pk_atividadeAtribuida` tinyint(2) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `banco_moedas`
--
ALTER TABLE `banco_moedas`
MODIFY `pk_bancoMoedas` tinyint(2) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
MODIFY `pk_usuario` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT 'Chave Primaria',AUTO_INCREMENT=17;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `atividade_atribuida`
--
ALTER TABLE `atividade_atribuida`
ADD CONSTRAINT `fk_codFilho` FOREIGN KEY (`cod_filho`) REFERENCES `filho` (`pk_filho`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `banco_moedas`
--
ALTER TABLE `banco_moedas`
ADD CONSTRAINT `fk_codFilhoMoedas` FOREIGN KEY (`cod_filho`) REFERENCES `filho` (`pk_filho`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `filho`
--
ALTER TABLE `filho`
ADD CONSTRAINT `fk_codResponsavelFilho` FOREIGN KEY (`cod_responsavel`) REFERENCES `responsavel` (`pk_responsavel`),
ADD CONSTRAINT `fk_codUsuarioFilho` FOREIGN KEY (`pk_filho`) REFERENCES `usuario` (`pk_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `responsavel`
--
ALTER TABLE `responsavel`
ADD CONSTRAINT `fk_codUsuarioResponsavel` FOREIGN KEY (`pk_responsavel`) REFERENCES `usuario` (`pk_usuario`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
