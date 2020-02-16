-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 16-02-2020 a las 10:41:23
-- Versión del servidor: 10.1.41-MariaDB-0+deb9u1
-- Versión de PHP: 7.0.33-0+deb9u6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hibernate`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cliente`
--

CREATE TABLE `Cliente` (
  `email` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `apelidos` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nome` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Empregado`
--

CREATE TABLE `Empregado` (
  `nif` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `nome` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `apelidos` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Empregado`
--

INSERT INTO `Empregado` (`nif`, `nome`, `apelidos`) VALUES
('54', 'Paulo', 'Burro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empregado_tenda`
--

CREATE TABLE `empregado_tenda` (
  `empregado_nif` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `tenda_nome` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `horas_semanais` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `empregado_tenda`
--

INSERT INTO `empregado_tenda` (`empregado_nif`, `tenda_nome`, `horas_semanais`) VALUES
('54', 'proba', 23);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Produto`
--

CREATE TABLE `Produto` (
  `nome` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `prezo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Produto`
--

INSERT INTO `Produto` (`nome`, `descripcion`, `prezo`) VALUES
('1', '2', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `produto_tenda`
--

CREATE TABLE `produto_tenda` (
  `produto_nome` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `tenda_nome` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `produto_tenda`
--

INSERT INTO `produto_tenda` (`produto_nome`, `tenda_nome`, `stock`) VALUES
('1', 'proba', 234);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Provincia`
--

CREATE TABLE `Provincia` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Provincia`
--

INSERT INTO `Provincia` (`id`, `nome`) VALUES
(2, 'Albacete'),
(3, 'Alicante/Alacant'),
(4, 'Almería'),
(1, 'Araba/Álava'),
(33, 'Asturias'),
(5, 'Ávila'),
(6, 'Badajoz'),
(7, 'Balears, Illes'),
(8, 'Barcelona'),
(48, 'Bizkaia'),
(9, 'Burgos'),
(10, 'Cáceres'),
(11, 'Cádiz'),
(39, 'Cantabria'),
(12, 'Castellón/Castelló'),
(51, 'Ceuta'),
(13, 'Ciudad Real'),
(14, 'Córdoba'),
(15, 'Coruña, A'),
(16, 'Cuenca'),
(20, 'Gipuzkoa'),
(17, 'Girona'),
(18, 'Granada'),
(19, 'Guadalajara'),
(21, 'Huelva'),
(22, 'Huesca'),
(23, 'Jaén'),
(24, 'León'),
(25, 'Lleida'),
(27, 'Lugo'),
(28, 'Madrid'),
(29, 'Málaga'),
(52, 'Melilla'),
(30, 'Murcia'),
(31, 'Navarra'),
(32, 'Ourense'),
(34, 'Palencia'),
(35, 'Palmas, Las'),
(36, 'Pontevedra'),
(26, 'Rioja, La'),
(37, 'Salamanca'),
(38, 'Santa Cruz de Tenerife'),
(40, 'Segovia'),
(41, 'Sevilla'),
(42, 'Soria'),
(43, 'Tarragona'),
(44, 'Teruel'),
(45, 'Toledo'),
(46, 'Valencia/València'),
(47, 'Valladolid'),
(49, 'Zamora'),
(50, 'Zaragoza');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Tenda`
--

CREATE TABLE `Tenda` (
  `nome` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `cidade` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `id_provincia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Tenda`
--

INSERT INTO `Tenda` (`nome`, `cidade`, `id_provincia`) VALUES
('2', 'a estrada', 36),
('proba', 'A Estrada', 50);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Cliente`
--
ALTER TABLE `Cliente`
  ADD PRIMARY KEY (`email`);

--
-- Indices de la tabla `Empregado`
--
ALTER TABLE `Empregado`
  ADD PRIMARY KEY (`nif`);

--
-- Indices de la tabla `empregado_tenda`
--
ALTER TABLE `empregado_tenda`
  ADD PRIMARY KEY (`empregado_nif`,`tenda_nome`),
  ADD KEY `nome_tenda` (`tenda_nome`);

--
-- Indices de la tabla `Produto`
--
ALTER TABLE `Produto`
  ADD PRIMARY KEY (`nome`);

--
-- Indices de la tabla `produto_tenda`
--
ALTER TABLE `produto_tenda`
  ADD PRIMARY KEY (`produto_nome`,`tenda_nome`),
  ADD KEY `nome_tenda` (`tenda_nome`);

--
-- Indices de la tabla `Provincia`
--
ALTER TABLE `Provincia`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `idx_provincia_nome` (`nome`);

--
-- Indices de la tabla `Tenda`
--
ALTER TABLE `Tenda`
  ADD PRIMARY KEY (`nome`),
  ADD KEY `Tenda_ibfk_1` (`id_provincia`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empregado_tenda`
--
ALTER TABLE `empregado_tenda`
  ADD CONSTRAINT `empregado_tenda_ibfk_1` FOREIGN KEY (`empregado_nif`) REFERENCES `Empregado` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `empregado_tenda_ibfk_2` FOREIGN KEY (`tenda_nome`) REFERENCES `Tenda` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `produto_tenda`
--
ALTER TABLE `produto_tenda`
  ADD CONSTRAINT `produto_tenda_ibfk_1` FOREIGN KEY (`produto_nome`) REFERENCES `Produto` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `produto_tenda_ibfk_2` FOREIGN KEY (`tenda_nome`) REFERENCES `Tenda` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Tenda`
--
ALTER TABLE `Tenda`
  ADD CONSTRAINT `Tenda_ibfk_1` FOREIGN KEY (`id_provincia`) REFERENCES `Provincia` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
