/*    ==Paramètres de script==

    Version du serveur source : SQL Server 2016 (13.0.4206)
    Édition du moteur de base de données source : Microsoft SQL Server Enterprise Edition
    Type du moteur de base de données source : SQL Server autonome

    Version du serveur cible : SQL Server 2017
    Édition du moteur de base de données cible : Microsoft SQL Server Standard Edition
    Type du moteur de base de données cible : SQL Server autonome
*/
USE [master]
GO
/****** Object:  Database [MyLibrary]    Script Date: 18/10/2017 16:45:57 ******/
CREATE DATABASE [MyLibrary]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'MyLibrary', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER2\MSSQL\DATA\MyLibrary.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'MyLibrary_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER2\MSSQL\DATA\MyLibrary_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [MyLibrary] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [MyLibrary].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [MyLibrary] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [MyLibrary] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [MyLibrary] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [MyLibrary] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [MyLibrary] SET ARITHABORT OFF 
GO
ALTER DATABASE [MyLibrary] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [MyLibrary] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [MyLibrary] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [MyLibrary] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [MyLibrary] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [MyLibrary] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [MyLibrary] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [MyLibrary] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [MyLibrary] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [MyLibrary] SET  ENABLE_BROKER 
GO
ALTER DATABASE [MyLibrary] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [MyLibrary] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [MyLibrary] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [MyLibrary] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [MyLibrary] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [MyLibrary] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [MyLibrary] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [MyLibrary] SET RECOVERY FULL 
GO
ALTER DATABASE [MyLibrary] SET  MULTI_USER 
GO
ALTER DATABASE [MyLibrary] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [MyLibrary] SET DB_CHAINING OFF 
GO
ALTER DATABASE [MyLibrary] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [MyLibrary] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [MyLibrary] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'MyLibrary', N'ON'
GO
ALTER DATABASE [MyLibrary] SET QUERY_STORE = OFF
GO
USE [MyLibrary]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [MyLibrary]
GO
/****** Object:  Table [dbo].[AccessProfile]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccessProfile](
	[accProfileCode] [int] IDENTITY(1,1) NOT NULL,
	[accProfileName] [varchar](20) NULL,
 CONSTRAINT [PK_AccessProfile] PRIMARY KEY CLUSTERED 
(
	[accProfileCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Address]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Address](
	[addId] [bigint] IDENTITY(1,1) NOT NULL,
	[cusResidId] [bigint] NOT NULL,
	[cusChargeId] [bigint] NOT NULL,
	[addLabel] [varchar](255) NULL,
	[addFirstName] [varchar](50) NULL,
	[addLastName] [varchar](50) NULL,
	[addCompany] [varchar](50) NULL,
	[addNumber] [varchar](10) NULL,
	[addStreetType] [varchar](15) NULL,
	[addStreetName] [varchar](50) NULL,
	[addComplement] [varchar](50) NULL,
	[addZipCode] [varchar](10) NULL,
	[addCity] [varchar](50) NULL,
	[addSecurityCode] [varchar](50) NULL,
	[addPhone] [varchar](50) NULL,
 CONSTRAINT [pkaddId] PRIMARY KEY CLUSTERED 
(
	[addId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Association]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Association](
	[booIsbn13] [varchar](13) NOT NULL,
	[subId] [bigint] NOT NULL,
 CONSTRAINT [pkAssociationBookSubTheme] PRIMARY KEY CLUSTERED 
(
	[booIsbn13] ASC,
	[subId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Author]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Author](
	[autId] [bigint] IDENTITY(1,1) NOT NULL,
	[autLastName] [varchar](50) NOT NULL,
	[autFirstName] [varchar](50) NULL,
	[autBiography] [varchar](255) NULL,
	[autStatusCode] [int] NULL,
 CONSTRAINT [pkAuthor] PRIMARY KEY CLUSTERED 
(
	[autId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Book]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Book](
	[booIsbn13] [varchar](13) NOT NULL,
	[vatCode] [int] NULL,
	[ediId] [int] NULL,
	[booTitle] [varchar](50) NOT NULL,
	[booSubtitle] [varchar](50) NULL,
	[booPublishYear] [date] NOT NULL,
	[booPriceHT] [float] NOT NULL,
	[booResume] [varchar](max) NULL,
	[booQuantity] [int] NULL,
	[booStatus] [int] NOT NULL,
	[booFrontCover] [varchar](255) NULL,
	[booPageNumber] [int] NULL,
	[bookLangCode] [int] NULL,
	[forId] [int] NOT NULL,
 CONSTRAINT [pkBook] PRIMARY KEY CLUSTERED 
(
	[booIsbn13] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BookLanguage]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookLanguage](
	[bookLangCode] [int] IDENTITY(1,1) NOT NULL,
	[bookLangName] [varchar](50) NOT NULL,
	[bookLangStatus] [int] NULL,
 CONSTRAINT [PK_BookLanguage] PRIMARY KEY CLUSTERED 
(
	[bookLangCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[cusId] [bigint] IDENTITY(1978,1) NOT NULL,
	[cusGender] [nchar](10) NULL,
	[cusLastName] [varchar](50) NOT NULL,
	[cusFirstName] [varchar](50) NOT NULL,
	[cusOrganisationName] [varchar](50) NULL,
	[cusEmail] [varchar](50) NOT NULL,
	[cusPhoneNumber] [varchar](50) NULL,
	[cusDateOfBirth] [date] NULL,
	[cusPassword] [varchar](255) NOT NULL,
	[cusSalt] [varchar](255) NOT NULL,
	[cusIP] [varchar](50) NULL,
	[cusStatus] [int] NOT NULL,
	[cusComment] [varchar](255) NULL,
	[cusClearPassword] [varchar](50) NULL,
 CONSTRAINT [pkCustomer] PRIMARY KEY CLUSTERED 
(
	[cusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Definitions]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Definitions](
	[keyName] [varchar](50) NOT NULL,
	[booIsbn13] [varchar](13) NOT NULL,
 CONSTRAINT [pkDefKeyNameBooIsbn13] PRIMARY KEY CLUSTERED 
(
	[keyName] ASC,
	[booIsbn13] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Determinate]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Determinate](
	[staCode] [int] NOT NULL,
	[purId] [bigint] NOT NULL,
	[detTime] [varchar](50) NOT NULL,
 CONSTRAINT [pkDeterminate] PRIMARY KEY CLUSTERED 
(
	[staCode] ASC,
	[purId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Editor]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Editor](
	[ediId] [int] IDENTITY(1,1) NOT NULL,
	[ediName] [varchar](50) NOT NULL,
	[ediPresentation] [varchar](255) NULL,
	[ediStatusCode] [int] NOT NULL,
 CONSTRAINT [PK_Editor] PRIMARY KEY CLUSTERED 
(
	[ediId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[empId] [bigint] IDENTITY(1,1) NOT NULL,
	[empLastName] [varchar](50) NOT NULL,
	[empFirstName] [varchar](50) NOT NULL,
	[empLogin] [varchar](50) NOT NULL,
	[empPassword] [varchar](255) NOT NULL,
	[empSalt] [varchar](255) NOT NULL,
	[empDateStart] [date] NOT NULL,
	[empDateEnd] [date] NULL,
	[empStatus] [int] NOT NULL,
	[accProfileCode] [int] NOT NULL,
	[empComment] [varchar](255) NULL,
 CONSTRAINT [pkEmployee] PRIMARY KEY CLUSTERED 
(
	[empId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Formats]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Formats](
	[forId] [int] IDENTITY(1,1) NOT NULL,
	[forName] [varchar](50) NULL,
	[forStatus] [int] NULL,
 CONSTRAINT [PK_Formats_1] PRIMARY KEY CLUSTERED 
(
	[forId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Have]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Have](
	[offId] [bigint] NOT NULL,
	[booIsbn13] [varchar](13) NOT NULL,
 CONSTRAINT [pkHave] PRIMARY KEY CLUSTERED 
(
	[offId] ASC,
	[booIsbn13] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Keywords]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Keywords](
	[keyName] [varchar](50) NOT NULL,
	[keyStatus] [int] NULL,
 CONSTRAINT [pkKeywords] PRIMARY KEY CLUSTERED 
(
	[keyName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MyLibrary]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MyLibrary](
	[myLibId] [bigint] IDENTITY(1,1) NOT NULL,
	[myLibName] [varchar](50) NOT NULL,
	[myLibLogo] [varchar](255) NULL,
	[myLibEmail] [varchar](50) NULL,
	[myLibPhone] [varchar](20) NULL,
	[myLibSiret] [varchar](14) NULL,
	[myLibCGU] [varchar](max) NULL,
	[myLibAddNumber] [varchar](3) NULL,
	[myLibAddStreetName] [varchar](50) NULL,
	[myLibAddComplement] [varchar](50) NULL,
	[myLibAddZipCode] [varchar](50) NULL,
	[myLibAddCity] [varchar](50) NULL,
 CONSTRAINT [pkMyLibId] PRIMARY KEY CLUSTERED 
(
	[myLibId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Offer]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Offer](
	[offId] [bigint] IDENTITY(1,1) NOT NULL,
	[offName] [varchar](50) NOT NULL,
	[offText] [varchar](255) NULL,
	[offDateStart] [date] NOT NULL,
	[offDateEnd] [date] NULL,
	[offDiscount] [float] NULL,
	[offPicture] [varchar](255) NULL,
	[offStatus] [int] NULL,
 CONSTRAINT [pkOffer] PRIMARY KEY CLUSTERED 
(
	[offId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderLine]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderLine](
	[ordLineId] [int] IDENTITY(1,1) NOT NULL,
	[purId] [bigint] NULL,
	[booIsbn13] [varchar](13) NULL,
	[ordLineQuantity] [int] NOT NULL,
	[ordBookPriceHT] [float] NOT NULL,
	[ordBookVAT] [float] NOT NULL,
	[ordDiscount] [float] NULL,
 CONSTRAINT [pkOrderLine] PRIMARY KEY CLUSTERED 
(
	[ordLineId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderStatus]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderStatus](
	[staCode] [int] NOT NULL,
	[staName] [varchar](50) NULL,
 CONSTRAINT [PK_OrderStatus] PRIMARY KEY CLUSTERED 
(
	[staCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Payment]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payment](
	[payId] [int] IDENTITY(1,1) NOT NULL,
	[purId] [bigint] NOT NULL,
	[payValidate] [bit] NOT NULL,
	[payChoice] [varchar](50) NULL,
	[payDate] [varchar](50) NOT NULL,
	[payCardType] [varchar](50) NULL,
	[payOwnerName] [varchar](50) NULL,
 CONSTRAINT [pkpayId] PRIMARY KEY CLUSTERED 
(
	[payId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Purchase]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Purchase](
	[purId] [bigint] IDENTITY(57231,1) NOT NULL,
	[cusId] [bigint] NOT NULL,
	[shippingCostId] [bigint] NOT NULL,
	[addDeliveryId] [bigint] NOT NULL,
	[addInvoiceId] [bigint] NOT NULL,
	[purIP] [varchar](50) NULL,
	[shippingDate] [varchar](50) NULL,
	[shippingNumber] [bigint] NULL,
	[purInternalId] [varchar](50) NULL,
 CONSTRAINT [pkpurId] PRIMARY KEY CLUSTERED 
(
	[purId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Review]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Review](
	[revId] [bigint] IDENTITY(1,1) NOT NULL,
	[cusId] [bigint] NOT NULL,
	[booIsbn13] [varchar](13) NOT NULL,
	[ordLineId] [int] NOT NULL,
	[revNote] [float] NULL,
	[revComment] [varchar](255) NULL,
	[revDate] [date] NULL,
	[revIP] [varchar](50) NULL,
	[revStatus] [int] NULL,
 CONSTRAINT [pkreview] PRIMARY KEY CLUSTERED 
(
	[revId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ShippingCost]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ShippingCost](
	[shipId] [bigint] IDENTITY(1,1) NOT NULL,
	[shipName] [varchar](50) NOT NULL,
	[shipCost] [float] NOT NULL,
 CONSTRAINT [pkShippingCost] PRIMARY KEY CLUSTERED 
(
	[shipId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StatusDisplay]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StatusDisplay](
	[staCode] [int] NOT NULL,
	[staName] [varchar](50) NULL,
 CONSTRAINT [PK_dbo.StatusDisplay] PRIMARY KEY CLUSTERED 
(
	[staCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SubTheme]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SubTheme](
	[subId] [bigint] IDENTITY(1,1) NOT NULL,
	[theId] [bigint] NULL,
	[subName] [varchar](50) NOT NULL,
	[subDescription] [varchar](255) NULL,
	[subStatus] [int] NULL,
 CONSTRAINT [pkSubTheme] PRIMARY KEY CLUSTERED 
(
	[subId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Theme]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Theme](
	[theId] [bigint] IDENTITY(1,1) NOT NULL,
	[theName] [varchar](50) NOT NULL,
	[theDescription] [varchar](255) NULL,
	[theStatus] [int] NULL,
 CONSTRAINT [pkTheme] PRIMARY KEY CLUSTERED 
(
	[theId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Vat]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vat](
	[vatCode] [int] IDENTITY(1,1) NOT NULL,
	[vatRate] [float] NOT NULL,
	[vatName] [varchar](20) NOT NULL,
	[vatStatus] [int] NULL,
 CONSTRAINT [PK_Vat] PRIMARY KEY CLUSTERED 
(
	[vatCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Writing]    Script Date: 18/10/2017 16:45:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Writing](
	[booIsbn13] [varchar](13) NOT NULL,
	[autId] [bigint] NOT NULL,
 CONSTRAINT [pkWriting] PRIMARY KEY CLUSTERED 
(
	[booIsbn13] ASC,
	[autId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[AccessProfile] ON 

INSERT [dbo].[AccessProfile] ([accProfileCode], [accProfileName]) VALUES (0, N'No Access')
INSERT [dbo].[AccessProfile] ([accProfileCode], [accProfileName]) VALUES (1, N'Administrateur
')
INSERT [dbo].[AccessProfile] ([accProfileCode], [accProfileName]) VALUES (2, N'Acces Editeur
')
INSERT [dbo].[AccessProfile] ([accProfileCode], [accProfileName]) VALUES (3, N'Acces Client')
INSERT [dbo].[AccessProfile] ([accProfileCode], [accProfileName]) VALUES (4, N'Acces Refusé
')
SET IDENTITY_INSERT [dbo].[AccessProfile] OFF
SET IDENTITY_INSERT [dbo].[Address] ON 

INSERT [dbo].[Address] ([addId], [cusResidId], [cusChargeId], [addLabel], [addFirstName], [addLastName], [addCompany], [addNumber], [addStreetType], [addStreetName], [addComplement], [addZipCode], [addCity], [addSecurityCode], [addPhone]) VALUES (1, 1981, 1981, N'Adresse Maison
', N'Percy
', N'Lizotte
', NULL, N'1', N'avenue', N'de Marseille', NULL, N'75014', N'PARIS', NULL, N'06 15 78 45 65')
INSERT [dbo].[Address] ([addId], [cusResidId], [cusChargeId], [addLabel], [addFirstName], [addLastName], [addCompany], [addNumber], [addStreetType], [addStreetName], [addComplement], [addZipCode], [addCity], [addSecurityCode], [addPhone]) VALUES (2, 1981, 1981, N'Adresse Travail
', N'Percy
', N'Lizotte
', N'Gallenkamp
', N'1
', N'avenue', N'de Lyon', NULL, N'75014', N'PARIS', NULL, N'06 46 48 45 46')
INSERT [dbo].[Address] ([addId], [cusResidId], [cusChargeId], [addLabel], [addFirstName], [addLastName], [addCompany], [addNumber], [addStreetType], [addStreetName], [addComplement], [addZipCode], [addCity], [addSecurityCode], [addPhone]) VALUES (3, 1982, 1982, N'Adresse Maison', N'Brunelle
', N'Armand 
', NULL, N'10
', N'rue', N'de Lyon', N'app 104', N'41000', N'BLOIS', NULL, N'06 78 46 51 48')
INSERT [dbo].[Address] ([addId], [cusResidId], [cusChargeId], [addLabel], [addFirstName], [addLastName], [addCompany], [addNumber], [addStreetType], [addStreetName], [addComplement], [addZipCode], [addCity], [addSecurityCode], [addPhone]) VALUES (4, 1982, 1982, N'Adresse Soeur', N'Brunelle', N'Brigitte', NULL, N'6', N'rue', N'du Chemin', NULL, N'41000', N'BLOIS', NULL, N'06 58 46 51 45')
INSERT [dbo].[Address] ([addId], [cusResidId], [cusChargeId], [addLabel], [addFirstName], [addLastName], [addCompany], [addNumber], [addStreetType], [addStreetName], [addComplement], [addZipCode], [addCity], [addSecurityCode], [addPhone]) VALUES (5, 1985, 1985, N'Adresse Maison', N'Courtois', N'Geoffrey 
', NULL, N'25', N'rue', N'Albert Camis', NULL, N'29100', N'DOUARNENEZ', NULL, N'06 45 84 62 51')
INSERT [dbo].[Address] ([addId], [cusResidId], [cusChargeId], [addLabel], [addFirstName], [addLastName], [addCompany], [addNumber], [addStreetType], [addStreetName], [addComplement], [addZipCode], [addCity], [addSecurityCode], [addPhone]) VALUES (6, 1985, 1985, N'Adresse autre', N'Courtois', N'Gilles', NULL, N'1', N'avenue', N'de Toulouse', NULL, N'16420', N'BRIGUEIL', N'051424V', N'06 78 41 15 49')
INSERT [dbo].[Address] ([addId], [cusResidId], [cusChargeId], [addLabel], [addFirstName], [addLastName], [addCompany], [addNumber], [addStreetType], [addStreetName], [addComplement], [addZipCode], [addCity], [addSecurityCode], [addPhone]) VALUES (7, 1985, 1985, N'Adresse Travail', N'Courtois', N'Geoffrey', NULL, N'1', N'rue', N'du Puit', NULL, N'29100', N'DOUARNENEZ', NULL, N'09 75 45 65 42')
INSERT [dbo].[Address] ([addId], [cusResidId], [cusChargeId], [addLabel], [addFirstName], [addLastName], [addCompany], [addNumber], [addStreetType], [addStreetName], [addComplement], [addZipCode], [addCity], [addSecurityCode], [addPhone]) VALUES (8, 1986, 1986, N'Adresse Principale', N'De Launay
', N'Diane', NULL, NULL, NULL, N'Le chemin', NULL, N'92130', N'ISSY LES MOULINEAUX', NULL, N'01 78 62 54 48')
INSERT [dbo].[Address] ([addId], [cusResidId], [cusChargeId], [addLabel], [addFirstName], [addLastName], [addCompany], [addNumber], [addStreetType], [addStreetName], [addComplement], [addZipCode], [addCity], [addSecurityCode], [addPhone]) VALUES (9, 1986, 1986, N'Adresse Secondaire', N'De Launay', N'Diane', NULL, NULL, NULL, N'Lieu Perdu', NULL, N'91240', N'ST MICHEL SUR ORGE', NULL, N'01 65 48 54 65')
INSERT [dbo].[Address] ([addId], [cusResidId], [cusChargeId], [addLabel], [addFirstName], [addLastName], [addCompany], [addNumber], [addStreetType], [addStreetName], [addComplement], [addZipCode], [addCity], [addSecurityCode], [addPhone]) VALUES (10, 1987, 1987, N'Adresse Principale', N'Parmentier
', N'Robinette 
', NULL, N'15', N'rue', N'du Retour', NULL, N'70100', N'ARC LES GRAY', NULL, N'06 45 48 45 65')
INSERT [dbo].[Address] ([addId], [cusResidId], [cusChargeId], [addLabel], [addFirstName], [addLastName], [addCompany], [addNumber], [addStreetType], [addStreetName], [addComplement], [addZipCode], [addCity], [addSecurityCode], [addPhone]) VALUES (11, 1987, 1987, N'Adresse Secondaire', N'Parmentier
', N'Robinette', NULL, N'1', N'avenue', N'de Lyon', NULL, N'75014', N'PARIS', NULL, N'06 14 15 46 66')
INSERT [dbo].[Address] ([addId], [cusResidId], [cusChargeId], [addLabel], [addFirstName], [addLastName], [addCompany], [addNumber], [addStreetType], [addStreetName], [addComplement], [addZipCode], [addCity], [addSecurityCode], [addPhone]) VALUES (12, 1979, 1980, N'Adresse Maison', N' Dubé
', N'Olympia 
', NULL, N'1', N'avenue', N'de Moscou', NULL, N'41000', N'BLOIS', NULL, N'04 15 14 65 25')
SET IDENTITY_INSERT [dbo].[Address] OFF
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9781444799132', 5)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9781444799132', 9)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782226328717', 8)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782226393197', 5)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782226399168', 5)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782253014997', 7)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782253083276', 8)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782253132943', 8)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782266130288', 33)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782266130288', 37)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782266272575', 4)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782266272575', 5)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782266276542', 5)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782266276542', 8)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782290138922', 6)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782290138922', 9)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782290145548', 9)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782290145548', 23)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782290152331', 6)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782290152331', 9)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782302064263', 11)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782302064263', 34)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782312015774', 7)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782344020937', 1)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782412020869', 31)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782747082600', 3)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782747082600', 4)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782755614664', 11)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782755614664', 14)
INSERT [dbo].[Association] ([booIsbn13], [subId]) VALUES (N'9782755614664', 34)
SET IDENTITY_INSERT [dbo].[Author] ON 

INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (1, N'Nothomb', N'Amélie', N'Amélie Nothom, nom de plume de Fabienne Claire, baronne Nothomb, née le 9 juillet 1966 à Etterbeek, Bruxelles, est une romancière belge d''expression française.
', 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (2, N'Whitehead', N'Colson', N'Colson Whitehead (son nom complet est Arch Colson Chipp Whitehead), né le 6 novembre 1969 à New York, est un romancier américain.
', 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (3, N'Horn', N'Mike', N'Mike Horn (Michael Frédérick Horn), né le 16 juillet 1966 (51 ans) à Johannesbourg (Afrique du Sud), est un explorateur-aventurier de nationalité suisse et sud-africaine, de culture afrikaner, résidant en Suisse.
', 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (4, N'King', N'Stephen', N'Stephen King est un écrivain américain né le 21 septembre 1947 à Portland, dans le Maine (États-Unis).
', 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (5, N'Thilliez', N'Franck', NULL, 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (6, N'Kinsella', N'Sophie', N'Sophie Kinsella, née le 12 décembre 1969 à Londres, est une femme de lettres britannique.
', 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (7, N'Burd', N'Barry', NULL, 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (8, N'Martin', N'George R.R.', N'George R. R. Martin, né le 20 septembre 1948 à Bayonne (New Jersey)
', 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (9, N'Asset', N'Bernard', NULL, 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (10, N'Briand', N'Arnaud', NULL, 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (11, N'Delacroix', N'Sibylle', N'Sibylle Delacroix realise des illustrations pour la jeunesse (Hansel et Gretel, La Barbe Bleue, Pinocchio...) et pour la presse
', 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (12, N'Lambert', N'Marc', NULL, 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (13, N'Jeffries', N'Sabrina', NULL, 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (14, N'Zep', N'', N'Zep, né Philippe Chappuis le 15 décembre 1967 à Onex, Genève, Suisse, est un auteur suisse de bandes dessinées dont la plus connue est Titeuf
', 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (15, N'Grisham', N'John', N'John Grisham, né le 8 février 1955 à Jonesboro, dans l''Arkansas, est un écrivain américain, auteur de plusieurs romans policiers 
', 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (16, N'Himekawa', N'Akira', NULL, 0)
INSERT [dbo].[Author] ([autId], [autLastName], [autFirstName], [autBiography], [autStatusCode]) VALUES (17, N'Hugo', N'Victor', NULL, 1)
SET IDENTITY_INSERT [dbo].[Author] OFF
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9781444799132', 0, 5, N'The whistler', NULL, CAST(N'2014-01-01' AS Date), 7.55, NULL, 26, 0, N'image\cover\17.png', 384, 1, 7)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782226328717', 0, 1, N'Fin de ronde', NULL, CAST(N'2015-01-01' AS Date), 22.5, N'Dans la chambre 217 de l''hôpital Kiner Memorial, Brady Hartsfield, alias Mr Mercedes, gît dans un état végétatif depuis sept ans, soumis aux expérimentations du docteur Babineau. Mais derrière son rictus douloureux et son regard fixe, Brady est bien vivant. Et capable de commettre un nouveau carnage sans même quitter son lit. Sa première pensée est pour Bill Hodges, son plus vieil ennemi. « Après Mr Mercedes et Carnets noirs, les fans de Stephen King trouveront dans ce mélange de suspense et d''horreur la conclusion parfaite à la trilogie de l''inspecteur Hodges. » Publishers Weekly Stephen King a écrit plus de 50 romans, autant de best-sellers, et plus de 200 nouvelles. Couronné par de nombreux prix littéraires, il est devenu un mythe vivant de la littérature américaine (médaille de la National Book Foundation en 2003 pour sa contribution aux lettres américaines, Grand Master Award en 2007 pour l''ensemble de son ouvre). Fin de partie est le troisième volet de la trilogie mettant en scène Bill Hodges, après Carnets noirs (2016) et Mr Mercedes (2015), récompensé par le prix Edgar-Allan Poe 2015 du meilleur roman', 3, 0, N'image\cover\7.png', 430, 9, 2)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782226393197', 0, 1, N'Underground railroad ', NULL, CAST(N'2017-01-01' AS Date), 21.64, N'Cora, seize ans, est esclave sur une plantation de coton dans la Géorgie d’avant la guerre de Sécession. Abandonnée par sa mère lorsqu’elle était enfant, elle survit tant bien que mal à la violence de sa condition. Lorsque Caesar, un esclave récemment arrivé de Virginie, lui propose de s’enfuir, elle accepte et tente, au péril de sa vie,  de gagner avec lui les États libres du Nord. De la Caroline du Sud à l’Indiana en passant par le Tennessee, Cora va vivre une incroyable odyssée. Traquée comme une bête par un impitoyable chasseur d’esclaves qui l’oblige à fuir, sans cesse, le « misérable cœur palpitant » des villes, elle fera tout pour conquérir sa liberté. L’une des prouesses de Colson Whitehead est de matérialiser l’« Underground Railroad », le célèbre réseau clandestin d’aide aux esclaves en fuite qui devient ici une véritable voie ferrée souterraine, pour explorer, avec une originalité et une maîtrise époustouflantes, les fondements et la mécanique du racisme. À la fois récit d’un combat poignant et réflexion saisissante sur la lecture de l’Histoire, ce roman, couronné par le prix Pulitzer, est une œuvre politique aujourd’hui plus que jamais nécessaire. « Un roman puissant et presque hallucinatoire. Une histoire essentielle pour comprendre les Américains d’hier et d’aujourd’hui. » The New York Times Né à New York en 1969, Colson Whitehead a été découvert en France avec la traduction de son premier roman, L’Intuitionniste. Ont suivi notamment Ballades pour John Henry, Le Colosse de New York ou encore Apex ou le cache-blessure (publiés aux Éditions Gallimard), qui tous ont confirmé l’exceptionnel talent de Colson Whitehead à inventer de véritables machines romanesques, irriguées par une méditation sur les mythologies américaines, ainsi que par une réflexion très politique sur la question raciale.', 100, 0, N'image\cover\1.png', 243, 9, 2)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782226399168', 0, 1, N'Frappe-toi le cœur ', NULL, CAST(N'2006-01-01' AS Date), 15.970000267028809, N'« Frappe-toi le cœur, c’est là qu’est le génie. » Alfred de Musset Amélie Nothomb est née à Kobé en 1967. Dès son premier roman Hygiène de l’assassin paru en 1992, elle s’est imposée comme un écrivain singulier. En 1999, elle obtient avec Stupeur et tremblements le Grand Prix de l’Académie française. Frappe-toi le cœur  est son 26ème roman.', 299, 0, N'image\cover\0.png', 180, 9, 7)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782253014997', 0, 7, N'Les Contemplations', NULL, CAST(N'2015-01-01' AS Date), 3.5, NULL, 50, 0, N'image\cover\19.png', 253, 9, 2)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782253083276', 0, 8, N'Carnets noirs', NULL, CAST(N'2016-01-01' AS Date), 8.9, N'En prenant sa retraite, John Rothstein a plongé dans le désespoir les millions de lecteurs des aventures de Jimmy Gold. Devenu fou de rage depuis la disparition de son héros favori, Morris Bellamy assassine le vieil écrivain pour s''emparer de sa fortune et, surtout, de ses précieux carnets de notes. Le bonheur dans le crime ? C''était compter sans les mauvais tours du destin... et la perspicacité du détective Bill Hodges. Trente ans après Misery, Stephen King renoue avec l''un de ses thèmes de prédilection : l''obsession d''un fan. Dans ce formidable roman noir, où l''on retrouve les protagonistes de Mr Mercedes (prix Edgar 2015), il rend un superbe hommage au pouvoir de la fiction, capable de susciter chez le lecteur le meilleur... comme le pire. Un suspense de très haut niveau et une intrigue au déroulé parfait qui vont faire passer au lecteur de belles nuits blanches. Renaud Baronian, Le Parisien. Né en 1947 à Portland (Maine), Stephen King a connu son premier succès en 1974 avec Carrie. En une trentaine d''années, il a publié plus de cinquante romans et autant de nouvelles, certains sous le pseudonyme de Richard Bachman. Il a reçu de nombreuses distinctions littéraires, dont le prestigieux Grand Master Award des Mystery Writers of America pour l''ensemble de sa carrière en 2007. Son oeuvre a été largement adaptée au cinéma', 0, 0, N'image\cover\6.png', NULL, 9, 2)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782253132943', 0, 8, N'Mr Mercedes', NULL, CAST(N'2015-01-01' AS Date), 22.19, N'Une fois n''est pas coutume, Stephen King délaisse le fantastique pour nous proposer un vrai roman noir. Mr Mercedes est un thriller pur jus où il sonde le cerveau malade d''un terrible chauffard psychopathe au goût prononcé pour les tueries de masse. Un roman poisseux et inconfortable, qui nous trouble par sa virtuosité narrative et l''épaisseur de ses personnages. Au fil d''une chasse à l''homme époustouflante, on se dit que, même hors de son terrain de jeu favori, le King reste le meilleur d''entre tous pour nous faire voyager dans les ténèbres. ', 105, 0, N'image\cover\4.png', 320, 9, 2)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782266130288', 0, 10, N'Latitude zéro ', N'40000 KM pour partir à la rencontre du monde', CAST(N'2014-01-01' AS Date), 6.99, N'Le temps s''est ramassé sur lui-même. Il est devenu compact. Les dix-sept mois de Latitude 0 se sont peu à peu transformés, dans ma mémoire, en une sorte d''instant unique, d''une intensité époustouflante, pendant lequel j''ai vécu presque simultanément tout ce qu''il est possible de vivre. J''ai vu des enfants naître et des hommes mourir. J''ai longé des fleuves et escaladé des montagnes. J''ai connu la paix et j''ai vécu la guerre. J''ai éclaté de rire et j''ai pleuré toutes les larmes de mon corps. J''ai éprouvé la joie, la déception, la tristesse, la peur de mourir, l''euphorie et le désespoir. "   Mike Horn ne vit que pour réaliser ses rêves. Alors un jour de printemps, il a laissé sur un rivage sa femme et ses deux filles qu''il adore, pour faire le tour de la planète, seul, en suivant la ligne de l''équateur. À pied, en pirogue, à la voile ou à vélo, à travers trois océans et deux continents, Mike Horn a cheminé sur ce fil invisible. Sans jamais s''en écarter de plus de quarante kilomètres. Il raconte ici comment un homme seul parvient à se fondre dans la nature pour qu''elle le laisse passer - et parfois il n''y arrive pas. Dans la forêt vierge du Brésil, mordu par un serpent, il est resté quatre jours aveugle, à demi conscient, sans savoir si le venin était ou non mortel. Mais le plus grand danger, c''est l''homme et ses guerres. En Afrique, des rebelles ont arrêté Mike Horn et l''ont condamné à mort. Il ne s''en est tiré que de justesse. Pourquoi tout ça ? Parce qu''il voulait aller au bout de son rêve, plus loin dans la rencontre de la nature et des hommes.   On découvre dans ce récit hors du commun un homme qui a peur, qui s''émerveille, qui a mal, mais qui avance, toujours.', 0, 1, N'image\cover\3.png', 175, 9, 2)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782266272575', 0, 10, N'L''accro du shopping à la rescousse     ', NULL, CAST(N'2016-01-01' AS Date), 6.99, N'Après avoir perdu la tête à Hollywood, entraînée par une célébrité aussi soudaine qu''éphémère, Becky redescend brutalement sur terre. Sans prévenir personne, son père est parti à la recherche d''un vieil ami, semble-t-il détenteur d''un lourd secret. Et Tarkie, le mari de sa meilleure amie, a disparu, comme hypnotisé par un gourou new age aux intentions troubles… Il n''en faudra pas plus à Becky pour entasser mari, amie et enfants dans un camping-car et partir à leur poursuite, dans un voyage s''annonçant haut en couleur', 250, 0, N'image\cover\9.png', 384, 9, 2)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782266276542', 0, 10, N'Rêver ', NULL, CAST(N'2017-01-01' AS Date), 8.03, N'Psychologue réputée pour son expertise dans les affaires criminelles, Abigaël souffre d''une narcolepsie sévère qui lui fait confondre le rêve avec la réalité. De nombreux mystères planent autour de la jeune femme, notamment concernant l''accident qui a coûté la vie à son père et à sa fille, et dont elle est miraculeusement sortie indemne. L’affaire de disparition d’enfants sur laquelle elle travaille brouille ses derniers repères et fait bientôt basculer sa vie dans un cauchemar éveillé… Dans cette enquête, il y a une proie et un prédateur :...', 6, 0, N'image\cover\8.png', 656, 9, 2)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782290138922', 0, 7, N'La tour sombre', N'Nouvelle Edition augmentée Tome 1', CAST(N'2015-01-01' AS Date), 7.8, N'Roland le Pistolero est le dernier justicier et aventurier d''un monde dont il cherche à inverser la destruction programmée. Pour cela, il doit arracher au sorcier vêtu de noir les secrets qui le mèneront vers la Tour sombre. Premier tome de la série, suivi d''un court roman.', 200, 0, N'image\cover\5.png', NULL, 9, 2)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782290145548', 0, 7, N'Quand la passion l''emporte', N'Les hommes du Duc Tome 2', CAST(N'2014-01-01' AS Date), 6.99, N'Isabelle, la femme de Victor Cale, disparaît après leur mariage. Il la cherche et découvre qu''elle est impliquée dans un crime. Dix ans plus tard, il la croise par hasard. Isabella a beaucoup changé. La passion ressurgit entre eux deux, plus forte qu''avant', 150, 0, N'image\cover\15.png', NULL, 9, 2)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782290152331', 0, 7, N'Chroniques du chevalier errant     ', NULL, CAST(N'2015-01-01' AS Date), 7.56, N'Qu''il joute ou qu''il guerroie, le chevalier errant n''a d''autre code que celui de l''honneur. Il met son talent au service des causes les plus nobles et prend la défense des opprimés. Ser Arlan suit cette ligne de conduite et l''inculque à son écuyer Dunk. La rencontre de ce dernier avec un étrange garçon qui se fait appeler Oeuf va changer à jamais son destin', 0, 0, N'image\cover\11.png', NULL, 9, 2)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782302064263', 1, 11, N'The Legend Of Zelda', N'Art and artifacts', CAST(N'2014-01-01' AS Date), 31.98, N'Depuis une trentaine d’année, Nintendo a su faire vivre de grandes aventures épiques à son jeune héros Link dans sa quête permanente pour protéger le monde d’Hyrule et la princesse Zelda. Que ce soit les premiers dessins, les premiers sprites des jeux sorties sur Nintendo NES ou les grandes illustrations du dernier jeu sorti sur Switch, tous sont réunis dans plus de 400 pages d’illustrations agrémentés de commentaires et interview.', 50, 0, N'image\cover\18.png', 416, 9, 5)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782312015774', 0, 9, N'Souriez !', NULL, CAST(N'2015-01-01' AS Date), 11.34, N'"Recette du mariage explosif"- Mélangez deux anciens amants accompagnés par leur compagnon respectif.- Jetez de l''huile sur le feu.- Retirez l''alliance et mettez-la au frais.- Montez les mariés en neige.- Epluchez les sentiments jusqu''à la dernière couche (attention, ça pique les yeux!)- Délayez avec beaucoup d''alcool.- Dans une sauteuse, versez un quiproquo et beaucoup d''égo.- Faites flamber le tout. Pimenter avec de l''ironie.Servez chaud.', 20, 0, N'image\cover\14.png', 148, 9, 2)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782344020937', 0, 4, N'Titeuf', N'tome 15 – A fond le slip', CAST(N'2013-01-01' AS Date), 9.92, N'L''album qui va faire craquer toute la famille ! Que ce soit en classe, dans la cour de récré, à la maison ou dans la rue, Titeuf est très attentif au monde qui l’entoure. Mais en ce moment il est carrément perdu ! Entre les manifs contre les déchets nucléaires qui puent du slip comme les couches de Zizie et les gens qui descendent dans la rue contre les IVGétariens, il a l’impression qu’aujourd’hui il faut avoir un avis sur tout. Mais pô facile de faire le bon choix dans un monde qui devient de plus en plus compliqué ! Heureusement qu’il reste les copains et les vidéos sur internet pour tout nous expliquer. La mèche la plus célèbre de la BD est de retour ! Après un album remarqué imaginant son passage à l’adolescence, Titeuf revient pour un album de gags à la fois drôles, tendres et totalement connectés à notre époque. Des grands phénomènes de notre société moderne aux petites gamelles de la cour de récré, Zep utilise avec une virtuosité rare l’humour du quotidien pour scruter le monde à travers le regard de l’enfance – parfois insolent, toujours juste.', 15, 0, N'image\cover\16.png', 48, 9, 4)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782412020869', 0, 3, N'JAVA pour les nuls', NULL, CAST(N'2018-01-01' AS Date), 23.57, N'Grâce à ce livre, vous allez rapidement écrire rapidement vos premières applets Java, sans pour autant devenir un gourou de la programmation objet', 150, 0, N'image\cover\10.png', 480, 9, 7)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782747082600', 0, 2, N'Graines de sable', NULL, CAST(N'2016-01-01' AS Date), 11.24, N'Graines de sable évoque avec sensibilité le bonheur et la nostalgie des vacances.', 5, 0, N'image\cover\13.png', NULL, 9, 2)
INSERT [dbo].[Book] ([booIsbn13], [vatCode], [ediId], [booTitle], [booSubtitle], [booPublishYear], [booPriceHT], [booResume], [booQuantity], [booStatus], [booFrontCover], [booPageNumber], [bookLangCode], [forId]) VALUES (N'9782755614664', 0, 6, N'Ayrton Senna', N'la victoire à tout prix     ', CAST(N'2015-01-01' AS Date), 33.07, N'Evoquer la mémoire d''Ayrton Senna, évoquer sa personnalité, ses capacités ou sa popularité, c''est susciter la nostalgie d''un pilote légendaire. Comme le résuma un jour Gérard Ducarouge, le directeur technique de l''équipe Lotus aux côtés duquel il passa trois années et eu tout le temps de l''apprécier, Ayrton Senna était « un être d''exception, un perpétuel étonnement qui était doué pour tout. »C''est parce qu''il était bien plus qu''un sportif d''exception que Senna est, encore aujourd''hui, vingt ans après sa disparition, aussi vivant dans nos esprits. Personne n''a oublié sa détermination, son abnégation et son agressivité, sa façon de décrocher la pôle position en allant toujours plus vite et plus loin, et parfois au-delà de lui-même. Personne n''a oublié non plus son duel homérique avec Alain Prost, ses accrochages et son sens de la vindicte et de la justice. Personne n''a oublié ce visage déterminé, ce regard mélancolique et cette foi inébranlable en lui, qui lui ont valu de réaliser des prouesses et de nous gratifier sans cesse d''exploits.Adulé par certains, décrié par d''autres, Senna a eu le mérite de faire l''unanimité, même auprès de ses pairs, sur sa valeur et sa performance. Pour lui, la victoire n''avait pas de prix. Elle passait avant tout. Pour lui, il ne fallait jamais transiger avec le travail et toujours chercher à se surpasser, quitte même à aller parfois au-delà de ses limites. Prince de la pole position, roi des courses dantesques, il était la référence en matière de bravoure, de courage et de talent.Il n''a cessé durant toute sa carrière de nous gratifier de prouesses passées à jamais à la postérité. Des prouesses que le photographe Bernard Asset a immortalisées et que nous vous proposons de découvrir dans cet ouvrage richement illustré pour vivre le parcours de cette divinité qui, comme Achille, a payé de sa vie le prix de l''immortalité.', 15, 0, N'image\cover\12.png', NULL, 9, 5)
SET IDENTITY_INSERT [dbo].[BookLanguage] ON 

INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (0, N'Allemand
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (1, N'Anglais
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (2, N'Arabe
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (3, N'Chinois
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (4, N'Coréen
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (5, N'Croate
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (6, N'Danois
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (7, N'Espagnol
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (8, N'Finnois
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (9, N'Français
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (10, N'Grec
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (11, N'Hongrois
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (12, N'Italien
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (13, N'Néerlandais
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (14, N'Portugais
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (15, N'Roumain
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (16, N'Russe
', NULL)
INSERT [dbo].[BookLanguage] ([bookLangCode], [bookLangName], [bookLangStatus]) VALUES (17, N'Turc
', NULL)
SET IDENTITY_INSERT [dbo].[BookLanguage] OFF
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([cusId], [cusGender], [cusLastName], [cusFirstName], [cusOrganisationName], [cusEmail], [cusPhoneNumber], [cusDateOfBirth], [cusPassword], [cusSalt], [cusIP], [cusStatus], [cusComment], [cusClearPassword]) VALUES (1979, N'F
       ', N'Sauvé
', N'Clarimunda', N'Solid Future
', N'ClarimundaSauve@armyspy.com', N'01 46 00 29 13
', CAST(N'1963-09-16' AS Date), N'FCB903F0BD8B15BE57F70F13F8B29E365177ACC1691EB51D3EE568F2D1A3956461DE3E8A76286C254B418A0A2B4DC678D542F6BCF8F71906BA48306254DAF86F', N'vs6saem5qmjn17c28qotqvqeu6', N'"117.153.221.193
"
', 0, N'pwd : "iNaNosh5h
"
', N'iNaNosh5h')
INSERT [dbo].[Customer] ([cusId], [cusGender], [cusLastName], [cusFirstName], [cusOrganisationName], [cusEmail], [cusPhoneNumber], [cusDateOfBirth], [cusPassword], [cusSalt], [cusIP], [cusStatus], [cusComment], [cusClearPassword]) VALUES (1980, N'F
       ', N'Olympia', N' Dubé

', N'Coon Chicken Inn
', N'OlympiaDube@armyspy.com', N'05 64 60 12 28
', CAST(N'1982-05-19' AS Date), N'CFF21837BCAC99C9AC76925AF05E8F3E2249228C362BC33B072BB836ACF8AD56AE037A061696EE609F7E51128E143E34987B1447B0CA99D45319F34BC2984EC9', N'soijuil0c58ijcbno2k6i4aglb', N'159.157.196.44
', 1, N'pwd : Gemee9evah', N'Gemee9evah')
INSERT [dbo].[Customer] ([cusId], [cusGender], [cusLastName], [cusFirstName], [cusOrganisationName], [cusEmail], [cusPhoneNumber], [cusDateOfBirth], [cusPassword], [cusSalt], [cusIP], [cusStatus], [cusComment], [cusClearPassword]) VALUES (1981, N'H
       ', N'Percy', N'Lizotte', NULL, N'PercyLizotte@teleworm.us', N'05 12 69 04 35
', CAST(N'1968-11-29' AS Date), N'9EDCF6A25181C7A87F003FA4214B1F7E30680A013775B33EA2609AB19BD5F886102664EF6017472576CEC857D2CAB1A80554E5A1CCDE27BEEA41F1B2EEDBEB91', N'spi9i96862lbs4052g10ns5ovp', N'207.105.185.229
', 1, N'pwd : meele9Ree8of', N'meele9Ree8of')
INSERT [dbo].[Customer] ([cusId], [cusGender], [cusLastName], [cusFirstName], [cusOrganisationName], [cusEmail], [cusPhoneNumber], [cusDateOfBirth], [cusPassword], [cusSalt], [cusIP], [cusStatus], [cusComment], [cusClearPassword]) VALUES (1982, N'H
       ', N'Brunelle', N'Armand', N'Avant Garde Interior Designs
', N'ArmandBrunelle@teleworm.us', N'03 11 70 05 09
', CAST(N'2010-05-01' AS Date), N'55509B9AC2379847E6CD46149B5F00EA13E29458975A0C88CDD3E0B219DBF5D45B35799FB808335EFA4E595508ADC0E3F15540688E42B5CF341D9C556568DDB4', N'dse3n74aqiq84kmu092b28v45m', N'80.29.91.9
', 1, N'pwd : wo0Eizohmah', N'wo0Eizohmah')
INSERT [dbo].[Customer] ([cusId], [cusGender], [cusLastName], [cusFirstName], [cusOrganisationName], [cusEmail], [cusPhoneNumber], [cusDateOfBirth], [cusPassword], [cusSalt], [cusIP], [cusStatus], [cusComment], [cusClearPassword]) VALUES (1983, N'H
       ', N'Quincy 
', N'Givry', N'Lee Wards
', N'QuincyGivry@armyspy.com', N'04 31 81 40 56
', CAST(N'1965-09-01' AS Date), N'D3A14C3EE62DE1F56FCC70D89E76DC4B26DBBFC3E55D03966B3D95AD74E4445E980CEF8382651205EBC4AA1FCD7BDD7CFB6282E8DE0E4127E5BB2FA08165E25A', N'7dl43rdclm4do2324ir77ef5v6', N'123.7.193.126
', 0, N'client fictif / pwd : Conage', NULL)
INSERT [dbo].[Customer] ([cusId], [cusGender], [cusLastName], [cusFirstName], [cusOrganisationName], [cusEmail], [cusPhoneNumber], [cusDateOfBirth], [cusPassword], [cusSalt], [cusIP], [cusStatus], [cusComment], [cusClearPassword]) VALUES (1984, N'F
       ', N'Piedalue
', N'Morgana', N'The Warner Brothers Store
', N'MorganaPiedalue@teleworm.us', N'05 55 84 25 13
', CAST(N'1985-02-01' AS Date), N'68249EFFA36D95C43B596CE1AEE6B4F03489AB3F92430B105602EB889704F856542856A5ACF341DF79B771F469AA607C2A30537F0E379D26BEA08A9DFCCE32FC', N'b8t9fs53d2gbc4e4kuch4urdvc', N'74.93.9.192
', 0, N'pwd : Eise8eix8', N'Eise8eix8')
INSERT [dbo].[Customer] ([cusId], [cusGender], [cusLastName], [cusFirstName], [cusOrganisationName], [cusEmail], [cusPhoneNumber], [cusDateOfBirth], [cusPassword], [cusSalt], [cusIP], [cusStatus], [cusComment], [cusClearPassword]) VALUES (1985, N'F         ', N'Courtois
', N'Geoffrey', N'Gallenkamp
', N'GeoffreyCourtois@teleworm.us', N'03 73 17 30 09
', CAST(N'1968-12-03' AS Date), N'221FE81BB6E377683C43449AA1774F1A63D5192F63DB954F078E440B4CE72EA7664CEE51B2B1AE6C7AF44A8CF6151275134031BBD0C3B571CB0EE263D9537796', N'j963pkeohifjrlpanmnn72gc25', N'59.37.245.21
', 1, N'pwd  : IZaecoozu3', N'IZaecoozu3')
INSERT [dbo].[Customer] ([cusId], [cusGender], [cusLastName], [cusFirstName], [cusOrganisationName], [cusEmail], [cusPhoneNumber], [cusDateOfBirth], [cusPassword], [cusSalt], [cusIP], [cusStatus], [cusComment], [cusClearPassword]) VALUES (1986, N'Femme     ', N'De Launay', N'Diane', N'', N'DianedeLaunay@rhyta.com', N'01 22 46 41 41', CAST(N'1964-05-01' AS Date), N'B3AAC82576EA48E88F2FDA9DA46AF6ADEB09B4709C8F259957ECC99A49180E02A3A9C559BDDD8FBB93C92F67B9BB4F00122E6F9675C2B7532D62D785EC316E14', N'pggn8poqbn0q1m1o18klbu3h0q', N'55.166.3.118', 1, N'pwd : oCh4die1goh', N'oCh4die1goh')
INSERT [dbo].[Customer] ([cusId], [cusGender], [cusLastName], [cusFirstName], [cusOrganisationName], [cusEmail], [cusPhoneNumber], [cusDateOfBirth], [cusPassword], [cusSalt], [cusIP], [cusStatus], [cusComment], [cusClearPassword]) VALUES (1987, N'F         ', N'Parmentier
', N'Robinette', NULL, N'RobinetteParmentier@jourrapide.com', N'04 90 27 39 84
', CAST(N'1968-08-01' AS Date), N'04CFEBE087897637B9E78DB65775C65895B0B8C1C3B835FC9A2CB7A14FCC03BBCF798B0BE184530F9BBA9C98BD97901AF468C9EEFC0D1827E5771ECC4DDB23DA', N'6e1pg2aj5ibll3drqk5h15fpir', N'8.47.40.245
', 1, N'pwd : ae0Ewowa', N'ae0Ewowa')
INSERT [dbo].[Customer] ([cusId], [cusGender], [cusLastName], [cusFirstName], [cusOrganisationName], [cusEmail], [cusPhoneNumber], [cusDateOfBirth], [cusPassword], [cusSalt], [cusIP], [cusStatus], [cusComment], [cusClearPassword]) VALUES (1988, N'H         ', N'Meunier
', N'Arthur', NULL, N'ArthurMeunier@rhyta.com
', N'01 06 35 79 24
', CAST(N'1986-06-02' AS Date), N'88FABCD7C0A33A5DFC51761778551B3B64BB6F6A3494A118A7FC4E1AC0C8CB8094F7018F4F26EB5EAF3B7124FE5E500E011E6EE3A9B8556E328DDFDF8BFABE9F', N'55ng2c02urhdqvqe7p70seuv5f', N'56.62.27.186
', 0, N'pwd : cooShai2
', N'cooShai2
')
INSERT [dbo].[Customer] ([cusId], [cusGender], [cusLastName], [cusFirstName], [cusOrganisationName], [cusEmail], [cusPhoneNumber], [cusDateOfBirth], [cusPassword], [cusSalt], [cusIP], [cusStatus], [cusComment], [cusClearPassword]) VALUES (1989, N'Homme     ', N'Garvanèse', N'Guillaume', N'', N'guillaume@coucou.com', N'01 23 45 67 89', CAST(N'1981-04-01' AS Date), N'1B23D93BE488B164DB3E8C728EE82CB1B1ACAD9319C89AB34C1367BF068A050A11D16DA80F49BC78017DC5270BD7CFD5D8440BAAFF0A26490CC7CA87B50EE529', N'qguatsfvlmn58la4bvfeep6m8r', N'', 0, N'', NULL)
SET IDENTITY_INSERT [dbo].[Customer] OFF
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'accident', N'9782266276542')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'bonheur', N'9782747082600')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'brésil', N'9782266130288')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'brésil', N'9782755614664')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'coeur', N'9782226399168')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'esclavage', N'9782226393197')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'fantastique', N'9782290138922')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'formule1', N'9782755614664')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'guerre', N'9782226393197')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'hugo', N'9782253014997')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'inspecteur', N'9782253132943')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'legende', N'9782755614664')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'mystere', N'9782266276542')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'nature', N'9782266130288')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'nostalgie', N'9782747082600')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'odyssée', N'9782226393197')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'poésie', N'9782253014997')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'psychologue', N'9782266276542')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'psychopathe', N'9782253132943')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'science fiction', N'9782290138922')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'sensibilité', N'9782747082600')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'thriller', N'9782226328717')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'thriller', N'9782253083276')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'thriller', N'9782253132943')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'victor', N'9782253014997')
INSERT [dbo].[Definitions] ([keyName], [booIsbn13]) VALUES (N'violence', N'9782226399168')
INSERT [dbo].[Determinate] ([staCode], [purId], [detTime]) VALUES (0, 57250, N'2017-09-24 08:16:06.119')
INSERT [dbo].[Determinate] ([staCode], [purId], [detTime]) VALUES (1, 57232, N'2017-08-18')
INSERT [dbo].[Determinate] ([staCode], [purId], [detTime]) VALUES (1, 57235, N'2017-08-19')
INSERT [dbo].[Determinate] ([staCode], [purId], [detTime]) VALUES (1, 57236, N'2017-08-20')
INSERT [dbo].[Determinate] ([staCode], [purId], [detTime]) VALUES (1, 57251, N'2017-09-23 21:24:10.703')
INSERT [dbo].[Determinate] ([staCode], [purId], [detTime]) VALUES (1, 57252, N'2017-09-24 14:15:27.467')
INSERT [dbo].[Determinate] ([staCode], [purId], [detTime]) VALUES (1, 57253, N'2017-09-25 14:52:35.89')
INSERT [dbo].[Determinate] ([staCode], [purId], [detTime]) VALUES (1, 57254, N'2017-09-25 15:48:18.516')
INSERT [dbo].[Determinate] ([staCode], [purId], [detTime]) VALUES (2, 57232, N'2017-08-24')
INSERT [dbo].[Determinate] ([staCode], [purId], [detTime]) VALUES (2, 57235, N'2017-08-22')
INSERT [dbo].[Determinate] ([staCode], [purId], [detTime]) VALUES (3, 57235, N'2017-08-24')
INSERT [dbo].[Determinate] ([staCode], [purId], [detTime]) VALUES (4, 57250, N'2017-09-24 08:15:05.511')
INSERT [dbo].[Determinate] ([staCode], [purId], [detTime]) VALUES (5, 57235, N'2017-09-24 12:49:38.877')
SET IDENTITY_INSERT [dbo].[Editor] ON 

INSERT [dbo].[Editor] ([ediId], [ediName], [ediPresentation], [ediStatusCode]) VALUES (1, N'Albin Michel', N'Depuis 1992, Albin Michel publie environ 450 nouveautés par an (pour 100 en 1967). Quatrième groupe d''édition français, ses auteurs se classent régulièrement parmi les meilleures ventes', 0)
INSERT [dbo].[Editor] ([ediId], [ediName], [ediPresentation], [ediStatusCode]) VALUES (2, N'Bayard Jeunesse', N'Bayard est un groupe de presse français créé en 1873, juste après la guerre franco-prussienne de 1870, par le père Emmanuel d''Alzon (1810/1880).', 0)
INSERT [dbo].[Editor] ([ediId], [ediName], [ediPresentation], [ediStatusCode]) VALUES (3, N'First Interactive', N'Editis est un groupe d''édition français constitué en janvier 2004 et appartenant à Grupo Planeta (Espagne).', 0)
INSERT [dbo].[Editor] ([ediId], [ediName], [ediPresentation], [ediStatusCode]) VALUES (4, N'Glenat', NULL, 0)
INSERT [dbo].[Editor] ([ediId], [ediName], [ediPresentation], [ediStatusCode]) VALUES (5, N'Hodder & Stoughton Libri', NULL, 0)
INSERT [dbo].[Editor] ([ediId], [ediName], [ediPresentation], [ediStatusCode]) VALUES (6, N'Hugo Sport', N'Hugo, une maison d’éditeurs ! Maison d’éditeurs avec un S…', 0)
INSERT [dbo].[Editor] ([ediId], [ediName], [ediPresentation], [ediStatusCode]) VALUES (7, N'J''ai Lu', N'J''ai lu est une maison d''édition française à Paris créée en 1958 par Frédéric Ditis à la demande d''Henri Flammarion1. Sa ligne éditoriale est très variée', 0)
INSERT [dbo].[Editor] ([ediId], [ediName], [ediPresentation], [ediStatusCode]) VALUES (8, N'Le livre de poche
', N'Le Livre de poche (parfois abrégé LDP) est, à l''origine, le nom d''une collection littéraire apparue le 9 février 1953 sous l''impulsion d''Henri Filipacchi et éditée par la Librairie générale française, filiale d''Hachette depuis 1954.
', 0)
INSERT [dbo].[Editor] ([ediId], [ediName], [ediPresentation], [ediStatusCode]) VALUES (9, N'Les editions du net
', N'Nombreux sont les auteurs qui ont choisi de faire confiance aux Editions du Net pour la publication de leur ouvrage. En résulte un catalogue très fourni où les nouveautés littéraires en tous genres se côtoient.', 0)
INSERT [dbo].[Editor] ([ediId], [ediName], [ediPresentation], [ediStatusCode]) VALUES (10, N'Pocket', N'Créé à l''origine par les Presses de la Cité en 1962 sous le nom de Presses Pocket, Pocket est un éditeur généraliste de littérature au format poche dont les collections couvrent tous les genres de la fiction et de la non-fiction.', 0)
INSERT [dbo].[Editor] ([ediId], [ediName], [ediPresentation], [ediStatusCode]) VALUES (11, N'Soleil', N'Soleil Productions est un éditeur français de bande dessinée localisé à Toulon. Cette maison d''édition a vu le jour en 1989 après l''ouverture par Mourad Boudjellal, de la librairie Bédulle en 1982.', 0)
INSERT [dbo].[Editor] ([ediId], [ediName], [ediPresentation], [ediStatusCode]) VALUES (12, N'Bravard', NULL, 1)
SET IDENTITY_INSERT [dbo].[Editor] OFF
SET IDENTITY_INSERT [dbo].[Employee] ON 

INSERT [dbo].[Employee] ([empId], [empLastName], [empFirstName], [empLogin], [empPassword], [empSalt], [empDateStart], [empDateEnd], [empStatus], [accProfileCode], [empComment]) VALUES (1, N'Grenier', N'Julie', N'Beas1995
', N'0E21235B8469A079129C602ADBD02CF222FB176345D14ECFAD242277837B68E6155F4304F2F62B95B03B5E00CFAE198F1E27256D8E860AB597A394194290261C', N'4cb4qr1dqvbg93o9fdd1h1sgc5', CAST(N'1995-06-30' AS Date), NULL, 0, 1, N'pwd : peP6oon0aith
')
INSERT [dbo].[Employee] ([empId], [empLastName], [empFirstName], [empLogin], [empPassword], [empSalt], [empDateStart], [empDateEnd], [empStatus], [accProfileCode], [empComment]) VALUES (2, N'afpa', N'afpa', N'123', N'123', N'5q9he92etk7rf56trg1t6tud6s', CAST(N'1997-01-01' AS Date), NULL, 0, 1, N'')
INSERT [dbo].[Employee] ([empId], [empLastName], [empFirstName], [empLogin], [empPassword], [empSalt], [empDateStart], [empDateEnd], [empStatus], [accProfileCode], [empComment]) VALUES (3, N'Goudreau', N'Brice', N'Forcive
', N'3A3EB3988DDA527DD30D2C383524380B0BD92EABA19CE80758EB4EA9AD517D9A5AE21853D7CC0BDC1C603D5B55920DA93114A2C484CEA6ACFE6840D46B8C3B55', N'litagk6240e94aed88f7fim385', CAST(N'1997-12-05' AS Date), NULL, 0, 2, N'pwd : aicaG4ree5
')
INSERT [dbo].[Employee] ([empId], [empLastName], [empFirstName], [empLogin], [empPassword], [empSalt], [empDateStart], [empDateEnd], [empStatus], [accProfileCode], [empComment]) VALUES (4, N'Artois', N'Merle', N'Shoseathe1983
', N'F2CBBBD523A8C40C2E3EA80C6E7D66BD89114D9669332B568904A5FD2316A396FE15700648FD2B058A0417702286A2CA70FE258861F8E505FD99CA53A82AA948', N'bc4ufeeejmfdrom6ncglk7bugf', CAST(N'2015-06-15' AS Date), NULL, 0, 2, N'pwd : ahP2aoch
')
INSERT [dbo].[Employee] ([empId], [empLastName], [empFirstName], [empLogin], [empPassword], [empSalt], [empDateStart], [empDateEnd], [empStatus], [accProfileCode], [empComment]) VALUES (5, N'Poirier', N'Amelie', N'Eveyear
', N'6A4CEE286CF30696AE0D8983ABF78393EF7E77A6027ADB474B7A2DAA21FB5927746BAC85FF48C109B461F7CE7652FFEEF88DABD3A3D24BA325A817C7E6AC4A0A', N'ep7aino725t7lakke3s81e5qk2', CAST(N'2015-12-06' AS Date), NULL, 0, 1, N'pwd : baiHeiwie0
')
INSERT [dbo].[Employee] ([empId], [empLastName], [empFirstName], [empLogin], [empPassword], [empSalt], [empDateStart], [empDateEnd], [empStatus], [accProfileCode], [empComment]) VALUES (6, N'Bureau', N'Vaillant', N'Whowerromed
', N'EAE830E4A359E42056B9D9852895FD51CB2A57E01E97DD53BADA1C11DB605CA873354666E32D932E82CE938EFBFEA103CA1D3D50CCEE56A58B802977DDCC8BF9', N'km712q6t0lahiae6raa0tgnqu3', CAST(N'2001-05-02' AS Date), NULL, 0, 1, N'pwd : koong5AiCh3
')
INSERT [dbo].[Employee] ([empId], [empLastName], [empFirstName], [empLogin], [empPassword], [empSalt], [empDateStart], [empDateEnd], [empStatus], [accProfileCode], [empComment]) VALUES (7, N'Guernon', N'Alaine', N'Lactly
', N'82E765B412DB5A44948876D6BF6EF53F647CF053B9F02712FDF1E180EE8F24D4BD833C972B8BAF4BB4BA5051AE39E5099D7AD705389E99A7F758C28EA38DFBBC', N'dic95i54sn7s6iue1svmhbajhu', CAST(N'2014-12-09' AS Date), NULL, 0, 2, N'pwd : AiK1MiGh2
')
INSERT [dbo].[Employee] ([empId], [empLastName], [empFirstName], [empLogin], [empPassword], [empSalt], [empDateStart], [empDateEnd], [empStatus], [accProfileCode], [empComment]) VALUES (8, N'Babin', N'Tempeste', N'Hishad
', N'172758FB2DCBEADFCD2E7E4D00A29A31812A40FA2CBFFB94DBC8195583AD20FF09D3B50754ECAB666D6F3493085ECB60ACFFF7F6AF58F57A56BB4C097FC9D236', N'1etv03tfhsnipnm8v5i5t55gd6', CAST(N'2006-02-11' AS Date), CAST(N'2015-07-08' AS Date), 1, 2, N'pwd : aob2Shex
')
INSERT [dbo].[Employee] ([empId], [empLastName], [empFirstName], [empLogin], [empPassword], [empSalt], [empDateStart], [empDateEnd], [empStatus], [accProfileCode], [empComment]) VALUES (9, N'Pitre', N'Veronique', N'Abousid
', N'045536561A943E3657D2B711530126D94EA1B65C7755C883A81ABA129FBB2AEA6B2B681FE877B4CC2D04F76F9BAADE3CA35E9B014A99BEB905E7B90D81F05DC0', N'pm1at0tql82u6klnjsduo6uola', CAST(N'2000-09-23' AS Date), NULL, 0, 1, N'pwd : ood0oCeumi
')
INSERT [dbo].[Employee] ([empId], [empLastName], [empFirstName], [empLogin], [empPassword], [empSalt], [empDateStart], [empDateEnd], [empStatus], [accProfileCode], [empComment]) VALUES (10, N'Moquin', N'Garland', N'Fultal
', N'B9D9803538C53FB8DF4784CCE0E445CD9B7048F696EA53496D4275B2F6766B2C4D02229FA416F1FD072259232F55A6841081EEB0D4535E7CD88A551DA86D392A', N'hbk0vl900sc5484d6h2drp4oqj', CAST(N'2015-09-23' AS Date), NULL, 0, 2, N'pwd : Ohm1zaishee
')
INSERT [dbo].[Employee] ([empId], [empLastName], [empFirstName], [empLogin], [empPassword], [empSalt], [empDateStart], [empDateEnd], [empStatus], [accProfileCode], [empComment]) VALUES (11, N'Vasseur', N'Caroline', N'cvasseur', N'450F3114765DAE37C04418D3A3889B03877EB0155420E07DE107B8061F1B352D04C6F8EF253B90AE8148CDB43EE1C12B0376D0D1731182FC544FDAA586EB5098', N'ouhnqnqj5kn5s7ufeteggjjful', CAST(N'2010-09-21' AS Date), NULL, 0, 1, N'1234')
INSERT [dbo].[Employee] ([empId], [empLastName], [empFirstName], [empLogin], [empPassword], [empSalt], [empDateStart], [empDateEnd], [empStatus], [accProfileCode], [empComment]) VALUES (12, N'afpa', N'afpa', N'123', N'DB8374D58D60FE79071B33592E8BA2F7F07103366A8F3731E5A7C50C60F78DCA26F53327D149A33561D4C18CF0E3F57D9A5D4F3194A278F6DAC02CB492F38F4F', N'g559ifnh97cjvatja7pmsb5ned', CAST(N'1999-01-01' AS Date), NULL, 0, 1, N'')
SET IDENTITY_INSERT [dbo].[Employee] OFF
SET IDENTITY_INSERT [dbo].[Formats] ON 

INSERT [dbo].[Formats] ([forId], [forName], [forStatus]) VALUES (1, N'Grand Carré 30x30cm
', 0)
INSERT [dbo].[Formats] ([forId], [forName], [forStatus]) VALUES (2, N'Grand Poche 13x20cm
', 0)
INSERT [dbo].[Formats] ([forId], [forName], [forStatus]) VALUES (3, N'Grand Poche 21x26cm
', 0)
INSERT [dbo].[Formats] ([forId], [forName], [forStatus]) VALUES (4, N'Magasine 22x28cm
', 0)
INSERT [dbo].[Formats] ([forId], [forName], [forStatus]) VALUES (5, N'Paysage Grand Format 33x28cm
', 0)
INSERT [dbo].[Formats] ([forId], [forName], [forStatus]) VALUES (6, N'Petit Carré 18x18cm
', 1)
INSERT [dbo].[Formats] ([forId], [forName], [forStatus]) VALUES (7, N'Portrait Standard 20x25cm
', 0)
INSERT [dbo].[Formats] ([forId], [forName], [forStatus]) VALUES (8, N'Format inconnu', 0)
SET IDENTITY_INSERT [dbo].[Formats] OFF
INSERT [dbo].[Have] ([offId], [booIsbn13]) VALUES (5, N'9782412020869')
INSERT [dbo].[Have] ([offId], [booIsbn13]) VALUES (7, N'9781444799132')
INSERT [dbo].[Have] ([offId], [booIsbn13]) VALUES (7, N'9782226399168')
INSERT [dbo].[Have] ([offId], [booIsbn13]) VALUES (7, N'9782290145548')
INSERT [dbo].[Have] ([offId], [booIsbn13]) VALUES (7, N'9782312015774')
INSERT [dbo].[Have] ([offId], [booIsbn13]) VALUES (7, N'9782747082600')
INSERT [dbo].[Have] ([offId], [booIsbn13]) VALUES (8, N'9782226328717')
INSERT [dbo].[Have] ([offId], [booIsbn13]) VALUES (8, N'9782253083276')
INSERT [dbo].[Have] ([offId], [booIsbn13]) VALUES (8, N'9782253132943')
INSERT [dbo].[Have] ([offId], [booIsbn13]) VALUES (8, N'9782290138922')
INSERT [dbo].[Have] ([offId], [booIsbn13]) VALUES (9, N'9782226328717')
INSERT [dbo].[Have] ([offId], [booIsbn13]) VALUES (9, N'9782226393197')
INSERT [dbo].[Have] ([offId], [booIsbn13]) VALUES (9, N'9782226399168')
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'accident', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'amour
', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'BD', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'bonheur', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'brésil', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'coeur', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'esclavage', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'fantastique', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'formule1', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'guerre', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'hugo', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'inspecteur', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'legende', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'mystere', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'nature', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'nostalgie', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'odyssée', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'poésie', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'psychologue', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'psychopathe', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'science fiction', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'sensibilité', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'test', 1)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'thriller', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'victor', 0)
INSERT [dbo].[Keywords] ([keyName], [keyStatus]) VALUES (N'violence', 0)
SET IDENTITY_INSERT [dbo].[MyLibrary] ON 

INSERT [dbo].[MyLibrary] ([myLibId], [myLibName], [myLibLogo], [myLibEmail], [myLibPhone], [myLibSiret], [myLibCGU], [myLibAddNumber], [myLibAddStreetName], [myLibAddComplement], [myLibAddZipCode], [myLibAddCity]) VALUES (0, N'Caroline
', N'images\logo\logo.png
', N'caroline@serviceClientCaroline.com
', N'08 15 45 64 76', N'37900632300016', N'document\cgu.pdf', N'1', N'tombe', N'du soldat inconnu', N'75008', N'PARIS')
SET IDENTITY_INSERT [dbo].[MyLibrary] OFF
SET IDENTITY_INSERT [dbo].[Offer] ON 

INSERT [dbo].[Offer] ([offId], [offName], [offText], [offDateStart], [offDateEnd], [offDiscount], [offPicture], [offStatus]) VALUES (5, N'Rentrée scolaire 2017', N'Les essentiels de la rentrée scolaire 2017
', CAST(N'2017-08-28' AS Date), CAST(N'2017-09-25' AS Date), 5, N'image\event\1.png
', 0)
INSERT [dbo].[Offer] ([offId], [offName], [offText], [offDateStart], [offDateEnd], [offDiscount], [offPicture], [offStatus]) VALUES (7, N'Salon du livre
', N'Le plus grand évènement du livre en France
', CAST(N'2017-10-01' AS Date), CAST(N'2017-12-19' AS Date), 5, N'image\event\2.png
', 0)
INSERT [dbo].[Offer] ([offId], [offName], [offText], [offDateStart], [offDateEnd], [offDiscount], [offPicture], [offStatus]) VALUES (8, N'Stephen King, invité d''honneur
', N'L''essentiel des romans de Stephen King
', CAST(N'2017-09-07' AS Date), CAST(N'2017-10-25' AS Date), 5, N'image\event\3.png
', 0)
INSERT [dbo].[Offer] ([offId], [offName], [offText], [offDateStart], [offDateEnd], [offDiscount], [offPicture], [offStatus]) VALUES (9, N'Foire au livre Albin Michel
', N'Albin Michel vous propose le meilleur de ses collections
', CAST(N'2017-09-14' AS Date), CAST(N'2017-12-31' AS Date), 0, N'image\event\0.png
', 0)
INSERT [dbo].[Offer] ([offId], [offName], [offText], [offDateStart], [offDateEnd], [offDiscount], [offPicture], [offStatus]) VALUES (10, N'Decouverte d''Amélie Nothomb ', N'Les meilleurs livres', CAST(N'2017-01-01' AS Date), CAST(N'2017-03-20' AS Date), 5, NULL, 1)
SET IDENTITY_INSERT [dbo].[Offer] OFF
SET IDENTITY_INSERT [dbo].[OrderLine] ON 

INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (2, 57232, N'9782226399168', 2, 15.97, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (3, 57232, N'9782290138922', 1, 7.8, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (7, 57232, N'9782290152331', 3, 7.56, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (8, 57232, N'9782312015774', 3, 11.34, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (9, 57233, N'9782266272575', 5, 6.99, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (10, 57233, N'9782266272575', 2, 6.99, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (11, 57234, N'9782226328717', 5, 22.5, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (12, 57234, N'9782755614664', 12, 33.07, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (13, 57235, N'9782755614664', 1, 33.07, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (14, 57235, N'9782226399168', 2, 15.97, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (15, 57236, N'9782266276542', 2, 8.03, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (17, 57236, N'9782266272575', 2, 6.99, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (18, 57236, N'9782302064263', 2, 31.98, 20, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (31, 57249, N'9782226328717', 1, 22.5, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (32, 57249, N'9782253132943', 1, 22.190000534057617, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (33, 57249, N'9782226399168', 1, 15.970000267028809, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (34, 57250, N'9782266272575', 1, 6.9899997711181641, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (35, 57250, N'9782290138922', 1, 7.8000001907348642, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (36, 57250, N'9782253014997', 1, 3.5, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (37, 57250, N'9782226393197', 1, 21.639999389648441, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (38, 57251, N'9782226399168', 1, 15.970000267028809, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (39, 57251, N'9782253014997', 1, 3.5, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (40, 57251, N'9782266276542', 1, 8.0299997329711914, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (41, 57251, N'9782290138922', 1, 7.8000001907348642, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (42, 57251, N'9782312015774', 1, 11.340000152587892, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (43, 57251, N'9782412020869', 1, 23.569999694824219, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (44, 57252, N'9782226393197', 1, 21.639999389648441, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (45, 57252, N'9782226399168', 1, 15.970000267028809, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (47, 57254, N'9782226328717', 1, 22.5, 5.5, NULL)
INSERT [dbo].[OrderLine] ([ordLineId], [purId], [booIsbn13], [ordLineQuantity], [ordBookPriceHT], [ordBookVAT], [ordDiscount]) VALUES (48, 57254, N'9782226399168', 1, 15.970000267028809, 5.5, NULL)
SET IDENTITY_INSERT [dbo].[OrderLine] OFF
INSERT [dbo].[OrderStatus] ([staCode], [staName]) VALUES (0, N'Suspendu')
INSERT [dbo].[OrderStatus] ([staCode], [staName]) VALUES (1, N'Commandé')
INSERT [dbo].[OrderStatus] ([staCode], [staName]) VALUES (2, N'payé')
INSERT [dbo].[OrderStatus] ([staCode], [staName]) VALUES (3, N'Expédié')
INSERT [dbo].[OrderStatus] ([staCode], [staName]) VALUES (4, N'Livré')
INSERT [dbo].[OrderStatus] ([staCode], [staName]) VALUES (5, N'Retour en cours')
INSERT [dbo].[OrderStatus] ([staCode], [staName]) VALUES (6, N'Retour accepté')
INSERT [dbo].[OrderStatus] ([staCode], [staName]) VALUES (7, N'Retour refusé')
INSERT [dbo].[OrderStatus] ([staCode], [staName]) VALUES (8, N'Remboursé')
INSERT [dbo].[OrderStatus] ([staCode], [staName]) VALUES (9, N'En cours')
SET IDENTITY_INSERT [dbo].[Payment] ON 

INSERT [dbo].[Payment] ([payId], [purId], [payValidate], [payChoice], [payDate], [payCardType], [payOwnerName]) VALUES (1, 57232, 1, N'carte bancaire', N'2017-08-16', NULL, NULL)
INSERT [dbo].[Payment] ([payId], [purId], [payValidate], [payChoice], [payDate], [payCardType], [payOwnerName]) VALUES (2, 57233, 1, N'carte bancaire', N'2017-08-17', NULL, NULL)
INSERT [dbo].[Payment] ([payId], [purId], [payValidate], [payChoice], [payDate], [payCardType], [payOwnerName]) VALUES (3, 57234, 1, N'carte bancaire', N'2017-08-18', NULL, NULL)
INSERT [dbo].[Payment] ([payId], [purId], [payValidate], [payChoice], [payDate], [payCardType], [payOwnerName]) VALUES (4, 57235, 1, N'carte bancaire', N'2017-08-19', NULL, NULL)
INSERT [dbo].[Payment] ([payId], [purId], [payValidate], [payChoice], [payDate], [payCardType], [payOwnerName]) VALUES (5, 57236, 1, N'carte bancaire', N'2017-08-20', NULL, NULL)
SET IDENTITY_INSERT [dbo].[Payment] OFF
SET IDENTITY_INSERT [dbo].[Purchase] ON 

INSERT [dbo].[Purchase] ([purId], [cusId], [shippingCostId], [addDeliveryId], [addInvoiceId], [purIP], [shippingDate], [shippingNumber], [purInternalId]) VALUES (57232, 1981, 1, 1, 2, N'207.105.185.229
', N'2017-08-24', 15006, NULL)
INSERT [dbo].[Purchase] ([purId], [cusId], [shippingCostId], [addDeliveryId], [addInvoiceId], [purIP], [shippingDate], [shippingNumber], [purInternalId]) VALUES (57233, 1981, 1, 1, 1, N'207.105.185.229

', N'2017-09-15', 15008, NULL)
INSERT [dbo].[Purchase] ([purId], [cusId], [shippingCostId], [addDeliveryId], [addInvoiceId], [purIP], [shippingDate], [shippingNumber], [purInternalId]) VALUES (57234, 1982, 1, 3, 3, N'80.29.91.9', N'2017-08-30', 15009, NULL)
INSERT [dbo].[Purchase] ([purId], [cusId], [shippingCostId], [addDeliveryId], [addInvoiceId], [purIP], [shippingDate], [shippingNumber], [purInternalId]) VALUES (57235, 1985, 1, 7, 8, N'59.37.245.21
', N'2017-08-22', 15007, NULL)
INSERT [dbo].[Purchase] ([purId], [cusId], [shippingCostId], [addDeliveryId], [addInvoiceId], [purIP], [shippingDate], [shippingNumber], [purInternalId]) VALUES (57236, 1986, 1, 8, 9, N'55.166.3.118
', N'2017-08-22', 15010, NULL)
INSERT [dbo].[Purchase] ([purId], [cusId], [shippingCostId], [addDeliveryId], [addInvoiceId], [purIP], [shippingDate], [shippingNumber], [purInternalId]) VALUES (57249, 1985, 1, 5, 6, NULL, N'2017-09-23 21:10:54.504', 15011, N'b2f901d1-0ad4-4b44-9f9c-96fb6c42e9d4')
INSERT [dbo].[Purchase] ([purId], [cusId], [shippingCostId], [addDeliveryId], [addInvoiceId], [purIP], [shippingDate], [shippingNumber], [purInternalId]) VALUES (57250, 1981, 1, 2, 1, NULL, N'2017-09-23 21:22:51.287', 15012, N'df02de82-04e6-47e4-9ff3-1f295cc1f54c')
INSERT [dbo].[Purchase] ([purId], [cusId], [shippingCostId], [addDeliveryId], [addInvoiceId], [purIP], [shippingDate], [shippingNumber], [purInternalId]) VALUES (57251, 1982, 1, 4, 3, NULL, N'2017-09-23 21:24:10.703', 15013, N'9b46a8ac-df41-452b-998b-b157baa5bb48')
INSERT [dbo].[Purchase] ([purId], [cusId], [shippingCostId], [addDeliveryId], [addInvoiceId], [purIP], [shippingDate], [shippingNumber], [purInternalId]) VALUES (57252, 1982, 1, 3, 4, NULL, N'2017-09-24 14:15:27.452', 15014, N'88a272cf-513d-41ae-9d09-0e4d154e5aa4')
INSERT [dbo].[Purchase] ([purId], [cusId], [shippingCostId], [addDeliveryId], [addInvoiceId], [purIP], [shippingDate], [shippingNumber], [purInternalId]) VALUES (57253, 1985, 1, 7, 6, NULL, N'2017-09-25 14:52:35.843', 0, N'bd018a04-b12c-4022-b2e8-eb37e6472e60')
INSERT [dbo].[Purchase] ([purId], [cusId], [shippingCostId], [addDeliveryId], [addInvoiceId], [purIP], [shippingDate], [shippingNumber], [purInternalId]) VALUES (57254, 1981, 1, 2, 1, NULL, N'2017-09-25 15:48:18.47', 0, N'b038d9fe-1f7f-4ecf-8c04-1601baa92b4e')
SET IDENTITY_INSERT [dbo].[Purchase] OFF
SET IDENTITY_INSERT [dbo].[Review] ON 

INSERT [dbo].[Review] ([revId], [cusId], [booIsbn13], [ordLineId], [revNote], [revComment], [revDate], [revIP], [revStatus]) VALUES (2, 1981, N'9782226399168', 2, 2, N'situation connue douloureuse mais exprimée tellement de façon juste mais je le trouve trop court Sourire
', CAST(N'2017-08-20' AS Date), N'207.105.185.229

', 3)
INSERT [dbo].[Review] ([revId], [cusId], [booIsbn13], [ordLineId], [revNote], [revComment], [revDate], [revIP], [revStatus]) VALUES (12, 1981, N'9782290138922', 3, 4, N'Super livre, à lire avant d''aller voir le film au ciné', CAST(N'2017-08-20' AS Date), N'207.105.185.229
', 4)
INSERT [dbo].[Review] ([revId], [cusId], [booIsbn13], [ordLineId], [revNote], [revComment], [revDate], [revIP], [revStatus]) VALUES (13, 1981, N'9782290152331', 7, 3.5, N'j''ai achete ce livre il y a 2 semaines, deja fini', CAST(N'2017-08-20' AS Date), N'207.105.185.229
', 2)
INSERT [dbo].[Review] ([revId], [cusId], [booIsbn13], [ordLineId], [revNote], [revComment], [revDate], [revIP], [revStatus]) VALUES (14, 1981, N'9782312015774', 8, 2.5, N'Histoire sans saveur', CAST(N'2017-08-20' AS Date), N'207.105.185.229
', 2)
INSERT [dbo].[Review] ([revId], [cusId], [booIsbn13], [ordLineId], [revNote], [revComment], [revDate], [revIP], [revStatus]) VALUES (15, 1986, N'9782302064263', 18, 3, N'Magnifique ! Toutes mon enfance avec Zelda !
', CAST(N'2017-08-29' AS Date), N'55.166.3.118
', 3)
INSERT [dbo].[Review] ([revId], [cusId], [booIsbn13], [ordLineId], [revNote], [revComment], [revDate], [revIP], [revStatus]) VALUES (16, 1986, N'9782266272575', 17, 4, N'Un livre génial', CAST(N'2017-08-28' AS Date), N'55.166.3.118
', 4)
INSERT [dbo].[Review] ([revId], [cusId], [booIsbn13], [ordLineId], [revNote], [revComment], [revDate], [revIP], [revStatus]) VALUES (17, 1985, N'9782266272575', 9, 3, N'Lu avidement après la première bouchée : un chaud-froid de sentiments pleins, de coeurs vides, de mots et de maux, bref un Nothomb grand millésime, avec le style, l'' horrible et le détestable dans un consommé d'' amour(s) manqué(s)....
', CAST(N'2017-08-26' AS Date), N'59.37.245.21
', 2)
INSERT [dbo].[Review] ([revId], [cusId], [booIsbn13], [ordLineId], [revNote], [revComment], [revDate], [revIP], [revStatus]) VALUES (18, 1981, N'9782290138922', 10, 5, N'Ce livre est tellement captivant ! Que cela me donne envie de dévorer toute la saga !
', CAST(N'2017-08-22' AS Date), N'207.105.185.229
', 3)
SET IDENTITY_INSERT [dbo].[Review] OFF
SET IDENTITY_INSERT [dbo].[ShippingCost] ON 

INSERT [dbo].[ShippingCost] ([shipId], [shipName], [shipCost]) VALUES (1, N'Livraison Standard', 4.5)
SET IDENTITY_INSERT [dbo].[ShippingCost] OFF
INSERT [dbo].[StatusDisplay] ([staCode], [staName]) VALUES (0, N'Actif')
INSERT [dbo].[StatusDisplay] ([staCode], [staName]) VALUES (1, N'Inactif')
INSERT [dbo].[StatusDisplay] ([staCode], [staName]) VALUES (2, N'En attente de modération')
INSERT [dbo].[StatusDisplay] ([staCode], [staName]) VALUES (3, N'Validé')
INSERT [dbo].[StatusDisplay] ([staCode], [staName]) VALUES (4, N'Rejeté')
SET IDENTITY_INSERT [dbo].[SubTheme] ON 

INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (1, 1, N'bd & humour
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (2, 1, N'manga
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (3, 1, N'livre jeunesse
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (4, 1, N'livre ados et young adults
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (5, 2, N'roman et nouvelles
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (6, 2, N'romans en poche
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (7, 2, N'poésie, théâtre, lettres
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (8, 2, N'roman policier et thriller
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (9, 2, N'fantasy et science fiction
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (10, 3, N'actualite: politique, economie, societe
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (11, 3, N'art, cinema, musique
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (12, 3, N'esoterisme et pananormal
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (14, 3, N'histoire
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (15, 3, N'religions et spiritualités
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (16, 3, N'sciences humaine', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (22, 4, N'cuisine et vins
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (23, 4, N'erotisme
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (24, 4, N'sante, bien être, puériculture
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (25, 5, N'scolaire et soutien scolaire
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (26, 5, N'rentrée universitaire
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (27, 5, N'concours et prépas
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (28, 6, N'dictionnaires et langues
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (29, 6, N'droit', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (30, 6, N'entreprise, management
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (31, 6, N'livres informatique
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (32, 6, N'sciences et medecine
', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (33, 7, N'nature, animaux, jardin', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (34, 7, N'Sports, loisirs, transports', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (35, 7, N'Tourisme et voyage', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (36, 4, N'loisirs creatifs, decoration, bricolage', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (37, 3, N'Biographie, autobiographie', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (38, 0, N'Inclassable', NULL, 0)
INSERT [dbo].[SubTheme] ([subId], [theId], [subName], [subDescription], [subStatus]) VALUES (39, 3, N'sculture', NULL, 1)
SET IDENTITY_INSERT [dbo].[SubTheme] OFF
SET IDENTITY_INSERT [dbo].[Theme] ON 

INSERT [dbo].[Theme] ([theId], [theName], [theDescription], [theStatus]) VALUES (0, N'Inclassable', NULL, 0)
INSERT [dbo].[Theme] ([theId], [theName], [theDescription], [theStatus]) VALUES (1, N'bd & jeunesse', NULL, 0)
INSERT [dbo].[Theme] ([theId], [theName], [theDescription], [theStatus]) VALUES (2, N'littérature & fiction', NULL, 0)
INSERT [dbo].[Theme] ([theId], [theName], [theDescription], [theStatus]) VALUES (3, N'art, culture & société', NULL, 0)
INSERT [dbo].[Theme] ([theId], [theName], [theDescription], [theStatus]) VALUES (4, N'vie pratique', NULL, 0)
INSERT [dbo].[Theme] ([theId], [theName], [theDescription], [theStatus]) VALUES (5, N'scolaire et universitaire', NULL, 0)
INSERT [dbo].[Theme] ([theId], [theName], [theDescription], [theStatus]) VALUES (6, N'savoirs', NULL, 0)
INSERT [dbo].[Theme] ([theId], [theName], [theDescription], [theStatus]) VALUES (7, N'nature et loisirs', NULL, 0)
INSERT [dbo].[Theme] ([theId], [theName], [theDescription], [theStatus]) VALUES (8, N'sport et societe', NULL, 1)
SET IDENTITY_INSERT [dbo].[Theme] OFF
SET IDENTITY_INSERT [dbo].[Vat] ON 

INSERT [dbo].[Vat] ([vatCode], [vatRate], [vatName], [vatStatus]) VALUES (0, 5.5, N'standard', 0)
INSERT [dbo].[Vat] ([vatCode], [vatRate], [vatName], [vatStatus]) VALUES (1, 20, N'beau livre', 0)
INSERT [dbo].[Vat] ([vatCode], [vatRate], [vatName], [vatStatus]) VALUES (2, 17, N'ancienne tva', 1)
SET IDENTITY_INSERT [dbo].[Vat] OFF
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9781444799132', 15)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782226328717', 4)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782226393197', 2)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782226399168', 1)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782253014997', 17)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782253083276', 4)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782253132943', 4)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782266130288', 3)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782266272575', 6)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782266276542', 5)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782290138922', 4)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782290145548', 13)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782290152331', 8)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782302064263', 16)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782312015774', 12)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782344020937', 14)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782412020869', 7)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782747082600', 11)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782755614664', 9)
INSERT [dbo].[Writing] ([booIsbn13], [autId]) VALUES (N'9782755614664', 10)
ALTER TABLE [dbo].[Address]  WITH CHECK ADD  CONSTRAINT [fkCusChargeId] FOREIGN KEY([cusChargeId])
REFERENCES [dbo].[Customer] ([cusId])
GO
ALTER TABLE [dbo].[Address] CHECK CONSTRAINT [fkCusChargeId]
GO
ALTER TABLE [dbo].[Address]  WITH CHECK ADD  CONSTRAINT [fkCusResidId] FOREIGN KEY([cusResidId])
REFERENCES [dbo].[Customer] ([cusId])
GO
ALTER TABLE [dbo].[Address] CHECK CONSTRAINT [fkCusResidId]
GO
ALTER TABLE [dbo].[Association]  WITH CHECK ADD  CONSTRAINT [fkBooAsso] FOREIGN KEY([booIsbn13])
REFERENCES [dbo].[Book] ([booIsbn13])
GO
ALTER TABLE [dbo].[Association] CHECK CONSTRAINT [fkBooAsso]
GO
ALTER TABLE [dbo].[Book]  WITH CHECK ADD  CONSTRAINT [fkbookeditor] FOREIGN KEY([ediId])
REFERENCES [dbo].[Editor] ([ediId])
GO
ALTER TABLE [dbo].[Book] CHECK CONSTRAINT [fkbookeditor]
GO
ALTER TABLE [dbo].[Book]  WITH CHECK ADD  CONSTRAINT [fkbookformats] FOREIGN KEY([forId])
REFERENCES [dbo].[Formats] ([forId])
GO
ALTER TABLE [dbo].[Book] CHECK CONSTRAINT [fkbookformats]
GO
ALTER TABLE [dbo].[Book]  WITH CHECK ADD  CONSTRAINT [fkbooklangcode] FOREIGN KEY([bookLangCode])
REFERENCES [dbo].[BookLanguage] ([bookLangCode])
GO
ALTER TABLE [dbo].[Book] CHECK CONSTRAINT [fkbooklangcode]
GO
ALTER TABLE [dbo].[Book]  WITH CHECK ADD  CONSTRAINT [fkbookVat] FOREIGN KEY([vatCode])
REFERENCES [dbo].[Vat] ([vatCode])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Book] CHECK CONSTRAINT [fkbookVat]
GO
ALTER TABLE [dbo].[Definitions]  WITH CHECK ADD  CONSTRAINT [fkBooIsbn13] FOREIGN KEY([booIsbn13])
REFERENCES [dbo].[Book] ([booIsbn13])
GO
ALTER TABLE [dbo].[Definitions] CHECK CONSTRAINT [fkBooIsbn13]
GO
ALTER TABLE [dbo].[Definitions]  WITH CHECK ADD  CONSTRAINT [fkKeyName] FOREIGN KEY([keyName])
REFERENCES [dbo].[Keywords] ([keyName])
GO
ALTER TABLE [dbo].[Definitions] CHECK CONSTRAINT [fkKeyName]
GO
ALTER TABLE [dbo].[Determinate]  WITH CHECK ADD  CONSTRAINT [fkDeterminatePurId] FOREIGN KEY([purId])
REFERENCES [dbo].[Purchase] ([purId])
GO
ALTER TABLE [dbo].[Determinate] CHECK CONSTRAINT [fkDeterminatePurId]
GO
ALTER TABLE [dbo].[Determinate]  WITH CHECK ADD  CONSTRAINT [fkDeterminateStaId] FOREIGN KEY([staCode])
REFERENCES [dbo].[OrderStatus] ([staCode])
GO
ALTER TABLE [dbo].[Determinate] CHECK CONSTRAINT [fkDeterminateStaId]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [fkAccessProfile] FOREIGN KEY([accProfileCode])
REFERENCES [dbo].[AccessProfile] ([accProfileCode])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [fkAccessProfile]
GO
ALTER TABLE [dbo].[Have]  WITH CHECK ADD  CONSTRAINT [fkBooHave] FOREIGN KEY([booIsbn13])
REFERENCES [dbo].[Book] ([booIsbn13])
GO
ALTER TABLE [dbo].[Have] CHECK CONSTRAINT [fkBooHave]
GO
ALTER TABLE [dbo].[OrderLine]  WITH CHECK ADD  CONSTRAINT [fkBookIsbn13] FOREIGN KEY([booIsbn13])
REFERENCES [dbo].[Book] ([booIsbn13])
GO
ALTER TABLE [dbo].[OrderLine] CHECK CONSTRAINT [fkBookIsbn13]
GO
ALTER TABLE [dbo].[Payment]  WITH CHECK ADD  CONSTRAINT [fkPurId] FOREIGN KEY([purId])
REFERENCES [dbo].[Purchase] ([purId])
GO
ALTER TABLE [dbo].[Payment] CHECK CONSTRAINT [fkPurId]
GO
ALTER TABLE [dbo].[Purchase]  WITH CHECK ADD  CONSTRAINT [fkAddDeliveryId] FOREIGN KEY([addDeliveryId])
REFERENCES [dbo].[Address] ([addId])
GO
ALTER TABLE [dbo].[Purchase] CHECK CONSTRAINT [fkAddDeliveryId]
GO
ALTER TABLE [dbo].[Purchase]  WITH CHECK ADD  CONSTRAINT [fkAddInvoiceId] FOREIGN KEY([addInvoiceId])
REFERENCES [dbo].[Address] ([addId])
GO
ALTER TABLE [dbo].[Purchase] CHECK CONSTRAINT [fkAddInvoiceId]
GO
ALTER TABLE [dbo].[Purchase]  WITH CHECK ADD  CONSTRAINT [fkCusId] FOREIGN KEY([cusId])
REFERENCES [dbo].[Customer] ([cusId])
GO
ALTER TABLE [dbo].[Purchase] CHECK CONSTRAINT [fkCusId]
GO
ALTER TABLE [dbo].[Purchase]  WITH CHECK ADD  CONSTRAINT [fkShippingCostId] FOREIGN KEY([shippingCostId])
REFERENCES [dbo].[ShippingCost] ([shipId])
GO
ALTER TABLE [dbo].[Purchase] CHECK CONSTRAINT [fkShippingCostId]
GO
ALTER TABLE [dbo].[Review]  WITH CHECK ADD  CONSTRAINT [fkCusReview] FOREIGN KEY([cusId])
REFERENCES [dbo].[Customer] ([cusId])
GO
ALTER TABLE [dbo].[Review] CHECK CONSTRAINT [fkCusReview]
GO
ALTER TABLE [dbo].[Review]  WITH CHECK ADD  CONSTRAINT [fkOrdLinReview] FOREIGN KEY([ordLineId])
REFERENCES [dbo].[OrderLine] ([ordLineId])
GO
ALTER TABLE [dbo].[Review] CHECK CONSTRAINT [fkOrdLinReview]
GO
ALTER TABLE [dbo].[SubTheme]  WITH CHECK ADD  CONSTRAINT [fkTheme] FOREIGN KEY([theId])
REFERENCES [dbo].[Theme] ([theId])
GO
ALTER TABLE [dbo].[SubTheme] CHECK CONSTRAINT [fkTheme]
GO
ALTER TABLE [dbo].[Writing]  WITH CHECK ADD  CONSTRAINT [fkAutWriting] FOREIGN KEY([autId])
REFERENCES [dbo].[Author] ([autId])
GO
ALTER TABLE [dbo].[Writing] CHECK CONSTRAINT [fkAutWriting]
GO
ALTER TABLE [dbo].[Writing]  WITH CHECK ADD  CONSTRAINT [fkBooWriting] FOREIGN KEY([booIsbn13])
REFERENCES [dbo].[Book] ([booIsbn13])
GO
ALTER TABLE [dbo].[Writing] CHECK CONSTRAINT [fkBooWriting]
GO
USE [master]
GO
ALTER DATABASE [MyLibrary] SET  READ_WRITE 
GO
