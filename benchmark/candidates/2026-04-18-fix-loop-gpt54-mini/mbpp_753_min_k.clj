(defn min_k
  "	Write a function to find minimum k records from vector list. https://www.geeksforgeeks.org/cljthon-find-minimum-k-records-from-vector-list/ - in this case a verbatim coclj of test cases"
  [test_list K]
  (vec (take K (sort test_list))))