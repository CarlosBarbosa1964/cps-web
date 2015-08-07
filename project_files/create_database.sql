USE [master]
GO
/****** Object:  Database [IB_CPS_DB]    Script Date: 05/08/2015 14:40:24 ******/
CREATE DATABASE [IB_CPS_DB]
GO
ALTER DATABASE [IB_CPS_DB] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [IB_CPS_DB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [IB_CPS_DB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET ARITHABORT OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [IB_CPS_DB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [IB_CPS_DB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [IB_CPS_DB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [IB_CPS_DB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [IB_CPS_DB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [IB_CPS_DB] SET  MULTI_USER 
GO
ALTER DATABASE [IB_CPS_DB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [IB_CPS_DB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [IB_CPS_DB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [IB_CPS_DB] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [IB_CPS_DB]
GO
/****** Object:  User [ib]    Script Date: 05/08/2015 14:40:24 ******/
CREATE USER [ib] FOR LOGIN [ib] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [ib]
GO

/*# ---------------------------------------------------------------------- #*/
/*# Tables                                                                 #*/
/*# ---------------------------------------------------------------------- #*/
/*# ---------------------------------------------------------------------- #*/
/*# Add table "Groups"		                                               #*/
/*# ---------------------------------------------------------------------- #*/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Groups](
	[ID] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[active] [bit] NOT NULL,
	[description] [varchar](255) NULL,
	[last_update] [datetime] NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO

/*# ---------------------------------------------------------------------- #*/
/*# Tables                                                                 #*/
/*# ---------------------------------------------------------------------- #*/
/*# ---------------------------------------------------------------------- #*/
/*# Add table "Permissions"		                                           #*/
/*# ---------------------------------------------------------------------- #*/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Permissions](
	[ID] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[groupID] [numeric](19, 0) NULL,
	[system_access] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

/*# ---------------------------------------------------------------------- #*/
/*# Tables                                                                 #*/
/*# ---------------------------------------------------------------------- #*/
/*# ---------------------------------------------------------------------- #*/
/*# Add table "Persons"		                                               #*/
/*# ---------------------------------------------------------------------- #*/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Persons](
	[ID] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[city] [varchar](255) NULL,
	[district] [varchar](255) NULL,
	[doc] [varchar](255) NULL,
	[last_update] [datetime] NULL,
	[name] [varchar](255) NULL,
	[state] [varchar](255) NULL,
	[street] [varchar](255) NULL,
	[zipcode] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO


/*# ---------------------------------------------------------------------- #*/
/*# Tables                                                                 #*/
/*# ---------------------------------------------------------------------- #*/
/*# ---------------------------------------------------------------------- #*/
/*# Add table "Users"		                                               #*/
/*# ---------------------------------------------------------------------- #*/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[ID] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[personID] [numeric](19, 0) NULL,
	[username] [varchar](255) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[active] [bit] NOT NULL,
	[canChangePass] [bit] NOT NULL,
	[changePassNextLogon] [bit] NOT NULL,
	[expire_date] [datetime] NULL,
	[role] [varchar](255) NOT NULL,
	[groupID] [numeric](19, 0) NOT NULL,
	[isProtected] [bit] NOT NULL,
	[last_update] [datetime] NOT NULL,
 CONSTRAINT [PK__Users__3214EC2743D0087D] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [Username_UNIQUE]    Script Date: 05/08/2015 14:40:24 ******/
CREATE UNIQUE NONCLUSTERED INDEX [Username_UNIQUE] ON [dbo].[Users]
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_8enxf78sl229cy57yxv23eb7t] FOREIGN KEY([groupID])
REFERENCES [dbo].[Groups] ([ID])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_8enxf78sl229cy57yxv23eb7t]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_dl1c9oebv7vci0i69hoif6dfj] FOREIGN KEY([personID])
REFERENCES [dbo].[Persons] ([ID])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_dl1c9oebv7vci0i69hoif6dfj]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Users] FOREIGN KEY([ID])
REFERENCES [dbo].[Users] ([ID])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Users]
GO
USE [master]
GO
ALTER DATABASE [IB_CPS_DB] SET  READ_WRITE 
GO

/*# ---------------------------------------------------------------------- #*/
/*# Tables                                                                 #*/
/*# ---------------------------------------------------------------------- #*/
/*# ---------------------------------------------------------------------- #*/
/*# Add info into "Groups"	                                               #*/
/*# ---------------------------------------------------------------------- #*/

INSERT INTO [dbo].[Groups]
           ([active]
           ,[description]
           ,[last_update]
           ,[name])
     VALUES
           (true
           , ''
           ,GETDATE()
           ,"Administradores")
GO

SELECT @@IDENTITY AS 'IdentityG';
GO

/*# ---------------------------------------------------------------------- #*/
/*# Tables                                                                 #*/
/*# ---------------------------------------------------------------------- #*/
/*# ---------------------------------------------------------------------- #*/
/*# Add info into "Permissions"	                                           #*/
/*# ---------------------------------------------------------------------- #*/

INSERT INTO [dbo].[Permissions]
           ([groupID]
           ,[system_access])
     VALUES
           (IdentityG
           ,true)
GO

/*# ---------------------------------------------------------------------- #*/
/*# Tables                                                                 #*/
/*# ---------------------------------------------------------------------- #*/
/*# ---------------------------------------------------------------------- #*/
/*# Add info into "Users"	                                               #*/
/*# ---------------------------------------------------------------------- #*/

INSERT INTO [dbo].[Users]
           ([personID]
           ,[username]
           ,[password]
           ,[active]
           ,[canChangePass]
           ,[changePassNextLogon]
           ,[expire_date]
           ,[role]
           ,[groupID]
           ,[isProtected]
           ,[last_update])
     VALUES
           (NULL
           ,'Admin'
           ,'6EAAF868BFC7D3F97B1ADF3B68D6094CE5F5ACFA704692ACB996DC5B1900F319'
           ,true
           ,true
           ,false
           ,NULL
           ,'ROLE_USER'
           ,IdentityG
           ,true
           ,GETDATE())
GO
