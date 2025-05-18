-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 15, 2025 at 10:48 PM
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
(10, 'Krish Mamtora', 'krishmamtora26@gmail.com', '$2b$10$93VpVNDOc1xgTszNZsiq3ueY5XIUsPCoEMhAyJ2zlY5O.0Aapi1Qm', NULL, '2025-04-28 05:50:16', '2025-04-28 05:50:16'),
(12, 'Krish', 'krish.mamtora2692005@gmail.com', '$2b$10$7jn8TPQUMAO04OFe538ubO6GCyaPpOZe5GkXgzjg1meIVakbm9pX.', NULL, '2025-04-28 05:51:22', '2025-04-28 05:51:22');

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
(8, 2, 'doshi', 'doshi@gmail.com', '68686868686', 'hostel', '2025-05-14 04:03:51');

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
(2, 2, 'Downtown Office Space', '456 City Center, San Francisco, CA', 1200000.00, 'Rent', 4, 4, 2000, 'House', 1997, 'Under Negotiation', 'Premium office space ideal for startups and tech companies.', '[\"Swimming Pool\",\"Elevator\",\"Balcony\"]', '2025-05-14 04:07:10', NULL);

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
(1, 2, 6, 1, '2025-03-01', '2025-07-01', 100.00, 'Active', '500', '2025-05-15 19:20:04');

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
(1, 1, 'March 2025', '2025-03-31', 100.00, 100.00, '2025-05-15', 'Paid', 'Payment recorded via rental management system', '2025-05-15 19:20:24', '2025-05-15 19:20:24');

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
(4, 1, 6, '2025-05-15 00:00:00', '22:59:00', 'Pending', '2025-05-14 04:29:07', '2025-05-14 04:29:07', 2, 'aavija'),
(5, 1, 8, '2025-05-22 00:00:00', '07:18:00', 'Pending', '2025-05-15 11:46:49', '2025-05-15 11:46:49', 2, 'aavija');

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
  MODIFY `broker_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `properties`
--
ALTER TABLE `properties`
  MODIFY `property_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `rental_management`
--
ALTER TABLE `rental_management`
  MODIFY `rental_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `rent_payments`
--
ALTER TABLE `rent_payments`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Schedules`
--
ALTER TABLE `Schedules`
  MODIFY `schedule_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
-- Constraints for table `Schedules`
--
ALTER TABLE `Schedules`
  ADD CONSTRAINT `schedules_ibfk_296` FOREIGN KEY (`property_id`) REFERENCES `properties` (`property_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedules_ibfk_297` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedules_ibfk_298` FOREIGN KEY (`broker_id`) REFERENCES `brokers` (`broker_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
