use das
go

create table dbo.identificacion_personas
(
 nro_persona	integer			not null 
				constraint PK__identificacion_personas__END primary key,
 apellido		varchar(100)	not null,
 nombre			varchar(100)	not null,
 tipo_documento	varchar(3)		not null
				constraint CK__identificacion_personas__tipo_documento__END check (tipo_documento in ('DNI', 'LC', 'LE', 'CI', 'PAS')),
 nro_documento	varchar(20)		not null,
 sexo			char(1)			not null
				constraint CK__identificacion_personas__sexo__END check (sexo in ('F', 'M'))
				constraint DF__identificacion_personas__sexo__F__END default 'F',
 vive			char(1)			not null
				constraint CK__identificacion_personas__vive__END check (vive in ('S', 'N'))
				constraint DF__identificacion_personas__vive__S__END default 'S',
 constraint UK__identificacion_personas__END unique (tipo_documento, nro_documento)
);
go

create procedure dbo.get_lista_personas
(
 @string_busqueda	varchar(255) = null
)
as
begin

  set @string_busqueda = '%' + isnull(ltrim(rtrim(@string_busqueda)), '') + '%' 

  select tipo_documento = tipo_documento,
         nro_documento  = nro_documento,
		 apellido       = apellido,
		 nombre         = nombre,
		 sexo           = sexo,
		 vive           = vive,
		 nro_persona    = nro_persona
    from dbo.identificacion_personas (nolock)
   where (tipo_documento                       like @string_busqueda
      or  nro_documento                        like @string_busqueda
	  or  tipo_documento + '-' + nro_documento like @string_busqueda
	  or  apellido                             like @string_busqueda
	  or  nombre                               like @string_busqueda)
   order by apellido,
            nombre

end
go

insert into dbo.identificacion_personas(nro_persona, apellido, nombre, tipo_documento, nro_documento, sexo)
values(1, 'PASTARINI', 'MARIELA EDITH', 'DNI', '28345381', 'F'),
      (2, 'GONZALEZ-GRANDA', 'MARIA DEL PILAR', 'DNI', 'F6678610', 'F')
go

create procedure dbo.ins_persona
(
 @apellido			varchar(100),
 @nombre			varchar(100),
 @tipo_documento	varchar(3),
 @nro_documento		varchar(20),
 @sexo				char(1),
 @vive				char(1)
)
as
begin
 
   declare @nro_persona integer

   if exists(select *
               from dbo.identificacion_personas (nolock)
			  where tipo_documento = @tipo_documento
			    and nro_documento  = @nro_documento)
	  begin
	    raiserror('Existe otra persona con el mismo tipo y nro. de documento', 16, 1)
		return
	  end		 

   select @nro_persona = isnull(max(nro_persona), 0) + 1
     from dbo.identificacion_personas (tablockx)

   insert into dbo.identificacion_personas(nro_persona, apellido, nombre, tipo_documento, nro_documento, sexo, vive)
   values(@nro_persona, upper(@apellido), upper(@nombre), @tipo_documento, @nro_documento, @sexo, @vive)

end
go

create procedure dbo.act_persona
(
 @nro_persona		integer,
 @apellido			varchar(100),
 @nombre			varchar(100),
 @tipo_documento	varchar(3),
 @nro_documento		varchar(20),
 @sexo				char(1),
 @vive				char(1)
)
as
begin

   if exists(select *
               from dbo.identificacion_personas (nolock)
			  where nro_persona    <> @nro_persona
			    and tipo_documento  = @tipo_documento
			    and nro_documento   = @nro_documento)
	  begin
	    raiserror('Existe otra persona con el mismo tipo y nro. de documento', 16, 1)
		return
	  end		 

  update dbo.identificacion_personas
     set apellido       = upper(@apellido), 
	     nombre         = upper(@nombre), 
		 tipo_documento = @tipo_documento, 
		 nro_documento  = @nro_documento, 
		 sexo           = @sexo, 
		 vive           = @vive
   where nro_persona = @nro_persona

end
go

create procedure dbo.del_persona
(
 @nro_persona integer
)
as
begin
  
  delete
    from dbo.identificacion_personas
   where nro_persona = @nro_persona

end
go

create procedure dbo.get_datos_persona
(
 @nro_persona integer
)
as
begin
  
  select apellido       = apellido, 
	     nombre         = nombre, 
		 tipo_documento = tipo_documento, 
		 nro_documento  = nro_documento, 
		 sexo           = sexo, 
		 vive           = vive,
		 nro_persona    = nro_persona
    from dbo.identificacion_personas (nolock)
   where nro_persona = @nro_persona

end
go
