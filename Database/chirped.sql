CREATE DATABASE  IF NOT EXISTS `chirped` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `chirped`;
-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: chirped
-- ------------------------------------------------------
-- Server version	8.0.36

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

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `street_address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `office_floor` varchar(10) DEFAULT NULL,
  `office_room_number` varchar(10) DEFAULT NULL,
  `office_area_description` text,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'123 Main St','New York','NY','USA','10001','10','101','North wing of the tenth floor','2024-05-13 20:52:22',NULL),(2,'456 Elm St','Los Angeles','CA','USA','90001','15','1501','South wing of the fifteenth floor','2024-05-13 20:52:22',NULL),(3,'789 Pine St','Seattle','WA','USA','98101','20','2001','East wing of the twentieth floor','2024-05-13 20:52:22',NULL),(4,'101 Oak St','Boston','MA','USA','02101','25','2501','West wing of the twenty-fifth floor','2024-05-13 20:52:22',NULL),(5,'102 Maple St','San Francisco','CA','USA','94101','30','3001','Center section of the thirtieth floor','2024-05-13 20:52:22',NULL),(6,'103 Birch St','Chicago','IL','USA','60601','5','501','North corner of the fifth floor','2024-05-13 20:52:22',NULL),(7,'104 Cedar St','Houston','TX','USA','77001','40','4001','South corner of the fortieth floor','2024-05-13 20:52:22',NULL),(8,'105 Redwood St','New York','NY','USA','10001','45','4501','Central lobby area','2024-05-13 20:52:22',NULL),(9,'106 Douglas Fir St','Los Angeles','CA','USA','90001','50','5001','Underground facilities','2024-05-13 20:52:22',NULL),(10,'107 Spruce St','Seattle','WA','USA','98101','8','801','Top floor penthouse','2024-05-13 20:52:22',NULL),(11,'108 Sycamore St','Boston','MA','USA','02101','12','1201','Historical section of the twelfth floor','2024-05-13 20:52:22',NULL),(12,'109 Ash St','San Francisco','CA','USA','94101','18','1801','Renovated area of the eighteenth floor','2024-05-13 20:52:22',NULL),(13,'110 Poplar St','Chicago','IL','USA','60601','2','201','Newly built extension','2024-05-13 20:52:22',NULL),(14,'111 Teak St','Houston','TX','USA','77001','22','2201','Old section needing repair','2024-05-13 20:52:22',NULL),(15,'112 Willow St','New York','NY','USA','10001','7','701','Recently upgraded section','2024-05-13 20:52:22',NULL),(16,'113 Alder St','Los Angeles','CA','USA','90001','9','901','Temporary offices','2024-05-13 20:52:22',NULL),(17,'114 Cherry St','Seattle','WA','USA','98101','3','301','Emergency planning offices','2024-05-13 20:52:22',NULL),(18,'115 Beech St','Boston','MA','USA','02101','14','1401','Future development area','2024-05-13 20:52:22',NULL),(19,'116 Linden St','San Francisco','CA','USA','94101','17','1701','Staff recreational facilities','2024-05-13 20:52:22',NULL),(20,'117 Palm St','Chicago','IL','USA','60601','6','601','Security operations center','2024-05-13 20:52:22',NULL);
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_images`
--

DROP TABLE IF EXISTS `comment_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_images` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `comment_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  PRIMARY KEY (`image_id`),
  KEY `comment_id` (`comment_id`),
  CONSTRAINT `comment_images_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_images`
--

LOCK TABLES `comment_images` WRITE;
/*!40000 ALTER TABLE `comment_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `tweet_id` int NOT NULL,
  `user_id` int NOT NULL,
  `parent_comment_id` int DEFAULT NULL,
  `content` text NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `user_id` (`user_id`),
  KEY `parent_comment_id` (`parent_comment_id`),
  KEY `idx_tweet_id_on_comments` (`tweet_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`tweet_id`) REFERENCES `tweets` (`tweet_id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `comments_ibfk_3` FOREIGN KEY (`parent_comment_id`) REFERENCES `comments` (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (31,63,11,NULL,'Absolutely agree! Every challenge is an opportunity to learn something new about ourselves and the world around us.','2024-05-20 21:45:11'),(32,63,11,NULL,'Well said! It\'s not just the victories but the struggles along the way that truly shape who we are.','2024-05-20 21:46:01'),(33,132,11,NULL,'Yes','2024-05-20 22:45:32'),(34,132,11,NULL,'I really like this sentence.','2024-05-20 22:57:33'),(35,132,11,NULL,'This is my comment','2024-05-21 13:12:59');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follows`
--

DROP TABLE IF EXISTS `follows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follows` (
  `follow_id` int NOT NULL AUTO_INCREMENT,
  `follower_id` int NOT NULL,
  `following_id` int NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`follow_id`),
  KEY `idx_follower_id` (`follower_id`),
  KEY `idx_following_id` (`following_id`),
  CONSTRAINT `follows_ibfk_1` FOREIGN KEY (`follower_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `follows_ibfk_2` FOREIGN KEY (`following_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follows`
--

LOCK TABLES `follows` WRITE;
/*!40000 ALTER TABLE `follows` DISABLE KEYS */;
INSERT INTO `follows` VALUES (1,11,18,'2024-05-17 07:54:07'),(2,11,16,'2024-05-17 07:56:54'),(3,11,8,'2024-05-17 07:58:35'),(4,11,19,'2024-05-17 07:58:42'),(5,11,9,'2024-05-17 07:58:43'),(6,11,7,'2024-05-17 08:00:03'),(7,11,17,'2024-05-17 08:00:04'),(8,11,6,'2024-05-17 08:00:04'),(9,11,4,'2024-05-17 08:03:56'),(10,11,15,'2024-05-17 08:04:02'),(11,11,2,'2024-05-19 20:57:31'),(12,7,11,'2024-05-20 19:40:33'),(13,8,11,'2024-05-20 19:40:33'),(14,9,11,'2024-05-20 19:40:33'),(15,2,11,'2024-05-20 19:40:33'),(16,5,11,'2024-05-20 19:40:33');
/*!40000 ALTER TABLE `follows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tweet_images`
--

DROP TABLE IF EXISTS `tweet_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tweet_images` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `tweet_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  PRIMARY KEY (`image_id`),
  KEY `tweet_id` (`tweet_id`),
  CONSTRAINT `tweet_images_ibfk_1` FOREIGN KEY (`tweet_id`) REFERENCES `tweets` (`tweet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tweet_images`
--

LOCK TABLES `tweet_images` WRITE;
/*!40000 ALTER TABLE `tweet_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `tweet_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tweets`
--

DROP TABLE IF EXISTS `tweets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tweets` (
  `tweet_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `content` text NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`tweet_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_created_at` (`created_at` DESC),
  KEY `idx_updated_at` (`updated_at` DESC),
  CONSTRAINT `tweets_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tweets`
--

LOCK TABLES `tweets` WRITE;
/*!40000 ALTER TABLE `tweets` DISABLE KEYS */;
INSERT INTO `tweets` VALUES (2,11,'I am coding now.','2024-05-16 15:34:03',NULL),(36,18,'What a beautiful day to start something new!','2024-05-20 20:51:41',NULL),(37,18,'Just finished a great book! Highly recommend \"The Power of Positive Thinking\".','2024-05-20 20:51:41',NULL),(38,18,'Loving the weather today. Perfect for a long walk!','2024-05-20 20:51:41',NULL),(39,18,'Gratitude is the best attitude. #ThankfulThursday','2024-05-20 20:51:41',NULL),(40,18,'Another day, another opportunity to shine!','2024-05-20 20:51:41',NULL),(41,16,'Starting the day with a smile and a cup of coffee! ☕️','2024-05-20 20:51:41',NULL),(42,16,'Nothing beats a morning run in the crisp air.','2024-05-20 20:51:41',NULL),(43,16,'So excited to share that I’ve started a new project! Details coming soon.','2024-05-20 20:51:41',NULL),(44,16,'Throw kindness around like confetti. ?','2024-05-20 20:51:41',NULL),(45,16,'Every day is a chance to be better than yesterday.','2024-05-20 20:51:41',NULL),(46,8,'Feeling motivated after today’s webinar on personal development!','2024-05-20 20:51:41',NULL),(47,8,'A healthy lifestyle not only changes your body, it changes your mind, your attitude and your mood.','2024-05-20 20:51:41',NULL),(48,8,'A smile is the universal welcome.','2024-05-20 20:51:41',NULL),(49,8,'Remember why you started.','2024-05-20 20:51:41',NULL),(50,8,'The best view comes after the hardest climb.','2024-05-20 20:51:41',NULL),(51,19,'Just had an amazing day out with family. #Blessed','2024-05-20 20:51:41',NULL),(52,19,'Positive mind. Positive vibes. Positive life.','2024-05-20 20:51:41',NULL),(53,19,'It’s a good day to have a good day!','2024-05-20 20:51:41',NULL),(54,19,'Focus on the journey, not the destination.','2024-05-20 20:51:41',NULL),(55,19,'Joy is not in things; it is in us.','2024-05-20 20:51:41',NULL),(56,9,'Every moment is a fresh beginning. - T.S. Eliot','2024-05-20 20:51:41',NULL),(57,9,'Do what you love and you’ll never have a problem with Monday.','2024-05-20 20:51:41',NULL),(58,9,'Be yourself; everyone else is already taken. - Oscar Wilde','2024-05-20 20:51:41',NULL),(59,9,'Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston S. Churchill','2024-05-20 20:51:41',NULL),(60,9,'You get what you give.','2024-05-20 20:51:41',NULL),(61,7,'Life is 10% what happens to us and 90% how we react to it.','2024-05-20 20:51:41',NULL),(62,7,'Stay positive, work hard, make it happen.','2024-05-20 20:51:41',NULL),(63,7,'Challenges are what make life interesting and overcoming them is what makes life meaningful.','2024-05-20 20:51:41',NULL),(64,7,'It’s never too late to be what you might have been.','2024-05-20 20:51:41',NULL),(65,7,'Dream big and dare to fail.','2024-05-20 20:51:41',NULL),(66,17,'Act as if what you do makes a difference. It does. - William James','2024-05-20 20:51:41',NULL),(67,17,'Believe you can and you’re halfway there. - Theodore Roosevelt','2024-05-20 20:51:41',NULL),(68,17,'When you have a dream, you’ve got to grab it and never let go.','2024-05-20 20:51:41',NULL),(69,17,'I have decided to stick with love. Hate is too great a burden to bear. - Martin Luther King, Jr.','2024-05-20 20:51:41',NULL),(70,17,'Life is what happens when you’re busy making other plans. - John Lennon','2024-05-20 20:51:41',NULL),(71,6,'Optimism is the faith that leads to achievement. Nothing can be done without hope and confidence.','2024-05-20 20:51:41',NULL),(72,6,'Change your thoughts and you change your world.','2024-05-20 20:51:41',NULL),(73,6,'With the new day comes new strength and new thoughts.','2024-05-20 20:51:41',NULL),(74,6,'You must do the things you think you cannot do.','2024-05-20 20:51:41',NULL),(75,6,'It is never too late to be what you might have been.','2024-05-20 20:51:41',NULL),(76,4,'Keep your face to the sunshine and you cannot see a shadow.','2024-05-20 20:51:41',NULL),(77,4,'Once you replace negative thoughts with positive ones, you’ll start having positive results.','2024-05-20 20:51:41',NULL),(78,4,'Positive thinking will let you do everything better than negative thinking will.','2024-05-20 20:51:41',NULL),(79,4,'Pessimism leads to weakness, optimism to power.','2024-05-20 20:51:41',NULL),(80,4,'The only place where your dreams become impossible is in your own thinking.','2024-05-20 20:51:41',NULL),(81,15,'Every day may not be good... but there’s something good in every day.','2024-05-20 20:51:41',NULL),(82,15,'Miracles happen to those who believe in them.','2024-05-20 20:51:41',NULL),(83,15,'Be the change that you wish to see in the world.','2024-05-20 20:51:41',NULL),(84,15,'No act of kindness, no matter how small, is ever wasted.','2024-05-20 20:51:41',NULL),(85,15,'What we think, we become.','2024-05-20 20:51:41',NULL),(86,2,'Your limitation—it’s only your imagination.','2024-05-20 20:51:41',NULL),(87,2,'Push yourself, because no one else is going to do it for you.','2024-05-20 20:51:41',NULL),(88,2,'Sometimes later becomes never. Do it now.','2024-05-20 20:51:41',NULL),(89,2,'Great things never come from comfort zones.','2024-05-20 20:51:41',NULL),(90,2,'Dream it. Wish it. Do it.','2024-05-20 20:51:41',NULL),(91,21,'This is beautiful day!','2024-05-20 21:20:43',NULL),(92,3,'This is beautiful day! from Alice Smith','2024-05-20 21:48:13',NULL),(93,18,'Explore the possibilities. Life is full of wonderful opportunities.','2024-05-20 21:54:06',NULL),(94,18,'Every day brings new choices. Embrace them!','2024-05-20 21:54:06',NULL),(95,18,'Life is a journey filled with lessons, hardships, heartaches, joys, celebrations and special moments.','2024-05-20 21:54:06',NULL),(96,18,'Happiness is a direction, not a place. Keep moving forward!','2024-05-20 21:54:06',NULL),(97,18,'No matter how you feel, get up, dress up, show up, and never give up.','2024-05-20 21:54:06',NULL),(98,18,'Do more things that make you forget to check your phone.','2024-05-20 21:54:06',NULL),(99,18,'When you focus on the good, the good gets better.','2024-05-20 21:54:06',NULL),(100,18,'Happiness often sneaks in through a door you didn’t know you left open.','2024-05-20 21:54:06',NULL),(101,18,'Try to be a rainbow in someone’s cloud.','2024-05-20 21:54:06',NULL),(102,18,'It’s a wonderful thing to be optimistic. It keeps you healthy and it keeps you resilient.','2024-05-20 21:54:06',NULL),(103,16,'Life changes very quickly, in a very positive way, if you let it.','2024-05-20 21:54:06',NULL),(104,16,'Optimism is the one quality more associated with success and happiness than any other.','2024-05-20 21:54:06',NULL),(105,16,'Keep looking up… that’s the secret of life.','2024-05-20 21:54:06',NULL),(106,16,'Smile more. Smiling can make you and others happy.','2024-05-20 21:54:06',NULL),(107,16,'Believe you can and you’re halfway there.','2024-05-20 21:54:06',NULL),(108,16,'You do not find the happy life. You make it.','2024-05-20 21:54:06',NULL),(109,16,'The most wasted of days is one without laughter.','2024-05-20 21:54:06',NULL),(110,16,'Stay close to anything that makes you glad you are alive.','2024-05-20 21:54:06',NULL),(111,16,'Think of all the beauty still left around you and be happy.','2024-05-20 21:54:06',NULL),(112,16,'To succeed in life, you need three things: a wishbone, a backbone, and a funny bone.','2024-05-20 21:54:06',NULL),(113,8,'What lies behind us and what lies before us are tiny matters compared to what lies within us.','2024-05-20 21:54:06',NULL),(114,8,'Every day brings new choices.','2024-05-20 21:54:06',NULL),(115,8,'Be so happy that when others look at you they become happy too.','2024-05-20 21:54:06',NULL),(116,8,'Optimism is a happiness magnet. If you stay positive, good things and good people will be drawn to you.','2024-05-20 21:54:06',NULL),(117,8,'Joy is not in things; it is in us.','2024-05-20 21:54:06',NULL),(118,8,'Start each day with a positive thought and a grateful heart.','2024-05-20 21:54:06',NULL),(119,8,'Every moment is a fresh beginning.','2024-05-20 21:54:06',NULL),(120,8,'Once you replace negative thoughts with positive ones, you’ll start having positive results.','2024-05-20 21:54:06',NULL),(121,8,'Happiness is not by chance, but by choice.','2024-05-20 21:54:06',NULL),(122,8,'Be the energy you want to attract.','2024-05-20 21:54:06',NULL),(123,9,'Find out where joy resides, and give it a voice far beyond singing. For to miss the joy is to miss all.','2024-05-20 21:54:06',NULL),(124,9,'The sun himself is weak when he first rises, and gathers strength and courage as the day gets on.','2024-05-20 21:54:06',NULL),(125,9,'The way to get started is to quit talking and begin doing.','2024-05-20 21:54:06',NULL),(126,9,'Happiness is not something you postpone for the future; it is something you design for the present.','2024-05-20 21:54:06',NULL),(127,9,'It is not how much we have, but how much we enjoy, that makes happiness.','2024-05-20 21:54:06',NULL),(128,9,'The best way to predict your future is to create it.','2024-05-20 21:54:06',NULL),(129,9,'You are never too old to set another goal or to dream a new dream.','2024-05-20 21:54:06',NULL),(130,9,'Look at the sparrows; they do not know what they will do in the next moment. Let us literally live from moment to moment.','2024-05-20 21:54:06',NULL),(131,9,'Perfection is not attainable, but if we chase perfection we can catch excellence.','2024-05-20 21:54:06',NULL),(132,9,'Life is 10% what happens to me and 90% of how I react to it.','2024-05-20 21:54:06',NULL),(133,3,'Every day is a new adventure. Embrace it with open arms!','2024-05-20 21:56:14',NULL),(134,3,'Remember to celebrate the small victories in life. Every step forward is a step towards achieving something bigger.','2024-05-20 21:56:14',NULL),(135,3,'The only limit to our realization of tomorrow will be our doubts of today.','2024-05-20 21:56:14',NULL),(136,3,'Be yourself; everyone else is already taken.','2024-05-20 21:56:14',NULL),(137,3,'Stay positive in every situation and everything you do, never stop trying, have faith don’t stop due to failure.','2024-05-20 21:56:14',NULL),(138,3,'An optimistic mindset finds a way it can be done; a pessimistic mindset looks for all the ways it can’t be done.','2024-05-20 21:56:14',NULL),(139,3,'Motivation is what gets you started. Habit is what keeps you going.','2024-05-20 21:56:14',NULL),(140,3,'Success is not how high you have climbed, but how you make a positive difference to the world.','2024-05-20 21:56:14',NULL),(141,3,'Happiness is not something ready made. It comes from your own actions.','2024-05-20 21:56:14',NULL),(142,3,'Positivity always wins…Always.','2024-05-20 21:56:14',NULL),(143,5,'Life is about making an impact, not making an income.','2024-05-20 21:56:14',NULL),(144,5,'Whatever the mind of man can conceive and believe, it can achieve.','2024-05-20 21:56:14',NULL),(145,5,'Strive not to be a success, but rather to be of value.','2024-05-20 21:56:14',NULL),(146,5,'You miss 100% of the shots you don’t take.','2024-05-20 21:56:14',NULL),(147,5,'Every strike brings me closer to the next home run.','2024-05-20 21:56:14',NULL),(148,5,'Definiteness of purpose is the starting point of all achievement.','2024-05-20 21:56:14',NULL),(149,5,'Life is what happens when you’re busy making other plans.','2024-05-20 21:56:14',NULL),(150,5,'We become what we think about.','2024-05-20 21:56:14',NULL),(151,5,'Life is 10% what happens to us and 90% how we react to it.','2024-05-20 21:56:14',NULL),(152,5,'The most common way people give up their power is by thinking they don’t have any.','2024-05-20 21:56:14',NULL),(153,10,'The mind is everything. What you think you become.','2024-05-20 21:56:14',NULL),(154,10,'The best time to plant a tree was 20 years ago. The second best time is now.','2024-05-20 21:56:14',NULL),(155,10,'An unexamined life is not worth living.','2024-05-20 21:56:14',NULL),(156,10,'Eighty percent of success is showing up.','2024-05-20 21:56:14',NULL),(157,10,'Your time is limited, so don’t waste it living someone else’s life.','2024-05-20 21:56:14',NULL),(158,10,'Winning isn’t everything, but wanting to win is.','2024-05-20 21:56:14',NULL),(159,10,'I am not a product of my circumstances. I am a product of my decisions.','2024-05-20 21:56:14',NULL),(160,10,'Every child is an artist.  The problem is how to remain an artist once he grows up.','2024-05-20 21:56:14',NULL),(161,10,'You can never cross the ocean until you have the courage to lose sight of the shore.','2024-05-20 21:56:14',NULL),(162,10,'Either you run the day, or the day runs you.','2024-05-20 21:56:14',NULL),(163,12,'Whether you think you can or you think you can’t, you’re right.','2024-05-20 21:56:14',NULL),(164,12,'The two most important days in your life are the day you are born and the day you find out why.','2024-05-20 21:56:14',NULL),(165,12,'Whatever you can do, or dream you can, begin it. Boldness has genius, power and magic in it.','2024-05-20 21:56:14',NULL),(166,12,'The best revenge is massive success.','2024-05-20 21:56:14',NULL),(167,12,'People often say that motivation doesn’t last. Well, neither does bathing.  That’s why we recommend it daily.','2024-05-20 21:56:14',NULL),(168,12,'Life shrinks or expands in proportion to one’s courage.','2024-05-20 21:56:14',NULL),(169,12,'If you hear a voice within you say ‘you cannot paint,’ then by all means paint and that voice will be silenced.','2024-05-20 21:56:14',NULL),(170,12,'There is only one way to avoid criticism: do nothing, say nothing, and be nothing.','2024-05-20 21:56:14',NULL),(171,12,'Ask and it will be given to you; search, and you will find; knock and the door will be opened for you.','2024-05-20 21:56:14',NULL),(172,12,'The only person you are destined to become is the person you decide to be.','2024-05-20 21:56:14',NULL),(173,14,'Go confidently in the direction of your dreams. Live the life you have imagined.','2024-05-20 21:56:14',NULL),(174,14,'When I stand before God at the end of my life, I would hope that I would not have a single bit of talent left and could say, I used everything you gave me.','2024-05-20 21:56:14',NULL),(175,14,'Few things can help an individual more than to place responsibility on him, and to let him know that you trust him.','2024-05-20 21:56:14',NULL),(176,14,'Certain things catch your eye, but pursue only those that capture the heart.','2024-05-20 21:56:14',NULL),(177,14,'Believe you can and you’re halfway there.','2024-05-20 21:56:14',NULL),(178,14,'Everything you’ve ever wanted is on the other side of fear.','2024-05-20 21:56:14',NULL),(179,14,'Start where you are. Use what you have.  Do what you can.','2024-05-20 21:56:14',NULL),(180,14,'Fall seven times and stand up eight.','2024-05-20 21:56:14',NULL),(181,14,'When one door of happiness closes, another opens, but often we look so long at the closed door that we do not see the one that has been opened for us.','2024-05-20 21:56:14',NULL),(182,14,'Everything has beauty, but not everyone can see.','2024-05-20 21:56:14',NULL),(183,11,'New tweet','2024-05-21 13:12:33',NULL),(184,11,'My new tweets 2. ','2024-05-21 13:15:02',NULL);
/*!40000 ALTER TABLE `tweets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profiles`
--

DROP TABLE IF EXISTS `user_profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_profiles` (
  `user_id` int NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `bio` text,
  `profile_pic_url` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_profiles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profiles`
--

LOCK TABLES `user_profiles` WRITE;
/*!40000 ALTER TABLE `user_profiles` DISABLE KEYS */;
INSERT INTO `user_profiles` VALUES (2,'John Doe','Experienced Marketing Specialist dedicated to effective strategic marketing plans and insightful market analysis.','profiles/2/1716253981461_man-6253257_1280.jpg','2023-12-18 00:00:00','2024-05-20 21:13:01','Qweasd123'),(3,'Alice Smith','HR Coordinator with a passion for enhancing employee engagement and optimizing workplace culture.',NULL,'2023-08-20 00:00:00',NULL,'Qweasd123'),(4,'Bob Johnson','Data Analyst with extensive experience in extracting insights from big data.','profiles/4/1716253945016_woman-5716875_1280.png','2023-06-21 00:00:00','2024-05-20 21:12:25','Qweasd123'),(5,'Carol White','Product Manager driving innovative solutions across the tech industry.',NULL,'2022-12-03 00:00:00',NULL,'Qweasd123'),(6,'Eve Black','Senior Developer focused on creating robust and scalable software applications.','profiles/6/1716253932680_farmer-6871500_1280.jpg','2022-08-25 00:00:00','2024-05-20 21:12:13','Qweasd123'),(7,'Frank Clark','Junior Developer committed to learning and applying new technologies effectively.','profiles/7/1716253888095_cats-7122943_1280.png','2023-03-13 00:00:00','2024-05-20 21:11:28','Qweasd123'),(8,'Grace Hopper','Junior Developer enthusiastic about coding and solving complex problems.','profiles/8/1716253822650_animal-3861398_1280.png','2024-01-07 00:00:00','2024-05-20 21:10:23','Qweasd123'),(9,'Harry Potter','Lead Developer with a strong record of project management and team leadership.','profiles/9/1716253863342_cat-3643705_1280.jpg','2023-06-21 00:00:00','2024-05-20 21:11:03','Qweasd123'),(10,'Ivy Bean','Marketing Manager known for outstanding campaign management skills.',NULL,'2023-09-29 00:00:00',NULL,'Qweasd123'),(11,'LydiaLu','Bio123','profiles/11/1717508946758_IMG_9058.JPG','2024-05-16 09:04:58','2024-06-04 09:49:07','123'),(12,'James White','Marketing Coordinator with a talent for creating high-impact marketing strategies.',NULL,'2023-12-13 00:00:00',NULL,'Qweasd123'),(13,'Linda Green','HR Specialist dedicated to fostering a positive work environment.',NULL,'2023-09-14 00:00:00',NULL,'Qweasd123'),(14,'Patricia Hill','Senior Data Analyst who turns data into actionable insights.',NULL,'2023-06-01 00:00:00',NULL,'Qweasd123'),(15,'Michael Scott','Associate Product Manager passionate about product development and market fit.','profiles/15/1716253966526_pirate-2123313_1280.png','2022-11-23 00:00:00','2024-05-20 21:12:47','Qweasd123'),(16,'Lisa Brown','Software Engineer with expertise in full stack development.','profiles/16/1716253808795_animal-2345418_1280.jpg','2022-09-14 00:00:00','2024-05-20 21:10:09','Qweasd123'),(17,'Chris Black','Software Architect who architects scalable and efficient software solutions.','profiles/17/1716253911045_fox-5679446_1280.png','2023-03-23 00:00:00','2024-05-20 21:11:51','Qweasd123'),(18,'Sandra Lee','System Analyst with a focus on optimizing business workflows through technology.','profiles/18/1716253769661_ai-generated-7879929_1280.jpg','2023-10-19 00:00:00','2024-05-20 21:09:30','Qweasd123'),(19,'Joseph Wilson','QA Engineer committed to ensuring software reliability and performance.','profiles/19/1716253846943_cartoon-7422577_1280.png','2023-07-31 00:00:00','2024-05-20 21:10:47','Qweasd123'),(20,'Emily Clark','Digital Marketing Specialist with a knack for driving digital transformation.',NULL,'2023-09-09 00:00:00',NULL,'Qweasd123'),(21,'Anthony Moore','PR Manager who excels at managing public relations and brand reputation.',NULL,'2023-06-21 00:00:00',NULL,'Qweasd123');
/*!40000 ALTER TABLE `user_profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `manager_id` int DEFAULT NULL,
  `start_date` date NOT NULL,
  `address_id` int DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `manager_id` (`manager_id`),
  KEY `idx_username` (`username`),
  KEY `idx_department` (`department`),
  KEY `idx_title` (`title`),
  KEY `idx_address_id` (`address_id`),
  KEY `idx_department_title` (`department`,`title`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`manager_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `users_ibfk_2` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'JohnDoe','john.doe@chirped.com','Marketing','Marketing Specialist',NULL,'2024-01-16',2,'2023-12-17 00:00:00',NULL),(3,'AliceSmith','alice.smith@chirped.com','Human Resources','HR Coordinator',NULL,'2023-09-08',3,'2023-08-19 00:00:00',NULL),(4,'BobJohnson','bob.johnson@chirped.com','Data Science','Data Analyst',NULL,'2023-07-20',4,'2023-06-20 00:00:00',NULL),(5,'CarolWhite','carol.white@chirped.com','Product Management','Product Manager',NULL,'2023-01-01',5,'2022-12-02 00:00:00',NULL),(6,'EveBlack','eve.black@chirped.com','Development','Senior Developer',2,'2022-09-23',6,'2022-08-24 00:00:00',NULL),(7,'FrankClark','frank.clark@chirped.com','Development','Junior Developer',2,'2023-04-11',7,'2023-03-12 00:00:00',NULL),(8,'GraceHopper','grace.hopper@chirped.com','Development','Junior Developer',2,'2024-02-05',8,'2024-01-06 00:00:00',NULL),(9,'HarryPotter','harry.potter@chirped.com','Development','Lead Developer',2,'2023-07-20',9,'2023-06-20 00:00:00',NULL),(10,'IvyBean','ivy.bean@chirped.com','Marketing','Marketing Manager',NULL,'2023-10-28',10,'2023-09-28 00:00:00',NULL),(11,'LydiaLu','lydia.lu@chirped.com','Development','Software Developer',2,'2023-11-17',1,'2023-11-15 00:00:00',NULL),(12,'JamesWhite','james.white@chirped.com','Marketing','Marketing Coordinator',NULL,'2024-01-11',11,'2023-12-12 00:00:00',NULL),(13,'LindaGreen','linda.green@chirped.com','Human Resources','HR Specialist',NULL,'2023-10-13',12,'2023-09-13 00:00:00',NULL),(14,'PatriciaHill','patricia.hill@chirped.com','Data Science','Senior Data Analyst',NULL,'2023-05-31',13,'2023-05-01 00:00:00',NULL),(15,'MichaelScott','michael.scott@chirped.com','Product Management','Associate Product Manager',NULL,'2022-12-22',14,'2022-11-22 00:00:00',NULL),(16,'LisaBrown','lisa.brown@chirped.com','Development','Software Engineer',2,'2022-10-13',15,'2022-09-13 00:00:00',NULL),(17,'ChrisBlack','chris.black@chirped.com','Development','Software Architect',2,'2023-03-22',16,'2023-02-20 00:00:00',NULL),(18,'SandraLee','sandra.lee@chirped.com','Development','System Analyst',2,'2023-11-17',17,'2023-10-18 00:00:00',NULL),(19,'JosephWilson','joseph.wilson@chirped.com','Development','QA Engineer',2,'2023-08-29',18,'2023-07-30 00:00:00',NULL),(20,'EmilyClark','emily.clark@chirped.com','Marketing','Digital Marketing Specialist',NULL,'2023-10-08',19,'2023-09-08 00:00:00',NULL),(21,'AnthonyMoore','anthony.moore@chirped.com','Marketing','PR Manager',NULL,'2023-07-20',20,'2023-06-20 00:00:00',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-28  7:17:19
