use iNetVOD

declare @ProviderID varchar(64)
declare @ProviderName varchar(128)
declare @ConnectionURL varchar(1024)

set @ProviderID = 'id'
set @ProviderName = 'name'
set @ConnectionURL = 'url'

insert into Provider (ProviderID, Name)
values (@ProviderID, @ProviderName)

insert ProviderConnection (ProviderConnectionID, ProviderID, ProviderConnectionType, ConnectionURL)
values (newid(), @ProviderID, 'Rss2', @ConnectionURL)

select * from Provider where ProviderID = @ProviderID
select * from ProviderConnection where ProviderID = @ProviderID
--select * from Show s join ShowProvider sp on s.ShowID = sp.ShowID where ProviderID = @ProviderID
