**AlgorithmsLab2**
==============
*Input File Format*: Your program will read its input from a plain-text file. The first line of the input
contains two positive integers, n and m, separated by a single space, where n represents the number of
devices, and m denotes the number of trace entries. Each of the next m lines contains a trace (one trace per
line) in the format defined above. The last line of the input contains the query whose format we defined above.

*Output Block Format*: The output of your program must follow the format described next. If the answer
to the query (encoded in the last line of the input file) is no, then the output should be 0.
If the answer to the query is yes, then the first line of output should print the non-negative integer k that
denotes the number of exchanges that were required for delivering the message at the destination, starting
from the source node in the query. The subsequent k lines should print the trace entries involved in the
message delivery sorted in the non-decreasing order of time.

