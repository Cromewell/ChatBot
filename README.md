# ChatBot
A little chatbot.

If a sentence contains: 
- "hi", "hello", "hey", "nice to meet you"
- "how are you", "how r u", "how r you", "how are u", "how're you", "how're u"
- "what are you doing", "what r u doing", "what r u doin", "what are u doing", "what r you doing", "what are you doin", "what r you doin", "what are u doin", "what're you doing", "what're u doing", "what're u doin", "what're you doin"
- "exit", "goodbye", "ciao", "bye", "im out", "i'm out"
- "thank you", "ty", "thanks", "thx"
- "lol", "lool", "haha", "lel", "lul", "hehe"
- "tell me a joke", "more jokes"
- "how old are you", "when were you born", "what is your age", "what's your age"
- "nice", "okay", "that was funny", "cool", "wow", "that is good", "that's good", "thats good", "true", "yeah", "yes", "np", "good"
- "tell me wise words", "wise words", "what is wise", "do you have an advise", "advise", "wisdom", "if i had wisdom"
- "what time is it", "what is the time", "what's the time"

Or the sentence is equal to:
- "why?", "why why why?"

The bot gets triggered.

After a command:
- "timer hh:mm:ss" a timer is set. hh stands for hours, mm for minutes, ss for seconds. For example "timer 00:00:10" will ring after 10 seconds
- "google search string" opens the default browser and searches for the following string -> in this case it would google for "search string"
- "amazon countryId search string" opens the default browser and searches for the following on amazon.countryId -> amazon results for "search string". For example "amazon de programmieren" will search for "programmieren" on amazon.de.
- "wikipedia countryId search string" opens the default browser and searches for the following on wikipedia.countryId -> wikipedia results for "search string". For example "wikipedia en leonardo da vinci" will search for "leonardo da vinci" on wikipedia. Doesn't work properly for de. en as countryId works just fine!
