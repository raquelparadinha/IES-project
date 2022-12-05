db.createUser(
    {
        user  : "grupo",
        pwd   : "estiado",
        roles : [
            {
                role : "dbOwner",
                db   : "mongodb"
            }
        ]
    }
)