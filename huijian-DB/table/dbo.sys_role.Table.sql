USE [TeleJY]
GO
/****** Object:  Table [dbo].[sys_role]    Script Date: 09/02/2018 17:08:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
	[description] [varchar](100) NULL,
	[useble] [tinyint] NOT NULL,
	[create_time] [datetime] NULL,
	[create_user_id] [int] NULL,
 CONSTRAINT [PK_sys_role] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'description'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否可用 1：可用， 0：不可用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'useble'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'create_user_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户角色表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role'
GO
ALTER TABLE [dbo].[sys_role] ADD  CONSTRAINT [DF_sys_role_useble]  DEFAULT ((1)) FOR [useble]
GO
ALTER TABLE [dbo].[sys_role] ADD  CONSTRAINT [DF_sys_role_create_time]  DEFAULT (getdate()) FOR [create_time]
GO
