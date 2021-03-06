USE [TeleJY]
GO
/****** Object:  Table [dbo].[sys_resource]    Script Date: 09/02/2018 17:08:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[sys_resource](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[description] [varchar](100) NULL,
	[type] [varchar](20) NOT NULL,
	[parent_id] [int] NULL,
	[path_url] [varchar](100) NULL,
	[iden] [int] NULL,
	[iden_will] [int] NULL,
	[iden_will_name] [varchar](50) NULL,
	[useble] [tinyint] NULL,
	[create_time] [datetime] NULL,
 CONSTRAINT [PK_sys_resource] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_resource', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_resource', @level2type=N'COLUMN',@level2name=N'description'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'资源类型，menu：目录，button：按钮' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_resource', @level2type=N'COLUMN',@level2name=N'type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'父id(目录id)，代表类型为button的按钮属于哪个目录' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_resource', @level2type=N'COLUMN',@level2name=N'parent_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'资源请求的URL' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_resource', @level2type=N'COLUMN',@level2name=N'path_url'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'标识，类型为button时，区分这个资源，如 1：新增，2：修改，3：删除，4导出。查询为基本功能，默认为0，数据库中可以不存这条记录' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_resource', @level2type=N'COLUMN',@level2name=N'iden'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'期望标识，意思为当权限配置出现第三种时，可以随意配置类型为button，只要标识一样，就作为一种权限处理' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_resource', @level2type=N'COLUMN',@level2name=N'iden_will'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'期望标识的名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_resource', @level2type=N'COLUMN',@level2name=N'iden_will_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否可用 1：可用， 0：不可用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_resource', @level2type=N'COLUMN',@level2name=N'useble'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_resource', @level2type=N'COLUMN',@level2name=N'create_time'
GO
ALTER TABLE [dbo].[sys_resource] ADD  CONSTRAINT [DF_sys_resource_create_time]  DEFAULT (getdate()) FOR [create_time]
GO
