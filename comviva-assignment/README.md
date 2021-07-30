1) Typeahead suggestions enable users to search for known and frequently searched terms. As the user types into the search box, it tries to predict the query based on the characters the user has entered and gives a list of suggestions to complete the query. Typeahead suggestions help the user to articulate their search queries better. It’s not about speeding up the search process but rather about guiding the users and lending them a helping hand in constructing their search query. What data structure would be used for the same and how?

Solution :-
The solution is implemented with Trie data structure. Where it will form the trie nodes graph, each trie node contains the character, map of <Charater,Trie> as children and isEndOfWord.
Each trie node can have maximum 26 child nodes('a' to 'z)'.

folder src/trie has below methods.
i) insert :-For each character of word , it will create trie node. Each trie node either map to the root or children path as we process.
When word is complete will set flag isEndOfWord to true and will increase the noOfHits by 1.
ii) search : This method iterate on the input prefix and find the last node from root using last character of prefix. From this last node will trace all the children path, when we find the isEndOfWord flag true, will add word and frequency into list.
iii) topSuggestions :- this method sort list of suggestions based on frequency(no of times user search word) in decreasing order.

----------------------------------------------------------------------------------
2. Design an API Rate limiting system that monitors the number of requests per a window time(i.e. could be second/hour etc) a service agrees to allow. IF the number of requests exceeds the allowed limit the rate limiter should block all excess calls

System should be designed considering the following:

a) Rate limiting should work for a distributed set up as the APIs are available through a group of API Gateways -> use redis and sorted set in redis along with sliding window counter in this approch we will store timestamp of window in array but in counter window t is more than slindng window in which if we have two same time stamp like 11:29:30 and 11:29:30 ,so we will store in array like timestamp with counter. b) What database would be used and the rationale behind the choice c) how would throttling be done :using sliding window counter algorithm d) the system should be highly available :redis

solution : doc file at root directory named as api-rate-limiter.

----------------------------------------------------------------------------------

3. How would you design a garbage collection system? The idea here is to design a system that recycles unused memory in the program. Here the key is to find which piece of memory is unused. What data structure would be chosen and how would it be used to ensure garbage collection does not halt the main process?

Solution: 
The solution is implemented using graph data structure(which contains graph of references) and mark and sweep algorithm.

GC: This is the main entry point of the module and performs following operations
i) get(Object) : Add new object to the reference graph
ii) release(Object) : To indicate that object is no more required
iii) gc() : Request to start the garbage collection
GCTask : This class is to traverse through graph and identify unused references. It also push collected objects(unused references) to finalize queue, which is taken care by FinalizeTask
FinalizeTask: FinalizeTask concurrently processes(calls finalize) objects pushed to finalize queue.
Reference: Basic reference implementation is node of graph.

Use cases covered: avoiding cyclic references during traversal. works for both objects with or without finalize method. non blocking implementation of finalize.