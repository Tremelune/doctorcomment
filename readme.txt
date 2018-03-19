OVERVIEW

I chose to do this in Java, presumably to show off the impressive verbosity of the ecosphere. In addition to this
verbosity, I took a sort of long-winded approach to (hopefully) demonstrate some ways to keep the system decoupled and
flexible. Even in Java, it could have been more succinct, but I pretended this app would need to survive for years
without a rewrite, during which dozens of unfamiliar devs would pass through and make changes. I think it's in a good
place to grow by orders of magnitude, if the basic architecture/pattern is stuck to. It's...not a particularly
efficient approach to what is effectively a CRUD app, but I digress...

ORGANIZATION
Software benefits greatly from separating the UI/API code from the rest of the system. Similarly, anything that hits
the disk, a datastore, or an external resource (say, Facebook's API) should likewise be isolated form teh rest of the
system and each other.