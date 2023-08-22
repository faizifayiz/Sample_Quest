-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 22, 2023 at 02:02 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sample_quest`
--

-- --------------------------------------------------------

--
-- Table structure for table `constructor`
--

CREATE TABLE IF NOT EXISTS `constructor` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `job` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `company` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `phone` varchar(200) NOT NULL,
  `employee_meet` varchar(200) NOT NULL,
  `employee_to_meet_label` varchar(200) NOT NULL,
  `identification` varchar(200) NOT NULL,
  `identification_label` varchar(200) NOT NULL,
  `purpose_of_visit` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `constructor`
--

INSERT INTO `constructor` (`id`, `name`, `job`, `address`, `company`, `email`, `phone`, `employee_meet`, `employee_to_meet_label`, `identification`, `identification_label`, `purpose_of_visit`) VALUES
(1, '', '', '', '', '', '', '', '', '', '', ''),
(2, '', '', '', '', '', '', '', '', '', '', ''),
(3, '', '', '', '', '', '', '', '', '', '', ''),
(4, 'fayiz ', 'dfhh', 'ghhhh', 'vhhj', 'vhhj', '1234567890', 'Employee1', 'yhhhhh', 'ffhuik', 'ghhjjjjikb', 'fyujbii'),
(5, '', '', '', '', '', '', '', '', '', '', ''),
(6, 'bshshs', 'syshsh', 'hHzha', 'gzgshs', 'gzgshs', '6921426308', 'employee 2', 'hsgssh', 'hshshs', 'hshshshs', 'visit');

-- --------------------------------------------------------

--
-- Table structure for table `employee_to_meet`
--

CREATE TABLE IF NOT EXISTS `employee_to_meet` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `employee_to_meet`
--

INSERT INTO `employee_to_meet` (`id`, `name`) VALUES
(1, 'employee 1'),
(2, 'employee 2');

-- --------------------------------------------------------

--
-- Table structure for table `purpose_of_visit`
--

CREATE TABLE IF NOT EXISTS `purpose_of_visit` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `purpose` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `purpose_of_visit`
--

INSERT INTO `purpose_of_visit` (`id`, `purpose`) VALUES
(1, 'enquiry'),
(2, 'visit');

-- --------------------------------------------------------

--
-- Table structure for table `visitors`
--

CREATE TABLE IF NOT EXISTS `visitors` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `job` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `company` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `phone` varchar(200) NOT NULL,
  `employee_meet` varchar(200) NOT NULL,
  `employee_to_meet_label` varchar(200) NOT NULL,
  `identification` varchar(200) NOT NULL,
  `identification_label` varchar(200) NOT NULL,
  `purpose_of_visit` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `visitors`
--

INSERT INTO `visitors` (`id`, `name`, `job`, `address`, `company`, `email`, `phone`, `employee_meet`, `employee_to_meet_label`, `identification`, `identification_label`, `purpose_of_visit`) VALUES
(1, 'gagaahah', 'hshshs', 'bhshshs', 'gshshw', 'gshshw', '1234567890', 'employee 1', 'hahahaah', 'gagshs', 'hahaha', ''),
(2, 'gsgsha', 'gsgaah', 'Bbaah', 'bshaha', 'bshaha', '8921426308', 'employee 1', 'gsshhshs', 'hsgshs', 'hsgshshs', 'enquiry');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
