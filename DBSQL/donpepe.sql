CREATE DATABASE  IF NOT EXISTS `donpepe` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `donpepe`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: donpepe.cgfauwbxunzx.us-east-1.rds.amazonaws.com    Database: donpepe
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `SPRING_SESSION`
--

DROP TABLE IF EXISTS `SPRING_SESSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SPRING_SESSION` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPRING_SESSION`
--

LOCK TABLES `SPRING_SESSION` WRITE;
/*!40000 ALTER TABLE `SPRING_SESSION` DISABLE KEYS */;
INSERT INTO `SPRING_SESSION` VALUES ('29ca1ffb-127d-4094-87c9-349fb7ecb02d','31c1eedc-ef99-447c-bb93-c1cf026643c9',1591522232357,1591536393352,1800,1591538193352,'a20160555@pucp.edu.pe'),('6bbdce4e-a15e-48d6-b269-55ed13eb2dcf','f1cdbeb8-748b-434b-9a6b-64d12ce810fb',1591533982988,1591535689685,1800,1591537489685,'a.velardep@pucp.edu.pe'),('ebd7ec71-e680-46fd-9757-a70d2edc97cd','089da134-d1b8-46b7-9254-ac53e5a4ed5e',1591533550128,1591535615604,1800,1591537415604,NULL);
/*!40000 ALTER TABLE `SPRING_SESSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SPRING_SESSION_ATTRIBUTES`
--

DROP TABLE IF EXISTS `SPRING_SESSION_ATTRIBUTES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SPRING_SESSION_ATTRIBUTES` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `SPRING_SESSION` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPRING_SESSION_ATTRIBUTES`
--

LOCK TABLES `SPRING_SESSION_ATTRIBUTES` WRITE;
/*!40000 ALTER TABLE `SPRING_SESSION_ATTRIBUTES` DISABLE KEYS */;
INSERT INTO `SPRING_SESSION_ATTRIBUTES` VALUES ('29ca1ffb-127d-4094-87c9-349fb7ecb02d','cart',_binary '¨\Ì\0sr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0w\0\0\0sr\0+exparcialg1.demo.constantes.ProductoCarritopî\€W¨≤\r\0Z\0	availableI\0cantidadL\0	productost\0)Lexparcialg1/demo/Entity/ProductosEntity;xp\0\0\0sr\0\'exparcialg1.demo.Entity.ProductosEntity\Álëö\riS	\0I\0stockL\0codproductot\0Ljava/lang/String;L\0descripcionq\0~\0L\0fotoq\0~\0L\0nombreq\0~\0L\0preciounitariot\0Ljava/math/BigDecimal;xp\0\0\0t\0A0007At\08Filete de At√∫n Florida en Aceite de Girasol. Lata 170g.t\0\0t\0\rAt√∫n Floridasr\0java.math.BigDecimalT\«W˘Å(O\0I\0scaleL\0intValt\0Ljava/math/BigInteger;xr\0java.lang.NumberÜ¨ïî\‡ã\0\0xp\0\0\0sr\0java.math.BigIntegerå¸ü©;˚\0I\0bitCountI\0	bitLengthI\0firstNonzeroByteNumI\0lowestSetBitI\0signum[\0	magnitudet\0[Bxq\0~\0ˇˇˇˇˇˇˇˇˇˇˇ˛ˇˇˇ˛\0\0\0ur\0[B¨Û¯T\‡\0\0xp\0\0\0%xxx'),('29ca1ffb-127d-4094-87c9-349fb7ecb02d','numcart',_binary '¨\Ì\0sr\0java.lang.Integer‚†§˜Åá8\0I\0valuexr\0java.lang.NumberÜ¨ïî\‡ã\0\0xp\0\0\0'),('29ca1ffb-127d-4094-87c9-349fb7ecb02d','org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN',_binary '¨\Ì\0sr\06org.springframework.security.web.csrf.DefaultCsrfTokenZ\Ô∑\»/¢˚\’\0L\0\nheaderNamet\0Ljava/lang/String;L\0\rparameterNameq\0~\0L\0tokenq\0~\0xpt\0X-CSRF-TOKENt\0_csrft\0$6726fa4a-b433-4f8f-ae54-9880ac5a110e'),('29ca1ffb-127d-4094-87c9-349fb7ecb02d','SPRING_SECURITY_CONTEXT',_binary '¨\Ì\0sr\0=org.springframework.security.core.context.SecurityContextImpl\0\0\0\0\0\0\0L\0authenticationt\02Lorg/springframework/security/core/Authentication;xpsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0\0L\0credentialst\0Ljava/lang/Object;L\0	principalq\0~\0xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailsq\0~\0xpsr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0\0L\0rolet\0Ljava/lang/String;xpt\0Gestorxq\0~\0\rsr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\0	127.0.0.1t\0$8da1b782-1a85-4e1e-ad8d-fdeda095dd33psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiest\0Ljava/util/Set;L\0passwordq\0~\0L\0usernameq\0~\0xpsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0\nsr\0java.util.TreeSet›òPìï\Ìá[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0\0\0xpw\0\0\0q\0~\0xpt\0a20160555@pucp.edu.pe'),('29ca1ffb-127d-4094-87c9-349fb7ecb02d','usuario',_binary '¨\Ì\0sr\0&exparcialg1.demo.Entity.UsuariosEntityÜØ´$VS\»Y\0I\0dniI\0\nidusuariosL\0apellidot\0Ljava/lang/String;L\0correoq\0~\0L\0enabledt\0Ljava/lang/Boolean;L\0nombreq\0~\0L\0pwdq\0~\0L\0rolt\0%Lexparcialg1/demo/Entity/RolesEntity;xpWò¸\0\0\0t\0Vargast\0a20160555@pucp.edu.pesr\0java.lang.Boolean\Õ rÄ’ú˙\Ó\0Z\0valuexpt\0	Katherinet\0<$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROSsr\0#exparcialg1.demo.Entity.RolesEntityÚçã\≈D\0I\0idrolesL\0	nombrerolq\0~\0xp\0\0\0t\0Gestor'),('6bbdce4e-a15e-48d6-b269-55ed13eb2dcf','cart',_binary '¨\Ì\0sr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0\0w\0\0\0\0x'),('6bbdce4e-a15e-48d6-b269-55ed13eb2dcf','numcart',_binary '¨\Ì\0sr\0java.lang.Integer‚†§˜Åá8\0I\0valuexr\0java.lang.NumberÜ¨ïî\‡ã\0\0xp\0\0\0\0'),('6bbdce4e-a15e-48d6-b269-55ed13eb2dcf','org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN',_binary '¨\Ì\0sr\06org.springframework.security.web.csrf.DefaultCsrfTokenZ\Ô∑\»/¢˚\’\0L\0\nheaderNamet\0Ljava/lang/String;L\0\rparameterNameq\0~\0L\0tokenq\0~\0xpt\0X-CSRF-TOKENt\0_csrft\0$49d67c74-7f53-4d88-aa16-24e1d4821536'),('6bbdce4e-a15e-48d6-b269-55ed13eb2dcf','SPRING_SECURITY_CONTEXT',_binary '¨\Ì\0sr\0=org.springframework.security.core.context.SecurityContextImpl\0\0\0\0\0\0\0L\0authenticationt\02Lorg/springframework/security/core/Authentication;xpsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0\0L\0credentialst\0Ljava/lang/Object;L\0	principalq\0~\0xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailsq\0~\0xpsr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0\0L\0rolet\0Ljava/lang/String;xpt\0Gestorxq\0~\0\rsr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\00:0:0:0:0:0:0:1t\0$d8237648-429d-48c7-a5ff-46d3ac077c72psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiest\0Ljava/util/Set;L\0passwordq\0~\0L\0usernameq\0~\0xpsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0\nsr\0java.util.TreeSet›òPìï\Ìá[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0\0\0xpw\0\0\0q\0~\0xpt\0a.velardep@pucp.edu.pe'),('6bbdce4e-a15e-48d6-b269-55ed13eb2dcf','usuario',_binary '¨\Ì\0sr\0&exparcialg1.demo.Entity.UsuariosEntityÜØ´$VS\»Y\0I\0dniI\0\nidusuariosL\0apellidot\0Ljava/lang/String;L\0correoq\0~\0L\0enabledt\0Ljava/lang/Boolean;L\0nombreq\0~\0L\0pwdq\0~\0L\0rolt\0%Lexparcialg1/demo/Entity/RolesEntity;xp6≠¸\0\0\0t\0Velardet\0a.velardep@pucp.edu.pesr\0java.lang.Boolean\Õ rÄ’ú˙\Ó\0Z\0valuexpt\0Allisont\0<$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROSsr\0#exparcialg1.demo.Entity.RolesEntityÚçã\≈D\0I\0idrolesL\0	nombrerolq\0~\0xp\0\0\0t\0Gestor'),('ebd7ec71-e680-46fd-9757-a70d2edc97cd','org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN',_binary '¨\Ì\0sr\06org.springframework.security.web.csrf.DefaultCsrfTokenZ\Ô∑\»/¢˚\’\0L\0\nheaderNamet\0Ljava/lang/String;L\0\rparameterNameq\0~\0L\0tokenq\0~\0xpt\0X-CSRF-TOKENt\0_csrft\0$253d63c9-e77b-43ff-8097-c03d8302c935'),('ebd7ec71-e680-46fd-9757-a70d2edc97cd','SPRING_SECURITY_LAST_EXCEPTION',_binary '¨\Ì\0sr\0Corg.springframework.security.authentication.BadCredentialsException&N¸dÆ]\0\0xr\09org.springframework.security.core.AuthenticationExceptionQ0\‚gT\0\0xr\0\Zjava.lang.RuntimeExceptionû_G\n4É\Â\0\0xr\0java.lang.Exception\–˝>\Z;\ƒ\0\0xr\0java.lang.Throwable\’\∆5\'9w∏\À\0L\0causet\0Ljava/lang/Throwable;L\0\rdetailMessaget\0Ljava/lang/String;[\0\nstackTracet\0[Ljava/lang/StackTraceElement;L\0suppressedExceptionst\0Ljava/util/List;xpq\0~\0	t\0Bad credentialsur\0[Ljava.lang.StackTraceElement;F*<<˝\"9\0\0xp\0\0\0:sr\0java.lang.StackTraceElementa	≈ö&6›Ö\0I\0\nlineNumberL\0declaringClassq\0~\0L\0fileNameq\0~\0L\0\nmethodNameq\0~\0xp\0\0\0ót\0Yorg.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvidert\0.AbstractUserDetailsAuthenticationProvider.javat\0authenticatesq\0~\0\r\0\0\0\«t\0;org.springframework.security.authentication.ProviderManagert\0ProviderManager.javaq\0~\0sq\0~\0\r\0\0\0\€q\0~\0q\0~\0q\0~\0sq\0~\0\r\0\0\0_t\0Torg.springframework.security.web.authentication.UsernamePasswordAuthenticationFiltert\0)UsernamePasswordAuthenticationFilter.javat\0attemptAuthenticationsq\0~\0\r\0\0\0\‘t\0Vorg.springframework.security.web.authentication.AbstractAuthenticationProcessingFiltert\0+AbstractAuthenticationProcessingFilter.javat\0doFiltersq\0~\0\r\0\0Nt\0Dorg.springframework.security.web.FilterChainProxy$VirtualFilterChaint\0FilterChainProxy.javaq\0~\0sq\0~\0\r\0\0\0tt\0Corg.springframework.security.web.authentication.logout.LogoutFiltert\0LogoutFilter.javaq\0~\0sq\0~\0\r\0\0Nq\0~\0q\0~\0 q\0~\0sq\0~\0\r\0\0\0çt\00org.springframework.security.web.csrf.CsrfFiltert\0CsrfFilter.javat\0doFilterInternalsq\0~\0\r\0\0\0wt\03org.springframework.web.filter.OncePerRequestFiltert\0OncePerRequestFilter.javaq\0~\0sq\0~\0\r\0\0Nq\0~\0q\0~\0 q\0~\0sq\0~\0\r\0\0\0\\t\0:org.springframework.security.web.header.HeaderWriterFiltert\0HeaderWriterFilter.javat\0doHeadersAftersq\0~\0\r\0\0\0Mq\0~\0.q\0~\0/q\0~\0(sq\0~\0\r\0\0\0wq\0~\0*q\0~\0+q\0~\0sq\0~\0\r\0\0Nq\0~\0q\0~\0 q\0~\0sq\0~\0\r\0\0\0it\0Iorg.springframework.security.web.context.SecurityContextPersistenceFiltert\0%SecurityContextPersistenceFilter.javaq\0~\0sq\0~\0\r\0\0Nq\0~\0q\0~\0 q\0~\0sq\0~\0\r\0\0\08t\0Worg.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFiltert\0%WebAsyncManagerIntegrationFilter.javaq\0~\0(sq\0~\0\r\0\0\0wq\0~\0*q\0~\0+q\0~\0sq\0~\0\r\0\0Nq\0~\0q\0~\0 q\0~\0sq\0~\0\r\0\0\0\◊t\01org.springframework.security.web.FilterChainProxyq\0~\0 q\0~\0(sq\0~\0\r\0\0\0≤q\0~\0>q\0~\0 q\0~\0sq\0~\0\r\0\0ft\04org.springframework.web.filter.DelegatingFilterProxyt\0\ZDelegatingFilterProxy.javat\0invokeDelegatesq\0~\0\r\0\0q\0~\0Aq\0~\0Bq\0~\0sq\0~\0\r\0\0\0¡t\0/org.apache.catalina.core.ApplicationFilterChaint\0ApplicationFilterChain.javat\0internalDoFiltersq\0~\0\r\0\0\0¶q\0~\0Fq\0~\0Gq\0~\0sq\0~\0\r\0\0\0dt\03org.springframework.web.filter.RequestContextFiltert\0RequestContextFilter.javaq\0~\0(sq\0~\0\r\0\0\0wq\0~\0*q\0~\0+q\0~\0sq\0~\0\r\0\0\0¡q\0~\0Fq\0~\0Gq\0~\0Hsq\0~\0\r\0\0\0¶q\0~\0Fq\0~\0Gq\0~\0sq\0~\0\r\0\0\0]t\00org.springframework.web.filter.FormContentFiltert\0FormContentFilter.javaq\0~\0(sq\0~\0\r\0\0\0wq\0~\0*q\0~\0+q\0~\0sq\0~\0\r\0\0\0¡q\0~\0Fq\0~\0Gq\0~\0Hsq\0~\0\r\0\0\0¶q\0~\0Fq\0~\0Gq\0~\0sq\0~\0\r\0\0\0çt\0<org.springframework.session.web.http.SessionRepositoryFiltert\0SessionRepositoryFilter.javaq\0~\0(sq\0~\0\r\0\0\0Rt\09org.springframework.session.web.http.OncePerRequestFilterq\0~\0+q\0~\0sq\0~\0\r\0\0\0¡q\0~\0Fq\0~\0Gq\0~\0Hsq\0~\0\r\0\0\0¶q\0~\0Fq\0~\0Gq\0~\0sq\0~\0\r\0\0\0\…t\06org.springframework.web.filter.CharacterEncodingFiltert\0CharacterEncodingFilter.javaq\0~\0(sq\0~\0\r\0\0\0wq\0~\0*q\0~\0+q\0~\0sq\0~\0\r\0\0\0¡q\0~\0Fq\0~\0Gq\0~\0Hsq\0~\0\r\0\0\0¶q\0~\0Fq\0~\0Gq\0~\0sq\0~\0\r\0\0\0\ t\0-org.apache.catalina.core.StandardWrapperValvet\0StandardWrapperValve.javat\0invokesq\0~\0\r\0\0\0`t\0-org.apache.catalina.core.StandardContextValvet\0StandardContextValve.javaq\0~\0fsq\0~\0\r\0\0t\03org.apache.catalina.authenticator.AuthenticatorBaset\0AuthenticatorBase.javaq\0~\0fsq\0~\0\r\0\0\0ãt\0*org.apache.catalina.core.StandardHostValvet\0StandardHostValve.javaq\0~\0fsq\0~\0\r\0\0\0\\t\0+org.apache.catalina.valves.ErrorReportValvet\0ErrorReportValve.javaq\0~\0fsq\0~\0\r\0\0\0Jt\0,org.apache.catalina.core.StandardEngineValvet\0StandardEngineValve.javaq\0~\0fsq\0~\0\r\0\0Wt\0+org.apache.catalina.connector.CoyoteAdaptert\0CoyoteAdapter.javat\0servicesq\0~\0\r\0\0ut\0(org.apache.coyote.http11.Http11Processort\0Http11Processor.javaq\0~\0ysq\0~\0\r\0\0\0At\0(org.apache.coyote.AbstractProcessorLightt\0AbstractProcessorLight.javat\0processsq\0~\0\r\0\0dt\04org.apache.coyote.AbstractProtocol$ConnectionHandlert\0AbstractProtocol.javaq\0~\0Äsq\0~\0\r\0\06t\06org.apache.tomcat.util.net.NioEndpoint$SocketProcessort\0NioEndpoint.javat\0doRunsq\0~\0\r\0\0\01t\0.org.apache.tomcat.util.net.SocketProcessorBaset\0SocketProcessorBase.javat\0runsq\0~\0\r\0\0}t\0\'java.util.concurrent.ThreadPoolExecutort\0ThreadPoolExecutor.javat\0	runWorkersq\0~\0\r\0\0pt\0.java.util.concurrent.ThreadPoolExecutor$Workerq\0~\0éq\0~\0ãsq\0~\0\r\0\0\0=t\0:org.apache.tomcat.util.threads.TaskThread$WrappingRunnablet\0TaskThread.javaq\0~\0ãsq\0~\0\r\0\0\Ït\0java.lang.Threadt\0Thread.javaq\0~\0ãsr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listq\0~\0xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0ùx');
/*!40000 ALTER TABLE `SPRING_SESSION_ATTRIBUTES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidohasproductos`
--

DROP TABLE IF EXISTS `pedidohasproductos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidohasproductos` (
  `codpedido` varchar(17) NOT NULL,
  `codproducto` varchar(6) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `subtotal` decimal(4,2) NOT NULL,
  PRIMARY KEY (`codproducto`,`codpedido`),
  KEY `fk_pedido_has_productos_pedido1_idx` (`codpedido`),
  KEY `fk_pedido_has_productos_productos1_idx` (`codproducto`),
  CONSTRAINT `fk_pedidohasproducto_pedido` FOREIGN KEY (`codpedido`) REFERENCES `pedidos` (`codpedido`),
  CONSTRAINT `fk_pedidohasproducto_producto` FOREIGN KEY (`codproducto`) REFERENCES `productos` (`codproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidohasproductos`
--

LOCK TABLES `pedidohasproductos` WRITE;
/*!40000 ALTER TABLE `pedidohasproductos` DISABLE KEYS */;
INSERT INTO `pedidohasproductos` VALUES ('PE0706202015','A0007A',2,10.98),('PE0706202016','A0007A',1,5.49),('PE0706202013','A0009R',2,20.98),('PE0706202016','A0009R',2,20.98),('PE0706202016','A0012S',1,13.99),('PE080620205','A0012S',3,41.97),('PE090620204','A0012S',4,55.96),('PE230520208','A0012S',4,55.96),('PE0706202013','A0013R',1,4.70),('PE0706202014','A0022I',1,20.00),('PE0706202014','A0025I',3,15.30),('PE050520201','E0023R',2,10.20),('PE230520207','E0023R',2,10.20),('PE2305202011','N0004A',2,5.58),('PE2305202012','N0004A',2,5.58),('PE050520201','P0005A',1,14.22),('PE2305202010','P0005A',6,85.32),('PE230520202','P0005A',1,14.22),('PE230520203','P0005A',1,14.22),('PE230520206','P0005A',1,14.22),('PE230520209','P0005A',1,14.22);
/*!40000 ALTER TABLE `pedidohasproductos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `codpedido` varchar(17) NOT NULL,
  `idusuarios` int(11) NOT NULL,
  `preciototal` decimal(4,2) NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`codpedido`),
  KEY `fk_pedido_usuarios_idx` (`idusuarios`),
  CONSTRAINT `fk_pedido_usuarios` FOREIGN KEY (`idusuarios`) REFERENCES `usuarios` (`idusuarios`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES ('PE050520201',20,24.42,'2020-05-05 03:49:00'),('PE0706202013',15,25.68,'2020-06-07 05:31:37'),('PE0706202014',16,35.30,'2020-06-07 06:55:02'),('PE0706202015',15,10.98,'2020-06-07 07:23:16'),('PE0706202016',16,40.46,'2020-06-07 07:58:26'),('PE080620205',5,41.97,'2020-06-08 04:40:00'),('PE090620204',4,55.96,'2020-06-09 04:40:00'),('PE2305202010',13,85.32,'2020-05-23 08:49:00'),('PE2305202011',7,5.58,'2020-05-23 08:49:00'),('PE2305202012',11,5.58,'2020-05-23 09:49:00'),('PE230520202',20,14.22,'2020-05-23 03:39:00'),('PE230520203',4,14.22,'2020-05-23 03:49:00'),('PE230520206',10,14.22,'2020-05-23 04:49:00'),('PE230520207',10,10.20,'2020-05-23 05:49:00'),('PE230520208',12,55.96,'2020-05-23 06:49:00'),('PE230520209',12,14.22,'2020-05-23 07:49:00');
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `codproducto` varchar(6) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `preciounitario` decimal(4,2) NOT NULL,
  `foto` varchar(10) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`codproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES ('A0007A','At√∫n Florida','Filete de At√∫n Florida en Aceite de Girasol. Lata 170g.',5.49,'',30),('A0009R','Avena Quaker','Avena Quaker. Bolsa 1Kg.',10.49,NULL,21),('A0012S','Az√∫car Bells','Az√∫car Rubia Bells. Bolsa 5Kg.',13.99,NULL,15),('A0013R','At√∫n Primor','Trozos de At√∫n Prmir en Aceite Vegetal. Lata 170g.',4.70,NULL,20),('A0022I','Alli','sdfghj',20.00,'Bamb√∫.jpg',23),('A0025I','Atun Nicollini','50 kg grasa.',5.10,'',23),('E0023R','er','F',5.10,'',0),('G0010O','Galleta Oreo','Galleta Oreo Regular. Paquete 6un.',3.10,NULL,35),('G0011A','Galleta Soda','Galleta Soda Field. Paquete 6un.',2.30,NULL,40),('G0015A','Gaseosa Coca Cola','Gaseosa Coca Cola. Botella 1.5L. Paquete 2un.',11.00,NULL,25),('K0008A','Ketchup Alacena','Ketchup Alacena. Doypack 380g.',4.90,NULL,26),('L0017N','Lavavajillas Ayud√≠n','Lavavajillas L√≠quido Ayud√≠n Lim√≥n. Botella 1.2L.',14.90,NULL,16),('M0001A','Mermelada Gloria','Mermelada Gloria Fresa. Frasco 320Gr.',4.90,NULL,25),('M0003A','Mandarina','Mandarina Happy Frut. Malla 2Kg. Precio x kg.',7.98,NULL,8),('M0014S','Manzanilla McColins','Manzanilla McCollins. Caja 100un.',8.29,NULL,22),('N0004A','Naranja','Naranja para jugo. Malla 1Kg. Precio x kg. ',2.79,NULL,17),('O0026S','OldTimes','Botella 2L.',23.50,NULL,20),('P0005A','Pi√±a','Pi√±a Golden. Malla 2.5Kg. Precio x kg.',14.22,NULL,12),('P0016A','Papel Toalla Nova','Papel Toalla Nova Cl√°sica Mega Rollo. Paquete 4un.',8.90,NULL,18),('P0018E','Papel Higi√©nico Suave','Papel Hgi√©nico Suave Rindemax Doble Hoja. Paquete 40un.',23.50,NULL,5),('S0002A','Spaguetti Molitalia','Spaguetti Molitalia. Bolsa 500g.',2.30,NULL,15);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `idroles` int(11) NOT NULL AUTO_INCREMENT,
  `nombrerol` varchar(15) NOT NULL,
  PRIMARY KEY (`idroles`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Administrador'),(2,'Gestor'),(3,'Registrado');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idusuarios` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  `apellido` varchar(40) NOT NULL,
  `dni` int(8) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `idroles` int(11) NOT NULL,
  PRIMARY KEY (`idusuarios`),
  KEY `fk_usuarios_roles1_idx` (`idroles`),
  CONSTRAINT `fk_usuarios_roles1` FOREIGN KEY (`idroles`) REFERENCES `roles` (`idroles`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Branko','Zambrano',72569846,'jbzambrano@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(2,'Ivan','Mata',73649618,'ivan.mata@pucp.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(3,'Sarita','Atanacio',72647891,'atanacio.sarita@pucp.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(4,'Eduardo','Sanchez',73694857,'a20122509@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(5,'Leonardo','Ayala',76248735,'a20125980@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(6,'Royer','Yangali',73694829,'r.yangali@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(7,'Sergio','Muro',75698243,'sergio.muroc@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(8,'Anthony','Rojas',78412935,'a20145976@pucp.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(9,'Ruth','Araujo',76395284,'a20150875@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(10,'Alejandro','Noblecilla',78639546,'noblecilla.ae@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(11,'Alex','Montes',79632486,'montes.alex@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(12,'Eric','Benites',73416895,'eric.benites@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(13,'Josue','Romero',76326489,'jcromero@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(14,'Sebastian','Rojas',76523846,'sebastian.rojas@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(15,'Katherine','Vargas',72849660,'a20160555@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,2),(16,'Allison','Velarde',70692348,'a.velardep@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,2),(17,'Alonso','Guevara',72930065,'a20160679@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,2),(18,'Lewis','Rengifo',76945532,'l.rengifo@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(19,'Julio','Carri√≥n',72319845,'a20161448@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(20,'Gustavo','Meza',73695486,'meza.gustavo@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(21,'Fernando','Guzm√°n',73268459,'a20162001@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(22,'Gabriel','Castillo',75264892,'a20166059@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(23,'Samuel','Ayala',72849365,'a20166262@pucp.edu.pe','$2y$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(24,'Samuel','Caro',74463290,'a20170086@pucp.edu.pe','$12$09ohg5Qo4YtolJy6Ry6KleY/bWfwQkC7XogXita3siZW0A43Z2ROS',1,3),(25,'Stuardo','Lucho',12345678,'admin','$2y$12$OseVKyUw.1s1JOZBm7gxROlE9bvxf7DeC9X79rvyCMiRch.pv7s8O',1,1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-07  8:27:06
