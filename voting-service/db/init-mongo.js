// init-mongo.js
// Usage: mongosh "mongodb+srv://<user>:<password>@<cluster>.mongodb.net/votaya" --file init-mongo.js
// Or: connect with mongosh and paste/execute the contents below.

// Select database (creates if not present)
use votaya;

// Create collection (optional; Mongo will create on insert)
db.createCollection("votes");

// Create indexes
db.votes.createIndex({ certificateId: 1 }, { unique: true });
db.votes.createIndex({ cedula: 1 });
db.votes.createIndex({ timestamp: 1 });

// Insert an example document (optional)
db.votes.insertOne({
  certificateId: "CERT-EXAMPLE",
  transactionHash: "0xexample",
  cedula: "1234567890",
  message: "Documento de prueba",
  timestamp: new Date()
});

print('init-mongo.js: OK - coleccion votes creada y indices aplicados');
