(defn min_k
  "	Write a function to find minimum k records from vector list. https://www.geeksforgeeks.org/cljthon-find-minimum-k-records-from-vector-list/ - in this case a verbatim coclj of test cases"
  [test_list K]
  (let [k (max 0 (or K 0))
        coll (or test_list [])]
    (->> coll
         (sort-by second)
         (take k)
         vec)))