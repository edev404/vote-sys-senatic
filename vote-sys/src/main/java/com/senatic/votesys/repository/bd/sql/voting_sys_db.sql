-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 09-03-2023 a las 10:30:26
-- Versión del servidor: 8.0.32-0ubuntu0.22.04.2
-- Versión de PHP: 8.1.2-1ubuntu2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `voting_sys_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aprendices`
--

CREATE TABLE `aprendices` (
  `id` varchar(10) NOT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `celular` varchar(10) DEFAULT NULL,
  `correoElectronico` varchar(75) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `ficha` varchar(7) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `programa` varchar(250) DEFAULT NULL,
  `tipoDocumento` varchar(2) DEFAULT NULL,
  `idUsuario` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `candidatos`
--

CREATE TABLE `candidatos` (
  `id` int NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `propuestas` varchar(250) DEFAULT NULL,
  `idAprendiz` varchar(10) DEFAULT NULL,
  `idImagen` int DEFAULT NULL,
  `idVotacion` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagenes`
--

CREATE TABLE `imagenes` (
  `id` int NOT NULL,
  `image` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfiles`
--

CREATE TABLE `perfiles` (
  `id` int NOT NULL,
  `perfil` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `perfiles`
--

INSERT INTO `perfiles` (`id`, `perfil`) VALUES
(1, 'APRENDIZ'),
(2, 'ADMINISTRADOR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `UsuarioPerfil`
--

CREATE TABLE `UsuarioPerfil` (
  `idUsuario` int NOT NULL,
  `idPerfil` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `UsuarioPerfil`
--

INSERT INTO `UsuarioPerfil` (`idUsuario`, `idPerfil`) VALUES
(28, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int NOT NULL,
  `fechaRegistro` datetime(6) DEFAULT NULL,
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `username` varchar(10) DEFAULT NULL,
  `estatus` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `fechaRegistro`, `password`, `username`, `estatus`) VALUES
(28, '2023-03-07 07:57:54.807000', '$2a$10$v/IejcfOvUDJbHJJcATEteBrZUGHRpfOPxmPJ9wBjNTB1336WUBHO', '3516924708', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `votaciones`
--

CREATE TABLE `votaciones` (
  `id` int NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fechaTerminacion` datetime DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `votos`
--

CREATE TABLE `votos` (
  `id` int NOT NULL,
  `fechaRegistro` datetime(6) DEFAULT NULL,
  `valido` bit(1) DEFAULT NULL,
  `idAprendiz` varchar(10) DEFAULT NULL,
  `idCandidato` int DEFAULT NULL,
  `idVotacion` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `aprendices`
--
ALTER TABLE `aprendices`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `aprendices_unique` (`correoElectronico`,`celular`),
  ADD KEY `FKhytrudneruo7533gl5wbk9odo` (`idUsuario`);

--
-- Indices de la tabla `candidatos`
--
ALTER TABLE `candidatos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `candidatos_unique` (`idAprendiz`),
  ADD KEY `FKfnxrvbv3yjlx7crddgvypsh89` (`idImagen`),
  ADD KEY `FKh1rqb7msjhxrs1rbtlr4oa46i` (`idVotacion`);

--
-- Indices de la tabla `imagenes`
--
ALTER TABLE `imagenes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `perfiles`
--
ALTER TABLE `perfiles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `UsuarioPerfil`
--
ALTER TABLE `UsuarioPerfil`
  ADD KEY `FK6hb726hs510g1pyqah1v7sq1t` (`idPerfil`),
  ADD KEY `FKlvqmb6ql3csag6dfsgwfdp2vi` (`idUsuario`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuarios_unique` (`username`);

--
-- Indices de la tabla `votaciones`
--
ALTER TABLE `votaciones`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `votos`
--
ALTER TABLE `votos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `votos_unique` (`idVotacion`,`idAprendiz`),
  ADD KEY `FKtm7xkwlfqc7lt0075yqf3j4b` (`idAprendiz`),
  ADD KEY `FKci82tovc2snpas4ov101m4ve1` (`idCandidato`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `candidatos`
--
ALTER TABLE `candidatos`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `imagenes`
--
ALTER TABLE `imagenes`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1045668031;

--
-- AUTO_INCREMENT de la tabla `perfiles`
--
ALTER TABLE `perfiles`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=112;

--
-- AUTO_INCREMENT de la tabla `votaciones`
--
ALTER TABLE `votaciones`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `votos`
--
ALTER TABLE `votos`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `aprendices`
--
ALTER TABLE `aprendices`
  ADD CONSTRAINT `FKhytrudneruo7533gl5wbk9odo` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `candidatos`
--
ALTER TABLE `candidatos`
  ADD CONSTRAINT `FK8tfgrgsxj7fca13fwnwn5fjak` FOREIGN KEY (`idAprendiz`) REFERENCES `aprendices` (`id`),
  ADD CONSTRAINT `FKfnxrvbv3yjlx7crddgvypsh89` FOREIGN KEY (`idImagen`) REFERENCES `imagenes` (`id`),
  ADD CONSTRAINT `FKh1rqb7msjhxrs1rbtlr4oa46i` FOREIGN KEY (`idVotacion`) REFERENCES `votaciones` (`id`);

--
-- Filtros para la tabla `UsuarioPerfil`
--
ALTER TABLE `UsuarioPerfil`
  ADD CONSTRAINT `FK6hb726hs510g1pyqah1v7sq1t` FOREIGN KEY (`idPerfil`) REFERENCES `perfiles` (`id`),
  ADD CONSTRAINT `FKlvqmb6ql3csag6dfsgwfdp2vi` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `votos`
--
ALTER TABLE `votos`
  ADD CONSTRAINT `FK3g2njpxay950a468rexypypjo` FOREIGN KEY (`idVotacion`) REFERENCES `votaciones` (`id`),
  ADD CONSTRAINT `FKci82tovc2snpas4ov101m4ve1` FOREIGN KEY (`idCandidato`) REFERENCES `candidatos` (`id`),
  ADD CONSTRAINT `FKtm7xkwlfqc7lt0075yqf3j4b` FOREIGN KEY (`idAprendiz`) REFERENCES `aprendices` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
