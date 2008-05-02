use iNetVOD


exec Provider_GetAll
exec Provider_Report 'unknown'

exec Provider_Insert 'id', 'Name', 0
exec Provider_Update 'id', 'Name', 0

exec ProviderConnection_MoveNewProviderID 'pc_id', 'p_id'


--exec Provider_Purge 'id'


select * from provider where providerid not in (select distinct providerid from showprovider where showAvail = 'Available')
