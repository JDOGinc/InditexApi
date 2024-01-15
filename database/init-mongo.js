db.createUser({
    user: "user",
    pwd: "password",
    roles: ["readWrite", "dbAdmin"]
});

db = db.getSiblingDB("Inditex");

db.createCollection("Products");

db.Products.insertMany(
    [{
        "_id": 1,
        "name": "V-NECH BASIC SHIRT",
        "stock": {
            "sizes": {
                "S": 4,
                "M": 9,
                "L": 0
            }
        },
        "sales_units": 100
    },
    {
        "_id": 2,
        "name": "CONTRASTING FABRIC T-SHIRT",
        "stock": {
            "sizes": {
                "S": 35,
                "M": 9,
                "L": 9
            }
        },
        "sales_units": 100
    },
    {
        "_id": 3,
        "name": "RAISED PRINT T-SHIRT",
        "stock": {
            "sizes": {
                "S": 20,
                "M": 2,
                "L": 20
            }
        },
        "sales_units": 80
    },
    {
        "_id": 4,
        "name": "PLEATED T-SHIRT",
        "stock": {
            "sizes": {
                "S": 25,
                "M": 30,
                "L": 10
            }
        },
        "sales_units": 25
    },
    {
        "_id": 5,
        "name": "CONTRASTING LACE T-SHIRT",
        "stock": {
            "sizes": {
                "S": 0,
                "M": 1,
                "L": 0
            }
        },
        "sales_units": 650
    },
    {
        "_id": 6,
        "name": "SLOGAN T-SHIRT",
        "stock": {
            "sizes": {
                "S": 9,
                "M": 2,
                "L": 5
            }
        },
        "sales_units": 20
    }]);