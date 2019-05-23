-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2017 at 06:36 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbakhir`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbladmin`
--

CREATE TABLE `tbladmin` (
  `id` int(11) NOT NULL,
  `uname` varchar(100) NOT NULL,
  `upwd` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbladmin`
--

INSERT INTO `tbladmin` (`id`, `uname`, `upwd`) VALUES
(1, 'root', 'toor');

-- --------------------------------------------------------

--
-- Table structure for table `tblanggota`
--

CREATE TABLE `tblanggota` (
  `idAnggota` int(11) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `telp` varchar(15) DEFAULT NULL,
  `tglLahir` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblanggota`
--

INSERT INTO `tblanggota` (`idAnggota`, `nama`, `alamat`, `telp`, `tglLahir`) VALUES
(9, 'jktyjt', 'dyjed', 'jydtdjd', '19-Oct-1995'),
(21, 'jhgjhgjghjs', 'kjhkhd', '58344', '12-Nov-2015');

-- --------------------------------------------------------

--
-- Table structure for table `tblbuku`
--

CREATE TABLE `tblbuku` (
  `idBuku` char(20) NOT NULL,
  `judul` varchar(100) DEFAULT NULL,
  `idPengarang` int(11) DEFAULT NULL,
  `idPenerbit` int(11) DEFAULT NULL,
  `tahunTerbit` char(4) DEFAULT NULL,
  `jenisBuku` varchar(50) DEFAULT NULL,
  `idLemari` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblbuku`
--

INSERT INTO `tblbuku` (`idBuku`, `judul`, `idPengarang`, `idPenerbit`, `tahunTerbit`, `jenisBuku`, `idLemari`) VALUES
('1', 'Book One', 1, 1, '2011', 'Novel', 1),
('2', 'a', 1, 1, '233', 'Novel', 1),
('4', '4', 1, 1, '1', 'Tutorial', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbllemari`
--

CREATE TABLE `tbllemari` (
  `idLemari` int(11) NOT NULL,
  `namaLemari` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbllemari`
--

INSERT INTO `tbllemari` (`idLemari`, `namaLemari`) VALUES
(1, 'Satu'),
(2, 'Nama'),
(3, 'n'),
(4, 'hfdhd'),
(44, 'yufgkf');

-- --------------------------------------------------------

--
-- Table structure for table `tblpenerbit`
--

CREATE TABLE `tblpenerbit` (
  `idPenerbit` int(11) NOT NULL,
  `namaPenerbit` varchar(50) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `telp` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblpenerbit`
--

INSERT INTO `tblpenerbit` (`idPenerbit`, `namaPenerbit`, `alamat`, `telp`) VALUES
(1, 'Penerbitgf', 'penerbit@mail.com', '123456789'),
(8998, 'fguj', 'gchgj', '787668');

-- --------------------------------------------------------

--
-- Table structure for table `tblpengarang`
--

CREATE TABLE `tblpengarang` (
  `idPengarang` int(11) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `telp` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblpengarang`
--

INSERT INTO `tblpengarang` (`idPengarang`, `nama`, `alamat`, `telp`) VALUES
(1, 'first', 'pengarang@mail.com', '1234'),
(2, 'Raditya Dika', 'Radit@mail.com', '123456789'),
(4, 'Nama', 'Alamat', '088888888');

-- --------------------------------------------------------

--
-- Table structure for table `tblpinjam`
--

CREATE TABLE `tblpinjam` (
  `noPinjam` int(11) NOT NULL,
  `idAnggota` int(11) DEFAULT NULL,
  `idBuku` int(11) DEFAULT NULL,
  `tglPinjam` datetime DEFAULT NULL,
  `tglKembali` datetime DEFAULT NULL,
  `denda` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tblrak`
--

CREATE TABLE `tblrak` (
  `idLemari` int(11) NOT NULL,
  `namaLemari` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblrak`
--

INSERT INTO `tblrak` (`idLemari`, `namaLemari`) VALUES
(1, 'barucx');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblanggota`
--
ALTER TABLE `tblanggota`
  ADD PRIMARY KEY (`idAnggota`);

--
-- Indexes for table `tblbuku`
--
ALTER TABLE `tblbuku`
  ADD PRIMARY KEY (`idBuku`),
  ADD KEY `idPengarang` (`idPengarang`),
  ADD KEY `idPenerbit` (`idPenerbit`),
  ADD KEY `idLemari` (`idLemari`);

--
-- Indexes for table `tbllemari`
--
ALTER TABLE `tbllemari`
  ADD PRIMARY KEY (`idLemari`);

--
-- Indexes for table `tblpenerbit`
--
ALTER TABLE `tblpenerbit`
  ADD PRIMARY KEY (`idPenerbit`),
  ADD KEY `idPenerbit` (`idPenerbit`);

--
-- Indexes for table `tblpengarang`
--
ALTER TABLE `tblpengarang`
  ADD PRIMARY KEY (`idPengarang`),
  ADD KEY `idPengarang` (`idPengarang`);

--
-- Indexes for table `tblpinjam`
--
ALTER TABLE `tblpinjam`
  ADD PRIMARY KEY (`noPinjam`);

--
-- Indexes for table `tblrak`
--
ALTER TABLE `tblrak`
  ADD PRIMARY KEY (`idLemari`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tblbuku`
--
ALTER TABLE `tblbuku`
  ADD CONSTRAINT `tblbuku_ibfk_1` FOREIGN KEY (`idPengarang`) REFERENCES `tblpengarang` (`idPengarang`),
  ADD CONSTRAINT `tblbuku_ibfk_2` FOREIGN KEY (`idLemari`) REFERENCES `tbllemari` (`idLemari`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tblbuku_ibfk_3` FOREIGN KEY (`idPenerbit`) REFERENCES `tblpenerbit` (`idPenerbit`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
