-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 28, 2025 at 06:39 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

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
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `brokers`
--

INSERT INTO `brokers` (`broker_id`, `name`, `email`, `password_hash`, `phone`, `createdAt`, `updatedAt`) VALUES
(1, 'Abhay Nathwani', 'abhay@gmail.com', '$2b$10$JXPrOytiO0zRPqycji41AeqScR76T4Dd9k1wxSxAo4EHsr96LnYPe', '9871234561', '2025-03-15 08:22:51', '2025-03-15 08:22:51'),
(2, 'krish', 'krish@gmail.com', '$2b$10$DERd2mUXgDPTbucWnOfjdunvAzJLglQkOoe/Thctg.iM4qAVNsocq', '1234567890', '2025-03-15 08:32:47', '2025-03-15 08:32:47'),
(3, 'Vatsal Parmar', 'vatsal@gmail.com', '$2b$10$YyECVPEorBS.EkyBWAguK.nOc5PW1aLS2EM4Vy.VFfiQWFHM2fyyy', '1234567890', '2025-03-15 09:35:57', '2025-03-15 09:35:57'),
(4, 'HAUNTED BOY', 'abhaynathwani123@gmail.com', '$2b$10$1QrtpbeM5d9XGETiUT8HxOhwxFi2jBikCVFXJmEVLT02LfMJVXdBy', '07016257237', '2025-03-15 10:03:43', '2025-03-15 10:03:43'),
(5, 'kishan', 'kishan@gmail.com', '$2b$10$nB/ItjzvpF18YkVlscGeau9uLwA/pyQFweK0KvlzcqPNJp40BVtHm', '1234567890', '2025-03-15 11:05:24', '2025-03-15 11:05:24'),
(6, 'krishaa', 'krishaa@gmail.com', '$2b$10$7onL6lluGp5ucb4FxfHoNueTFIlaVHnOqjF3geBW0I10aDZWIUD5.', '1234567890', '2025-03-15 11:28:29', '2025-03-15 11:28:29'),
(7, 'abcd', 'abcd@gmail.com', '$2b$10$Dm0jRvzzffjE/mRi8L0gv.v18rzdxItbWsLHoZV3zcDD8jCIfKHUu', '1234567890', '2025-03-19 03:08:08', '2025-03-19 03:08:08'),
(8, 'Krish Mamtora', 'krishmamtora26@gmail.com', '$2b$10$pSIL1vUM85rql6Ve87zJJOWuR8s/HRlJdZWuROszxR/yQywNydDgK', NULL, '2025-04-23 04:50:00', '2025-04-23 04:50:00');

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
(3, 1, 'rishit', 'krish@gmail.com', '999033333333', 'abcd', '2025-04-02 06:59:42'),
(4, 1, 'abhay', 'abhay@gmail.com', '1212121212', 'rajkot', '2025-04-21 15:32:42');

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
  `contact_agent` varchar(100) DEFAULT NULL,
  `year_built` int(11) DEFAULT NULL,
  `status` enum('Available','Sold','Rented','Under Negotiation') NOT NULL DEFAULT 'Available',
  `description` text DEFAULT NULL,
  `amenities` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`amenities`)),
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `properties`
--

INSERT INTO `properties` (`property_id`, `broker_id`, `name`, `location`, `price`, `property_for`, `bedrooms`, `bathrooms`, `area`, `property_type`, `contact_agent`, `year_built`, `status`, `description`, `amenities`, `created_at`) VALUES
(1, 1, 'Luxury Beachfront Villa', '123 Ocean Drive, Miami Beach, FL 33139', 2750000.00, 'Sale', 4, 3, 3500, 'Villa', 'John Smith', 2015, 'Sold', 'Stunning modern villa with panoramic ocean views, private pool, and direct beach access. Featuring an open floor plan, high-end finishes, and spacious living areas.', '[\"Swimming Pool\",\"Gym\",\"Security\",\"Parking\",\"Balcony\"]', '2025-04-14 18:47:35'),
(2, 1, 'Downtown Loft Apartment', '456 Urban Street, New York, NY 10001', 850000.00, 'Sale', 2, 2, 1200, 'Apartment', 'Emily Rodriguez', 2010, 'Under Negotiation', 'Modern, spacious loft in the heart of downtown with floor-to-ceiling windows, open concept design, and high-end appliances.', '[\"Elevator\",\"Gym\",\"Parking\",\"Security\",\"Terrace\"]', '2025-04-14 20:06:05'),
(3, 1, 'Serene Sea View Villa', '123 Palm Grove Road, Candolim, Goa, 403515', 15000000.00, 'Sale', 4, 5, 3200, 'Villa', 'Arjun', 2018, 'Available', 'Luxurious villa with private pool, lush garden, and panoramic sea views. Perfect for a peaceful lifestyle or vacation home.', '[\"Swimming Pool\",\"Garden\",\"Private Parking\",\"Security\",\"Balcony\",\"Gym\"]', '2025-04-14 20:08:28'),
(4, 1, 'Greenview Residency', '78 4th Cross, Electronic City Phase 1, Bangalore, Karnataka 560100', 6200000.00, 'Sale', 3, 2, 1450, 'House', 'Sneha Iyer', 2016, 'Available', 'Affordable and spacious flat in a gated society with excellent amenities, close to IT parks and public transport.', '\"[\\\"Gym\\\",\\\"Elevator\\\"]\"', '2025-04-14 20:09:53'),
(5, 1, 'Luxury 2BHK Apartment', 'Sector 45, Gurugram, Haryana', 7500000.00, 'Rent', 3, 3, 1250, 'Apartment', 'Ravi Sharma', 2016, 'Available', 'A modern 2-bedroom apartment with premium fittings, located in a prime locality. Close to schools, markets, and metro station.', '[\"Swimming Pool\",\"Elevator\",\"Balcony\",\"Parking\",\"Garden\"]', '2025-04-16 12:50:06'),
(6, 1, 'one House', 'rajkot , gujrat', 200.00, 'Sale', 3, 2, 100, 'House', 'john cena', 1900, 'Available', 'very old house', '[\"Gym\",\"Parking\",\"Balcony\"]', '2025-04-16 13:08:20');

-- --------------------------------------------------------

--
-- Table structure for table `Schedules`
--

CREATE TABLE `Schedules` (
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
-- Dumping data for table `Schedules`
--

INSERT INTO `Schedules` (`schedule_id`, `property_id`, `client_id`, `date`, `time`, `status`, `createdAt`, `updatedAt`, `broker_id`, `description`) VALUES
(1, 6, 3, '2025-04-25 00:00:00', '07:50:00', 'Pending', '2025-04-21 14:20:29', '2025-04-21 14:20:29', 1, 'lets have visit '),
(2, 2, 4, '2025-04-22 00:00:00', '10:04:00', 'Pending', '2025-04-21 15:33:18', '2025-04-21 15:33:18', 1, 'ok ok ');

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
  ADD UNIQUE KEY `email_41` (`email`);

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
  ADD UNIQUE KEY `email_21` (`email`),
  ADD UNIQUE KEY `email_22` (`email`),
  ADD UNIQUE KEY `email_23` (`email`),
  ADD UNIQUE KEY `email_24` (`email`),
  ADD UNIQUE KEY `email_25` (`email`),
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
  ADD KEY `broker_id` (`broker_id`);

--
-- Indexes for table `properties`
--
ALTER TABLE `properties`
  ADD PRIMARY KEY (`property_id`),
  ADD KEY `broker_id` (`broker_id`);

--
-- Indexes for table `Schedules`
--
ALTER TABLE `Schedules`
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
  MODIFY `broker_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `properties`
--
ALTER TABLE `properties`
  MODIFY `property_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `Schedules`
--
ALTER TABLE `Schedules`
  MODIFY `schedule_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
-- Constraints for table `Schedules`
--
ALTER TABLE `Schedules`
  ADD CONSTRAINT `schedules_ibfk_77` FOREIGN KEY (`property_id`) REFERENCES `properties` (`property_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedules_ibfk_78` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedules_ibfk_79` FOREIGN KEY (`broker_id`) REFERENCES `brokers` (`broker_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
