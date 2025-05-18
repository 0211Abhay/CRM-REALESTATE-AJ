-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2025 at 11:33 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aj_awt_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `brokers`
--

CREATE TABLE `brokers` (
  `broker_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL,
  `reset_token` varchar(255) DEFAULT NULL,
  `reset_token_expiry` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `brokers`
--

INSERT INTO `brokers` (`broker_id`, `name`, `email`, `password_hash`, `phone`, `createdAt`, `updatedAt`, `reset_token`, `reset_token_expiry`) VALUES
(1, 'Abhay Nathwani', 'abhay@gmail.com', '$2b$10$JXPrOytiO0zRPqycji41AeqScR76T4Dd9k1wxSxAo4EHsr96LnYPe', '9871234561', '2025-03-15 08:22:51', '2025-03-15 08:22:51', NULL, NULL),
(2, 'krish', 'kri@gmail.com', '$2b$10$DERd2mUXgDPTbucWnOfjdunvAzJLglQkOoe/Thctg.iM4qAVNsocq', '1234567890', '2025-03-15 08:32:47', '2025-05-16 11:41:08', NULL, NULL),
(3, 'Vatsal Parmar', 'vatsal@gmail.com', '$2b$10$YyECVPEorBS.EkyBWAguK.nOc5PW1aLS2EM4Vy.VFfiQWFHM2fyyy', '1234567890', '2025-03-15 09:35:57', '2025-03-15 09:35:57', NULL, NULL),
(5, 'kishan', 'kishan@gmail.com', '$2b$10$nB/ItjzvpF18YkVlscGeau9uLwA/pyQFweK0KvlzcqPNJp40BVtHm', '1234567890', '2025-03-15 11:05:24', '2025-03-15 11:05:24', NULL, NULL),
(6, 'krishaa', 'krishaa@gmail.com', '$2b$10$7onL6lluGp5ucb4FxfHoNueTFIlaVHnOqjF3geBW0I10aDZWIUD5.', '1234567890', '2025-03-15 11:28:29', '2025-03-15 11:28:29', NULL, NULL),
(7, 'abcd', 'abcd@gmail.com', '$2b$10$Dm0jRvzzffjE/mRi8L0gv.v18rzdxItbWsLHoZV3zcDD8jCIfKHUu', '1234567890', '2025-03-19 03:08:08', '2025-03-19 03:08:08', NULL, NULL),
(10, 'Krish Mamtora', 'krishmamtora26@gmail.com', '$2b$10$93VpVNDOc1xgTszNZsiq3ueY5XIUsPCoEMhAyJ2zlY5O.0Aapi1Qm', NULL, '2025-04-28 05:50:16', '2025-04-28 05:50:16', NULL, NULL),
(12, 'Krish', 'krish.mamtora2692005@gmail.com', '$2b$10$7jn8TPQUMAO04OFe538ubO6GCyaPpOZe5GkXgzjg1meIVakbm9pX.', NULL, '2025-04-28 05:51:22', '2025-04-28 05:51:22', NULL, NULL),
(13, 'abhay nathwani', 'abhayn@gmail.com', '$2b$10$ODbdgCa5Ft06IFWhHF2CBO7I/3o/nnh8sLUbd/W.RI8jpBFJGgBVS', '1234567890', '2025-05-15 21:09:12', '2025-05-16 01:47:05', NULL, NULL),
(16, 'ABHAY NATHWANI', 'abhay.nathwani121182@marwadiuniversity.ac.in', '$2b$10$T8DV/8kGPVu5LeN1Xz11QuZjCpJOMyooDvEsJWwsOGweoGbTzl0hO', NULL, '2025-05-16 02:28:14', '2025-05-16 02:28:14', NULL, NULL),
(17, 'Abhay Nathwani', 'abhaynathwani123@gmail.com', '$2b$10$6piBTM7L67IfI3JTKLrkMOoZmAFvJYQctS.XI60nnhyS6Wje9xZ.e', NULL, '2025-05-16 02:28:26', '2025-05-16 03:12:05', NULL, NULL),
(18, 'Aryan Mahida', 'aryanaryan@gmail.com', '$2b$10$e1REttktWMyQDCnB4xYGMuOyertvgfn9JvhTDU6H7opEtMxAz2Tk2', '1234567890', '2025-05-16 02:44:54', '2025-05-16 02:44:54', NULL, NULL),
(19, 'jay jay', 'jay@jay.com', '$2b$10$vixK7whBJoz.HaKUD03/du5ONRwYnteoXPDX2u3QSDjzd6KMTrkfS', '1234567890', '2025-05-16 02:45:49', '2025-05-16 02:45:49', NULL, NULL),
(21, 'Broker Name', 'broker@example.com', '$2a$10$P7eZRMkKMFEYvv0YP81Wg.MJmXsvvGfkBpJlXIRogVXIu0Wuwosbq', '1234567890', '2025-05-18 19:55:53', '2025-05-18 19:55:53', NULL, NULL),
(22, 'rohit sharma', 'rohit@gmail.com', '$2a$10$jtcX8U5dwKDUdrK3Kxll/ety7c06KM8.sec8C7xIlgzNw7f72VhGC', '1231231231', '2025-05-18 23:39:37', '2025-05-18 23:39:37', NULL, NULL),
(23, 'umang hirani', 'umangh@gmail.com', '$2a$10$JwjSU2qOnhshB0rtJEyj3OGBr1VJ2PlnCY2NscQDWECMXhQvNBVbS', '1231231233', '2025-05-18 23:40:33', '2025-05-19 01:34:54', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `client_id` int(11) NOT NULL,
  `broker_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `address` text DEFAULT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`client_id`, `broker_id`, `name`, `email`, `phone`, `address`, `created_at`) VALUES
(6, 2, 'rishit rathod', 'rishit@gmail.com', '9898989898', 'rajkot karachi', '2025-05-14 03:58:54'),
(7, 2, 'aryan', 'aryan@gmail.com', '8787878787', 'bihari', '2025-05-14 04:03:12'),
(8, 2, 'doshi', 'doshi@gmail.com', '68686868686', 'hostel', '2025-05-14 04:03:51'),
(9, 13, 'Krish Mamtora', 'test@gmail.com', '123', '123', '2025-05-15 21:17:09'),
(10, 17, 'Krish Mamtora', 'krish@gmail.comaa', '123123123', '123123123', '2025-05-16 03:13:13'),
(11, 17, 'g', 'ha@gmail.com', '9898989898', 'bbbb', '2025-05-16 12:15:52'),
(12, 23, 'Abhay aa', 'aaaaa@gmail.com', '1231231231', 'uhirani added me', '2025-05-19 00:11:33');

-- --------------------------------------------------------

--
-- Table structure for table `properties`
--

CREATE TABLE `properties` (
  `property_id` int(11) NOT NULL,
  `broker_id` int(11) NOT NULL DEFAULT 1,
  `name` varchar(100) NOT NULL,
  `location` varchar(255) NOT NULL,
  `price` decimal(12,2) NOT NULL,
  `property_for` enum('Rent','Sale') NOT NULL,
  `bedrooms` int(11) DEFAULT NULL,
  `bathrooms` int(11) DEFAULT NULL,
  `area` int(11) DEFAULT NULL,
  `property_type` enum('House','Apartment','Condo','Villa','Commercial','Land') DEFAULT NULL,
  `year_built` int(11) DEFAULT NULL,
  `status` enum('Available','Sold','Rented','Under Negotiation') NOT NULL DEFAULT 'Available',
  `description` text DEFAULT NULL,
  `amenities` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`amenities`)),
  `created_at` datetime NOT NULL,
  `contact_agent` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `properties`
--

INSERT INTO `properties` (`property_id`, `broker_id`, `name`, `location`, `price`, `property_for`, `bedrooms`, `bathrooms`, `area`, `property_type`, `year_built`, `status`, `description`, `amenities`, `created_at`, `contact_agent`) VALUES
(1, 2, 'Sunset Villa', '123 Palm Street, Miami, FL', 850000.00, 'Sale', 3, 3, 2800, 'Villa', 2017, 'Available', 'Luxurious villa with sea view and private pool.', '[\"Swimming Pool\",\"Gym\",\"Balcony\",\"Security\",\"Parking\"]', '2025-05-14 04:05:52', NULL),
(2, 2, 'Downtown Office Space', '456 City Center, San Francisco, CA', 1200000.00, 'Rent', 4, 4, 2000, 'House', 1997, 'Under Negotiation', 'Premium office space ideal for startups and tech companies.', '[\"Swimming Pool\",\"Elevator\",\"Balcony\"]', '2025-05-14 04:07:10', NULL),
(3, 13, '101,Yogeshwar Park', 'Near XYZ Road', 20000.00, 'Sale', 4, 5, 2000, 'Commercial', 2015, 'Available', 'lorem', '[\"Swimming Pool\",\"Gym\",\"Parking\",\"Security\",\"Garden\",\"Elevator\",\"Balcony\",\"Terrace\"]', '2025-05-15 21:24:17', NULL),
(4, 17, 'Yearly', 'MA115', 2500.00, 'Sale', 4, 4, 2500, 'House', 2015, 'Sold', NULL, '[\"Swimming Pool\",\"Garden\",\"Gym\",\"Elevator\",\"Parking\",\"Balcony\",\"Security\",\"Terrace\"]', '2025-05-16 03:15:02', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `rentals`
--

CREATE TABLE `rentals` (
  `rental_id` int(11) NOT NULL,
  `broker_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `end_date` date NOT NULL,
  `notes` text DEFAULT NULL,
  `property_id` int(11) NOT NULL,
  `rent_amount` double NOT NULL,
  `start_date` date NOT NULL,
  `status` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rental_management`
--

CREATE TABLE `rental_management` (
  `rental_id` int(11) NOT NULL,
  `broker_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `property_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `rent_amount` decimal(12,2) NOT NULL,
  `status` enum('Active','Completed','Terminated') NOT NULL DEFAULT 'Active',
  `notes` text DEFAULT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rental_management`
--

INSERT INTO `rental_management` (`rental_id`, `broker_id`, `client_id`, `property_id`, `start_date`, `end_date`, `rent_amount`, `status`, `notes`, `created_at`) VALUES
(1, 2, 6, 1, '2025-03-01', '2025-07-01', 100.00, 'Active', '500', '2025-05-15 19:20:04'),
(2, 17, 10, 4, '2025-05-18', '2025-05-21', 999.99, 'Active', '200', '2025-05-16 04:29:31'),
(3, 17, 10, 4, '2025-05-31', '2025-06-07', 5000.00, 'Active', '255', '2025-05-16 04:30:22'),
(4, 13, 9, 3, '2025-05-01', '2025-07-31', 4999.99, 'Active', 'Deposit 5L', '2025-05-16 05:20:06'),
(5, 17, 11, 4, '2025-03-01', '2025-08-01', 800.00, 'Active', 'n', '2025-05-16 12:16:21');

-- --------------------------------------------------------

--
-- Table structure for table `rent_payments`
--

CREATE TABLE `rent_payments` (
  `payment_id` int(11) NOT NULL,
  `rental_id` int(11) NOT NULL,
  `month` varchar(50) DEFAULT NULL,
  `due_date` date NOT NULL,
  `amount_due` decimal(12,2) NOT NULL,
  `amount_paid` decimal(12,2) NOT NULL DEFAULT 0.00,
  `payment_date` date DEFAULT NULL,
  `status` enum('Paid','Pending','Due','Overdue') NOT NULL DEFAULT 'Pending',
  `notes` text DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rent_payments`
--

INSERT INTO `rent_payments` (`payment_id`, `rental_id`, `month`, `due_date`, `amount_due`, `amount_paid`, `payment_date`, `status`, `notes`, `created_at`, `updated_at`) VALUES
(1, 1, 'March 2025', '2025-03-31', 100.00, 100.00, '2025-05-15', 'Paid', 'Payment recorded via rental management system', '2025-05-15 19:20:24', '2025-05-15 19:20:24'),
(3, 1, 'March 2025', '2025-03-31', 100.00, 100.00, '2025-05-15', 'Paid', 'Payment recorded via rental management system', '2025-05-15 21:16:26', '2025-05-15 21:16:26'),
(4, 1, 'March 2025', '2025-03-31', 100.00, 100.00, '2025-05-15', 'Paid', 'Payment recorded via rental management system', '2025-05-15 21:26:40', '2025-05-15 21:26:40'),
(5, 1, 'March 2025', '2025-03-31', 100.00, 100.00, '2025-05-15', 'Paid', 'Payment recorded via rental management system', '2025-05-15 21:27:00', '2025-05-15 21:27:00'),
(6, 1, 'April 2025', '2025-04-30', 100.00, 100.00, '2025-05-15', 'Paid', 'Payment recorded via rental management system', '2025-05-15 21:27:25', '2025-05-15 21:27:25'),
(7, 1, 'May 2025', '2025-05-31', 100.00, 100.00, '2025-05-15', 'Paid', 'Payment recorded via rental management system', '2025-05-15 21:28:03', '2025-05-15 21:28:03'),
(8, 5, 'March 2025', '2025-03-31', 800.00, 800.00, '2025-05-16', 'Paid', 'Payment recorded via rental management system', '2025-05-16 12:16:37', '2025-05-16 12:16:37');

-- --------------------------------------------------------

--
-- Table structure for table `schedules`
--

CREATE TABLE `schedules` (
  `schedule_id` int(11) NOT NULL,
  `property_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `time` time NOT NULL,
  `status` enum('Pending','Completed','Cancelled') NOT NULL DEFAULT 'Pending',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL,
  `broker_id` int(11) NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `schedules`
--

INSERT INTO `schedules` (`schedule_id`, `property_id`, `client_id`, `date`, `time`, `status`, `createdAt`, `updatedAt`, `broker_id`, `description`) VALUES
(4, 1, 6, '2025-05-15 00:00:00', '22:59:00', 'Pending', '2025-05-14 04:29:07', '2025-05-14 04:29:07', 2, 'aavija'),
(5, 1, 8, '2025-05-22 00:00:00', '07:18:00', 'Pending', '2025-05-15 11:46:49', '2025-05-15 11:46:49', 2, 'aavija'),
(6, 3, 9, '2025-05-18 00:00:00', '04:00:00', 'Pending', '2025-05-15 21:25:14', '2025-05-15 21:25:14', 13, ''),
(7, 3, 9, '2025-05-18 00:00:00', '04:00:00', 'Cancelled', '2025-05-15 21:25:39', '2025-05-16 05:13:14', 13, ''),
(8, 4, 10, '2025-05-23 00:00:00', '10:49:00', 'Completed', '2025-05-16 03:17:38', '2025-05-16 04:30:49', 17, 'ghjkl');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brokers`
--
ALTER TABLE `brokers`
  ADD PRIMARY KEY (`broker_id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `email_2` (`email`),
  ADD UNIQUE KEY `email_3` (`email`),
  ADD UNIQUE KEY `email_4` (`email`),
  ADD UNIQUE KEY `email_5` (`email`),
  ADD UNIQUE KEY `email_6` (`email`),
  ADD UNIQUE KEY `email_7` (`email`),
  ADD UNIQUE KEY `email_8` (`email`),
  ADD UNIQUE KEY `email_9` (`email`),
  ADD UNIQUE KEY `email_10` (`email`),
  ADD UNIQUE KEY `email_11` (`email`),
  ADD UNIQUE KEY `email_12` (`email`),
  ADD UNIQUE KEY `email_13` (`email`),
  ADD UNIQUE KEY `email_14` (`email`),
  ADD UNIQUE KEY `email_15` (`email`),
  ADD UNIQUE KEY `email_16` (`email`),
  ADD UNIQUE KEY `email_17` (`email`),
  ADD UNIQUE KEY `email_18` (`email`),
  ADD UNIQUE KEY `email_19` (`email`),
  ADD UNIQUE KEY `email_20` (`email`),
  ADD UNIQUE KEY `email_21` (`email`),
  ADD UNIQUE KEY `email_22` (`email`),
  ADD UNIQUE KEY `email_23` (`email`),
  ADD UNIQUE KEY `email_24` (`email`),
  ADD UNIQUE KEY `email_25` (`email`),
  ADD UNIQUE KEY `email_26` (`email`),
  ADD UNIQUE KEY `email_27` (`email`),
  ADD UNIQUE KEY `email_28` (`email`),
  ADD UNIQUE KEY `email_29` (`email`),
  ADD UNIQUE KEY `email_30` (`email`),
  ADD UNIQUE KEY `email_31` (`email`),
  ADD UNIQUE KEY `email_32` (`email`),
  ADD UNIQUE KEY `email_33` (`email`),
  ADD UNIQUE KEY `email_34` (`email`),
  ADD UNIQUE KEY `email_35` (`email`),
  ADD UNIQUE KEY `email_36` (`email`),
  ADD UNIQUE KEY `email_37` (`email`),
  ADD UNIQUE KEY `email_38` (`email`),
  ADD UNIQUE KEY `email_39` (`email`),
  ADD UNIQUE KEY `email_40` (`email`),
  ADD UNIQUE KEY `email_41` (`email`),
  ADD UNIQUE KEY `email_42` (`email`),
  ADD UNIQUE KEY `email_43` (`email`),
  ADD UNIQUE KEY `email_44` (`email`),
  ADD UNIQUE KEY `email_45` (`email`),
  ADD UNIQUE KEY `email_46` (`email`),
  ADD UNIQUE KEY `email_47` (`email`),
  ADD UNIQUE KEY `email_48` (`email`),
  ADD UNIQUE KEY `email_49` (`email`),
  ADD UNIQUE KEY `email_50` (`email`),
  ADD UNIQUE KEY `email_51` (`email`),
  ADD UNIQUE KEY `email_52` (`email`),
  ADD UNIQUE KEY `email_53` (`email`),
  ADD UNIQUE KEY `email_54` (`email`),
  ADD UNIQUE KEY `email_55` (`email`),
  ADD UNIQUE KEY `email_56` (`email`),
  ADD UNIQUE KEY `email_57` (`email`),
  ADD UNIQUE KEY `email_58` (`email`),
  ADD UNIQUE KEY `email_59` (`email`),
  ADD UNIQUE KEY `email_60` (`email`),
  ADD UNIQUE KEY `email_61` (`email`),
  ADD UNIQUE KEY `email_62` (`email`),
  ADD UNIQUE KEY `email_63` (`email`);

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`client_id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `email_2` (`email`),
  ADD UNIQUE KEY `email_3` (`email`),
  ADD UNIQUE KEY `email_4` (`email`),
  ADD UNIQUE KEY `email_5` (`email`),
  ADD UNIQUE KEY `email_6` (`email`),
  ADD UNIQUE KEY `email_7` (`email`),
  ADD UNIQUE KEY `email_8` (`email`),
  ADD UNIQUE KEY `email_9` (`email`),
  ADD UNIQUE KEY `email_10` (`email`),
  ADD UNIQUE KEY `email_11` (`email`),
  ADD UNIQUE KEY `email_12` (`email`),
  ADD UNIQUE KEY `email_13` (`email`),
  ADD UNIQUE KEY `email_14` (`email`),
  ADD UNIQUE KEY `email_15` (`email`),
  ADD UNIQUE KEY `email_16` (`email`),
  ADD UNIQUE KEY `email_17` (`email`),
  ADD UNIQUE KEY `email_18` (`email`),
  ADD UNIQUE KEY `email_19` (`email`),
  ADD UNIQUE KEY `email_20` (`email`),
  ADD UNIQUE KEY `email_21` (`email`),
  ADD UNIQUE KEY `email_22` (`email`),
  ADD UNIQUE KEY `email_23` (`email`),
  ADD UNIQUE KEY `email_24` (`email`),
  ADD UNIQUE KEY `email_25` (`email`),
  ADD UNIQUE KEY `email_26` (`email`),
  ADD UNIQUE KEY `email_27` (`email`),
  ADD UNIQUE KEY `email_28` (`email`),
  ADD UNIQUE KEY `email_29` (`email`),
  ADD UNIQUE KEY `email_30` (`email`),
  ADD UNIQUE KEY `email_31` (`email`),
  ADD UNIQUE KEY `email_32` (`email`),
  ADD UNIQUE KEY `email_33` (`email`),
  ADD UNIQUE KEY `email_34` (`email`),
  ADD UNIQUE KEY `email_35` (`email`),
  ADD UNIQUE KEY `email_36` (`email`),
  ADD UNIQUE KEY `email_37` (`email`),
  ADD UNIQUE KEY `email_38` (`email`),
  ADD UNIQUE KEY `email_39` (`email`),
  ADD UNIQUE KEY `email_40` (`email`),
  ADD UNIQUE KEY `email_41` (`email`),
  ADD UNIQUE KEY `email_42` (`email`),
  ADD UNIQUE KEY `email_43` (`email`),
  ADD UNIQUE KEY `email_44` (`email`),
  ADD UNIQUE KEY `email_45` (`email`),
  ADD UNIQUE KEY `email_46` (`email`),
  ADD UNIQUE KEY `email_47` (`email`),
  ADD UNIQUE KEY `email_48` (`email`),
  ADD UNIQUE KEY `email_49` (`email`),
  ADD UNIQUE KEY `email_50` (`email`),
  ADD UNIQUE KEY `email_51` (`email`),
  ADD UNIQUE KEY `email_52` (`email`),
  ADD UNIQUE KEY `email_53` (`email`),
  ADD UNIQUE KEY `email_54` (`email`),
  ADD UNIQUE KEY `email_55` (`email`),
  ADD UNIQUE KEY `email_56` (`email`),
  ADD UNIQUE KEY `email_57` (`email`),
  ADD UNIQUE KEY `email_58` (`email`),
  ADD UNIQUE KEY `email_59` (`email`),
  ADD UNIQUE KEY `email_60` (`email`),
  ADD UNIQUE KEY `email_61` (`email`),
  ADD UNIQUE KEY `email_62` (`email`),
  ADD KEY `broker_id` (`broker_id`);

--
-- Indexes for table `properties`
--
ALTER TABLE `properties`
  ADD PRIMARY KEY (`property_id`),
  ADD KEY `broker_id` (`broker_id`);

--
-- Indexes for table `rentals`
--
ALTER TABLE `rentals`
  ADD PRIMARY KEY (`rental_id`);

--
-- Indexes for table `rental_management`
--
ALTER TABLE `rental_management`
  ADD PRIMARY KEY (`rental_id`),
  ADD KEY `broker_id` (`broker_id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `property_id` (`property_id`);

--
-- Indexes for table `rent_payments`
--
ALTER TABLE `rent_payments`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `rental_id` (`rental_id`);

--
-- Indexes for table `schedules`
--
ALTER TABLE `schedules`
  ADD PRIMARY KEY (`schedule_id`),
  ADD KEY `property_id` (`property_id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `broker_id` (`broker_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `brokers`
--
ALTER TABLE `brokers`
  MODIFY `broker_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `properties`
--
ALTER TABLE `properties`
  MODIFY `property_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `rentals`
--
ALTER TABLE `rentals`
  MODIFY `rental_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rental_management`
--
ALTER TABLE `rental_management`
  MODIFY `rental_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `rent_payments`
--
ALTER TABLE `rent_payments`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `schedules`
--
ALTER TABLE `schedules`
  MODIFY `schedule_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `clients`
--
ALTER TABLE `clients`
  ADD CONSTRAINT `clients_ibfk_1` FOREIGN KEY (`broker_id`) REFERENCES `brokers` (`broker_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `properties`
--
ALTER TABLE `properties`
  ADD CONSTRAINT `properties_ibfk_1` FOREIGN KEY (`broker_id`) REFERENCES `brokers` (`broker_id`) ON DELETE CASCADE;

--
-- Constraints for table `rental_management`
--
ALTER TABLE `rental_management`
  ADD CONSTRAINT `rental_management_ibfk_127` FOREIGN KEY (`broker_id`) REFERENCES `brokers` (`broker_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rental_management_ibfk_128` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rental_management_ibfk_129` FOREIGN KEY (`property_id`) REFERENCES `properties` (`property_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rent_payments`
--
ALTER TABLE `rent_payments`
  ADD CONSTRAINT `rent_payments_ibfk_1` FOREIGN KEY (`rental_id`) REFERENCES `rental_management` (`rental_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `schedules`
--
ALTER TABLE `schedules`
  ADD CONSTRAINT `schedules_ibfk_296` FOREIGN KEY (`property_id`) REFERENCES `properties` (`property_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedules_ibfk_297` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedules_ibfk_298` FOREIGN KEY (`broker_id`) REFERENCES `brokers` (`broker_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
