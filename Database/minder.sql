-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-04-2021 a las 13:05:19
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
  `data` date NOT NULL,
  KEY `pair_ibfk_2` (`idDesti`),
  KEY `pair_ibfk_1` (`idOrigen`) USING BTREE
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuari`
--

INSERT INTO `usuari` (`nomPila`, `nickname`, `edat`, `uuid`, `tipusCompte`, `email`, `password`, `pathImage`, `descripcio`, `llenguatgeDeProgramacio`) VALUES
('Edmon Bosch', 'boschedmon', 21, 1, 'Normal', 'edmonbosch@students.salle.url.edu', 'hola', '/pathincompleto', 'Sóc l\'Edmon estudio GTAS i m\'encanta la programació, de gran vull treballar a Google o Facebook.', 'Java'),
('David Marquet', 'david.marquet', 25, 2, 'Premium', 'david.marquet@students.salle.url.edu', 'hola2', '/pathincomplete', 'Hola més endavant ja editare la meva descripció', 'Java'),
('Joan Casals', 'joan.casals', 21, 3, 'Premium', 'joan.casals@students.salle.url.edu', 'hola3', '/pathincomplete', 'Hola més endavant ja editare la meva descripció', 'Javascript'),
('Xavier Garrigos', 'xavier.garrigos', 21, 4, 'Normal', 'xavier.garrigos@students.salle.url.edu', 'hola4', '/pathicnomplete', 'Hola més endavant ja editare la meva descripció', 'CSS'),
('Pol Muñoz', 'pol.munoz', 23, 5, 'Premium', 'pol.munoz@salle.url.edu', 'hola5', '/pathincomplete', 'Hola més endavant ja editare la meva descripció', 'Java'),
('Artur Alcoverro', 'artur.alcoverro', 21, 6, 'Normal', 'artur.alcoverro@students.salle.url.edu', 'hola6', '/pathincomplete', 'Hola més endavant ja editare la meva descripció', 'Javascript'),
('Xavier Gomez', 'xavier.gomez', 26, 7, 'Normal', 'xavier.gomez@students.salle.url.edu', 'hola7', '/pathincomplete', 'Hola més endavant ja editare la meva descripció', 'html'),
('Ricard Viñolas', 'richard.vinolas', 21, 8, 'Normal', 'ricard.vinolas@students.salle.url.edu', 'hola8', '/pathincomplete', 'Hola més endavant ja editare la meva descripció', 'html'),
('Elon Musk', 'elon.musk', 38, 9, 'Premium', 'elon.musk@salle.url.edu', 'hola10', '/pathincomplete', 'Hola més endavant ja editare la meva descripció', 'C'),
('Bill Gates', 'bill.gates', 58, 10, 'Normal', 'bill.gates@salle.url.edu', 'hola11', '/pathincomplete', 'Hola més endavant ja editare la meva descripció', 'Javascript'),
('Emily Ermion', 'ermion.potter', 24, 11, 'Normal', 'emily.ermion@salle.url.edu', 'hola12', '/pathincomplete', 'Hola més endavant ja editare la meva descripció', 'C'),
('Jennifer Aniston', 'jenifer.aniston', 45, 12, 'Normal', 'jenifer.aniston@salle.url.edu', 'hola13', '/pathincomplete', 'Hola més endavant ja editare la meva descripció', 'css'),
('Angela Merkel', 'angela.merkel', 52, 13, 'Normal', 'angela.merkel@salle.url.edu', 'hola14', '/pathincomplete', 'Hola més endavant ja editare la meva descripció', 'html'),
('Carla Divinity', 'diva.carla', 27, 14, 'Normal', 'carla.divinity@salle.url.edu', 'hola15', '/pathincomplete', 'Hola més endavant ja editare la meva descripció', 'Javascript');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `xat`
--

DROP TABLE IF EXISTS `xat`;
CREATE TABLE IF NOT EXISTS `xat` (
  `idOrigen` int(11) NOT NULL,
  `idDesti` int(11) NOT NULL,
  `missatge` text NOT NULL,
  `data` date NOT NULL,
  KEY `xat_ibfk_1` (`idOrigen`),
  KEY `xat_ibfk_2` (`idDesti`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pair`
--
ALTER TABLE `pair`
  ADD CONSTRAINT `pair_ibfk_1` FOREIGN KEY (`idOrigen`) REFERENCES `usuari` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pair_ibfk_2` FOREIGN KEY (`idDesti`) REFERENCES `usuari` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `xat`
--
ALTER TABLE `xat`
  ADD CONSTRAINT `xat_ibfk_1` FOREIGN KEY (`idOrigen`) REFERENCES `usuari` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `xat_ibfk_2` FOREIGN KEY (`idDesti`) REFERENCES `usuari` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
