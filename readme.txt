OVERVIEW
I chose to do this in Java, presumably to show off the impressive verbosity of the ecosphere. In addition to this intrinsic verbosity, I took a sort of long-winded approach to (hopefully) demonstrate some ways of keeping the system decoupled and flexible. Even in Java, it could have been more succinct, but I pretended this app would need to survive for years without a rewrite, during which dozens of unfamiliar devs would pass through and make changes. I think it's in a good place to grow well beyond triviality if the basic architecture/pattern is stuck to. It's...not a particularly efficient approach to what is effectively a CRUD app, but hey...we're building more than CRUD in life (hopefully).

API
There are four endpoints for manipulating comments:

POST /comments
GET /comments/<id>
PUT /comments/<id>
POST /comments/<id>/deactivations

If this were a public-facing API, I'd want thorough, explicit, detailed documentation on request/response structure, expected behavior, example code, etc...but since it's my fellow engineers, I'd probably just point them to the only Source of Truth: the code. Or, if you have the luxury, some page generated via YAML based on the code and comments...

ORGANIZATION
Software benefits greatly from separating the UI/API code from the rest of the system. Similarly, anything that hits the disk, a datastore, or an external resource (say, Facebook's API) should likewise be isolated from the rest of the system and each other. They are all tied together by the Business layer of classes, which should have no knowledge of what UI is being used (website vs CLI vs batch job), how things are being persisted (disk, RDB, NoSQL, S3), or the details of Facebook's API.

TESTING
There are two (arguably three) types of tests here. Unit tests are named SomethingTest, integration and endpoint tests are named SomethingIT to differentiate them, should one choose to run them in a different manner or at a different frequency. Unit tests focus on switchy logic while integration tests focus on the system as a whole. The endpoint tests hit the "real" database, but they should probably just focus on request/response/routing stuff...It just turned out to be easier to hit the real database instead of mocking stuff out. Testing is all about the best bang-for-buck, and different features call for different testing approaches.

RUNNING
Since there's no real UI, I've just been running the tests (they all pass, consistently) to ensure this app is doing what it should. Assuming you have the bazillion dependencies that come with Spring Boot, the actual server can be run with this command:

./gradlew bootRun

It will then be reachable at this URL:

http://localhost:8080/

BONUS
Profanity - Lots of ways to skin this cat. I would probably google for a popular enumeration of slurs, load them from a file or simply hardcode them as constants, and run all the words in new comments against them. If there's a match, you can either reject the comment or mask the word...If you want to really prevent people from getting fcuk and b4lls into comments, well, that's onerous. I bet there's a company with a complex system to identify the many ways in which a hilarious person can circumnavigate language filters. There are plenty of ways to slander a doctor without using profanity anyway, so...from a business standpoint, I would say take the easy approach and try not to worry about it until it is revealed as a significant concern.

Auth - Lots of ways to skin this cat. Just about every platform and framework has some form of built-in authentication and security, so I would just use that. Most of them boil down to storing a username and hashed password in a datastore, and keeping credentials in the HTTP session. The more built-in stuff you use, the better, because there are many eyes and tests and audits for giant projects such as Spring and Ruby on Rails. Rolling your own is...risky at best.

Approved comments - Unless there were some larger business requirements, I'd just change the Comment.active boolean to a Comment.status enum, and add a status for "unapproved". The CommentFinder would simply filter out comments that were not status APPROVED. From there, you'd have some kind of list of unapproved comments displayed to an admin with sufficient privileges, who could click something that would have another endpoint to activate them.