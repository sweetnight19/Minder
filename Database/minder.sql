-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-03-2021 a las 20:58:48
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `minder`
--
CREATE DATABASE IF NOT EXISTS `minder` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `minder`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pair`
--

DROP TABLE IF EXISTS `pair`;
CREATE TABLE IF NOT EXISTS `pair` (
  `idOrigen` int(11) NOT NULL,
  `idDesti` int(11) NOT NULL,
  `matchDuo` tinyint(1) NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuari`
--

DROP TABLE IF EXISTS `usuari`;
CREATE TABLE IF NOT EXISTS `usuari` (
  `nomPila` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `edat` int(11) NOT NULL,
  `uuid` int(11) NOT NULL AUTO_INCREMENT,
  `tipusCompte` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `pathImage` varchar(255) NOT NULL,
  `descripcio` text NOT NULL,
  `llenguatgeDeProgramacio` varchar(255) NOT NULL,
  PRIMARY KEY (`uuid`,`nickname`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `xat`
--

DROP TABLE IF EXISTS `xat`;
CREATE TABLE IF NOT EXISTS `xat` (
  `idOrigen` int(11) NOT NULL,
  `idDesti` int(11) NOT NULL,
  `missatge` text NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
